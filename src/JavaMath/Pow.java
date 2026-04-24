package JavaMath;

import java.util.Objects;

public class Pow implements Expr {

    private Expr expr;
    private Expr constan;

    public Pow(Expr constan,Expr expr){
        this.constan = constan;
        this.expr = expr;
    }

    @Override
    public Expr Derivative() {
        if (constan instanceof  Constan | constan instanceof E){
        return new MultiplicativeVar(new MultiplicativeVar(new Pow(constan,expr),new Log(new E(),constan)),expr.Derivative());
        }else {
            return new MultiplicativeVar(new AdditionVar(new MultiplicativeVar(expr.Derivative(),new Log(new SingleVar("e"),constan)),new MultiplicativeVar(expr,new Log(new SingleVar("e"),constan).Derivative())),new Pow(constan,expr));
        }
        //return new MultiplicativeVar(new Pow(constan,expr),new Log(new SingleVar("e"),constan));
    }

    public Expr Simplify() {
        Expr constanSimplify = constan.Simplify();
        Expr exprSimplify = expr.Simplify();
        if (constanSimplify.isZero()){
            if (exprSimplify.isPositive()){
                return new Constan(0);
            }else {
                return new Pow(constanSimplify,exprSimplify);
            }
        }else {
            if (exprSimplify.isZero()){
                return new Constan(1);
            }else {
                return new Pow(constanSimplify,exprSimplify);
            }
        }
    }

    @Override
    public Expr LikeTerms() {
        Expr constanLike = this.constan.LikeTerms();
        Expr exprLike = this.expr.LikeTerms();
        return new Pow(constanLike.Simplify(),exprLike.Simplify()).Simplify();
    }


    @Override
    public int hashCode() {
        int h1 = constan.hashCode();
        int h2 = expr.hashCode();
        return Objects.hash(h1,h2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pow pow = (Pow) obj;

        return Objects.equals(this.constan , pow.constan) & Objects.equals(this.expr , pow.expr);
    }

    @Override
    public String toString() {
        return constan.toString()+"^"+expr.toString();
    }

}
