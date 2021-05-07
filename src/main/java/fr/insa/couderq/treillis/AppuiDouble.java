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
public class AppuiDouble extends Appuis {
    
    private double Rx;
    private double Ry;
    
    public AppuiDouble(Treilli treilli, double a, Segment sgt) {
        super(treilli, a, sgt);
        this.getTreilli().setAppuisD(this);
    }
    
    public AppuiDouble(Treilli treilli,double a, Segment sgt, Color couleur) {
        super(treilli, a, sgt, couleur);
        this.getTreilli().setAppuisD(this);
    }
    
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if(! num.objExist(this)) {
            int id = num.creeID(this);
            w.append("Appui Double;"+id+";"+this.getA()+";"+this.getSgt()+
                    ";" + Figure.saveColor(this.getCouleur()) + "\n");
        }
    }

    
}


