package src.gameobject.ObjCarcassonne;

//Import des autres dossiers
import src.gameobject.Tuile;

//Représente une tuile pour Carcassonne
public class TuileCarcassonne extends Tuile { // Une tuileCarcassonne est une tuile
    Lieu haut, bas, gauche, droite, centre; // Une tuile est composée de 4 lieux (un par côté)
    boolean symbole; // Une tuile peut posséder ou non un symbole
    boolean separator;
    String id; // Un identifiant pour retrouver l'image correspondante

    /// Constructeurs
    public TuileCarcassonne(Lieu h, Lieu d, Lieu b, Lieu g, Lieu c, boolean s, boolean sep) { // Constructeur avec des Lieux
        haut = h;
        bas = b;
        gauche = g;
        droite = d;
        centre = c;
        symbole = s;
        separator = sep;
        id = calculId();
    }

    // Getter et Setter
    public boolean isSeparator() {
        return separator;
    }
    public String getId() {
        return this.calculId();
    }

    public Lieu getBas() {
        return bas;
    }

    public Lieu getCentre() {
        return centre;
    }

    public Lieu getDroite() {
        return droite;
    }

    public Lieu getGauche() {
        return gauche;
    }

    public Lieu getHaut() {
        return haut;
    }

    public boolean getSymbole() {
        return symbole;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBas(Lieu bas) {
        this.bas = bas;
    }

    public void setDroite(Lieu droite) {
        this.droite = droite;
    }

    public void setCentre(Lieu centre) {
        this.centre = centre;
    }

    public void setGauche(Lieu gauche) {
        this.gauche = gauche;
    }

    public void setHaut(Lieu haut) {
        this.haut = haut;
    }

    public void setSymbole(boolean symbole) {
        this.symbole = symbole;
    }

    /// Méthodes de la class Tuile Carcassonne
    public boolean estComptabile(TuileCarcassonne t, int index) { // Vérifie la compatibilité entre 2 tuiles Carcassonne
        // Si t est en haut de la tuile (index = 0),
        // on vérifie que le haut de la tuile est compatabile avec le bas de t
        // c'est-à-dire que les objets sont les mêmes (.getClass())
        if (index == 0) {
            return haut.getClass() == t.bas.getClass();
        }
        if (index == 1) {
            return droite.getClass() == t.gauche.getClass();
        }
        if (index == 2) {
            return bas.getClass() == t.haut.getClass();
        }
        if (index == 3) {
            return gauche.getClass() == t.droite.getClass();
        } else {
            System.out.println("Erreur TuileCarcassonne.estCompatible : Position non conforme !");
            return false;
        }
    }

    public void tourneTuile() { // Tourne la tuile, dans le sens des aiguis d'une montre
        Lieu temp = haut;
        haut = gauche;
        gauche = bas;
        bas = droite;
        droite = temp;
    }

    public String toString() { // Affichage textuel
        return "haut " + haut.toString() + "\nbas " + bas.toString() + "\ngauche " + gauche.toString() + "\ndroit "
                + droite.toString();
    }

    private String calculId() {
        String s = "";

        // Code du côté haut
        if (haut instanceof Abbaye)
            s += "1";
        if (haut instanceof Carrefour)
            s += "2";
        if (haut instanceof Chemin)
            s += "3";
        if (haut instanceof Pre)
            s += "4";
        if (haut instanceof QuartierVille)
            s += "5";

        // Code du côté droit
        if (droite instanceof Abbaye)
            s += "1";
        if (droite instanceof Carrefour)
            s += "2";
        if (droite instanceof Chemin)
            s += "3";
        if (droite instanceof Pre)
            s += "4";
        if (droite instanceof QuartierVille)
            s += "5";

        // Code du côté bas
        if (bas instanceof Abbaye)
            s += "1";
        if (bas instanceof Carrefour)
            s += "2";
        if (bas instanceof Chemin)
            s += "3";
        if (bas instanceof Pre)
            s += "4";
        if (bas instanceof QuartierVille)
            s += "5";

        // Code du côté gauche
        if (gauche instanceof Abbaye)
            s += "1";
        if (gauche instanceof Carrefour)
            s += "2";
        if (gauche instanceof Chemin)
            s += "3";
        if (gauche instanceof Pre)
            s += "4";
        if (gauche instanceof QuartierVille)
            s += "5";

        // Code du centre
        if (centre instanceof Abbaye)
            s += "1";
        if (centre instanceof Carrefour)
            s += "2";
        if (centre instanceof Chemin)
            s += "3";
        if (centre instanceof Pre)
            s += "4";
        if (centre instanceof QuartierVille)
            s += "5";

        // Code si symbole présent
        if (symbole)
            s += "0";

        if (separator)
            s+="7";
        return s;
    }
}