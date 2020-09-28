import flib.*;

public class Main {

    public static void main(String[] args) {
        // Matrix matrix = new Matrix(0,0);
        // matrix = Matrix.readFromTxt("C:\\Users\\ASUS ROG GL503G\\Algeo01-19062\\test\\isi.txt");
        
        Matrix manual = new Matrix(3,2);
        manual.DF = new double[][] {    {8.0, 2.0794}, 
                                        {9.0, 2.1972}, 
                                        {9.5, 2.2513}};
        manual.PrintMatrix();
        //Interpolation test
        System.out.println("\n");
        interpolation i = new interpolation();
        Matrix interpolated = i.makeMatrixForInterpolation(manual);
        interpolated.PrintMatrix();
        double x = i.interpolationResult(manual, 9.2);
        System.out.println("\n");
        System.out.println(x);


        // System.out.println("Matriks awal:");
        // matrix.PrintMatrix();
        // System.out.printf("\n\n");
        // System.out.println("Matriks disort:");
        // matrix.UpperTriangularSelectionSort();
        // matrix.PrintMatrix();
        // System.out.printf("\n\n");
        // System.out.println("Matriks diubah ke dalam bentuk eselon baris:");
        // matrix.Convert_RowEchelon();
        // matrix.PrintMatrix();
        // System.out.printf("\n\n");
        // System.out.println("Matriks setelah diubah ke dalam bentuk eselon baris tereduksi:");
        // matrix.Convert_ReducedRowEchelon();
        // matrix.PrintMatrix();
        // System.out.printf("\n\n");
        // Matrix squarematrix = new Matrix(5, 5);

        // squarematrix.DF = new double[][] { { 1, 3, 1, 5, 3 }, { -2, -7, 0, -4, 2 }, { 0, 0, 1, 0, 1 },
        //         { 0, 0, 2, 1, 1 }, { 0, 0, 0, 1, 1 } };
        // System.out.println("Matrix determinan awal:");
        // squarematrix.PrintMatrix();
        // System.out.printf("\n\n");
      
        // // Determinant TEST
        // Determinant obeDeterminant = new Determinant();
        // obeDeterminant.OBE(squarematrix);
        // System.out.print("Determinan OBE: ");
        // obeDeterminant.PrintDeterminant();
        // System.out.println();
        // Determinant cofactorDeterminant = new Determinant();
        // cofactorDeterminant.Kofaktor(squarematrix);
        // System.out.print("Determinan Kofaktor: ");
        // cofactorDeterminant.PrintDeterminant();
        // System.out.println();
      
        // // Inverse & Baca Matrix TEST
        // Matrix squarematrix2 = new Matrix(3, 3);

        // Inverse OBEInverse = new Inverse();
        // squarematrix2.bacaMatriks();
        // OBEInverse.makeAugmentedIdentity(squarematrix2);

        // System.out.println();
        // OBEInverse.InverseOBE(squarematrix2);

        // System.out.println("Inverse:");
        // OBEInverse.PrintInverseOBE(squarematrix2);
        // System.out.println();

    }
}