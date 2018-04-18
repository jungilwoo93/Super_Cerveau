package fr.ensisa.supercerveau.model.util;

public abstract class Constantes {
	
	/**
	 * Nombre de questions dans une partie
	 */
	public static final int NB_QUESTIONS=10;
	public static final int NB_QUESTIONS_TOTAL=40;
	
	/**
	 * Nombre de réponses proposées pour chaque question
	 */
	public static final int NB_REPONSES=4;
	
	public static final int GEOGRAPHIE=0;
	public static final int HISTOIRE=1;
	public static final int SPORT=2;
	
	public static final String[] CATEGORIES = {"Géographie", "Histoire", "Sport","Maquillage","Dessin animée","Plats"};
	public static final int FACILE = 0;
	public static final int MOYEN = 1;
	public static final int DIFFICILE = 2;
}
