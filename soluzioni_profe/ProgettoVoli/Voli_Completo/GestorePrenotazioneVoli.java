
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eccezioni.ExistIDException;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.VoloPienoException;
import prenotazioni.*;
import prog.io.FileInputManager;
import prog.utili.Data;
import prog.utili.Orario;
import prog.utili.SequenzaOrdinata;
import voli.Volo;

public class GestorePrenotazioneVoli {

	// Lista dei voli gestiti da questo gestore
	private SequenzaOrdinata<Volo> voli;

	public GestorePrenotazioneVoli() {
		voli = new SequenzaOrdinata<Volo>();
	}

	/**
	 * 1. Funzionalità di lettura da file (Nella prima parola distinguo se è un volo
	 * o una prenotazione se volo o prenotazione)
	 * 
	 * @param path
	 * @throws VoloPienoException
	 * @throws NumberFormatException
	 * @throws ExistIDException
	 * @throws PrenotazioneEsistenteException
	 */
	public void caricaDaFile(String path)
			throws VoloPienoException, NumberFormatException, ExistIDException, PrenotazioneEsistenteException {
		String righe, campi[];
		FileInputManager in = new FileInputManager(path);
		do {
			righe = in.readLine();
			if (righe != null) {
				campi = righe.split(";");
				String scelta = campi[0];
				if (scelta.equals("volo")) // INSERIMENTO VOLO
				{
					// Ad esempio -> volo;1;BGY;MIL;09.03.2012;240;12:00
					String id = campi[1];
					String origine = campi[2];
					String destinazione = campi[3];
					Data d = new Data(campi[4]);
					Orario o = new Orario(campi[6]);
					int nMax = Integer.parseInt(campi[5]);
					inserisciVolo(origine, destinazione, d, o, nMax, Integer.parseInt(id));
				} else {
					scelta = campi[1];
					// Se è una prenotazione individuale
					if (scelta.equals("individuale")) { // Ad esempio ->
														// prenotazione;individuale;1;vecchi;luca;CORRIDOIO;8
						int idVolo = Integer.parseInt(campi[2]);
						String cognome = campi[3];
						String nome = campi[4];
						String posto = campi[5];
						int costo = Integer.parseInt(campi[6]);
						inserisciPrenotazione(new PrenotazioneIndividuale(cognome, nome, posto, costo), idVolo);

					}
					// Se è una prenotazione di gruppo
					else {
						// Ad esempio -> prenotazione;gruppo;1;5;paolo;micaela;giusy;manuel;giacomo;9;2
						int idVolo = Integer.parseInt(campi[2]);
						int gruppoN = Integer.parseInt(campi[3]); // quantePersoneHannoPrenotato
						int i;
						String[] persone = new String[gruppoN];
						for (i = 0; i < gruppoN; i++)
							persone[i] = campi[i + 4];
						int costo = Integer.parseInt(campi[i + 4]);
						inserisciPrenotazione(new PrenotazioneGruppo(persone, costo), idVolo);
					}

				}
			}
		} while (righe != null);

	}

	/**
	 * Inserisci volo utilizzando un ID letto dall'esterno
	 * (utilizzato dalla funzione 1)
	 * @param o   partenza
	 * @param de  arrivo
	 * @param da  data
	 * @param ora orario di partenza
	 * @param n   numero di posti
	 * @param ID  the id
	 * @throws ExistIDException the exist ID exception
	 */
	public void inserisciVolo(String o, String de, Data da, Orario ora, int n, int ID) throws ExistIDException {
		voli.add(new Volo(o, de, da, n, ora, ID));

	}

	/**
	 * 2. Funzionalitò di inserimento di un volo
	 * 
	 * @param o
	 * @param de
	 * @param da
	 * @param ora
	 * @param n
	 * @throws ExistIDException
	 */
	public void inserisciVolo(String o, String de, Data da, Orario ora, int n) throws ExistIDException {
		voli.add(new Volo(o, de, da, ora, n));
	}

