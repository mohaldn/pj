package src.gui.View;

import javax.imageio.ImageIO;
//Import de packages
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Import des autres dossiers
import src.gameobject.ObjCarcassonne.*;

//Représente la vue d'une tuile de Carcassonne
public class TuileCarcassonneView extends JPanel { // Une vue de Tuile Carcassonne est un JPanel
    BufferedImage bi;
    TuileCarcassonne tuile; // La tuile représentée
    String id;
    static String chemin = (new File("src/gui/photos/")).getAbsolutePath();

    /// Constructeurs
    public TuileCarcassonneView() { // Constructeur d'une tuile vide
        id = "88888";
        try {
            bi = ImageIO.read(new File(chemin + "/" + id + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TuileCarcassonneView(TuileCarcassonne tpl) { // Constructeur d'une tuile
        tuile = tpl;
        id = tuile.getId();
        try {
            bi = ImageIO.read(new File(chemin + "/" + id + ".png"));
            System.out.println("J'ai réussi à lire l'image " + id);
        } catch (IOException e) {
            System.out.println("Impossible de lire l'image " + id);
            e.printStackTrace();
        }
    }

    /// Getter et Setter
    public BufferedImage getBi() {
        return bi;
    }

    public TuileCarcassonne getTuile() {
        return tuile;
    }

    public void setBi(BufferedImage bi) {
        this.bi = bi;
    }

    public void setTuile(TuileCarcassonne tuile) {
        this.tuile = tuile;
    }

    /// Méthodes de la classe Tuile Carcassonne View
    public void paintComponent(Graphics g) {
        g.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}