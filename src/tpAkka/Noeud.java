package tpAkka;

import java.util.ArrayList;
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
	private List<Integer> messages;

	public Noeud(String nom, List<ActorRef> enfants) {
		this.nom = nom;
		this.enfants = enfants;
		this.messages = new ArrayList<Integer>();
	}

	public String getNom() {
		return this.nom;
	}

	/**
	 * Lorsqu'un noeud reçoit un message, il le propage a tous ses enfants
	 * 
	 * http://doc.akka.io/docs/akka/2.4.2/java/untyped-actors.html#receive-
	 * messages
	 */
	@Override
	public void onReceive(Object message) throws Exception {
		Message msg = null;
		boolean envoyer = false;

		if (message instanceof Message) {
			// Recuperer le message
			msg = (Message) message;

			// trace permettant de visualiser la propagation (Q2)
			System.out.println("Message : " + msg.getText() + ", reçu par : " + this.nom);

			if (!messages.contains(msg.getIdentifiant())) {
				messages.add(msg.getIdentifiant());
				envoyer = true;
			}
		} else if (message instanceof String) {
			String text = (String) message;
			msg = new Message(text, this.nom, 1);

			if (!messages.contains(msg.getIdentifiant())) {
				messages.add(msg.getIdentifiant());
				envoyer = true;
			}
		}

		if (envoyer) {
			for (ActorRef noeud : enfants) {
				// transferer le message
				// doc sur forward juste avant receive message
				noeud.forward(msg, getContext());
			}
		}
	}

}
