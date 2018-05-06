package fr.ensisa.supercerveau.model.questions;

public class QuestionFactory {

	public static Question createQuestion(int categorie)
	{
		//int questionType= (int)(Math.random()*3);
		Question question;
		switch(categorie)
		{
			case 0 : question = new QuestionFilm();
			break;
			case 7: question = new QuestionArtist();
			break;
			case 6 :	question =new QuestionRaceChien();
			break;
			/*case 0 : 	question=new QuestionCapitale();
						break;
			case 1 :	question=new QuestionRoiEtPredecesseur();
			break;	*/		
			default : 	question= new QuestionChampionnatFranceFootball();
						break;
		}
		return question;
	}
	
}
