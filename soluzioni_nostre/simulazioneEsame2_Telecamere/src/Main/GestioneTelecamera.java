package Main;

import Telecamera.StatoTelecamera;
import Telecamera.Telecamera;
import Telecamera.TelecameraAllarme;
import Telecamera.TelecameraSemplici;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import Exception.IDNotFoundException;
import Exception.PositionIncorrectException;
import Exception.StatoIncorrectException;

public class GestioneTelecamera {

	
	ArrayList <Telecamera> telecamere;
	
	public GestioneTelecamera() {
		
		this.telecamere = new ArrayList <Telecamera>();
		
	}
	
	
	public String[] letturaFile(String nomeFile) {
		
		String[] campi = null;
		
		File file = new File (nomeFile);
		try (Scanner scan = new Scanner (file)) {
			while (scan.hasNext()) {
				String line = scan.next();
				campi = line.split(",");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return campi;
		
	}
	

	public void addTelecamera(String[] campi) throws NumberFormatException, StatoIncorrectException, NullPointerException, PositionIncorrectException {
				
		if (campi[0].equals("A")) {
			
			telecamere.add (new TelecameraAllarme(campi[1],Double.parseDouble(campi[2]),StatoTelecamera.valueOf(campi[3]),Integer.parseInt(campi[4])));	
		
		} else if (campi[0].equals("S")) {
			
			telecamere.add (new TelecameraSemplici(campi[1], Double.parseDouble(campi[2]), StatoTelecamera.valueOf(campi[3]), campi[4]));
			
		}
	}
	
	
	public void cambioStatoTelecamera (int id, String[] campi) throws StatoIncorrectException, IDNotFoundException {

		ordinamentoTelecamera();
		
		for (int i = 0; i < telecamere.size(); i++) {
			
			if (telecamere.get(i).getID() == id) {
				
			if (StatoTelecamera.valueOf(campi[1]) == StatoTelecamera.SPEGNI) {
				
				
				
			} else {
				telecamere.get(i).setStato(StatoTelecamera.valueOf(campi[1]));;
			}
				
			} else {
				throw new IDNotFoundException("ID non trovato");
			}
		}
	}


	public void ordinamentoTelecamera() {
		Collections.sort(telecamere);
		System.out.println(telecamere);
	}


	public void stampaTelecamere () {
		
		System.out.println(telecamere.toString());
	}


	@Override
	public String toString() {
		return "GestioneTelecamera [telecamere=" + telecamere + "]";
	}
	
}
