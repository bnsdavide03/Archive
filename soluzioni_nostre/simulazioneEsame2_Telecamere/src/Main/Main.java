package Main;
import Telecamera.*;
import Exception.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, NullPointerException, StatoIncorrectException, PositionIncorrectException {
		
		GestioneTelecamera sistema1 = new GestioneTelecamera ();
		
		sistema1.addTelecamera(sistema1.letturaFile("telecamera1.txt"));
		sistema1.addTelecamera(sistema1.letturaFile("telecamera2.txt"));
		sistema1.addTelecamera(sistema1.letturaFile("telecamera3.txt"));
		sistema1.addTelecamera(sistema1.letturaFile("telecamera4.txt"));
		sistema1.addTelecamera(sistema1.letturaFile("telecamera5.txt"));

		sistema.ordinamentoTelecamere();
		sistema1.stampaTelecamere();
	}	
}
