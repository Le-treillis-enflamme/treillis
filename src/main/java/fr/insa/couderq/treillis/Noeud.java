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
public abstract class Noeud extends Figure{

    private static int CUR_ID = 1;

    private int id;
    private List<Barre> barres;
    
    public Noeud (Treilli treilli, Color couleur){
        super(treilli,couleur);
        this.barres = new ArrayList<Barre>();
        this.getTreilli().setNoeuds(this);
    }

    public void setBarres(Barre b) {
        this.barres.add(b);
    }

    public List<Barre> getBarres() {
        return barres;
    }
    
    
    public abstract double calPx();
    public abstract double calPy();
    
    @Override
    public double maxX() {
        return this.calPx();
    }

    @Override
    public double minX() {
        return this.calPx();
    }

    @Override
    public double maxY() {
        return this.calPy();
    }

    @Override
    public double minY() {
        return this.calPy();
    }
    
    @Override
    public double distancePoint(Point p) {
        double dx = this.calPx() - p.getPx();
        double dy = this.calPy() - p.getPy();
        return Math.sqrt(dx*dx+dy*dy);

    }
    
    @Override
    public void dessine(GraphicsContext context) {
        context.setFill(this.getCouleur());
        context.fillOval(this.calPx()-RAYON_IN_DRAW, this.calPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }
    
    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setFill(Figure.COULEUR_SELECTION);
        context.fillOval(this.calPx()-RAYON_IN_DRAW, this.calPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }
    
    
    
}
