/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Administrateur
 */
public class Treilli {
    
    private List<Noeud> noeuds;
    private List<NoeudSimple> noeudsS;
    private List<Barre> barres;
    private List<Type> catalogue;
    private List<Triangle> terrain;
    private List<Segment> segments;
    private List<AppuiSimple> appuisS;
    private List<AppuiDouble> appuisD;
    private List<Figure> figures;
    private List<Point> points;
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private int id;
    
    private static int CUR_ID = 1;

    public static int nextID() {
        int res = CUR_ID;
        CUR_ID ++;
        return res;
    }
    
    public Treilli(double xmin, double xmax, double ymin, double ymax) {
        this.id = nextID();
        this.noeuds = new ArrayList<Noeud>();
        this.noeudsS = new ArrayList<NoeudSimple>();
        this.barres = new ArrayList<Barre>();
        this.catalogue = new ArrayList<Type>();
        this.terrain = new ArrayList<Triangle>();
        this.segments = new ArrayList<Segment>();
        this.appuisS = new ArrayList<AppuiSimple>();
        this.appuisD = new ArrayList<AppuiDouble>();
        this.figures = new ArrayList<Figure>();
        this.points = new ArrayList<Point>();
        
        /* pas utile puisqu'à chaque fois qu'on crée une figure, la figure est ajoutée à la liste figures (constructeur Figure)
        for(int i =0;i<this.noeuds.size();i++){
            this.figures.add(this.noeuds.get(i));
        }
        for(int i =0;i<this.barres.size();i++){
            this.figures.add(this.barres.get(i));
        }
        for(int i =0;i<this.terrain.size();i++){
            this.figures.add(this.terrain.get(i));
        }
        for(int i =0;i<this.appuisS.size();i++){
            this.figures.add(this.appuisS.get(i));
        }
        for(int i =0;i<this.appuisD.size();i++){
            this.figures.add(this.appuisD.get(i));
        }
        for(int i =0;i<this.noeudsS.size();i++){
            this.figures.add(this.noeudsS.get(i));
        }
        for(int i =0;i<this.noeudsS.size();i++){
            this.figures.add(this.noeudsS.get(i));
        }
        for(int i =0;i<this.noeudsS.size();i++){
            this.figures.add(this.noeudsS.get(i));
        }
        */ 
        
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }
    public Treilli() { //valeurs par défaut
        this(800,800,800,800);
    }
    public Treilli(double xmin, double xmax, double ymin, double ymax, int id) {
        this.id = id;
        this.noeuds = new ArrayList<Noeud>();
        this.noeudsS = new ArrayList<NoeudSimple>();
        this.barres = new ArrayList<Barre>();
        this.catalogue = new ArrayList<Type>();
        this.terrain = new ArrayList<Triangle>();
        this.segments = new ArrayList<Segment>();
        this.appuisS = new ArrayList<AppuiSimple>();
        this.appuisD = new ArrayList<AppuiDouble>();
        this.figures = new ArrayList<Figure>();
        this.points = new ArrayList<Point>();
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
    }
    

    public int getId() {
        return id;
    }
    
