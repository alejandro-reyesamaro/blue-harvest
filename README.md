# Introduction

This document presents the main elements of the project Blue-HARVEST Coding assignment.

## Technologies

The following tools are needed for this project:

| Techno     | Version          |
| ---------- | ---------------- |
| JAVA       | 17.0.2 minimum   |
| SQL Server | 16.0.1105        |

## Project setup 

### Data base

This projects use a SQL Server database for data persistance. We assume you have already installed the database server, and tools to run commands on it.

The folder **blue-harvest-db** contains a .NET Database project. If you have Visual Studio 2022 installed, you can open the project and publish the project into the new database. Otherwise, you can follow the steps in this section to create all needed components

**<ins>Database creation and security</ins>**

1- Create a blank database. Let's call it **harvest-db**.

2- All commands in the script **Database.Properties.Dev.NoBuild.sql** to configure the dabase user. 

3- Run all script in the folders **Tables** and **Views** to create tables and views.

### API

We assume you have a JDK installation on your system. We also assume you have set the JAVA_HOME environment variable pointing to your JDK installation (ex. C:\Program Files\Java\jdk-17). Otherwise you can download JAVA JDK from here: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

**<ins>Intall Maven</ins>**

To install maven in your system, dowload it from here: https://maven.apache.org/download.cgi, then sdd the bin directory of the created directory **apache-maven-3.9.6** to the PATH environment variable (ex C:\apache-maven-3.9.6\bin)

Confirm with the following command in a new shell: 

```bash
mvn -v
```

Run the following command to compile the project:

```bash
mvn compile
```

This command will download all required dependecies, and compile the project. You can now build your project:

```bash
mvn package
```

This command will create your **.tar** executable object in the **target** folder, and test your external connexions. Once your delivrable is OK, you can run the application:

```bash
java -jar target/<maven-object-name>.jar
```
