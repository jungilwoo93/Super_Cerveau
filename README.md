
Super Cerveau
===========
<center>
![logo](https://git.chabalier.com/snowert/Super_Cerveau/raw/6f839a73ce49dc5f58349beaf7c44f5393b8d252/src/fr/ensisa/supercerveau/model/image/logo.png)
</center>

PAN Liuyan , CHABALIER Andy
-----------

<u>**Projet Logique et web semantique ENSISA 2A IR**</u>

Repository git du projet: [https://git.chabalier.com/snowert/Super_Cerveau)

<u>I) Objectifs:</u>
----------

- Réaliser une interface graphique pour le quizz
- Interroger et apprendre à utiliser DBpedia en SPARQL depuis l’API Apache Jena
- Réaliser un quizz automatique qui construit des questions par le biais de requètes DBpedia
- Proposer des questions avec un support visuel (Reconnaitre une image ect.)

<u>II) Fonctionnement:</u>
----------
Réalisé en Swing, l'interface est composé de deux fenètres, la [fenètre principale](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/application/MainPrincipe.java) donne le choix du nombre de joueur
et des différents sujets, puis lance la [fenètre de jeu](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/application/WindowsPlayer.java).
Apres le demarrage de la partie, la gestion des questions est effectuée par la classe [PanelQuestion](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/application/PanelQuestion.java)

Lorsque un joueur atteint 10 points (Definit dans [Constantes](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/model/util/Constantes.java)) le jeu
s'arrete et propose de continuer pour atteindre un meilleur score ou s'arreter.

Lors du lancement du programme, on initialise l'affichage et on attend la saisie des joueurs et des questions pour laisser [WindowsPlayer](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/application/WindowsPlayer.java)
prendre le relai. [WindowsPlayer](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/application/WindowsPlayer.java) initialise l'interface de jeu. lorsque l'utilisateur lance la partie, [PanelQuestion](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/application/PanelQuestion.java) utilise la
[QuestionFactory](https://git.chabalier.com/snowert/Super_Cerveau/src/master/src/fr/ensisa/supercerveau/model/questions/QuestionFactory.java) pour créer les questions et réponses et permettre leur affichage

La factory initialise la nouvelle question. Celle ci interroge DBpedia par le biais d'une requète et récupère les informations nécessaire.

Lors d'une partie multijoueur, chaque joueur repond a une question differente, s'affrontant sur le score ou la gloire.

<u>III) Categories de Questions:</u>
----------
- Acteur
- Capitale
- Championnat de football
- Chanteur
- Dessin Animé
- Film
- Peinture
- Race de chien
- Roi et predecesseur

- Melange (chaque question est tiré aléatoirement parmis les catégories de questions)

<u>IV) Requètes:</u>
----------

		select ?nom ?comment where {
			?animee a <http://dbpedia.org/ontology/Cartoon>.
			?animee <http://www.w3.org/2000/01/rdf-schema#label> ?nom.
			?animee <http://www.w3.org/2000/01/rdf-schema#comment> ?comment
			FILTER (lang(?nom) = 'fr')
			FILTER (lang(?comment) = 'fr')
		}

		
		select ?nomPeinture ?nomAutheur ?imgPeinture ?imgAutheur where { ?painting a <http://dbpedia.org/ontology/Painting>.
			?painting <http://dbpedia.org/ontology/author> ?autheur.
			?painting <http://dbpedia.org/ontology/thumbnail> ?imgPeinture.
			?painting <http://www.w3.org/2000/01/rdf-schema#label> ?nomPeinture.
			?autheur <http://www.w3.org/2000/01/rdf-schema#label> ?nomAutheur.
			?autheur <http://dbpedia.org/ontology/thumbnail> ?imgAutheur.
			FILTER (lang(?nomPeinture) = 'fr').
			FILTER (lang(?nomAutheur) = 'fr').
		}

		
		select ?nom (str(?imgg) as ?img) where {
			<http://fr.dbpedia.org/resource/Liste_des_races_de_chiens> <http://dbpedia.org/ontology/wikiPageWikiLink> ?chien.
			?chien <http://www.w3.org/2000/01/rdf-schema#label> ?nom.
			?chien <http://dbpedia.org/ontology/thumbnail> ?imgg.
			FILTER (lang(?nom)='fr').
		}

		
		select ?nom ?type where {
			?films a <http://dbpedia.org/ontology/Film>.
			?films <http://www.w3.org/2000/01/rdf-schema#label> ?nom.
			?films <http://dbpedia.org/ontology/genre> ?genre.
			?genre <http://www.w3.org/2000/01/rdf-schema#label> ?type.
			FILTER (lang(?nom) = 'fr')
			FILTER (lang(?type) = 'fr')
		}

		
		select ?nom (str(?imgg) as ?img) where {
			?acteur a <http://dbpedia.org/ontology/Actor>.
			?acteur <http://dbpedia.org/ontology/thumbnail> ?imgg.
			?acteur <http://www.w3.org/2000/01/rdf-schema#label> ?nom.
			FILTER (lang(?nom) = 'fr').
		}

		
		select ?nom (str(?imgg) as ?img) where {
			?acteur a <http://dbpedia.org/ontology/Singer>.
			?acteur <http://dbpedia.org/ontology/thumbnail> ?imgg.
			?acteur <http://www.w3.org/2000/01/rdf-schema#label> ?nom.
			FILTER (lang(?nom) = 'fr').
		}