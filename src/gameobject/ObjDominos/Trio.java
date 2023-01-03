package src.gameobject.ObjDominos;

//Un Trio représente un côté d'une tuile de Dominos
public class Trio {
    int[] trio; // Tableau d'entiers représentant un triplet ( = Trio)

    /// Constructeurs de la classe
    public Trio(int a0, int a1, int a2) { // Constructeur avec 3 entiers
        trio = new int[3];
        trio[0] = a0;
        trio[1] = a1;
        trio[2] = a2;
    }

    public Trio(int[] t) { // Constructeur avec un tableau
        trio = t;
    }

    /// Getter et Setter
    public int[] getTrio() {
        return trio;
    }

    public void setTrio(int[] trio) {
        this.trio = trio;
    }

    /// Méthodes de la classe Trio
    public boolean estEgal(Trio t) { // Vérifie si les trios sont égaux (au sens des valeurs)
        for (int i = 0; i < 3; i++) {
            if (t.trio[i] != this.trio[i])
                return false;
        }
        return true;
    }

    public void reverse() { // Applique un miroir au trio (abc => cab)
        int temp = trio[0];
        trio[0] = trio[2];
        trio[2] = temp;
    }

    public String toString() { // Affichage d'un trio
        String s = "";
        for (int i = 0; i < 3; i++)
            s += trio[i] + " ";
        return s;
    }
}