/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.couderq.treillis;

import static fr.insa.couderq.treillis.Noeud.nextID;
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
    
    public void removeAllN(List<Noeud> ln) {
        for(Noeud n : ln) {
            this.remove(n);
        }
    }
    // on comprend pas trop la différence
    public void clearN() {
        List<Noeud> toRemove = new ArrayList<>(this.noeuds);
        this.removeAllN(toRemove);
    }

    public int sizeN() {
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
    
    public void removeAllB(List<Barre> lb) {
        for(Barre b : lb) {
            this.remove(b);
        }
    }
    // on comprend pas trop la différence
    public void clearB() {
        List<Barre> toRemove = new ArrayList<>(this.barres);
        this.removeAllB(toRemove);
    }

    public int sizeB() {
        return this.barres.size();
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

    public static Groupe groupeTest() {
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
    }

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

    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher le treilli");
            System.out.println("2) ajouter un noeud");
            System.out.println("3) ajouter une barre avec deux nouveaux noeuds");
            System.out.println("4) ajouter une barre sur deux noeuds existants");
            System.out.println("5) créer un sous-groupe");
            System.out.println("6) afficher la zone de construction");
            System.out.println("7) calculer la distance à un point");
            System.out.println("8) retirer des figures du groupe");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 2) {
                Point np = Point.demandePoint();
                this.add(np);
            } else if (rep == 3) {
                Segment ns = Segment.demandeSegment();
                this.add(ns);
            } else if (rep == 4) {
                System.out.println("choisissez le début du segment");
                Point deb = this.choisiPoint();
                if (deb != null) {
                    System.out.println("choisissez la fin du segment");
                    Point fin = this.choisiPoint();
                    Segment ns = new Segment(deb, fin);
                    this.add(ns);
                }
            } else if (rep == 5) {
                List<Figure> select = this.choisiFigures();
                this.sousGroupe(select);
            } else if (rep == 6) {
                System.out.println("maxX = " + this.maxX() + " ; " +
                        "minX = " + this.minX() + "\n" +
                        "maxY = " + this.maxY() + " ; " +
                        "minY = " + this.minY() + "\n");
            } else if (rep == 7) {
                System.out.println("entrez un point :");
                Point p = Point.demandePoint();
                System.out.println("distance : "+this.distancePoint(p));
            } else if (rep == 8) {
                List<Figure> select = this.choisiFigures();
                this.removeAll(select);
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


