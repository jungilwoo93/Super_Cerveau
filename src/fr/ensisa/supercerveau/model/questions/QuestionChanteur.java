package fr.ensisa.supercerveau.model.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.DBpediaQuery;

public class QuestionChanteur extends Question {

	public QuestionChanteur()
	{
		super(Constantes.CHANTEUR);
		//Récupère toutes les nom et l'images de chanteurs
		String requete = "select ?nom (str(?imgg) as ?img) where { "
				+ "?chanteur a <http://dbpedia.org/ontology/Singer>. "
				+ "?chanteur <http://dbpedia.org/ontology/thumbnail> ?imgg. "
				+ "?chanteur <http://www.w3.org/2000/01/rdf-schema#label> ?nom. "
				+ "FILTER (lang(?nom) = 'fr')}";
		List<QuerySolution> acteurs = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = acteurs.get((int)(Math.random()*acteurs.size()));

		this.enonce = " Qui est ce ?";
		this.img = ligne.getLiteral("?img").getString() ;
		this.bonneReponse= ligne.getLiteral("?nom").getString();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = acteurs.get((int)(Math.random()*acteurs.size()));
			if(reponseAbsente(ligne.getLiteral("?nom").getString()))
			{
				this.mauvaisesReponses[index]=ligne.getLiteral("?nom").getString();
				index++;
			}
		}
	}

}