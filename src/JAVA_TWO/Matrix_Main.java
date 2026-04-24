package JAVA_TWO;

import java.util.Scanner;

public class Matrix_Main {

    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        MatrixDataHandler matrixDataHandler = new MatrixDataHandler(sca);

        Matrix matrix = new Matrix(matrixDataHandler.BatchcalculateMatrixDataHandler());

        ResultDataOutput output = new MatrixResultDataOutput(matrix);

        output.PrintResultSet_BatchMatrixInfo();

    }
}
