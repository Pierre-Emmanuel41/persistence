package fr.pederobien.persistence.exceptions;

import fr.pederobien.persistence.interfaces.ISerializer;

public class SerializerRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ISerializer<?> serializer;

	public SerializerRegisteredException(ISerializer<?> serializer) {
		super("A serializer is already registered for version : " + serializer.getVersion());
		this.serializer = serializer;
	}

	/**
	 * @return The registered serializer.
	 */
	public ISerializer<?> getSerializer() {
		return serializer;
	}
}
