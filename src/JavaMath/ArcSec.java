package JavaMath;

public class ArcSec implements Expr {

    private Expr expr;

    public ArcSec(Expr expr){
        this.expr = expr;
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new ArcSec(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Simplify() {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new DivisionVar(expr.Derivative(),new MultiplicativeVar(expr,new Tan(new ArcSec(expr))));
    }

    @Override
    public String toString() {
        return "ArcSec("+expr.toString()+")";
    }
}
