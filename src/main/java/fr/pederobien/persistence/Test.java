package fr.pederobien.persistence;

import java.time.LocalDate;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import fr.pederobien.persistence.impl.Persistences;
import fr.pederobien.persistence.impl.xml.AbstractXmlSerializer;
import fr.pederobien.persistence.impl.xml.XmlPersistence;
import fr.pederobien.persistence.interfaces.IPersistence;

public class Test {

	public static void main(String[] args) {
		String path = "C:\\Users\\pierr\\OneDrive\\Documents\\Workspace\\Eclipse\\Tests\\FakeDirectory\\AnotherFakeDirectory\\";
		// Person born the 5th April 1994.
		Person person = new Person("Main person", LocalDate.of(1994, 4, 5));

		// <path> has to be changed for compilation.
		XmlPersistence<Person> persistence = Persistences.xmlPersistence();
		persistence.register(persistence.adapt(new PersonSerializerV10()));
		persistence.serialize(person, IPersistence.LATEST, path.concat("Main_Person.xml"));

		// To load
		persistence.deserialize(new Person(), path.concat("Main_Person.xml"));
	}

	public static class PersonSerializerV10 extends AbstractXmlSerializer<Person> {

		public PersonSerializerV10() {
			super(1.0);
		}

		@Override
		public boolean deserialize(Person element, Element root) {
			// Set the character name
			Node name = getElementsByTagName(root, "name").item(0);
			element.setName(name.getChildNodes().item(0).getNodeValue());

			// Set the character birthday.
			Node birthday = getElementsByTagName(root, "birthday").item(0);
			element.setBirthday(LocalDate.parse(birthday.getChildNodes().item(0).getNodeValue()));

			return true;
		}

		@Override
		public boolean serialize(Person element, Element root) {
			// Set the character name
			Element name = createElement("name");
			name.appendChild(createTextNode(element.getName()));
			root.appendChild(name);

			// Set the character birthday.
			Element birthday = createElement("birthday");
			birthday.appendChild(createTextNode(element.getBirthday().toString()));
			root.appendChild(birthday);

			return true;
		}
	}

	private static class Person {
		private String name;
		private LocalDate birthday;

		public Person() {
		}

		public Person(String name, LocalDate birthday) {
			this.name = name;
			this.birthday = birthday;
		}

		/**
		 * @return The person name.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Set the character name.
		 * 
		 * @param name The character name.
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return The character birthday.
		 */
		public LocalDate getBirthday() {
			return birthday;
		}

		/**
		 * Set the character birthday.
		 * 
		 * @param birthday The character birthday.
		 */
		public void setBirthday(LocalDate birthday) {
			this.birthday = birthday;
		}
	}
}
