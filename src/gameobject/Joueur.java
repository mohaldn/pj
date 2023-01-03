package src.gameobject;

//Import de package
import java.awt.*;

//Représente un joueur
public class Joueur {
    private Tuile tuileCourante; // La tuile en main du joueur
    int score; // Son score
    boolean bot; // True si le joueur est un bot, false sinon
    String nom; // Le nom du joueur
    Color couleur; // La couleur associée
    public static String[] ListNom = { "Bleu", "Rouge", "Vert", "Jaune" }; // Liste de noms par défaut
    public static Color[] ListColor = { Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW }; // Liste de couleurs
    int pion;

    /// Constructeurs
    public Joueur(boolean ia, String name, Color c) { // Constructeur de base
        tuileCourante = null;
        bot = ia;
        score = 0;
        nom = name;
        couleur = c;
        pion = 8;
    }

    /// Getter et Setter
    public int getPion() {
        return pion;
    }

    public Color getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public boolean isBot() {
        return bot;
    }

    public Tuile getTuileCourante() {
        return tuileCourante;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPion(int pion) {
        this.pion = pion;
    }

    public void setTuileCourante(Tuile tuileCourante) {
        this.tuileCourante = tuileCourante;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /// Méthodes de la classe Joueur
    public void piocher(Plateau p) { // Fait piocher le joueur
        tuileCourante = p.piocher();
    }

    public boolean placerTuiles(Plateau p, int x, int y) { // Fait placer sa tuile au joueueur
        // Place la tuile si possible, return false sinon
        return p.placer(tuileCourante, x, y);
    }

    public String toString() { // Affichage textuel d'un joueur
        String a;
        if (isBot()) {
            a = "Joueur " + nom + " est un bot.";
        } else {
            a = "Joueur " + nom + " n'est pas un bot.";
        }
        return a;
    }
}