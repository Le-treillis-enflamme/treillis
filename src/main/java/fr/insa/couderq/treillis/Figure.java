/*
Copyright 2000- Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.couderq.treillis;
import fr.insa.couderq.treillis.gui.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author francois
 */
public abstract class Figure {

    public static Color COULEUR_SELECTION = Color.RED;
    public static double RAYON_IN_DRAW = 5;
    /**
     * null si aucun groupe
     */
    private Treilli treilli;
    private Color couleur;
    private int id;

    public Treilli getTreilli() { //la figure doit connaître son treilli, c'est important lors de la lecture et en particulier lors de l'ouverture d'un mainpane
        return treilli;
    }
    
    public Figure(Treilli treilli, Color couleur) {
        this.id= Treilli.nextID();
        this.couleur = couleur;
        this.treilli = treilli;
        treilli.setFigures(this);
    }
    //void setTreilli(Treilli treilli) {
      //  this.treilli = treilli;
    //}
    
    
    public abstract double maxX();

    public abstract double minX();

    public abstract double maxY();

    public abstract double minY();

    public abstract double distancePoint(Point p);

    public abstract void dessine(GraphicsContext context);

    public abstract void dessineSelection(GraphicsContext context);
    
    public abstract void save(Writer w, Numeroteur<Figure> num) throws IOException;
    
    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
    public void changeCouleur(Color value) {
        this.setCouleur(value);
    }
    
    public static String saveColor(Color c) {
        return c.getRed()+";"+c.getGreen()+";"+c.getBlue();
    }
    
    public static Color parseColor(String sr, String sg, String sb) {
       double rouge = Double.parseDouble(sr);
       double vert = Double.parseDouble(sg);
       double bleu = Double.parseDouble(sb);
       return Color.color(rouge, vert, bleu);
    }
    
    public void sauvegarde(File fout) throws IOException {
        Numeroteur<Figure> num = new Numeroteur<Figure>();
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))) {
            this.save(bout, num);
        }
    }

    public static Figure lecture(File fin, Treilli treilli) throws IOException {
        Numeroteur<Figure> num = new Numeroteur<Figure>();
        Figure derniere = null;
        try (BufferedReader bin = new BufferedReader(new FileReader(fin))) {
            String line;
            while ((line = bin.readLine()) != null && line.length() != 0) {
                String[] bouts = line.split(";");
                if (bouts[0].equals("Treilli")) {
                    /*int TreilliId = Integer.parseInt(bouts[1]);
                    double xmin = Double.parseDouble(bouts[2]);
                    double xmax = Double.parseDouble(bouts[3]);
                    double ymin = Double.parseDouble(bouts[4]);
                    double ymax = Double.parseDouble(bouts[5]);
                Treilli treilli = new Treilli(xmin,xmax,ymin,ymax,TreilliId);
                num.associe(TreilliId, treilli);*/
                }else if (bouts[0].equals("Point")) {
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = Figure.parseColor(bouts[4], bouts[5], bouts[6]);
                    Point np = new Point(treilli,px, py, col);
                    num.associe(id, np);
                    derniere = np;
                } else if (bouts[0].equals("Segment")) {
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    Color col = Figure.parseColor(bouts[4], bouts[5], bouts[6]);
                    Point p1 = (Point) num.getObj(idP1);
                    Point p2 = (Point) num.getObj(idP2);
                    Segment ns = new Segment(treilli,p1, p2);
                    num.associe(id, ns);
                    derniere = ns;
                } else if (bouts[0].equals("Barre")) {
                    int id = Integer.parseInt(bouts[1]);
                    int idN1 = Integer.parseInt(bouts[2]);
                    int idN2 = Integer.parseInt(bouts[3]);
                    Noeud n1 = (Noeud) num.getObj(idN1);
                    Noeud n2 = (Noeud) num.getObj(idN2);
                    Barre nb = new Barre(treilli,n1,n2); //on utilise un type par défaut pour l'instant TODO
                    num.associe(id, nb);
                    derniere = nb;
                } else if (bouts[0].equals("Noeud Simple")) {
                    int id = Integer.parseInt(bouts[1]);
                    double x = Double.parseDouble(bouts[2]);
                    double y = Double.parseDouble(bouts[3]);
                    Color col = Figure.parseColor(bouts[4], bouts[5], bouts[6]);
                    NoeudSimple ns = new NoeudSimple(treilli,x, y, col);
                    num.associe(id, ns);
                    derniere = ns;
                } else if (bouts[0].equals("Appui Simple")) {
                    int id = Integer.parseInt(bouts[1]);
                    double a = Double.parseDouble(bouts[2]);
                    int idSgt = Integer.parseInt(bouts[3]);
                    Segment sgt = (Segment) num.getObj(idSgt);
                    Color col = Figure.parseColor(bouts[4], bouts[5], bouts[6]);
                    AppuiSimple as = new AppuiSimple(treilli,a, sgt,col);
                    num.associe(id, as);
                    derniere = as;
                } else if (bouts[0].equals("Appui Double")) {
                    int id = Integer.parseInt(bouts[1]);
                    double a = Double.parseDouble(bouts[2]);
                    int idSgt = Integer.parseInt(bouts[3]);
                    Segment sgt = (Segment) num.getObj(idSgt);
                    Color col = Figure.parseColor(bouts[4], bouts[5], bouts[6]);
                    AppuiDouble ad = new AppuiDouble(treilli,a, sgt,col);
                    num.associe(id, ad);
                    derniere = ad;
                } else if (bouts[0].equals("Triangle")) {
                    int id = Integer.parseInt(bouts[1]);
                    int idA = Integer.parseInt(bouts[2]);
                    int idB = Integer.parseInt(bouts[3]);
                    int idC = Integer.parseInt(bouts[4]);
                    Point A = (Point) num.getObj(idA);
                    Point B = (Point) num.getObj(idB);
                    Point C = (Point) num.getObj(idC);
                    Color col = Figure.parseColor(bouts[5], bouts[6], bouts[7]);
                    Triangle t = new Triangle(treilli,A,B,C,col);
                    num.associe(id, t);
                    derniere = t;
                }
            }

        }
        return derniere;
    }
    

}
