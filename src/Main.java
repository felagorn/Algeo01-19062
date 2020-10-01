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

        Matrix squarematrix3 = new Matrix(3, 3);
        Inverse AdjointInverse = new Inverse();
        squarematrix3.bacaMatriks();

        AdjointInverse.InverseSPL(squarematrix3);

        // Cramer Test
        Matrix squarematrix4 = new Matrix(3, 3);
        Cramer CramerTest = new Cramer();
        squarematrix4.bacaMatriks();
        CramerTest.CramerSPL(squarematrix4);

        // readFromTxt test
        System.out.println("Coba readTxt:\n");
        Matrix M1 = Matrix.readFromTxt("C:\\Users\\ASUS ROGGL503G\\Algeo01-19062\\test\\isi.txt");
        M1.PrintMatrix();
        System.out.println("\n");

        // Matriks Hilbert
        System.out.println("Coba hilbert:\n");
        Matrix M2 = Matrix.makeHilbertAugmented(6);
        M2.PrintMatrix();
        ;
        System.out.println("\n");

        // Interpolation test
        System.out.println("Coba interpolasi:\n");
        Matrix manual = new Matrix(3, 2);
        manual.DF = new double[][] { { 8.0, 2.0794 }, { 9.0, 2.1972 }, { 9.5, 2.2513 } };
        manual.PrintMatrix();
        System.out.println("\n");

        interpolation i = new interpolation();
        Matrix interpolated = i.makeMatrixForInterpolation(manual);
        interpolated.PrintMatrix();
        System.out.println("\n");
        double x = i.interpolationResult(manual, 9.2);
        System.out.println("\n");
        System.out.println(x);
        System.out.println("\n");
    }
}