package JavaMath;

public class Csc implements Expr{

    private Expr expr;

    public Csc(Expr expr){
        this.expr = expr;
    }


    @Override
    public Expr Simplify() {
        Expr exprSimplify = expr.Simplify();
        if (exprSimplify.isZero() | exprSimplify.isPItwo() | exprSimplify.isPI()){
            return new Csc(exprSimplify);
        } else if (exprSimplify.isPIoneintwo()) {
            return new Constan(1);
        } else if (exprSimplify.isPIthreeintwo()) {
            return new Constan(-1);
        }else {
            return new Csc(exprSimplify);
        }
    }

    @Override
    public Expr Derivative() {
        return new MultiplicativeVar(new Constan(-1),new MultiplicativeVar(new MultiplicativeVar(new Csc(expr),new Cot(expr)),expr.Derivative()));
    }

    @Override
    public Expr LikeTerms() {
        Expr exprLike = expr.LikeTerms();
        return new Csc(exprLike.Simplify()).Simplify();
    }

    @Override
    public String toString() {
        return "Csc("+expr.toString()+")";
    }
}
