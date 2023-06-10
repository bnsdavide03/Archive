package Prenotazioni;

public class PrenotazioneSingola extends Prenotazione{

	private final String idCliente;
	TipologiaPosto posto;
	
	public PrenotazioneSingola(String id, String idCliente, Double costo, TipologiaPosto posto) {
		super(id, costo);
		this.idCliente = idCliente;
		this.posto = posto;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public TipologiaPosto getPosto() {
		return posto;
	}

	public void setPosto(TipologiaPosto posto) {
		this.posto = posto;
	}

	@Override
	public String toString() {
		return "PrenotazioneSingola [idCliente=" + idCliente + ", posto=" + posto + "]";
	}
	
}
