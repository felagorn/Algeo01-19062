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
        this.reg = new Matrix(0, 0);
        this.y = new double[100];
        this.beta = new double[100];
        this.estimation = new double[100];
        this.n = 0;
        this.k = 0;

    }

    public void BacaReg() {

        Scanner scanner = new Scanner(System.in);
        // Mencari nilai n dan k
        System.out.println("n:");
        this.n = scanner.nextInt();
        System.out.println("k:");
        this.k = scanner.nextInt();
        this.x.ROWCOUNT = this.n;
        this.x.COLCOUNT = this.k;

        // input nilai x
        System.out.println("X:");
        for (int i = 0; i <= this.x.GetLastRowID(); i++) {
            for (int j = 0; j <= this.x.GetLastColID(); j++) {
                this.x.DF[i][j] = scanner.nextDouble();
            }
        }
        this.x.PrintMatrix();

        // input nilai y
        System.out.println("");
        System.out.println("Y:");
        for (int i = 0; i < this.n; i++) {
            this.y[i] = scanner.nextDouble();

        }

        // input nilai xk(estimasi)
        System.out.println("Xk:");
        for (int i = 0; i < this.k; i++) {
            this.estimation[i] = scanner.nextDouble();

        }

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
        System.out.println("MakeReg1:");
        this.reg.PrintMatrix();
        this.reg.DF[0][0] = this.n;
        for (int i = 1; i <= this.reg.GetLastRowID(); i++) {
            for (int j = 0; j <= this.reg.GetLastColID(); j++) {

                this.reg.DF[i][j] = this.reg.DF[i][j] * SumCol(i);

            }

        }
        System.out.println("");
        System.out.println("MakeReg2:");
        this.reg.PrintMatrix();
    }

    public Matrix SolveReg() {
        Matrix SPL = new Matrix(this.reg.ROWCOUNT, this.reg.COLCOUNT);
        SPL.PasteDFFrom(this.reg);
        SPL.Convert_ReducedRowEchelon();
        System.out.println("");
        System.out.println("SolveReg:");
        SPL.PrintMatrix();
        return SPL;

    }

    public void GetBeta() {
        Matrix SPL = SolveReg();
        int j;
        j = SPL.GetLastColID();
        System.out.println("");
        System.out.println("Beta");
        for (int i = 0; i <= SPL.GetLastRowID(); i++) {
            this.beta[i] = SPL.GetElement(i, j);
            System.out.println(this.beta[i]);

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
        // Untuk mendapatkan persamaan Regresi :
        // this.reg.PrintMatrix();
        System.out.println("Estimate");
        System.out.println(Estimate());

    }
}