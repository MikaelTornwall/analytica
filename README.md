## Analytica

Application is intended for the use of an imaginary company that organizes investment events.
It provides an interface for collecting and analyzing data that is most relevant to their core business.

## 1. Documentation

[Requirements](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/requirements.md)

[Work hour log](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/workhours.md)

[Architecture](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/documentation/architecture.md)

## 2. How to

### 2.1. Initialize

1. Clone the project to your local machine
2. Navigate to "analytica" folder

### 2.2 Run the application

Type the following to your command line

`mvn compile exec:java -Dexec.mainClass=analytica.Main`

### 2.3. Run the tests

Type the following to your command line

`mvn test`

### 2.4. Create and view the test report

Type the following to your command line

`mvn test jacoco:report`

After creating the test report, you can view it by opening the following file in your browser target/site/jacoco/index.hmtl
