package fr.ensisa.supercerveau.model.questions;

import java.util.List;
import org.apache.jena.query.QuerySolution;
import fr.ensisa.supercerveau.model.util.Constantes;
import fr.ensisa.supercerveau.model.util.DBpediaQuery;

public class QuestionPeinture extends Question {

	public QuestionPeinture()
	{
		super(Constantes.PEINTURE);
		//Récupère toutes les noms et les images de peintures et les noms et les images d'autheurs,
		String requete = "select ?nomPeinture ?nomAutheur ?imgPeinture ?imgAutheur where { ?painting a <http://dbpedia.org/ontology/Painting>. "
				+ "?painting <http://dbpedia.org/ontology/author> ?autheur. "
				+ "?painting <http://dbpedia.org/ontology/thumbnail> ?imgPeinture. "
				+ "?painting <http://www.w3.org/2000/01/rdf-schema#label> ?nomPeinture."
				+ "?autheur <http://www.w3.org/2000/01/rdf-schema#label> ?nomAutheur."
				+ "?autheur <http://dbpedia.org/ontology/thumbnail> ?imgAutheur."
				+ "FILTER (lang(?nomPeinture) = 'fr'). "
				+ "FILTER (lang(?nomAutheur) = 'fr'). } ";
		List<QuerySolution> arts = DBpediaQuery.execRequete(requete);
		QuerySolution ligne = arts.get((int)(Math.random()*arts.size()));
		System.out.println(arts.size());

		if(Math.random()<0.5)
		{

			this.enonce = "L'autheur de \" "+ligne.getLiteral("?nomPeinture").getString()+" \" est : ";
			this.bonneReponse= ligne.getLiteral("?nomAutheur").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = arts.get((int)(Math.random()*arts.size()));
				if(reponseAbsente(ligne.getLiteral("?nomPeinture").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomAutheur").getString();
					index++;
				}
			}
		}
		else
		{

			this.enonce = "Quel est le peinture de "+ligne.getLiteral("?nomAutheur").getString()+" ?";
			this.bonneReponse= ligne.getLiteral("?nomPeinture").getString();

			int index=0;
			while(index<Constantes.NB_REPONSES-1)
			{
				ligne = arts.get((int)(Math.random()*arts.size()));
				if(!this.bonneReponse.equalsIgnoreCase(ligne.getLiteral("?nomPeinture").getString()))
				{
					this.mauvaisesReponses[index]=ligne.getLiteral("?nomPeinture").getString();
					index++;
				}
			}
		}


	}

}
