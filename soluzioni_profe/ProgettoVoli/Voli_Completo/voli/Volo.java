package voli;

import java.util.ArrayList;
import java.util.List;

import eccezioni.AeroportoNonCorrettoException;
import eccezioni.ExistIDException;
import eccezioni.PrenotazioneEsistenteException;
import prenotazioni.Prenotazione;
import prog.utili.Data;
import prog.utili.Orario;
import prog.utili.SequenzaOrdinata;

/**
 * Classe che rappresenta un volo
 */
public class Volo implements Comparable<Volo> {
	private String origine, destinazione;
	Data dataPartenza;
	Orario orarioPartenza;
	final int IDvolo, nMax;

	SequenzaOrdinata<Prenotazione> prenotazioni;

	private static List<Integer> IDusati = new ArrayList<>();

	private static int contatoreVoli = 0;

	/**
	 * 
	 * @param o  origine
	 * @param de destinazione
	 * @param da data del volo
	 * @param orarioPartenza orario del volo
	 * @param n  numero di passeggeri
	 * @throws ExistIDException
	 */
	public Volo(String o, String de, Data da, Orario orarioPartenza, int n) throws ExistIDException {
		this(o, de, da, n, orarioPartenza, ++contatoreVoli);
	}

	/**
	 * 
	 * @param o  origine
	 * @param de destinazione
	 * @param da data del volo
	 * @param orarioPartenza orario del volo
	 * @param n  numero di passeggeri
	 * @param ID id del volo
	 * @throws ExistIDException
	 */
	public Volo(String o, String de, Data da, int n, Orario orarioPartenza, int ID) throws ExistIDException {
		boolean existID = checkID(ID);
		if (!existID) {
			this.IDvolo = ID;
			// Controlla origine
			if (o.length() != 3)
				throw new AeroportoNonCorrettoException("Aeroporto origine non corretto");
			this.origine = o;
			// Controlla destinazione
			if (de.length() != 3)
				throw new AeroportoNonCorrettoException("Aeroporto destinazione non corretto");
			this.destinazione = de;
			this.dataPartenza = da;
			this.nMax = n;
			this.orarioPartenza = orarioPartenza;
			this.prenotazioni = new SequenzaOrdinata<Prenotazione>();
			IDusati.add(ID);

		} else {
			throw new ExistIDException("ID già inserito");
		}
	}

	// Controllo che l'ID inserito non esista
	private boolean checkID(int iD) {
		for (int id : IDusati)
			if (iD == id)
				return true;
		return false;
	}

	// Metodo che stampa il volo
	public String toString() {
		String s = "";
		s = "\n" + IDvolo + " origine: " + origine + " destinazione: " + destinazione + " data partenza: "
				+ dataPartenza + " ora partenza: " + orarioPartenza;
		return s;
	}

	// Metodo per l'aggiunta di una nuova prenotazione per il volo
	public void inserisciPrenotazione(Prenotazione p) throws PrenotazioneEsistenteException {
		if (!checkIDPren(p))
			this.prenotazioni.add(p);
		else
			throw new PrenotazioneEsistenteException("Prenotazione già inserita, ID duplicato");
	}

	// Metodo che controlla se la prenotazione con un determinato ID è già presente sul volo stesso
	private boolean checkIDPren(Prenotazione p) {
		for (Prenotazione currentp : prenotazioni)
			if (currentp.getID().equalsIgnoreCase(p.getID()))
				return true;
		return false;
	}

	@Override
	public int compareTo(Volo x) {
		return this.IDvolo - x.IDvolo;
	}

	// Get ID
	public int getID() {
		return IDvolo;
	}

	// Get Destinazione
	public String getDestinazione() {
		return destinazione;
	}

	// Get Origine
	public String getOrigine() {
		return origine;
	}

	// Metodo che ci dice se il volo è già pieno
	public boolean pieno() {
		return (prenotazioni.size() >= nMax);
	}

	// Get lista prenotazioni
	public SequenzaOrdinata<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

}
