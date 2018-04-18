package application;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class WindowsPlayer extends JFrame{
	private int nbPlayer;
	Map<JLabel,JLabel> players;
	public WindowsPlayer(int nb) throws IOException {
		super("Super Cerveau");
		WindowListener l =new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		};
		nbPlayer = nb;
		players= new HashMap<JLabel,JLabel>();
		for(int i =0;i<nbPlayer;i++) {
			String namePlayer="Joueur "+i;
			JLabel player = new JLabel(namePlayer);
			JLabel note = new JLabel("");
			//player.setBounds();
			//note.setBounds();
			players.put(player, note);
		}
		BufferedImage logoImage=ImageIO.read(new File("src\\image\\logo.png"));
		JLabel logo = new JLabel(new ImageIcon(logoImage));
		logo.setSize(200, 200);
		JButton button = new JButton("Let's Go!");
		String[] elements = { "1", "2", "3", "4"};
		//JComboBox jCombo = new JComboBox(elements);
		//jCombo.setBackground(Color.WHITE);
		//JLabel label = new JLabel("Multi-Joueur");
		
		JPanel panneau = new JPanel();
		//panneau.add(button);
		//panneau.add(jCombo);
		//panneau.add(label);
		panneau.add(logo);
		panneau.setBackground(Color.WHITE);
		setContentPane(panneau);
		panneau.setLayout(null);
		logo.setBounds(10, 10, 200, 100);
		
		//label.setBounds(150,300, 100, 30);
		//jCombo.setBounds(280,300,80,30);
		button.setBounds(200, 350, 100, 30);
		//setSize(500,500);
	}

	public int getNbPlayer() {
		return nbPlayer;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	
	
}
