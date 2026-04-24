package JavaMath;

import java.util.Objects;

public class Constan implements Expr {

    private double n;

    public Constan(double n){
        this.n = n;
    }

    public double getN() {
        return n;
    }

    @Override
    public Expr clearCoe(Expr expr) {
        return new Constan(1);
    }

    @Override
    public Constan getCoe(Expr expr) {
        return this;
    }

    @Override
    public Expr Derivative() {
        return new Constan(0);
    }

    public Expr Simplify(){
        return new Constan(n);
    }

    @Override
    public boolean isZero() {
        if (n == 0 ){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPositive() {
        if (n >0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isOne(){
        if (n == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPI() {
        if (n == Math.PI){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPIoneintwo() {
        if (n == Math.PI/2){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPIoneinfour() {
        if (n == Math.PI/4){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPIthreeinfour() {
        if (n == Math.PI*3/4){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPIthreeintwo() {
        if (n == Math.PI/3){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean isPItwo() {
        if (n == Math.PI*2){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Expr LikeTerms() {
        return new Constan(n);
    }

    @Override
    public int hashCode() {
        return Objects.hash(String.valueOf(n));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Constan con = (Constan) obj;

        return Objects.equals(this.n,con.n);
    }

    @Override
    public String toString() {
        return String.valueOf(n);
    }
}
