package flib;

public class Cramer {
    public Cramer() {
    };

    public void CramerSPL(Matrix matrix) {
        double[] T = new double[matrix.GetROWCOUNT()];
        double[] SPL = new double[matrix.GetCOLCOUNT() - 1];
        double DetMatrix, DetCramer;

        int j, k, i;
        j = matrix.GetLastColID();
        for (k = 0; k <= matrix.GetLastRowID(); k++) {
            T[k] = matrix.GetElement(k, j);
        }
        matrix.COLCOUNT -= 1;

        Determinant X = new Determinant();
        X.Kofaktor(matrix);
        DetMatrix = X.GetDeterminant();
        Matrix temp = new Matrix((matrix.GetROWCOUNT()), (matrix.GetCOLCOUNT()));
        temp.PasteDFFrom(matrix);
        for (j = 0; j <= temp.GetLastColID(); j++) {
            for (i = 0; i <= temp.GetLastRowID(); i++) {
                temp.SetElement(i, j, T[i]);

            }
            X.Kofaktor(temp);
            DetCramer = X.GetDeterminant();
            SPL[j] = DetCramer;
            temp.PasteDFFrom(matrix);

        }

        for (int e = 0; e <=  temp.GetLastColID(); e++) {
            SPL[e] = SPL[e] / DetMatrix;
            System.out.println("x"+(e+1)+": "+SPL[e]);
        }

    }

}