package tpAkka;

import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

/**
 * Classe représentant un noeud de l'arbre.
 * 
 * @author Tristan Camus
 * @author Sarah Wissocq
 *
 */
public class Noeud extends UntypedActor {

	private String nom;
	private List<ActorRef> enfants;

	public Noeud(String nom, List<ActorRef> enfants) {
		this.nom = nom;
		this.enfants = enfants;
	}

	/**
	 * Lorsqu'un noeud reçoit un message, il le propage a tous ses enfants
	 * 
	 * http://doc.akka.io/docs/akka/2.4.2/java/untyped-actors.html#receive-
	 * messages
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			// Recuperer le message
			String m = (String) message;
			// trace permettant de visualiser la propagation (Q2)
			System.out.println("Message : " + message + " recu, signé : " + this.nom);
			for (ActorRef noeud : enfants) {
				// transferer le message
				// doc sur forward juste avant receive message
				noeud.forward(m, getContext());
			}
		}
	}

}
