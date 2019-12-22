# Application architecture

## Structure

The application structure follows a three layer architecture:

![package structure](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/packagestructure.png?raw=true)

The top layer is the graphical user interface that is built with JavaFX components. The middle-layer consists of domain classes Account and Event that are the application entities and Statistics and Regression that provide methods for calculations, and of service classes that handle the application logic. The top and middle layers use the bottom layer dao, that is responsible for saving and retrieving information to and from the database.

## User interface

The user interface consists of five views:

- Login
- Register
- Dashboard
- Events
- New event

Login and Register have their own Scene objects, whereas Dashboard, Events and New event are set as BorderPane objects into a LoggedInScene, that is responsible for all of the three objects, and can be managed through the Menu object that is displayed while the user is logged in.

The user interface is built within the class [analytica.ui.analyticaUI](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/main/java/analytica/ui/AnalyticaUI.java), and it uses the components located within the folder [analytica.ui](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/analytica/src/main/java/analytica/ui/). The user interface is responsible for the UI components, event handling and input validation.

## Application logic

### Basic class structure

The domain package contains four classes, Account, Event, Statistics and Regression:

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/domainclasses.png?raw=true)

The Account and Event classes are responsible for the implementation and representation of the entity data. Statistics and Regression classes are responsible for making models and calculations from the event data.

The AccountService class is responsible for the application logic related to the Account class. It contains several methods, that connect user inputs and requests to the SQLAccountDao class, and through it to the database in form of database queries. It is responsible for account management logic by fetching information from the database with given parameters and delivering this data to the user interface.

The EventService class is responsible for the application logic related to the Event class. It contains several methods, that connect user inputs and requests to the SQLEventDao class, and through it to the database in form of database queries. It is responsible for event management logic by fetching information from the database with given parameters and delivering this data to the user interface.

The AnalyticsService class invokes and delivers calculations made using event data, that it retrieves using services provided by the EventService class, for the use of Statistics and Regression classes, which in turn use this data to conduct relevant calculations through specific methods within the AnalyticsService class. AnalyticsService defines which event data is to be used and its methods prepare this data for the Statistics and Regression classes.

Below is a more detailed class/package diagram representing the most relevant relationships between domain, service and dao packages and classes within these packages:

![extended package diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/extendedpackagestruct.png?raw=true)

## Most important features

### Create user

A User can be created by clicking the Create new user button on the application login page and then feeding in username and password. After the user clicks the Add button, the following actions are going to be executed to create a new user account:

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/create_sequence.png?raw=true)

After this, the user will be directed to the login scene, where they can use their newly created credential to access the tool.

### Login

The User can log in on the login view by typing valid credentials into login form and clicking login button. After the user clicks the Login button, the following actions are going to be executed for the user to be able to access the tool:

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/login_sequence.png?raw=true)

After this, the user will be directed to the application dashboard.

### New event

The User can create a new event in the events view by typing the event information and clicking the Add event button. After clicking the button, the following actions are being executed to create a new event:

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/newevent_sequence.png?raw=true)

After a successful event creation the user can view event details in the Events view and view updated statistics on the dashboard view.

## Database

Application uses SQL database through H2 database engine.

The database has two database tables, Account and Event, which contain the following information:

![database tables](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/databasetables.png?raw=true)

These two tables are independent, i.e. there is no dependency relationship between the two tables through foreign keys.

Both Account and Event have corresponding classes for saving and retrieving data to and from the SQL database. These classes are SQLAccountDao, which implements the AccountDao interface, and SQLEventDao, which implements the EventDao interface.

## Structural deficiencies

__Input validation__
- Some of the input validation messages could be more specific and specified for a specific input field.
- Validation is done only on the user interface level

__User interface__
- Dashboard class could be divided into several smaller classes that are responsible for the individual UI components
- Input validation could be done in one static class
- Rendering logic for more than 16 events in EventList could be done more properly by pagination (or page scroll)
  - Pagination could be done on the database layer

__AnalyticsService class__
- An additional class could be created to be responsible for the creation of regression models
