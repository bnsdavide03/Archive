package telecamera;

import telecamera.eccezioni.OperazioneNonSupportata;
import telecamera.stato.StatoTelecamera;

public class Allarme extends Telecamera {

	private final String n_telefono;
	
	public Allarme(String descrizione,String n_telefono) {
		super(descrizione);
		if(n_telefono==null) {
			throw new NullPointerException("Il numero di telefono "
					+ "non puo essere null");
		}
		this.n_telefono=n_telefono;
	}
	
	public Allarme(String descrizione,float posizione,String n_telefono){
		super(descrizione,posizione);
		if(n_telefono==null) {
			throw new NullPointerException("Il numero di telefono "
					+ "non puo essere null");
		}
		this.n_telefono=n_telefono;
		
	}

	/**
	 * @return the n_telefono
	 */
	public String getN_telefono() {
		return n_telefono;
	}

	public void spegni() throws OperazioneNonSupportata {
		throw new OperazioneNonSupportata("Non posso spegnere"
				+ "un allarme");
	}

	
}
