package GestioneTelecamere;

import java.io.FileNotFoundException;

import Eccezioni.IDNotFound;
import Eccezioni.IncorrectState;
import Eccezioni.PosizioneScorretta;
import Telecamere.*;

public class Main {

	public static void main(String[] args) throws PosizioneScorretta, IncorrectState, IDNotFound, NumberFormatException, FileNotFoundException {

		GestioneTelecamere sistema1 = new GestioneTelecamere();
		sistema1.inserimentoTelecamera("pippo", 1.0, 348384);		
		sistema1.inserimentoTelecamera("pippo", 0.4, 348384);
		sistema1.inserimentoTelecamera("pippo", 4.2, "pluto");
		sistema1.inserimentoTelecamera("pippo", 3.2, "pluto");
		sistema1.cambioStato(1, StatoTelecamere.SPENTO);
		
		sistema1.inserimentoTelecameraDaFile(sistema1.letturaFile("telecamera1.txt"));
		sistema1.inserimentoTelecameraDaFile(sistema1.letturaFile("telecamera2.txt"));
		sistema1.inserimentoTelecameraDaFile(sistema1.letturaFile("telecamera3.txt"));
		sistema1.inserimentoTelecameraDaFile(sistema1.letturaFile("telecamera4.txt"));
		
		sistema1.ordinamentoTelecamere();
		sistema1.stampaTelecamere();
		
	}

}
