/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;

/**
 *
 * @author Administrateur
 */
public class NoeudSimple extends Noeud{
    
    private double px;
    private double py;

    public NoeudSimple(Treilli treilli, double x, double y, Color couleur) {
        super(treilli, couleur);
        this.px = x;
        this.py = y; 
        this.getTreilli().setNoeudsS(this);
    }
    public NoeudSimple(Treilli treilli,double x, double y) {
        super(treilli,Color.BLACK);
        this.px = x;
        this.py = y;  
        this.getTreilli().setNoeudsS(this);
    }
    
    @Override
    public double calPx() {
        return this.px;
    }

    @Override
    public double calPy() {
        return this.py;
    }
    @Override
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if(! num.objExist(this)) {
            int id = num.creeID(this);
            w.append("Noeud Simple ;"+id+";"+this.calPx()+";"+this.calPy()+
                    ";" + Figure.saveColor(this.getCouleur()) + "\n");
        }
    }

    
}
