package JAVA_TWO;

import java.util.ArrayList;

public class KramaeResultDataOutput implements ResultDataOutput{

    //美化并处理输出
    //作为一个试水作品，试一下实现接口怎么用

    private double[] resultSet;  //结果集合
    private double[][] augMatrix;  //增广矩阵
    private ArrayList<double[][]> data;  //完成替换列的矩阵集合
    private double[] constan;  //常数列
    private Kramer kramer;
    private double coeMatrixResult;
    private double[][] coeMatrix;
    private double[] batchMatrixResult;

    private void setBatchMatrixResult(double[] batchMatrixResult) {
        this.batchMatrixResult = batchMatrixResult;
    }

    private void setData(ArrayList<double[][]> data) {
        this.data = data;
    }

    private void setResultSet(double[] resultSet) {
        this.resultSet = resultSet;
    }

    private void setConstan(double[] constan) {
        this.constan = constan;
    }

    private void setAugMatrix(double[][] augMatrix) {
        this.augMatrix = augMatrix;
    }

    private void setCoeMatrixResult(double coeMatrixResult) {
        this.coeMatrixResult = coeMatrixResult;
    }

    private void setCoeMatrix(double[][] coeMatrix) {
        this.coeMatrix = coeMatrix;
    }

    private void setKramer(Kramer kramer){
        this.kramer = kramer;
    }

    KramaeResultDataOutput(Kramer kramer){
        setKramer(kramer);
    }

    private void getKramerInfo(){
        setData(kramer.getData());
        setResultSet(kramer.getResultSet());
        setConstan(kramer.getConstan());
        setAugMatrix(kramer.getMatrix());
        setCoeMatrix(kramer.getCoeMatrix());
        setCoeMatrixResult(kramer.getCoeMatrixResult());
        setBatchMatrixResult(kramer.getBatchMatrixResult());
    }

    @Override
    public void PrintResultSet() {
        getKramerInfo();
        System.out.println("计算所得精确结果的集合："+"\n"+"**********");

        for (int i=0;i<this.resultSet.length;i++){
            System.out.print(resultSet[i]+",");
        }
        System.out.println("\n"+"**********"+"\n"+"以分数展示的结果:");
        System.out.println("**********");
        for(int i=0;i<batchMatrixResult.length;i++){
            System.out.print(batchMatrixResult[i]+"/"+coeMatrixResult+",");
        }
        System.out.println("\n"+"**********");
    }

    @Override
    public void PrintResultSet_BatchMatrixInfo() {
        getKramerInfo();
        System.out.println("增广矩阵数据展示"+"\n"+"**********");
        for(int i=0;i<augMatrix.length;i++){
            System.out.print("|");
            for (int k=0;k<augMatrix[0].length;k++){
                System.out.print(augMatrix[i][k]+",");
            }
            System.out.println("|");
        }
        System.out.println("**********"+"\n"+"系数矩阵数据展示"+"\n"+"**********");
        for (int i=0;i<coeMatrix.length;i++){
            System.out.print("|");
            for(int k=0;k<coeMatrix.length;k++){
                System.out.print(coeMatrix[i][k]+",");
            }
            System.out.println("|");
        }
        System.out.println("**********"+"\n"+"常数列展示"+"\n"+"**********");
        for (int i=0;i<constan.length;i++){
            System.out.println("|"+constan[i]+"|");
        }
        System.out.println("**********"+"\n"+"矩阵替换列数据展示"+"\n"+"**********");
        for (int i=0;i<data.size();i++){
            System.out.println("第"+(i+1)+"位替换列矩阵");
            for (int k=0;k<data.get(0).length;k++){
                System.out.print("|");
                for (int j=0;j<data.get(0).length;j++){
                    System.out.print(data.get(i)[k][j]+",");
                }
                System.out.println("|");
            }
        }
        System.out.println("**********");
        PrintResultSet();
    }
}
