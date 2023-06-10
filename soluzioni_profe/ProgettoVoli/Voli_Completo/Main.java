
import eccezioni.ExistIDException;
import eccezioni.PrenotazioneEsistenteException;
import eccezioni.VoloPienoException;
import prenotazioni.*;
import prog.utili.Data;
import prog.utili.Orario;
import voli.*;

public class Main {

	public static void main(String[] args) throws VoloPienoException, NumberFormatException, ExistIDException, PrenotazioneEsistenteException {
		Data d1 = new Data();
		Orario o1 = new Orario();
		GestorePrenotazioneVoli primoGestore = new GestorePrenotazioneVoli();

		System.out.println("1. LETTURA DA FILE");
		// 1. Lettura da file
		primoGestore.caricaDaFile("data.txt");
		primoGestore.stampa();

		// 2. Inserimento volo
		System.out.println("2. INSERIMENTO VOLI CON STAMPA PER CONTROLLO \n");
		primoGestore.inserisciVolo("WAS", "NYC", d1, o1, 3);
		primoGestore.inserisciVolo("BWI", "BER", d1, o1, 3);
		primoGestore.inserisciVolo("WAS", "CDG", d1, o1, 3);
		// Provo ad aggiungere un volo già esistente
		try {
			primoGestore.inserisciVolo("WAS", "CDG", d1, o1, 2,1); 
		} catch (ExistIDException e) {
			System.out.println("Volo con ID già esistente");
		}
		System.out.println(primoGestore.toString());

		// 3. Inserimento prenotazioni
		System.out.println("3. INSERIMENTO PRENOTAZIONI \n");
		Prenotazione p = new PrenotazioneIndividuale("BOSIS", "ROLANDO", "CORRIDOIO", 3);
		Prenotazione pg = new PrenotazioneGruppo(new String[] {"Luca", "Marco", "Andrea"}, 2); 
		primoGestore.inserisciPrenotazione(p, 1);
		primoGestore.inserisciPrenotazione(new PrenotazioneIndividuale("BOSIS", "Walter", "FINESTRINO", 9), 1);
		primoGestore.inserisciPrenotazione(new PrenotazioneIndividuale("cambia", "ciccio", "CORRIDOIO", 5), 1);
		primoGestore.inserisciPrenotazione(pg, 3);
		primoGestore.stampa();
		
		// 4. Spostamento di una prenotazione da un volo ad un altro
		System.out.println("4. SPOSTAMENTO PERSONA DA UN VOLO AD UN ALTRO\n");
		primoGestore.sposta(p.getID(), 2);
		primoGestore.stampa();
		
		// 5. Aggiunta persona a prenotazione collettiva
		System.out.println("5. AGGIUNTA PERSONA A PRENOTAZIONE\n");
		primoGestore.aggiungi("Alessandra", pg.getID());
		primoGestore.stampa();		
		
		// 6. Stampa voli con prenotazioni
		System.out.println("6.STAMPA VOLI CON PRENOTAZIONI E COSTI\n");
		primoGestore.stampa();
		
		// 7. Ricerca volo tramite sigla origine
		System.out.println("7. RICERCA VOLI \n");
		Volo v1 = primoGestore.cercaOrigine("BGY");
		System.out.println(v1.toString());
	}

}
