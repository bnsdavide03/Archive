package Telecamere;

import Eccezioni.IncorrectState;
import Eccezioni.PosizioneScorretta;

public abstract class Telecamere implements Comparable<Telecamere> {

	private final int id;
	private static int IDCounter;
	private String descrizione;
	private Double posizione;
	protected StatoTelecamere stato;

	public Telecamere(String descrizione, Double posizione) throws PosizioneScorretta {

		if (descrizione == null) {

			throw new NullPointerException("la descrizione non può essere vuota");

		} else {

			this.descrizione = descrizione;
			this.posizione = posizione;

			if (posizione < 0 || posizione > 200) {

				throw new PosizioneScorretta ("la posizione non è nel range predisposto");

			} else {

				this.stato = StatoTelecamere.ACCESO;
				this.id = IDCounter++;

			}
		}
	}

	public abstract void setStato(StatoTelecamere stato) throws IncorrectState;

	public int getId() {
		return id;
	}

	
	public Double getPosizione() {
		return posizione;
	}

	
	public int compareTo(Telecamere o) {
		return Double.compare(posizione, o.posizione);
	}

	@Override
	public String toString() {
		return "Telecamere [id=" + id + ", descrizione=" + descrizione + ", posizione=" + posizione + ", stato=" + stato
				+ "]";
	}

	public StatoTelecamere getStato() {
		return stato;
	}	
	
}
