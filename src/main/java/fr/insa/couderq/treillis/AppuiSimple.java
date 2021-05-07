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
 * @author constantincouzinet
 */
public class AppuiSimple extends Appuis{
    private double R;

    public AppuiSimple( Treilli treilli, double a, Segment sgt) {
        super(treilli, a, sgt);
        this.R = R;
        this.getTreilli().setAppuisS(this);
    }
    
    public AppuiSimple(Treilli treilli, double a, Segment sgt, Color couleur) {
        super(treilli,a, sgt, couleur);
        this.R = R;
        this.getTreilli().setAppuisS(this);
    }
    
    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
        if(! num.objExist(this)) {
            int id = num.creeID(this);
            w.append("Appui Simple ;"+id+";"+this.getA()+";"+this.getSgt()+
                    ";" + Figure.saveColor(this.getCouleur()) + "\n");
        }
    }

    
}
