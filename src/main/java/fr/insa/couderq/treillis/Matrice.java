/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

/**
 *
 * @author Léa
 */

import java.lang.*;
import java.text.DecimalFormat;
import static javafx.application.Application.launch;
/**
 *
 * @author constantincouzinet
 */
public class Matrice {
    
    
    private int nbrLig;
    private int nbrCol;
    private double[][] coeffs;

    /**
     * le pivôt est considéré nul s'il est inférieur ou égal à EPSILON_PIVOT
     */
    private static final double EPSILON_PIVOT = 1E-8;

    /**
     * Cree la matrice nulle de taille nbrLig x nbrCol.
     */
    public Matrice(int nbrLig, int nbrCol) {
        this.nbrLig = nbrLig;
        this.nbrCol = nbrCol;
        this.coeffs = new double[nbrLig][nbrCol];
    }

    private Matrice(double[][] coeffs) {
        this.nbrLig = coeffs.length;
        this.nbrCol = coeffs[0].length;
        this.coeffs = coeffs;
    }
    
    public int getNbrLig() {
        return this.nbrLig;
    }

    public int getNbrCol() {
        return this.nbrCol;
    }

    public double get(int lig, int col) {
        return this.coeffs[lig][col];
    }

    public void set(int lig, int col, double val) {
        this.coeffs[lig][col] = val;
    }
    
    public static String formatDouble(double x) {
//        return formatDouble2Digits(x);
//        return formatDoubleMax2Decimales(x);
        return formatDoubleFixe(x);
    }

    public static String formatDoubleMax2Decimales(double x) {
        DecimalFormat f = new DecimalFormat("#.##");
        return f.format(x);
    }

    public static String formatDouble2Digits(double x) {
        return String.format("%+3.1f", x);
    }

    public static String formatDoubleFixe(double x) {
        return String.format("%+4.2E", x);
    }

    //--------------- partie 1.5
    @Override
    public String toString() {
        // oui, il serait plus efficace d'utiliser un {@link java.lang.StringBuilder}
        // mais ils n'ont pas été vu
        String res = "";
        for (int i = 0; i < nbrLig; i++) {
            res = res + "[";
            for (int j = 0; j < nbrCol; j++) {
                res = res + formatDouble(this.get(i, j));
                if (j < nbrCol - 1) {
                    res = res + " ";
                }
            }
            res = res + "]\n";
        }
        return res;
    }
    
    public Matrice subCols(int colMin, int colMax) {
        if (colMin < 0 || colMax < colMin || colMax >= this.getNbrCol()) {
            throw new Error("indices colonnes invalides");
        }
        Matrice res = new Matrice(this.getNbrLig(), colMax - colMin + 1);
        for (int lig = 0; lig < res.getNbrLig(); lig++) {
            for (int col = 0; col < res.getNbrCol(); col++) {
                res.set(lig, col, this.get(lig, colMin + col));
            }
        }
        return res;
    }

    
    public int permuteLigne(int i1, int i2){
        int res =1;
        double [] temp = new double [this.nbrCol];
        int k =0;
        if (!((i1<this.nbrLig) && (i2<this.nbrCol))){
            throw new Error("les lignes n'existent pas");
        }
        for (int j =0;j<this.nbrCol;j++){
            temp [j]=this.coeffs[i2][j];
        }
        for (int j =0;j<this.nbrCol;j++){
            this.coeffs[i2][j]=this.coeffs[i1][j];
        }
        for (int j =0;j<this.nbrCol;j++){
            this.coeffs[i1][j]=temp[j];
        }
        while ((res == 1) && (k<this.nbrCol)){
            for (int j =0;j<this.nbrCol;j++){
                if (this.coeffs[i1][j]!=this.coeffs[i2][j]){
                    res =-1;
                }
                k=k+1;
            }
        }
        return res;
    }
    
    public void Transvection(int i1, int i2){
        double p;
        if (i1>=this.nbrCol){
            throw new Error("i1>=nc");
        }
        if ((this.coeffs[i1][i1])==0){
            throw new Error("le pivot est nul");
        }
        p= (this.coeffs[i2][i1])/(this.coeffs[i1][i1]);
        
        for (int j=0;j<this.nbrCol;j++){
            this.set(i2, j, (this.get(i2,j)-p*this.get(i1,j)));
        }
        this.set(i2, i1, 0);
    }
    
    public int LignePlusGrandPivot(int e){
        int res =e;
        if (!((e<this.nbrLig) && (e<nbrCol))){
            throw new Error("ce pivot n'existe pas");
        }
        for (int j=e+1;j<this.nbrLig;j++){
            if (Math.abs(this.get(j, e))>Math.abs(this.get(e,e))){
                res=j;
            }
        }
        if (res<=EPSILON_PIVOT){
            res =-1;
        }
        return res;
    }
    
    public ResGauss descenteGauss(){
        ResGauss resgauss = new ResGauss(0,1);
        int pivotMax = 0;
         
        for (int j=0;j<Math.min(this.nbrLig, this.nbrCol);j++){
            pivotMax = LignePlusGrandPivot(j);
            resgauss.signature = resgauss.signature*permuteLigne(j,pivotMax);
            for (int k = j+1;k<this.nbrLig;k++){
                Transvection(j,k);
            }
            resgauss.rang++;
        }
        return resgauss;
    }
    
    public void remonteeGauss(){
        for (int j=Math.min(this.nbrLig, this.nbrCol);j>=0;j--){
            for (int k = j-1;k>=0;k--){
                Transvection(j,k);
            }
        }
    }
    
    public static void test4(Matrice m) {
       
        
        for (int i =0;i<m.nbrLig;i++){
            for (int j =0;j< m.nbrCol;j++){
                System.out.println("coeff ("+i+","+j+")");
                Lire.d();
            }
        }
        m.resolution().toString();
        
        
    }
    
    public double determinant(){
        double res;
        double p = this.get(0,0);
        for(int j=0;j<Math.min(this.nbrLig,this.nbrCol);j++){
            p=p*this.get(j, j);
        }
        res = this.descenteGauss().signature*p;
        return res;
    }
    
    public Matrice resolution(){
        Matrice res = new Matrice(this.nbrLig,1);
        double det = this.determinant();
        if(det==0){
            throw new Error("La matrice n'est pas inversible");
        }else{
            this.remonteeGauss();
            for(int j=0;j<Math.min(this.nbrLig,this.nbrCol);j++){
                for (int k=j+1; k<this.nbrCol;k++){
                    this.set(j, k, (this.get(k, k))/(this.get(j, j)));
                }
                this.set(j, j, 1);
            }
        }
        res = this.subCols(this.nbrCol-1,this.nbrCol-1);
        return res;
    }
    
    public static void main(String[] args) {
        Matrice m = new Matrice(3,4);
        test4(m);
    }
    
}



