# Application architecture

## Structure

The application structure follows a three layer architecture:

![package structure](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/packagestructure.png?raw=true)

Top layer is the graphical user interface that is built with JavaFX components. Middle-layer consists of domain classes Account and Event that are the application entities and Statistics and Regression that provide methods for calculations, and of service classes that handle the application logic. Top and middle layers use the bottom layer dao, that is responsible for saving and retrieving information to and from the database.

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

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/analytica_class.png?raw=true)

Account and Event classes are responsible for the implementing the representing the entity data. Statistics and Regression classes are responsible for making models and calculations from the event data.

AccountService class is responsible for the functional application logic related to account class. It contains several methods, that connect user inputs and request to the DAO classes, AccountDAO, and through them to the database. It is responsible account management logic by fetching information from the database using DAO interfaces with given parameters and returning this data to the user interface.

Below is a more detailed class/package diagram representing the most relevant relationships between domain, service and dao packages and classes within these packages:

![extended package diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/extendedpackagestruct.png?raw=true)


## Most important features

### Create user

User can be created by clicking the Create new user button on the application login page and then feeding in username and password. After the user clicks the Add button, the following actions are going to be executed to create a new user account:

![class diagram](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/analytica_sequence.png?raw=true)

After this the user will be directed to the login scene, where they can use their newly created credential to access the tool.

### Login

User can log in on the login view by typing valid credentials into login form and clicking login button. After the user click the Login button, the following actions are going to be executed for the user to be able to access the tool:

After this the user will be directed to the application dashboard.

### New event

User can create a new event in the events view by typing in the event information and clicking the Add event button. After clicking the button, the following actions are being executed to create a new event:

After succesful event creation user can view event details in the Events view and view updated statistics on the dashboard view.

## Database

Application uses SQL database through H2 database engine.

The database has two database tables, Account and Event, which contain the following information:

![database tables](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/databasetables.png?raw=true)

These two tables are independent, i.e. there is no dependency relationship between these two tables through foreign keys.

Both Account and Event have corresponding classes for saving and retrieving data to and from the SQL database. These classes are SQLAccountDao, which implements the AccountDao interface, and SQLEventDao, which implements the EventDao interface.

## Structural deficiencies
