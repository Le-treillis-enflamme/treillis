/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

/**
 *
 * @author Administrateur
 */
public class Noeud {
    
    private int id;
    private double px;
    private double py;
    
    public Noeud (double x, double y){
        int k=0;
        this.id = k;
        this.px = x;
        this.py = y;
        k=k+1;
    }//pbm avec id
    
    public String toString (){
        // test modifs
        return "coucou233" ;
    }
}
