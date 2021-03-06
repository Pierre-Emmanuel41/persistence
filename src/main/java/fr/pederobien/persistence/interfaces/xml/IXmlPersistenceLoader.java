package fr.pederobien.persistence.interfaces.xml;

import org.w3c.dom.Element;

import fr.pederobien.persistence.interfaces.IPersistenceLoader;

public interface IXmlPersistenceLoader<T> extends IPersistenceLoader<T> {

	/**
	 * Load data coming from the given root.
	 * 
	 * @param root The node that correspond to the root of an xml document.
	 * 
	 * @return this loader.
	 */
	IXmlPersistenceLoader<T> load(Element root);
}
