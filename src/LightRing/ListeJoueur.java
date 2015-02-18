package LightRing;

import java.util.ArrayList;
import java.util.List;
import Constante.Constante;


public class ListeJoueur {
	List<Joueur> Liste = new ArrayList<Joueur>();
	private int posX;
	private int posY;
	public ListeJoueur(int nbJoueur, int[][] plateau, int hauteur, int largeur) {
		for (int i=0; i < nbJoueur; i++) {
			do {
			posX = (int) (Math.random()*largeur);
			posY = (int) (Math.random()*hauteur);
			}while(plateau[posX][posY] == Constante.NEUTRE);
			Liste.add(new Joueur(0-i, posX,posY));
		}
	}
}
