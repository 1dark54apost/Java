package JavaMath;

public class ArcCot implements Expr {

    private Expr expr;

    public ArcCot(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new ArcCot(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Simplify() {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new DivisionVar(new MultiplicativeVar(new Constan(-1),expr.Derivative()),new Pow(new Csc(new ArcCot(expr)),new Constan(2)));
    }

    @Override
    public String toString() {
        return "ArcCot("+expr.toString()+")";
    }
}
