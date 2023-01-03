package src.utils;
import java.util.Scanner;

import src.gameobject.Joueur;
import src.gameobject.Plateau;

public class Lanceur {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Nombre de joueur:");
        int nbJoueur = myObj.nextInt();
        Joueur[] joueurs = new Joueur[nbJoueur];
        for (int i = 0; i < nbJoueur; i++) {
            System.out.println("Joueur(1) ou IA(0) :");
            int bot = myObj.nextInt();
            if (bot == 1) {
                joueurs[i] = new Joueur(false, null, null);
            } else {
                joueurs[i] = new Joueur(true, null, null);
            }
        }
        Plateau p = new Plateau(nbJoueur * 4, false);
        while (p.resteTuiles()) {
            for (int i = 0; i < nbJoueur; i++) {
                if (!joueurs[i].isBot()) {
                    joueurs[i].piocher(p);
                    System.out.println(joueurs[i].getTuileCourante());
                    System.out.println("Jouer(j) ou défausser(d):");
                    String prochainCoup = myObj.nextLine();
                    if (prochainCoup.equals("d")) {
                        p.defausser(joueurs[i].getTuileCourante());
                    }
                    if (prochainCoup.equals("j")) {
                        System.out.println("Nombre de joueur:");
                    }
                }
            }
        }

        // Numerical input
    }
}