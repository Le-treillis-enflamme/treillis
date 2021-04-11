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
public class NoeudSimple extends Noeud{
    
    private double px;
    private double py;

    public NoeudSimple(double x, double y) {
        
        this.px = x;
        this.py = y;    
    }
    
    @Override
    public double calPx() {
        return this.px;
    }

    @Override
    public double calPy() {
        return this.py;
    }
    
}
