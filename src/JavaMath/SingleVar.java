package JavaMath;

import java.util.Objects;

public class SingleVar implements Expr{

    private String x;

    public SingleVar(String x){
        this.x = x ;
    }

    @Override
    public Expr Derivative() {
        return new Constan(1);
    }

    public Expr Simplify(){
        return new SingleVar(x);
    }

    @Override
    public Expr LikeTerms() {
        return new SingleVar(x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SingleVar that = (SingleVar) obj;
        return Objects.equals(this.x, that.x);
    }

    @Override
    public String toString() {
        return x;
    }
}
