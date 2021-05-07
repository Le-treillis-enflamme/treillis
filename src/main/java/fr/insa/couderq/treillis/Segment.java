/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Administrateur
 */
public class Segment extends Figure{
    
    private Color couleur;
    private Point dbt;
    private Point fin;
    private List<Triangle> triangles;
    
    public Segment(Treilli treilli, Point A, Point B){
        super(treilli,Color.GREEN);
        this.dbt = A;
        this.fin = B;
        this.triangles = new ArrayList<Triangle>();
        this.getTreilli().setSegments(this);
    }     

    public Point getDbt() {
        return dbt;
    }

    public Point getFin() {
        return fin;
    }
    
    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    public void setTriangle(Triangle t) {
        this.triangles.add(t);
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }
    
  
    public double distancePoint(Point p) {
        double x1 = this.dbt.getPx();
        double y1 = this.dbt.getPy();
        double x2 = this.fin.getPx();
        double y2 = this.fin.getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.dbt.distancePoint(p);
        } else if (up > 1) {
            return this.fin.distancePoint(p);
        } else {
            Point p4 = new Point(this.getTreilli(),x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distancePoint(p);
        }
    }
    
    
    public void dessine(GraphicsContext context) {
        context.setStroke(this.getCouleur());
        context.strokeLine(this.dbt.getPx(), this.dbt.getPy(), this.fin.getPx(), this.fin.getPy());
    }
    
    
    public void dessine(GraphicsContext context, Color couleur) {
        context.setStroke(this.couleur);
        context.strokeLine(this.dbt.getPx(), this.dbt.getPy(), this.fin.getPx(), this.fin.getPy());
    }
    
    @Override
    public double maxX() {
        return Math.max(this.dbt.maxX(), this.fin.maxX());
    }

    @Override
    public double minX() {
        return Math.min(this.dbt.minX(), this.fin.minX());
    }

    @Override
    public double maxY() {
        return Math.max(this.dbt.maxY(), this.fin.maxY());
    }

    @Override
    public double minY() {
        return Math.min(this.dbt.minY(), this.fin.minY());
    }
    
    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setStroke(Figure.COULEUR_SELECTION);
        context.strokeLine(this.dbt.getPx(), this.dbt.getPy(), this.fin.getPx(), this.fin.getPy());
    }
    
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if (!num.objExist(this)) {
            int id = num.creeID(this);
            this.dbt.save(w, num);
            this.fin.save(w, num);
            w.append("Barre ;" + id + ";" +
                    num.getID(this.dbt) + ";" + num.getID(this.fin) +
                    ";" + Figure.saveColor(this.getCouleur())+"\n");
        }
    }
    
    

    
    
}
