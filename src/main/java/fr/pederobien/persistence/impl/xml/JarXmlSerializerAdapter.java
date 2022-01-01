package fr.pederobien.persistence.impl.xml;

public class JarXmlSerializerAdapter<T> extends AbstractXmlSerializerAdapter<T> {

	/**
	 * Creates an XML file serializer adapter in order to register the specified serializer in a {@link JarXmlPersistence}.
	 * 
	 * @param serializer The serializer for the XML format.
	 */
	protected JarXmlSerializerAdapter(AbstractXmlSerializer<T> serializer) {
		super(serializer);
	}

	@Override
	public void serialize(T element, String path) throws Exception {
		throw new UnsupportedOperationException();
	}
}
