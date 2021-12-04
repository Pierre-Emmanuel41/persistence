# Presentation

This project is a simple tool that simplify the way to use java xml API. It is useful when you need to save and/or load data from an xml file.
This api provides also a simple management of the save version number.

# Download

First you need to download this project on your computer. To do so, you can use the following command line :

```git
git clone https://github.com/Pierre-Emmanuel41/persistence.git --recursive
```

and then double click on the deploy.bat file. This will deploy this project and all its dependencies on your computer. Which means it generates the folder associated to this project and its dependencies in your .m2 folder. Once this has been done, you can add the project as maven dependency on your maven project :

```xml
<dependency>
	<groupId>fr.pederobien</groupId>
	<artifactId>persistence</artifactId>
	<version>2.0-SNAPSHOT</version>
</dependency>
```

To see how you can use thoses features, please have a look to [This tutorial](https://github.com/Pierre-Emmanuel41/persistence/blob/master/Tutorial.md)
