package main;

import information.Information;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import LightRing.LightRing;
import Plateau.PlateauJeu;

public class LightRingPosition {
	/*
	 * Author : Benjamin Chevalier
	 * Version : 1.3
	 * Name : Light Ring Position
	 */
	private int tour;
	
	public int getTour() {
		return tour;
	}

	public static void main(String[] args) throws IOException {

		PlateauJeu Plateau = new PlateauJeu(20);
		JFrame frame = new JFrame();
		frame.add(Plateau.getMenu());
		frame.setMinimumSize(new Dimension(Plateau.getPlateau().getLargeur()*30+30,Plateau.getPrint().getSize().height+Plateau.getInfo().getSize().height+75));
		frame.setTitle("Light Ring Position - Created by SkyLight");
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
}