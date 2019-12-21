# Requirement specification

## Description

Application is intended for the use of an imaginary company that organizes investment events.
It provides an interface for collecting and analyzing data that is most relevant for their core business.

For each event they can record
- Event name
- Ticket price
- How many people participated in the event
- How many of the participants opened an investment portfolio account at the event
- How many male and female participants were in the event

With this information they can calculate key figures that will help the company in data-driven future business planning.

## Users

Currently the application is intended only for the internal use within the company, so only one user role is required. Each user is able to access all the data, since all the event information is relevant to everyone within the company.

## Features

Application includes the following features:

__Before logging in__

- [x] User can register
  - Input validation
    - [x] Username (length 6-15 characters)
    - [x] Password (length 8-15 characters)
- [x] User can log in
  - [x] If login is successful user will be directed to the application
  - [x] If login is unsuccessful user will receive a notification and can try again

  __After logging in__  

  - [x] User can select preferred view from the menu

  _Dashboard_  
  - [x] User will see a summary and key KPIs calculated from the event data and can predict future values. E.g.
    - Total revenue
    - Total participants
    - Total opened accounts
    - Average ticket price
    - Average revenue KPIs
    - Correlation between number of participants and number of opened accounts
    - Field for predicting future ticket prices, number of participants and revenue by participants or by ticket price

  _New event_

  - [x] User can input event details
  - [x] User can create new event

  _Events_

  - [x] User can view at most 20 latest events with their specific details (older event details are not important to view, but the data is relevant for calculations)

## User interface

__Dashboard__

![dashboard draft](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/dashboard.png?raw=true)

__Events__

![event list draft](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/events.png?raw=true)

__New event__

![new event draft](https://github.com/MikaelTornwall/ot-harjoitustyo/blob/master/documentation/images/newevent.png?raw=true)

## Further development ideas

- Super user
  - Super user could manage events and accounts
- Extending the tool for the use of several organizations
  - User can access only the data of the company one belongs to
- More data, such as the number of people interested in the event in events social media page
- More tools
  - These tools include multivariable linear regression and non-linear estimators
- Graphs from data
- Features for sorting and analyzing data between certain timelines

## Environmental constrains

The application should work on both MacOS and Linux systems.
