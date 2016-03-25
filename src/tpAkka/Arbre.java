package tpAkka;

import java.util.ArrayList;
import java.util.HashMap;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * 
 * @author Tristan Camus
 * @author Sarah Wissocq
 *
 */
public class Arbre extends UntypedActor {

	private final ActorSystem system = ActorSystem.create("MamanCrumbleLeRetour");
	private HashMap<String, ActorRef> noeuds;

	/**
	 * http://doc.akka.io/docs/akka/2.4.2/java/untyped-actors.html#creating-actors
	 * -> Creating Actors with Props:
	 * 
	 * final ActorRef myActor =
	 * system.actorOf(Props.create(MyUntypedActor.class), "myactor");
	 */

	/**
	 * Création de l'arbre dans le document de Car
	 */
	public Arbre() {
		this.noeuds = new HashMap<String, ActorRef>();
		noeuds.put("3", system.actorOf(Props.create(Noeud.class, "3", new ArrayList<ActorRef>())));
		noeuds.put("4", system.actorOf(Props.create(Noeud.class, "4", new ArrayList<ActorRef>())));
		noeuds.put("6", system.actorOf(Props.create(Noeud.class, "6", new ArrayList<ActorRef>())));
		ArrayList<ActorRef> gauche = new ArrayList<ActorRef>();
		gauche.add(noeuds.get("3"));
		gauche.add(noeuds.get("4"));
		ArrayList<ActorRef> droit = new ArrayList<ActorRef>();
		droit.add(noeuds.get("6"));
		noeuds.put("2", system.actorOf(Props.create(Noeud.class, "2", gauche)));
		noeuds.put("5", system.actorOf(Props.create(Noeud.class, "5", droit)));
		ArrayList<ActorRef> parent = new ArrayList<ActorRef>();
		parent.add(noeuds.get("2"));
		parent.add(noeuds.get("5"));
		noeuds.put("1", system.actorOf(Props.create(Noeud.class, "1", parent)));
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			// Recuperer le message
			String m = (String) message;
			// trace permettant de visualiser la propagation (Q2)
			System.out.println("Message : " + message + ", signé : Arbre");
			noeuds.get("1").forward(m, getContext());

		}
	}
	
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("MamanCrumbleLeRetour");
		ActorRef arbre = system.actorOf(Props.create(Arbre.class));
		arbre.tell("bébéCrumble", null);
	}

}
