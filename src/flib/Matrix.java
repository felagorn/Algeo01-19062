package flib;

import java.util.Scanner;
// import java.util.Arrays;

public class Matrix {
    public double[][] DF;
    public int ROWCOUNT, COLCOUNT;

    public Matrix(int row, int col) {
        this.DF = new double[100][100];
        this.ROWCOUNT = row;
        this.COLCOUNT = col;
    }

    /* Get, Set */

    public int GetROWCOUNT() {
        return this.ROWCOUNT;
    }

    public int GetCOLCOUNT() {
        return this.COLCOUNT;
    }

    public int GetFirstRowID() {
        return 0;
    }
    public int GetFirstColID() {
        return 0;
    }

    public int GetLastRowID() {
        return (this.GetROWCOUNT() - 1);
    }

    public int GetLastColID() {
        return (this.GetCOLCOUNT() - 1);
    }

    public double GetElement(int rowID, int colID) {
        return this.DF[rowID][colID];
    }

    public double[] GetRow(int rowID) {
        return this.DF[rowID];
    }

    public void SetElement(int rowID, int colID, double elementVal) {
        this.DF[rowID][colID] = elementVal;
    }

    /*
     * public void SetRow(int rowID, double[] rowVal) { int jFinal =
     * this.GetLastColID(); for (int j = this.GetFirstColID(); j <= jFinal; j += 1)
     * { this.SetElement(rowID, j, rowVal[j]); } }
     */

    public void PasteDFFrom(Matrix anotherSmallerMatrix) {
        if ((this.GetROWCOUNT() >= anotherSmallerMatrix.GetROWCOUNT())
                && (this.GetCOLCOUNT() >= anotherSmallerMatrix.GetCOLCOUNT())) {

            int iFinal = anotherSmallerMatrix.GetLastRowID();
            int jFinal = anotherSmallerMatrix.GetLastColID();
            for (int i = anotherSmallerMatrix.GetFirstRowID(); i <= iFinal; i += 1) {
                for (int j = anotherSmallerMatrix.GetFirstColID(); j <= jFinal; j += 1) {
                    this.SetElement(i, j, anotherSmallerMatrix.GetElement(i, j));
                }
            }

        } else {

            System.out.println("ERROR: Paste failed. Destination matrix too small.");
        }
    }

    /* Print */

    public void PrintMatrix() {

        /*
         * for (double[] rows : DF) { System.out.println(Arrays.toString(rows)); }
         */

        int iFinal = this.GetLastRowID();
        for (int i = this.GetFirstRowID(); i <= iFinal; i += 1) {
            int jFinal = this.GetLastColID();
            for (int j = this.GetFirstColID(); j <= jFinal; j += 1) {
                System.out.printf("%10.3f", this.GetElement(i, j));
            }
            if (i != iFinal) {
                System.out.println();
            }
        }
    }

    /* Basic Operations */

    public boolean IsValidRowID(int rowID) {
        boolean isValid;
        if ((rowID >= this.GetFirstRowID()) && (rowID <= this.GetLastRowID())) {
            isValid = true;

        } else {

            isValid = false;
        }
        return isValid;
    }

    public boolean IsValidColID(int colID) {
        boolean isValid;
        if ((colID >= this.GetFirstColID()) && (colID <= this.GetLastColID())) {
            isValid = true;

        } else {

            isValid = false;
        }
        return isValid;
    }

    public void OBE_SwapRow(int row1, int row2) {
        if (this.IsValidRowID(row1) && this.IsValidRowID(row2)) {
            if (row1 != row2) {
                double[] temp = this.GetRow(row1); // variabel pembantu swapping
                this.DF[row1] = this.GetRow(row2);
                this.DF[row2] = temp;
                // this.SetRow(row1, this.GetRow(row2));
                // this.SetRow(row2, temp);
            }

        } else {

            System.out.println("ERROR: Row index out of bounds.");
        }
    }

