package JAVA_TWO;

import java.util.ArrayList;

public class MatrixResultDataOutput implements ResultDataOutput {
    //前面已经完成了数据处理
    //这里将完成数据美化输出
    /*它提供多个方法处理数据
        一、输出结果
        二、输出结果集
        三、输出将要参与计算的矩阵
        四、输出将要参与批处理计算的矩阵集
        五、构造方法的多次重载提供可选输出类型
     */
    private double[] resultSet;  //结果集
    private double result;  //结果

    private double[][] cloneMatrix;  //矩阵的克隆
    private ArrayList<double[][]> cloneBatchMatrix;  //一群矩阵的克隆

    MatrixResultDataOutput(double result){
        setResult(result);
    }
    MatrixResultDataOutput(double result,double[][] cloneMatrix){
        setResult(result);
        setCloneMatrix(cloneMatrix);
    }
    MatrixResultDataOutput(Matrix matrix){
        setResultSet(matrix.getResultSet());
        setCloneBatchMatrix(matrix.getData());
    }

    private void setResult(double result) {
        this.result = result;
    }

    private void setResultSet(double[] resultSet) {
        this.resultSet = resultSet;
    }

    private void setCloneMatrix(double[][] cloneMatrix) {
        this.cloneMatrix = cloneMatrix;
    }

    private void setCloneBatchMatrix(ArrayList<double[][]> cloneBatchMatrix) {
        this.cloneBatchMatrix = cloneBatchMatrix;
    }

    private double getResult() {
        return result;
    }

    private double[] getResultSet() {
        return resultSet;
    }

    private double[][] getCloneMatrix() {
        return cloneMatrix;
    }

    private ArrayList<double[][]> getCloneBatchMatrix() {
        return cloneBatchMatrix;
    }

    public void PrintResult(){
        System.out.println("计算得出结果："+getResult());
    }  //只输出结果

    public void PrintResult_MatrixInfo(){
        try{
            if(getCloneMatrix() == null){
                throw new NullPointerException("发现空指针异常");
            }
        }catch (NullPointerException e){
            System.out.println("你并未初始化参与计算的矩阵无法得到矩阵数据输出");
            System.out.println("计算结果："+getResult());
            return;
        }
        System.out.println("参与计算的矩阵如下");
        System.out.println("**********");
        for (int i=0;i<getCloneMatrix().length;i++){
            System.out.print("|");
            for (int k=0;k< getCloneMatrix().length;k++){
                System.out.print(getCloneMatrix()[i][k]+",");
            }
            System.out.print("|"+"\n");
        }
        System.out.println("**********");
        System.out.println("矩阵计算结果："+getResult());
    }  //输出带有参与运算的矩阵和结果

    public void PrintResultSet() {
        System.out.println("得到结果集" + "\n" + "**********");
        System.out.print("{");
        for (int i = 0; i < getResultSet().length; i++) {
            System.out.print(getResultSet()[i] + ",");
        }
        System.out.println("}"+"\n"+"**********");

    }  //只输出结果集

    public void PrintResultSet_BatchMatrixInfo(){
        try{
            if (getCloneBatchMatrix() == null){
                throw new NullPointerException("空指针异常");
            }
        }catch (NullPointerException e){
            System.out.println("你并未初始化参与批处理的矩阵集无法得到矩阵集的数据输出");
            PrintResultSet();
            return;
        }
        System.out.println("参与批处理的矩阵集"+"\n"+"**********");
        for (int i=0;i<getCloneBatchMatrix().size();i++){
            System.out.println("参与批处理的第"+(i+1)+"个矩阵");
            for (int k=0;k<getCloneBatchMatrix().get(0).length;k++){
                System.out.print("|");
                for (int j=0;j<getCloneBatchMatrix().get(0).length;j++){
                    System.out.print(getCloneBatchMatrix().get(i)[k][j]+",");
                }
                System.out.println("|");
            }
        }
        System.out.println("**********");
        PrintResultSet();
    }  //输出带有参与批处理的矩阵集和结果集
}
