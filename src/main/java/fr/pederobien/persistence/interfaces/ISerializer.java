package fr.pederobien.persistence.interfaces;

public interface ISerializer<T> {

	/**
	 * @return The version of this serializer.
	 */
	Double getVersion();

	/**
	 * Load the file associated to the given path, and update the element properties.
	 * 
	 * @param element The element that contains data registered in the configuration file.
	 * @param path    The path leading to the configuration file. It should contains the file name.
	 */
	void deserialize(T element, String path) throws Exception;

	/**
	 * Save the element properties in a file associated to the specified path.
	 * 
	 * @param element the element that contains informations to save.
	 * @param path    The path leading to the configuration file. It should contains the file name.
	 */
	void serialize(T element, String path) throws Exception;
}
