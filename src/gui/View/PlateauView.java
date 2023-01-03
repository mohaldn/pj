package src.gui.View;

//Import de package
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import src.gameobject.Joueur;
//Import des autres dossiers
import src.gameobject.Plateau;
import src.gameobject.Tuile;
import src.gameobject.ObjCarcassonne.TuileCarcassonne;
import src.gameobject.ObjDominos.TuileDominos;
import src.gui.View.TuileCarcassonneView;

//Représente la vue du Plateau
public class PlateauView extends JPanel { // Une vue de plateau est un JPanel
    Plateau plateau; // Le plateau représenté
    // Ligne et colone sont le nombre de lignes et de colones du plateau affiché
    // Les autres valeurs x,y sont les coordonnées qui délimitent l'espace affiché
    // En effet, nous n'affichons pas tout le plateau (cf. rapport)
    int xmin, ymin, xmax, ymax, ligne, colone;

    /// Constructeurs
    public PlateauView(Plateau p) { // //Constructeur d'un plateau
        this.plateau = p;
        // Dimensions de l'espace au départ
        this.xmin = plateau.getxCentrePlateau() - 1;
        this.ymin = plateau.getyCentrePlateau() - 1;
        this.xmax = plateau.getxCentrePlateau() + 1;
        this.ymax = plateau.getyCentrePlateau() + 1;
        this.ligne = xmax - xmin + 1;
        this.colone = ymax - ymin + 1;

        this.setLayout(new GridLayout(ligne, colone));
        for (int x = xmin; x <= xmax; x++) {
            for (int y = ymin; y <= ymax; y++) {
                add(new CaseView()); // On ajoute des cases vides
            }
        }
    }

    /// Getter et Setter
    public int getLigne() {
        return ligne;
    }

    public int getColone() {
        return colone;
    }

    public int getXmin() {
        return xmin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getYmin() {
        return ymin;
    }

    public int getYmax() {
        return ymax;
    }

    public int getXView(int index) { // Renvoie la coordonnée x sur le plateauView en fonction de l'index
        return ((index - (index % colone)) / colone);
    }

    public int getYView(int index) { // Renvoie la coordonnée y sur le plateauView en fonction de l'index
        return (index % colone);
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public void setColone(int colone) {
        this.colone = colone;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    /// Méthodes de la classe PlateauView
    public void update(int xpl, int ypl, Plateau pl) { // Met à jour la vue du plateau
        // Etape 1 : Acutalisation des variables
        if (xmin > xpl - 1)
            xmin = xpl - 1;
        if (ymin > ypl - 1)
            ymin = ypl - 1;
        if (xmax < xpl + 1)
            xmax = xpl + 1;
        if (ymax < ypl + 1)
            ymax = ypl + 1;

        this.plateau = pl;
        this.ligne = xmax - xmin + 1;
        this.colone = ymax - ymin + 1;

        /// Etape 2 : Affichage actualisée
        this.removeAll(); // On supprime tout
        this.setLayout(new GridLayout(ligne, colone)); // On crée une grille à partir des nouvelles variables

        for (int x = xmin; x <= xmax; x++) {
            for (int y = ymin; y <= ymax; y++) {
                Tuile[][] platAct = plateau.getPlateauActuel();
                if (platAct[x][y] == null) { // S'il n'y a pas de tuile, on ajoute une case vide
                    add(new CaseView());
                } else { // Sinon on ajoute la tuile
                    Tuile tpl = platAct[x][y];
                    if (tpl instanceof TuileDominos){
                        add(new TuileDominosView((TuileDominos) tpl));
                    }
                    else {
                        int pin = plateau.getPionplacé()[x][y];
                        // On crée une vue de Tuile Carcassonne
                        JPanel j = new TuileCarcassonneView((TuileCarcassonne)tpl);
                        if(pin != -1){
                            Border bordera = BorderFactory.createLineBorder(Joueur.ListColor[pin], 2);
                            j.setBorder(bordera);
                        }
                        add(j); // On ajoute l'image correspondante
                    }
                }
            }
        }
    }
}