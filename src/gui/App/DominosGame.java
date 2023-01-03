package src.gui.App;

//Import de package
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

//Import des autres dossiers
import src.gameobject.Joueur;
import src.gameobject.Plateau;
import src.gameobject.ObjDominos.TuileDominos;
import src.gui.View.PlateauView;
import src.gui.View.TuileDominosView;

//Représente une partie de Dominos
public class DominosGame extends JFrame { // Cette partie se lance dans une fenêtre
    Plateau plateau; // Le pateau de jeu
    Joueur[] Liste_Joueurs; // La liste des joueurs
    PlateauView plateauView; // La vue du plateau
    JPanel panelPlateauView, panelDroit, panelGauche; // Des panels utiles
    JButton buttonTourner, buttonDefausser, buttonPiocher, buttonStop, buttonRetourMenu; // Des bouttons utiles
    JLabel msgStatutView, msgJoueurView; // Des messages de statut
    TuileDominosView tuilePioche; // La tuile actuellement piochée
    int joueurDuTour; // Index du joueur actuel

    /// Constructeurs
    public DominosGame(Joueur[] lj, int nbDeTuile) { // Constructeur avec une liste de joueurs
        // Création de la fenêtre
        super("Dominos"); // Création de la fenêtre avec son titre
        // Définition de l'opération lorsqu'on ferme la fenêtre
        // Ici, revient à exécuter "System.exite(0)" (ferme toutes les fenêtres)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700); // Définition de la taille part défaut
        // Positionne la fenêre au centre du conteneur null
        // (la fenêtre de l'ordinateur, le bureau en quelques sortes)
        this.setLocationRelativeTo(null);

        // Création du conteneur principal
        JPanel win = (JPanel) this.getContentPane();
        // BorderLayout, pour placer des élements à des endroits précis
        win.setLayout(new BorderLayout());

        // Creation du plateau
        plateau = new Plateau(nbDeTuile, false); // Le nombre de tuiles peut être variable
        panelPlateauView = new PlateauView(plateau);
        plateauView = (PlateauView) panelPlateauView;

        // Définition des variables
        Liste_Joueurs = lj;
        joueurDuTour = 0;

        // Ajout des élements à la win
        win.add(createPanelDroit(), BorderLayout.EAST);
        win.add(createPanelGauche(), BorderLayout.WEST);
        win.add(panelPlateauView);

        // Activation des différents gestionnaires d'évenements
        piocherView();
        defausserView();
        tournerView();
        stopAndMenuView();
    }

    /// Méthodes de la classe DominosGame

    /// Création de différents panel pour le jeu
    private JPanel createPanelDroit() { // Crée le panel de droite
        // Initalisation du panel
        panelDroit = new JPanel(new FlowLayout());
        panelDroit.setPreferredSize(new Dimension(120, 160));

        // Initialisation des élements
        buttonTourner = new JButton("Tourner");
        buttonTourner.setPreferredSize(new Dimension(85, 60));
        buttonTourner.setVisible(false); // Pas visible par défaut
        buttonDefausser = new JButton("Défausser");
        buttonDefausser.setPreferredSize(new Dimension(85, 60));
        buttonDefausser.setVisible(false);
        buttonPiocher = new JButton("Piocher");
        buttonPiocher.setPreferredSize(new Dimension(85, 60));
        // Pas besoin de set buttonPiocher visible, c'est déjà fait par défaut
        msgStatutView = new JLabel(plateau.getMsgStatut(), JLabel.CENTER);
        msgStatutView.setPreferredSize(new Dimension(120, 60));
        tuilePioche = new TuileDominosView();
        tuilePioche.setPreferredSize(new Dimension(120, 120));
        tuilePioche.setVisible(false);

        // AJout des élements dans le panel
        panelDroit.add(buttonTourner);
        panelDroit.add(buttonDefausser);
        panelDroit.add(buttonPiocher);
        panelDroit.add(tuilePioche);
        panelDroit.add(msgStatutView);
        return panelDroit;
    }

    private JPanel createPanelGauche() { // Crée le panel de gauche
        // Initalisation du panel
        panelGauche = new JPanel(new FlowLayout());
        panelGauche.setPreferredSize(new Dimension(160, 60));

        // Initialisation des élements
        buttonStop = new JButton("Arrêter la partie");
        buttonStop.setPreferredSize(new Dimension(120, 60));
        buttonStop.setForeground(Color.RED);
        buttonRetourMenu = new JButton("Menu principal");
        buttonRetourMenu.setPreferredSize(new Dimension(120, 60));
        buttonRetourMenu.setForeground(Color.BLUE);
        msgJoueurView = new JLabel("Tour du joueur " + Liste_Joueurs[0].getNom(), JLabel.CENTER);
        msgStatutView.setPreferredSize(new Dimension(120, 60));

        // AJout des élements dans le panel
        panelGauche.add(buttonStop);
        panelGauche.add(buttonRetourMenu);
        panelGauche.add(msgJoueurView);
        return panelGauche;
    }

