package JAVA_TWO;

import java.util.Scanner;
import java.util.ArrayList;

//他们从来不管数据怎么来，但我们需要扛起这个责任
public class MatrixDataHandler {

    //我们要求外部定义Scanner对象由外部定义数据来源
    //而我们获取外部的Scanner对象对它进行解析，处理内容并呈递给特定的计算逻辑类

    private Scanner sca;

    private double[][] matrix;
    private ArrayList<double[][]> batchMatrix;

    MatrixDataHandler(Scanner sca) {
        setSca(sca);
    }

    private MatrixDataHandler(double[][] matrix){
        setMatrix(matrix);
    }
    private MatrixDataHandler(ArrayList<double[][]> batchMatrix){
        setBatchMatrix(batchMatrix);
    }

    public double[][] getMatrix() {
        double[][] cloneMatrix = new double[matrix.length][matrix.length];
        for (int i=0;i< matrix.length;i++){
            for (int k=0;k< matrix.length;k++){
                cloneMatrix[i][k] = matrix[i][k];
            }
        }
        return cloneMatrix;
    }

    public ArrayList<double[][]> getBatchMatrix() {
        ArrayList<double[][]> cloneBatchMatrix = new ArrayList<>();
        cloneBatchMatrix.addAll(batchMatrix);
        return cloneBatchMatrix;
    }

    private void setSca(Scanner sca) {
        this.sca = sca;
    }

    private void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    private void setBatchMatrix(ArrayList<double[][]> batchMatrix) {
        this.batchMatrix = batchMatrix;
    }

    private Scanner getSca() {
        return sca;
    }

    private double[][] CloneArray(double[][] matrix) {
        double[][] cloneArray = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                cloneArray[i][k] = matrix[i][k];
            }
        }
        return cloneArray;
    }

    public MatrixDataHandler GeneralMatrixDataHandler() {
        System.out.println("请输入标准行列式内容，以‘，’分隔内容");
        System.out.println("<<警告：程序不保证结果对于用户的正确性>>");
        System.out.print(">");
        int count = 0;
        String str = null;
        double[][] matrix;
        str = getSca().nextLine();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while (true) {
            count = sb.toString().split("[,，]").length;
            if (count != (int) Math.sqrt(count) * (int) Math.sqrt(count) | sb.toString().isEmpty()) {
                System.out.print(">");
                str = getSca().nextLine();
                sb.append(",").append(str);
                continue;
            }
            matrix = new double[(int) Math.sqrt(count)][(int) Math.sqrt(count)];
            System.out.println("捕获到合法矩阵格式，正在进行格式解析");
            try {
                int j = 0;
                for (int i = 0; i < matrix.length; i++) {
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[i][k] = Double.parseDouble(sb.toString().split("[,，]")[j]);
                        j++;
                    }
                }
                System.out.println("解析成功");
                break;
            } catch (NumberFormatException e) {
                System.out.println("检测到非法字符，请重新输入");
                sb.delete(0, sb.length());
                str = null;
                count = 0;
                System.out.print(">");
                str = getSca().nextLine();
                sb.append(str);
            }
        }
        return new MatrixDataHandler(matrix);
    }  //标准行列式处理

    public MatrixDataHandler BatchcalculateMatrixDataHandler() {
        System.out.println("批处理行列式计算模式启动");
        System.out.println("<<警告：程序不保证结果对于用户的正确性>>");
        System.out.println("<警告：终止输入需要输入终止符‘！’>");
        System.out.println("***每次输入回车将会进行一次检测***");
        System.out.println("批处理行列式需要获取单个行列式的具体阶数，请输入：");
        System.out.print(">");
        int n = getSca().nextInt();
        getSca().nextLine();
        ArrayList<double[][]> BatchMatrix = new ArrayList<>();
        double[][] matrix = new double[n][n];
        int count = 0;
        String str = null;
        StringBuilder sb = new StringBuilder();
        int j = 0;
        outer:
        while (true) {
            System.out.print(">");
            str = getSca().nextLine();
            sb.append(str);
            count = sb.toString().split("[,，]").length;
            if (count % (n * n) == 0) {
                System.out.println("捕获到合法矩阵，正在解析当前矩阵");
                try {
                    System.out.println("开始解析");
                    for (int i = 0; i < matrix.length; i++) {
                        for (int k = 0; k < matrix.length; k++) {
                            matrix[i][k] = Double.parseDouble(sb.toString().split("[,，]")[j]);
                            j++;
                        }
                    }

                    BatchMatrix.add(CloneArray(matrix));
                    System.out.println("解析完成");
                    sb.append(",");
                    continue;
                } catch (NumberFormatException e) {
                    sb.delete(0, sb.length());
                    j=0;
                    System.out.println("解析失败,检测到非法数字，缓存矩阵已全部清空请重新输入");
                    System.out.print(">");
                    str = getSca().nextLine();
                    sb.append(str);
                }
            }
            sb.append(",");
            if ( ! str.contains("!") | str.contains("！")){
                continue;
            }
            if(str.contains("!") | str.contains("！")){
                System.out.println("检测到终止符出现，您有以下选择");
                System.out.println("1.丢弃终止符所在矩阵数据，提交以处理数据");
                System.out.println("2.检测终止符所在矩阵数据，并写入");
                int y = getSca().nextInt();
                getSca().nextLine();
                if (y == 1){
                    break;
                } else if (y == 2) {
                    while (true){
                        if (sb.toString().split("[,，!！]+").length - j >= (n*n)){
                            try{
                                int endCount = (sb.toString().split("[,，!！]+").length - j) / (n*n);
                                System.out.println("终止前解析开始");
                                System.out.println("总共"+endCount+"次终止前解析");
                                for (int i=0;i< matrix.length;i++){
                                    for (int k=0;k< matrix.length;k++){
                                        matrix[i][k] = Double.parseDouble(sb.toString().split("[,，!！]+")[j]);
                                        j++;
                                    }
                                }
                                BatchMatrix.add(CloneArray(matrix));
                                int suesscesEnd = 0;
                                suesscesEnd++;
                                System.out.println("已完成"+suesscesEnd+"次终止前解析");
                                if (sb.toString().split("[,，!！]+").length - j >= (n*n)){
                                    continue;
                                }
                                break outer;
                            }catch (NumberFormatException e){
                                System.out.println("终止前解析失败！出现非法字符");
                                System.out.println("正在重新初始化终止前解析");
                                sb.delete(0,sb.length());
                                j =0 ;
                                System.out.println("继续输入");
                                System.out.print(">");
                                str = getSca().nextLine();
                                sb.append(str);
                            }
                        }else {
                            System.out.println("请继续补全：");
                            System.out.print(">");
                            str = getSca().nextLine();
                            sb.append(",").append(str);
                        }
                    }
                }else {
                    System.out.println("非法输入，强制终止并提交数据");
                    break;
                }
            }
        }
        return new MatrixDataHandler(BatchMatrix);
    }  //行列式批处理

}
