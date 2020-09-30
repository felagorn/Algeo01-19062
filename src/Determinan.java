import flib.*;
import java.util.Scanner;

public class Determinan {

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
        Determinant determinanOBE = new Determinant();
        Determinant determinanKofaktor = new Determinant();
        determinanOBE.OBE(matrix);
        determinanKofaktor.Kofaktor(matrix);
        System.out.printf("Determinan hasil OBE: %f\n", determinanOBE.GetDeterminant());
        System.out.printf("Determinan hasil Kofaktor: %f", determinanKofaktor.GetDeterminant());
    }
}