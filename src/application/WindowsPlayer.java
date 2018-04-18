package application;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ensisa.supercerveau.model.player.Player;
import fr.ensisa.supercerveau.model.player.Score;
import fr.ensisa.supercerveau.model.util.Constantes;


public class WindowsPlayer extends JFrame{
	private int nbPlayer;
	private int sujetChoiced;
	private String[] sujets = Constantes.CATEGORIES;
	//Map<JLabel,JLabel> players;
	ArrayList<Player> players;
	ArrayList<JLabel> labels;
	public WindowsPlayer(int nb,int sujet) throws IOException {
		super("Super Cerveau");
		WindowListener l =new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		};
		this.nbPlayer = nb;
		this.sujetChoiced = sujet;
		players= new ArrayList<Player>();
		labels = new ArrayList<JLabel>();
		JPanel panneau = new JPanel();

		for(int i =1;i<=nbPlayer;i++) {
			String namePlayer="Joueur "+i;
			Player player = new Player(namePlayer);
			players.add(player);
		}
		
		for(int i=0;i<players.size();i++) {
			JLabel player = new JLabel(players.get(i).getName());
			JLabel note = new JLabel(players.get(i).getScore().toString());
			labels.add(player);
			labels.add(note);
		}
		
		ImageIcon img = new ImageIcon(new ImageIcon("src\\image\\logo.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(img);
		panneau.add(logo);
		
		for(int i = 0; i<labels.size();i++) {
			panneau.add(labels.get(i));
		}
		
		panneau.setBackground(Color.WHITE);
		setContentPane(panneau);
		panneau.setLayout(null);
		logo.setBounds(0, 0, 200, 200);
		
		for(int i = 0; i<labels.size();i+=2) {
			labels.get(i).setBounds(30, 210+i*45, 100, 30);
			labels.get(i+1).setBounds(130, 210+i*45, 100, 30);
		}

	}

	public int getNbPlayer() {
		return nbPlayer;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	
	
}
