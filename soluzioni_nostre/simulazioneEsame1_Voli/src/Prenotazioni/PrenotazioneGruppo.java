package Prenotazioni;
import java.util.ArrayList;

public class PrenotazioneGruppo extends Prenotazione{

	ArrayList <String> prenotazioneGruppo = new ArrayList <String> ();
	
	public PrenotazioneGruppo(String id, Double costo, ArrayList<String> nomePersoneGruppo) {
		super(id, costo);
		prenotazioneGruppo = nomePersoneGruppo;
	}
	
	
	
	public ArrayList<String> getPrenotazioneGruppo() {
		return prenotazioneGruppo;
	}



	public void setPrenotazioneGruppo(ArrayList<String> prenotazioneGruppo) {
		this.prenotazioneGruppo = prenotazioneGruppo;
	}



	@Override
	public double getCosto() {
		
		double costoTotale = 0;
		
		double costoPersona = super.getCosto();
		
		if (prenotazioneGruppo.size() <= 10) {
			costoTotale = costoPersona * prenotazioneGruppo.size() * 0.10;
		} else {
			costoTotale = costoPersona * prenotazioneGruppo.size() * 0.20;
		}
		
		return costoTotale;
	}
	
	
	@Override
	public String toString() {
		return "PrenotazioneGruppo [personeGruppo=" + prenotazioneGruppo + "]";
	}
	
}
