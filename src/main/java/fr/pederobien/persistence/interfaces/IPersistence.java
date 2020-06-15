package fr.pederobien.persistence.interfaces;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public interface IPersistence<T extends IUnmodifiableNominable> {

	/**
	 * @return The path of the folder in which this persistence load and save objects it managed.
	 */
	Path getPath();

	/**
	 * Set the path to the folder in which this persistence should save its data.
	 * 
	 * @param path The path to the folder.
	 */
	void setPath(Path path);

	/**
	 * Load and create an object of type <code>T</code>. If there is no file associated to the given name in the folder associated to
	 * the path returned by {@link #getPath()}, the method throws a FileNotFoundException.
	 * 
	 * @param name The name used to load and create a T object.
	 * 
	 * @return This persistence.
	 * 
	 * @throws FileNotFoundException If there is no file associated to the given name.
	 */
	void load(String name) throws FileNotFoundException;

	/**
	 * @return The object this persistence managed.
	 */
	T get();

	/**
	 * Set the element to load/save.
	 * 
	 * @param elt The element to load/save.
	 * 
	 * @return This persistence.
	 */
	void set(T elt);

	/**
	 * Save the current object managed by this persistence in the folder associated by the path returned by the method
	 * {@link #getPath()}.
	 * 
	 * @return True if everything went right, false otherwise.
	 */
	boolean save();

	/**
	 * Check if there is a file associated to the given name in the folder associated by the path returned by the method
	 * {@link #getPath()}.
	 * 
	 * @param name The name used to verify the existence of a file with the same name.
	 * 
	 * @return True if the file exist, false otherwise.
	 */
	boolean exist(String name);

	/**
	 * Delete the file associated to the given name from the folder associated by the path returned by the method {@link #getPath()}.
	 * 
	 * @param name The name of the file to delete.
	 * 
	 * @return true if the file has been deleted, false otherwise.
	 */
	boolean delete(String name);

	/**
	 * @return The list of file's name in the folder associated by the path returned by the method {@link #getPath()}.
	 */
	List<String> list();

	/**
	 * Return the absolute path associated to the given name.
	 * 
	 * @param name The name used to get the absolute path.
	 * 
	 * @return A path that correspond to <code>getPath().resolve(Paths.get(name + ".xml"))</code>
	 * 
	 * @see #getPath()
	 * @see Paths#get(String, String...)
	 * @see Path#resolve(Path)
	 */
	Path getAbsolutePath(String name);

	/**
	 * @return The current version number of this persistence. This number is used to update the default content when version has
	 *         changed.
	 */
	Double getVersion();

	/**
	 * @return True if the default content should be update whenever the associated file exists, false otherwise. This method should
	 *         be used when persistence is under development.
	 */
	boolean forceUpdate();

	/**
	 * Method called if the method {@link #forceUpdate()} returns true.
	 */
	void update();
}
