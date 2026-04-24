package JavaMath;

public class ArcTan implements Expr {

    private Expr expr;

    public ArcTan(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new ArcTan(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Simplify() {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new DivisionVar(expr.Derivative(), new Pow(new Sec(new ArcTan(expr)), new Constan(2)));
    }

    @Override
    public String toString() {
        return "ArcTan(" + expr.toString() + ")";
    }
}
