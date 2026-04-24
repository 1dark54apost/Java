package JavaMath;

import java.util.Objects;

public class Log implements Expr {

    private Expr base;
    private Expr argument;

    public Log(Expr base, Expr argument) {
        this.base = base;
        this.argument = argument;
    }

    @Override
    public Expr Simplify() {
        //System.out.println("执行我");
        Expr baseSimplify = base.Simplify();
        Expr argumentSimplify = argument.Simplify();
        //System.out.println(baseSimplify.getClass());
        //System.out.println(argumentSimplify.getClass());
        if (baseSimplify.equals(argumentSimplify)) {
            //System.out.println("检测到 Log(e, e)，应该返回 1");
            return new Constan(1);
        } else {
            //System.out.println("检测失败");
            return new Log(baseSimplify, argumentSimplify);
        }
    }

    @Override
    public Expr Derivative() {
        if (base instanceof Constan | base.isE()) {
            return new MultiplicativeVar(new DivisionVar(new Constan(1), new MultiplicativeVar(argument, new Log(new E(), base))), argument.Derivative());
        } else {
            return new DivisionVar(new Log(new E(), argument), new Log(new E(), base)).Derivative();
        }
        //return new DivisionVar(new Constan(1),new MultiplicativeVar(argument,new Log(new SingleVar("e"),base)));
    }

    @Override
    public Expr LikeTerms() {
        Expr baseLike = this.base.LikeTerms();
        Expr argumentLike = this.argument.LikeTerms();
        return new Log(baseLike.Simplify(),argumentLike.Simplify()).Simplify();
    }

    @Override
    public int hashCode() {
        int h1 = base.hashCode();
        int h2 = argument.hashCode();
        return Objects.hash(h1,h2);
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Log log = (Log) obj;
        return Objects.equals(this.base,log.base) & Objects.equals(this.argument,log.argument);
    }

    @Override
    public String toString() {
        return "Log(" + base.toString() + "," + argument.toString() + ")";
    }
}
