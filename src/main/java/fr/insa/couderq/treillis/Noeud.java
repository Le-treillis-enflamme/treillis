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
public abstract class Noeud {

private static int CUR_ID = 1;

public static int nextID() {
    int res = CUR_ID;
    CUR_ID ++;
    return res;
}
    
    private int id;
    
    public Noeud (){
        this.id = 
    }
    
    public abstract double calPx();
    public abstract double calPy();
    
}
