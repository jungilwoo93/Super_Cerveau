package fr.ensisa.supercerveau.model.questions;

import java.util.Arrays;
import java.util.Scanner;

import fr.ensisa.supercerveau.model.util.Constantes;

public abstract class Question {
	
	protected int categorie;
	protected String enonce;
	protected String bonneReponse;
	protected String[] mauvaisesReponses;
	
	public Question(int categorie)
	{
		this.categorie=categorie;
		this.mauvaisesReponses= new String[3];
	}
	
	public int ask(Scanner entry)
	{
		//Place les réponses dans le désordre
		String[] reponses= new String[4];
		Arrays.fill(reponses, null);
		int bonneReponseIndex = (int)(Math.random()*Constantes.NB_REPONSES);
		reponses[bonneReponseIndex]=bonneReponse;
		int mauvaisesReponsesPlacees=0;
		while(mauvaisesReponsesPlacees<Constantes.NB_REPONSES-1)
		{
			int index=(int)(Math.random()*Constantes.NB_REPONSES);
			if(reponses[index]==null)
			{
				reponses[index]=mauvaisesReponses[mauvaisesReponsesPlacees];
				mauvaisesReponsesPlacees++;
			}
		}
		// Affiche questions + réponses
		System.out.println("Question de "+Constantes.CATEGORIES[this.categorie]);
		System.out.println(enonce);
		for(int i=0;i<Constantes.NB_REPONSES;i++)
		{
			System.out.println((i+1)+") "+reponses[i]);
		}
		// Demande à l'utilisateur sa réponse
		System.out.print("Votre choix : ");
		int choix;
		do{
			choix=entry.nextInt();
		}while(choix<1||choix>Constantes.NB_QUESTIONS);
		int score=0;
		// Analyse de la réponse
		if(choix==bonneReponseIndex+1)
		{
			System.out.println("BRAVO VOUS AVEZ TROUVÉ LA BONNE RÉPONSE !");
			score=1;
		}
		else
		{
			System.out.println("NON, LA BONNE RÉPONSE ÉTAIT : "+this.bonneReponse);
		}
		return score;
	}
	
	protected boolean reponseAbsente(String nouvelleReponse)
	{
		boolean absent=true;
		if(this.bonneReponse.equalsIgnoreCase(nouvelleReponse))
		{
			absent=false;
		}
		else
		{
			for(int i=0; i<mauvaisesReponses.length; i++)
			{
				if(this.mauvaisesReponses[i]!=null&&this.mauvaisesReponses[i].equalsIgnoreCase(nouvelleReponse))
				{
					absent=false;
				}
			}
		}
		return absent;
	}

	

}
