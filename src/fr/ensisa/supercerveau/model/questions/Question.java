package fr.ensisa.supercerveau.model.questions;

import java.util.Arrays;
import java.util.Scanner;

import fr.ensisa.supercerveau.model.util.Constantes;

public abstract class Question {
	
	protected int categorie;
	protected String enonce;
	protected String bonneReponse;
	protected int bonneReponseIndex;
	protected String[] mauvaisesReponses;
	
	public Question(int categorie)
	{
		this.categorie=categorie;
		this.mauvaisesReponses= new String[3];
	}
	
	//Place les réponses dans le désordre
	public String[] stockReponses() {
		String[] reponses= new String[4];
		Arrays.fill(reponses, null);
		bonneReponseIndex = (int)(Math.random()*Constantes.NB_REPONSES);
		System.out.println("reponse:"+bonneReponseIndex);
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
		return reponses;
	}
	//demander la réponse choisi qui est juste ou pas
	public boolean demander(int reponseChoix) {
		if(reponseChoix==bonneReponseIndex)
		{
			return true;
		}
		return false;
	}
	//retourner la question
	public String returnEnonce() {
		return this.enonce;
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
