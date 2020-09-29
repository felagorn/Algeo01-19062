package flib;

public class interpolation {

    public double power(double x, int degree){
        //Only for positive degree
        if (degree==0){
            return 1;
        }else{
            return x*power(x,degree-1);
        }
    }

    public Matrix makeMatrixForInterpolation(Matrix M){
        
        if (M.ROWCOUNT !=0){
            
            Matrix interpolationMatrix = new Matrix(M.ROWCOUNT, M.ROWCOUNT+1);
            double[] x = new double[M.ROWCOUNT];
            double[] y = new double[M.ROWCOUNT];
            for(int i=0;i< M.ROWCOUNT;i++){
                x[i] = M.DF[i][0];
                y[i] = M.DF[i][1];
            }
            for(int row = 0;row < M.ROWCOUNT;row++){
                for(int col = 0;col<=M.ROWCOUNT;col++){
                    if(col == M.ROWCOUNT){
                        interpolationMatrix.SetElement(row, col, y[row]);
                    }else{
                        interpolationMatrix.SetElement(row, col, power(x[row], col));
                    }
                }
            }
            return interpolationMatrix;
        } else{
            return M;
        }
    }

    public double interpolationResult(Matrix M, double x){
        Matrix interpolated = new Matrix(0,0);
        double coef[] = new double[M.ROWCOUNT];
        double result = 0;
        interpolated = makeMatrixForInterpolation(M);
        interpolated.Convert_ReducedRowEchelon();
        for(int i = 0; i<M.ROWCOUNT;i++){
            coef[i] = interpolated.GetElement(i, interpolated.GetLastColID());
        }
        for(int j=0;j<M.ROWCOUNT;j++){
            result += power(x,j)*coef[j];
        }
        return result;
    }
}
