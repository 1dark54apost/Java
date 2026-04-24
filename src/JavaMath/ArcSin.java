package JavaMath;

public class ArcSin implements Expr {

    private Expr expr;

    public ArcSin(Expr expr) {
        this.expr = expr;
    }


    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new ArcSin(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Simplify() {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new DivisionVar(new Constan(1), new Cos(new ArcSin(expr))), expr.Derivative());
    }

    @Override
    public String toString() {
        return "ArcSin(" + expr.toString() + ")";
    }
}
