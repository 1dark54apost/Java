package JavaMath;

import java.util.ArrayList;

public class SimpleKramer {

    private Expr[][] originMatrix;
    private Expr[] rightHandSide;  //常数列
    private Expr[][] simpleMatrix;  //系数矩阵
    private ArrayList<Expr[][]> molecule = new ArrayList<>();  //分子项
    private Expr denominator; //分母项
    private Expr[] moleculeResult;  //分子结果项
    private Expr[] result;

    public SimpleKramer(Expr[][] originMatrix){
        this.originMatrix = originMatrix;
        setRightHandSide(originMatrix.length);
        setResult(originMatrix.length);
        setMoleculeResult(originMatrix.length);
        setSimpleMatrix(originMatrix.length);
    }

    private void setRightHandSide(int n) {
        this.rightHandSide =  new Expr[n];
    }

    private void setSimpleMatrix(int n) {
        this.simpleMatrix = new Expr[n][n];
    }

    private void setResult(int n) {
        this.result = new Expr[n];
    }

    private void setMoleculeResult(int n) {
        this.moleculeResult = new Expr[n];
    }

    public Expr[][] getOriginMatrix() {
        Expr[][] clone = new Expr[originMatrix.length][originMatrix[0].length];
        for (int i=0;i<clone.length;i++){
            for (int k=0;k<clone[0].length;k++){
                clone[i][k] = originMatrix[i][k];
            }
        }
        return clone;
    }  //增广矩阵的深拷贝

    public Expr[][] getSimpleMatrix() {
        Expr[][] clone = new Expr[simpleMatrix.length][simpleMatrix.length];
        for (int i=0;i<clone.length;i++){
            for (int k=0;k<clone.length;k++){
                clone[i][k] = simpleMatrix[i][k];
            }
        }
        return clone;
    }

    public Expr[] getRightHandSide() {
        Expr[] clone = new Expr[rightHandSide.length];
        for (int i=0;i<clone.length;i++){
            clone[i] = rightHandSide[i];
        }
        return clone;
    }  //获取右列的数据

    private void MatrixDataTransform(){
        Expr[][] clone = getOriginMatrix();
        for (int i=0;i<clone.length;i++){
            rightHandSide[i] = clone[i][clone.length];
        }
        clone = getOriginMatrix();

        for (int i=0;i<clone.length;i++){
            for (int k=0;k<clone.length;k++){
                simpleMatrix[i][k] = clone[i][k];
            }
        }
    }  //分离数据

    private void ShiftSimpleMatricx(){
        MatrixDataTransform();
        Expr[][] clone = getSimpleMatrix();
        Expr[] cloneRightSide = getRightHandSide();
        for (int i=0;i<clone.length;i++){
            for (int k=0;k<clone.length;k++){
                clone[k][i] = cloneRightSide[k];
            }
            molecule.add(clone);
            clone = getSimpleMatrix();
            cloneRightSide = getRightHandSide();
        }
    }  //获取分子的集合

    public void CalculateResult(){
        ShiftSimpleMatricx();
        int i = 0;
        for (Expr[][] exprs : molecule){
            moleculeResult[i] = new SimpleMatrix().Construct(exprs);
            i++;
        }
        denominator = new SimpleMatrix().Construct(simpleMatrix);
        i = 0;
        for (Expr clone : moleculeResult){
            result[i] = new DivisionVar(clone,denominator);
            i++;
        }
    }  //启动计算

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Expr clone : result){
            sb.append(clone).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
