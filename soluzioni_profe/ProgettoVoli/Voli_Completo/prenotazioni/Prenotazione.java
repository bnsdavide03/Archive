package prenotazioni;

import java.util.Random;

public abstract class Prenotazione implements Comparable<Prenotazione> {
	// ID univoco di 6 caratteri e numeri mischiati (tipo EH79HI).
	protected String ID;

	private static Random rnd = new Random();

	// La prenotazione di default genera un ID di prenotazione random
	protected Prenotazione() {
		// first two ints
		this.ID = "" + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10);
		// next two chars
		this.ID += (char) (rnd.nextInt(26) + 'A') + (char) (rnd.nextInt(26) + 'A');
		this.ID += ("" + rnd.nextInt(10) + rnd.nextInt(10));
	}

	@Override
	public int compareTo(Prenotazione x) {
		return this.ID.compareTo(x.ID);
	}

	// Get ID
	public String getID() {
		return ID;
	}

	// Il metodo per il calcolo del costo è astratto poichè ogni tipo di
	// prenotazione avrà un costo differente
	public abstract double getCosto();
}
