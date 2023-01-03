package src.gui.App;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.nimbus.*;

import src.gameobject.Joueur;

public class Lancement extends JFrame {
    public boolean isCarcassonne; // false si Dominos, true si Carcassonne
    JPanel menu, menu1, menu2, menu3;
    JButton buttonDominos, buttonCarcassonne, button2Joueurs, button3Joueurs, button4Joueurs, buttonLancerPartie;
    int nbJoueur, nbTuile;
    Joueur[] listeDesJoueurs;

    public Lancement() {
        // Création de la fenêtre
        super("Menu"); // Création de la fenêtre avec son titre
        // Définition de l'opération lorsqu'on ferme la fenêtre
        // Ici, revient à exécuter "this.dispose()" (ne ferme que la fenêtre actuelle )
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 700); // Définition de la taille part défaut
        // Positionne la fenêre au centre du conteneur null
        // (la fenêtre de l'ordinateur, le bureau en quelques sortes)
        this.setLocationRelativeTo(null);

        this.setLayout(new FlowLayout());

        // Création des menus
        menu = new JPanel(new GridLayout(4, 1));
        menu1 = createMenu1();
        menu2 = createMenu2();
        menu2.setVisible(false);
        buttonLancerPartie = new JButton("Lancer la partie ! ");
        buttonLancerPartie.setPreferredSize(new Dimension(85, 60));
        // buttonNbTuile28 = new JButton("28 tuiles");

        // Ajout des élements au menu :
        menu.add(menu1);
        menu.add(menu2);

        // Gestion d'évenement
        choixJeu();
        choixNombreJoueurs();
        choixVariables();

        // Ajout du menu à la fenêtre
        this.add(menu);
    }

    public JPanel createMenu1() {
        // Initalisation du panel
        JPanel m1 = new JPanel();
        m1.setLayout(new GridLayout(2, 1));
        m1.setPreferredSize(new Dimension(60, 60));

        // Initialisation des élements
        buttonDominos = new JButton("Joueur au Dominos !");
        buttonDominos.setPreferredSize(new Dimension(60, 60));
        buttonCarcassonne = new JButton("Joueur au Carcassonne !");
        buttonCarcassonne.setPreferredSize(new Dimension(60, 60));

        // AJout des élements dans le panel
        m1.add(buttonDominos);
        m1.add(buttonCarcassonne);

        return m1;
    }

    public JPanel createMenu2() {
        // Initalisation du panel
        JPanel m2 = new JPanel();
        m2.setLayout(new GridLayout(1, 3));
        m2.setPreferredSize(new Dimension(300, 70));

        // Initialisation des élements
        button2Joueurs = new JButton("2 joueurs");
        button2Joueurs.setPreferredSize(new Dimension(100, 60));
        button3Joueurs = new JButton("3 joueurs");
        button3Joueurs.setPreferredSize(new Dimension(100, 60));
        button4Joueurs = new JButton("4 joueurs");
        button4Joueurs.setPreferredSize(new Dimension(100, 60));

        // AJout des élements dans le panel
        m2.add(button2Joueurs);
        m2.add(button3Joueurs);
        m2.add(button4Joueurs);

        return m2;
    }

    public JPanel createMenu3(int nbJ) {
        // Initalisation du panel
        JPanel m3 = new JPanel();
        m3.setLayout(new GridLayout(nbJ, 3));
        m3.setPreferredSize(new Dimension(140, 100));

        for (int i = 0; i < nbJ; i++) {
            JLabel jlb = new JLabel((i + 1) + ". Joueur " + Joueur.ListNom[i]);
            jlb.setHorizontalAlignment(JLabel.CENTER);
            JTextArea jtxta = new JTextArea("Entrer le Nom");
            jtxta.setPreferredSize(new Dimension(45, 100));
            JCheckBox jcb = new JCheckBox("Bot ?");
            jcb.setPreferredSize(new Dimension(45, 100));
            jcb.setHorizontalAlignment(JCheckBox.CENTER);
            m3.add(jlb);
            m3.add(jtxta);
            m3.add(jcb);
        }

        Border bordera = BorderFactory.createLineBorder(Color.BLACK, 3);
        m3.setBorder(bordera);

        return m3;
    }

    private void choixJeu() {
        buttonDominos.addActionListener(e -> {
            // Si on choisi un jeu, on ne peut plus cliquer sur les bouttons de choix
            menu1.setVisible(false);
            menu2.setVisible(true);
            isCarcassonne = false;
        });

        buttonCarcassonne.addActionListener(e -> {
            // Si on choisi un jeu, on ne peut plus cliquer sur les bouttons de choix
            menu1.setVisible(false);
            menu2.setVisible(true);
            isCarcassonne = true;
        });
    }

    private void choixNombreJoueurs() {
        button2Joueurs.addActionListener(e -> {
            menu2.setVisible(false);
            nbJoueur = 2;
            menu3 = createMenu3(nbJoueur);
            buttonLancerPartie.setPreferredSize(new Dimension(60, 60));
            menu.add(menu3);
            menu.add(buttonLancerPartie);
        });

        button3Joueurs.addActionListener(e -> {
            menu2.setVisible(false);
            nbJoueur = 3;
            menu3 = createMenu3(nbJoueur);
            buttonLancerPartie.setPreferredSize(new Dimension(60, 60));
            menu.add(menu3);
            menu.add(buttonLancerPartie);
        });

        button4Joueurs.addActionListener(e -> {
            menu2.setVisible(false);
            nbJoueur = 4;
            menu3 = createMenu3(nbJoueur);
            buttonLancerPartie.setPreferredSize(new Dimension(60, 60));
            menu.add(menu3);
            menu.add(buttonLancerPartie);
        });
    }

    private void choixVariables() {
        buttonLancerPartie.addActionListener(e -> {
            Joueur[] listJ = new Joueur[nbJoueur];
            int cntjcb = 2;
            for (int i = 0; i < nbJoueur; i++) {
                JCheckBox jcbtemp = (JCheckBox) menu3.getComponent(cntjcb);
                JTextArea jtxt = (JTextArea) menu3.getComponent(cntjcb - 1);
                String lenom;
                if (jtxt.getText().equals("Entrer le Nom")) {
                    lenom = Joueur.ListNom[i];
                } else {
                    lenom = jtxt.getText();
                }
                listJ[i] = new Joueur(jcbtemp.isSelected(), lenom, Joueur.ListColor[i]);
                cntjcb = cntjcb + 3;
            }
            if (isCarcassonne) {
                JFrame cg = new CarcassonneGame(listJ);
                cg.setVisible(true);
                this.dispose();
            } else {
                JFrame dg = new DominosGame(listJ, 25);
                dg.setVisible(true);
                this.dispose();
            }
        });
    }

    public static void main(String[] args) {
        // Appliquer un look'n feel
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        // Démarrer la fenêtre.
        Lancement mw = new Lancement();
        mw.setVisible(true);
    }
}
