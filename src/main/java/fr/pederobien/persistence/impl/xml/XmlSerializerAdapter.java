package fr.pederobien.persistence.impl.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlSerializerAdapter<T> extends AbstractXmlSerializerAdapter<T> {
	private XmlPersistence<T> persistence;

	/**
	 * Creates an XML file serializer adapter in order to register the specified serializer in the given persistence.
	 * 
	 * @param serializer  The serializer for the XML format.
	 * @param persistence The persistence to which this adapter belongs.
	 */
	protected XmlSerializerAdapter(AbstractXmlSerializer<T> serializer, XmlPersistence<T> persistence) {
		super(serializer);
		this.persistence = persistence;
	}

	@Override
	public void serialize(T element, String path) throws Exception {
		Document document = persistence.getDocumentBuilder().newDocument();
		document.setXmlStandalone(true);
		getXmlSerializer().setDocument(document);

		Element root = getXmlSerializer().createElement(ROOT);
		document.appendChild(root);

		Element version = getXmlSerializer().createElement(VERSION);
		version.appendChild(getXmlSerializer().createTextNode(getVersion().toString()));
		root.appendChild(version);

		getXmlSerializer().serialize(element, root);
		createFile(document, path);
	}
}
