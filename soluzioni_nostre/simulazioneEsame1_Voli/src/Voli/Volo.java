package Voli;

import java.util.HashMap;

import Prenotazioni.Prenotazione;
import Prenotazioni.PrenotazioneGruppo;
import Prenotazioni.PrenotazioneSingola;

import java.util.ArrayList;

public class Volo {

	private static int IDCOUNT = 0;
	private final String data;
	private final String ora;
	private final int id;
	private final Integer maxPasseggeri;
	private HashMap<String, String> volo = new HashMap<String, String>();
	private ArrayList<PrenotazioneGruppo> prenotazioniGruppo;
	private ArrayList<PrenotazioneSingola> prenotazioniSingola;


	public Volo (String partenza, String destinazione, String data, String ora, Integer maxPasseggeri) {
		
		this.data = data;
		this.ora = ora;
		this.maxPasseggeri = maxPasseggeri;
		volo.put(partenza, destinazione);
		this.prenotazioniGruppo = new ArrayList <PrenotazioneGruppo>();
		this.prenotazioniSingola = new ArrayList <PrenotazioneSingola>();
		IDCOUNT++;
		id = IDCOUNT;
	}
	
	public Volo (String data, String ora, String partenza, String destinazione, int id, Integer maxPasseggeri) {
				
		this.data = data;
		this.ora = ora;
		this.maxPasseggeri = maxPasseggeri;
		this.id = id;
		volo.put(partenza, destinazione);
		this.prenotazioniGruppo = new ArrayList <PrenotazioneGruppo>();
		this.prenotazioniSingola = new ArrayList <PrenotazioneSingola>();
		
	}

	
	
	public ArrayList<PrenotazioneGruppo> getPrenotazioniGruppo() {
		return prenotazioniGruppo;
	}

	public void setPrenotazioniGruppo(ArrayList<PrenotazioneGruppo> prenotazioniGruppo) {
		this.prenotazioniGruppo = prenotazioniGruppo;
	}

	public ArrayList<PrenotazioneSingola> getPrenotazioniSingola() {
		return prenotazioniSingola;
	}

	public void setPrenotazioniSingola(ArrayList<PrenotazioneSingola> prenotazioniSingola) {
		this.prenotazioniSingola = prenotazioniSingola;
	}

	public void aggiungiPrenotazioneG(PrenotazioneGruppo p) {
		this.prenotazioniGruppo.add(p);
	}
	
	public void aggiungiPrenotazioneS(PrenotazioneSingola p) {
		this.prenotazioniSingola.add(p);
	}
	
	public int contaPostiOccupati () {
		
		int postiGruppo = 0;
		
		for (int i = 0; i < prenotazioniGruppo.size(); i++)
		
			postiGruppo = postiGruppo + prenotazioniGruppo.get(i).getPrenotazioneGruppo().size();
			
		return postiGruppo + prenotazioniSingola.size() ;
	}
	
	
	public static int getIDCOUNT() {
		return IDCOUNT;
	}

	public static void setIDCOUNT(int iDCOUNT) {
		IDCOUNT = iDCOUNT;
	}

	public HashMap<String, String> getVolo() {
		return volo;
	}

	public void setVolo(HashMap<String, String> volo) {
		this.volo = volo;
	}


	public String getData() {
		return data;
	}

	public String getOra() {
		return ora;
	}

	public int getId() {
		return id;
	}

	public int getMaxPasseggeri() {
		return maxPasseggeri;
	}	
	
}