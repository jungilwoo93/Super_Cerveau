package fr.ensisa.supercerveau.model.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.DBpediaQuery;

public class QuestionFilm extends Question {

	public QuestionFilm()
	{
		super(Constantes.FILM);
		//Récupère toutes les films et le type de film
		String requete = "select ?nom ?type where { ?films a <http://dbpedia.org/ontology/Film>. "
				+ "?films <http://www.w3.org/2000/01/rdf-schema#label> ?nom. "
				+ "?films <http://dbpedia.org/ontology/genre> ?genre. "
				+ "?genre <http://www.w3.org/2000/01/rdf-schema#label> ?type. "
				+ "FILTER (lang(?nom) = 'fr') "
				+ "FILTER (lang(?type) = 'fr')}";
		List<QuerySolution> films = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = films.get((int)(Math.random()*films.size()));



			this.enonce = "Quelle est le type de film \""+ligne.getLiteral("?nom").getString()+"\" ?";
			this.bonneReponse= ligne.getLiteral("?type").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = films.get((int)(Math.random()*films.size()));
				if(reponseAbsente(ligne.getLiteral("?type").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?type").getString();
					index++;
				}
			}


	}

}