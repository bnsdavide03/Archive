package prova;

public class Main {

	public static void main(String[] args) {
		
		
		
		Negozio negozio1 = new Negozio("Negozio 1", 1);
        Negozio negozio2 = new Negozio("Negozio 2", 2);

        // Creazione dei prodotti
        Prodotto prodotto1 = new Prodotto(1, 10, 2.5);
        Prodotto prodotto2 = new Prodotto(2, 5, 5.0);

        // Aggiunta dei prodotti ai negozi
        negozio1.aggiungiProdotti(prodotto1);
        negozio1.aggiungiProdotti(prodotto2);
        negozio2.aggiungiProdotti(prodotto1);

        // Creazione del cliente
        Cliente cliente = new Cliente("Mario", "Rossi", 1);

        // Aggiunta prodotti alla lista della spesa del cliente
        cliente.aggiungiProdotto(prodotto1, 2);
        cliente.aggiungiProdotto(prodotto2, 1);

        // Rimozione di un prodotto dalla lista della spesa del cliente
         cliente.rimuoviProdotto(prodotto2);

        // Calcolo del totale della spesa
        double totale = calcolaTotaleSpesa(cliente);
        System.out.println("Totale della spesa: " + totale);
		
	}

	
	public static double calcolaTotaleSpesa(Cliente cliente) {
        double totale = 0.0;
        for (Prodotto prodotto : cliente.getListaSpesa()) {
            double prezzoUnitario = prodotto.getPrezzoUnitario();
            int quantita = prodotto.getQuantitaDisponibile();
            totale += prezzoUnitario * quantita;
        }
        return totale;
    }
}
