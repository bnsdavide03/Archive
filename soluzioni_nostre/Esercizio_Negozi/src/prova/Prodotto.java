package prova;

import java.util.HashSet;
import java.util.Set;

public class Prodotto {

	private final int id;
	private int quantitaDisponibile;
	private double prezzoUnitario;
	private Set <Integer> idAssegnati = new HashSet <Integer> ();
	
	public Prodotto (int id, int quantitaDisponibile, double prezzoUnitario) {
		
		if (idAssegnati.contains(id)) {
			//ECCEZIONE
		}
		
		this.id = id;
		this.quantitaDisponibile = quantitaDisponibile;
		this.prezzoUnitario = prezzoUnitario;
	}

	
	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", quantitaDisponibile=" + quantitaDisponibile + ", prezzoUnitario="
				+ prezzoUnitario + ", idAssegnati=" + idAssegnati + "]";
	}


	public int decrementaDisponibilità(int quantitaRichiesta) {
		return quantitaDisponibile - quantitaRichiesta;
	}
	
	public int aumentaDisponibilità(int quantita) {
		return quantitaDisponibile + quantita;
	}
	
	public int getQuantitaDisponibile() {
		return quantitaDisponibile;
	}

	public void setQuantitaDisponibile(int quantitaDisponibile) {
		this.quantitaDisponibile = quantitaDisponibile;
	}

	public double getPrezzoUnitario() {
		return prezzoUnitario;
	}
	
	public void setPrezzoUnitario(double prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	
	public int getId() {
		return id;
	}
	
	
	
}
