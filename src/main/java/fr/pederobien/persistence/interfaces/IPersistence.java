package fr.pederobien.persistence.interfaces;

import java.util.Map;

import fr.pederobien.persistence.exceptions.ExtensionException;
import fr.pederobien.persistence.exceptions.SerializerNotFoundException;
import fr.pederobien.persistence.exceptions.SerializerRegisteredException;

public interface IPersistence<T, U extends ISerializer<T>> {
	public static final int LATEST = -1;

	/**
	 * @return The file extension associated to this persistence.
	 */
	String getExtension();

	/**
	 * @return The version associated to this persistence, it correspond to the latest registered {@link ISerializer}.
	 */
	Double getVersion();

	/**
	 * Load the file associated to the given path, and update the element properties.
	 * 
	 * @param element The element that contains data registered in the configuration file.
	 * @param path    The path leading to the configuration file. It should contains the file name and the extension.
	 * 
	 * @return True if the element has been successfully updated, false otherwise.
	 * 
	 * @throws ExtensionException If the extension associated to the file to deserialize does not match with the extension of this
	 *                            persistence.
	 */
	boolean deserialize(T element, String path);

	/**
	 * Save the element properties in a file associated to the specified path. If the path does not end with the extension associated
	 * to this persistence it is automatically added. If some intermediate directories are missing they are automatically created.
	 * 
	 * @param element the element that contains informations to save.
	 * @param version The version in which the informations should be saved.
	 * @param path    The path leading to the configuration file. It should contains the file name.
	 * 
	 * @return True if the save went well.
	 * 
	 * @throws SerializerNotFoundException If there are no serializer associated to the given version.
	 */
	boolean serialize(T element, double version, String path);

	/**
	 * Register this serializer to this persistence. If there is already a serializer associated to the version of the given
	 * serializer, the old serializer will be replaced.
	 * 
	 * @param serializer The serializer used to load data.
	 * 
	 * @return This persistence to register serializers easier.
	 * 
	 * @throws SerializerRegisteredException If a serializer is already registered for the version of the specified serializer.
	 */
	IPersistence<T, U> register(U serializer);

	/**
	 * Unregister the serializer associated to the given version.
	 * 
	 * @param version The version of the serializer to unregister.
	 * 
	 * @return This persistence to unregister serializers easier.
	 */
	IPersistence<T, U> unregister(double version);

	/**
	 * @return A map that contains all registered serializers to this persistence. This map is unmodifiable.
	 */
	Map<Double, ? extends U> getSerializers();

	/**
	 * Get the serializer associated to the given version.
	 * 
	 * @param version The version used to get the associated serializer.
	 * 
	 * @return The associated serializer.
	 * 
	 * @throws SerializerNotFoundException If there is no registered serializer for the given version number
	 */
	U getSerializer(double version);
}
