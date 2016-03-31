CAR TP3 : AKKA

Tristan Camus
Sarah Wissocq

Pour lancer, il faut executer la classe Main.

L'arbre du TP est créé dans la classe Arbre.
L'arbre réparti sur plusieur systems est créé dans la classe ArbreReparti. 
Le graphe de la question 5 est crée dans la classe Graphe.

Pour tester le TP, il suffit de décommenter le code du main qui correspond à la question à tester.

On peut préciser l'acteur à partir duquel on souhaite propagé le message à la création d'un Arbre, ArbreReparti ou d'un Graphe.

Nous avons respecté la forme de l'arbre du TP.
Pour l'arbre réparti, il est réparti sur 2 système. Les acteurs 1, 2 et 3 sont sur le premier système. Les acteurs 4, 5 et 6 sont sur le deuxième.
Pour le graphe, nous avons, comme dans le TP, créé un arc entre l'acteur 4 et l'acteur 6.
Notre graphe est non orienté (Un lien va de 4 à 6 et un autre de 6 à 4).

Pour la partie sur les graphes, nous avons fait en sorte qu'un noeud n'envoie pas deux fois le même message. Cependant, un noeud peut recevoir plusieur fois le même message de la part de noeuds différents. Le comportement n'étant pas précisé dans le TP, nous avons opté pour appliqué la logique vu dans les précédents TD.

Les tests n’ont pas pu être mis en place avec JUnit, mais nous avons rédigé les scénarios de test suivant:

Pour les arbres :
	- vérifier que le message est bien envoyer à tous les fils.
	- vérifier que le message est bien envoyer par le bon père.

Pour le graphe :
	- vérifier que le message est envoyé qu'une fois par noeud.