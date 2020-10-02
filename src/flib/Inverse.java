package flib;

public class Inverse {
    public Matrix MATRIX;

    public Inverse() {
    };

    private Matrix Create_AugmentedIdentity(Matrix M) {
        Matrix augmentedIdentity = new Matrix(M.GetROWCOUNT(), (2 * M.GetCOLCOUNT()));
        augmentedIdentity.PasteDFFrom(M);
        int iFinal = M.GetLastRowID();
        int jFinal = M.GetLastColID();
        for (int i = M.GetFirstRowID(); i <= iFinal; i++) {
            for (int j = M.GetFirstColID(); j <= jFinal; j++) {
                if (i == j) {
                    augmentedIdentity.SetElement(i, j + M.GetCOLCOUNT(), 1);
                } else {
                    augmentedIdentity.SetElement(i, j + M.GetCOLCOUNT(), 0);
                }
            }
        }
        return augmentedIdentity;
    }

    public void GaussJordan(Matrix M) {
        if (M.ROWCOUNT == M.COLCOUNT) {
            Matrix augmentedIdentity = Create_AugmentedIdentity(M);
            int jInitial = augmentedIdentity.GetFirstColID();
            int jFinal = (augmentedIdentity.COLCOUNT / 2) - 1;
            int iInitial = augmentedIdentity.GetFirstRowID();
            int iFinal = augmentedIdentity.GetLastRowID();
            int currentRow;
            double scalek;
            double sumk;
            for (int j = jInitial; j <= jFinal; j += 1) {
                currentRow = iInitial + (j - jInitial);
                scalek = 1 / augmentedIdentity.GetElement(currentRow, j);
                augmentedIdentity.OBE_ScaleRow(currentRow, scalek);
                for (int i = currentRow + 1; i <= iFinal; i += 1) {
                    sumk = - (augmentedIdentity.GetElement(i, j));
                    augmentedIdentity.OBE_SumRow(i, currentRow, sumk);
                }
            }
            for (int j = jInitial; j <= jFinal; j += 1) {
                currentRow = iInitial + (j - jInitial);
                for (int i = currentRow - 1; i >= iInitial; i -= 1) {
                    sumk = - (augmentedIdentity.GetElement(i, j));
                    augmentedIdentity.OBE_SumRow(i, currentRow, sumk);
                }
            }
            boolean isValid = true;
            int i = iInitial;
            int j;
            while ((i <= iFinal) && (isValid)) {
                j = jInitial;
                while ((j <= jFinal) && (isValid)) {
                    if (i == j) {
                        if (augmentedIdentity.GetElement(i, j) != 1) {
                            isValid = false;
                        }
                    } else {
                        if (augmentedIdentity.GetElement(i, j) != 0) {
                            isValid = false;
                        }
                    }
                    j += 1;
                }
                i += 1;
            }
            if (!(isValid)) {
                System.out.println("Matriks Singular (tidak mempunyai balikan)");
            } else {
                int j0Final;
                int j1Final;
                this.MATRIX = new Matrix(M.GetROWCOUNT(), M.GetCOLCOUNT());
                for (i = iInitial; i <= iFinal; i += 1) {
                    j0Final = M.GetLastColID();
                    j1Final = augmentedIdentity.GetLastColID();
                    for (int j0 = M.GetFirstColID(), j1 = jFinal + 1; ((j0 <= j0Final) && (j1 <= j1Final)); j0 += 1, j1 += 1) {
                        this.MATRIX.SetElement(i, j0, augmentedIdentity.GetElement(i, j1));
                    }
                }
            }
        } else {
            System.out.println("Matriks tidak memiliki balikan (banyak baris ≠ banyak kolom)");
        }
    }

    public void Adjoint(Matrix M) {
        if (M.ROWCOUNT == M.COLCOUNT) {
            Determinant Det = new Determinant();
            Det.OBE(M);
            M.Convert_CoFactorMatrix();
            this.MATRIX = new Matrix(M.GetCOLCOUNT(), M.GetROWCOUNT());
            this.MATRIX = M.Transpose();
            int iFinal = this.MATRIX.GetLastRowID();
            int jFinal = this.MATRIX.GetLastColID();
            try {
                for (int i = 0; i <= iFinal; i++) {
                    for (int j = 0; j <= jFinal; j++) {
                        this.MATRIX.SetElement(i, j, this.MATRIX.GetElement(i, j) / Det.GetDeterminant());
    
                    }
                }
            } catch (ArithmeticException e) {
                System.out.println("Matriks Singular (tidak mempunyai balikan)");
            }
        } else {
            System.out.println("Matriks tidak memiliki balikan (banyak baris ≠ banyak kolom)");
        }
    }

    public void InverseSPL(Matrix matrix) {
        Matrix A = new Matrix(matrix.GetROWCOUNT(), (matrix.GetCOLCOUNT() - 1));
        int iFinal = A.GetLastRowID();
        int jFinal = A.GetLastColID();
        for (int i = A.GetFirstRowID(); i <= iFinal; i += 1) {
            for (int j = A.GetFirstColID(); j <= jFinal; j += 1) {
                A.SetElement(i, j, matrix.GetElement(i, j));
            }
        }
        Inverse inverse = new Inverse();
        inverse.GaussJordan(A);
        int x;
        int y = matrix.GetLastColID();
        for (int i = A.GetFirstRowID(); i <= iFinal; i += 1) {
            x = 0;
            for (int j = A.GetFirstColID(); j <= jFinal; j += 1) {
                x += A.GetElement(i, j) * matrix.GetElement(i, y);
            }
            System.out.println("x" + (i + 1) + ": " + x);
        }
    }
    
    
    /*public void InverseSPL(Matrix matrix) {
        double[] T = new double[100];
        double[] SPL = new double[100];
        double sum;
        Matrix InverseMatrix;
        int j, i;
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
            System.out.println("x"+(e+1)+": "+SPL[e]);
        }

    }*/
}