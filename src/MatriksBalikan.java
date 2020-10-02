import flib.*;
import java.util.Scanner;

public class MatriksBalikan {
    public static void Menu() {
        System.out.println("DETERMINAN");
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
        Inverse inverseGaussJordan = new Inverse();
        Inverse inverseAdjoint = new Inverse();
        inverseGaussJordan.GaussJordan(matrix);
        inverseAdjoint.Adjoint(matrix);
        System.out.println("Matriks balikan hasil Gauss-Jordan:");
        inverseGaussJordan.MATRIX.PrintMatrix();
        System.out.println();
        System.out.println("Matriks balikan hasil Adjoint:");
        inverseAdjoint.MATRIX.PrintMatrix();
    }
}