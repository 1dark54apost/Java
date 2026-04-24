package MyMathFactory;


import JavaMath.Expr;
import JavaMath.SimpleMatrix;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatrixRunable implements Runnable {


    private ArrayList<Expr[][]> matrixs;
    private Map<String,Expr[][]> m = new LinkedHashMap<>();

    public MatrixRunable(ArrayList<Expr[][]> matrixs){
        this.matrixs = matrixs;
    }

    private void Mark(){
        int count = 1;
        for (Expr[][] value : this.matrixs){
            String name = "第"+(count)+"个矩阵";
            m.put(name,value);
            count++;
        }
    }

    @Override
    public void run() {
        Mark();
        SimpleMatrix need = new SimpleMatrix();
        for (String key : m.keySet()){
            System.out.println(key+"的结果:"+need.Construct(m.get(key)).LikeTerms());
        }
    }
}
