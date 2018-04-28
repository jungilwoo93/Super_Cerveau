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
	private int nbPlayer=1;
	private WindowsPlayer wp;
	private boolean newWindows=false;
	private int sujetChoiced;
	String[] sujets = Constantes.CATEGORIES;
	public MainPrincipe() throws IOException {
		super("Super Cerveau");
		WindowListener l =new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		};
		
		JButton button = new JButton("Let's Go!");
		String[] elements = { "1", "2", "3", "4"};
		
		JComboBox jCombo = new JComboBox(elements);
		jCombo.setBackground(Color.WHITE);
		jCombo.setSelectedIndex(0);
		JLabel label = new JLabel("Multi-Joueur");
		JLabel sujet = new JLabel("Sujets");
		JComboBox sujetCombo = new JComboBox(sujets);
		sujetCombo.setSelectedIndex(0);
		JPanel panneau = new JPanel();
		BufferedImage logoImage=ImageIO.read(new File("src\\image\\logo.png"));
		JLabel logo = new JLabel(new ImageIcon(logoImage));
		
		jCombo.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	nbPlayer = jCombo.getSelectedIndex()+1;
		    	sujetChoiced = sujetCombo.getSelectedIndex();
		    }
		});
		
		button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	try {
					wp=new WindowsPlayer(nbPlayer,sujetChoiced);
					wp.setBackground(Color.WHITE);
			        wp.setSize(1200,1000);
			        wp.setResizable(false);
			        wp.setVisible(true);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	           
	        }

	    });
		
		panneau.add(button);
		panneau.add(jCombo);
		panneau.add(label);
		panneau.add(logo);
		panneau.add(sujetCombo);
		panneau.add(sujet);
		panneau.setBackground(Color.WHITE);
		setContentPane(panneau);
		panneau.setLayout(null);
		logo.setBounds(50, 10, 400, 300);
		label.setBounds(150,300, 100, 30);
		jCombo.setBounds(280,300,120,30);
		button.setBounds(200, 400, 100, 30);
		sujetCombo.setBounds(280, 350, 120, 30);
		sujet.setBounds(150, 350, 120, 30);
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
