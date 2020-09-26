import flib.Matrix;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(4, 5);
        matrix.DF = new double[][] { {8,-9,1,-8,80},
                                     {-3,-1,5,4,7},
                                     {-2,-1,-3,8,-30},
                                     {-2,-8,-1,2,18} };
        matrix.PrintMatrix();
        System.out.println();
        matrix.UpperTriangularSelectionSort();
        matrix.PrintMatrix();
        System.out.println();
        matrix.Convert_RowEchelon();
        matrix.PrintMatrix();
        System.out.println();
        matrix.Convert_ReducedRowEchelon();
        matrix.PrintMatrix();
        System.out.println();
    }
}