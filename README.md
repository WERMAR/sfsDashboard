# Builder Boards

Welcome to Builder Boards. Builder Boards has the goal to optimize the managing process of projects. In the future updates will be bring more practicable features into the application. 

### Why Builder Boards? 
Builder Boards is an application for constructors. These companies have a massive load of managing projects. When a project is ready to build, the installer must often overnight in a hotel. Then for some projects it is necessary to do a maintenance in a fixed interval. In few month it should be possible to handle all these feature in this application.

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
---
This Project is a Maven-Project. That means, if you want to collaborate in this project, you should install Maven. Currently I used the **Maven Version 3.6.3.** 

> If you need an Tutorial for the Installation of Maven [click here](https://maven.apache.org/install.html)


#### NPM
--- 
NPM is necessary, to be able to install others frameworks like angular. NPM is the NodePackageManager and here is an [Installation Guide](https://www.npmjs.com/get-npm)


#### Angular (CLI)
---
The next component which should be installed is the **angular cli**. This is used in the web project. For this i can recommend the official page which displays an quick start guide on the start page -> [Angular CLI](https://cli.angular.io/)

#### JDK
---
To run the Server System, it is recommendable to install an JDK. You can install any kind of the JDK, in my opinion the [AdoptJDK](https://adoptopenjdk.net/) is good choice. The version do you need is 8.

# Server - Project Description
The server project described and contains the backend system. We decided to use Spring Boot, cause one of the gorgeous features, is the easy way of extend the system with gorgeous feautures (e.g. Rest-Controller-Advice, for setting up the an ErrorHandler in the Rest-API).

### Server Project - Details
---
The server project is setup with Maven and this build processing is setup in the [pom.xml](../server/builderBoards/pom.xml). The database will be migrate with the framework `flyway`. The flyway scripts are based in the folder: `server/buildersBoard/src/main/resources/db/migration`

> If you want to add some new Migration-Scripts, it based on this Pattern: `Vx.x__{fileName}.sql`

The Server has a standard configuration.

# Web - Project Description
The web project is the frontend system. Here we use Angular in the newest version.

# Start Project
In this project we used docker for our production enviroment. When you started with this project, first of all it is important to build the docker images. To build both images, you can execute the `buildImages.sh` (for Linux and Unix) or `buildImages.bat`(for Windows). After building the Docker-Images of server and web, you can start the application with the help of `docker-compose.yml`. This file contains the configuration of all docker container and with the command `docker-compose up -d`, in the main directory, you start the application.
