package JAVA_TWO;

import java.util.ArrayList;

public class Kramer{
    //克拉默类只要求输入n元1次的数字矩阵
    private int n;  //n个未知数n阶系数矩阵
    private double[][] matrix; //输入的原始增广矩阵
    private double[] constan = new double[n];  //常数列
    private double[][] coeMatrix; //系数矩阵
    private double[] batchMatrixResult;  //分子结果集合
    private double[] resultSet; //结果集合
    private ArrayList<double[][]> data = new ArrayList<>();  //替换列矩阵的集合
    private double coeMatrixResult;


    Kramer(int n ,double[][] matrix){
        setN(n);
        setMatrix(matrix);
    }

    Kramer(KramerDataHandler kramerDataHandler){
        setMatrix(kramerDataHandler.getAugMatrix());
        setN(kramerDataHandler.getN1());
    }

    private void setCoeMatrixResult(double coeMatrixResult) {
        this.coeMatrixResult = coeMatrixResult;
    }

    public double getCoeMatrixResult() {
        return coeMatrixResult;
    }

    private void setBatchMatrixResult(double[] coeMatrixResult) {
        this.batchMatrixResult = coeMatrixResult;
    }

    public double[] getBatchMatrixResult() {
        double[] cloneCoeMatrixResult = new double[batchMatrixResult.length];
        for (int i=0;i<cloneCoeMatrixResult.length;i++){
            cloneCoeMatrixResult[i] = batchMatrixResult[i];
        }
        return cloneCoeMatrixResult;
    }

    private void setData(ArrayList<double[][]> data) {
        this.data = data;
    }

    public ArrayList<double[][]> getData() {
        ArrayList<double[][]> cloneData = new ArrayList<>();
        cloneData.addAll(data);
        return cloneData;
    }

    private void setResultSet() {
        this.resultSet = Result();
    }

    public double[] getResultSet() {
        setResultSet();
        double[] cloneResultSet = new double[resultSet.length];
        for (int i =0;i<resultSet.length;i++){
            cloneResultSet[i] = resultSet[i];
        }
        return cloneResultSet;
    }  //输出克隆结果集

    private void setCoeMatrix(double[][] coeMatrix) {
        this.coeMatrix = coeMatrix;
    }

    public double[][] getCoeMatrix() {
        double[][] cloneCoeMatrix = new double[this.coeMatrix.length][this.coeMatrix.length];
        for (int i=0;i< this.coeMatrix.length;i++){
            for (int k=0;k< this.coeMatrix.length;k++){
                cloneCoeMatrix[i][k] = this.coeMatrix[i][k];
            }
        }
        return cloneCoeMatrix;
    }

    private void setConstan(double[] constan) {
        this.constan =constan;
    }

    public double[] getConstan() {
        double[] cloneConstan = new double[this.constan.length];
        for (int i=0;i<this.constan.length;i++){
            cloneConstan[i] = this.constan[i];
        }
        return cloneConstan;
    }

    private void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public double[][] getMatrix() {
        double[][] cloneMatrix = new double[this.matrix.length][this.matrix[0].length];
        for(int i=0;i<this.matrix.length;i++){
            for (int k=0;k<this.matrix.length+1;k++){
                cloneMatrix[i][k] = this.matrix[i][k];
            }
        }
        return cloneMatrix;
    }  //外部的增广矩阵深拷贝，其实就是写多了

    private void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    private void MatrixDataTransform(){
        double[][] cloneAugMatrix = getMatrix();
        double[] cloneConstan = new double[getMatrix().length];
        for (int i=0;i<getMatrix().length;i++){
            cloneConstan[i] = getMatrix()[i][getMatrix().length];
        }
        setConstan(cloneConstan);
        double[][] cloneCoeMatrix = new double[getMatrix().length][getMatrix().length];
        cloneAugMatrix = getMatrix();
        for (int i=0;i<getMatrix().length;i++){
            for (int k=0;k<getMatrix().length;k++){
                cloneCoeMatrix[i][k] = getMatrix()[i][k];
            }
        }
        setCoeMatrix(cloneCoeMatrix);
    }  //增广矩阵的数据分离

    private double calculateMatrix(){
        Matrix matrix = new Matrix(getN(),getCoeMatrix());
        setCoeMatrixResult(matrix.getResult());
        return matrix.getResult();
    }  //计算克拉默法则的分母

    private double[] calculateMatrix(ArrayList<double[][]> data){
        Matrix matrix = new Matrix(data.size(),data);
        return matrix.getResultSet();
    }   //同时处理多个克拉默法则的分子

    private double[][] CloneMatrix(double[][] coeMatrix){
        double[][] clone = new double[coeMatrix.length][coeMatrix.length];
        for (int i =0;i<coeMatrix.length;i++){
            for (int k=0;k<coeMatrix.length;k++){
                clone[i][k] = coeMatrix[i][k];
            }
        }
        return clone;
    } //原系数矩阵深拷贝

    private double[][] CopyMatrix(double[][] coeMatrix){
        double[][] copy = new double[coeMatrix.length][coeMatrix.length];
        for (int i =0;i<coeMatrix.length;i++){
            for (int k=0;k<coeMatrix.length;k++){
                copy[i][k] = coeMatrix[i][k];
            }
        }
        return copy;
    }  //完成矩阵替换列的矩阵副本

    private void ShiftMatricx(double[] constan, double[][] coeMatrix){
        int length = coeMatrix.length;
        ArrayList<double[][]> data = new ArrayList<>();
        for(int m =0;m < length; m++){
            double[][] matrixCache = CloneMatrix(coeMatrix);
            for (int n=0; n<length;n++){
                matrixCache[n][m] = constan[n];
            }
            data.add(CopyMatrix(matrixCache));
        }
        setData(data);
    }  //进行矩阵替换列

    private double[] Result(){
        MatrixDataTransform();
        ShiftMatricx(getConstan(),getCoeMatrix());
        setBatchMatrixResult(calculateMatrix(getData()));
        double[] result = new double[getN()];
        for (int i =0;i<result.length;i++){
           result[i] = getBatchMatrixResult()[i]/calculateMatrix();
        }
        return result;
    } //整体结果计算

}
