package simulazioneEsame1;

import Voli.Volo;
import Prenotazioni.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GestorePrenotazionieVoli {

	ArrayList<Volo> voli;

	public GestorePrenotazionieVoli(Volo voli) {

		this.voli = new ArrayList<Volo>();

	}

	public Volo getVolo(int id) {

		for (int i = 0; i < voli.size(); i++) {

			if (voli.get(i).getId() == id) {
				return voli.get(i);
			}
		}
		return null;
	}

	public void addVoliDaFile() throws NumberFormatException, FileNotFoundException {

		File file = new File("volo.txt");
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] campi = line.split(",");
				Volo voloA = new Volo(campi[0], campi[1], campi[2], campi[3], Integer.parseInt(campi[4]));
				voli.add(voloA);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}	
	
	public void addPrenotazioniDaFile() throws NumberFormatException, FileNotFoundException {

		File file = new File("Prenotazioni.txt");
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] campi = line.split(",");

				if (campi[0] == "I") {

					PrenotazioneSingola prenotazioneA = new PrenotazioneSingola(campi[1], campi[2],
							Double.parseDouble(campi[3]), TipologiaPosto.valueOf(campi[4]));
					this.getVolo(Integer.parseInt(campi[4])).aggiungiPrenotazioneS(prenotazioneA);

				} else if (campi[0] == "G") {

					ArrayList<String> nomePersoneGruppo = new ArrayList<String>();
					for (int i = 0; i < campi.length; i++) {
						nomePersoneGruppo.add(campi[i + 3]);
					}
					PrenotazioneGruppo prenotazioneA = new PrenotazioneGruppo(campi[1], Double.parseDouble(campi[2]),
							nomePersoneGruppo);
					this.getVolo(Integer.parseInt(campi[4])).aggiungiPrenotazioneG(prenotazioneA);

				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void spostaPrenotazione(int id1, int id2, String idPrenotazione) {
		
		int postiLiberi = 0;
		
		for (int i = 0; i < voli.size(); i++) {
			if (voli.get(i).getId() == id1) {
				postiLiberi = voli.get(i).getMaxPasseggeri() - voli.get(i).contaPostiOccupati();
				if (postiLiberi > 0) {
					for (int j = 0; i < voli.get(i).getPrenotazioniSingola().size(); i++) {
						if (voli.get(i).getPrenotazioniSingola().get(j).getId().equals(idPrenotazione)) {
							if ((postiLiberi - 1) >= 0) {
								PrenotazioneSingola p = new PrenotazioneSingola(idPrenotazione, voli.get(i).getPrenotazioniSingola().get(j).getIdCliente(), voli.get(i).getPrenotazioniSingola().get(j).getCosto(), voli.get(i).getPrenotazioniSingola().get(j).getPosto());
								for (int k = 0; k < voli.size(); k++) {
									if (voli.get(k).getId() == id2) {
										voli.get(k).aggiungiPrenotazioneS(p);
										break;
									}
								}
							}
							}
						} 
				
					for (int j = 0; i < voli.get(i).getPrenotazioniGruppo().size(); i++) {
						if (voli.get(i).getPrenotazioniGruppo().get(j).getId().equals(idPrenotazione)) {
							if ((postiLiberi - 1) >= 0) {
								PrenotazioneGruppo p = new PrenotazioneGruppo (voli.get(i).getPrenotazioniGruppo().get(j).getId(), voli.get(i).getPrenotazioniGruppo().get(j).getCosto(), voli.get(i).getPrenotazioniGruppo().get(j).getPrenotazioneGruppo());
								for (int k = 0; k < voli.size(); k++) {
									if (voli.get(k).getId() == id2) {
										voli.get(k).aggiungiPrenotazioneG(p);
										break;
									} 
								}
							}
						}
					}
				}
			}
		}
	}
}
