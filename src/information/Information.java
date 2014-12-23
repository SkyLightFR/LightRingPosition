package information;


import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import LightRing.LightRing;
import Plateau.PlateauJeu;
import Constante.Constante;

public class Information extends JPanel {

	private JLabel tour;
	private LightRing LightArea;
	private static int bouleJ1;
	private static int bouleJ2;

	public Information(LightRing plateau, PlateauJeu area) throws IOException {
		BufferedImage J1 = null;
		BufferedImage J2 = null;
		J1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("fireball.png"));
		J2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("lightball.png"));
		

		LightArea = plateau;
		if(LightArea.getTour()%2 == 0) {
			tour = new JLabel("<html>Au Joueur 1 de jouer <br> Tour restant : "+LightArea.getTour()+"</html>");
		} else {
			tour = new JLabel("<html>Au Joueur 2 de jouer <br> Tour restant : "+LightArea.getTour()+"</html>");
		}

		setLayout(new GridLayout(0,3));
		add(tour);
		compterScore();
		JLabel scoreJ1 = new JLabel(""+getBouleJ1());
		scoreJ1.setIcon(new ImageIcon(J1));
		JLabel scoreJ2 = new JLabel(""+getBouleJ2());
		scoreJ2.setIcon(new ImageIcon(J2));
		add(scoreJ1);
		add(scoreJ2);

	}


	public void updateInfo() throws IOException {
		BufferedImage J1 = null;
		BufferedImage J2 = null;
		J1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("fireball.png"));
		J2 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("lightball.png"));
	
		LightArea.setTour(LightArea.getTour()-1);
		setLayout(new GridLayout(0,3));
		removeAll();
		if(LightArea.getJoueur() == LightArea.getJ1()) {
			tour = new JLabel("<html>Au Joueur 2 de jouer <br> Tour restant : "+LightArea.getTour()+"</html>");
			add(tour);

		} else if(LightArea.getJoueur() == LightArea.getJ2()) {
			tour = new JLabel("<html>Au Joueur 1 de jouer <br> Tour restant : "+LightArea.getTour()+"</html>");
			add(tour);
		}
		compterScore();
		JLabel scoreJ1 = new JLabel(""+getBouleJ1());
		scoreJ1.setIcon(new ImageIcon(J1));
		JLabel scoreJ2 = new JLabel(""+getBouleJ2());
		scoreJ2.setIcon(new ImageIcon(J2));
		add(scoreJ1);
		add(scoreJ2);
		validate();
		revalidate();
	}

	public void compterScore() {
		initBouleJ1();
		initBouleJ2();
		int val1=0;
		int val2=0;
		
		for(int h=0;h<LightArea.getHauteur();h++) {
			for(int l=0;l<LightArea.getLargeur();l++) {
				
				if((LightArea.getPlateau()[h][l] == Constante.J1 || LightArea.getPlateau()[h][l] == Constante.CASEJ2_J1 || LightArea.getPlateau()[h][l] == Constante.CASE_J1)) {
					val1++;
				} else if( (LightArea.getPlateau()[h][l] == Constante.J2 || LightArea.getPlateau()[h][l] == Constante.CASEJ1_J2 || LightArea.getPlateau()[h][l] == Constante.CASE_J2)) {
					val2++;
				}
			}
		}
		setBouleJ1(val1);
		setBouleJ2(val2);
	}

	public static int getBouleJ1() {
		return bouleJ1;
	}

	public void setBouleJ1(int val) {
		bouleJ1=val;
	}

	public void initBouleJ1() {
		bouleJ1 = 0;
	}

	public static int getBouleJ2() {
		return bouleJ2;
	}

	public void setBouleJ2(int val) {
		bouleJ2=val;
	}

	public void initBouleJ2() {
		bouleJ2 = 0;
	}
}