package MyMathFactory;

import JavaMath.Constan;
import JavaMath.Expr;
import JavaMath.MultiplicativeVar;
import JavaMath.SingleVar;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        Expr x1 = new SingleVar("x");
        Expr x2 = new MultiplicativeVar(new Constan(2),new SingleVar("x"));
        Expr x3 =  new MultiplicativeVar(new Constan(3),new SingleVar("x"));
        Expr x4 =  new MultiplicativeVar(new Constan(6),new SingleVar("x"));
        Expr x5 =  new MultiplicativeVar(new Constan(2),new SingleVar("x"));
        Expr x6 =  new MultiplicativeVar(new Constan(11),new SingleVar("x"));
        Expr x7 =  new MultiplicativeVar(new Constan(8),new SingleVar("x"));
        Expr x8 =  new MultiplicativeVar(new Constan(3),new SingleVar("x"));
        Expr x9 =  new MultiplicativeVar(new Constan(12),new SingleVar("x"));

        Expr[][] exprs = new Expr[3][3];
        exprs[0][0] = x1;exprs[0][1] = x2;exprs[0][2] = x3;
        exprs[1][0] = x4;exprs[1][1] = x5;exprs[1][2] = x6;
        exprs[2][0] = x7;exprs[2][1] = x8;exprs[2][2] = x9;

        Expr[][] exprs1 = new Expr[3][3];
        exprs1[0][0] = x1;exprs1[0][1] = x2;exprs1[0][2] = x3;
        exprs1[1][0] = x4;exprs1[1][1] = x5;exprs1[1][2] = x6;
        exprs1[2][0] = x7;exprs1[2][1] = x8;exprs1[2][2] = x9;

        Expr[][] exprs2 = new Expr[3][3];
        exprs2[0][0] = x1;exprs2[0][1] = x2;exprs2[0][2] = x3;
        exprs2[1][0] = x4;exprs2[1][1] = x5;exprs2[1][2] = x6;
        exprs2[2][0] = x7;exprs2[2][1] = x8;exprs2[2][2] = x9;

        ArrayList<Expr[][]> runa = new ArrayList<>();
        runa.add(exprs1);
        runa.add(exprs);
        runa.add(exprs2);

        MatrixRunable matrix = new MatrixRunable(runa);
        Thread t = new Thread(matrix);
        t.start();

    }
}
