package tpAkka;

import java.util.ArrayList;
import java.util.HashMap;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * 
 * @author Tristan Camus
 * @author Sarah Wissocq
 *
 */
public class ArbreReparti {

	private final ActorSystem system1 = ActorSystem.create("MamanCrumbleLeRetour");
	private final ActorSystem system2 = ActorSystem.create("MamanCrumbleLeRetour");
	private HashMap<String, ActorRef> noeuds;

	/**
	 * Création de l'arbre dans le document de Car
	 */
	public ArbreReparti() {
		ArrayList<ActorRef> parent = new ArrayList<ActorRef>();
		ArrayList<ActorRef> gauche = new ArrayList<ActorRef>();
		ArrayList<ActorRef> droit = new ArrayList<ActorRef>();

		ActorRef noeud3 = system1.actorOf(Props.create(Noeud.class, "3", new ArrayList<ActorRef>()));
		ActorRef noeud4 = system2.actorOf(Props.create(Noeud.class, "4", new ArrayList<ActorRef>()));

		gauche.add(noeud3);
		gauche.add(noeud4);

		ActorRef noeud6 = system2.actorOf(Props.create(Noeud.class, "6", new ArrayList<ActorRef>()));

		droit.add(noeud6);

		ActorRef noeud2 = system1.actorOf(Props.create(Noeud.class, "2", gauche));
		ActorRef noeud5 = system2.actorOf(Props.create(Noeud.class, "5", droit));

		parent.add(noeud2);
		parent.add(noeud5);

		ActorRef noeud1 = system1.actorOf(Props.create(Noeud.class, "1", parent));

		noeuds = new HashMap<String, ActorRef>();
		noeuds.put("1", noeud1);
		noeuds.put("2", noeud2);
		noeuds.put("3", noeud3);
		noeuds.put("4", noeud4);
		noeuds.put("5", noeud5);
		noeuds.put("6", noeud6);
	}

	public void tell(String message) {
		this.tell("1", message);
	}

	public void tell(String id, String message) {
		noeuds.get(id).tell(message, ActorRef.noSender());
	}

}
