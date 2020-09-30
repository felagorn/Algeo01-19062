package flib;

import java.lang.Math;

public class Inverse {

    public Inverse() {
    };

    public void makeAugmentedIdentity(Matrix matrix) {

        for (int i = 0; i <= matrix.GetLastRowID(); i++) {
            for (int j = 0; j <= matrix.GetLastColID(); j++) {
                if (i == j) {
                    matrix.SetElement(i, j + matrix.COLCOUNT, 1);
                } else {
                    matrix.SetElement(i, j + matrix.COLCOUNT, 0);
                }

            }

        }

        matrix.COLCOUNT = matrix.COLCOUNT * 2;
    }

    public void InverseOBE(Matrix matrix) {
        matrix.Convert_ReducedRowEchelon();
    }

    public boolean IsInverseOBEValid(Matrix matrix) {
        int i, j, zero_sum;
        boolean inverse;
        inverse = true;
        i = matrix.GetFirstRowID();
        j = matrix.GetFirstColID();
        zero_sum = 0;

        while (i <= matrix.GetLastRowID() && inverse) {

            while (j < matrix.COLCOUNT / 2 && inverse) {
                if (matrix.GetElement(i, j) == 0) {
                    zero_sum += 1;
                }
                if (zero_sum == matrix.COLCOUNT / 2) {
                    inverse = false;
                }
                j += 1;

            }
            j = 0;
            zero_sum = 0;
            i += 1;
        }

        return inverse;
    }

    public void PrintInverseOBE(Matrix matrix) {
        if (IsInverseOBEValid(matrix)) {
            int iFinal = matrix.GetLastRowID();
            for (int i = matrix.GetFirstRowID(); i <= iFinal; i += 1) {
                int jFinal = matrix.GetLastColID();
                for (int j = matrix.GetFirstColID() + matrix.COLCOUNT / 2; j <= jFinal; j += 1) {
                    System.out.printf("%10.3f", matrix.GetElement(i, j));
                }
                if (i != iFinal) {
                    System.out.println();
                }
            }
        } else {
            System.out.println("Matriks Singular");
        }

    }

    public Matrix MakeCoFactorMatrix(Matrix matrix) {

        Matrix CoFactorMatrix = new Matrix((matrix.GetROWCOUNT()), (matrix.GetCOLCOUNT()));
        double Det, sign;
        for (int i = 0; i <= matrix.GetLastRowID(); i++) {

            for (int j = 0; j <= matrix.GetLastColID(); j++) {
                Matrix minor = new Matrix((matrix.GetROWCOUNT()), (matrix.GetCOLCOUNT()));
                minor.PasteDFFrom(matrix);
                minor.Convert_Minor(i, j);

                Determinant X = new Determinant();
                sign = Math.pow(-1, i + j);

                Det = X.GetDeterminant(minor);
                CoFactorMatrix.SetElement(i, j, Det * sign);

            }

        }
        return CoFactorMatrix;
    }

    public Matrix Transpose(Matrix matrix) {

        Matrix TransposeMatrix = new Matrix((matrix.GetROWCOUNT()), (matrix.GetCOLCOUNT()));
        for (int i = 0; i <= matrix.GetLastRowID(); i++) {
            for (int j = 0; j <= matrix.GetLastColID(); j++) {
                TransposeMatrix.SetElement(i, j, matrix.GetElement(j, i));

            }

        }
        return TransposeMatrix;
    }

    public Matrix InverseAdjoint(Matrix matrix) {
        Matrix InverseAdjointMatrix;
        double Det;
        Determinant X = new Determinant();
        Det = X.GetDeterminant(matrix);
        InverseAdjointMatrix = Transpose(MakeCoFactorMatrix(matrix));

        for (int i = 0; i <= matrix.GetLastRowID(); i++) {
            for (int j = 0; j <= matrix.GetLastColID(); j++) {
                InverseAdjointMatrix.SetElement(i, j, InverseAdjointMatrix.GetElement(i, j) / Det);

            }

        }
        return InverseAdjointMatrix;
    }

    public void PrintInverseAdjoint(Matrix matrix) {
        Matrix InverseAdjointMatrix;
        double Det;

        Determinant X = new Determinant();
        Det = X.GetDeterminant(matrix);
        if (Det == 0) {
            System.out.println("Matriks Singular");
        } else {
            InverseAdjointMatrix = InverseAdjoint(matrix);
            InverseAdjointMatrix.PrintMatrix();
        }

    }

    public void InverseSPL(Matrix matrix) {
        double[] T = new double[100];
        double[] SPL = new double[100];
        double sum;
        Matrix InverseMatrix;
        int j, k, i;
        j = matrix.GetLastColID();
        for (i = 0; i <= matrix.GetLastRowID(); i++) {
            T[i] = matrix.GetElement(i, j);
        }
        matrix.COLCOUNT -= 1;
        InverseMatrix = InverseAdjoint(matrix);
        for (int z = 0; z <= InverseMatrix.GetLastRowID(); z++) {
            sum = 0;
            for (int w = 0; w <= InverseMatrix.GetLastColID(); w++) {
                // InverseMatrix.SetElement(z, w, InverseMatrix.GetElement(z, w) / Det);
                sum = InverseMatrix.GetElement(z, w) * T[w] + sum;
            }
            SPL[z] = sum;
        }
        for (int e = 0; e < i; e++) {
            System.out.println(SPL[e]);
        }

    }
}