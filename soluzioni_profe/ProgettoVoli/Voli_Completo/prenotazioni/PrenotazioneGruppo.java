package prenotazioni;

import java.util.ArrayList;
import java.util.List;

public class PrenotazioneGruppo extends Prenotazione {

	private static final int SOGLIA_N_PERSONE = 10;
	private static final int SCONTO_MENO_10 = 10;
	private static final int SCONTO_ALMENO_10 = 20;

	private List<String> persone;

	private float costoPersona;

	/**
	 * Instantiates a new prenotazione gruppo.
	 *
	 * @param d
	 *            the d data delal prenotazione
	 * @param x
	 *            the x array delle persone
	 * @param c
	 *            the c costo per persona
	 */
	public PrenotazioneGruppo(String[] x, float c) {
		super();
		int n = x.length;
		persone = new ArrayList<>();
		for (int i = 0; i < n; i++)
			persone.add(x[i]);
		this.costoPersona = c;

	}

	public String toString() {
		String stringa = "GRUPPO ";
		for (String p : persone)
			stringa += " " + p;
		return stringa;
	}

	@Override
	// Calcolo il costo utilizzando le varie soglie
	public double getCosto() {
		int n = persone.size();
		if (n < SOGLIA_N_PERSONE)
			return costoPersona * n * (100 - SCONTO_MENO_10) / 100.0;
		else
			return costoPersona * n * (100 - SCONTO_ALMENO_10) / 100.0;
	}

	// Aggiunge una persona ad una prenotazione di gruppo
	public void addPersona(String persona) {
		persone.add(persona);
	}
}
