package fr.pederobien.persistence.exceptions;

public class ExtensionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String expected, actual;

	public ExtensionException(String expected, String actual) {
		super(String.format("Bad file extension, expected %s but found %s", expected, actual));
		this.expected = expected;
		this.actual = actual;
	}

	/**
	 * @return The expected extension
	 */
	public String getExpected() {
		return expected;
	}

	/**
	 * @return The actual extension.
	 */
	public String getActual() {
		return actual;
	}
}
