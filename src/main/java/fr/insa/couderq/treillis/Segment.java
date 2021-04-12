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
public class Segment {
    
    private Point dbt;
    private Point fin;
    
    public Segment(Point A, Point B){
        this.dbt = A;
        this.fin = B;
    }    

    public Point getDbt() {
        return dbt;
    }

    public Point getFin() {
        return fin;
    }
    
    
}
