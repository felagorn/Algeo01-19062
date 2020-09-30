package flib;

import java.lang.Math;

public class Interpolation {
    public Matrix INTERPOLATIONMATRIX;

    public Interpolation(Matrix listOfCoordinates) {
        Create_InterpolationMatrix(listOfCoordinates);
    }

    public double power(double x, int degree) {
        //Only for positive degree
        if (degree == 0) {
            return 1;
        }else{
            return x * power(x, degree-1);
        }
    }

    public void Create_InterpolationMatrix(Matrix listOfCoordinates) {
        if (listOfCoordinates.COLCOUNT != 2) {
            System.out.println("Matriks input salah");
        } else {
            if (listOfCoordinates.GetROWCOUNT() == 0) {
                System.out.println("Input kosong");
            } else {
                this.INTERPOLATIONMATRIX = new Matrix(listOfCoordinates.GetROWCOUNT(), (listOfCoordinates.GetROWCOUNT() + 1));
                int iFinal = this.INTERPOLATIONMATRIX.GetLastRowID();
                int jFinal = this.INTERPOLATIONMATRIX.GetLastColID();
                int x = listOfCoordinates.GetFirstColID();
                int y = listOfCoordinates.GetLastColID();
                for (int i = this.INTERPOLATIONMATRIX.GetFirstRowID(); i <= iFinal; i += 1) {
                    for (int j = this.INTERPOLATIONMATRIX.GetFirstColID(); j <= jFinal; j += 1) {
                        if (j != jFinal) {
                            this.INTERPOLATIONMATRIX.SetElement(i, j, Math.pow(listOfCoordinates.GetElement(i, x), j));
                        } else {
                            this.INTERPOLATIONMATRIX.SetElement(i, j, listOfCoordinates.GetElement(i, y));
                        }
                    }
                }
                this.INTERPOLATIONMATRIX.Convert_ReducedRowEchelon();
            }
        }
    }

    public double Interpolate(double x) {
        double y = 0;
        int iFinal = this.INTERPOLATIONMATRIX.GetLastRowID();
        int coefID = this.INTERPOLATIONMATRIX.GetLastColID();
        for (int i = this.INTERPOLATIONMATRIX.GetFirstRowID(); i <= iFinal; i += 1) {
            y += this.INTERPOLATIONMATRIX.GetElement(i, coefID) * Math.pow(x, i);
        }
        return y;
    }
}