package LightRing;

public class Joueur {
	private int Joueur;
	private int posX;
	private int posY;
	
	public Joueur(int Joueur, int posX, int posY) {
		this.Joueur = Joueur;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void setJoueur(int joueur) {
		this.Joueur = joueur;
	}
	
	public int getJoueur() {
		return this.Joueur;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getPosY() {
		return this.posY;
	}
}