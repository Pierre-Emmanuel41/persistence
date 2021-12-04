package fr.pederobien.persistence.exceptions;

public class SerializerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private double version;

	public SerializerNotFoundException(double version) {
		super("No serializer found for version : " + version);
		this.version = version;
	}

	/**
	 * @return The version that correspond to any loader.
	 */
	public double getVersion() {
		return version;
	}
}
