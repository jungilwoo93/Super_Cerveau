package application;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import fr.ensisa.supercerveau.model.player.Player;
import fr.ensisa.supercerveau.model.util.Constantes;


public class WindowsPlayer extends JFrame{
	//c'est la fenêtre pour le quiz
	private int nbPlayer;
	private int sujetChoiced;
	private JPanel panel;
	private JLabel joueurAJouer;
	private String[] sujets = Constantes.CATEGORIES;
	private int currentPlayer;
	private int firstQuestion;
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
		this.firstQuestion = 0;
		this.currentPlayer=1;
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
		JButton play=new JButton("Play !");
		panneau.add(play);
		JLabel question =new JLabel("Questions sur le sujet : ");
		JLabel sujetChoix = new JLabel(sujets[sujetChoiced]);
		panneau.add(question);
		panneau.add(sujetChoix);
		
		for(int i = 0; i<labels.size();i++) {
			panneau.add(labels.get(i));
		}
		
		play.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	PanelQuestion panelQuestion = new PanelQuestion(nbPlayer,sujet);
	        	JButton next=new JButton("Next");
	        	panneau.add(next);
	        	
	        	next.setBounds(play.getBounds().getBounds()); //on place le bouton next sur les coordonnées du bouton play
	        	play.setVisible(false); //on cache le bouton play
	        	if(firstQuestion==0) {
	        		try {
						panel = panelQuestion.setQuestionPanel();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	        		panneau.add(panel);
		        	panel.setBounds(200, 150, 900, 800);
		        	panel.setBackground(Color.WHITE);
	        	}

	        	if(currentPlayer==1) {
	        		joueurAJouer = panelQuestion.setJoueurLabel(1);
    	        	panneau.add(joueurAJouer);
    	        	joueurAJouer.setBounds(300, 800, 300, 30);
	        	}
	        	
	        	next.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				int choix = panelQuestion.getReponseChoix();
    					System.out.println(choix);
	    				if(panelQuestion.envoyerReponse()) {
	    					players.get(currentPlayer-1).addPoint();
	    					Rectangle r = labels.get((currentPlayer-1)*2+1).getBounds();
	    					labels.get((currentPlayer-1)*2+1).setVisible(false);
	    					labels.add((currentPlayer-1)*2+1, new JLabel(players.get(currentPlayer-1).getScore().toString()));
	    					labels.remove(currentPlayer*2);
	    					panneau.add(labels.get((currentPlayer-1)*2+1));
	    					labels.get((currentPlayer-1)*2+1).setBounds(r);
	    					labels.get((currentPlayer-1)*2+1).setVisible(true);
	    				}
	    				currentPlayer++;
	    			    panel.setVisible(false);
	    			    panneau.remove(joueurAJouer);
	    				if(currentPlayer==nbPlayer+1) {
	    					currentPlayer=1;
	    				}
						
	    				try {
							panel = panelQuestion.setQuestionPanel();
						} catch (IOException e1) {
							e1.printStackTrace();
						}

	    				joueurAJouer = panelQuestion.setJoueurLabel(currentPlayer);
	    				
	    	        	panneau.add(joueurAJouer);
	    				panneau.add(panel);
			        	panel.setBounds(200, 150, 900, 800);
			        	joueurAJouer.setBounds(300, 800, 300, 30);
			        	panel.setBackground(Color.WHITE);
			        	joueurAJouer.setVisible(true);
	    				panel.setVisible(true);
	    				firstQuestion++;
	    			}
	    		});
	        }
	    });
		
		panneau.setBackground(Color.WHITE);
		setContentPane(panneau);
		panneau.setLayout(null);
		logo.setBounds(0, 0, 200, 200);
		
		for(int i = 0; i<labels.size();i+=2) {
			labels.get(i).setBounds(30, 210+i*45, 100, 30);
			labels.get(i+1).setBounds(130, 210+i*45, 100, 30);
			if(i==labels.size()-2) {
				if(i==0) {
					question.setBounds(30,280,150,30);
					sujetChoix.setBounds(30,300,100,30);
					play.setBounds(30, 335, 100, 30);
				}else {
					question.setBounds(30,210+i*70,150,30);
					sujetChoix.setBounds(30,210+i*90,100,30);
					play.setBounds(30, 210+i*105, 100, 30);
				}
			}
		}
	}

	public int getNbPlayer() {
		return nbPlayer;
	}

	public void setNbPlayer(int nbPlayer) {
		this.nbPlayer = nbPlayer;
	}
	
}
