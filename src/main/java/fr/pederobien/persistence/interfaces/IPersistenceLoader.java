package fr.pederobien.persistence.interfaces;

public interface IPersistenceLoader<T> {

	/**
	 * @return The version of this loader.
	 */
	Double getVersion();

	/**
	 * @return The object that the loader has loaded.
	 */
	T get();
}
