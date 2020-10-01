import flib.*;
import java.util.Scanner;

public class Main {

    private static void clrscr() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    
    private static void MainMenu() {
  
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
        
        if (q.equals("1")) {
   
            Menu1();
            input.close();
        } else if (q.equals("2")) {

        } else if (q.equals("3")) {

        } else if (q.equals("4")) {

        } else if (q.equals("5")) {

        } else {
            System.exit(0);
        }
    }

    private static void Menu1(){
 
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
            input.close();
        } else if (q.equals("2")) {

        } else if (q.equals("3")) {

        } else{

        }

    }

    public static void MenuGauss(){
        System.out.println("Sistem Persamaan Linear - Metode eliminasi Gauss");
        System.out.println("----------------------------------------------------------------");
        System.out.println("Pilih metode input:");
        System.out.println("1. Input dari keyboard");
        System.out.println("2. Input dari file txt");
        System.out.println("3. Back");

        Scanner input = new Scanner(System.in);
        String q;
        do {
            System.out.print("Pilihan anda: ");
            q = input.nextLine();
            if (!(q.equals("1") || q.equals("2")) || q.equals("3")) {
                System.out.println("ERROR: Input tidak valid");
            }
        } while (!(q.equals("1") || q.equals("2") || q.equals("3")));
        Matrix M=new Matrix(0,0);
        if (q.equals("1")) {    
            M.bacaMatriks();
            
        } else if (q.equals("2")) {
            M = Matrix.Create_FromTxt();

        } else{
            Menu1();
        }
        if (M.ROWCOUNT > M.COLCOUNT){
            if (!M.checkMatrix()){
                System.out.println("SPL tak memiliki solusi");
                System.out.println("----------------------------------------------------------------");
                System.out.println("Tekan Y bila ingin kembali ke menu utama");
                System.out.println("Tekan sembarang untuk keluar");
                Scanner input1 = new Scanner(System.in);
                System.out.print("Pilihan anda: ");
                q = input1.nextLine();
                if (q.equals("Y")){
                    input1.close();
                    MainMenu();
                }else{
                    System.exit(0);
                }
            }
        }
        M.Convert_RowEchelon();
        M.ParametricOnGauss();
        System.out.println("----------------------------------------------------------------");
        System.out.println("Tekan Y bila ingin kembali ke menu utama");
        System.out.println("Tekan sembarang untuk keluar");
        Scanner input1 = new Scanner(System.in);
        System.out.print("Pilihan anda: ");
        q = input1.nextLine();
        if (q.equals("Y")){
            input1.close();
            MainMenu();
        }else{
            System.exit(0);
        }

    }
    public static void main(String[] args) {
        MainMenu();
    }
}
