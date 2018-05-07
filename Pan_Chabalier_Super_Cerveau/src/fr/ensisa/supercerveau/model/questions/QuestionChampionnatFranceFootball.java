package fr.ensisa.supercerveau.model.questions;

import java.util.List;

import org.apache.jena.query.QuerySolution;

import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.DBpediaQuery;

public class QuestionChampionnatFranceFootball extends Question {

	public QuestionChampionnatFranceFootball()
	{
		super(Constantes.SPORT);
		//Récupère tous les championnats de France de Football (Pro Division 1)
		String requete = "select ?nomTournoi ?nomVainqueur where {<http://fr.dbpedia.org/resource/Championnat_de_France_de_football_1932-1933> <http://dbpedia.org/ontology/followingEvent>* ?tournoi. "
																		+ "?tournoi <http://dbpedia.org/ontology/soccerLeagueWinner> ?vainqueur. "
																		+ "?tournoi <http://www.w3.org/2000/01/rdf-schema#label> ?nomTournoi. "
																		+ "?vainqueur <http://www.w3.org/2000/01/rdf-schema#label> ?nomVainqueur. "
																		+ "FILTER(lang(?nomTournoi)='fr'). "
																		+ "FILTER(lang(?nomVainqueur)='fr'). } ";
		//String requete = "select distinct ?tournoi where {<http://fr.dbpedia.org/resource/Championnat_de_France_de_football_1932-1933> <http://dbpedia.org/ontology/followingEvent>* ?tournoi. }";
		List<QuerySolution> chamionnats = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = chamionnats.get((int)(Math.random()*chamionnats.size()));



			this.enonce = "Quelle équipe a gagné le "+ligne.getLiteral("?nomTournoi").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomVainqueur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = chamionnats.get((int)(Math.random()*chamionnats.size()));
				if(reponseAbsente(ligne.getLiteral("?nomVainqueur").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomVainqueur").getString();
					index++;
				}
			}
		


	}

}