    public double getXmin() {
        return xmin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmin() {
        return ymin;
    }

    public double getYmax() {
        return ymax;
    }
    
    public List<Triangle> getTerrain(){
        return terrain;
    }

    public void setBarres(Barre b) {
        this.barres.add(b);
    }

    public List<Figure> getFigures() {
        return figures;
    }
    
    public void setFigures(Figure f) {
        this.figures.add(f);
    }
    
    public void setNoeuds(Noeud n) {
        this.noeuds.add(n);
    }
    
    public void setNoeudsS(NoeudSimple ns) {
        this.noeudsS.add(ns);
    }
    
    public void setAppuisS(AppuiSimple as) {
        this.appuisS.add(as);
    }
    
    public void setAppuisD(AppuiDouble ad) {
        this.appuisD.add(ad);
    }
    
    public void setPoints(Point p) {
        this.points.add(p);
    }
    
    public void setSegments(Segment s) {
        this.segments.add(s);
    }
    
    public void setTerrain(Triangle t) {
        this.terrain.add(t);
    }
    //TODO 
    //fonction qui calcule le cout total du treillis
    //Figure
    public void add(Figure f) {
        if (! figures.contains(f)) {
            this.figures.add(f);
        }else {
            System.out.println("Noeud deja dans treilli");
        }
    }
    
    ///NOEUD///
    public void add(Noeud n) {
        if (! noeuds.contains(n)) {
            this.noeuds.add(n);
        }else {
            System.out.println("Noeud deja dans treilli");
        }
    }
    
    public void remove(Noeud n) {
        if (! noeuds.contains(n)) {
            System.out.println("Noeud pas dans le treilli");
        }
        this.noeuds.remove(n);
    }
    
    public void removeAllNoeuds(List<Noeud> ln) {
        for(Noeud n : ln) {
            this.remove(n);
        }
    }
    // on comprend pas trop la différence
    public void clearNoeuds() {
        List<Noeud> toRemove = new ArrayList<>(this.noeuds);
        this.removeAllNoeuds(toRemove);
    }

    public int sizeNoeuds() {
        return this.noeuds.size();
    }
    // Noeud Simple
    public void add(NoeudSimple ns) {
        if (! noeudsS.contains(ns)) {
            this.noeudsS.add(ns);
        }else {
            System.out.println("Noeud Simple deja dans treilli");
        }
    }
    //Appui Simple
    public void add(AppuiSimple as) {
        if (! appuisS.contains(as)) {
            this.appuisS.add(as);
        }else {
            System.out.println("Appui Simple deja dans treilli");
        }
    }
    //Appui Double
    public void add(AppuiDouble ad) {
        if (! appuisD.contains(ad)) {
            this.appuisD.add(ad);
        }else {
            System.out.println("Appui Double deja dans treilli");
        }
    }
    
    ///BARRE///
    public void add(Barre b) {
        if (! barres.contains(b)) {
            this.barres.add(b);
        }else {
            System.out.println("Barre deja dans treilli");
        }
    }
    
    public void remove(Barre b) {
        if (! barres.contains(b)) {
            System.out.println("Barre pas dans le treilli");
        }
        this.barres.remove(b);
    }
    
    public void removeAllBarres(List<Barre> lb) {
        for(Barre b : lb) {
            this.remove(b);
        }
    }
    
    
    // on comprend pas trop la différence
    public void clearBarres() {
        List<Barre> toRemove = new ArrayList<>(this.barres);
        this.removeAllBarres(toRemove);
    }

    public int sizeBarres() {
        return this.barres.size();
    }
    
    
    ///TERRAIN///
    public void add(Triangle t) {
        if (! terrain.contains(t)) {
            this.terrain.add(t);
        }else {
            System.out.println("Triangle deja dans terrain");
        }
    }
    
    public void remove(Triangle t) {
        if (! terrain.contains(t)) {
            System.out.println("Triangle pas dans le terrain");
        }
        this.terrain.remove(t);
    }
    
    public void removeAllTerrain(List<Triangle> lt) {
        for(Triangle t : lt) {
            this.remove(t);
        }
    }
    //Segment 
    
    public void remove(Segment s) {
        if (! segments.contains(s)) {
            System.out.println("Segment pas dans le terrain");
        }
        this.removeAllTerrain(s.getTriangles()); 
        this.segments.remove(s);
    }
    
    public void removeAllSegments(List<Segment> ls) {
        for(Segment s : ls) {
            this.remove(s);
        }
    }
    
    public void add(Segment s) {
        if (! segments.contains(s)) {
            this.segments.add(s);
        }else {
            System.out.println("Segment deja dans treilli");
        }
    }
    
    //Point
    public void add(Point p) {
        if (! points.contains(p)) {
            this.points.add(p);
        }else {
            System.out.println("point deja dans treilli");
        }
    }
    
    //Figure
    public void remove(Figure f) {
        if (! figures.contains(f)) {
            System.out.println("figure pas dans le treilli");
        }
        this.figures.remove(f);
        if(f instanceof AppuiDouble){
            this.appuisD.remove(f);
        }
        if(f instanceof AppuiSimple){
            this.appuisS.remove(f);
        }
        if(f instanceof Barre){
            this.barres.remove(f);
        }
        if(f instanceof Noeud){
            this.noeuds.remove(f);
        }
        if(f instanceof NoeudSimple){
            this.noeudsS.remove(f);
        }
        if(f instanceof Point){
            this.points.remove(f);
        }
        if(f instanceof Segment){
            this.segments.remove(f);
        }
        if(f instanceof Triangle){
            this.terrain.remove(f);
        }
    }
    
    public void removeAllFigures(List<Figure> lf) {
        for(Figure f : lf) {
            this.remove(f);
        }
    }
    
    // on comprend pas trop la différence
    public void clearTerrain() {
        List<Triangle> toRemove = new ArrayList<>(this.terrain);
        this.removeAllTerrain(toRemove);
    }

    public int sizeTerrain() {
        return this.terrain.size();
    }
    
    public int sizeAppuisS() {
        return this.appuisS.size();
    }
    public int sizeAppuisD() {
        return this.appuisD.size();
    }
    
    /**
     * retourne la figure contenue dans le groupe la plus proche du point et 
     * au maximum à distMax du point;
     * retourne null si aucune figure n'est à une distance plus faible que
     * distMax;
     */
    public Figure plusProche(Point p,double distMax) {
        //if (this.noeuds.isEmpty() && this.barres.isEmpty() && this.terrain.isEmpty()) {
           //return null;
        if (this.figures.isEmpty()){
            return null;
        } else {
            Figure fmin = (Figure)this.figures.get(0); 
            double min = fmin.distancePoint(p);
            for(int i = 1 ; i < this.figures.size() ; i ++) {
                Figure fcur = this.figures.get(i);
                double cur = fcur.distancePoint(p);
                if (cur < min) {
                    min = cur;
                    fmin = fcur;
                }
            }
            if (min <= distMax) {
                return fmin;
            } else {
                return null;
            }
        }
    }

//cpd pas
    public static String indente(String toIndente, String prefix) {
        return prefix + toIndente.replaceAll("\n", "\n" + prefix);
    }
//ckoiget(i)
    @Override
    public String toString() {
        String res = "Treilli {\n";
        res = res + "Noeuds {\n";
        for (int i = 0; i < this.noeuds.size(); i++) {
            res = res + indente(this.noeuds.get(i).toString(), "  ") + "\n";
        }
        res =res +"}\n Barres {";
        for (int i = 0; i < this.barres.size(); i++) {
            res = res + indente(this.barres.get(i).toString(), "  ") + "\n";
        }
        res = res +"}\n Terrain {";
        for (int i = 0; i < this.terrain.size(); i++) {
            res = res + indente(this.terrain.get(i).toString(), "  ") + "\n";
        }
        return res + "}";
    }

    /*public static Groupe groupeTest() {
        Point p1 = new Point(10,10);
        Point p2 = new Point(100, 10);
        Point p3 = new Point(100, 100);
        Point p4 = new Point(10, 100);
        Point p5 = new Point(50, 50);
        Segment s1 = new Segment(p1, p2);
        Segment s2 = new Segment(p2, p3);
        Segment s3 = new Segment(p3, p1);
        Segment s4 = new Segment(p1, p4);
        Groupe triangle = new Groupe();
        triangle.add(s1);
        triangle.add(s2);
        triangle.add(s3);
        Groupe res = new Groupe();
        res.add(p5);
        res.add(s4);
        res.add(triangle);
        return res;
    }*/

    public Noeud choisiNoeud() {
        List<Noeud> ln = new ArrayList<>();
        System.out.println("liste des noeuds disponibles : ");
        int nbr = 0;
        for (int i = 0; i < this.noeuds.size(); i++) {
            Noeud n = this.noeuds.get(i);
            if (n instanceof Noeud) {
                nbr++;
                ln.add((Noeud) n);
                System.out.println(nbr + ") " + n);
            }
        }
        if (nbr == 0) {
            System.out.println("Aucun noeud disponible");
            return null;
        } else {
            int rep = -1;
            while (rep < 0 || rep > nbr) {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0) {
                return null;
            } else {
                return ln.get(rep - 1);
            }
        }
    }
    
    public Barre choisiBarre() {
        List<Barre> lb = new ArrayList<>();
        System.out.println("liste des barres disponibles : ");
        int nbr = 0;
        for (int i = 0; i < this.barres.size(); i++) {
            Barre b = this.barres.get(i);
            if (b instanceof Barre) {
                nbr++;
                lb.add((Barre) b);
                System.out.println(nbr + ") " + b);
            }
        }
        if (nbr == 0) {
            System.out.println("Aucune barre disponible");
            return null;
        } else {
            int rep = -1;
            while (rep < 0 || rep > nbr) {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0) {
                return null;
            } else {
                return lb.get(rep - 1);
            }
        }
    }
    
    public Triangle choisiTriangleDsTerrain() {
        List<Triangle> lt = new ArrayList<>();
        System.out.println("Liste des triangles disponibles dans le terrain: ");
        int nbr = 0;
        for (int i = 0; i < this.terrain.size(); i++) {
            Triangle t = this.terrain.get(i);
            if (t instanceof Triangle) {
                nbr++;
                lt.add((Triangle) t);
                System.out.println(nbr + ") " + t);
            }
        }
        if (nbr == 0) {
            System.out.println("Aucun triangle disponible");
            return null;
        } else {
            int rep = -1;
            while (rep < 0 || rep > nbr) {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0) {
                return null;
            } else {
                return lt.get(rep - 1);
            }
        }
    }
    
    public Type choisiType() {
        List<Type> lt = new ArrayList<>();
        System.out.println("liste des types disponibles : ");
        int nbr = 0;
        for (int i = 0; i < this.catalogue.size(); i++) {
            Type t = this.catalogue.get(i);
            if (t instanceof Type) {
                nbr++;
                lt.add((Type) t);
                System.out.println(nbr + ") " + t);
            }
        }
        if (nbr == 0) {
            System.out.println("Aucun type disponible");
            return null;
        } else {
            int rep = -1;
            while (rep < 0 || rep > nbr) {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0) {
                return null;
            } else {
                return lt.get(rep - 1);
            }
        }
    }

    /*public List<Figure> choisiFigures() {
        List<Figure> res = new ArrayList<>();
        int rep = -1;
        while (rep != 0) {
            System.out.println("liste des figures disponibles : ");
            for (int i = 0; i < this.contient.size(); i++) {
                System.out.println((i + 1) + ") " + this.contient.get(i));
            }
            System.out.println("votre choix (0 pour finir) : ");
            rep = Lire.i();
            if (rep > 0 && rep <= this.contient.size()) {
                Figure f = this.contient.get(rep - 1);
                if (res.contains(f)) {
                    System.out.println("déja selectionnée !!");
                } else {
                    res.add(f);
                }
                System.out.println(res.size() + " figure(s) séléctionnée(s)");
            }
        }
        return res;
    }*/

    public static void menuTexte() {
        
        System.out.println("Donnez les coordonnées xmin, xmax, ymin, ymax de la zone de construction");
        double xmin = Lire.d();
        double xmax = Lire.d();
        double ymin = Lire.d();
        double ymax = Lire.d();
        Treilli treilli = new Treilli (xmin, xmax, ymin, ymax);                    // ?? est-ce qu'on utilise this, est-ce qu'on en fait une méthode
        System.out.println("xmax = " +treilli.xmax+ " ; " + "xmin = " + treilli.xmin + "\n" +"ymax = " + treilli.ymax + " ; " +"ymin = " + treilli.ymin + "\n");


        int rep = -1;
        while (rep != 0) {
            System.out.println("Definition du terrain : ");
            System.out.println("1)Ajouter un triangle");
            System.out.println("2)Supprimer un triangle");
            System.out.println("3)Supprimer tout");
            rep = Lire.i();

            if (rep==1){
                double px, py;
                System.out.println("Donnez les positions des sommets du triangle");
                System.out.println("Sommet A : ");
                px = Lire.d();
                py = Lire.d();
                Point A = new Point (treilli,px, py);

                System.out.println("Sommet B : ");
                px = Lire.d();
                py = Lire.d();
                Point B = new Point (treilli,px, py);

                System.out.println("Sommet C : ");
                px = Lire.d();
                py = Lire.d();
                Point C = new Point (treilli,px, py);

                Triangle ABC = new Triangle (treilli,A,B,C);
                treilli.add(ABC);
            }else if(rep==2){

                Triangle sup = treilli.choisiTriangleDsTerrain();                                    // ?? est-ce qu'on utilise this, méthode ?
                treilli.remove(sup);
            }
            else if(rep==3){
                treilli.clearTerrain();
            }    
        }


        System.out.println("Construction Treilli");
        int rep2 = -1;
        while (rep2 != 0) {
        System.out.println("1)Creer un noeud simple");
        System.out.println("3)Creer un appuis double");
        System.out.println("4)Creer un appuis simple");
        System.out.println("5)Creer une barre");
        System.out.println("7)Modifier type de barre");
        System.out.println("8)Supprimer un noeud");
        System.out.println("9)Supprimer une barre");
        rep2 =Lire.i();

        if (rep2==1){
            System.out.println("Donnez px, py");
            double px = Lire.d();
            double py = Lire.d();
            NoeudSimple N = new NoeudSimple(treilli,px,py);
            treilli.add(N);
        }else if(rep2==3){
            //TODO list

        }else if(rep2==4){
                //TODO list

        }else if(rep2==5){
            System.out.println("Choisissez noeud de début, noeud de fin, type de barre");   //TODO
            Noeud dbt = treilli.choisiNoeud();
            Noeud fin = treilli.choisiNoeud();
            
        }else if(rep2==7){
            Barre b = treilli.choisiBarre();
            Type type = treilli.choisiType();
            b.setType(type);
        }
        else if(rep2==8){
            Noeud n = treilli.choisiNoeud();
            treilli.remove(n);
            treilli.removeAllBarres(n.getBarres());
        }
        else if(rep2==9){
            Barre b = treilli.choisiBarre();
            treilli.remove(b);
        }

        }
        
        System.out.println("Etude du treilli");
        int rep3 = -1;
        
        while (rep3 != 0) {
            rep3 = Lire.i();
    
           //isostatique 2.ns = nb + nsas + 2.nsad
            if (2*treilli.sizeNoeuds()== treilli.sizeBarres()+ treilli.sizeAppuisS() + 2*treilli.sizeAppuisD()){
            //calcul force de compression traction et réaction
            //comparer type
            //afficher les barres rouge + commentaire
            //retour au treilli
            //sauvegarder
            }
            
        }
    }   
    /*   
    public Matrice Calcul () {
        Matrice Res = new Matrice (2* this.sizeNoeuds(),2* this.sizeNoeuds()+1 );
        return Res;
    }
    */

    /*
    public void draw ( GraphicsContext gc) { //méthode remplacée par dessine
        for(Figure f : this.figures) { 
            f.draw(gc);
        }
        
    }*/

    public void save(Writer w, Numeroteur<Figure> num) throws IOException {
            w.append("Treilli;" + id+";"+xmin+";"+xmax+";"+ymin+";"+ymax);
            w.append("\n");
    }
    
    public void sauvegarde(File fout) throws IOException {
        Numeroteur<Figure> num = new Numeroteur<Figure>();
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))) {
            this.save(bout, num);
        }
    }

}



