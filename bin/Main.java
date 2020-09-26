import flib.Matrix;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 3);
        matrix.bacaMatriks();
        matrix.PrintMatrix();
        System.out.println();
        // matrix.UpperTriangularSelectionSort();
        // matrix.PrintMatrix();
        // System.out.println();
        // matrix.Convert_RowEchelon();
        // matrix.PrintMatrix();
        // System.out.println();
        // matrix.Convert_ReducedRowEchelon();
        // matrix.PrintMatrix();
        // System.out.println();
        matrix.makeAugmentedIdentity();
        matrix.PrintMatrix();
        System.out.println();

    }
}