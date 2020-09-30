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
        //readFromTxt test
        //System.out.println("Coba readTxt:\n");
        //Matrix matrix = Matrix.Create_FromTxt();
        //"C:\\Users\\ASUS ROG GL503G\\Algeo01-19062\\test\\isi.txt"
        //matrix.PrintMatrix();
        //System.out.println("\n");
        
        //Interpolation test
        System.out.println("Coba interpolasi:\n");
        Matrix manual = new Matrix(3, 2);
        manual.DF = new double[][] {    {8.0, 2.0794}, 
                                        {9.0, 2.1972}, 
                                        {9.5, 2.2513}};
        manual.PrintMatrix();
        System.out.println("\n");
        
        Interpolation i = new Interpolation(manual);
        i.INTERPOLATIONMATRIX.PrintMatrix();;
        System.out.println("\n");
        double y = i.Interpolate(9.2);
        System.out.println("\n");
        System.out.println(y);
        System.out.println("\n");

        //Matriks Hilbert
        System.out.println("Coba hilbert:\n");
        Matrix M1 = Matrix.makeHilbertAugmented(6);
        M1.PrintMatrix();;
        System.out.println("\n");

        //Parametric test
        Matrix M2 = new Matrix(4,5);
        M2.DF = new double[][] {    {1,2,3,4,5},
                                    {0,1,0,1,2},
                                    {0,0,0,1,1},
                                    {0,0,0,0,0}};
        M2.ParametricOnGauss();
    }
}