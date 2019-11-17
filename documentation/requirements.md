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

- User can register
  - Required information is
    - Username (length at least 6 characters)
    - Password (length at least 8 characters and must include both letters and numbers)
- User can log in
  - If login is successful user will be directed to the application
  - If login is unsuccessful user will receive a notification and can try again

  __After logging in__

  Application includes the following views:

  _Dashboard_

  - User select preferred view from the menu
  - User will see a summary

  _Input form_

  - User can input event specific information

  _Analysis_

  - User can select a preferred analysis method and select preferred data and receive the results for the analysis
  - To be updated

  _Graphs_
  - User can view graphs

## Further development ideas
- Superuser
- More tools

## Environmental constrains

The application should work on both MacOS and Linux systems.
