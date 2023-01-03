package src.gui.View;

//Import de packages
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

//Import des autres dossiers
import src.gameobject.ObjDominos.*;

//Représente la vue d'une tuile de Dominos
public class TuileDominosView extends JPanel { // Une vue de Tuile Dominos est un JPanel
    Color couleurClair = new Color(241, 193, 15); // Couleur cases pleines
    Color couleurFoncee = new Color(241, 159, 15); // Couleur cases
    // Color couleurClair = Color.BLACK; // Couleur cases pleines
    // Color couleurFoncee = Color.BLACK; // Couleur cases

    TuileDominos tuile; // La tuile représentée

    /// Constructeurs
    public TuileDominosView() { // Constructeur d'une tuile vide
        this.setLayout(new GridLayout(5, 5)); // Une tuile Dominos est une grille de 5 x 5
        for (int i = 0; i < 25; i++) { // Pour chaque élement de la tuile
            add(new CaseView("", couleurFoncee)); // On crée une case foncée vide
        }
    }

    public TuileDominosView(TuileDominos t) { // Constructeur d'une tuile
        this.tuile = t;
        this.setLayout(new GridLayout(5, 5)); // Une tuile Dominos est une grille de 5 x 5

        // Ajouter les cases à la tuile :
        // Les tuiles s'ajoutent les unes à la suite des autres, en ligne
        for (int i = 0; i < 25; i++) {
            // Ces index sont des index de cases vides (en foncée)
            if (i == 0 || i == 4 || i == 6 || i == 7 || i == 8 || i == 11 || i == 12 || i == 13 || i == 16 || i == 17
                    || i == 18 || i == 20 || i == 24)
                add(new CaseView("", couleurFoncee));
            if (i >= 1 && i <= 3)
                add(new CaseView("" + tuile.getHaut().getTrio()[i - 1], couleurClair));
            if (i >= 21 && i <= 23)
                add(new CaseView("" + tuile.getBas().getTrio()[i - 21], couleurClair));
            if (i == 5 || i == 10 || i == 15) {
                add(new CaseView("" + tuile.getGauche().getTrio()[(i / 5) - 1], couleurClair));
            }
            if (i == 9 || i == 14 || i == 19) {
                add(new CaseView("" + tuile.getDroite().getTrio()[((i - 4) / 5) - 1], couleurClair));
            }
        }

        Border bordera = BorderFactory.createLineBorder(Color.BLACK, 1);
        this.setBorder(bordera); // Chaque tuile possède des bordures de 1, en noir, sur chaque côté
    }

    /// Getter et Setter
    public TuileDominos getTuile() {
        return tuile;
    }

    public void setTuile(TuileDominos tuile) {
        this.tuile = tuile;
    }
}