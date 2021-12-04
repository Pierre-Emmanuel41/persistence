package fr.pederobien.persistence.impl;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import fr.pederobien.persistence.exceptions.SerializerNotFoundException;
import fr.pederobien.persistence.exceptions.SerializerRegisteredException;
import fr.pederobien.persistence.interfaces.IPersistence;
import fr.pederobien.persistence.interfaces.ISerializer;

public abstract class AbstractPersistence<T, U extends ISerializer<T>> implements IPersistence<T, U> {
	private NavigableMap<Double, U> serializers;
	private String extension;

	/**
	 * Creates a persistence with an empty serializers list associated to a specific format. The extension specified in this
	 * constructor should be complete which means, it should include the dot: <code>.txt</code>
	 * 
	 * @param extension The file extension.
	 */
	protected AbstractPersistence(String extension) {
		this.extension = extension;
		serializers = new TreeMap<Double, U>();
	}

	@Override
	public String getExtension() {
		return extension;
	}

	@Override
	public Double getVersion() {
		return serializers.lastEntry().getKey();
	}

	@Override
	public boolean deserialize(T element, String path) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean serialize(T element, double version, String path) {
		return getSerializer(version).serialize(element, path.endsWith(extension) ? path : path.concat(extension));
	}

	@Override
	public IPersistence<T, U> register(U serializer) {
		ISerializer<T> registered = serializers.get(serializer.getVersion());
		if (registered != null)
			throw new SerializerRegisteredException(registered);

		serializers.put(serializer.getVersion(), serializer);
		return this;
	}

	@Override
	public IPersistence<T, U> unregister(double version) {
		serializers.remove(version);
		return this;
	}

	@Override
	public Map<Double, ? extends U> getSerializers() {
		return serializers;
	}

	@Override
	public U getSerializer(double version) {
		if (version == IPersistence.LATEST) {
			Map.Entry<Double, U> latest = serializers.lastEntry();
			if (latest == null)
				throw new SerializerNotFoundException(version);
			return latest.getValue();
		}

		U loader = serializers.get(version);
		if (loader == null)
			throw new SerializerNotFoundException(version);
		return loader;
	}
}
