# Bowling-alley
A Java Program that plays a the bowling game for the given number of users..

## Description
Design of the entire bowling alley system. One bowling game will be played by multiple players on a single lane.
The names of the players will be provided by the user through the application.yaml file.
A lane free for playing will be assigned to the group of players to play the game.
During the game,players and their scores will be maintained and shown by the system and winner will be declared at the end of the game.

## Technologies Used
* JAVA 8
* Jackson library for reading the input from application.yaml file.

## Installations Needed
* JAVA 8
* Maven

## How to use
* Clone the project.
* Add the name of the players who are playing in the application.yaml file located in the main/java/resources folder.
* Run mvn clean install in the location of pom.xml file.
* Execute the class BowlingAlleyManager.

## Output
On execution of the code the scoreboard will be displayed at the end of every frame. Press enter to move ahead to the next frame.
Once all the frames are done the winner will be printed at the end.
