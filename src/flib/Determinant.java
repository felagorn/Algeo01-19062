package flib;

public class Determinant {
    double DET;

    public Determinant() {
        this.DET = 0;
    }

    public void OBE(Matrix matrix) {
        if (matrix.GetROWCOUNT() == matrix.GetCOLCOUNT()) {
            Matrix upperTriangle = new Matrix(matrix.GetROWCOUNT(), matrix.GetCOLCOUNT());
            upperTriangle.PasteDFFrom(matrix);
            int iInitial = upperTriangle.GetFirstRowID();
            int jInitial = upperTriangle.GetFirstColID();
            int iFinal = upperTriangle.GetLastRowID();
            int jFinal = upperTriangle.GetLastColID();
            for (int j = jInitial, iCurrent = iInitial; j <= jFinal; j += 1, iCurrent += 1) {
                for (int i = iCurrent + 1; i <= iFinal; i += 1) {
                    double k = upperTriangle.GetElement(i, j) / upperTriangle.GetElement(iCurrent, j);
                    upperTriangle.OBE_SumRow(i, iCurrent, -k);
                }
            }
            this.DET = 1;
            for (int i = iInitial, j = jInitial; ((i <= iFinal) && (j <= jFinal)); i += 1, j += 1) {
                this.DET *= upperTriangle.GetElement(i, j);
            }
        } else {
            System.out.println("ERROR: Undefined determinant. Matrix must be n x n in dimension.");
        }
    }

    private double Kofaktor_Calculator(Matrix matrix) {
        double recurse;
        if ((matrix.GetROWCOUNT() == 2) && (matrix.GetCOLCOUNT() == 2)) {
            int i1 = matrix.GetFirstRowID();
            int i2 = matrix.GetLastRowID();
            int j1 = matrix.GetFirstColID();
            int j2 = matrix.GetLastColID();
            recurse = (matrix.GetElement(i1, j1) * matrix.GetElement(i2, j2))
                    - (matrix.GetElement(i2, j1) * matrix.GetElement(i1, j2));
        } else {
            int jFinal = matrix.GetLastColID();
            int i = matrix.GetFirstRowID();
            double d = 0;
            for (int j = matrix.GetFirstColID(); j <= jFinal; j += 1) {
                Matrix minor = new Matrix((matrix.GetROWCOUNT()), (matrix.GetCOLCOUNT()));
                minor.PasteDFFrom(matrix);
                minor.Convert_Minor(i, j);
                d += (Math.pow(-1, ((i + 1) + (j + 1))) * matrix.GetElement(i, j) * Kofaktor_Calculator(minor));
            }
            recurse = d;
        }
        return recurse;
    }

    public void Kofaktor(Matrix matrix) {
        if (matrix.GetROWCOUNT() == matrix.GetCOLCOUNT()) {
            this.DET = Kofaktor_Calculator(matrix);
        } else {
            System.out.println("ERROR: Undefined determinant. Matrix must be n x n in dimension.");
        }
    }

    public void PrintDeterminant() {
        System.out.printf("%f", this.DET);
    }

    public double GetDeterminant(Matrix matrix) {
        this.DET = Kofaktor_Calculator(matrix);
        return this.DET;
    }
}
