package src.gameobject;

//Import de package
import java.util.ArrayList;
import java.util.Random;

//Import des autres dossiers
import src.gameobject.ObjCarcassonne.TuileCarcassonne;
import src.gameobject.ObjDominos.TuileDominos;
import src.utils.Generateur;

public class Plateau {
    Tuile[][] plateauActuel; // Un Plateau est un tableau de tableaux de Tuiles.
    ArrayList<Tuile> defausse; // Une ArrayList des tuiles qui sont défaussées.
    Tuile[] pioche; // Un tableau de tuile représentant la pioche (le sac).
    int[] tuilesRestantes; // Un tableau d'entiers représentant les indexes des tuiles restantes.
    int[][] pionplacé; // Un tableau d'entiers représentant les indexes des pions placés.
    int nbCasesCote; // Nombre de cases du plateau = nbCasesCote * nbCasesCote
    String msgStatut = "Début du tour"; // Message indiquant le statut des actions
    int xCentrePlateau, yCentrePlateau; // Index x,y du centre du plateau
    boolean isCarcassonne; // Indique si le jeu joué est carcassonne ou pas

    /// Constructeurs
    public Plateau(int nbTuiles, boolean carcassonne) {// Constructeur avec un nombre de tuiles
        isCarcassonne = carcassonne;
        // Nombre de cases nécessaires, avec une bordure supplémentaire. ("+2")
        // En effet, on doit pouvoir placer toutes les tuiles sur une moitié de plateau,
        // les uns derrières les autres. D'où le calcul suivant :
        nbCasesCote = nbTuiles * 2 + 2;
        plateauActuel = new Tuile[nbCasesCote][nbCasesCote];
        defausse = new ArrayList<Tuile>();
        tuilesRestantes = new int[nbTuiles];
        pionplacé = new int[nbCasesCote][nbCasesCote];
        pioche = new Tuile[nbTuiles];
        if (isCarcassonne) { // Si le jeu est carcassonne
            pioche = Generateur.genPiocheCarcasssonne();
            for (int i = 0; i < nbTuiles; i++) {
                tuilesRestantes[i] = i;
            }
        } else { // Sinon c'est Dominos
            for (int i = 0; i < nbTuiles; i++) {
                pioche[i] = Generateur.genTuileDominos();
                tuilesRestantes[i] = i;
            }
        }
        for(int j = 0 ; j < pionplacé.length ; j++ ){
            for(int z = 0 ; z < pionplacé[j].length ; z++){
                pionplacé[j][z] = -1;
            }
        }
        centrePlateau();
    }

    /// Getter et Setter
    public int[][] getPionplacé() {
        return pionplacé;
    }
    public int getxCentrePlateau() {
        return xCentrePlateau;
    }

    public int getyCentrePlateau() {
        return yCentrePlateau;
    }

    public String getMsgStatut() {
        return msgStatut;
    }

    public ArrayList<Tuile> getDefausse() {
        return defausse;
    }

    public int getNbCasesCote() {
        return nbCasesCote;
    }

    public Tuile[] getPioche() {
        return pioche;
    }

    public Tuile[][] getPlateauActuel() {
        return plateauActuel;
    }

    public int[] getTuilesRestantes() {
        return tuilesRestantes;
    }

    public boolean isCarcassonne() {
        return isCarcassonne;
    }

    public void setxCentrePlateau(int xCentrePlateau) {
        this.xCentrePlateau = xCentrePlateau;
    }

    public void setyCentrePlateau(int yCentrePlateau) {
        this.yCentrePlateau = yCentrePlateau;
    }

    public void setMsgStatut(String msgStatut) {
        this.msgStatut = msgStatut;
    }

    public void setDefausse(ArrayList<Tuile> defausse) {
        this.defausse = defausse;
    }

    public void setNbCasesCote(int nbCasesCote) {
        this.nbCasesCote = nbCasesCote;
    }

    public void setPioche(Tuile[] pioche) {
        this.pioche = pioche;
    }

    public void setPlateauActuel(Tuile[][] plateauActuel) {
        this.plateauActuel = plateauActuel;
    }

    public void setisCarcassonne(boolean isCarcassonne) {
        this.isCarcassonne = isCarcassonne;
    }

    public void setTuilesRestantes(int[] tuilesRestantes) {
        this.tuilesRestantes = tuilesRestantes;
    }

    /// Méthodes de classe Plateau
    private void centrePlateau() { // Définie le centre du plateau;
        if (nbCasesCote % 2 == 0) {
            xCentrePlateau = (nbCasesCote / 2) - 1;
            yCentrePlateau = (nbCasesCote / 2) - 1;
        } else {
            xCentrePlateau = nbCasesCote / 2;
            yCentrePlateau = nbCasesCote / 2;
        }
    }

    public boolean resteTuiles() { // Vérifie s'il reste des tuiles qui ne sont pas placées
        for (int i = 0; i < tuilesRestantes.length; i++) {
            if (tuilesRestantes[i] != -1)
                return true;
        }
        return false;
    }

