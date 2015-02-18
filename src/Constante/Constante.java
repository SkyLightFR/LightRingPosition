package Constante;

public class Constante {
	public final static int NEUTRE  = -60;
	public final static int MUR = -50;
	public final static int CASE_J1 = 1;
	public final static int CASE_J2 = 2;
	public final static int J1 = -1;
	public final static int J2 = -2;
	public final static int J3 = -3;
	public final static int J4 = -4;
	public final static int J5 = -5;
	public final static int J6 = -6;
	public final static int CASEJ1_J2 = -30;
	public final static int CASEJ2_J1 = -40;
	
	public final static int TAILLE_CARREAU = 30;
	
	public static Vecteur HAUT = new Vecteur(0,-1);
	public static Vecteur BAS = new Vecteur(0,1);
	public static Vecteur GAUCHE = new Vecteur(-1,0);
	public static Vecteur DROITE = new Vecteur(1,0);
	public static Vecteur HAUT_GAUCHE = new Vecteur(-1,-1);
	public static Vecteur HAUT_DROITE = new Vecteur(1,-1);
	public static Vecteur BAS_GAUCHE = new Vecteur(-1,1);
	public static Vecteur BAS_DROITE = new Vecteur(1,1);
}