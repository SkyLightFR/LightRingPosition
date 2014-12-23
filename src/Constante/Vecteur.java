package Constante;

import LightRing.LightRing;

public class Vecteur {
	private int directionX;
	private int directionY;
	private boolean valide;

	public Vecteur(int directionX, int directionY) {
		this.directionX = directionX;
		this.directionY = directionY;
		this.valide = true;
	}

	public int getDirectionX() {
		return directionX;
	}

	public int getDirectionY() {
		return directionY;
	}

	public void reinitialiserX(int x) {
		directionX = x;
	}

	public void reinitialiserY(int y) {
		directionY = y;
	}

	public void setDirectionX(int x) {
		directionX+=x;
	}

	public void setDirectionY(int y) {
		directionY+=y;
	}

	public boolean isValide() {
		return valide;
	}

	public String toString() {
		return ("Direction X :"+directionX+" Direction Y :"+directionY);
	}

	public boolean calcul(LightRing Plateau, int coordX, int coordY) {
		int conditionX = coordX+getDirectionX();
		int conditionY = coordY+getDirectionY();
		if(conditionX >=0 && conditionX<Plateau.getLargeur() && conditionY >=0 && conditionY <Plateau.getHauteur() && Plateau.getJoueur() == Plateau.getJ1()) {
			if(Plateau.getPlateau()[conditionY][conditionX] == Constante.J1) {
				return true;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.J2) {
				Plateau.getPlateau()[conditionY][conditionX] = Constante.CASEJ2_J1;
				Plateau.coloriage(conditionX, conditionY);
				valide = false;
				return false;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.CASE_J2) {
				Plateau.getPlateau()[conditionY][conditionX] = Constante.CASE_J1;
				Plateau.coloriage(conditionX, conditionY);
				valide = false;
				return false;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.CASEJ2_J1) {
				return true;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.CASE_J1) {
				return true;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.NEUTRE) {
				return true;
			} else {	
				valide = false;
				return false;
			}
		} else if(conditionX >=0 && conditionX<Plateau.getLargeur() && conditionY >=0 && conditionY <Plateau.getHauteur() && Plateau.getJoueur() == Plateau.getJ2()) {
			if(Plateau.getPlateau()[conditionY][conditionX] == Constante.J2) {
				return true;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.J1) {
				Plateau.getPlateau()[conditionY][conditionX] = Constante.CASEJ1_J2;
				Plateau.coloriage(conditionX, conditionY);
				valide = false;
				return false;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.CASE_J1) {
				Plateau.getPlateau()[conditionY][conditionX] = Constante.CASE_J2;
				Plateau.coloriage(conditionX, conditionY);
				valide = false;
				return false;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.CASEJ1_J2) {
				return true;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.CASE_J2) {
				return true;
			} else if (Plateau.getPlateau()[conditionY][conditionX] == Constante.NEUTRE) {
				return true;
			} else {
				valide = false;
				return false;
			}
		} else {
			return false;
		}
	}
}