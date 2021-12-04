package fr.pederobien.persistence.impl.xml;

import java.time.LocalTime;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import fr.pederobien.persistence.interfaces.xml.IXmlSerializer;

public abstract class AbstractXmlSerializer<T> implements IXmlSerializer<T> {
	private Double version;
	private Document document;

	/**
	 * Creates a new XML persistence serializer.
	 * 
	 * @param version The serializer version.
	 */
	public AbstractXmlSerializer(Double version) {
		this.version = version;
	}

	@Override
	public Double getVersion() {
		return version;
	}

	/**
	 * @return The XML document associated to this serializer.
	 * 
	 * @see Document
	 */
	protected Document getDocument() {
		return document;
	}

	/**
	 * Set the document associated to this serializer.
	 * 
	 * @param document The document that represents an XML file.
	 */
	protected void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * Create an element from the given document.
	 * 
	 * @param tag The tag associated the element to create.
	 * @return The element created from the underlying document.
	 * 
	 * @see Document#createElement(String)
	 */
	protected Element createElement(String tag) {
		return document.createElement(tag);
	}

	/**
	 * Create an element from the given document.
	 * 
	 * @param tag The object that represent the tag associated the element to create.
	 * @return The element created from the underlying document.
	 * 
	 * @see Document#createElement(String)
	 */
	protected Element createElement(Object tag) {
		return createElement(tag.toString());
	}

	/**
	 * Creates a <code>Text</code> node given the specified string.
	 * 
	 * @param data The data for the node.
	 * @return The new <code>Text</code> object.
	 * 
	 * @see Document#createTextNode(String)
	 */
	protected Text createTextNode(String data) {
		return document.createTextNode(data);
	}

	/**
	 * Adds a new attribute. If an attribute with that name is already present in the element, its value is changed to be that of the
	 * value parameter. This value is a simple string; it is not parsed as it is being set. So any markup (such as syntax to be
	 * recognized as an entity reference) is treated as literal text, and needs to be appropriately escaped by the implementation when
	 * it is written out. In order to assign an attribute value that contains entity references, the user must create an
	 * <code>Attr</code> node plus any <code>Text</code> and <code>EntityReference</code> nodes, build the appropriate subtree, and
	 * use <code>setAttributeNode</code> to assign it as the value of an attribute. <br>
	 * To set an attribute with a qualified name and namespace URI, use the <code>setAttributeNS</code> method.
	 * 
	 * @param element The element used to add a new attribute.
	 * @param name    The name of the attribute to create or alter.
	 * @param value   Value to set in string form.
	 * @exception DOMException INVALID_CHARACTER_ERR: Raised if the specified name is not an XML name according to the XML version in
	 *                         use specified in the <code>Document.xmlVersion</code> attribute. <br>
	 *                         NO_MODIFICATION_ALLOWED_ERR: Raised if this node is readonly.
	 */
	protected void setAttribute(Element element, String tag, String value) {
		element.setAttribute(tag, value);
	}

	/**
	 * @param element The element used to add a new attribute.
	 * @param tag     The name of the attribute to create or alter.
	 * @param value   Value to set in string form.
	 * 
	 * @see #setAttribute(Element, String, String)
	 */
	protected void setAttribute(Element element, Object tag, Object value) {
		setAttribute(element, tag.toString(), value.toString());
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
		return Integer.parseInt(getStringAttribute(element, name));
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
		return Double.parseDouble(getStringAttribute(element, name));
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
		return Boolean.parseBoolean(getStringAttribute(element, name));
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
		return LocalTime.parse(getStringAttribute(element, name));
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into an integer.
	 * 
	 * @param node The node use to transform its value into an integer.
	 * 
	 * @return An integer.
	 */
	protected int getIntNodeValue(Node node) {
		return Integer.parseInt(node.getNodeValue());
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into a double.
	 * 
	 * @param node The node use to transform its value into an double.
	 * 
	 * @return A double.
	 */
	protected double getDoubleNodeValue(Node node) {
		return Double.parseDouble(node.getNodeValue());
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into a boolean.
	 * 
	 * @param node The node use to transform its value into a boolean.
	 * 
	 * @return A boolean.
	 */
	protected boolean getBooleanNodeValue(Node node) {
		return Boolean.parseBoolean(node.getNodeValue());
	}

	/**
	 * Convert the result returned by {@link Element#getNodeValue()} into a {@link LocalTime}.
	 * 
	 * @param node The node use to transform its value into a local time.
	 * 
	 * @return A local time.
	 */
	protected LocalTime getLocalTimeNodeValue(Node node) {
		return LocalTime.parse(node.getNodeValue());
	}
}
