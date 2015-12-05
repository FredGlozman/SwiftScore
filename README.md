# ECSE-321 Project: Soccer Scorekeeping Application

## What works
- The domain model is fully implemented
- The domain model has been tested with JUnit tests
- The build script successfully:
	- Generates Java code from Umple
	- Compiles the model's Java packages
	- Tests the model with JUnit
	- Links the model packages into jars. One of which is placed in the mobile app's lib folder
	- Generates Javadoc documentation for the model
- Persistence functions and has been tested with JUnit
- Our Ant script (including unit tests) runs on a TravisCI server
- Our mobile app is complete and integrated with the model
- The domain model is fully documented using JavaDoc

## What doesn't
- The website is incomplete but partially integtated with the model
- The desktop application is mostly complete but cannot run due to JavaFX dependency issues. Otherwise it would function as shown in the course demo

## How to use it
- To generate the model, simply run `ant` in the root of the repo. Note that you must be running Java 1.7 or the Android application will not be able to use the model.
- To use the android application, open the `mobile` folder with Android Studio and run the virtual machine. The username is `fred` and the password is `123`.
- To use the webapp, place the whole repository in the `htdocs` folder of an xampp server. Open `localhost:portname/web/index.php` to start using the app.
- The username for the desktop app is `username` and the password is `password`. You should be able to run Main.java in `src/ca/mcgill/ecse321/scorekeeper/desktop` to run it.

## Build status
Master branch: [![Build Status](https://travis-ci.com/Fall2015-ECSE321/team1.svg?token=LkpGPP4TCa9K83w7TLBE&branch=master)](https://travis-ci.com/Fall2015-ECSE321/team1)

Dev branch: [![Build Status](https://travis-ci.com/Fall2015-ECSE321/team1.svg?token=LkpGPP4TCa9K83w7TLBE&branch=dev)](https://travis-ci.com/Fall2015-ECSE321/team1)
