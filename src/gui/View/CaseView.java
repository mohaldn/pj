package src.gui.View;

//Import de package
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

//Représente une case (d'une tuile de Carcassonne ou d'un plateau vide)
public class CaseView extends JLabel { // Une Case est un JLabel

    /// Constructeurs
    public CaseView() { // Constructeur d'une case vide
        this.setText(""); // La valeur de la case
        Border bordera = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(bordera); // Chaque case possède des bordeu de 1, en noir, sur chaque côté de la case
    }

    public CaseView(String val, Color c) { // Constructeur avec valeur
        this.setText(val); // La valeur de la case
        this.setForeground(Color.BLACK);
        this.setHorizontalAlignment(JLabel.CENTER); // Le texte est centré par rapport à la case
        this.setPreferredSize(new Dimension(25, 25)); // Avec une taille prédéfinie (carré)
        this.setBackground(c); // On choisi la couleur de fond
        this.setOpaque(true); // Et on rend cette couleur visible
        Border bordera = BorderFactory.createLineBorder(Color.BLACK, 1);
        setBorder(bordera); // Chaque case possède des bordures de 1, en noir, sur chaque côté
    }
}