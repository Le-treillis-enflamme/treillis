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
public class Triangle {
    
    private int id;
   
    private Segment AB;
    private Segment BC;
    private Segment CA;
    
    public Triangle (Point A, Point B, Point C){
        this.AB = new Segment(A,B);
        this.BC = new Segment(B,C);
        this.CA = new Segment(C,A);
    }
}
