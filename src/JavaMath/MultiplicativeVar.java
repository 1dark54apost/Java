package JavaMath;

import java.util.*;

public class MultiplicativeVar implements Expr {

    private Expr left;
    private Expr right;


    public MultiplicativeVar(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expr Derivative() {
        return new AdditionVar(new MultiplicativeVar(left.Derivative(), right), new MultiplicativeVar(left, right.Derivative()));
    }

    @Override
    public Expr Simplify() {
        Expr leftSimplify = left.Simplify();
        Expr rightSimplify = right.Simplify();
        if (leftSimplify.isZero() | rightSimplify.isZero()) {
            return new Constan(0);
        } else if (leftSimplify.isOne()) {
            return rightSimplify;
        } else if (rightSimplify.isOne()) {
            return leftSimplify;
        } else {
            return new MultiplicativeVar(leftSimplify, rightSimplify);
        }
    }

    @Override
    public Expr clearCoe(Expr expr) {
        Expr noneCoe = new Constan(1);
        if (expr instanceof MultiplicativeVar) {
            MultiplicativeVar mul = (MultiplicativeVar) expr;
            if (mul.left instanceof Constan) {
                noneCoe = clearCoe(mul.right);
            } else if (mul.right instanceof Constan) {
                noneCoe = clearCoe(mul.left);
            } else {
                noneCoe = new MultiplicativeVar(clearCoe(mul.left), clearCoe(mul.right));
            }
        } else {
            if (expr instanceof Constan) {
                noneCoe = new Constan(1);
            } else {
                noneCoe = expr;
            }
        }
        return noneCoe;
    }  //去除系数的表达式

    @Override
    public Constan getCoe(Expr expr) {
        Constan coe = new Constan(1);
        //System.out.println(expr);
        if (expr instanceof MultiplicativeVar) {
            //System.out.println("1执行我");
            MultiplicativeVar mul = (MultiplicativeVar) expr;
            if (mul.left instanceof Constan | mul.right instanceof Constan) {
                if (mul.left instanceof Constan & !(mul.right instanceof Constan)) {
                    coe = new Constan(((Constan) mul.left).getN() * getCoe(mul.right).getN());
                    //System.out.println(coe);
                } else if (!(mul.left instanceof Constan)) {
                    coe = new Constan(((Constan) mul.right).getN() * getCoe(mul.left).getN());
                } else {
                    coe = new Constan(getCoe(mul.left).getN() * getCoe(mul.right).getN());
                }
            } else {
                coe = new Constan(getCoe(mul.left).getN() * getCoe(mul.right).getN());
                return coe;
            }
        } else {
            if (expr instanceof Constan) {
                //System.out.println("执行我");
                coe = (Constan) expr;
            } else {
                //System.out.println(expr);
                return coe;
            }
        }
        //System.out.println("结束前"+coe);
        return coe;
    }  //得到表达式系数

    private List<Expr> flatten(Expr expr) {
        List<Expr> exprs = new ArrayList<>();
        if (expr instanceof MultiplicativeVar) {
            MultiplicativeVar mul = (MultiplicativeVar) expr;
            exprs.addAll(flatten(mul.left));
            exprs.addAll(flatten(mul.right));
        } else {
            exprs.add(expr);
        }
        //System.out.println(exprs);
        return exprs;
    }

    @Override
    public Expr LikeTerms() {
        List<Expr> exprs = flatten(this.Simplify());
        List<Expr> term = new ArrayList<>();
        //System.out.println(exprs);
        for (Expr expr : exprs) {
            term.add(expr.LikeTerms());
        }
        //System.out.println("term:" + term);
        List<Expr> coeff = new ArrayList<>();
        List<Expr> exprList1 = new ArrayList<>();
        for (Expr expr : term) {
            if (expr instanceof Constan) {
                coeff.add(expr);
            } else {
                exprList1.add(expr);
            }
        }
        //System.out.println("exprList1:"+ exprList1);
        /*
        List<Expr> exprList = new ArrayList<>();
        Map<Expr,Integer> map = new HashMap<>();
        for (Expr expr : exprList1){
            int i =0;
            for (int k=0;k<exprList1.size();k++){
                if (expr.equals(exprList1.get(k))){
                    i++;
                }
            }
            map.put(expr,i);
        }
        for (Expr expr : map.keySet()){
            exprList.add(new Pow(expr,new Constan(map.get(expr))));
        }
        */
        //System.out.println("ExprList:"+exprList);
        double coeResult = 1; //系数总数
        for (Expr expr : coeff) {
            Constan constan = (Constan) expr;
            coeResult *= constan.getN();
        }
        Expr resultExpr = new Constan(1);
        for (Expr expr : term) {
            if (!(expr instanceof Constan)) {
              resultExpr = new MultiplicativeVar(resultExpr,expr);
            }
        }
        resultExpr = new MultiplicativeVar(new Constan(coeResult),resultExpr);
        //System.out.println("resultExpr"+resultExpr.Simplify());
        return resultExpr.Simplify();
    }  //合并同类项

    @Override
    public int hashCode() {
        int h1 = left.hashCode();
        int h2 = right.hashCode();
        if (h1 < h2) {
            return Objects.hash(h1, h2);
        } else {
            return Objects.hash(h2, h1);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MultiplicativeVar that = (MultiplicativeVar) obj;
        return (Objects.equals(this.left, that.left) && Objects.equals(this.right, that.right)) ||
                (Objects.equals(this.left, that.right) && Objects.equals(this.right, that.left));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }
}
