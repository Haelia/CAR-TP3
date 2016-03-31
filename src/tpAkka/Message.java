package tpAkka;

public class Message {
	private int identifiant;
	private String text;

	public Message(String msg, String envoyeur, int identifiant) {
		this.identifiant = identifiant;
		this.text = msg;
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public String getText() {
		return text;
	}
}
