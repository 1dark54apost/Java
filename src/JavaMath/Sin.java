package JavaMath;

public class Sin implements Expr {

    private Expr expr;

    public Sin(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new Cos(expr),expr.Derivative());
    }

    @Override
    public Expr Simplify() {
        Expr exprSimplify = expr.Simplify();
        if (exprSimplify.isZero() | exprSimplify.isPItwo() | exprSimplify.isPI()){
            return new Constan(0);
        } else if (exprSimplify.isPIoneintwo()) {
            return new Constan(1);
        } else if (exprSimplify.isPIthreeintwo()) {
            return new Constan(-1);
        }else {
            return new Sin(exprSimplify);
        }
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new Sin(exprLike.Simplify()).Simplify();
    }

    @Override
    public String toString() {
        return "sin("+expr.toString()+")";
    }
}
