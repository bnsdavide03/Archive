package Telecamere;

import Eccezioni.IncorrectState;
import Eccezioni.PosizioneScorretta;

public class TelecameraSemplice extends Telecamere {

	private String descrizioneCostruttore;
	
	public TelecameraSemplice(String descrizione, Double posizione, String descrizioneCostruttore) throws PosizioneScorretta {
		super(descrizione, posizione);
		this.descrizioneCostruttore = descrizioneCostruttore;
	}

	public void setStato(StatoTelecamere stato) throws IncorrectState {
		
		if (stato.equals(StatoTelecamere.BLOCCOIMMAGINE)) {
			
			throw new IncorrectState ("Stato scorretto");
			
		} else {
			super.stato = stato;
		}
		
	}
}
