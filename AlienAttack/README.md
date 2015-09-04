README.md
=======

###Author

Bailey Wall

###Dates

####Started

August 25, 2015

####Finished

September 4, 2015

###Hours Worked

About 25

###Resources

####Framework

Some of Carl Dea's ideas from "Create Your Own Game Using JavaFX 2" were implemeted

####Sound Files

Sounds taken from MediaCollege.com

####Images

Earth from https://pixabay.com/en/world-earth-planet-continents-154527/

Fire from http://ncptt.nps.gov/wp-content/uploads/fire-vector.png

Alien from http://s265.photobucket.com/user/lll_085/media/Alien.png.html

Stars from http://pngimg.com/download/1589

UFO from http://cliparts.co/clipart/2341166

###Starting Classes

GameLoop contains the only main method

###Required Data

####Images

alien.png

earth.png

fire.png

mothership.png

shot.png

star.png

####Sound Files

houstonProblem.mp3

jupiterWaves.mps

radioWaves.mp3

smallStep.mp3

###Cheats

Pressing the "W" key causes Earth to emit eight shots in all different directions at once.

Pressing the "S" key in each round causes the next spawned Aliens to move much slower. Pressing it again sets them back to normal pace.

###Bugs

Shooting straight up or straight down causes the shot to travel much faster than other directions and can sometimes cause an Alien who was really hit to not register the impact.

This isn't a bug, but I could not figure out how to use getClass() with the Audio Clip files. I kept getting a null pointer exception while if I pointed to the folder in the code where the resource is located I had not problems. All of my audio files are saved in the assets/Sounds folder, and assets should be a class folder included on the Build Path for the game. The same situation happened for my font, which is saved in assets/Fonts.

###Extra Features

Star figures explode into five shots when hit.

###Assignment Impressions

Making a game is a fun way to create a large chunk of code from scratch. I appreciated the wide variety of games we were able to choose from and how much creative liberty we were given and think that it is important to learn how to make choices like the ones we were forced to make during this project. The pace was overwhelming, but finishing the game provides a sense of accomplishment that makes it worth the hours.