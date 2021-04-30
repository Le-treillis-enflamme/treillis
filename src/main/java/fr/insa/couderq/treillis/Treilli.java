/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class Treilli {
    
    private List<Noeud> noeuds;
    private List<Barre> barres;
    private List<Type> catalogue;
    private List<Triangle> terrain;
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
        this.barres = new ArrayList<Barre>();
        this.catalogue = new ArrayList<Type>();
        this.terrain = new ArrayList<Triangle>();
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
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

    //TODO 
    //fonction qui calcule le cout total du treillis
    
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
    // on comprend pas trop la différence
    public void clearTerrain() {
        List<Triangle> toRemove = new ArrayList<>(this.terrain);
        this.removeAllTerrain(toRemove);
    }

    public int sizeTerrain() {
        return this.terrain.size();
    }
    
    /**
     * retourne la figure contenue dans le groupe la plus proche du point et 
     * au maximum à distMax du point;
     * retourne null si aucune figure n'est à une distance plus faible que
     * distMax;
     */
    /*public Figure plusProche(Point p,double distMax) {
        if (this.contient.isEmpty()) {
            return null;
        } else {
            Figure fmin = this.contient.get(0); 
            double min = fmin.distancePoint(p);
            for(int i = 1 ; i < this.contient.size() ; i ++) {
                Figure fcur = this.contient.get(i);
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
    }*/

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
        System.out.println("xmax = " +this.xmax+ " ; " + "xmin = " + this.xmin + "\n" +"ymax = " + this.ymax + " ; " +"ymin = " + this.ymin + "\n");


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
                Point A = new Point (px, py);

                System.out.println("Sommet B : ");
                px = Lire.d();
                py = Lire.d();
                Point B = new Point (px, py);

                System.out.println("Sommet C : ");
                px = Lire.d();
                py = Lire.d();
                Point C = new Point (px, py);

                Triangle ABC = new Triangle (A,B,C);
                add(ABC);
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
        System.out.println("2)Creer un appuis encastre");
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
            NoeudSimple N = new NoeudSimple(px,py);
            add(N);
        }else if(rep2==2){         

        }else if(rep2==3){

        }else if(rep2==4){

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
            treilli.removeAllBarres(n.barres);
        }
        else if(rep2==9){
            Barre b = treilli.choisiBarre();
            treilli.remove(b);
        }

        }
    }

    public static void test1() {
        System.out.println("groupe test : \n" + Groupe.groupeTest());
    }

    public static void testMenu() {
        Groupe g = groupeTest();
        g.menuTexte();
    }

    public static void main(String[] args) {
//        test1();
        testMenu();
    }

    /**
     * abscice maximale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double maxX() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double max = this.contient.get(0).maxX();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).maxX();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }

    /**
     * abscice minimale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double minX() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double min = this.contient.get(0).minX();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).minX();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }

    /**
     * ordonnee maximale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double maxY() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double max = this.contient.get(0).maxY();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).maxY();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }

    /**
     * ordonnee minimale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double minY() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double min = this.contient.get(0).minY();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).minY();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }

    @Override
    public double distancePoint(Point p) {
        if (this.contient.isEmpty()) {
            return new Point(0,0).distancePoint(p);
        } else {
            double dist = this.contient.get(0).distancePoint(p);
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }

    }

    @Override
    public void draw(GraphicsContext gc) {
        for(Figure f : this.contient) {
            f.draw(gc);
        }
    }

}



