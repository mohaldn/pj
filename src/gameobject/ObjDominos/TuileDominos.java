package src.gameobject.ObjDominos;

//Import des autres dossiers
import src.gameobject.Tuile;

//Représente une Tuile pour Dominos
public class TuileDominos extends Tuile { // Une tuileDominos est une tuile
    private Trio haut, bas, gauche, droite; // Une tuile est composée de 4 trio (un par côté)

    /// Constructeurs
    public TuileDominos(int[] h, int[] b, int[] g, int[] d) { // Constructeur avec 4 tableaux d'entier
        haut = new Trio(h);
        bas = new Trio(b);
        gauche = new Trio(g);
        droite = new Trio(d);
        gauche.reverse();
        bas.reverse();
    }

    public TuileDominos(Trio h, Trio b, Trio g, Trio d) { // Constructeur avec 4 Trio
        haut = h;
        bas = b;
        gauche = g;
        droite = d;
        gauche.reverse();
        bas.reverse();
    }

    // Getter et Setter
    public Trio getBas() {
        return bas;
    }

    public Trio getDroite() {
        return droite;
    }

    public Trio getGauche() {
        return gauche;
    }

    public Trio getHaut() {
        return haut;
    }

    public void setBas(Trio bas) {
        this.bas = bas;
    }

    public void setDroite(Trio droite) {
        this.droite = droite;
    }

    public void setGauche(Trio gauche) {
        this.gauche = gauche;
    }

    public void setHaut(Trio haut) {
        this.haut = haut;
    }

    /// Méthodes de la classe Tuile
    public boolean estComptabile(TuileDominos t, int index) { // Vérifie la compatibilité entre 2 tuiles Dominos
        // Si t est en haut de la tuile (index = 0),
        // on vérifie que le haut de la tuile est compatabile avec le bas de t
        if (index == 0) {
            return this.haut.estEgal(t.bas);
        }
        if (index == 1) {
            return this.droite.estEgal(t.gauche);
        }
        if (index == 2) {
            return this.bas.estEgal(t.haut);
        }
        if (index == 3) {
            return this.gauche.estEgal(t.droite);
        } else {
            System.out.println("Erreur TuileDominos.estCompatible : Position non conforme !");
            return false;
        }
    }

    public void tourneTuile() { // Tourne la tuile, dans le sens des aiguis d'une montre
        // On doit appliquer un "mirroir" (.reverse()) dans certains cas
        droite.reverse();
        gauche.reverse();
        int[] temp = droite.trio;
        droite = new Trio(haut.trio);
        haut = new Trio(gauche.trio);
        gauche = new Trio(bas.trio);
        bas = new Trio(temp);
    }

    public String toString() { // Affichage dans le terminal (pour le mode de jeux "Texte")
        String s = "| |";
        for (int i = 0; i < 3; i++) {
            s += haut.trio[i] + " ";
        }
        s += "| |\n";
        for (int i = 0; i < 3; i++) {
            s += "|" + gauche.trio[i] + "|      |" + droite.trio[i] + "| \n";
        }
        s += "| |";
        for (int i = 0; i < 3; i++) {
            s += bas.trio[i] + " ";
        }
        s += "| |\n";
        return s;
    }
}