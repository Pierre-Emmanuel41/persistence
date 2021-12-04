# Persistence

This project is a simple tool that simplify the way to use java xml API. It is useful when you need to save and/or load data from an xml file.
This api provides also a simple management of the save version number.

# Register as maven dependency

It is easy to register this project as dependency for your own project. To do so, you need to download this project.
First, you need to download this project on your machine. The easiest way to do so is to use the following git command line "git clone https://github.com/Pierre-Emmanuel41/persistence.git".
Then, you need to run the following maven command line "mvn clean package install". This will create the archive of this project in your .m2 folder.
Finally, in the pom.xml of your project, you have to add the following lines :

```xml
<dependency>
	<groupId>fr.pederobien</groupId>
	<artifactId>persistence</artifactId>
	<version>2.0-SNAPSHOT</version>
</dependency>
```
You can now use features provided by this api in you project.

To see how you can use thoses features, please have a look to [This tutorial](https://github.com/Pierre-Emmanuel41/persistence/blob/master/Tutorial.md)
