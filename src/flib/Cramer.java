package flib;

public class Cramer {
    public Cramer() {
    };

    public void CramerSPL(Matrix matrix) {
        double[] T = new double[100];
        double[] SPL = new double[100];
        double sum, DetMatrix, DetCramer;

        int j, k, i;
        j = matrix.GetLastColID();
        for (k = 0; k <= matrix.GetLastRowID(); k++) {
            T[k] = matrix.GetElement(k, j);
        }
        matrix.COLCOUNT -= 1;

        Determinant X = new Determinant();
        DetMatrix = X.GetDeterminant(matrix);
        Matrix temp = new Matrix((matrix.GetROWCOUNT()), (matrix.GetCOLCOUNT()));
        temp.PasteDFFrom(matrix);
        for (j = 0; j <= temp.GetLastColID(); j++) {
            for (i = 0; i <= temp.GetLastRowID(); i++) {
                temp.SetElement(i, j, T[i]);

            }
            DetCramer = X.GetDeterminant(temp);
            SPL[j] = DetCramer;
            temp.PasteDFFrom(matrix);

        }
        System.out.println("Here");
        for (int e = 0; e < j; e++) {
            SPL[e] = SPL[e] / DetMatrix;
            System.out.println(SPL[e]);
        }

    }

}