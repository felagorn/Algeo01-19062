package flib;

// import java.util.Arrays;

public class Matrix {
    public double[][] DF;
    public int ROWCOUNT, COLCOUNT;

    public Matrix(int row, int col) {
        DF = new double[row][col];
        ROWCOUNT = row;
        COLCOUNT = col;
    }

    /* Get, Set */

    public int GetFirstRowID() {
        return 0;
    }
    
    public int GetFirstColID() {
        return 0;
    }

    public int GetLastRowID() {
        return (ROWCOUNT - 1);
    }

    public int GetLastColID() {
        return (COLCOUNT - 1);
    }

    public double GetElement(int rowID, int colID) {
        return DF[rowID][colID];
    }

    public double[] GetRow(int rowID) {
        return DF[rowID];
    }

    public void SetElement(int rowID, int colID, double elementVal) {
        DF[rowID][colID] = elementVal;
    }

    public void PasteDFFrom(Matrix anotherSmallerMatrix) {
        if ((ROWCOUNT >= anotherSmallerMatrix.ROWCOUNT) && (COLCOUNT >= anotherSmallerMatrix.COLCOUNT)) {
            this.DF = anotherSmallerMatrix.DF;
        }
        else {
            System.out.println("ERROR: Paste failed. Destination matrix too small.");
        }
    }

    /* Print */

    public void PrintMatrix() {
        /*for (double[] rows : DF) {
            System.out.println(Arrays.toString(rows));
        }*/
        int iFinal = GetLastRowID();
        for (int i = GetFirstRowID(); i <= iFinal; i += 1) {
            int jFinal = GetLastColID();
            for (int j = GetFirstColID(); j <= jFinal; j += 1) {
                System.out.printf("%10.3f", GetElement(i, j));
            }
            if (i != iFinal) {
                System.out.println();
            }
        }
    }

    /* Basic Operations */

    public boolean IsValidRowID(int rowID) {
        boolean isValid;
        if ((rowID >= GetFirstRowID()) && (rowID <= GetLastRowID())) {
            isValid = true;
        }
        else {
            isValid = false;
        }
        return isValid;
    }

    public boolean IsValidColID(int colID) {
        boolean isValid;
        if ((colID >= GetFirstColID()) && (colID <= GetLastColID())) {
            isValid = true;
        }
        else {
            isValid = false;
        }
        return isValid;
    }

    public void OBE_SwapRow(int row1, int row2) {
        if (IsValidRowID(row1) && IsValidRowID(row2)) {
            if (row1 != row2) {
                double[] temp = GetRow(row1); // variabel pembantu swapping
                DF[row1] = GetRow(row2);
                DF[row2] = temp;
            }
        }
        else {
            System.out.println("ERROR: Row index out of bounds.");
        }
    }

    public void OBE_SumRow(int row1, int row2, double k) {
        // Semua elemen di row1 ditambahkan dengan k kali setiap elemen di row2
        if (IsValidRowID(row1) && IsValidRowID(row2)) {
            int jFinal = GetLastColID();
            for (int j = GetFirstColID(); j <= jFinal ; j += 1) {
                DF[row1][j] += (k * GetElement(row2, j));
            }
        }
        else {
            System.out.println("ERROR: Row index out of bounds.");
        }
    }

    public void OBE_ScaleRow(int row, double k) {
        if (IsValidRowID(row)) {
            int jFinal = GetLastColID();
            for(int j = GetFirstColID(); j <= jFinal; j += 1) {
                DF[row][j] *= k;
            }
        }
        else {
            System.out.println("ERROR: Row index out of bounds.");
        }
    }

    public int GetLeadingValue_Row_ColID(int row) {
        // Mencari indeks kolom di baris row ynng tak nol pertama kali
        // Bila input tidak valid, return -1
        // Bila semua elemen bernilai nol return COLCOUNT
        int j = -1;
        if (IsValidRowID(row)) {
            j = GetFirstColID();
            int jFinal = GetLastColID();
            boolean isFound = false;
            while ((j <= jFinal) && !(isFound)) {
                if (GetElement(row, j) != 0) {
                    isFound = true;
                }
                else {
                    j += 1;
                }
            }
        }
        return j;
    }

    public double GetLeadingValue_Row_Element(int row) {
        //Mencari nilai baris tak nol pertama
        // bila seluruh baris adalah 0 akan di return 0
        double element = 0;
        int j = GetLeadingValue_Row_ColID(row);
        if (j != COLCOUNT) {
            element = GetElement(row, j);
        }
        return element;
    }

