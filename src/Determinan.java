import flib.*;
import java.util.Scanner;

public class Determinan {

    public static void Menu() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("DETERMINAN");
        System.out.println("Pilih metode:");
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));
        
        if (q.equals("1")){
            MenuReduksiBaris();
        } else{
            MenuKofaktor();
        }
    }

    private static void MenuReduksiBaris(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.println("----------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));
        
        Matrix matrix;
        if (q.equals("1")) {
            matrix = Matrix.Create_FromUserInput();
        } else {
            matrix = Matrix.Create_FromTxt();
        }
        Determinant determinanOBE = new Determinant();
        determinanOBE.OBE(matrix);
        System.out.printf("Determinan hasil OBE: %f\n", determinanOBE.GetDeterminant());
        System.out.println("----------------------------------------------------------------");
    }

    private static void MenuKofaktor(){
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Keyboard");
        System.out.println("2. File");
        System.out.println("----------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2")));
        
        Matrix matrix;
        if (q.equals("1")) {
            matrix = Matrix.Create_FromUserInput();
        } else {
            matrix = Matrix.Create_FromTxt();
        }
        Determinant determinanKofaktor = new Determinant();
        determinanKofaktor.Kofaktor(matrix);
        System.out.printf("Determinan hasil Kofaktor: %f\n", determinanKofaktor.GetDeterminant());
        System.out.println("----------------------------------------------------------------");
    }
}