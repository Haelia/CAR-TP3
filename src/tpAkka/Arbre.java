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
public class Arbre {

	private final ActorSystem system = ActorSystem.create("MamanCrumbleLeRetour");
	private HashMap<String, ActorRef> noeuds;

	/**
	 * http://doc.akka.io/docs/akka/2.4.2/java/untyped-actors.html#creating-
	 * actors -> Creating Actors with Props:
	 * 
	 * final ActorRef myActor =
	 * system.actorOf(Props.create(MyUntypedActor.class), "myactor");
	 */

	/**
	 * Création de l'arbre dans le document de Car
	 */
	public Arbre() {
		ArrayList<ActorRef> parent = new ArrayList<ActorRef>();
		ArrayList<ActorRef> gauche = new ArrayList<ActorRef>();
		ArrayList<ActorRef> droit = new ArrayList<ActorRef>();		
		
		ActorRef noeud3 = system.actorOf(Props.create(Noeud.class, "3", new ArrayList<ActorRef>()));
		ActorRef noeud4 = system.actorOf(Props.create(Noeud.class, "4", new ArrayList<ActorRef>()));
		
		gauche.add(noeud3);
		gauche.add(noeud4);
		
		ActorRef noeud6 = system.actorOf(Props.create(Noeud.class, "6", new ArrayList<ActorRef>()));
		
		droit.add(noeud6);
		
		ActorRef noeud2 = system.actorOf(Props.create(Noeud.class, "2", gauche));
		ActorRef noeud5 = system.actorOf(Props.create(Noeud.class, "5", droit));
		
		parent.add(noeud2);
		parent.add(noeud5);
		
		ActorRef noeud1 = system.actorOf(Props.create(Noeud.class, "1", parent));
		
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
		System.out.println("Message : " + message + ", émis par : " + id);
		noeuds.get(id).tell(message, ActorRef.noSender());
	}

}
