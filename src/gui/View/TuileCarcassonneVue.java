package src.gui.View;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TuileCarcassonneVue extends JPanel {
    private BufferedImage bi;

    public TuileCarcassonneVue(String nomPh) {
        try {
            bi = ImageIO.read(new File("photos\\" + nomPh + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TuileCarcassonneVue() {
        String nomPh = "88888";
        try {
            bi = ImageIO.read(new File("C:\\Users\\kb9la\\Documents\\MEGAsync\\FAC\\Fac - Cours\\Programmation Orientée Objet\\Projet\\src\\gui\\photos\\" + nomPh + ".png"));
        } catch (IOException e) {
            System.out.print("Dezolay ");
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        g.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public BufferedImage getBi() {
        return bi;
    }

    public void setBi(BufferedImage bi) {
        this.bi = bi;
    }
}