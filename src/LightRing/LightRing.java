package LightRing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Constante.Constante;

public class LightRing extends JPanel {

	private int[][] plateau;
	private int hauteur, largeur;
	private Joueur J1, J2, joueur;
	private int tour = 30;

	private JFrame frame = new JFrame();

	public LightRing(int hauteur, int largeur) {
		plateau = new int[hauteur][largeur];
		J1 = new Joueur(Constante.J1,largeur/2,0);
		J2 = new Joueur(Constante.J2,largeur/2,hauteur-1);
		joueur = J1;

		for(int i=0;i<hauteur;i++) {
			for(int j=0;j<largeur;j++) {
				if((Math.abs(hauteur/2 - i) + Math.abs(largeur/2 - j))>hauteur/2){
					plateau[i][j] = Constante.MUR;

				} else {
					plateau[i][j] = Constante.NEUTRE;
				}
			}
		}

		plateau[J1.getPosY()][J1.getPosX()] = Constante.J1;
		plateau[J2.getPosY()][J2.getPosX()] = Constante.J2;
		this.hauteur = hauteur;
		this.largeur = largeur;
	}

	public int getTour() {
		return tour;
	}
	
	public void setTour(int nb){
		tour = nb;
	}
	

	public int getHauteur() {
		return this.hauteur;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public int[][] getPlateau() {
		return plateau;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Joueur getJ1() {
		return J1;
	}

	public Joueur getJ2() {
		return J2;
	}

	public void setJoueur(Joueur lambda) {
		joueur = lambda;
	}

	public boolean validation(int coordX, int coordY) {
		if(!calculCavalier(joueur, coordX, coordY)) {
			return false;
		} 
		if(deplacement(joueur, coordX, coordY)){
			return true;
		} else {
			return false;
		}

	}

	public boolean calculCavalier(Joueur joueur, int coordX, int coordY) {
		if(plateau[coordY][coordX] == Constante.J1 || plateau[coordY][coordX] == Constante.J2 || plateau[coordY][coordX] == Constante.CASEJ1_J2 || plateau[coordY][coordX] == Constante.CASEJ2_J1) {
			JOptionPane.showMessageDialog(frame, "Deplacement sur un joueur impossible.", "Erreur deplacement",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if( ((Math.abs(hauteur/2 - coordY) + Math.abs(largeur/2 - coordX))<=hauteur/2)){
			if( (Math.abs(joueur.getPosX()-coordX) == 2 && Math.abs(joueur.getPosY() - coordY) == 1) ||
					(Math.abs(joueur.getPosX()-coordX) == 1 && Math.abs(joueur.getPosY() - coordY) == 2)) {
				return true;

			} else {
				JOptionPane.showMessageDialog(frame, "Deplacement uniquement en cavalier.", "Erreur deplacement",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Vous avez cliquer en dehors de la zone de jeu.", "Zone de Jeu",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public boolean deplacement(Joueur lambda, int coordX, int coordY) {
		if(lambda.equals(J1)) {
			getPlateau()[lambda.getPosY()][lambda.getPosX()] = Constante.CASE_J1;
			getJoueur().setPosX(coordX);
			getJoueur().setPosY(coordY);
			getPlateau()[getJoueur().getPosY()][getJoueur().getPosX()] = Constante.J1;
			return true;
		} else {	
			getPlateau()[lambda.getPosY()][lambda.getPosX()] = Constante.CASE_J2;
			getJoueur().setPosX(coordX);
			getJoueur().setPosY(coordY);
			getPlateau()[getJoueur().getPosY()][getJoueur().getPosX()] = Constante.J2;
			return true;
		} 
	}

	public void coloriage(int coordX, int coordY) {
		if(getJoueur() == J1 && getPlateau()[coordY][coordX] == Constante.NEUTRE) {
			getPlateau()[coordY][coordX] = Constante.CASE_J1;
		} else if(getJoueur() == J2 && getPlateau()[coordY][coordX] == Constante.NEUTRE ) {
			getPlateau()[coordY][coordX] = Constante.CASE_J2;
		}
	}
}