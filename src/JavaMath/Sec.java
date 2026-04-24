package JavaMath;

public class Sec implements Expr{

    private Expr expr;

    public Sec(Expr expr){
        this.expr = expr;
    }

    @Override
    public Expr Simplify() {
        Expr exprSimplify = expr.Simplify();
        if (exprSimplify.isZero() | exprSimplify.isPItwo()){
            return new Constan(1);
        } else if (exprSimplify.isPIoneintwo() | exprSimplify.isPIthreeintwo()) {
            return new Sec(exprSimplify);
        } else if (exprSimplify.isPI()) {
            return new Constan(-1);
        }else {
            return new Sec(exprSimplify);
        }
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new Sec(exprLike.Simplify()).Simplify();
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new MultiplicativeVar(new Sec(expr),new Tan(expr)),expr.Derivative());
    }

    @Override
    public String toString() {
        return "Sec("+expr.toString()+")";
    }
}
