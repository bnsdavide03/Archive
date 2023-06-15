package GestioneTelecamere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Eccezioni.IDNotFound;
import Eccezioni.IncorrectState;
import Eccezioni.PosizioneScorretta;
import Telecamere.StatoTelecamere;
import Telecamere.TelecameraAllarme;
import Telecamere.TelecameraSemplice;
import Telecamere.Telecamere;

public class GestioneTelecamere {

	ArrayList<Telecamere> telecamere = new ArrayList<Telecamere>();

	public GestioneTelecamere() {

	}

	public String[] letturaFile(String nomeFile) throws FileNotFoundException {

		String[] campi = null;

		File file = new File(nomeFile);
		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {

			String line = scan.nextLine();
			campi = line.split(",");
		}

		return campi;

	}

	public void inserimentoTelecameraDaFile(String[] campi) throws NumberFormatException, PosizioneScorretta {

		if (campi[0].equals("S")) {

			telecamere.add(new TelecameraSemplice(campi[1], Double.parseDouble(campi[2]), campi[3]));

		}

		if (campi[0].equals("A")) {

			telecamere.add(new TelecameraAllarme(campi[1], Double.parseDouble(campi[2]), Integer.parseInt(campi[3])));

		}

	}

	public void inserimentoTelecamera(String descrizione, Double posizione, int numeroTelefono)
			throws PosizioneScorretta {

		telecamere.add(new TelecameraAllarme(descrizione, posizione, numeroTelefono));

	}

	public void inserimentoTelecamera(String descrizione, Double posizione, String descrizioneCostruttore)
			throws PosizioneScorretta {

		telecamere.add(new TelecameraSemplice(descrizione, posizione, descrizioneCostruttore));

	}

	public boolean cambioStato(int id, StatoTelecamere stato) throws IncorrectState, IDNotFound {

		boolean marker = false;

		Collections.sort(telecamere);

		for (int i = 0; i < telecamere.size(); i++) {

			if (telecamere.get(i).getId() == id) {

				if (stato.equals(StatoTelecamere.SPENTO)) {

					if (i == 0 && telecamere.get(i + 1).getStato().equals(StatoTelecamere.ACCESO)) {

						telecamere.get(i).setStato(stato);
						marker = true;

					} else if (telecamere.get(i + 1).getStato().equals(StatoTelecamere.ACCESO)
							|| telecamere.get(i - 1).getStato().equals(StatoTelecamere.ACCESO)) {

						telecamere.get(i).setStato(stato);
						marker = true;

					} else if (i == telecamere.size()
							&& telecamere.get(i - 1).getStato().equals(StatoTelecamere.ACCESO)) {
						telecamere.get(i).setStato(stato);
						marker = true;
					}
				} else {
					throw new IncorrectState("Stato scorretto");
				}

			}

			if (marker == false) {

				throw new IDNotFound("ID non trovato");

			}
		}

		return marker;
	}

	public void ordinamentoTelecamere() {

		Collections.sort(telecamere);

	}

	public void stampaTelecamere() {

		System.out.println(telecamere);

	}

}
