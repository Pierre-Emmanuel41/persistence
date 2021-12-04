package fr.pederobien.persistence.impl.xml;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.pederobien.persistence.exceptions.ExtensionException;

public class XmlPersistence<T> extends AbstractXmlPersistence<T, XmlSerializerAdapter<T>> {

	@Override
	public boolean deserialize(T element, String path) {
		if (!path.endsWith(getExtension()))
			throw new ExtensionException(getExtension(), path.substring(path.lastIndexOf(".")));

		try {
			Document document = getDocumentBuilder().parse(new File(path));
			Element root = document.getDocumentElement();
			Node version = root.getElementsByTagName(VERSION).item(0);

			// Step 1: Get the loader associated to the version found in the XML file.
			XmlSerializerAdapter<T> adapter = getSerializer(Double.parseDouble(version.getChildNodes().item(0).getNodeValue()));

			// Step 2: Set the loader's document
			adapter.getXmlSerializer().setDocument(document);

			// Step 2: Update the element properties
			return adapter.getXmlSerializer().deserialize(element, root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Creates an adapter in order to register the given serializer to this persistence.
	 * 
	 * @param serializer The serializer associated to the created adapter.
	 * 
	 * @return A new adapter.
	 */
	public XmlSerializerAdapter<T> adapt(AbstractXmlSerializer<T> serializer) {
		return new XmlSerializerAdapter<T>(serializer, this);
	}
}
