# To-Do-List-Project

### Summary
Testing Coverage: 92.4%

To Do List project, integrating a Spring Boot/Java backend with a HTML/CSS frontend to create a functioning To Do List application.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
Back-End:
1) Java Eclipse IDE
2) Maven
3) Spring Boot
4) Selenium
5) SonarQube

Front-End:
1) Visual Studio Code

### Installation

1) Fork this repository over to your own on Github. Then clone down the forked repo to a local source.

2) On the Eclipse IDE, go to File -> Open Projects from File System -> Find your local source and open it. 
  a) This will create the project on your IDE allowing you to edit the code as you wish.

3) Right click on the project -> Show is Local Terminal -> Terminal -> 'mvn package'... This compiles the project into an executable JAR.
4) If you repeat Step 3 but instead type 'mvn spring-boot:run' you can run the projects back-end.
5) For this step, you'll need to have completeled Step-4. On the Visual Studio Code, right click and select 'open with live server' to open a localhost web browser with the design on.
6) From here, you can communicate with the back-end via the web browser.

## Testing

### Unit Testing

Unit tests test each individual constructor, method or string within a given class. These work with class isolation so each class has its own set of unit tests run.

In order to run these, we can right click on the overall project file and click the 'coverage as' and then select 'junit test'. This will run the whole project and all junit tests within the project. This gives you a complete breakdown of all the tests that are running within the program and also the class names and individual methods being tested within them.

Alternatively, we can also test classes in isolation. Again, by opening up the project src/test/java folder, we can select which package we want to test, then further into that, which class we want to test. This is an individualised way of targetting classes for testing purposes that allows you to easily diagnose issues within a given class.

As part of the unit tests, I used Mockito. You can access Mockito by having the following dependencies within your POM file:

    <dependency>
			<groupId>org.mockito</groupId>
			<artifactId>mockito-core</artifactId>
			<version>3.7.7</version>
			<scope>test</scope>
		</dependency
  
 Using Mockito we can further test classes and methods by specifying parameters and verifying whether those parameters pass the tests.

### Integration Testing

Integration Testing is used to combine units together to check their functionality together. It is designed to expose fault in functionality. These type of tests are accessed the same way as the unit tests above.

## Deployment

Once 'mvn package' has been run and the project has been compiled there will be a target folder in the To-Do-List folder. Run the command line within this folder and type the command 'java -jar ToDoList-0.0.1-jar-with dependencies.jar. This will then open the project in the command line as if you were running it in Eclipse.

## Built With

Maven - Dependency Management.
Spring Boot - Auto Configuration.
Selenium - Front-End Testing.

## Versioning

We use SemVer for versioning.

## Authors

Tom Hoey

## License

This project is licensed under the MIT license - see the LICENSE.md file for details

## Acknowledgements

Giving big ups to Edward Reynolds and Morgan Walsh for all training and support for this project.
