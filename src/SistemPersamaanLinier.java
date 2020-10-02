import flib.*;
import java.util.Scanner;

public class SistemPersamaanLinier {
    public static void Menu() {

        System.out.println("MENU 1");
        System.out.println("1. Metode eliminasi Gauss");
        System.out.println("2. Metode eliminasi Gauss-Jordann");
        System.out.println("3. Metode matriks balikan");
        System.out.println("4. Kaidah Cramer");
        System.out.println("----------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();

            if (!(q.equals("1") || q.equals("2") || q.equals("3") || q.equals("4"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2") || q.equals("3") || q.equals("4")));

        if (q.equals("1")) {
            MenuGauss();

        } else if (q.equals("2")) {
            MenuGaussJordan();

        } else if (q.equals("3")) {
            MenuMatriksInverse();
        } else {
            MenuCramer();
        }

    }

    private static void MenuGauss() {
        System.out.println("Sistem Persamaan Linear - Metode eliminasi Gauss");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file txt");

        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));

        Matrix M = new Matrix(0, 0);
        if (q.equals("1")) {
            M = Matrix.Create_FromUserInput();

        } else {
            M = Matrix.Create_FromTxt();
        }

        // if (M.ROWCOUNT > M.COLCOUNT){
        // if (!M.checkMatrix()){
        // System.out.println("Linear Equation has no real solution2");
        // System.out.println("----------------------------------------------------------------");

        // System.exit(0);
        // }
        // }

        // System.out.println("Tekan Y bila ingin kembali ke menu utama");
        // System.out.println("Tekan sembarang untuk keluar");
        // Scanner input1 = new Scanner(System.in);
        // System.out.print("Pilihan anda: ");
        // q = input1.nextLine();
        // if (q.equals("Y")){
        // input1.close();
        // MainMenu();
        // }else{
        // System.exit(0);
        // }
        System.out.println("Matriks Input:");
        M.PrintMatrix();
        System.out.println();
        System.out.println("Matriks eselon:");
        M.Convert_RowEchelon();
        M.PrintMatrix();
        System.out.println();
        M.ParametricOnGauss();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }

    private static void MenuGaussJordan() {
        System.out.println("Sistem Persamaan Linear - Metode eliminasi Gauss jordan");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file txt");

        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));

        Matrix M = new Matrix(0, 0);
        if (q.equals("1")) {
            M = Matrix.Create_FromUserInput();

        } else {
            M = Matrix.Create_FromTxt();
        }

        System.out.println("Matriks Input:");
        M.PrintMatrix();
        System.out.println();
        System.out.println("Matriks eselon tereduksi:");
        M.Convert_ReducedRowEchelon();
        M.PrintMatrix();
        System.out.println();
        M.ParametricOnGauss();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }

    private static void MenuMatriksInverse() {
        System.out.println("Sistem Persamaan Linear - Metode matriks balikan");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file txt");

        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));

        Matrix M = new Matrix(0, 0);
        if (q.equals("1")) {
            M = Matrix.Create_FromUserInput();

        } else {
            M = Matrix.Create_FromTxt();
        }

        Inverse i = new Inverse();
        i.InverseSPL(M);
    }

    private static void MenuCramer() {
        System.out.println("Sistem Persamaan Linear - Metode matriks balikan");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file txt");

        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));

        Matrix M = new Matrix(0, 0);
        if (q.equals("1")) {
            M = Matrix.Create_FromUserInput();

        } else {
            M = Matrix.Create_FromTxt();
        }

        Cramer c = new Cramer();
        c.CramerSPL(M);
    }
}