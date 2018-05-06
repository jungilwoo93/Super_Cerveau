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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import fr.ensisa.supercerveau.model.player.Player;
import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.ScoreWinException;


public class WindowsPlayer extends JFrame{
	//c'est la fenêtre pour le quiz
	private int nbPlayer;
	private int sujetChoiced;
	private JPanel panel;
	private JLabel joueurAJouer;
	private String[] sujets = Constantes.CATEGORIES;
	private int currentPlayer;
	ArrayList<Player> players;
	ArrayList<JLabel> labels;
	
	public WindowsPlayer(int nb,int sujet) throws IOException {
		super("Super Cerveau");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		WindowListener l =new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		};
		
		this.nbPlayer = nb;
		this.sujetChoiced = sujet;
		this.currentPlayer=1;
		players= new ArrayList<Player>();
		labels = new ArrayList<JLabel>();
		JPanel panneau = new JPanel();
		
		//creer les joueurs et les enregistrer dans la liste
		for(int i =1;i<=nbPlayer;i++) {
			String namePlayer="Joueur "+i;
			Player player = new Player(namePlayer);
			players.add(player);
		}
		
		//ajouter les players et leur note dans la liste de Labels
		for(int i=0;i<players.size();i++) {
			JLabel player = new JLabel(players.get(i).getName());
			JLabel note = null;
			try {
				note = new JLabel(players.get(i).getScore().toString());
			} catch (ScoreWinException e1) {
				e1.printStackTrace();
			}
			labels.add(player);
			labels.add(note);
		}
		
		//ajouter les labels de joueur dans le panneau
		for(int i = 0; i<labels.size();i++) {
			panneau.add(labels.get(i));
		}
		
		//récupérer le logo et l'afficher
		ImageIcon img = new ImageIcon(new ImageIcon("src\\image\\logo.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		JLabel logo = new JLabel(img);
		panneau.add(logo);
		
		//ajouter un button pour commencer le jeu
		JButton play=new JButton("Play !");
		panneau.add(play);
		
		//afficher le sujet qu'on choisit
		JLabel question =new JLabel("Questions sur le sujet : ");
		JLabel sujetChoix = new JLabel(sujets[sujetChoiced]);
		panneau.add(question);
		panneau.add(sujetChoix);
		
		//action du button play
		play.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	//créer le panel avec le sujet choisi
	        	PanelQuestion panelQuestion = new PanelQuestion(sujet);
	        	//on cache le bouton play
	        	play.setVisible(false);
	        	//le button play remplace par le button next
	        	JButton next=new JButton("Next");
	        	panneau.add(next);
	        	next.setBounds(play.getBounds().getBounds()); //on place le bouton next sur les coordonnées du bouton play
	        	
	        	//afficher le panel Question
	        	try {
					panel = panelQuestion.setQuestionPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	panneau.add(panel);
		        panel.setBounds(200, 150, 900, 800);
		        panel.setBackground(Color.WHITE);
	        	
	        	//afficher le joueur qui va repondre à la question
	        	if(currentPlayer==1) {
	        		joueurAJouer = new JLabel("Joueur " + currentPlayer +" à Jouer");
    	        	panneau.add(joueurAJouer);
    	        	joueurAJouer.setBounds(300, 800, 300, 30);
    	        	joueurAJouer.setVisible(true);
    	        	
	        	}
	        	
	        	//action de button next
	        	next.addActionListener(new ActionListener() {
	    			@Override
	    			public void actionPerformed(ActionEvent e) {
	    				//envoyer la réponse et demander la réponse qui est juste ou pas
	    				if(panelQuestion.envoyerReponse()) {
	    					//calculer les notes et mis à jour le label de note
	    					players.get(currentPlayer-1).addPoint();
	    					Rectangle r = labels.get((currentPlayer-1)*2+1).getBounds();
	    					labels.get((currentPlayer-1)*2+1).setVisible(false);
	    					String score;
	    					try{ //Si le score est superieur a la constante SCORE_WIN, cela génère une exception
	    						score = players.get(currentPlayer-1).getScore().toString();
	    					}catch(ScoreWinException scoreEx)
	    					{	//on demande si il veut continuer a jouer ou quitter l'application
	    						int option = JOptionPane.showConfirmDialog(null, players.get(currentPlayer-1).getName()+" Est Victorieux. Voulez vous continuer ?", "Victoire!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    						score = scoreEx.getMessage();
	    						if(option != JOptionPane.OK_OPTION){
	    							System.exit(0);
	    						}
	    					}
	    					labels.add((currentPlayer-1)*2+1, new JLabel(score));
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

	    				joueurAJouer = new JLabel("Joueur " + currentPlayer +" à Jouer");
	    	        	panneau.add(joueurAJouer);
	    				panneau.add(panel);
			        	panel.setBounds(200, 150, 900, 800);
			        	joueurAJouer.setBounds(300, 800, 300, 30);
			        	panel.setBackground(Color.WHITE);
			        	joueurAJouer.setVisible(true);
	    				panel.setVisible(true);
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
