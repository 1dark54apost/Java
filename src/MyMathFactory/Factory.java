package MyMathFactory;

import JavaMath.*;

public class Factory {



    public static Expr cons(double n){
        return new Constan(n);
    }

    public static Expr x(String x){
        return new SingleVar(x);
    }

    public static Expr add(Expr left,Expr right){
        return new AdditionVar(left,right);
    }

    public static Expr mul(Expr left,Expr right){
        return new MultiplicativeVar(left,right);
    }

    public static Expr sub(Expr left,Expr right){
        return new SubtractiveVar(left,right);
    }

    public static Expr div(Expr up,Expr down){
        return new DivisionVar(up,down);
    }

    public static Expr pow(Expr base,Expr argument){
        return new Pow(base,argument);
    }

    public static Expr log(Expr base,Expr argument){
        return new Log(base,argument);
    }

    public static Expr sin(Expr expr){
        return new Sin(expr);
    }

    public static Expr cos(Expr expr){
        return new Cos(expr);
    }

    public static Expr tan(Expr expr){
        return new Tan(expr);
    }

    public static Expr csc(Expr expr){
        return new Csc(expr);
    }

    public static Expr sec(Expr expr){
        return new Sec(expr);
    }

    public static Expr cot(Expr expr){
        return new Cot(expr);
    }

    public static Expr arcSin(Expr expr){
        return new ArcSin(expr);
    }

    public static Expr arcCos(Expr expr){
        return new ArcCos(expr);
    }

    public static Expr arcTan(Expr expr){
        return new ArcTan(expr);
    }

    public static Expr arcCsc(Expr expr){
        return new ArcCsc(expr);
    }

    public static Expr arcSec(Expr expr){
        return new ArcSec(expr);
    }

    public static Expr arcCot(Expr expr){
        return new ArcCot(expr);
    }

    public static SimpleMatrix SimpleMatrix(Expr[][] exprs){
        return new SimpleMatrix(exprs);
    }

    public static Expr PI(){
        return new PI();
    }
    public static Expr E(){
        return new E();
    }

}
