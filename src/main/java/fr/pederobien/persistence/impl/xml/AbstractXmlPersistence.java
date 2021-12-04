package fr.pederobien.persistence.impl.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import fr.pederobien.persistence.impl.AbstractPersistence;

public abstract class AbstractXmlPersistence<T, U extends AbstractXmlSerializerAdapter<T>> extends AbstractPersistence<T, U> {
	protected static final String VERSION = "version";
	private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();

	private DocumentBuilder documentBuilder;

	static {
		FACTORY.setIgnoringComments(true);
	}

	/**
	 * Creates persistence that serialize and deserialize information in the XML format.
	 */
	protected AbstractXmlPersistence() {
		super(".xml");
		try {
			documentBuilder = FACTORY.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return The document builder used to parse file and generate XML document.
	 * 
	 * @see Document
	 */
	public DocumentBuilder getDocumentBuilder() {
		return documentBuilder;
	}
}
