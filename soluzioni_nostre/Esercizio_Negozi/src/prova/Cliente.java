package prova;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Cliente {

	private final String nome, cognome;
	private final int id;
	ArrayList<Prodotto> listaSpesa;
	private Set<Integer> idAssegnati = new HashSet<Integer>();

	public Cliente (String nome, String cognome, int id) {
		
		if (idAssegnati.contains(id)) {
			
		}
		
		this.nome = nome;
		this.cognome = cognome;
		this.id = id;
		listaSpesa = new ArrayList<Prodotto>();
	}

	public ArrayList<Prodotto> getListaSpesa() {
		return listaSpesa;
	}
	
	public void aggiungiProdotto(Prodotto prodotto, int quantita) {
		if (prodotto.getQuantitaDisponibile() >= quantita) {
			listaSpesa.add(prodotto);
			prodotto.decrementaDisponibilità(quantita);
		} else {
			System.out.println("Non si può");
		}
	}

	public void rimuoviProdotto(Prodotto prodotto) {
		
		listaSpesa.remove(prodotto);
		
	}
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cognome=" + cognome + ", id=" + id + ", listaSpesa=" + listaSpesa
				+ ", idAssegnati=" + idAssegnati + "]";
	}

	public void setListaSpesa(ArrayList<Prodotto> listaSpesa) {
		this.listaSpesa = listaSpesa;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public int getId() {
		return id;
	}

}
