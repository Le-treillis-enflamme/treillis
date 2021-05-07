/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author Administrateur
 */
public class Point extends Figure{
    
    
    
    private double px;
    private double py;
    
    public Point(Treilli treilli, double x, double y, Color couleur){
        super(treilli,couleur);
        //TODO 
        //if ((x< Treilli.getXmin())||(x>getXmax()))||(y<getYmin())||(y>getYmax())){
         //   double xmin = Treilli.this.getXmin();
        
        this.px = x;
        this.py = y;
        this.getTreilli().setPoints(this);
    }
    
    public Point(Treilli treilli, double x, double y){
        this(treilli, x, y, Color.BLACK);
        this.getTreilli().setPoints(this);
    }

    public double getPx() {
        return px;
    }

    public double getPy() {
        return py;
    }
    
    @Override
    public double distancePoint(Point p) {
        double dx = this.px - p.px;
        double dy = this.py - p.py;
        return Math.sqrt(dx*dx+dy*dy);

    }
    
    @Override
    public double maxX() {
        return this.getPx();
    }

    @Override
    public double minX() {
        return this.getPx();
    }

    @Override
    public double maxY() {
        return this.getPy();
    }

    @Override
    public double minY() {
        return this.getPy();
    }
    
    @Override
    public void dessine(GraphicsContext context) {
        context.setFill(this.getCouleur());
        context.fillOval(this.getPx()-RAYON_IN_DRAW, this.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }
    
    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setFill(Figure.COULEUR_SELECTION);
        context.fillOval(this.getPx()-RAYON_IN_DRAW, this.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }
    
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if(! num.objExist(this)) {
            int id = num.creeID(this);
            w.append("Point;"+id+";"+this.getPx()+";"+this.getPy()+
                    ";" + Figure.saveColor(this.getCouleur()) + "\n");
        }
    }
}
