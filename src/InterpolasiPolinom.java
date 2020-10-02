import flib.*;
import java.util.Scanner;

public class InterpolasiPolinom {
    
    public static void Menu() {
        System.out.println("INTERPOLASI POLINOM");
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
        
        Matrix listOfCoordinates;
        if (q.equals("1")) {
            listOfCoordinates = Matrix.Create_FromUserInput();
        } else {
            listOfCoordinates = Matrix.Create_FromTxt();
        }
        Interpolation interpolasi = new Interpolation(listOfCoordinates);
        System.out.println("Polinom interpolasi P(x) berhasil dibuat!");
        System.out.println("Sekarang input x, dan P(x) akan ditampilkan. ");
        boolean isContinue = true;
        while (isContinue) {
            System.out.print("x = ");
            double x = input.nextDouble();
            double y = interpolasi.Interpolate(x);
            System.out.println("P(x) = " + y);
            do {
                System.out.print("Apakah ada lagi nilai P(x) yang mau dicari? [Y/N]");
                q = input.nextLine();
                if (!((q == "Y") || (q == "N") || (q == "y") || (q == "n"))) {
                    System.out.println("ERROR: Input tidak valid");
                } else if ((q == "N") || (q == "n")) {
                    isContinue = false;
                }
            } while (!(q.equals("1") || q.equals("2")));
        }
        //incomplete
    }
}