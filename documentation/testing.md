# Testing documentation

The application has been tested using JUnit testing library with unit and integration tests. System level testing has been done manually on MacOS and Linux environments.

## Unit and integration testing

### Application logic

Part of the application logic is tested by unit tests for [domain classes](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/analytica/src/test/java/analytica/domain) [Account](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/domain/AccountTest.java), [Statistics](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/domain/StatisticsTest.java) and [Regression](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/domain/RegressionTest.java) by testing the relevant class methods. These tests do not include getter, setter, equals or hashCode methods. Event class only contains such methods, so it's only tested through integration tests.

Most of the integration testing is executed by testing classes within [analytica.service](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/analytica/src/test/java/analytica/service) package with [AccountServiceTest](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/service/AccountServiceTest.java), [EventServiceTest](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/service/EventServiceTest.java) and [AnalyticsServiceTest](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/service/AnalyticsServiceTest.java) test classes. These test classes use corresponding mock dao interfaces [FakeSQLAccountDao](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/service/FakeSQLAccountDao.java) (AccountService) and [FakeSQLEventDao](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/service/FakeSQLEventDao.java) (EventService and AnalyticsService) for saving and retrieving information. Especially, AnalyticsService tests Event, Statistics and Regression classes and overall application logic through a wide variety of test cases.

### Dao classes and database

The application uses a test database, _analytica_test_db.mv.db_ for testing dao classes. The test database is identical in structure to the application database, and this way we can achieve reliable test results. [SQLAccountDaoTest](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/dao/SQLAccountDaoTest.java) and [SQLEventDaoTest](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/test/java/analytica/dao/SQLEventDaoTest.java) classes within the test package [analytica.dao](https://github.com/MikaelTornwall/ot-harjoitustyo/tree/master/analytica/src/test/java/analytica/dao) are responsible for testing the dao interface. By using SQLDatabase for creating database tables and retrieving database connection, the functionality of the database and database queries are tested comprehensively.

### Test coverage

Test coverage on the application level 85%. Lowest test coverage is in Event class which is due to the large amount of getters and setters that are not being tested nor reached by the integrations tests. Below is the test coverage report generated with Jacoco:

![test coverage](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/coverage.png?raw=true)

## System level testing

System level testing has been conducted manually on MacOS Mojave 10.14.6 and Linux environments.

### Installation

The application has been downloaded and tested as the [instructions](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/instructions.md) suggests. This included launching the application without an existing database so that the application had to create a new database and database tables.

### Features

All the features described in the [requirements](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/requirements.md) and [instructions](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/instructions.md) have been tested manually. This includes inputting a variety of different invalid inputs, such as text into fields that require numeric values, to make sure that the input validation works and the application runs as intended.

### Existing quality concerns

Input validation is done on the user interface level. Hence system-wise for example Event class allows a situation where the number of opened and not opened accounts does not have to sum up to the number of participants. The data integrity is important for accurate calculations and predictions. This is not a major concern, since the only way to input these values is through the graphical user interface. However, from the quality standpoint this is something that could be enhanced.
