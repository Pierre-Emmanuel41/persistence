package fr.pederobien.persistence.impl;

import java.nio.file.Path;

import fr.pederobien.persistence.impl.xml.JarXmlPersistence;
import fr.pederobien.persistence.impl.xml.XmlPersistence;

public class Persistences {

	/**
	 * Creates a new {@link XmlPersistence}. This persistence save/load in the XML format the information contained in the element of
	 * type T.
	 * 
	 * @param <T> The type of element that contains the information to save/load.
	 * 
	 * @return A new XML persistence.
	 */
	public static <T> XmlPersistence<T> xmlPersistence() {
		return new XmlPersistence<T>();
	}

	/**
	 * Creates a new {@link JarXmlPersistence}. This persistence load the information contained in an XML file registered in a jar
	 * file.
	 * 
	 * @param <T>     The type of element whose the properties should be updated.
	 * @param jarPath The path leading to the jar file.
	 * 
	 * @return A new jar XML persistence.
	 */
	public static <T> JarXmlPersistence<T> jarXmlPersistence(Path jarPath) {
		return new JarXmlPersistence<T>(jarPath);
	}
}
