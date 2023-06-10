package Prenotazioni;
import java.util.ArrayList;

public abstract class Prenotazione {
	
	private ArrayList <String> idAssegnati = new ArrayList <String> ();
	private final String id;
	private Double costo;
	
	public Prenotazione (String id, Double costo) {
		
		if (idAssegnati.contains(id)) {
			// throw new 
		}
		
		this.id = id;
		this.costo = costo;
		idAssegnati.add(id);
		
	}

	public String getId() {
		return id;
	}

	
	public double getCosto() {
		return costo;
	}	
	
	
	
}

