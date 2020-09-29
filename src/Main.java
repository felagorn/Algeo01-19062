import flib.*;
import java.util.Scanner;

public class Main {

    private static void clrscr() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
    private static void MainMenu() {
        //clrscr();
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linier Berganda");
        System.out.println("6. Keluar");
        System.out.println("----------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2") || q.equals("3") || q.equals("4") || q.equals("5") || q.equals("6"))) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2") || q.equals("3") || q.equals("4") || q.equals("5") || q.equals("6")));
        input.close();
        if (q.equals("1")) {

        } else if (q.equals("2")) {

        } else if (q.equals("3")) {

        } else if (q.equals("4")) {

        } else if (q.equals("5")) {

        } else {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        MainMenu();
    }
}