package fr.ensisa.supercerveau.model.questions;

public class QuestionFactory {

	public static Question createQuestion(int categorie)
	{
		//int questionType= (int)(Math.random()*3);
		Question question;
		switch(categorie)
		{
			case 0 :	question =new QuestionPeinture();
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
