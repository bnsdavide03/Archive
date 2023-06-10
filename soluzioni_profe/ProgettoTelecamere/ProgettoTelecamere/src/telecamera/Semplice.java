package telecamera;

import telecamera.eccezioni.OperazioneNonSupportata;

public class Semplice extends Telecamera {

	public Semplice(String descrizione) {
		super(descrizione);
	}
	
	public Semplice(String descrizione, float posizione) {
		super(descrizione,posizione);
	}
	
	public void bloccaImmagine() throws OperazioneNonSupportata {
		throw new OperazioneNonSupportata("Non posso bloccare immagine"
				+ "un allarme");
	}

}
