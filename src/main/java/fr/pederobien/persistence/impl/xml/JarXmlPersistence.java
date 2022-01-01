package fr.pederobien.persistence.impl.xml;

import java.io.IOException;
import java.nio.file.Path;
import java.util.jar.JarFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.pederobien.persistence.exceptions.ExtensionException;

public class JarXmlPersistence<T> extends AbstractXmlPersistence<T, JarXmlSerializerAdapter<T>> {
	private Path jarPath;

	/**
	 * Creates a jar persistence in order load XML file contained in the jar associated to the given path.
	 * 
	 * @param jarPath The path leading to the jar file.
	 */
	public JarXmlPersistence(Path jarPath) {
		this.jarPath = jarPath;
	}

	@Override
	public void deserialize(T element, String path) throws Exception {
		if (!path.endsWith(getExtension()))
			throw new ExtensionException(getExtension(), path.substring(path.lastIndexOf(".")));

		JarFile jar = null;
		try {
			jar = new JarFile(jarPath.toFile());

			Document document = getDocumentBuilder().parse(jar.getInputStream(jar.getJarEntry(path)));
			Element root = document.getDocumentElement();
			Node version = root.getElementsByTagName(VERSION).item(0);

			// Step 1: Get the loader associated to the version found in the XML file.
			JarXmlSerializerAdapter<T> adapter = getSerializer(Double.parseDouble(version.getChildNodes().item(0).getNodeValue()));

			// Step 2: Set the loader's document
			adapter.getXmlSerializer().setDocument(document);

			// Step 2: Update the element properties
			adapter.getXmlSerializer().deserialize(element, root);
		} finally {
			if (jar != null)
				try {
					jar.close();
				} catch (IOException e) {
					// Do nothing
				}
		}
	}

	/**
	 * Creates an adapter in order to register the given loader to this persistence.
	 * 
	 * @param loader The loader that transform information contained in an element into a XML document.
	 * 
	 * @return A new adapter.
	 */
	public JarXmlSerializerAdapter<T> adapt(AbstractXmlSerializer<T> serializer) {
		return new JarXmlSerializerAdapter<T>(serializer);
	}
}
