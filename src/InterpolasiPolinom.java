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
        input.close();
        Matrix listOfCoordinates;
        if (q.equals("1")) {
            listOfCoordinates = Matrix.Create_FromUserInput();
        } else {
            listOfCoordinates = Matrix.Create_FromTxt();
        }
        Interpolation interpolasi = new Interpolation(listOfCoordinates);
        interpolasi.Interpolate(9.2);
        //incomplete
    }
}
