package fr.ensisa.supercerveau.model.util;

public abstract class Constantes {
	
	/**
	 * Nombre de questions dans une partie
	 */
	public static final int NB_QUESTIONS=10;
	public static final int NB_QUESTIONS_TOTAL=100;
	
	
	/**
	 * Nombre de point avant la victoire
	 */
	public static final int SCORE_WIN = 2;
	
	/**
	 * Nombre de réponses proposées pour chaque question
	 */
	public static final int NB_REPONSES=4;
	
	public static final int FILM = 0;
	public static final int GEOGRAPHIE=1;
	public static final int HISTOIRE=2;
	public static final int SPORT=3;
	public static final int ANIMEE=4;
	public static final int PEINTURE=5;
	public static final int CHIEN=6;
	public static final int ACTEUR=7;
	public static final int CHANTEUR=8;
	public static final int HAZARD=9;
	public static final int MELANGE=10;
	
	//les sujets
	public static final String[] CATEGORIES = {"Film","Géographie","Histoire", "Sport","Dessin animée","Peinture","Race de Chien","Acteur","Chanteur","Au hazard","Sujets mélangés"};
	
	//le complexité des jeux
	public static final int FACILE = 0;
	public static final int MOYEN = 1;
	public static final int DIFFICILE = 2;
}