    public void OBE_SumRow(int row1, int row2, double k) {
        // Semua elemen di row1 ditambahkan dengan k kali setiap elemen di row2
        if (this.IsValidRowID(row1) && this.IsValidRowID(row2)) {
            int jFinal = this.GetLastColID();

            for (int j = this.GetFirstColID(); j <= jFinal; j += 1) {
                this.DF[row1][j] += (k * this.GetElement(row2, j));
            }
        } else {

            System.out.println("ERROR: Row index out of bounds.");
        }
    }

    public void OBE_ScaleRow(int row, double k) {
        if (this.IsValidRowID(row)) {
            int jFinal = this.GetLastColID();

            for (int j = this.GetFirstColID(); j <= jFinal; j += 1) {
                this.DF[row][j] *= k;
            }
        } else {

            System.out.println("ERROR: Row index out of bounds.");
        }
    }

    public int GetLeadingValue_Row_ColID(int row) {
        // Mencari indeks kolom di baris row ynng tak nol pertama kali
        // Bila input tidak valid, return -1
        // Bila semua elemen bernilai nol return COLCOUNT
        int j = -1;
        if (this.IsValidRowID(row)) {
            j = this.GetFirstColID();
            int jFinal = this.GetLastColID();
            boolean isFound = false;
            while ((j <= jFinal) && !(isFound)) {
                if (this.GetElement(row, j) != 0) {
                    isFound = true;

                } else {

                    j += 1;
                }
            }
        }
        return j;
    }

    public double GetLeadingValue_Row_Element(int row) {

        // Mencari nilai baris tak nol pertama

        // bila seluruh baris adalah 0 akan di return 0
        double element = 0;
        int j = this.GetLeadingValue_Row_ColID(row);
        if (j != this.GetCOLCOUNT()) {
            element = this.GetElement(row, j);
        }
        return element;
    }

    public int GetLeadingValue_Col_RowID(int col) {

        // mencari nilai leading dari kolom col, me-return indeks baris dengan kolom tak
        // nol pertama dari row
        // bila input tidak valid, return -1
        // jika semua nilai di kolom bernilai 0 return ROWCOUNT

        int i = -1;
        if (this.IsValidColID(col)) {
            i = this.GetFirstRowID();
            int iFinal = this.GetLastRowID();
            boolean isFound = false;
            while ((i <= iFinal) && !(isFound)) {
                if (this.GetElement(i, col) != 0) {
                    isFound = true;

                } else {

                    i += 1;
                }
            }
        }
        return i;
    }

    public int GetLeadingValue_Col_RowID_Next(int col) {
        int i = this.GetLeadingValue_Col_RowID(col);
        if (i <= this.GetLastRowID()) {
            i += 1;
            int iFinal = this.GetLastRowID();
            boolean isFound = false;
            while ((i <= iFinal) && !(isFound)) {
                if (this.GetElement(i, col) != 0) {
                    isFound = true;

                } else {

                    i += 1;
                }
            }
        }
        return i;
    }

    public int GetLeadingValue_Col_RowID_FromRow(int col, int row) {

        // mencari nilai leading dari kolom col, me-return indeks baris dengan kolom tak
        // nol pertama dari row
        // bila input col tidak valid, return -1
        // bila input row tidak valid, return -2
        // jika semua nilai di kolom bernilai 0 return ROWCOUNT

        int i = -1;
        if (this.IsValidColID(col)) {
            i = -2;
            if (this.IsValidRowID(row)) {
                i = row;
                int iFinal = this.GetLastRowID();
                boolean isFound = false;
                while ((i <= iFinal) && !(isFound)) {
                    if (this.GetElement(i, col) != 0) {
                        isFound = true;

                    } else {

                        i += 1;
                    }
                }
            }
        }
        return i;
    }

    public int GetLeadingValue_Col_RowID_FromRow_Next(int col, int row) {
        int i = this.GetLeadingValue_Col_RowID_FromRow(col, row);
        if (i <= this.GetLastRowID()) {
            i += 1;
            int iFinal = this.GetLastRowID();
            boolean isFound = false;
            while ((i <= iFinal) && !(isFound)) {
                if (this.GetElement(i, col) != 0) {
                    isFound = true;

                } else {

                    i += 1;
                }
            }
        }
        return i;
    }

