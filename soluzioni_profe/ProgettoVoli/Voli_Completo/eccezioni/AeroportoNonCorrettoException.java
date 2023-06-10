package eccezioni;

/** viene generato se la sigla dell'aeroporto non è di 3 caratteri*/

public class AeroportoNonCorrettoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AeroportoNonCorrettoException(String msg) {
		super(msg);
	}
}
