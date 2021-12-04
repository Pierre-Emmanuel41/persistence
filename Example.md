# Example

Let's say we want to save a person. A person is defined by its name and its birthday. The class associated to a person should looks like the following :

```java
	public static class Person {
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
		 * Set the person name.
		 * 
		 * @param name The person name.
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return The person birthday.
		 */
		public LocalDate getBirthday() {
			return birthday;
		}

		/**
		 * Set the person birthday.
		 * 
		 * @param birthday The person birthday.
		 */
		public void setBirthday(LocalDate birthday) {
			this.birthday = birthday;
		}
	}
```

And then you have to create your own serializer which should extends <code>AbsractXmlSerializer</code> :

```java
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
```

and register it in a persistence of your choice:

```java
	XmlPersistence<Person> personPersistence = Persistences.xmlPersistence();
	personPersistence.register(persistence.adapt(new PersonSerializerV10());
```

In order to save the data of a person, you only need to call the method <code>serialize</code> from the persistence, and to load you only need to call method <code>deserialize</code> from the persistence.

```java
	public static void main(String[] args) {
		String path = "";
		// Person born the 5th April 1994.
		Person person = new Person("Main person", LocalDate.of(1994, 4, 5));

		// <path> has to be changed for compilation.
		XmlPersistence<Person> persistence = Persistences.xmlPersistence();
		persistence.register(persistence.adapt(new PersonSerializerV10()));
		
		// To save
		persistence.serialize(person, IPersistence.LATEST, path.concat("Main_Person.xml"));

		// To load
		persistence.deserialize(new Person(), path.concat("Main_Person.xml"));
	}
```