    public void UpperTriangularSelectionSort() {
        int j = this.GetFirstColID();
        int jFinal = this.GetLastColID();
        int currentRow = this.GetFirstRowID();
        int finalRow = this.GetLastRowID();
        int i;
        while ((currentRow <= finalRow) && (j <= jFinal)) {
            i = this.GetLeadingValue_Col_RowID_FromRow(j, currentRow);
            if (i != currentRow) {
                if (i == this.GetROWCOUNT()) {
                    j += 1;

                } else {
                    this.OBE_SwapRow(currentRow, i);
                }
            } else {

                currentRow += 1;
            }
        }
    }

    public void OBE_ScaleRow_LeadingOne(int row) {
        if (this.GetLeadingValue_Row_ColID(row) < this.GetCOLCOUNT()) {
            double k = 1 / this.GetLeadingValue_Row_Element(row);
            this.OBE_ScaleRow(row, k);
        }
    }

    public void Convert_RowEchelon() {
        int finalRow = this.GetLastRowID();
        int i;
        for (int currentRow = this.GetFirstRowID(); currentRow <= finalRow; currentRow += 1) {
            this.UpperTriangularSelectionSort();
            this.OBE_ScaleRow_LeadingOne(currentRow);
            int j = this.GetLeadingValue_Row_ColID(currentRow);
            i = currentRow + 1;

            while ((i <= finalRow)
                    && (this.GetLeadingValue_Row_ColID(i) == this.GetLeadingValue_Row_ColID(currentRow))) {
                this.OBE_SumRow(i, currentRow, (-this.GetElement(i, j)));
                if (this.GetElement(i, j) < 0) {
                    System.out.println("Ini jalan");
                    this.OBE_SumRow(i, i, (-this.GetElement(i, j)));

                }
                i += 1;
            }
        }
    }

    public void Convert_ReducedRowEchelon() {
        this.Convert_RowEchelon();
        int i = this.GetFirstRowID();
        int iFinal = this.GetLastRowID();
        int jUndefined = this.GetCOLCOUNT();
        while ((i <= iFinal) && (this.GetLeadingValue_Row_ColID(i) != jUndefined)) {
            int j = this.GetLeadingValue_Row_ColID(i);
            for (int k = 0; k < i; k += 1) {

                this.OBE_SumRow(k, i, (-this.GetElement(k, j)));

            }
            i += 1;
        }
    }

    public void Convert_Minor(int row, int col) {
        Matrix minor = new Matrix((this.GetROWCOUNT() - 1), (this.GetCOLCOUNT() - 1));
        int iFinal = this.GetLastRowID();
        int jFinal = this.GetLastColID();
        int iMinor = minor.GetFirstRowID();
        for (int i = this.GetFirstRowID(); i <= iFinal; i += 1) {
            int jMinor = minor.GetFirstColID();
            for (int j = this.GetFirstColID(); j <= jFinal; j += 1) {
                if ((i != row) && (j != col)) {
                    minor.SetElement(iMinor, jMinor, this.GetElement(i, j));
                    jMinor += 1;
                }
            }
            if (i != row) {
                iMinor += 1;
            }
        }
        this.PasteDFFrom(minor);
        this.ROWCOUNT = minor.GetROWCOUNT();
        this.COLCOUNT = minor.GetCOLCOUNT();
    }


    public void bacaMatriks() {
        int row, col;
        // menerima input pengguna untuk mengisi array 2d isi
        Scanner scanner = new Scanner(System.in);
        System.out.println("row:");
        row = scanner.nextInt();
        System.out.println("col:");
        col = scanner.nextInt();
        ROWCOUNT = row;
        COLCOUNT = col;
        for (int i = 0; i <= GetLastRowID(); i++) {
            for (int j = 0; j <= GetLastColID(); j++) {
                DF[i][j] = scanner.nextDouble();
            }
        }

    }

}