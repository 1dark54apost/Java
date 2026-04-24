package JavaMath;

public class E implements Expr{



    @Override
    public boolean isE() {
        return true;
    }

    @Override
    public Expr Simplify() {
        return new E();
    }

    @Override
    public Expr Derivative() {
        return new Constan(0);
    }

    @Override
    public Expr LikeTerms() {
        return this;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }

    @Override
    public String toString() {
        return "e";
    }
}
