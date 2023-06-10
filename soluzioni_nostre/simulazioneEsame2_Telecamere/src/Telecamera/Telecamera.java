package Telecamera;
import java.util.*;

import Exception.PositionIncorrectException;
import Exception.StatoIncorrectException;

public abstract class Telecamera implements Comparable<Telecamera>{

	private static int IDCounter = 0;
	private final Integer ID;
	private static Set<Integer>IDAssigned = new HashSet<Integer>();
	private final String descrizione;
	private final Double position;	
	
	public Telecamera (String descrizione, Double position) throws NullPointerException, PositionIncorrectException{
		
		if (descrizione == null) {
			throw new NullPointerException("descrizione non presente");
		}
		
		this.ID = IDCounter++;
		IDAssigned.add(ID);
		this.descrizione = descrizione;
		
		if (position < 0 || position > 200) {
			throw new PositionIncorrectException("posizione non corretta");
		}
		
		this.position = position;
		
	}	

	public static int getIDCounter() {
		return IDCounter;
	}

	public Integer getID() {
		return ID;
	}

	public static Set<Integer> getIDAssigned() {
		return IDAssigned;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Double getPosition() {
		return position;
	}
	
	@Override
	public String toString() {
		return "Telecamera [ID=" + ID + ", descrizione=" + descrizione + ", position=" + position + "]";
	}

	
	public void setStato(StatoTelecamera stato) throws StatoIncorrectException {
		
		
		
	}
	
	public int compareTo(Telecamera o) {
		
		if (this.getPosition() > o.getPosition()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
}