    /// Evenements du jeu
    private void piocherView() { // Gestion du bouton piocher
        buttonPiocher.addActionListener(e -> { // Lorsque le bouton est cliqué
            if (plateau.resteTuiles()) { // S'il reste des tuile
                Joueur j = Liste_Joueurs[joueurDuTour]; // On va vérifier quel type de joueur pioche
                if (j.isBot()) { // Si c'est un bot, on lui fait jouer son tour
                    tourDuBot();
                } else { // Sinon
                    panelDroit.remove(tuilePioche); // On supprime la pioche actuelle...
                    tuilePioche = new TuileDominosView((TuileDominos) plateau.piocher());
                    tuilePioche.setPreferredSize(new Dimension(80, 80));
                    panelDroit.add(tuilePioche, 3); // ... et on la met à jour
                    msgStatutView.setText(plateau.getMsgStatut()); // On met à jour le message de statut
                    buttonTourner.setVisible(true); // On active les boutons tourner
                    buttonDefausser.setVisible(true); // et défausser
                    buttonPiocher.setVisible(false); // On empêche de piocher à nouveau
                    appliqueEffectForAll(); // Puis on applique des effets au cases
                }
            } else { // Si on ne peut plus piocher (il ne reste plus de tuiles)
                // C'est la fin de la partie
                // On met à jour les messages de statut
                msgStatutView.setText("Fin de la partie !");
                msgJoueurView.setText("Bravo à tous !");
            }
        });
    }

    private void defausserView() { // Gestion du bouton défausser
        buttonDefausser.addActionListener(e -> { // Si on clique sur le boutton défausser
            // On empêche le placement d'une tuile ou l'affichage du selecteur
            // Cela revient à désactiver les mouselistner pour toutes les cases
            unAppliqueEffectForAll();
            plateau.defausser(tuilePioche.getTuile()); // On défause la tuile
            msgStatutView.setText(plateau.getMsgStatut()); // On met à jour le message de statut
            // On met l'affichage en mode début de tour
            tuilePioche.setVisible(false);
            buttonDefausser.setVisible(false);
            buttonTourner.setVisible(false);
            buttonPiocher.setVisible(true);
            unAppliqueEffectForAll();
            finDeTour(); // On lance le gesionnaire de fin de tour
        });
    }

    private void tournerView() { // Gestion du boutton tourner
        buttonTourner.addActionListener(e -> { // Si on clique sur le boutton tourner
            panelDroit.remove(3); // On supprime la tuile en main affichée
            TuileDominos tt = tuilePioche.getTuile();
            tt.tourneTuile(); // On tourne la tuile
            tuilePioche = new TuileDominosView(tt); // La tuile en pioche devient la même mais tournée
            tuilePioche.setPreferredSize(new Dimension(80, 80));
            panelDroit.add(tuilePioche, 3);
            msgStatutView.setText("Tuile Tournée"); // On met à jour le message de statut
            // On affiche tout parfaitement
            buttonDefausser.setVisible(false);
            buttonTourner.setVisible(false);
            buttonPiocher.setVisible(true);
            buttonDefausser.setVisible(true);
            buttonTourner.setVisible(true);
            buttonPiocher.setVisible(false);
        });
    }

