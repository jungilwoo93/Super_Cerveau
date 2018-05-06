package fr.ensisa.supercerveau.model.questions;

import java.util.List;
import org.apache.jena.query.QuerySolution;
import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.DBpediaQuery;

public class QuestionRaceChien extends Question {

	public QuestionRaceChien()
	{
		super(Constantes.CHIEN);
		//Récupère les images de chien et les noms de race
		String requete = "select ?nom (str(?imgg) as ?img) where { "
				+ "<http://fr.dbpedia.org/resource/Liste_des_races_de_chiens> <http://dbpedia.org/ontology/wikiPageWikiLink> ?chien. "
				+ "?chien <http://www.w3.org/2000/01/rdf-schema#label> ?nom. "
				+ "?chien <http://dbpedia.org/ontology/thumbnail> ?imgg."
				+ "FILTER (lang(?nom) = 'fr'). }";
		List<QuerySolution> chiens = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = chiens.get((int)(Math.random()*chiens.size()));

		this.enonce = "Quelle est la race de ce chien ?";
		this.img = ligne.getLiteral("?img").getString();
		this.bonneReponse= ligne.getLiteral("?nom").getString();

		int index=0;
		while(index<Constantes.NB_REPONSES-1)
		{
			ligne = chiens.get((int)(Math.random()*chiens.size()));
			if(reponseAbsente(ligne.getLiteral("?nom").getString()))
			{
				this.mauvaisesReponses[index]=ligne.getLiteral("?nom").getString();
				index++;
			}
		}


	}

}
