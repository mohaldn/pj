package src.utils;

import java.util.Random;

import src.gameobject.*;
import src.gameobject.ObjCarcassonne.*;
import src.gameobject.ObjDominos.TuileDominos;

public class Generateur { // Generateur de tuiles spécial :
    // Assure que chaque tuile générée possède au moins une solution pour la tuile
    // générée précédemment
    public static int[] temp = new int[3]; // On sauvegarde ici un côté de la tuile précédente
    public static boolean first = true; // Le cas de la première tuile est différent

    public static TuileDominos genTuileDominos() {
        Random random = new Random();
        int nb = 0;
        TuileDominos t = new TuileDominos(new int[3], new int[3], new int[3], new int[3]); // La tuile qu'on va générer
        if (first) { // Si c'est la première, elle est parfaitement aléatoire
            for (int i = 0; i < 12; i++) {
                nb = random.nextInt(9);
                if (i < 3) {
                    t.getDroite().getTrio()[i] = nb;
                    temp[i] = nb; // On sauvegarde la côté droit de cette première tuile
                }
                if (i < 6 && i >= 3)
                    t.getBas().getTrio()[i - 3] = nb;
                if (i < 9 && i >= 6)
                    t.getGauche().getTrio()[i - 6] = nb;
                if (i < 12 && i >= 9)
                    t.getHaut().getTrio()[i - 9] = nb;
            }
            first = false;
        } else { // Si ce n'est pas la première, elle va dépendre de la précédente
            for (int i = 0; i < 12; i++) {
                nb = random.nextInt(9);
                if (i < 3) {
                    t.getDroite().getTrio()[2 - i] = temp[i]; // Le côté droit est identique au côté droit de la
                                                              // précédente
                    temp[i] = nb; // On sauvegarde un tableau aléatoire
                }
                if (i < 6 && i >= 3)
                    t.getBas().getTrio()[5 - i] = temp[i - 3]; // Le côté bas est identique au côté bas de la précédente
                if (i < 9 && i >= 6)
                    t.getGauche().getTrio()[i - 6] = nb;
                if (i < 12 && i >= 9)
                    t.getHaut().getTrio()[i - 9] = nb;
            }
        }
        return t; // On renvoie la tuile
    }

    public static Tuile[] genPiocheCarcasssonne() { // Création manuelle de toutes les tuiles pour carcassonne
        Tuile[] pioche = new Tuile[72];
        for (int i = 0; i < 9; i++) {
            pioche[i] = new TuileCarcassonne(new Pre(), new Pre(), new Chemin(), new Chemin(), new Chemin(), false,
                    false);
        }
        for (int i = 9; i < 12; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Chemin(), new Chemin(), new Pre(),
                    new Chemin(), false, false);
        }
        for (int i = 12; i < 14; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Chemin(), new Chemin(),
                    new QuartierVille(), new Chemin(), true, true);
        }
        pioche[14] = new TuileCarcassonne(new QuartierVille(), new QuartierVille(),
                new Chemin(), new QuartierVille(), new QuartierVille(), false, false);
        pioche[15] = new TuileCarcassonne(new QuartierVille(), new QuartierVille(),
                new Pre(), new QuartierVille(), new QuartierVille(), true, false);
        for (int i = 16; i < 19; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Chemin(), new Chemin(),
                    new QuartierVille(), new Chemin(), false, true);
        }
        for (int i = 19; i < 22; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Chemin(), new Chemin(), new Chemin(),
                    new Carrefour(), false, false);
        }
        for (int i = 22; i < 30; i++) {
            pioche[i] = new TuileCarcassonne(new Chemin(), new Pre(), new Chemin(), new Pre(), new Chemin(), false,
                    false);
        }
        for (int i = 30; i < 34; i++) {
            pioche[i] = new TuileCarcassonne(new Pre(), new Chemin(), new Chemin(), new Chemin(), new Carrefour(),
                    false, false);
        }
        for (int i = 34; i < 39; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Pre(), new Pre(), new Pre(), new Pre(),
                    false, false);
        }
        for (int i = 39; i < 41; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new QuartierVille(), new Pre(), new Pre(),
                    new Pre(), false, false);
        }
        for (int i = 41; i < 44; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new QuartierVille(),
                    new Pre(), new QuartierVille(), new QuartierVille(), false, false);
        }
        for (int i = 44; i < 48; i++) {
            pioche[i] = new TuileCarcassonne(new Pre(), new Pre(), new Pre(), new Pre(), new Abbaye(), false, false);
        }
        for (int i = 48; i < 50; i++) {
            pioche[i] = new TuileCarcassonne(new Pre(), new Pre(), new Chemin(), new Pre(), new Abbaye(), false, false);
        }
        for (int i = 50; i < 53; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Pre(), new Pre(), new QuartierVille(),
                    new Pre(), false, true);
        }
        for (int i = 53; i < 55; i++) {
            pioche[i] = new TuileCarcassonne(new Pre(), new QuartierVille(), new Pre(), new QuartierVille(),
                    new QuartierVille(), true, false);
        }
        for (int i = 55; i < 59; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Chemin(), new Pre(), new Chemin(),
                    new Chemin(), false, false);
        }
        for (int i = 59; i < 62; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Pre(), new Chemin(), new Chemin(),
                    new Pre(), false, false);
        }
        pioche[62] = new TuileCarcassonne(new Pre(), new QuartierVille(), new Pre(), new QuartierVille(),
                new QuartierVille(), false, false);
        pioche[63] = new TuileCarcassonne(new QuartierVille(), new QuartierVille(), new QuartierVille(),
                new QuartierVille(), new QuartierVille(), true, false);
        for (int i = 64; i < 66; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new QuartierVille(),
                    new Chemin(), new QuartierVille(), new QuartierVille(), true, false);
        }
        pioche[66] = new TuileCarcassonne(new Chemin(), new Chemin(), new Chemin(), new Chemin(), new Carrefour(),
                false, false);
        for (int i = 67; i < 69; i++) {
            pioche[i] = new TuileCarcassonne(new QuartierVille(), new Pre(), new Pre(), new QuartierVille(),
                    new Pre(), false, true);
        }
        for (int i = 69; i < 72; i++) {
            pioche[i] = new TuileCarcassonne(new Pre(), new QuartierVille(), new Pre(), new QuartierVille(),
                    new Pre(), false, false);
        }
        return pioche;
    }

    /*
     * public static void main(String[] args) {
     * System.out.println("Tuile A");
     * System.out.println(genTuileDominos());
     * System.out.println("Tuile B");
     * System.out.println(genTuileDominos());
     * System.out.println("Tuile C");
     * System.out.println(genTuileDominos());
     * System.out.println("Tuile D");
     * TuileDominos tuile = genTuileDominos();
     * System.out.println(tuile);
     * System.out.println("Tourne Tuile D - 1");
     * tuile.tourneTuile();
     * System.out.println("Tourne Tuile D - 2");
     * tuile.tourneTuile();
     * System.out.println("Tourne Tuile D - 3");
     * tuile.tourneTuile();
     * System.out.println(tuile);
     * }
     */
}