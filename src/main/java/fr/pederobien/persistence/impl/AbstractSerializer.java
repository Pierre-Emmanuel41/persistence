package fr.pederobien.persistence.impl;

import fr.pederobien.persistence.interfaces.ISerializer;

public abstract class AbstractSerializer<T> implements ISerializer<T> {
	private double version;

	/**
	 * Creates a serializer associated to a specific version.
	 * 
	 * @param version The version of this serializer.
	 */
	protected AbstractSerializer(double version) {
		this.version = version;
	}

	@Override
	public Double getVersion() {
		return version;
	}

	@Override
	public void deserialize(T element, String path) throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public void serialize(T element, String path) throws Exception {
		throw new UnsupportedOperationException();
	}
}
