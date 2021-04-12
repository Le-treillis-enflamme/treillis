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
public abstract class Appuis extends Noeud {
    
    private Segment sgt;
    private double a;
    
    public Appuis(double a, Segment sgt) {
        if ((a<0)||(a>1)){
            throw new Error ("(a<0)||(a>1)");
        }
        this.sgt = sgt;
        this.a = a;
    }

    @Override
    public double calPx() {
        double px = this.a*this.sgt.getDbt().getPx() + (1-this.a)*this.sgt.getFin().getPx();
        return px;
    }
    
    @Override
    public double  calPy(){
        double py = this.a*this.sgt.getDbt().getPy() + (1-this.a)*this.sgt.getFin().getPy();
        return py;
    }
}