	/**
	 * 3. Inserisci prenotazione.
	 *
	 * @param p  the p
	 * @param id the id del volo
	 * @throws VoloPienoException
	 * @throws PrenotazioneEsistenteException
	 */
	public void inserisciPrenotazione(Prenotazione p, int id)
			throws VoloPienoException, PrenotazioneEsistenteException {
		for (Volo v : voli) {
			if (v.getID() == id) {
				if (v.pieno())
					throw new VoloPienoException("volo ID:" + v.getID() + " pieno. Siamo spiacenti");
				else {
					v.inserisciPrenotazione(p);
					return;
				}
			}
		}
		// nessun volo trovato, non faccio nulla
		return;
	}
	
	/**
	 * 4. Sposta prenotazione
	 *
	 * @param idpren      ID della prenotazione da spostare
	 * @param idvoloNuovo the idvolo nuovo
	 * @throws VoloPienoException
	 * @throws PrenotazioneEsistenteException
	 */
	public void sposta(String idpren, int idvoloNuovo) throws VoloPienoException, PrenotazioneEsistenteException {
		Volo src = null;
		Prenotazione pDamuovere = null;
		// Cerco la prenotazione
		for (Volo v : voli) {
			for (Prenotazione p : v.getPrenotazioni()) {
				if (p.getID().equals(idpren)) {
					src = v;
					pDamuovere = p;
				}
			}
		}
		// Prenotazione non trovata
		if (src == null)
			return;
		// cerca volo su cui spostare
		for (Volo v : voli) {
			if (v.getID() == idvoloNuovo) {
				// se pieno lancia eccezione
				if (v.pieno()) {
					throw new VoloPienoException("volo ID:" + v.getID() + " pieno. Siamo spiacenti");
				} else {
					// cambia volo
					src.getPrenotazioni().remove(pDamuovere);
					v.inserisciPrenotazione(pDamuovere);
				}
			}
		}
	}

	/**
	 * 5. Aggiunta persona a prenotazione collettiva
	 * 
	 * @param persona
	 * @param idpren
	 */
	public void aggiungi(String persona, String idpren) {
		for (Volo v : voli) {
			for (Prenotazione p : v.getPrenotazioni()) {
				if (p.getID().equals(idpren) && p instanceof PrenotazioneGruppo) {

					((PrenotazioneGruppo) p).addPersona(persona);
				}
			}
		}
	}

	/**
	 * 6. Stampa voli - in ordine di ID
	 * 
	 * Il fatto che la stampa sia in ordine di ID è dato dal fatto che voli è una SequenzaOrdinata
	 */
	public void stampa() {
		for (Volo v : voli) {
			System.out.println(v.toString());
			for (Prenotazione p : v.getPrenotazioni()) {
				System.out.println("\t" + p.toString() + " " + p.getCosto());
			}
		}
	}

	/**
	 * 6.stampa voli - in ordine di costi
	 * 
	 * Avendo una sequenza ordinata in base all'ID, dobbiamo definire un nuovo Comparator
	 */
	public void stampaPerCosto() {
		for (Volo v : voli) {
			System.out.println(v.toString());
			// Copio le prenotazioni in una Collection per poterle ordinare per costo sfruttando la Collections.sort
			List<Prenotazione> prenotazioni = new ArrayList<>();
			for (Prenotazione p : v.getPrenotazioni()) {
				prenotazioni.add(p);
			}
			Collections.sort(prenotazioni, new ComparatorPerCosto());
			for (Prenotazione p : prenotazioni) {
				System.out.println("\t" + p.toString() + " " + p.getCosto());
			}
		}
	}

	/**
	 * 7. Ricerca volo tramite sigla di origine
	 * 
	 * @param origin
	 * @return
	 */
	public Volo cercaOrigine(String origin) {
		for (Volo v : voli) {
			if (v.getOrigine().equals(origin)) {
				if (v.pieno())
					return null;
				else
					return v;
			}
		}
		return null;

	}

	/**
	 * 7. Ricerca volo tramite sigla di destinazione
	 * 
	 * @param destinazione
	 * @return
	 */
	public Volo cercaDestinazione(String destinazione) {
		for (Volo v : voli) {
			if (v.getDestinazione().equals(destinazione)) {
				if (v.pieno())
					return null;
				else
					return v;
			}
		}
		return null;

	}

	// Metodo di utilità che stampa tutti i voli gestiti dal gestore voli
	public String toString() {
		String s = "";
		for (Volo a : voli)
			s += " " + a.toString();
		return s;
	}

}