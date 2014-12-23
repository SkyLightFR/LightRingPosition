package Plateau;

import information.Information;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Constante.Constante;
import LightRing.LightRing;


public class PlateauJeu extends JFrame{
	
	private JPanel menu = new JPanel();
	private LightRing plateau;
	private Information info;
	private Print print;
	public PlateauJeu(int taille) throws IOException {
		if(taille%2 == 0) {
			plateau = new LightRing(taille+1,taille+1);
		} else {
			plateau = new LightRing(taille, taille);
		}
		menu.setLayout(null);
		info = new Information(plateau,this);
		print =new Print(plateau,info);
		print.setBounds(5,5,plateau.getLargeur()*Constante.TAILLE_CARREAU,plateau.getLargeur()*Constante.TAILLE_CARREAU);
		info.setBounds(5,plateau.getLargeur()*Constante.TAILLE_CARREAU+10,plateau.getLargeur()*Constante.TAILLE_CARREAU,50);
		menu.add(print);
		menu.add(info);
		
	}
	
	public JPanel getPanel() {
		return menu;
	}
	
	public LightRing getPlateau() {
		return plateau;
	}
	
	public JPanel getMenu() {
		return menu;
	}
	
	public Information getInfo() {
		return info;
	}
	
	public Print getPrint(){
		return print;
	}
}