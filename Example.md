# Example

Let's say we want to save a character. A character is defined by its name and its birthday. The class associated to a character should looks like the following :

```java
	public class Character implements IUnmodifiableNominable {
		private String name;
		private LocalDate birthday;
	
		public Character() {
		}
	
		public Character(String name, LocalDate birthday) {
			this.name = name;
			this.birthday = birthday;
		}
	
		@Override
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
```

I created an intermediate class from which each loader will inherits :

```java
	public abstract class AbstractIntermediateXmlLoader extends AbstractXmlPersistenceLoader<Character> {
	
		protected AbstractIntermediateXmlLoader(Double version) {
			super(version);
		}
	
		@Override
		protected Character create() {
			return new Character();
		}
	
		/**
		 * Set the character name.
		 * 
		 * @param root The xml root that contains all character properties.
		 */
		protected void setName(Element root) {
			Node name = getElementsByTagName(root, "name").item(0);
			get().setName(name.getChildNodes().item(0).getNodeValue());
		}
	
		/**
		 * Set the character birhtday.
		 * 
		 * @param root The xml root that contains all character properties.
		 */
		protected void setBirthday(Element root) {
			Node birthday = getElementsByTagName(root, "birthday").item(0);
			get().setBirthday(LocalDate.parse(birthday.getChildNodes().item(0).getNodeValue()));
		}
	}
```

And then I created the loader for the first version :

```java
	public class CharacterLoaderV10 extends AbstractIntermediateXmlLoader {
	
		protected CharacterLoaderV10() {
			super(1.0);
		}
	
		@Override
		public IXmlPersistenceLoader<Character> load(Element root) {
			// Set the character name
			setName(root);
	
			// Set the character birthday.
			setBirthday(root);
			return this;
		}
	}
```

Finally, I created the character persistence :

```java
	public class CharacterPersistence extends AbstractXmlPersistence<Character> {
	
		public CharacterPersistence(Path path) {
			super(path);
			register(new CharacterLoaderV10());
		}
	
		@Override
		public boolean save() {
			Document doc = newDocument();
			doc.setXmlStandalone(true);
	
			Element root = createElement(doc, "character");
			doc.appendChild(root);
	
			Element version = createElement(doc, VERSION);
			doc.appendChild(doc.createTextNode(getVersion().toString()));
			root.appendChild(version);
	
			Element name = createElement(doc, "name");
			name.appendChild(doc.createTextNode(get().getName()));
			root.appendChild(name);
	
			Element birthday = createElement(doc, "birthday");
			birthday.appendChild(doc.createTextNode(get().getBirthday().toString()));
			root.appendChild(birthday);
	
			saveDocument(doc, get().getName());
			return false;
		}
	
		@Override
		protected Document createDoc(Object... objects) throws IOException {
			return parseFromFileName((String) objects[0]);
		}
	}
```

In order to save a character you only need to create a character, set the new character as the one to save for you persistence and finally call the method save :

```java
	public static void main(String[] args) {
		// Character born at 5th April 1994.
		Character character = new Character("Main character", LocalDate.of(1994, 4, 5));
		
		// <path> has to be changed for compilation.
		IPeristence<Character> persistence = new CharacterPersistence(<path>);
		persistence.set(character);
		persistence.save();
		
		// To load
		persistence.load("Main character");
		Character loadCharacter = persistence.get();
	}
```