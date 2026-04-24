package JavaMath;

public class ArcCsc implements Expr{

    private Expr expr;

    public ArcCsc(Expr expr){
        this.expr = expr;
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new ArcCsc(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Simplify() {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new DivisionVar(new Constan(-1),new MultiplicativeVar(expr,new Cot(new ArcCsc(expr)))),expr.Derivative());
    }

    @Override
    public String toString() {
        return "ArcCsc("+expr.toString()+")";
    }
}
