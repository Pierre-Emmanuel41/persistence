package fr.pederobien.persistence.impl.xml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import fr.pederobien.persistence.impl.AbstractSerializer;
import fr.pederobien.persistence.interfaces.ISerializer;

public abstract class AbstractXmlSerializerAdapter<T> extends AbstractSerializer<T> implements ISerializer<T> {
	protected static final String ROOT = "root";
	protected static final String VERSION = "version";
	private AbstractXmlSerializer<T> xmlSerializer;

	/**
	 * Creates an adapter in order to register the given serializer in a persistence.
	 * 
	 * @param serializer The underlying serializer.
	 */
	protected AbstractXmlSerializerAdapter(AbstractXmlSerializer<T> serializer) {
		super(serializer.getVersion());
		this.xmlSerializer = serializer;
	}

	/**
	 * @return The underlying XML serializer.
	 */
	public AbstractXmlSerializer<T> getXmlSerializer() {
		return xmlSerializer;
	}

	/**
	 * Get a file associated to the given XML document and the given path. The path should contains the xML file name and its
	 * extension.
	 * 
	 * @param document The document to save.
	 * @param path     The path leading to the XML file.
	 * 
	 * @return The file at the specified path and the specified content.
	 * 
	 * @throws TransformerConfigurationException When it is not possible to create a <code>Transformer</code> instance.
	 * @throws TransformerException              If an unrecoverable error occurs during the course of the transformation.
	 * @throws IOException                       If an I/O error occurred
	 */
	protected File createFile(Document document, String path) throws TransformerFactoryConfigurationError, TransformerException, IOException {
		Path pathFile = Paths.get(path);
		File folder = pathFile.getParent().toFile();
		if (!folder.exists())
			folder.mkdirs();
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(new DOMSource(document), new StreamResult(pathFile.toFile()));
		return folder;
	}
}
