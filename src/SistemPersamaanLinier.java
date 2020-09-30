import flib.*;
import java.util.Scanner;

public class SistemPersamaanLinier {

    public static void Menu() {
        System.out.println("SISTEM PERSAMAAN LINIER");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordan");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2") || q.equals("3") || q.equals("4"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2") || q.equals("3") || q.equals("4")));
        input.close();
        if (q.equals("1")) {
            System.out.println();
            SistemPersamaanLinier.MetodeEliminasiGauss();
        } else if (q.equals("2")) {
            System.out.println();
            SistemPersamaanLinier.MetodeEliminasiGaussJordan();
        } else if (q.equals("3")) {
            System.out.println();
            SistemPersamaanLinier.MetodeMatriksBalikan();
        } else {
            System.out.println();
        }
    }

    private static void MetodeEliminasiGauss() {
        System.out.println("METODE ELIMINASI GAUSS");
        System.out.println("Pilih metode input:");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));
        input.close();
        Matrix matrix;
        if (q.equals("1")) {
            matrix = Matrix.Create_FromUserInput();
        } else {
            matrix = Matrix.Create_FromTxt();
        }
        matrix.Convert_RowEchelon();
        matrix.ParametricOnGauss();
    }

    private static void MetodeEliminasiGaussJordan() {
        System.out.println("METODE ELIMINASI GAUSS-JORDAN");
        System.out.println("Pilih metode input:");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));
        input.close();
        Matrix matrix;
        if (q.equals("1")) {
            matrix = Matrix.Create_FromUserInput();
        } else {
            matrix = Matrix.Create_FromTxt();
        }
        matrix.Convert_ReducedRowEchelon();
        //incomplete
    }

    private static void MetodeMatriksBalikan() {
        System.out.println("METODE MATRIKS BALIKAN");
        System.out.println("Pilih metode input:");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));
        input.close();
        Matrix matrix;
        if (q.equals("1")) {
            matrix = Matrix.Create_FromUserInput();
        } else {
            matrix = Matrix.Create_FromTxt();
        }
        Matrix a = new Matrix(matrix.GetROWCOUNT(), (matrix.GetCOLCOUNT() - 1));
        int iFinal = a.GetLastRowID();
        int jFinal = a.GetLastColID();
        int jInitial = a.GetFirstColID();
        for (int i = a.GetFirstRowID(); i <= iFinal; i += 1) {
            for (int j = jInitial; j <= jFinal; j += 1) {
                a.SetElement(i, j, matrix.GetElement(i, j));
            }
        }
        Inverse aInverse = new Inverse();
        aInverse.GaussJordan(a);
        //incomplete
    }
}