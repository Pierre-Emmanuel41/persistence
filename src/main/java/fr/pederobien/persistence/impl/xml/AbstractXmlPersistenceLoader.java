package fr.pederobien.persistence.impl.xml;

import java.time.LocalTime;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.pederobien.persistence.impl.AbstractPersistenceLoader;
import fr.pederobien.persistence.interfaces.IUnmodifiableNominable;
import fr.pederobien.persistence.interfaces.xml.IXmlPersistenceLoader;

public abstract class AbstractXmlPersistenceLoader<T extends IUnmodifiableNominable> extends AbstractPersistenceLoader<T> implements IXmlPersistenceLoader<T> {

	protected AbstractXmlPersistenceLoader(Double version, T elt) {
		super(version, elt);
	}

	/**
	 * Returns a <code>NodeList</code> of all descendant <code>Elements</code> with a given tag name, in document order.
	 * 
	 * @param element The element used to search its descendant.
	 * @param name    The name of the tag to match on. The special value "*" matches all tags.
	 * 
	 * @return A list of matching <code>Element</code> nodes.
	 */
	protected NodeList getElementsByTagName(Element element, Object name) {
		return element.getElementsByTagName(name.toString());
	}

	/**
	 * Retrieves an attribute value by name.
	 * 
	 * @param element The element used to search attributes.
	 * @param name    The name of the attribute to retrieve.
	 * 
	 * @return The <code>Attr</code> value as a string, or the empty string if that attribute does not have a specified or default
	 *         value.
	 */
	protected String getStringAttribute(Element element, Object name) {
		return element.getAttribute(name.toString());
	}

	/**
	 * Convert the result returned by {@link #getStringAttribute(Element, Object)} into an integer.
	 * 
	 * @param element The element used to search attributes.
	 * @param name    The name of the attribute to retrieve.
	 * 
	 * @return An integer.
	 */
	protected int getIntAttribute(Element element, Object name) {
		return toInt(getStringAttribute(element, name));
	}

	/**
	 * Convert the result returned by {@link #getStringAttribute(Element, Object)} into a double.
	 * 
	 * @param element The element used to search attributes.
	 * @param name    The name of the attribute to retrieve.
	 * 
	 * @return An integer.
	 */
	protected double getDoubleAttribute(Element element, Object name) {
		return toDouble(getStringAttribute(element, name));
	}

	/**
	 * Convert the result returned by {@link #getStringAttribute(Element, Object)} into a boolean.
	 * 
	 * @param element The element used to search attributes.
	 * @param name    The name of the attribute to retrieve.
	 * 
	 * @return A boolean.
	 */
	protected boolean getBooleanAttribute(Element element, Object name) {
		return toBool(getStringAttribute(element, name));
	}

	/**
	 * Convert the result returned by {@link #getStringAttribute(Element, Object)} into a {@link LocalTime}.
	 * 
	 * @param element The element used to search attributes.
	 * @param name    The name of the attribute to retrieve.
	 * 
	 * @return A LocalTime.
	 */
	protected LocalTime getLocalTimeAttribute(Element element, Object name) {
		return toLocalTime(getStringAttribute(element, name));
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into an integer.
	 * 
	 * @param node The node use to transform its value into an integer.
	 * 
	 * @return An integer.
	 */
	protected int getIntNodeValue(Node node) {
		return toInt(node.getNodeValue());
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into a double.
	 * 
	 * @param node The node use to transform its value into an double.
	 * 
	 * @return A double.
	 */
	protected double getDoubleNodeValue(Node node) {
		return toDouble(node.getNodeValue());
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into a boolean.
	 * 
	 * @param node The node use to transform its value into a boolean.
	 * 
	 * @return A boolean.
	 */
	protected boolean getBooleanNodeValue(Node node) {
		return toBool(node.getNodeValue());
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into a {@link LocalTime}.
	 * 
	 * @param node The node use to transform its value into a local time.
	 * 
	 * @return A local time.
	 */
	protected LocalTime getLocalTimeNodeValue(Node node) {
		return toLocalTime(node.getNodeValue());
	}
}
