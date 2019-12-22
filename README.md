## Analytica

Application is intended for the use of an imaginary company that organizes investment events.
It provides an interface for collecting and analyzing data that is most relevant to their core business.

## 1. Documentation

[Instructions](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/instructions.md)

[Requirements](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/requirements.md)

[Architecture](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/architecture.md)

[Testing](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/testing.md)

[Work hour log](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/workhours.md)

## 2. How to

### 2.1. Initialize

1. Clone the project to your local machine
2. Navigate to "analytica" folder

### 2.2. Run the application

Run the following command:

`mvn compile exec:java -Dexec.mainClass=analytica.Main`

### 2.3. Run the tests

Run the following command:

`mvn test`

### 2.4. Create and view the test report

Run the following command:

`mvn test jacoco:report`

After creating the test report, you can view it by opening the following file in your browser _target/site/jacoco/index.html_

### 2.5. Checkstyle

Run the following command:

`mvn jxr:jxr checkstyle:checkstyle`

Then open file _target/site/checkstyle.html_ in your browser to see the results

### 2.6. Generate and open .jar file

Run the following command:

`mvn package`

Then navigate to target and run the following command:

`open analytica-1.0-SNAPSHOT.jar`

_Notice: the name of the generated .jar file may be different. Navigate to /target/ and select the file with the filename extension .jar_  

### 2.7. JavaDoc

Run the following command:

`mvn javadoc:javadoc`

Then open file _target/site/apidocs/index.html_ in your browser to see the results

## 3. Releases

[Week 5](https://github.com/MikaelTornwall/ot-harjoitustyo/releases/tag/week5)

[Week 6](https://github.com/MikaelTornwall/ot-harjoitustyo/releases/tag/week6)

[Final release](https://github.com/MikaelTornwall/ot-harjoitustyo/releases/tag/loppupalautus)
