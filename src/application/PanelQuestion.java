package application;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import fr.ensisa.supercerveau.model.questions.Question;
import fr.ensisa.supercerveau.model.questions.QuestionFactory;

public class PanelQuestion{
	private int sujetChoiced;
	private int nbPlayer;
	private int currentPlayer;
	private int reponseChoix;
	private Question questions;
	
	public PanelQuestion(int nb,int sujet) {
		this.nbPlayer = nb;
		this.sujetChoiced = sujet;
	}
	
	public JPanel setQuestionPanel() throws IOException {
		JPanel panneau = new JPanel();
		questions=QuestionFactory.createQuestion(sujetChoiced);
		JLabel questionLabel= new JLabel("Question : " +questions.returnEnonce());
		String[] reponses =questions.stockReponses();
		String urlImg = questions.returnURLImg();
		
		JRadioButton reponse1=new JRadioButton(reponses[0]);
		JRadioButton reponse2=new JRadioButton(reponses[1]);
		JRadioButton reponse3=new JRadioButton(reponses[2]);
		JRadioButton reponse4=new JRadioButton(reponses[3]);
		panneau.add(questionLabel);
		panneau.add(reponse1);
		panneau.add(reponse2);
		panneau.add(reponse3);
		panneau.add(reponse4);
		
		if(urlImg != null) {
			URL obj =new URL(urlImg);
			HttpURLConnection con = (HttpURLConnection)obj.openConnection();
			con.setInstanceFollowRedirects(true);
			con.connect();
			String location = con.getHeaderField("Location").toString();
			ImageIcon img = new ImageIcon(new ImageIcon(new URL(location)).getImage().getScaledInstance(350, 400, Image.SCALE_DEFAULT));
			JLabel questionImg = new JLabel(img);
			panneau.add(questionImg);
			questionImg.setBounds(100, 40, 350, 400);
			questionLabel.setBounds(100,10,600,30);
			reponse1.setBounds(100, 400, 600, 60);
			reponse1.setBackground(Color.WHITE);
			reponse2.setBounds(100, 460, 600, 60);
			reponse2.setBackground(Color.WHITE);
			reponse3.setBounds(100,520 , 600, 60);
			reponse3.setBackground(Color.WHITE);
			reponse4.setBounds(100, 580, 600, 60);
			reponse4.setBackground(Color.WHITE);
		}
		else {
			questionLabel.setBounds(100,40,600,60);
			reponse1.setBounds(100, 140, 600, 60);
			reponse1.setBackground(Color.WHITE);
			reponse2.setBounds(100, 240, 600, 60);
			reponse2.setBackground(Color.WHITE);
			reponse3.setBounds(100,340 , 600, 60);
			reponse3.setBackground(Color.WHITE);
			reponse4.setBounds(100, 440, 600, 60);
			reponse4.setBackground(Color.WHITE);
		}
		
		reponse1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reponse2.setSelected(false);
				reponse3.setSelected(false);
				reponse4.setSelected(false);
				setReponseChoix(0);
			}
		});
			
		reponse2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reponse1.setSelected(false);
				reponse3.setSelected(false);
				reponse4.setSelected(false);
				setReponseChoix(1);
			}
		});
		
		reponse3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reponse2.setSelected(false);
				reponse1.setSelected(false);
				reponse4.setSelected(false);
				setReponseChoix(2);
			}
		});

		reponse4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reponse2.setSelected(false);
				reponse3.setSelected(false);
				reponse1.setSelected(false);
				setReponseChoix(3);
			}
		});
		if(!reponse1.isSelected()&&!reponse2.isSelected()&&!reponse3.isSelected()&&!reponse4.isSelected()) {
			setReponseChoix(-1);
		}
		panneau.setLayout(null);
		return panneau;
	}
	
	public JLabel setJoueurLabel(int joueur) {
		currentPlayer=joueur;
		return new JLabel("Joueur " + joueur +" à Jouer");
	}

	public int getReponseChoix() {
		return reponseChoix;
	}

	public void setReponseChoix(int reponseChoix) {
		this.reponseChoix = reponseChoix;
	}
	
	public boolean envoyerReponse() {
		return questions.demander(reponseChoix);
	}
}
