package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.ensisa.supercerveau.model.util.Constantes;

public class MainPrincipe extends JFrame{
	//c'est la fenêtre principale qu'on peut choisir le nb de joueur, le sujet de quiz
	//et le niveau de quiz
	private int nbPlayer=1;
	//private boolean newWindows=false;
	private int sujetChoiced;
	String[] sujets = Constantes.CATEGORIES;
	
	public MainPrincipe() throws IOException {
		super("Super Cerveau");//titre de la fenêtre
		
		WindowListener l =new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		};
		
		JPanel panneau = new JPanel();
		
		//un JCombobox pour le nb de joueur
		JLabel label = new JLabel("Multi-Joueur");
		String[] elements = { "1", "2", "3", "4"};//élements dans le jCombox pour le nb de joueur
		JComboBox jCombo = new JComboBox(elements);
		jCombo.setBackground(Color.WHITE);
		jCombo.setSelectedIndex(0);//le premier élément (nb 1) est par défaut
		
		//un JCombobox pour le sujet
		JLabel sujet = new JLabel("Sujets");
		JComboBox sujetCombo = new JComboBox(sujets);
		sujetCombo.setSelectedIndex(0);
		
		//l'image logo 
		BufferedImage logoImage=ImageIO.read(new File("src\\image\\logo.png"));
		JLabel logo = new JLabel(new ImageIcon(logoImage));
		
		//action de JCombo, enregistrer le nb choisi de joueur et le sujet choisi dans les objets globals
		jCombo.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	nbPlayer = jCombo.getSelectedIndex()+1; // nb de joueur qu'on choisit
		    	sujetChoiced = sujetCombo.getSelectedIndex(); // sujet qu'on choisit
		    	//le niveau de jeux qu'on choisit
		    }
		});
		
		//un bouton pour lancer le quiz et ses actions
		JButton button = new JButton("Let's go!");
		button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
	        		WindowsPlayer wp=new WindowsPlayer(nbPlayer,sujetChoiced);
					// lancer la nouvelle fenêtre et envoyer le nb choisi de joueur et le sujet choisi
					wp.setBackground(Color.WHITE);
			        wp.setSize(1200,1000);
			        wp.setResizable(false); 
			        // fixer la taille de fenêtre, on peut pas le grandir
			        wp.setVisible(true);
			        setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	           
	        }

	    });
		
		//ajouter tous les composants dans le Panel
		panneau.add(button);
		panneau.add(jCombo);
		panneau.add(label);
		panneau.add(logo);
		panneau.add(sujetCombo);
		panneau.add(sujet);
		panneau.setBackground(Color.WHITE);
		setContentPane(panneau);
		panneau.setLayout(null);
		//mettre les coordonnées des composants
		logo.setBounds(50, 10, 400, 300);
		label.setBounds(150,300, 100, 30);
		jCombo.setBounds(280,300,120,30);
		button.setBounds(200, 400, 100, 30);
		sujetCombo.setBounds(280, 350, 120, 30);
		sujet.setBounds(150, 350, 120, 30);
		//fixer la taille de fenêtre principale
		setSize(500,500);
		setVisible(true);
		setResizable(false);
		
	}
	
	public int getNbPlayer() {
		return nbPlayer;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}

	public static void main(String[] args) throws IOException {
		JFrame frame = new MainPrincipe();
	}

	

}
