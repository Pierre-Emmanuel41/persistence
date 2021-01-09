package fr.pederobien.persistence.impl;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import fr.pederobien.persistence.exceptions.LoaderNotFoundException;
import fr.pederobien.persistence.interfaces.ILoadersPersistence;
import fr.pederobien.persistence.interfaces.IPersistenceLoader;

public abstract class AbstractLoadersPersistence<T, U extends IPersistenceLoader<T>> extends AbstractPersistence<T> implements ILoadersPersistence<T, U> {
	private NavigableMap<Double, U> loaders;

	protected AbstractLoadersPersistence(Path path) {
		super(path);
		loaders = new TreeMap<Double, U>();
	}

	@Override
	public ILoadersPersistence<T, U> register(U loader) {
		loaders.put(loader.getVersion(), loader);
		return this;
	}

	@Override
	public ILoadersPersistence<T, U> unregister(Double version) {
		loaders.remove(version);
		return this;
	}

	@Override
	public Map<Double, U> getLoaders() {
		return Collections.unmodifiableMap(loaders);
	}

	@Override
	public U getLoader(Double version) {
		U loader = loaders.get(version);
		if (loader == null)
			throw new LoaderNotFoundException(version);
		return loader;
	}

	@Override
	public Double getVersion() {
		return loaders.lastEntry().getKey();
	}
}
