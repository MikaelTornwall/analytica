# Requirement spesification

## Description

Application is intended for the use of an imaginary company that organizes investment events.
It provides an interface for collecting and analyzing data that is most relevant to their core business.

For each event they can record
- What was the ticket price
- How many people participated in the event
- How many of the participants opened an investment portfolio at the event
etc.

With this information they can calculate key figures that will help the company in data-driven future business planning.

## Users

Currently the application is intended only for the internal use within the company, so only one user role is required. Each user is also able to access all the data, since all the event information is relevant to everyone.

## User interface draft

## Features

__Before logging in__

- [x] User can register
  - Required information is
    - [x] Username (length 6-15 characters)
    - [x] Password (length 8-15 characters)
- [x] User can log in
  - [x] If login is successful user will be directed to the application
  - [x] If login is unsuccessful user will receive a notification and can try again

  __After logging in__

  Application includes the following views:

  - [x] User can select preferred view from the menu

  _Dashboard_  
  - [x] User will see a summary and key KPIs calculated from the event data. These include
    - Total revenue
    - Total participants
    - Total opened accounts
    - Average ticket price
    - Average revenue KPIs
    - Correlation between number of participants and number of opened accounts
    - Field for predicting future ticket prices, number of participants and revenue by participants or ticket price

  _Input form_

  - [x] User can input event details

  _Events_

  - [x] User can view at most 20 latest events with their specific details (older event details are not important to view, but the data is relevant for calculations)

    __Statistics__
      - [x] Add values      
      - [x] Sum
      - [x] Mean
      - [x] Median
      - [x] Mode
      - [x] Variance
      - [x] Standard error        

    __Regression__      
      - [x] Add value pairs
      - [x] Covariance
      - [x] Slope, intercept and predict
      - [x]

  _Graphs_
  - User can view graphs

## Further development ideas
- Superuser
- More tools

## Environmental constrains

The application should work on both MacOS and Linux systems.
