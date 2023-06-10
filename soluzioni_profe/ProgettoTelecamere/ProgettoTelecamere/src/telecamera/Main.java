package telecamera;

import java.io.FileNotFoundException;

import telecamera.eccezioni.OperazioneNonSupportata;
import telecamera.eccezioni.SpegnimentoNonPossible;
import telecamera.eccezioni.TelecameraNonPresente;

public class Main {

	public static void main(String[] args)
			throws FileNotFoundException, OperazioneNonSupportata, TelecameraNonPresente {

		System.out.println("Scenario 1");
		// Test 1
		TelecameraManager manager = new TelecameraManager();

		manager.leggiDaFile("configurazione.txt");

		manager.stampa();

		System.out.println("--------------------");
		System.out.println("Esecuo le azioni");

		try {
			manager.eseguiAzioni("azioni.txt");

			manager.stampa();
		} catch (SpegnimentoNonPossible e) {
			System.out.println(e.toString());
		}

		// Test 2 una sola telecamera
		System.out.println("Scenario 2");
		manager.reset();
		manager.leggiDaFile("configurazione1.txt");

		manager.stampa();

		System.out.println("--------------------");
		System.out.println("Esecuo le azioni");

		try {
			manager.eseguiAzioni("azioni1.txt");

			manager.stampa();
		} catch (SpegnimentoNonPossible e) {
			System.out.println(e.toString());
		}

		// Test 3 - 2 telecamere errore
		System.out.println("Scenario 3");
		manager.reset();
		manager.leggiDaFile("configurazione2.txt");

		manager.stampa();

		System.out.println("--------------------");
		System.out.println("Esecuo le azioni");

		try {
			manager.eseguiAzioni("azioni2.txt");

			manager.stampa();
		} catch (SpegnimentoNonPossible e) {
			System.out.println(e.toString());
			manager.stampa();
		} catch (TelecameraNonPresente e) {
			System.out.println(e.toString());
			manager.stampa();
		}

		// Test 3 - 3 telecamere
		System.out.println("Scenario 3");
		manager.reset();
		manager.leggiDaFile("configurazione3.txt");

		manager.stampa();

		System.out.println("--------------------");
		System.out.println("Esecuo le azioni");

		try {
			manager.eseguiAzioni("azioni3.txt");

			manager.stampa();
		} catch (SpegnimentoNonPossible e) {
			System.out.println(e.toString());
			manager.stampa();
		} catch (TelecameraNonPresente e) {
			System.out.println(e.toString());
			manager.stampa();
		}

		// Test 4 - 3 telecamere - errore
		System.out.println("Scenario 4");
		manager.reset();
		manager.leggiDaFile("configurazione4.txt");

		manager.stampa();

		System.out.println("--------------------");
		System.out.println("Esecuo le azioni");

		try {
			manager.eseguiAzioni("azioni4.txt");

			manager.stampa();
		} catch (SpegnimentoNonPossible e) {
			System.out.println(e.toString());
			manager.stampa();
		} catch (TelecameraNonPresente e) {
			System.out.println(e.toString());
			manager.stampa();
		}
	}

}