    private void placerView(int index) { // Gestion du placement
        JComponent c = (JComponent) panelPlateauView.getComponent(index);
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // Si on clique sur une case du plateau (vue)
                TuileDominos tuileAPlacer = tuilePioche.getTuile(); // La tuile à placer
                // Coordonnées x,y sélectionnées sur le plateauView
                int xv = plateauView.getXView(index);
                int yv = plateauView.getYView(index);
                // Coordonnées x,y où placer la tuile sur le Plateau
                int xpl = xv + plateauView.getXmin();
                int ypl = yv + plateauView.getYmin();

                Boolean placementOk = plateau.placer(tuileAPlacer, xpl, ypl); // On essaye de placer la tuile

                if (placementOk) {// Si on peut placer la tuile :
                    plateauView.update(xpl, ypl, plateau); // On met à jour le plateau affiché
                    unAppliqueEffectForAll(); // On empêche un nouveau placement
                    // On met l'affichage en mode début de tour
                    tuilePioche.setVisible(false);
                    buttonDefausser.setVisible(false);
                    buttonTourner.setVisible(false);
                    buttonPiocher.setVisible(true);
                    unAppliqueEffectForAll();
                    msgStatutView.setText(plateau.getMsgStatut()); // On met à jour le message de statut
                    finDeTour(); // On lance le gestionnaire de fin de tour
                } else {
                    // Sinon, si on ne peut placer la tuile
                    msgStatutView.setText(plateau.getMsgStatut()); // On met à jour le message de statut
                }
            }
        });
    }

    private void stopAndMenuView() { // Gestion des boutons d'arrêt de la partie et de retour au menu
        buttonStop.addActionListener(e -> { // Si on clique sur le bouton arrêter
            // On ouvre une boîte de Dialog qui explique à l'utilisateur son action
            int clickbtn = JOptionPane.showConfirmDialog(DominosGame.this,
                    "Êtes-vous sûr de vouloir abandonner ?\nAbandonner terminera la partie pour tous les joueurs !",
                    "Abandon et fermture de la fenêtre.", JOptionPane.YES_NO_OPTION);

            if (clickbtn == JOptionPane.YES_OPTION) { // S'il valide l'action, on effectue un JFrame.EXIT_ON_CLOSE
                // Cela ferme toutes les fenêtres ouvertes par le programme
                // Et termine le programme
                System.exit(0);
            }
        });

        buttonRetourMenu.addActionListener(e -> { // Si on clique sur le bouton Menu Principal
            // On ouvre une boîte de Dialog qui explique à l'utilisateur son action
            int clickbtn = JOptionPane.showConfirmDialog(DominosGame.this,
                    "Vous êtes sur le point de retourner au menu principal !",
                    "Retour au menu principal et nouvelle partie.", JOptionPane.YES_NO_OPTION);
            if (clickbtn == JOptionPane.YES_OPTION) { // S'il valide l'action, on effectue un JFrame.DISPOSE_ON_CLOSE
                // Cela ferme uniquement la fenêtre actuelle sans terminer le programme
                this.dispose();
                // Puis on ouvre un nouveau menu
                JFrame l = new Lancement();
                l.setVisible(true);
            }
        });
    }

    private void afficheSelection(int index) { // Affiche un sélecteur
        // Permet de savoir quelle case est survolée par la souris

        JComponent c = (JComponent) panelPlateauView.getComponent(index);

        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { // Si on passe la souris par-dessus la case
                Joueur j = Liste_Joueurs[joueurDuTour];
                Border bordera = BorderFactory.createLineBorder(j.getCouleur(), 3); // On change la couleur du bord
                c.setBorder(bordera);
            }

            @Override
            public void mouseExited(MouseEvent e) { // Si on quitte la case, on remet la valeur
                Border bordera = BorderFactory.createLineBorder(Color.BLACK, 1);
                c.setBorder(bordera);
            }
        });
    }

    private boolean appliqueEffectForAll() { // Applique tous les évenements à toutes les cases
        for (int i = 0; i < plateauView.getLigne() * plateauView.getColone(); i++) {
            // Coordonnées x,y sélectionnées sur le plateauView
            int xv = plateauView.getXView(i);
            int yv = plateauView.getYView(i);
            // Coordonnées x,y équivalentes sur le Plateau
            int xpl = xv + plateauView.getXmin();
            int ypl = yv + plateauView.getYmin();

            if (plateau.getPlateauActuel()[xpl][ypl] != null) { // S'il y a déjà une tuile, on désactive les effets
                unAppliqueEffect(i);
            } else { // Sinon on active les effets sur la case
                afficheSelection(i);
                placerView(i);
            }
        }
        return true;
    }

    private void unAppliqueEffect(int index) { // Désactive tous les effets sur une case
        JComponent c = (JComponent) panelPlateauView.getComponent(index);
        for (int j = 0; j < c.getMouseListeners().length; j++) {
            c.removeMouseListener(c.getMouseListeners()[0]);
        }
    }

    private boolean unAppliqueEffectForAll() { // Désactive tous les évenements à toutes les cases
        // C'est-à-dire, empêche qu'une case cliquée active le placement de tuile
        // tant qu'on a pas pioché
        for (int i = 0; i < plateauView.getLigne() * plateauView.getColone(); i++) {
            unAppliqueEffect(i);
        }
        return true;
    }

    // Gestion des tours du jeu
    private boolean finDeTour() { // Gestion de la fin d'un tour
        // On met à jour le joueur
        if (joueurDuTour == Liste_Joueurs.length - 1) {
            joueurDuTour = 0;
        } else {
            joueurDuTour++;
        }
        // On met à jour le message du Joueur
        msgJoueurView.setText("Tour du joueur " + Liste_Joueurs[joueurDuTour].getNom());
        Joueur j = Liste_Joueurs[joueurDuTour];
        // Si le joueur suivant est un bot, on lui fait jouer son tour
        if (j.isBot()) {
            tourDuBot();
        }
        return true;
    }

    private boolean tourDuBot() { // Gestion du tour d'un bot
        // Le bot va piocher une tuile
        if (plateau.resteTuiles()) {
            panelDroit.remove(tuilePioche); // On supprime la pioche actuelle...
            tuilePioche = new TuileDominosView((TuileDominos) plateau.piocher());
            tuilePioche.setPreferredSize(new Dimension(80, 80));
            panelDroit.add(tuilePioche, 3); // ... et on la met à jour
            msgStatutView.setText(plateau.getMsgStatut());
            buttonTourner.setVisible(true);
            buttonDefausser.setVisible(true);
            buttonPiocher.setVisible(false);
            appliqueEffectForAll();
        }
        // Puis il va automatiquement défausser cette tuile
        unAppliqueEffectForAll();
        plateau.defausser(tuilePioche.getTuile());
        msgStatutView.setText(plateau.getMsgStatut());
        tuilePioche.setVisible(false);
        buttonDefausser.setVisible(false);
        buttonTourner.setVisible(false);
        buttonPiocher.setVisible(true);
        unAppliqueEffectForAll();
        finDeTour();
        return true;
    }
}