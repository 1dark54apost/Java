package JavaMath;

public class DivisionVar implements Expr {
    //商的求导
    private Expr up;
    private Expr down;
    private Expr deriUp;
    private Expr deriDown;

    public DivisionVar(Expr up, Expr down) {
        this.down = down;
        this.up = up;
    }

    @Override
    public Expr Derivative() {
        deriDown = down.Derivative();
        deriUp = up.Derivative();
        return new DivisionVar(new SubtractiveVar(new MultiplicativeVar(deriUp, down), new MultiplicativeVar(up, deriDown)), new Pow(down, new Constan(2)));
    }

    @Override
    public Expr Simplify() {
        Expr upSimplify = up.Simplify();
        Expr downSimplify = down.Simplify();

        if (downSimplify.isZero()) {
            return new DivisionVar(upSimplify, downSimplify);
        } else {
            if (upSimplify.isZero()) {
                return new Constan(0);
            } else if (upSimplify.isOne() & downSimplify.isOne()) {
                return new Constan(1);
            } else {
                return new DivisionVar(upSimplify, downSimplify);
            }
        }
    }

    @Override
    public Expr LikeTerms() {
        Expr ubLike = up.LikeTerms();
        Expr downLike = down.LikeTerms();
        return new DivisionVar(ubLike,downLike).Simplify();
    }

    @Override
    public String toString() {
        return "(" + up.toString() + "/" +"("+ down.toString() + ")";
    }
}
