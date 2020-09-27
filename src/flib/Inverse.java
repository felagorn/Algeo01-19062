package flib;

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
}