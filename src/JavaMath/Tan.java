package JavaMath;

public class Tan implements Expr {

    private Expr expr;

    public Tan(Expr expr) {
        this.expr = expr;
    }


    @Override
    public Expr Simplify() {
        Expr exprSimplify = expr.Simplify();
        if (exprSimplify.isPI() | exprSimplify.isZero()) {
            return new Constan(0);
        } else if (exprSimplify.isPIoneintwo()) {
            return new Tan(exprSimplify);
        } else if (exprSimplify.isPIoneinfour()) {
            return new Constan(1);
        } else if (exprSimplify.isPIthreeinfour()) {
            return new Constan(-1);
        } else {
            return new Tan(exprSimplify);
        }
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new Pow(new Sec(expr), new Constan(2)), expr.Derivative());
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new Tan(exprLike).Simplify();
    }

    @Override
    public String toString() {
        return "tan(" + expr.toString() + ")";
    }
}
