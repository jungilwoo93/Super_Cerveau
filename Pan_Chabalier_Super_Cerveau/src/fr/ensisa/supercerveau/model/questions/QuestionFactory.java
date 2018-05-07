package fr.ensisa.supercerveau.model.questions;

import java.util.Random;

public class QuestionFactory {

	public static Question createQuestion(int categorie)
	{
		// si on choisit le sujet mélange
		if(categorie==10) {
			Random i = new Random();
			categorie=i.nextInt(8);
		}
		Question question;
		switch(categorie)
		{
			case 0 : question = new QuestionFilm();
			break;
			case 1 : question = new QuestionCapitale();
			break;
			case 2 : question = new QuestionRoiEtPredecesseur();
			break;
			case 3 : question= new QuestionChampionnatFranceFootball();
			break;
			case 4 : question = new QuestionDessinAnimee();
			break;
			case 5 : question = new QuestionPeinture();
			break;
			case 6 : question = new QuestionRaceChien();
			break;
			case 7 : question = new QuestionActor();
			break;
			case 8: question = new QuestionChanteur();
			break;			
			default:question = new QuestionActor();
			break;
		}
		return question;
	}
	
}
