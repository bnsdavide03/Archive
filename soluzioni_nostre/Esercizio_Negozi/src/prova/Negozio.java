package prova;

import java.util.HashSet;
import java.util.Set;

public class Negozio {

	private final int id;
	private String nome;
	private HashSet <Prodotto> prodotti;
	private Set <Integer> idAssegnati = new HashSet <Integer> ();
	
	public Negozio(String nome, int id) {
		
		if (idAssegnati.contains(id)) {
			//ECCEZIONE
		}
		
		this.id = id;
		idAssegnati.add(id);
		this.nome = nome;
		prodotti = new HashSet <Prodotto>();
	}
	
	@Override
	public String toString() {
		return "Negozio [id=" + id + ", nome=" + nome + ", prodotti=" + prodotti + ", idAssegnati=" + idAssegnati + "]";
	}

	public void aggiungiProdotti (Prodotto prodotto) {
		if (prodotti.contains(prodotto)) {
			System.out.println("C'è già");
		} else {
			prodotti.add(prodotto);
		}
	}
	
	public void rimuoviProdotti (Prodotto prodotto) {
		prodotti.remove(prodotto);
	}

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public HashSet<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(HashSet<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
