package JAVA_TWO;

import java.util.Scanner;

public class KramerDataHandler {
    //专门处理克拉默计算使用的增广矩阵

    private Scanner sca;
    private int n1;
    private double[][] augMatrix;

    KramerDataHandler(Scanner sca){
        setSca(sca);
    }

    private KramerDataHandler(int n,double[][] augMatrix){
        setN(n);
        setAugMatrix(augMatrix);
    }

    private void setSca(Scanner sca) {
        this.sca = sca;
    }

    private Scanner getSca() {
        return sca;
    }

    private void setAugMatrix(double[][] augMatrix) {
        this.augMatrix = augMatrix;
    }

    public double[][] getAugMatrix() {
        double[][] cloneAugMatrix = new double[augMatrix.length][augMatrix.length+1];
        for (int i=0;i< augMatrix.length;i++){
            for (int k=0;k< augMatrix.length+1;k++){
                cloneAugMatrix[i][k] = augMatrix[i][k];
            }
        }
        return cloneAugMatrix;
    }

    public int getN1() {
        return n1;
    }

    private void setN(int n) {
        this.n1 = n;
    }

    public KramerDataHandler KramaerMatrixDataHandler(){
        System.out.println("使用克拉默法则的n元1次方程求解"+"\n"+"输入格式严格按照每条方程的系数排列，以“，”分隔元素");
        System.out.println("需要得到未知数的个数n");
        System.out.print(">");
        int n = getSca().nextInt();
        getSca().nextLine();
        System.out.println("输入方程内容");
        System.out.print(">");
        double[][] matrix = new double[n][n+1];
        String str = null;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int j = 0;
        while (true){
            str = getSca().nextLine();
            sb.append(str);
            count = sb.toString().split("[，,]").length;
            if (count == n*(n+1)){
                try{
                    for(int i=0;i<matrix.length;i++){
                        for (int k=0;k< matrix[0].length;k++){
                            matrix[i][k] = Double.parseDouble(sb.toString().split("[,，]")[j]);
                            j++;
                        }
                    }
                    break;
                }catch (NumberFormatException e){
                    System.out.println("解析失败"+"\n"+"出现非法数字");
                    System.out.println("正在清除数据");
                    sb.delete(0,sb.length());
                    count =0;
                    System.out.println("请重新输入");
                    System.out.print(">");
                }
            } else if (count < n*(n+1)) {
                System.out.println(count);
                System.out.print(">");
                sb.append(",");
            } else if (count > n*(n+1)) {
                System.out.println(count);
                System.out.println("当前输入的内容不能构成适用于克拉默法则的增广矩阵");
                System.out.println("正在清空数据");
                sb.delete(0,sb.length());
                count = 0;
                System.out.println("请重新输入:");
                System.out.print(">");
            }
        }
        System.out.println("解析成功，数据提交");
        for(int i=0;i< matrix.length;i++){
            for (int k=0;k< matrix.length+1;k++){
                System.out.print(matrix[i][k]+",");
            }
            System.out.println();
        }
        return new KramerDataHandler(n,matrix);
    }

}
