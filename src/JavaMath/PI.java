package JavaMath;

public class PI implements Expr{

    @Override
    public boolean isPI() {
        return true;
    }

    @Override
    public Expr Simplify() {
        return new PI();
    }

    @Override
    public Expr Derivative() {
        return new Constan(0);
    }

    @Override
    public Expr LikeTerms() {
        return null;
    }

    @Override
    public String toString() {
        return "Π";
    }
}
