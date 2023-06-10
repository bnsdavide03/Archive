package telecamera;

import telecamera.eccezioni.OperazioneNonSupportata;
import telecamera.stato.StatoTelecamera;

public abstract class Telecamera implements Comparable<Telecamera>{
	private static int ID_COUNTER=0;
	
	private final int id;
	private final String descrizione;
	private float posizione;
	private StatoTelecamera stato;
	
	private Telecamera telecameraPrecente;
	private Telecamera telecameraSuccessiva;
	
	/**
	 * crea una nuova telecamera, e la mette in posizione -1
	 * inizialmente la telecamera e' assunta accesa.
	 * @param descrizione la descrizione della telecamera
	 * 
	 * @throws NullPointerException se la descrizione e' nulla
	 */
	public Telecamera(String descrizione) {
		if(descrizione==null) {
			throw new NullPointerException("La "
					+ "descrizione non puo essere nulla");
		}
		this.id=ID_COUNTER++;
		this.descrizione=descrizione;
		this.posizione=-1;
		this.stato=StatoTelecamera.ACCESA;
	}
	
	public Telecamera(String descrizione, float posizione) {
		this(descrizione);
		if(posizione<0 || posizione >200) {
			throw new IllegalArgumentException("La posizione deve"
					+ "stare tra 0 e 200");
		}
		this.setPosizione(posizione);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @return the posizione
	 */
	public float getPosizione() {
		return posizione;
	}

	/**
	 * @param posizione the posizione to set
	 */
	public void setPosizione(float posizione) {
		if(posizione<0 || posizione >200) {
			throw new IllegalArgumentException("La posizione"+posizione+" deve"
					+ "stare tra 0 e 200");
		}
		this.posizione = posizione;
	}

	/**
	 * @return the stato
	 */
	public StatoTelecamera getStato() {
		return stato;
	}

	public void accendi() {
		this.stato=StatoTelecamera.ACCESA;
		
	}
	
	public void spegni() throws OperazioneNonSupportata {
		this.stato=StatoTelecamera.SPENTA;
	}
	
	public void bloccaImmagine() throws OperazioneNonSupportata {
		this.stato=StatoTelecamera.BLOCCO_IMMAGINE;
		
	}

	@Override
	public String toString() {
		return "Telecamera [id=" + id + ", descrizione=" + descrizione + ", posizione=" + posizione + ", stato=" + stato
				+ "]";
	}
	
	@Override
	public int compareTo(Telecamera o) {
		if(this.posizione>o.posizione) {
			return 1;
		}else {
			if(this.posizione==o.posizione)
				return 0;
			else
				return -1;
		}
	}
	
}
