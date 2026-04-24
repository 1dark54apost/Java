package JavaMath;

public class Cos implements Expr{

    private Expr expr;

    public Cos(Expr expr){
        this.expr = expr;
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new Constan(-1),new MultiplicativeVar(new Sin(expr),expr.Derivative()));
    }

    @Override
    public Expr Simplify() {
        Expr exprSimplify = expr.Simplify();
        if (exprSimplify.isZero() | exprSimplify.isPItwo()){
            return new Constan(1);
        } else if (exprSimplify.isPIoneintwo() | exprSimplify.isPIthreeintwo()) {
            return new Constan(0);
        } else if (exprSimplify.isPI()) {
            return new Constan(-1);
        }else {
            return new Cos(exprSimplify);
        }
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new Cos(exprLike.Simplify()).Simplify();
    }

    @Override
    public String toString() {
        return "cos("+expr.toString()+")";
    }

}
