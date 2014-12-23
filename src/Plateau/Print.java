package Plateau;


import information.Information;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;






import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import LightRing.LightRing;
import Constante.Constante;
import Constante.Vecteur;

public class Print extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LightRing plateau;
	private int hCentre;
	private int lCentre;

	private int haut;
	private int larg;
	private List<Vecteur> list;
	JFrame frame = new JFrame();

	public Print(final LightRing plateau, final Information info)  {

		this.plateau = plateau;
		this.haut = plateau.getHauteur();
		this.larg = plateau.getLargeur();
		this.list = new ArrayList<Vecteur>();
		list.add(Constante.BAS);
		list.add(Constante.HAUT);
		list.add(Constante.GAUCHE);
		list.add(Constante.DROITE);
		list.add(Constante.BAS_DROITE);
		list.add(Constante.BAS_GAUCHE);
		list.add(Constante.HAUT_DROITE);
		list.add(Constante.HAUT_GAUCHE);

		this.setLayout(new GridLayout(plateau.getHauteur()+1,plateau.getLargeur()+1));
		this.setPreferredSize(new Dimension(plateau.getLargeur()*Constante.TAILLE_CARREAU,plateau.getHauteur()*Constante.TAILLE_CARREAU));
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		addMouseListener(new MouseListener(){

			public void mouseReleased(MouseEvent e){

				boolean continuer = true;
				int coordX = (int) (e.getX()/Constante.TAILLE_CARREAU);
				int coordY = (int) (e.getY()/Constante.TAILLE_CARREAU);
				if(plateau.getTour() == 0 ) {
					if(Information.getBouleJ1() > Information.getBouleJ2()) {
						JOptionPane.showMessageDialog(frame, "Le joueur 1 gagne.", "Vainqueur : Joueur 1",JOptionPane.INFORMATION_MESSAGE);
					} else if (Information.getBouleJ1() < Information.getBouleJ2()) {
						JOptionPane.showMessageDialog(frame, "Le joueur 2 gagne.", "Vainqueur : Joueur 2",JOptionPane.INFORMATION_MESSAGE);
					} else if (Information.getBouleJ1() == Information.getBouleJ2()) {
						JOptionPane.showMessageDialog(frame, "Match Nul", "Aucun Vainqueur",JOptionPane.INFORMATION_MESSAGE);
					}
				} else if(plateau.validation(coordX, coordY) && plateau.getTour() != 0) {
					while(continuer) {
						continuer = false;
						for(Vecteur v : list) {
							while(v.calcul(plateau,coordX,coordY)){
								plateau.coloriage(coordX+v.getDirectionX(), coordY+v.getDirectionY());
								if(v.equals(Constante.HAUT)) {
									v.setDirectionY(-1);
								}else if (v.equals(Constante.BAS)) {
									v.setDirectionY(1);
								}else if (v.equals(Constante.GAUCHE)) {
									v.setDirectionX(-1);
								}else if (v.equals(Constante.DROITE)) {
									v.setDirectionX(1);
								}else if (v.equals(Constante.BAS_DROITE)) {
									v.setDirectionX(1);
									v.setDirectionY(1);
								}else if (v.equals(Constante.BAS_GAUCHE)) {
									v.setDirectionX(-1);
									v.setDirectionY(1);
								}else if (v.equals(Constante.HAUT_DROITE)) {
									v.setDirectionX(1);
									v.setDirectionY(-1);
								}else if (v.equals(Constante.HAUT_GAUCHE)) {
									v.setDirectionX(-1);
									v.setDirectionY(-1);
								}
							}
							if(v.isValide()) {
								continuer = true;
							}
							repaint();
						}	
					}
					reinitialiserVecteur();
					try {
						info.updateInfo();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(plateau.getJoueur() == plateau.getJ1()) {
						plateau.setJoueur(plateau.getJ2());
						reinitialiserVecteur();
					} else {
						plateau.setJoueur(plateau.getJ1());
						reinitialiserVecteur();
					}
					if(plateau.getTour() == 0 ) {
						if(Information.getBouleJ1() > Information.getBouleJ2()) {
							JOptionPane.showMessageDialog(frame, "Le joueur 1 gagne.", "Vainqueur : Joueur 1",JOptionPane.INFORMATION_MESSAGE);
						} else if (Information.getBouleJ1() < Information.getBouleJ2()) {
							JOptionPane.showMessageDialog(frame, "Le joueur 2 gagne.", "Vainqueur : Joueur 2",JOptionPane.INFORMATION_MESSAGE);
						} else if (Information.getBouleJ1() == Information.getBouleJ2()) {
							JOptionPane.showMessageDialog(frame, "Match Nul", "Aucun Vainqueur",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} 
			}


			public void mouseEntered(MouseEvent arg0) {
			}


			public void mouseExited(MouseEvent arg0) {
			}


			public void mouseClicked(MouseEvent arg0) {
			}


			public void mousePressed(MouseEvent arg0) {
			}
		});
	}

	public int getHauteur() {
		return haut;
	}

	public int getLargeur() {
		return larg;
	}

	public int getHauteurCentre() {
		return hCentre;
	}

	public int getLargeurCentre() {
		return lCentre;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage caseJ1 = null;
		BufferedImage caseJ2 = null;
		BufferedImage J1 = null;
		BufferedImage J2 = null;
		BufferedImage vide = null;


		try {
			caseJ1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("fireball.png"));
			J1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("Mage-rouge.gif"));
			caseJ2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("lightball.png"));
			J2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("mage-bleu.gif"));
			vide = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("void.gif"));
		} catch (IOException e) {
		}

		hCentre = plateau.getHauteur()/2;
		lCentre = plateau.getLargeur()/2;

		for(int h=0;h<plateau.getHauteur();h++) {
			for(int l=0;l<plateau.getLargeur();l++) {

				if((Math.abs(getHauteurCentre() - h) + Math.abs(getLargeurCentre() -l))<=plateau.getHauteur()/2) {	
					g.setColor(Color.white);
					g.fillRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				}

				if(plateau.getPlateau()[h][l] == Constante.J1) {
					g.drawImage(J1, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} else if (plateau.getPlateau()[h][l] == Constante.CASE_J1) {
					g.drawImage(caseJ1, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} else if (plateau.getPlateau()[h][l] == Constante.CASEJ2_J1) {
					g.drawImage(J2, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Color.red, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} else if (plateau.getPlateau()[h][l] == Constante.J2) {
					g.drawImage(J2, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} else if (plateau.getPlateau()[h][l] == Constante.CASE_J2) {
					g.drawImage(caseJ2, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} else if (plateau.getPlateau()[h][l] == Constante.CASEJ1_J2) {
					g.drawImage(J1, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Color.cyan, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} else if (plateau.getPlateau()[h][l] == Constante.MUR){
					g.drawImage(vide, l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU, null);
					g.setColor(Color.black);
					g.drawRect(l*Constante.TAILLE_CARREAU,h*Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU,Constante.TAILLE_CARREAU);
				} 
			} 
		}


	}

	public void reinitialiserVecteur()  {
		for(Vecteur v : list) {

			if(v.equals(Constante.HAUT)) {
				v.reinitialiserX(0);
				v.reinitialiserY(-1);
			}if (v.equals(Constante.BAS)) {
				v.reinitialiserX(0);
				v.reinitialiserY(1);
			}if (v.equals(Constante.GAUCHE)) {
				v.reinitialiserX(-1);
				v.reinitialiserY(0);
			}if (v.equals(Constante.DROITE)) {
				v.reinitialiserX(1);
				v.reinitialiserY(0);
			}if (v.equals(Constante.BAS_DROITE)) {
				v.reinitialiserX(1);
				v.reinitialiserY(1);
			}if (v.equals(Constante.BAS_GAUCHE)) {
				v.reinitialiserX(-1);
				v.reinitialiserY(1);
			}if (v.equals(Constante.HAUT_DROITE)) {
				v.reinitialiserX(1);
				v.reinitialiserY(-1);
			}if (v.equals(Constante.HAUT_GAUCHE)) {
				v.reinitialiserX(-1);
				v.reinitialiserY(-1);
			}
		}
	}
}