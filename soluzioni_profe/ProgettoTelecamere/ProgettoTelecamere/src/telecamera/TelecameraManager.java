package telecamera;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import telecamera.eccezioni.OperazioneNonSupportata;
import telecamera.eccezioni.SpegnimentoNonPossible;
import telecamera.eccezioni.TelecameraNonPresente;
import telecamera.stato.StatoTelecamera;

public class TelecameraManager {

	private TreeSet<Telecamera> telecamere;

	public TelecameraManager() {
		this.telecamere = new TreeSet<>();
	}

	public void reset() {
		this.telecamere = new TreeSet<>();
	}

	public void aggiungiTelecamera(Telecamera t) {
		if (t == null) {
			throw new NullPointerException("Telecamera nulla");
		}
		this.telecamere.add(t);
	}

	public void stampa() {

		System.out.println("--------------------");
		System.out.println("Stato telecamere:");
		for (Telecamera t : this.telecamere) {
			System.out.println(t);
		}
	}

	public void leggiDaFile(String nome) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(nome));

		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] campi = line.split(",");
			if (campi[0].equals("A")) {
				this.aggiungiTelecamera(new Allarme(campi[2], Float.parseFloat(campi[1]), campi[3]));
			} else {
				this.aggiungiTelecamera(new Semplice(campi[2], Float.parseFloat(campi[1])));
			}
		}
		sc.close();
	}

	public void eseguiAzioni(String nome)
			throws FileNotFoundException, OperazioneNonSupportata, SpegnimentoNonPossible, TelecameraNonPresente {
		Scanner sc = new Scanner(new File(nome));
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String[] campi = line.split(",");
			// legge l'ID della telecamera
			String idTelecamera = campi[0];

			Telecamera telecamera = null;
			for (Telecamera t : this.telecamere) {
				if (t.getId() == Integer.parseInt(idTelecamera)) {
					telecamera = t;
				}
			}
			if (telecamera == null) {
				throw new TelecameraNonPresente("La telecamera non e' presente");
			}

			// legge l'azione da eseguire sulla telecamera
			StatoTelecamera azione = StatoTelecamera.valueOf(campi[1]);

			System.out.println("Telecamera: " + idTelecamera + " Azione: " + azione);

			eseguiAzione(telecamera, azione);
		}
		sc.close();
	}

	private boolean eseguiAzione(Telecamera telecamera, StatoTelecamera azione)
			throws OperazioneNonSupportata, SpegnimentoNonPossible {
		if (azione.equals(StatoTelecamera.ACCESA)) {
			telecamera.accendi();
			return true;
		}

		if (azione.equals(StatoTelecamera.BLOCCO_IMMAGINE)) {
			try {
				telecamera.bloccaImmagine();
				return true;
			} catch (OperazioneNonSupportata e) {
				System.out.println(e.toString());
				return false;
			}
		}
		if (azione.equals(StatoTelecamera.SPENTA)) {
			spegni(telecamera);
		}
		return true;
	}

	private boolean spegni(Telecamera telecamera) throws SpegnimentoNonPossible {
		try {

			// controlla che le due telecamere adiacenti non siano spenti
			Iterator<Telecamera> it = telecamere.iterator();

			Telecamera telecameraPrecedente = null;
			Telecamera telecameraSeguente = null;
			boolean found = false;

			while (it.hasNext()) {
				Telecamera nextTelecamera = it.next();
				if (nextTelecamera.equals(telecamera)) {
					found = true;
					if (it.hasNext()) {
						telecameraSeguente = it.next();
					}
				}
				if (found == false) {
					telecameraPrecedente = nextTelecamera;
				}

			}
			if (telecameraPrecedente == null && telecameraSeguente == null) {
				telecamera.spegni();
			} else {
				if (telecameraPrecedente == null && !telecameraSeguente.getStato().equals(StatoTelecamera.SPENTA)) {
					telecamera.spegni();
				} else {
					if (telecameraSeguente == null && !telecameraPrecedente.getStato().equals(StatoTelecamera.SPENTA)) {
						telecamera.spegni();
					} else {
						if (!telecameraPrecedente.getStato().equals(StatoTelecamera.SPENTA)
								&& !telecameraSeguente.getStato().equals(StatoTelecamera.SPENTA)) {
							telecamera.spegni();
						} else {
							throw new SpegnimentoNonPossible("Non posso eseguire lo spegnimento");
						}
					}
				}
			}

			return true;
		} catch (OperazioneNonSupportata e) {
			System.out.println(e.toString());
			return false;
		}
	}
}
