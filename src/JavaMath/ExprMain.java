package JavaMath;

public class ExprMain {

    public static void main(String[] args) {

        Expr x1 = new SingleVar("x");
        Expr x2 = new MultiplicativeVar(new Constan(2),new SingleVar("x"));
        Expr x3 =  new MultiplicativeVar(new Constan(3),new SingleVar("x"));
        Expr x4 =  new MultiplicativeVar(new Constan(6),new SingleVar("x"));
        Expr x5 =  new MultiplicativeVar(new Constan(2),new SingleVar("x"));
        Expr x6 =  new MultiplicativeVar(new Constan(11),new SingleVar("x"));
        Expr x7 =  new MultiplicativeVar(new Constan(8),new SingleVar("x"));
        Expr x8 =  new MultiplicativeVar(new Constan(3),new SingleVar("x"));
        Expr x9 =  new MultiplicativeVar(new Constan(12),new SingleVar("x"));

        Expr expr = new MultiplicativeVar(new MultiplicativeVar(new Constan(3),new SingleVar("x")),new MultiplicativeVar(new Constan(3),new SingleVar("x")));

        Expr expr1 = new MultiplicativeVar(expr,new AdditionVar(new SingleVar("x"),expr));

        Expr[][] exprs = new Expr[3][3];
        exprs[0][0] = x1;exprs[0][1] = x2;exprs[0][2] = x3;
        exprs[1][0] = x4;exprs[1][1] = x5;exprs[1][2] = x6;
        exprs[2][0] = x7;exprs[2][1] = x8;exprs[2][2] = x9;

        SimpleMatrix matrix = new SimpleMatrix(exprs);

        //System.out.println(expr1.Derivative().Simplify());
        //System.out.println(expr1.Derivative().Simplify().LikeTerms().Simplify());


        System.out.println(matrix.Construct(matrix.getExprs()).Simplify());
        System.out.println(matrix.Construct(matrix.getExprs()).LikeTerms().Simplify());

        // Expr expr = new MultiplicativeVar(new SingleVar("x"),new MultiplicativeVar(new Constan(3),new Sin(new SingleVar("x"))));



    }
}
