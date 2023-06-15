package Telecamere;

import Eccezioni.IncorrectState;
import Eccezioni.PosizioneScorretta;

public class TelecameraAllarme extends Telecamere {

	private final Integer numeroTelefono;
	
	public TelecameraAllarme(String descrizione, Double posizione, Integer numeroTelefono) throws PosizioneScorretta {
		super(descrizione, posizione);
		this.numeroTelefono = numeroTelefono;
	}

	@Override
	public void setStato(StatoTelecamere stato) throws IncorrectState {
	
		if (stato.equals(StatoTelecamere.ACCESO)) {
			
			throw new IncorrectState ("Stato scorretto");
			
		} else {
			super.stato = stato;
		}
		
	}
}
