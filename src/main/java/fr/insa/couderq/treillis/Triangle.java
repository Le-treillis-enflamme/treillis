/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
/**
 *
 * @author Administrateur
 */
public class Triangle extends Figure {
    
    private int id;
   
    private Segment AB;
    private Segment BC;
    private Segment CA;
    private Polygon polygone; //le polygone permet de colorer l'intérieur du triangle avec la méthode fill
    
    public Triangle (Treilli treilli,Point A, Point B, Point C){
        super(treilli,Color.GREEN);
        this.AB = new Segment(this.getTreilli(),A,B);
        this.BC = new Segment(this.getTreilli(),B,C);
        this.CA = new Segment(this.getTreilli(),C,A);
        this.polygone = new Polygon();
        polygone.getPoints().addAll(new Double[]{ 
         A.getPx(), A.getPy(), 
         B.getPx(), B.getPy(),
         C.getPx(), C.getPy()
      }); 
        this.getTreilli().setTerrain(this);
    }
    
    public Triangle (Treilli treilli,Point A, Point B, Point C, Color couleur){
        super(treilli, couleur);
        this.AB = new Segment(this.getTreilli(),A,B);
        this.BC = new Segment(this.getTreilli(),B,C);
        this.CA = new Segment(this.getTreilli(),C,A);
        this.polygone = new Polygon();
        polygone.getPoints().addAll(new Double[]{ 
         A.getPx(), A.getPy(), 
         B.getPx(), B.getPy(),
         C.getPx(), C.getPy()
      }); 
        this.getTreilli().setTerrain(this);
    }
    
    @Override
    public double maxX() {
        double m;
        m = Math.max(this.AB.getDbt().maxX(), this.BC.getDbt().maxX()); //on ne peut pas trouver le max de trois éléments directement, il faut faire deux par deux
        return Math.max(m, this.CA.getDbt().maxX());
    }

    @Override
    public double minX() {
        double m;
        m = Math.min(this.AB.getDbt().minX(), this.BC.getDbt().minX()); 
        return Math.min(m, this.CA.getDbt().minX());
    }

    @Override
    public double maxY() {
        double m;
        m = Math.max(this.AB.getDbt().maxY(), this.BC.getDbt().maxY()); 
        return Math.max(m, this.CA.getDbt().maxY());
    }

    @Override
    public double minY() {
        double m;
        m = Math.min(this.AB.getDbt().minY(), this.BC.getDbt().minY()); 
        return Math.min(m, this.CA.getDbt().minY());
    }
    
    @Override
    public double distancePoint(Point p) { 
        double dAB, dBC,dCA,dABC;
        dAB = this.AB.distancePoint(p);
        dBC = this.BC.distancePoint(p);
        dCA = this.CA.distancePoint(p);
        dABC = Math.min(dAB, Math.min(dBC, dCA));
        return dABC;
    }
    
    @Override
    public void dessine(GraphicsContext context) {
        this.AB.dessine(context);
        this.BC.dessine(context);
        this.CA.dessine(context);
        this.polygone.setFill(Color.GREEN);
    }
        
    @Override
    public void dessineSelection(GraphicsContext context) {
        this.AB.dessine(context,COULEUR_SELECTION);
        this.BC.dessine(context,COULEUR_SELECTION);
        this.CA.dessine(context,COULEUR_SELECTION);
        this.polygone.setFill(COULEUR_SELECTION);
    }
    
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if (!num.objExist(this)) {
            int id = num.creeID(this);
            this.AB.getDbt().save(w, num);
            this.AB.getFin().save(w, num);
            this.BC.getDbt().save(w, num);
            this.BC.getFin().save(w, num);
            this.CA.getDbt().save(w, num);
            this.CA.getFin().save(w, num);
            w.append("Triangle;" + id + ";" +
                    num.getID(this.AB.getDbt()) + ";" + num.getID(this.BC.getDbt()) +";" + num.getID(this.CA.getDbt()) + //Pour sauvegarder un triangle on sauvegarde ses sommets car on ne peut pas sauvegarder les segments car ce ne sont pas des figures
                    ";" + Figure.saveColor(this.getCouleur())+"\n");
        }
    }
}
