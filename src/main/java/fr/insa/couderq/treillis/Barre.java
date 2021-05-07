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
public class Barre extends Figure{
 
    private int id;
    private Type type;
    private Noeud dbt;
    private Noeud fin;
    
    public Barre(Treilli treilli, Noeud dbt, Noeud fin, Type type, Color couleur){
        super(treilli, couleur);
        this.dbt=dbt;
        this.fin=fin;
        this.type=type;
        this.getTreilli().setBarres(this);
        dbt.setBarres(this);
        fin.setBarres(this);
    }
    
    public Barre(Treilli treilli, Noeud dbt, Noeud fin, Type type){
        super(treilli,Color.BLACK);
        this.dbt=dbt;
        this.fin=fin;
        this.type=type;
        this.getTreilli().setBarres(this);
        dbt.setBarres(this);
        fin.setBarres(this);
    }
    
    public Barre(Treilli treilli, Noeud dbt, Noeud fin){
        this(treilli, dbt,fin,new Type());
        this.dbt=dbt;
        this.fin=fin;
        this.type=type;
        this.getTreilli().setBarres(this);
        dbt.setBarres(this);
        fin.setBarres(this);
    }
    
   

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Noeud getDbt() {
        return dbt;
    }

    public Noeud getFin() {
        return fin;
    }
    
    //TODO : set
    @Override
    public double distancePoint(Point p) {
        double x1 = this.dbt.calPx();
        double y1 = this.dbt.calPy();
        double x2 = this.fin.calPx();
        double y2 = this.fin.calPy();
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
    public void dessine(GraphicsContext context) {
        context.setStroke(this.getCouleur());
        context.strokeLine(this.dbt.calPx(), this.dbt.calPy(), this.fin.calPx(), this.fin.calPy());
    }
    
    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setStroke(Figure.COULEUR_SELECTION);
        context.strokeLine(this.dbt.calPx(), this.dbt.calPy(), this.fin.calPx(), this.fin.calPy());
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
