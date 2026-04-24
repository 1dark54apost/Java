package JavaMath;

public interface Expr {

    Expr  Derivative();
    String toString();  //潜规则
    Expr Simplify();
    Expr LikeTerms();

    default Expr clearCoe(Expr expr){
        return this;
    }

    default Constan getCoe(Expr expr){
        return new Constan(1);
    }

    default boolean isZero(){

        return false;
    }  //0

    default boolean isPositive(){
        return false;
    }

    default boolean isOne(){
        return false;
    }

    default boolean isE(){
        return false;
    }

    default boolean isPIoneintwo(){return false;}  //2分之Π

    default boolean isPI(){return false;}  //Π

    default boolean isPIthreeintwo(){return false;}  //二分之3Π

    default boolean isPIoneinfour(){return false;}  //四分之Π

    default boolean isPIthreeinfour(){return false;}  //四分3Π

    default boolean isPItwo(){return false;}



}
