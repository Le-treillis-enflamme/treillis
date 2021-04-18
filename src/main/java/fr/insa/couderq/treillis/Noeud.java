/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Administrateur
 */
public abstract class Noeud extends Figure{

    private static int CUR_ID = 1;

    private int id;
    private List<Barre> barres;
    
    public Noeud (){
        this.id = Treilli.nextID();
        this.barres = new ArrayList<Barre>();
    }

    public void setBarres(List<Barre> barres) {
        this.barres = barres;
    }
    
    
    public abstract double calPx();
    public abstract double calPy();
    
}