    public int GetLeadingValue_Col_RowID(int col) {
        //mencari nilai leading dari kolom col, me-return indeks baris dengan kolom tak nol pertama dari row
        // bila input tidak valid, return -1
        //jika semua nilai di kolom bernilai 0 return ROWCOUNT
        int i = -1;
        if (IsValidColID(col)) {
            i = GetFirstRowID();
            int iFinal = GetLastRowID();
            boolean isFound = false;
            while ((i <= iFinal) && !(isFound)) {
                if (GetElement(i, col) != 0) {
                    isFound = true;
                }
                else {
                    i += 1;
                }
            }
        }
        return i;
    }

    public int GetLeadingValue_Col_RowID_Next(int col) {
        int i = GetLeadingValue_Col_RowID(col);
        if (i <= GetLastRowID()) {
            i += 1;
            int iFinal = GetLastRowID();
            boolean isFound = false;
            while ((i <= iFinal) && !(isFound)) {
                if (GetElement(i, col) != 0) {
                    isFound = true;
                }
                else {
                    i += 1;
                }
            }
        }
        return i;
    }

    public int GetLeadingValue_Col_RowID_FromRow(int col, int row) {
        //mencari nilai leading dari kolom col, me-return indeks baris dengan kolom tak nol pertama dari row
        // bila input col tidak valid, return -1
        // bila input row tidak valid, return -2
        //jika semua nilai di kolom bernilai 0 return ROWCOUNT
        int i = -1;
        if (IsValidColID(col)) {
            i = -2;
            if (IsValidRowID(row)) {
                i = row;
                int iFinal = GetLastRowID();
                boolean isFound = false;
                while ((i <= iFinal) && !(isFound)) {
                    if (GetElement(i, col) != 0) {
                        isFound = true;
                    }
                    else {
                        i += 1;
                    }
                }
            }
        }
        return i;
    }

    public int GetLeadingValue_Col_RowID_FromRow_Next(int col, int row) {
        int i = GetLeadingValue_Col_RowID_FromRow(col, row);
        if (i <= GetLastRowID()) {
            i += 1;
            int iFinal = GetLastRowID();
            boolean isFound = false;
            while ((i <= iFinal) && !(isFound)) {
                if (GetElement(i, col) != 0) {
                    isFound = true;
                }
                else {
                    i += 1;
                }
            }
        }
        return i;
    }

    public void UpperTriangularSelectionSort() {
        int j = GetFirstColID();
        int jFinal = GetLastColID();
        int currentRow = GetFirstRowID();
        int finalRow = GetLastRowID();
        int i;
        while ((currentRow <= finalRow) && (j <= jFinal)) {
            i = GetLeadingValue_Col_RowID_FromRow(j, currentRow);
            if (i != currentRow) {
                if (i == ROWCOUNT) {
                    j += 1;
                }
                else {
                    OBE_SwapRow(currentRow, i);
                }
            }
            currentRow += 1;
        }
    }

    public void OBE_ScaleRow_LeadingOne(int row) {
        if (GetLeadingValue_Row_ColID(row) < COLCOUNT) {
            double k = 1 / GetLeadingValue_Row_Element(row);
            OBE_ScaleRow(row, k);
        }
    }

    public void Convert_RowEchelon() {
        UpperTriangularSelectionSort();
        int finalRow = GetLastRowID();
        int i;
        for (int currentRow = GetFirstRowID(); currentRow <= finalRow; currentRow += 1) {
            OBE_ScaleRow_LeadingOne(currentRow);
            int j = GetLeadingValue_Row_ColID(currentRow);
            i = currentRow + 1;
            while ((i <= finalRow) && (GetLeadingValue_Row_ColID(i) == GetLeadingValue_Row_ColID(currentRow))) {
                OBE_SumRow(i, currentRow, (- GetElement(i, j)));
                if (GetElement(i, j) < 0) {
                    System.out.println("Ini jalan");
                    OBE_SumRow(i, i, (- GetElement(i, j)));
                }
                i += 1;
            }
        }
    }

    public void Convert_ReducedRowEchelon() {
        Convert_RowEchelon();
        int i = GetFirstRowID();
        int iFinal = GetLastRowID();
        while ((i <= iFinal) && (GetLeadingValue_Row_ColID(i) != COLCOUNT)) {
            int j = GetLeadingValue_Row_ColID(i);
            for (int k = 0; k < i; k += 1) {
                OBE_SumRow(k, i, (- GetElement(k, j)));
            }
            i += 1;
        }
    }
}
