package JavaMath;

public class ArcCos implements Expr{

    private Expr expr;

    public ArcCos(Expr expr){
        this.expr = expr;
    }


    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new ArcCos(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Simplify() {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new DivisionVar(expr.Derivative(),new Sin(new ArcCos(expr)));
    }

    @Override
    public String toString() {
        return "ArcCos("+expr.toString()+")";
    }
}
