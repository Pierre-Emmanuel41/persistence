# How to use this API

First you need to create an object to save. There is however one condition to respect, the object need to implement the interface <code>IUnmodifiableNominable</code>

```java
	public class ObjectToSaveAndLoad implements IUnmodifiableNominable {
		private String name;
		
		public ObjectToSaveAndLoad(String name) {
			this.name = name;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
```

Then you need to create a persistence object. There is no factory, you simply need to extend the object <code>AbstractXmlPersistence</code>.
Once this has been done, you should have the following lines:

```java
	public class Persistence extends AbstractXmlPersistence<ObjectToSaveAndLoad> {
	
		protected Persistence(Path path) {
			super(path);
		}
	
		@Override
		public boolean save() {
			return false;
		}
	
		@Override
		protected Document createDoc(Object... objects) throws IOException {
			return null;
		}
	}
```

Some explanations are needed :

  * The path parameter in the constructor is the path used to save your data. This path cannot be changed.
  * The save method is used to save your data.
  * The createDoc method allow you to precise how to create an xml <code>Document</code> in order to save your data.

For the last point, there are several protected methods that allow you to create a Document. (see methods parseXXX)

As you can see, you still cannot load you data. You need to use another object : A persistence loader. This loader has to extends <code>AbstractXmlPersistenceLoader</code>
You should get the following line

```java
	public class Loader extends AbstractXmlPersistenceLoader<ObjectToSaveAndLoad> {
	
		protected Loader(Double version) {
			super(version);
		}
	
		@Override
		public IXmlPersistenceLoader<ObjectToSaveAndLoad> load(Element root) {
			return null;
		}
	
		@Override
		protected ObjectToSaveAndLoad create() {
			return new ObjectToSave();
		}
	}
```
Some explanations are needed :

  * The version parameter in the constructor is the version number of the algorithm that save the object.
  * The load method is used to load your data. The root parameter correspond to the root of the xml document.
  * The create method allow you to precise how to create the object to create and then to modify using the content of the xml file.
  
Now that both objects (Persistence and loader) are created, we only need the add the loader to the persistence. It can be done in the persistence constructor but also in another part of your code. For me, I think the best would be in the persistence constructor.
Here is the result :

```java
	protected Persistence(Path path) {
		super(path);
		register(new Loader(1.0));
	}
```

# Best practices

First, in the saved xml file, you have to furnish a mandatory tag : version. This tag is used to find the correct loader that will fill the object associated to the xml file.

Second, at the end of the method <code>save()</code>, you will have to write the following line : <code>saveDocument(doc, name);</code> This will use java xml API to effectively save the xml document.

Third, but optional, you can create an intermediate class between the <code>AbstractXmlPersistenceLoader</code> and the <code>Loader</code>. In this class you can override the method <code>create()</code> and put all common code for your different xml loaders. Doing so will reduce the amount of code, and in each loader there is only the method <code>load(Element root)</code> is implemented which is easier to read.

To see a concrete example, please have a look [here](https://github.com/Pierre-Emmanuel41/persistence/blob/master/Example.md)