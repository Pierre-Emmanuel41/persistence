package fr.pederobien.persistence.interfaces.xml;

import org.w3c.dom.Element;

public interface IXmlSerializer<T> {

	/**
	 * @return The version of this loader.
	 */
	Double getVersion();

	/**
	 * Load data coming from the given root.
	 * 
	 * @param element The element that contains informations to save.
	 * @param root    The node that correspond to the root of an XML document.
	 * 
	 * @return True if the load went successfully, false otherwise.
	 */
	boolean deserialize(T element, Element root);

	/**
	 * Load data coming from the given root.
	 * 
	 * @param element the element to update according to information prent in the XML file.
	 * @param root    The node that correspond to the root of an XML document.
	 * 
	 * @return True if the save went successfully, false otherwise.
	 */
	boolean serialize(T element, Element root);
}
