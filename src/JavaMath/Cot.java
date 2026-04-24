package JavaMath;

public class Cot implements Expr {

    private Expr expr;


    public Cot(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Expr Simplify() {
        Expr exprSimplify = expr.Simplify();
        if (exprSimplify.isZero() | exprSimplify.isPI()) {
            return new Cot(exprSimplify);
        } else if (exprSimplify.isPIoneintwo()) {
            return new Constan(0);
        } else if (exprSimplify.isPIoneinfour()) {
            return new Constan(1);
        } else if (exprSimplify.isPIthreeinfour()) {
            return new Constan(-1);
        } else {
            return new Cot(exprSimplify);
        }
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new MultiplicativeVar(new Constan(-1), new Pow(new Csc(expr), new Constan(2))), expr.Derivative());
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new Cot(exprLike.Simplify()).Simplify();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
