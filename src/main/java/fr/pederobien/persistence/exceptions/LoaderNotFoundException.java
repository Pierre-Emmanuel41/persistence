package fr.pederobien.persistence.exceptions;

public class LoaderNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private double version;

	public LoaderNotFoundException(double version) {
		super("No loader found for version : " + version);
		this.version = version;
	}

	/**
	 * @return The version that correspond to any loader.
	 */
	public double getVersion() {
		return version;
	}
}
