import flib.*;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(4, 5);
        matrix.DF = new double[][] { {8,-9,1,-8,80},
                                     {0,-1,5,4,7},
                                     {-2,-1,-3,8,-30},
                                     {-2,-8,-1,2,18} };
        System.out.println("Matriks awal:");
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        matrix.UpperTriangularSelectionSort();
        System.out.println("Matriks setelah disort:");
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        matrix.Convert_RowEchelon();
        System.out.println("Matriks setelah diubah ke dalam bentuk eselon baris:");
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        matrix.Convert_ReducedRowEchelon();
        System.out.println("Matriks setelah diubah ke dalam bentuk eselon baris tereduksi:");
        matrix.PrintMatrix();
        System.out.printf("\n\n");
        Matrix squarematrix = new Matrix(5, 5);
        squarematrix.DF = new double[][] {  {1, 3, 1, 5, 3},
                                            {-2, -7, 0, -4, 2},
                                            {0, 0, 1, 0, 1},
                                            {0, 0, 2, 1, 1},
                                            {0, 0, 0, 1, 1}     };
        System.out.println("Matrix determinan awal:");
        squarematrix.PrintMatrix();
        System.out.printf("\n\n");
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
    }
}