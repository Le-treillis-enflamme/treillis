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
public class Barre extends Figure{
 
    private int id;
    private Type type;
    private Noeud dbt;
    private Noeud fin;
    
    public Barre(Noeud dbt, Noeud fin, Type type){
        this.id= Treilli.nextID();
        this.dbt=dbt;
        this.fin=fin;
        this.type=type;
        dbt.setBarres(this);
        fin.setBarres(this);
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Noeud getDbt() {
        return dbt;
    }

    public Noeud getFin() {
        return fin;
    }
    
    //TODO : set
    
}
