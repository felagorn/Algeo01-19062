package flib;

import java.util.Scanner;
// import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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

    public static Matrix Create_FromUserInput() {
        int row, col;
        // menerima input pengguna untuk mengisi Matrix
        Scanner scanner = new Scanner(System.in);
        System.out.print("Banyak baris matriks: ");
        row = scanner.nextInt();
        System.out.println();
        System.out.print("Banyak kolom matriks: ");
        col = scanner.nextInt();
        System.out.println();
        Matrix matrix = new Matrix(row, col);
        int iFinal = matrix.GetLastRowID();
        int jFinal = matrix.GetLastColID();
        for (int i = matrix.GetFirstRowID(); i <= iFinal; i++) {
            for (int j = matrix.GetFirstColID(); j <= jFinal; j++) {
                matrix.DF[i][j] = scanner.nextDouble();
            }
        }
        scanner.close();
        return matrix;
    }

     /* Read From txt File */
    public static Matrix readFromTxt(String path){ 
        Matrix M = new Matrix(0,0);
        File f = new File(path);
            ArrayList<ArrayList<Double>> arrayList2D = new ArrayList<ArrayList<Double>>();
            try {
                Scanner rowScanner = new Scanner(f);
                int row = -1;
                while (rowScanner.hasNext()) {
                    row += 1;
                    arrayList2D.add(new ArrayList<Double>());
                    String line = rowScanner.nextLine();
                    Scanner scanValue = new Scanner(line);
                    scanValue.useDelimiter(" ");
                    while (scanValue.hasNext()){
                        double value = scanValue.nextDouble();
                        arrayList2D.get(row).add(value);
                        
                    }
                    scanValue.close();
                }
                rowScanner.close();
                

                if (row!=-1){
                    int rowCount = arrayList2D.size();
                    int columnCount = arrayList2D.get(0).size();
                    M = new Matrix(rowCount, columnCount);
                    M.ROWCOUNT = rowCount;
                    M.COLCOUNT = columnCount;
                    for(int i=0;i<rowCount;i++){
                        for(int j=0;j<columnCount;j++){
                            M.SetElement(i, j,  arrayList2D.get(i).get(j));
                        }
                    }


                } else{
                    System.out.println("File Kosong");
                }


                //arrayList2D sudah terisi penuh dari file
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not Found");
            } finally{
                return M;
            }
        }

    
     /* Read From txt File */
     public static Matrix Create_FromTxt() { 
        Matrix M;
        ArrayList<ArrayList<Double>> arrayList2D = new ArrayList<ArrayList<Double>>();
        int row = -1;
        boolean isValid = false;
        while (!(isValid)) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Path lengkap dari file .txt matriks: ");
                String path = input.nextLine();
                input.close();
                File f = new File(path);
                Scanner rowScanner = new Scanner(f);
                row = -1;
                while (rowScanner.hasNext()) {
                    row += 1;
                    arrayList2D.add(new ArrayList<Double>());
                    String line = rowScanner.nextLine();
                    Scanner scanValue = new Scanner(line);
                    scanValue.useDelimiter(" ");
                    while (scanValue.hasNext()){
                        double value = scanValue.nextDouble();
                        arrayList2D.get(row).add(value);
                    }
                    scanValue.close();
                    
                }
                rowScanner.close();
                
                
                isValid = true;
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not Found");
            }
        }
        if (row != -1) {
            int rowCount = arrayList2D.size();
            int columnCount = arrayList2D.get(0).size();
            M = new Matrix(rowCount, columnCount);
            for(int i = 0; i < rowCount; i++){
                for(int j = 0; j < columnCount; j++){
                    M.SetElement(i, j,  arrayList2D.get(i).get(j));
                }
            }
        } else {
            System.out.println("File Kosong");
            M = new Matrix(0, 0);
        }
        return M;
    }

        /* Make Hilbert Augmented Matrix for test case purpose */
        public static Matrix makeHilbertAugmented(int N){
            Matrix hilbertAugmented = new Matrix(N,N+1);
            for (int i=0;i<N;i++){
                for (int j=0;j<=N;j++){
                    if (j == N){
                        if (i == 0){
                            hilbertAugmented.SetElement(i, j, 1);
                        }else{
                            hilbertAugmented.SetElement(i, j, 0);
                        }
                    } else{
                        hilbertAugmented.SetElement(i, j, (double)1/(i+j+1));
                    }
                    
                }
            }
    
            return hilbertAugmented;
        }

    
        // public void addAllZeroColumn(){
        //     //In case ROWCOUNT > COLCOUNT
        //     for(int i=0; i<this.ROWCOUNT-this.COLCOUNT;i++){
        //         for(int row=0;row<=this.ROWCOUNT;row++){
        //             this.DF[row][this.COLCOUNT+i] = 0;
        //         }
        //     }
        //     this.COLCOUNT = this.ROWCOUNT;
        // }
    
        // public void removeAllZeroRow(){
        //     this.Convert_RowEchelon();
        //     for(int row=0;row<=this.GetLastRowID();row++){
        //         if(this.Row_IsAllZero(row)){
        //             this.ROWCOUNT-=1;
        //         }
        //     }
        // }
    
        // public boolean checkMatrix(){
        //     //Check only if when ROWCOUNT > COLCOUNT
        //     this.addAllZeroColumn();
        //     this.removeAllZeroRow();
        //     if (this.ROWCOUNT > this.COLCOUNT){
        //         return false;
        //     }return true;
        // }

        public boolean Row_IsAllZero(int row) {
            boolean isAllZero = true;
            int j = this.GetFirstColID();
            int jFinal = this.GetLastColID();
            while ((j <= jFinal) && (isAllZero)) {
                if (GetElement(row, j) != 0) {
                    isAllZero = false;
                }
            }
            return isAllZero; 
        }
    
        public boolean Row_IsAllZero_ExceptLast(int row) {
            for(int j=0;j<this.GetLastColID();j++){
                if(GetElement(row, j) != 0){
                    return false;
                }
            }
            return (this.GetElement(row, this.GetLastColID()) != 0);
        }
    
        public boolean Row_HasZero_ExceptLast(){
            for(int row=0;row<=this.GetLastRowID();row++){
                if (this.Row_IsAllZero_ExceptLast(row)){
                    return true;
                }
            }
            return false;
        }
    
        public boolean Row_OnlyHaveLeadingOne(int row){
            int totalOne = 0;
            for (int col=0;col<this.GetLastColID();col++){
                if (this.GetElement(row, col) == 1){
                    totalOne+=1;
                }else if (this.GetElement(row,col) != 0){
                    return false;
                }
            }
            return (totalOne==1);
        }
    
        public boolean is_all_zero_row(int row){
            for(int j=0;j<=this.GetLastColID();j++){
                if(GetElement(row, j) != 0){
                    return false;
                }
            }
            return true; 
            
        }
    
        public boolean is_all_zero_row_except_last(int row){
            for(int j=0;j<this.GetLastColID();j++){
                if(GetElement(row, j) != 0){
                    return false;
                }
            }
            return (this.GetElement(row, this.GetLastColID()) !=0); 
        }
    
        public boolean has_zero_row_except_last(){
            for(int row=0;row<=this.GetLastRowID();row++){
                if (this.is_all_zero_row_except_last(row)){
                    return true;
                }
            }
            return false;
        }
    
        public boolean only_have_leadingOne_row(int row){
            int totalOne = 0;
            for (int col=0;col<this.GetLastColID();col++){
                if (this.GetElement(row, col) == 1){
                    totalOne+=1;
                }else if (this.GetElement(row,col) != 0){
                    return false;
                }
            }
            return (totalOne==1);
        }
    
        public void ParametricOnGauss(){
            if (!this.has_zero_row_except_last()){   
                //M must be a row echelon augmented matrix (with <= 27 columns)
                char[] parametricVar = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                String[] parametricSolution = new String[this.GetCOLCOUNT()-1];
                char[] hasSolution = new char[this.GetCOLCOUNT()-1] ;
                for(int i=0;i<this.GetCOLCOUNT()-1;i++){
                    parametricSolution[i] ="";
                    hasSolution[i] ='0';
                }
                
                /* char '0' means it has unlimited solution
                   char '1' means it also has unlimited solution but it is bound by those with char '0'
                   char '2' means it only has 1 solution that we got from row that only has leading 1
                   char '3' means it only has 1 solution but we got it from subtracting*/ 
                double[] absoluteSolution = new double[this.GetCOLCOUNT()-1]; 
    
                int leadingOneCol;
                for(int row=0;row<=this.GetLastRowID();row++){
                    if(this.only_have_leadingOne_row(row)){
                        leadingOneCol = GetLeadingValue_Row_ColID(row);
                        absoluteSolution[leadingOneCol] = this.GetElement(row, this.GetLastColID());
                        hasSolution[leadingOneCol]='2'; 
                    }
                    else if(!this.is_all_zero_row(row)){
                        leadingOneCol = GetLeadingValue_Row_ColID(row);
                        hasSolution[leadingOneCol] = '1';
                        absoluteSolution[leadingOneCol] = this.GetElement(row, this.GetLastColID());
                    }
                }
    
                for(int row=0;row<=this.GetLastRowID();row++){
                    if(!this.is_all_zero_row(row)){
                        leadingOneCol = GetLeadingValue_Row_ColID(row);
                        // parametricSolution[leadingOneCol] = Double.toString(this.GetElement(row, this.GetLastColID()));
                        for(int col=leadingOneCol+1;col<this.GetLastColID();col++){
                            if (hasSolution[col] =='2'){
                                absoluteSolution[leadingOneCol] -= this.GetElement(row,col)*absoluteSolution[col] ;
                            }
                            else{
                                if(this.GetElement(row, col) > 0){
                                    parametricSolution[leadingOneCol] += " - ";
                                    parametricSolution[leadingOneCol] += Double.toString(this.GetElement(row, col));
                                    parametricSolution[leadingOneCol] += parametricVar[col];
                                }else if(this.GetElement(row, col)<0){
                                    parametricSolution[leadingOneCol] += " + ";
                                    parametricSolution[leadingOneCol] += Double.toString(-this.GetElement(row, col));
                                    parametricSolution[leadingOneCol] += parametricVar[col];
                                }
                            }
                        }
    
                    }
                }
    
    
                for(int col=0;col<this.GetLastColID();col++){
                    if(hasSolution[col] == '0'){
                        parametricSolution[col] = "" +parametricVar[col];
                    }else if(hasSolution[col] == '1'){
                        if(parametricSolution[col] !=""){
                            parametricSolution[col] = Double.toString(absoluteSolution[col]) + "" + parametricSolution[col];
                        } else{
                            hasSolution[col] = '3';
                            parametricSolution[col] = Double.toString((absoluteSolution[col]));
                        }
                        
                    } else{
                        parametricSolution[col] = Double.toString(absoluteSolution[col]);
                    }
                }
    
                for(int row=0;row<=this.GetLastRowID();row++){
                    if(!this.is_all_zero_row(row)){
                        leadingOneCol = GetLeadingValue_Row_ColID(row);
                        for(int col=this.GetLastColID()-2;col>leadingOneCol;col--){
                            if((hasSolution[col] == '1') || (hasSolution[col] =='3')){
                                parametricSolution[leadingOneCol]= parametricSolution[leadingOneCol].replace(""+parametricVar[col], "("+parametricSolution[col]+")");
                            }
                        }
                    }
    
                }
                
                for(int i=0;i<this.GetLastColID();i++){
                    System.out.println("x"+Integer.toString(i+1)+"= "+parametricSolution[i]);
                }
                
            } else{
                System.out.println("Linear Equation has no real solution");
            }  
        }

}