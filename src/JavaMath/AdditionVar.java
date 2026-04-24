package JavaMath;

import java.util.*;

public class AdditionVar implements Expr{

    private Expr right;
    private Expr left;

    public AdditionVar(Expr left,Expr right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Expr Derivative() {
        return new AdditionVar(left.Derivative(),right.Derivative());
    }

    @Override
    public Expr Simplify() {
        Expr leftSimplify = left.Simplify();
        Expr rightSimplify = right.Simplify();
        if (leftSimplify.isZero()){
            return rightSimplify;
        } else if (rightSimplify.isZero()) {
            return leftSimplify;
        } else if (leftSimplify.isZero() | rightSimplify.isZero()) {
            return new Constan(0);
        }else {
            return new AdditionVar(leftSimplify,rightSimplify);
        }
    }

    private List<Expr> faltten(Expr expr){  //加法表达式展平
        List<Expr> term = new ArrayList<>();
        if (expr instanceof AdditionVar){
            AdditionVar add = (AdditionVar) expr;
            term.addAll(faltten(add.left));
            term.addAll(faltten(add.right));
        }else {
            term.add(expr);
        }
        //System.out.println("term:"+term);
        return term;
    }  //加法表达式展平

    @Override
    public Expr LikeTerms() {
        //ystem.out.println(this.getClass());
        List<Expr> term = faltten(this.Simplify());
        //System.out.println("term:"+term);

        List<Expr> expr1 = new ArrayList<>();
        for (Expr x : term){
            Expr y = x.LikeTerms();
            expr1.add(y);
        }

        //System.out.println("expr1"+expr1);

        Map<Expr,Double> map = new HashMap<>();
        double coeKind = 0;
        List<Expr> kinds = new ArrayList<>();


        for (Expr expr : expr1){
            Expr kind = expr.clearCoe(expr);
            //System.out.println("kind:"+kind);
            kinds.add(kind);
            double coe = expr.getCoe(expr).getN();
            //System.out.println(coe);
            if (kind instanceof Constan){
                coeKind += coe;
                //System.out.println("coeKind"+((Constan) kind).getN());
            }else {
                map.put(kind, map.getOrDefault(kind,0.0)+coe);
            }
        }
        //System.out.println("map:"+map.get(kinds.get(0)));
        Expr result = new Constan(0);

        for (Expr key : map.keySet()){
            result = new AdditionVar(result,new MultiplicativeVar(new Constan(map.get(key)),key));
        }
        //System.out.println("coeKind"+coeKind);
        result = new AdditionVar(result,new Constan(coeKind));

        return result.Simplify();
    }  //加法系数求和

    @Override
    public int hashCode() {
        int h1 = left.hashCode();
        int h2 = right.hashCode();
        if (h1 > h2){
            return Objects.hash(h1,h2);
        }else {
            return Objects.hash(h2,h1);
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AdditionVar add = (AdditionVar) obj;

        return Objects.equals(this.left , add.left) & Objects.equals(this.right , add.right) ||(
                Objects.equals(this.left , add.right) & Objects.equals(add.right , add.left));
    }

    @Override
    public String toString() {
        return "("+left.toString()+"+"+right.toString()+")";
    }
}


/*
for (int i=0;i<kinds.size();i++){
Expr kind = kinds.get(i);  //必须缓存第一次的kind在这里复用
            if (kind instanceof Constan){  //hashmap不认不同地址不同哈希值的对象就是不认不是自己的自己
result = new AdditionVar(result,new Constan(coeKind));
        }else {
//System.out.println(map.get(kind));
result = new AdditionVar(result,new MultiplicativeVar(new Constan(map.get(kind)),kind));
        }
        }
*/



/*
if (left instanceof AdditionVar | right instanceof AdditionVar){
        if (left instanceof AdditionVar & !(right instanceof  AdditionVar)){
        term.add(((AdditionVar) left).faltten());
        term.add(right);
                } else if (! (left instanceof AdditionVar)){
        term.add(left);
                    term.add(((AdditionVar) right).faltten());
        }else {
        term.add(((AdditionVar) left).faltten());
        term.add(((AdditionVar) right).faltten());
        }
        }else {
        term.add(right);
                term.add(left);
            }
                    return term;

 */