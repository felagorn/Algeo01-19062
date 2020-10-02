package flib;

import java.util.Scanner;

public class MultiRegression {
    // spek :
    // n menyatakan jumlah baris data x
    // k menyatakan jumlah kolom data x
    // y menytakan hasil dari setiap baris data x

    double[] y, beta, estimation;
    Matrix x, reg;

    int n, k;

    public MultiRegression() {
        this.x = new Matrix(0, 0);
        this.x.DF = new double[200][200];
        this.reg = new Matrix(0, 0);
        this.reg.DF = new double[200][200];
        this.y = new double[200];
        this.beta = new double[200];
        this.estimation = new double[200];
        this.n = 0;
        this.k = 0;

    }

    public void BacaReg() {

        Scanner scanner = new Scanner(System.in);
        // Mencari nilai n dan k
        System.out.println("Masukan Data");
        System.out.print("Masukan nilai n = ");
        this.n = scanner.nextInt();
        System.out.print("Masukan nilai k = ");
        this.k = scanner.nextInt();
        System.out.println("");
        this.x.ROWCOUNT = this.n;
        this.x.COLCOUNT = this.k;

        // input nilai x
        System.out.println("Masukan Data X:");
        for (int i = 0; i <= this.x.GetLastRowID(); i++) {
            for (int j = 0; j <= this.x.GetLastColID(); j++) {
                System.out.printf("X[%d][%d] = ", i + 1, j + 1);
                this.x.DF[i][j] = scanner.nextDouble();
            }
            System.out.println("");
        }

        // input nilai y
        System.out.println("Masukan Data  Y:");
        for (int i = 0; i < this.n; i++) {
            System.out.printf("Y[%d] = ", i + 1);
            this.y[i] = scanner.nextDouble();

        }
        System.out.println("");

        // input nilai xk(estimasi)
        System.out.println("Masukan Nilai Yang Akan Ditaksir Xk:");
        for (int i = 0; i < this.k; i++) {
            System.out.printf("Xk[%d] = ", i + 1);
            this.estimation[i] = scanner.nextDouble();

        }
        System.out.println("");

    }

    public double SumCol(int j) {
        double sum;
        sum = 0;
        for (int i = 0; i <= this.x.GetLastRowID(); i++) {
            sum = sum + this.x.GetElement(i, j - 1);
        }
        return sum;

    }

    public double SumY() {
        double sum;
        sum = 0;
        for (int i = 0; i < this.n; i++) {
            sum = sum + this.y[i];
        }
        return sum;

    }

    public void MakeReg() {

        this.reg.ROWCOUNT = k + 1;
        this.reg.COLCOUNT = k + 2;

        for (int i = 0; i <= this.reg.GetLastRowID(); i++) {
            for (int j = 0; j <= this.reg.GetLastColID(); j++) {
                if (j == 0) {
                    this.reg.DF[i][j] = 1;

                } else if (j == this.reg.GetLastColID()) {
                    this.reg.DF[i][j] = SumY();
                } else {
                    this.reg.DF[i][j] = SumCol(j);
                }

            }

        }

        this.reg.DF[0][0] = this.n;
        for (int i = 1; i <= this.reg.GetLastRowID(); i++) {
            for (int j = 0; j <= this.reg.GetLastColID(); j++) {

                this.reg.DF[i][j] = this.reg.DF[i][j] * SumCol(i);

            }

        }

        System.out.println("Persamaan Linear Hasil Normal Estimation Equation ");
        System.out.println("dalam bentuk matriks augmented :");
        this.reg.PrintMatrix();
    }

    public Matrix SolveReg() {
        Matrix SPL = new Matrix(this.reg.ROWCOUNT, this.reg.COLCOUNT);
        SPL.PasteDFFrom(this.reg);
        SPL.Convert_ReducedRowEchelon();
        return SPL;

    }

    public void GetBeta() {
        Matrix SPL = SolveReg();
        int j;
        j = SPL.GetLastColID();
        System.out.println("");
        System.out.println("");
        System.out.println("Didapati Beta:");
        for (int i = 0; i <= SPL.GetLastRowID(); i++) {
            this.beta[i] = SPL.GetElement(i, j);
            System.out.println("Beta[" + (i) + "] : " + this.beta[i]);

        }

    }

    public double Estimate() {
        double estimated;
        estimated = this.beta[0];
        for (int i = 1; i <= k; i++) {
            estimated = estimated + this.beta[i] * this.estimation[i - 1];

        }

        return estimated;

    }

    public void ProsesReg() {
        BacaReg();
        MakeReg();
        GetBeta();
        System.out.println("");
        System.out.print("Estimasi nilai y : ");
        System.out.println(Estimate());

    }
}