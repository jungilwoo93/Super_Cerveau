package application;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JRadioButton;

import fr.ensisa.supercerveau.model.player.Player;
import fr.ensisa.supercerveau.model.player.Score;
import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.questions.Question;
import fr.ensisa.supercerveau.model.questions.QuestionFactory;


public class WindowsPlayer extends JFrame{
	private int nbPlayer;
	private int sujetChoiced;
	private JPanel panel;
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
	        	panel =setQuestionPanel();
	        	panneau.add(panel);
	        	panel.setBounds(200, 180, 900, 800);
	        	panel.setBackground(Color.WHITE);
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
	
	public JPanel setQuestionPanel() {
		JPanel panneau = new JPanel();
		Question questions=QuestionFactory.createQuestion(sujetChoiced);
		JLabel questionLabel= new JLabel("Question : " +questions.returnEnonce());
		String[] reponses =questions.stockQuestions();
		JRadioButton reponse1=new JRadioButton(reponses[0]);
		JRadioButton reponse2=new JRadioButton(reponses[1]);
		JRadioButton reponse3=new JRadioButton(reponses[2]);
		JRadioButton reponse4=new JRadioButton(reponses[3]);
		panneau.add(questionLabel);
		panneau.add(reponse1);
		panneau.add(reponse2);
		panneau.add(reponse3);
		panneau.add(reponse4);
		questionLabel.setBounds(100,40,600,60);
		reponse1.setBounds(100, 140, 600, 60);
		reponse1.setBackground(Color.WHITE);
		reponse2.setBounds(100, 240, 600, 60);
		reponse2.setBackground(Color.WHITE);
		reponse3.setBounds(100,340 , 600, 60);
		reponse3.setBackground(Color.WHITE);
		reponse4.setBounds(100, 440, 600, 60);
		reponse4.setBackground(Color.WHITE);
		return panneau;
	}
	
}
