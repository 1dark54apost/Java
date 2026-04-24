package JavaMath;

import java.util.*;

public class SubtractiveVar implements Expr {

    private Expr left;
    private Expr right;

    public SubtractiveVar(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expr Derivative() {
        return new SubtractiveVar(left.Derivative(), right.Derivative());
    }

    @Override
    public Expr Simplify() {
        Expr leftSimplify = left.Simplify();
        Expr rightSimplify = right.Simplify();
        if (leftSimplify.isZero()) {
            return new MultiplicativeVar(new Constan(-1), rightSimplify);
        } else if (rightSimplify.isZero()) {
            return new MultiplicativeVar(new Constan(-1), leftSimplify);
        } else if (leftSimplify.isZero() | rightSimplify.isZero()) {
            return new Constan(0);
        } else {
            return new SubtractiveVar(leftSimplify, rightSimplify);
        }
    }

    private List<Expr> faltten(Expr expr) {
        List<Expr> term = new ArrayList<>();
        if (expr instanceof SubtractiveVar) {
            SubtractiveVar sub = (SubtractiveVar) expr;
            term.addAll(faltten(sub.left));
            term.addAll(faltten(sub.right));
        } else {
            term.add(expr);
        }
        return term;
    }

    @Override
    public Expr LikeTerms() {
        List<Expr> exprs = faltten(this.Simplify());
        List<Expr> term = new ArrayList<>();
        for (Expr expr : exprs){
            term.add(expr.LikeTerms());
        }
        //System.out.println("term:"+term);
        Map<Expr,Double> map = new HashMap<>();
        double coeffKind = 0;
        int sign = 1;
        for (Expr expr : term){
            Expr kind = expr.clearCoe(expr);
            //System.out.println(kind);
            double coeff = expr.getCoe(expr).getN();
            //System.out.println(coeff);
            if (kind instanceof Constan){
                coeffKind -= coeff;
            }else {
                if (sign % 2 == 0) {coeff *= -1;}
                //System.out.println(coeff);
                map.put(kind,map.getOrDefault(kind,0.0) + coeff);
                sign++;
            }
        }
        //System.out.println(map.get(key.get(0)));
        Expr resultExpr = new Constan(0);
        for (Expr expr : map.keySet()){

            resultExpr = new SubtractiveVar(resultExpr,new MultiplicativeVar(new Constan(map.get(expr)),expr));
        }
        resultExpr = new SubtractiveVar(resultExpr,new Constan(coeffKind));

        return resultExpr.Simplify();
    }

    @Override
    public int hashCode() {
        int h1 = left.hashCode();
        int h2 = right.hashCode();
        return Objects.hash(h1,h2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)return true;
        if (obj == null || getClass() != obj.getClass())return false;
        SubtractiveVar sub = (SubtractiveVar) obj;
        return Objects.equals(this.left, sub.left) && Objects.equals(this.right , sub.right);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }
}
