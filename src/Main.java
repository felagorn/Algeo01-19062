import flib.*;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(4, 5);
        matrix.DF = new double[][] { { 1, 2, 3, 4, 5 }, { 3, 6, 3, 6, 8 }, { 1, 2, 3, 4, 6 }, { 0, 4, 2, 3, 5 } };
        System.out.println("Matriks awal:");
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        System.out.println("Matriks disort:");
        matrix.UpperTriangularSelectionSort();
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        System.out.println("Matriks diubah ke dalam bentuk eselon baris:");
        matrix.Convert_RowEchelon();
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        System.out.println("Matriks setelah diubah ke dalam bentuk eselon baris tereduksi:");
        matrix.Convert_ReducedRowEchelon();
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        Matrix squarematrix = new Matrix(5, 5);
        squarematrix.DF = new double[][] { { 1, 3, 1, 5, 3 }, { -2, -7, 0, -4, 2 }, { 0, 0, 1, 0, 1 },
                { 0, 0, 2, 1, 1 }, { 0, 0, 0, 1, 1 } };
        System.out.println("Matrix determinan awal:");
        squarematrix.PrintMatrix();
        System.out.printf("\n\n");
        // Determinant TEST
        Determinant obeDeterminant = new Determinant();
        obeDeterminant.OBE(squarematrix);
        System.out.print("Determinan OBE: ");
        obeDeterminant.PrintDeterminant();
        System.out.println();
        Determinant cofactorDeterminant = new Determinant();
        cofactorDeterminant.Kofaktor(squarematrix);
        System.out.print("Determinan Kofaktor: ");
        cofactorDeterminant.PrintDeterminant();
        System.out.println();
        // Inverse & Baca Matrix TEST
        Matrix squarematrix2 = new Matrix(3, 3);

        Inverse OBEInverse = new Inverse();
        squarematrix2.bacaMatriks();
        OBEInverse.makeAugmentedIdentity(squarematrix2);

        System.out.println();
        OBEInverse.InverseOBE(squarematrix2);

        System.out.println("Inverse:");
        OBEInverse.PrintInverseOBE(squarematrix2);
        System.out.println();
    }
}