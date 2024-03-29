package prenotazioni;

public class PrenotazioneIndividuale extends Prenotazione {

	String cognome, nome;

	PreferenzaPosto preferenza;

	final float costo;

	// Permettiamo l'inserimento di prenotazioni individuali utilizzando sia
	// l'enumerativo...
	public PrenotazioneIndividuale(String c, String n, PreferenzaPosto pref, float costo) {
		super();
		this.cognome = c;
		this.nome = n;
		this.preferenza = pref;
		this.costo = costo;
	}

	// ... che la corrispondente versione stringa
	public PrenotazioneIndividuale(String c, String n, String posto, float costo) {
		this(c, n, PreferenzaPosto.valueOf(posto), costo);
	}

	public String toString() {
		return "PINDIV " + cognome + " " + nome + " preferenza: " + preferenza;
	}

	@Override
	public double getCosto() {
		return costo;
	}
}
