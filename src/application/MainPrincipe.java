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

public class MainPrincipe extends JFrame{
	private int nbPlayer=0;
	private WindowsPlayer wp;
	private boolean newWindows=false;
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
		JLabel label = new JLabel("Multi-Joueur");
		JPanel panneau = new JPanel();
		BufferedImage logoImage=ImageIO.read(new File("src\\image\\logo.png"));
		JLabel logo = new JLabel(new ImageIcon(logoImage));
		
		jCombo.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	nbPlayer = jCombo.getSelectedIndex();
		    	try {
					wp = new WindowsPlayer(nbPlayer+1);
					newWindows=true;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});
		
		button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	           wp.setBackground(Color.WHITE);
	           wp.setSize(1000,1000);
	           wp.setResizable(false);
	           wp.setVisible(true);
	        }

	    });
		
		panneau.add(button);
		panneau.add(jCombo);
		panneau.add(label);
		panneau.add(logo);
		panneau.setBackground(Color.WHITE);
		setContentPane(panneau);
		panneau.setLayout(null);
		logo.setBounds(50, 10, 400, 300);
		label.setBounds(150,300, 100, 30);
		jCombo.setBounds(280,300,80,30);
		button.setBounds(200, 350, 100, 30);
		setSize(500,500);
		
		
	}
	
	public int getNbPlayer() {
		return nbPlayer;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}

	public static void main(String[] args) throws IOException {
		JFrame frame = new MainPrincipe();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBackground(Color.DARK_GRAY);
	}

	

}
