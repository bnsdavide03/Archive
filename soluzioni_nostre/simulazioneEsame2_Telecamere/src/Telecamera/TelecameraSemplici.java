package Telecamera;

import Exception.PositionIncorrectException;
import Exception.StatoIncorrectException;

public class TelecameraSemplici extends Telecamera {

	private StatoTelecamera stato;
	private final String descrizioneCostruttore;

	public TelecameraSemplici(String descrizione, Double position, StatoTelecamera stato,String descrizioneCostruttore) throws StatoIncorrectException, NullPointerException, PositionIncorrectException{
		super(descrizione, position);
		
		if (stato.equals("BLOCCAIMMAGINE")) {
			throw new StatoIncorrectException ("non è possibile bloccare l'immagine della telecamera semplice");
		} else {
			
			this.stato = stato;
			this.descrizioneCostruttore = descrizioneCostruttore;
		}
		
	}
	
	public StatoTelecamera getStato() {
		return stato;
	}

	
	@Override
	public void setStato(StatoTelecamera stato) throws StatoIncorrectException {
		
		if (stato.equals("BLOCCAIMMAGINE")) {
			throw new StatoIncorrectException ("non è possibile bloccare l'immagine della telecamera semplice");
		} else {
			
			this.stato = stato;
		}	
	}

	public String getDescrizioneCostruttore() {
		return descrizioneCostruttore;
	}
	
}
