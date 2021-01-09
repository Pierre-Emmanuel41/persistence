package fr.pederobien.persistence.interfaces;

import java.util.Map;

import fr.pederobien.persistence.exceptions.LoaderNotFoundException;

public interface ILoadersPersistence<T, U extends IPersistenceLoader<T>> extends IPersistence<T> {

	/**
	 * Register this loader to this persistence. If there is already a loader associated to the version of the given loader, the old
	 * loader will be replaced.
	 * 
	 * @param loader The loader used to load data.
	 * 
	 * @return This persistence to register loaders easier.
	 */
	ILoadersPersistence<T, U> register(U loader);

	/**
	 * Unregister the loader associated to the given version.
	 * 
	 * @param version The version of the loader to unregister.
	 * 
	 * @return This persistence to unregister loaders easier.
	 */
	ILoadersPersistence<T, U> unregister(Double version);

	/**
	 * @return A map that contains all registered loaders to this persistence. This map is unmodifiable.
	 */
	Map<Double, U> getLoaders();

	/**
	 * Get the loader associated to the given version.
	 * 
	 * @param version The version used to get the associated loader.
	 * 
	 * @return The associated loader.
	 * 
	 * @throws LoaderNotFoundException If there is no registered loader for the given version number
	 */
	U getLoader(Double version);
}
