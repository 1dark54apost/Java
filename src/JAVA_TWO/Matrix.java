package JAVA_TWO;

import java.util.ArrayList;

public class Matrix {

    private int id ;
    private double[][] matrix ;   //单个矩阵
    private ArrayList<double[][]> data;  //批处理的矩阵
    private double result ;
    private double[] resultSet;

    Matrix(int n,double[][] matrix){
        setId(n);
        setMatrix(matrix);
    }
    Matrix(int n,ArrayList<double[][]> data){
        setId(n);
        setData(data);
    }

    Matrix(MatrixDataHandler matrixDataHandler){
        if (matrixDataHandler.getBatchMatrix() == null){
            setMatrix(matrixDataHandler.getMatrix());
        }else {
            setData(matrixDataHandler.getBatchMatrix());
        }
    }

    private void setResultSet() {
        this.resultSet = BatchcalculateMatrix(getData());
    }  //计算结果

    public double[] getResultSet() {
        setResultSet();
        double[] cloneResultSet = new double[resultSet.length];
        for (int i=0;i<resultSet.length;i++){
            cloneResultSet[i] = resultSet[i];
        }
        return cloneResultSet;
    }  //获取结果集

    private void setResult() {
        this.result = calculateMatrix(getMatrix());
    }  //计算结果集

    public double getResult() {
        setResult();
        return result;
    }  //获取结果

    private void setData(ArrayList<double[][]> data) {
        this.data = data;
    }

    public ArrayList<double[][]> getData() {
        ArrayList<double[][]> dataClone = new ArrayList<>();
        dataClone.addAll(data);
        return dataClone;
    }  //克隆矩阵集

    private void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        double[][] cloneMatrix = new double[matrix.length][matrix.length];
        for (int i=0;i<matrix.length;i++){
            for (int k=0;k<matrix.length;k++){
                cloneMatrix[i][k] = this.matrix[i][k];
            }
        }
        return cloneMatrix;
    }

    private double calculateMatrix(double[][] matrix){
        int n = matrix.length;
        if (n == 1){
            return matrix[0][0];
        }
        if (n == 2){
            return matrix[0][0]*matrix[1][1]-matrix[0][1]*matrix[1][0];
        }

        double result = 0;
        for (int j = 0;j<n;j++){   //subRow行   subCol 列
            int sign = (j%2 == 0)?1:-1;

            double[][] sub = new double[n-1][n-1];
            int subRow = 0;
            for (int i =0;i<n;i++){
                int subCol = 0;
                if (i == 0)continue;
                for (int k =0;k<n;k++){
                    if(k == j){
                        continue;
                    }
                    sub[subRow][subCol] = matrix[i][k];
                    subCol++;
                }
                subRow++;
            }
            result += sign*matrix[0][j]*calculateMatrix(sub);
        }

        return result;
    }

    private double[] BatchcalculateMatrix(ArrayList<double[][]> data){
        double[] resultSet = new double[data.size()];
        for (int i =0;i<data.size();i++){
            double[][] matrixCache = data.get(i);
            resultSet[i] = calculateMatrix(matrixCache);
        }
        return resultSet;
    }

}