    private boolean plateauVide() { // Vérifie si le plateau est vide (aucune tuile n'est posée dessus)
        for (int i = 0; i < plateauActuel.length; i++) {
            for (int j = 0; j < plateauActuel.length; j++) {
                if (plateauActuel[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private Tuile[] tuilesAdjacentes(int x, int y) { // Cherche les tuiles adjacentes à la position voulue
        Tuile[] tuileAdj = new Tuile[4];
        for (int i = 0; i < 4; i++) {
            if (plateauActuel[x - 1][y] != null) { // S'il y a une tuile en haut
                tuileAdj[0] = plateauActuel[x - 1][y];
            } else {
                tuileAdj[0] = null;
            }
            if (plateauActuel[x][y + 1] != null) { // S'il y a une tuile à droite
                tuileAdj[1] = plateauActuel[x][y + 1];
            } else {
                tuileAdj[1] = null;
            }
            if (plateauActuel[x + 1][y] != null) { // S'il y a une tuile en bas
                tuileAdj[2] = plateauActuel[x + 1][y];
            } else {
                tuileAdj[2] = null;
            }
            if (plateauActuel[x][y - 1] != null) { // S'il y a une tuile à gauche
                tuileAdj[3] = plateauActuel[x][y - 1];
            } else {
                tuileAdj[3] = null;
            }
        }
        return tuileAdj;
    }

    public boolean placer(Tuile t, int x, int y) { // Place une tuile en x,y
        boolean comptabileAvecAll = true; // Indique la compatibilité
        int cntVide = 0; // Compte le nombre de cases adjacentes vides
        Tuile[] tuileAdj = tuilesAdjacentes(x, y); // Cases adjacentes à x,y

        // On enregistre la compatibilité ou non de la tuile avec les autres !
        for (int i = 0; i < tuileAdj.length; i++) {
            if (tuileAdj[i] == null) // S'il n'y a pas de tuile, on incrémente le compteur de 1
                cntVide++;
            else { // Sinon, on enregistre la compatibilité
                if (t instanceof TuileCarcassonne) {
                    TuileCarcassonne tt = (TuileCarcassonne) t;
                    TuileCarcassonne ta = (TuileCarcassonne) tuileAdj[i];
                    comptabileAvecAll = comptabileAvecAll && tt.estComptabile(ta, i);
                } else if (t instanceof TuileDominos) {
                    TuileDominos tt = (TuileDominos) t;
                    TuileDominos ta = (TuileDominos) tuileAdj[i];
                    comptabileAvecAll = comptabileAvecAll && tt.estComptabile(ta, i);
                }
            }
        }

        // On vérifie que la tuile est compatible avec toutes les autres !
        // c'est-à-dire, qu'on peut la placer à l'endroit indiqué
        if (plateauVide() || cntVide < 4 && comptabileAvecAll) {
            plateauActuel[x][y] = t;
            msgStatut = "Placement OK";
            return true; // Le placement a été effectué !
        } else {
            msgStatut = "Placement Refusé";
            return false; // Le placement n'a pas été effectué !
        }
    }

    public boolean placerPion(Joueur j, int x, int y){
        int nbPions = j.getPion();
        int ind = 0;
        for(int i = 0 ; i < Joueur.ListColor.length ; i++ ){
            if(j.getCouleur() == Joueur.ListColor[i]){
                ind = i;
            }
        }
        if(nbPions > 0 && pionplacé[x][y]==-1){ //S'il reste des pions
        pionplacé[x][y] = ind;
        j.setPion(nbPions-1);
        return true;
    }
        else{
            return false;
        }
    }
    public Tuile piocher() { // Pioche une tuile au hasard dans la pioche
        Random random = new Random();
        int nb = random.nextInt(pioche.length);
        if (tuilesRestantes[nb] == -1) { // Si la tuile a déjà été piochée, on repioche
            return piocher();
        }
        Tuile temp = new Tuile();
        if (pioche[nb] instanceof TuileDominos) {
            TuileDominos p = (TuileDominos) pioche[nb];
            temp = new TuileDominos(p.getHaut(), p.getBas(), p.getGauche(), p.getDroite());
        } else if (pioche[nb] instanceof TuileCarcassonne) {
            TuileCarcassonne p = (TuileCarcassonne) pioche[nb];
            temp = new TuileCarcassonne(p.getHaut(), p.getDroite(), p.getBas(), p.getGauche(), p.getCentre(),
                    p.getSymbole(), p.isSeparator());
        }
        tuilesRestantes[nb] = -1;
        pioche[nb] = null;
        msgStatut = "Tuile piochée";
        return temp;
    }

    public void defausser(Tuile t) { // Defausse une tuile (l'ajoute aux tuiles défaussées)
        defausse.add(t);
        msgStatut = "Tuile défaussée";
    }
}