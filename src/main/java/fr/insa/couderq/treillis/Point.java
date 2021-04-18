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
public class Point {
    
    private double px;
    private double py;
    
    public Point(double x, double y){
        //TODO 
        if ((x< Treilli.getXmin())||(x>getXmax()))||(y<getYmin())||(y>getYmax())){
            double xmin = Treilli.this.getXmin();
        this.px = x;
        this.py = y;
        }
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }
    
    
}
