# Application architecture

## Structure

The application structure follows a three layer architecture:

## User interface

The user interface consists of five views:

- Login
- Register
- Dashboard (list of events)
- Event (displays event specific statistical and regression values)
- New event

Login and Register have their own Scene objects, where as Dashboard, Event and New event are set as BorderPane objects into a LoggedInScene, that is responsible for all of the three objects, and can be managed through the Menu object that is displayed while the user is logged in.

The user interface is built within the class [analytica.ui.analyticaUI](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/main/java/analytica/ui/AnalyticaUI.java), and it uses the components located within the folder [analytica.ui](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/main/java/analytica/ui/). The user interface is responsible only for the UI components and event handling.

## Application logic

### Basic class structure

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/analytica_class.png?raw=true)

Account and Event classes are responsible for the logical data modeling within the application. Statistics and Regression classes are responsible for making models and calculations from the event data.

AnalyticaService class is responsible for the functional application logic. It contains several methods, that connect user inputs and request to the DAO classes, AccountDAO and EventDAO, and through them to the database. It is responsible for example login, user creation and event listing logic by fetching information from the database using DAO interfaces with given parameters and returning this data to the user interface.

## Most important features

### Create user

User can be created by clicking the Create New User button on the application login page and then feeding in username and password. After the user clicks the Add button, the following actions are going to be executed:

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/analytica_sequence.png?raw=true)

After this the user will be directed to the login scene, where they can use their newly created credential to access the tool.

## Database

Application uses SQL database through H2 database engine.

The database has two tables, Account and Event.

These classes follow Data Access Object design pattern. Two DAO classes are used, AccountDAO and EventDAO.
