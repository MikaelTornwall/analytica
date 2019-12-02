# Requirement spesification

## Description

Application is intended for the use of an imaginary company that organizes investment events.
It provides an interface for collecting and analyzing data that is most relevant to their core business.

For each event they can
- Record how many people demonstrated their interest towards participating in the event in social media
- How many people bought tickets to the event (and the ticket price)
- How many participated in the event
- How many of the participants opened an investment portfolio at the event
etc.

With this information they can calculate event specific (and for example month specific) key figures that will help the company in data-driven future business planning.

## Users

Currently the application is intended only for the internal use within the company, so only one user role is required. Later maybe a superuser will be added.

## User interface draft

## Features

__Before logging in__

- [x] User can register
  - Required information is
    - [ ] Username (length at least 6 characters)
    - [ ] Password (length at least 8 characters and must include both letters and numbers)
- [x] User can log in
  - [x] If login is successful user will be directed to the application
  - [x] If login is unsuccessful user will receive a notification and can try again

  __After logging in__

  Application includes the following views:

  - [x] User select preferred view from the menu

  _Dashboard_  
  - [ ] User will see a summary

  _CSV file form_
  - [ ] User can write comma separated values into a form and save them

  _Input form_

  - User can input event specific information

  _Analysis_

  - User can select a preferred analysis method and select preferred data and receive the results for the analysis

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

  _Graphs_
  - User can view graphs

## Further development ideas
- Superuser
- More tools

## Environmental constrains

The application should work on both MacOS and Linux systems.
