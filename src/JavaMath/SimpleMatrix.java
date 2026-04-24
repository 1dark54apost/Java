package JavaMath;

public class SimpleMatrix {


    private Expr[][] exprs;

    public SimpleMatrix(){

    }

    public SimpleMatrix(Expr[][] exprs) {
        this.exprs = exprs;
    }

    public Expr[][] getExprs() {
        Expr[][] cloneExpr = new Expr[exprs.length][exprs.length];
        for (int i=0;i<exprs.length;i++){
            for (int k=0;k<exprs.length;k++){
                cloneExpr[i][k] = exprs[i][k];
            }
        }
        return cloneExpr;
    }

    public Expr Construct(Expr[][] exprs) {
        if (exprs.length == 1) {
            return exprs[0][0];
        }
        if (exprs.length == 2) {
            return new SubtractiveVar(new MultiplicativeVar(exprs[0][0], exprs[1][1]), new MultiplicativeVar(exprs[0][1], exprs[1][0]));
        }
        Expr[][] subExpr = new Expr[exprs.length - 1][exprs.length - 1];
        Expr sonExpr = new Constan(0);
        Expr relustExpr = new Constan(0);
        for (int i = 0; i < exprs.length; i++) {
            int sign = i % 2 == 0 ? 1 : -1;
            int subrow = 0;
            for (int k = 0; k < exprs.length; k++) {
                int subline = 0;
                if (k == 0) continue;
                for (int j = 0; j < exprs.length; j++) {
                    if (j == i) continue;
                    subExpr[subrow][subline] = exprs[k][j];
                    subline++;
                }
                subrow++;
            }
            sonExpr = new MultiplicativeVar(new Constan(sign),new MultiplicativeVar(exprs[0][i],Construct(subExpr)));
            relustExpr = new AdditionVar(relustExpr,sonExpr);
        }
        return relustExpr;
    }

    public void print(Expr[][] exprs){
        for (int i=0;i<exprs.length;i++){
            for (int k=0;k<exprs.length;k++){
                System.out.println(exprs[i][k]);
            }
        }
    }

}
