# How to use this API (XML)

First you need to create an object to save.

```java
	public class ObjectToSerializeAndDeserialize {
		
		public ObjectToSerializeAndDeserialize() {
		}
	}
```

Then you have to create your own serializer that has to extends <code>AbstractXmlSerializer</code>
You should get the following line

```java
	public class SerializerV10 extends AbstractXmlSerializer<ObjectToSerializeAndDeserialize> {
	
		protected SerializerV10() {
			super(1.0);
		}
	
		@Override
		public boolean serialize(ObjectToSerializeAndDeserialize element, Element root) {
			return false;
		}
	
		@Override
		public boolean deserialize(ObjectToSerializeAndDeserialize element, Element root) {
			return false;
		}
	}
```

Some explanations are needed :

  * The version parameter in the constructor is the version number of the algorithm that save the object.
  * The serialize method is used to save your data. The element parameter is a container for your data to save and the root parameter correspond to the root of the xml document.
  * The deserialize method is used to load your data. The element parameter is a container for your data to save and the root parameter correspond to the root of the xml document.
  
Now that your <code>serializer</code> is created, we only need the add it to an XML persistence. Here is the result :

```java
	XmlPersistence<ObjectToSerializeAndDeserialize> persistence = Persistences.xmlPersistence();
	persistence.register(persistence.adapt(new SerializerV10()));
```

To see a concrete example, please have a look [here](https://github.com/Pierre-Emmanuel41/persistence/blob/master/Example.md)