package fr.ensisa.supercerveau.model.questions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.jena.query.QuerySolution;

import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.DBpediaQuery;

public class QuestionDessinAnimee extends Question {

	public QuestionDessinAnimee()
	{
		super(Constantes.ANIMEE);
		//Récupère toutes les dessins animée
		String requete = "select ?nomAnimee ?description where {?animee a <http://dbpedia.org/ontology/Cartoon>. "
				+ "?animee <http://www.w3.org/2000/01/rdf-schema#label> ?nomAnimee. "
				+ "?animee <http://www.w3.org/2000/01/rdf-schema#comment> ?description. "
				+ "FILTER (lang(?nomAnimee) = 'fr'). "
				+ "FILTER (lang(?description) = 'fr'). } ";
		List<QuerySolution> animees = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = animees.get((int)(Math.random()*animees.size()));

		if(Math.random()<0.5)
		{

			this.enonce = ligne.getLiteral("?nomAnimee").getString();
			String descriptionCourte = ligne.getLiteral("?description").getString();
			Pattern pattern = Pattern.compile("(est.*\\.)");
	        Matcher matcher = pattern.matcher(descriptionCourte);
	        matcher.find();
	        this.bonneReponse = matcher.group();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = animees.get((int)(Math.random()*animees.size()));
				if(reponseAbsente(ligne.getLiteral("?nomAnimee").getString()))
				{
					descriptionCourte = ligne.getLiteral("?description").getString();
			        matcher = pattern.matcher(descriptionCourte);
			        matcher.find();
			        this.mauvaisesReponses[index]=matcher.group();
					index++;
				}
			}
		}
		else
		{
			String descriptionCourte = ligne.getLiteral("?description").getString();
			Pattern pattern = Pattern.compile("(est.*\\.)");
	        Matcher matcher = pattern.matcher(descriptionCourte);
	        matcher.find();
			this.enonce = "Quel "+matcher.group()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomAnimee").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = animees.get((int)(Math.random()*animees.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomAnimee").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomAnimee").getString();
					index++;
				}
			}
		}


	}

}
