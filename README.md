# Builder Boards

Welcome to Builder Boards. Builder Boards has the goal to optimize the managing process of projects. In the future maybe some updates bring more feature to get some practicable Features in the application. 

### Why Builder Boards? 
Builder Boards is an application for constructors. These companies have a massive load of managing projects. When a project is ready to build, the installer must often overnight in a hotel. Then for some project it is necessary to do a maintenance in a fixed interval. In few month it should be possible to handle all these feature in this application.

## Table of Contents
* Installation Guide
* Server - Project Description
* Web - Project Description

# Installation Guide
Basically it should be mentioned, that this project was setup with the [Spring Initializr]([https://start.spring.io/](https://start.spring.io/)).

A little summarize about the used frameworks & technologies:
* Spring Boot 2
* Flyway - For DB Migration
* Swagger - to get an generated API-Documentation
* Lombok - to clean up the code

### Installation of all required systems

#### Maven
This Project is a Maven-Project. That means, if you want to collaborate in this project, you should install Maven. Currently I used the **Maven Version 3.6.3.** 
> If you need an Tutorial for the Installation of Maven [click here]([https://maven.apache.org/install.html](https://maven.apache.org/install.html))

#### NPM 
NPM is necessary to install among others angular. NPM is the NodePackageManager and here is an [Installation Guide]([https://www.npmjs.com/get-npm](https://www.npmjs.com/get-npm))

#### Angular (CLI)
The next component which should be installed is the **angular cli**. This is used in the web project. For this i can recommend the official page which displays an quick start guide on the start page -> [Angular CLI]([https://cli.angular.io/](https://cli.angular.io/))

#### JDK
To run the Server System, it is recommendable to install an JDK. You can install any kind of the JDK, in my opinion the [AdoptJDK]([https://adoptopenjdk.net/](https://adoptopenjdk.net/)) is good choice. The version do you need is 8.

# Server - Project Description
The server project is for the backend system. There we use spring boot and for the database we use an MYSQL - Database (V. 5.7)

# Web - Project Description
The web project is the frontend system. Here we use Angular in the newest version.
