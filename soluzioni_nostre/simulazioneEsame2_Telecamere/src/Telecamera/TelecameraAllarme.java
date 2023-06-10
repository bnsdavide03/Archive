package Telecamera;

import Exception.PositionIncorrectException;
import Exception.StatoIncorrectException;

public class TelecameraAllarme extends Telecamera{
	
	private StatoTelecamera stato;
	private final Integer numeroTelefono;
	
	public TelecameraAllarme(String descrizione, Double position, StatoTelecamera stato, Integer numeroTelefono) throws StatoIncorrectException, NullPointerException, PositionIncorrectException {
		super(descrizione, position);
		
		if (stato.equals("SPEGNI")) {
			throw new StatoIncorrectException ("non è possibile inserire lo stato");
		} else {
			
			this.stato = stato;
			this.numeroTelefono = numeroTelefono;
		}
	}

	public StatoTelecamera getStato() {
		return stato;
	}

	@Override
	public void setStato(StatoTelecamera stato) throws StatoIncorrectException {
		
		if (stato.equals("SPEGNI")) {
			throw new StatoIncorrectException ("non è possibile inserire lo stato");
		} else {
			this.stato = stato;
		}
	}

	public Integer getNumeroTelefono() {
		return numeroTelefono;
	}
	
	

}
