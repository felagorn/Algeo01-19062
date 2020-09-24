package com.company;

import java.util.Arrays;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Matriks.setBaris(4);
        Matriks.setKolom(5);
        Matriks.isi = new double[][] {  {8,-9,1,-8,80},
                                        {-3,-1,5,4,7},
                                        {-2,-1,-3,8,-30},
                                        {-2,-8,-1,2,18}};
        Matriks.tulisMatriks();
        Matriks.makeEselonTereduksiByGaussJordan();

        Matriks.tulisMatriks();
    }



    public static class Matriks {
        public static double[][] isi;
        private static int baris;
        private static int kolom;

        public Matriks(int nB, int nK){
            baris = nB;
            kolom = nK;
            isi = new double[baris][kolom];
        }

        public int getBaris(){
            return baris;
        }

        public int getKolom(){
            return kolom;
        }

        public static int getLastBarisIdx(){
            return baris-1;
        }

        public static int getLastKolomIdx(){
            return kolom -1;
        }

        public static void setBaris(int nB){
            baris = nB;
        }

        public static void setKolom(int nK){
            kolom = nK;
        }

        public static void bacaMatriks(){
            //menerima input pengguna untuk mengisi array 2d isi
            Scanner scanner = new Scanner(System.in);
            for(int i=0;i<=getLastBarisIdx();i++){
                for(int j=0;j<=getLastKolomIdx();j++){
                    isi[i][j] = scanner.nextDouble();
                }
            }

        }

        public static void tulisMatriks(){
            //fungsi tulis matriks untuk memudahkan pembacaan
            for (double[] floats : isi) {
                System.out.println(Arrays.toString(floats));
            }

        }

        public static void swapBaris(int brs1, int brs2){
            if (brs1!=brs2){
                double[] temp; // variabel pembantu swapping
                temp = isi[brs1];
                isi[brs1] = isi[brs2];
                isi[brs2] = temp;
            }

        }

        public static void baris1PlusBaris2(int brs1, int brs2, double x){
            // Semua elemen di brs1 ditambahkan dengan x kali setiap elemen di brs2
            for(int kol=0;kol<=getLastKolomIdx();kol++){
                isi[brs1][kol] += isi[brs2][kol]*x;
            }
        }

        public static void kaliBaris(int brs, double x){
            for(int kol=0;kol<=getLastKolomIdx();kol++){
                isi[brs][kol] = x*isi[brs][kol];
            }
        }

        public static int getLeadingBrsIdx(int brs){
            //Mencari indeks kolom di baris brs ynng tak nol pertama kali, bila semua elemen bernilai nol return kolom
            for(int kol=0;kol<=getLastKolomIdx();kol++){
                if (isi[brs][kol] != 0){
                    return kol;
                }
            }
            return kolom;
        }

        public static int getLeadingKolIdx(int kol, int bar){
            //mencari nilai leading dari kolom kol, me-return indeks baris dengan kolom tak nol pertama dari bar
            //jika semua nilai di kolom bernilai 0 return baris
            for(int brs=bar;brs<=getLastBarisIdx();brs++){
                if(isi[brs][kol] != 0){
                    return brs;
                }
            }
            return baris;
        }

        public static double getLeadingBrs(int brs){
            //Mencari nilai baris tak nol pertama, bila seluruh baris adalah 0 akan di return 0
            for(int kol=0;kol<=getLastKolomIdx();kol++){
                if (isi[brs][kol] != 0){
                    return isi[brs][kol];
                }
            }
            return 0;
        }



        public static void makeSegitigaAtas(){
            int n=0; // baris yang sudah fix
            for(int kol=0;kol<=getLastKolomIdx();kol++) {
                int brsLeadKol = getLeadingKolIdx(kol, n);
                if ((brsLeadKol != baris) && (brsLeadKol >= n)) {
                    swapBaris(n, brsLeadKol);
                    n += 1;
                }
            }
        }

        public static void makeLeadingOneBrs(int brs){
            if (getLeadingBrs(brs)!=0 && getLeadingBrsIdx(brs) < kolom) {
                double x = 1 /(getLeadingBrs(brs));
                kaliBaris(brs, x);
            }
        }

        public static void makeEselonByGauss(){

            double x; //sebagai variabel untuk membuat matriks eselon
            int y;

            for(int brs=0;brs<=getLastBarisIdx();brs++){
                makeSegitigaAtas();
                if (getLeadingBrsIdx(brs) < getLastKolomIdx()){
                    makeLeadingOneBrs(brs);

                    y = getLeadingBrsIdx(brs);
                    for(int bar=brs+1;bar<=getLastBarisIdx();bar++){
                        if (isi[bar][y] != 0){
                            x = -isi[bar][y];
                            baris1PlusBaris2(bar, brs, x);
                        }
                    }
                }

            }

        }

        public static void makeEselonTereduksiByGaussJordan(){
            makeEselonByGauss();
            for(int brs=getLastBarisIdx();brs>0;brs--){
                if(getLeadingBrs(brs)!=0){
                    double x;
                    int y = getLeadingBrsIdx(brs);
                    for(int bar=brs-1;bar>=0;bar--){
                        x= -isi[bar][y];
                        baris1PlusBaris2(bar, brs, x);
                    }
                }
            }
        }

    }

}
