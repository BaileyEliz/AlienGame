DESIGN
=======

###Goals

The game is contained in the GameLoop class, and is an instance of the AlienWorld class. Thus this class implements the GameLoop and is responsible for calling the correct implementations of the functions which make up the game loop, and for making sure the correct scene and level are active at all times. The two types of Levels which can be active are Screens, which give the player instructions and practice shooting, and Modes, which host the actual gameplay. The Modes build on each other, the first creating aliens, the second adding in exploding stars, and the final adding in a boss. These characters are all Sprites with their own defined behaviors and they include Earth, Alien, Shot, Star, and Boss; most of these have their own managers.

###Adding Features

It is quite simple to create a new Mode; extend the Mode class which already includes a framework to handle Aliens and Shots and collisions between the two and the Earth. Functionality can easily be added in the updateShapes and checkCollisions functions. Or just extend the Alien or Star Modes if they already include some of the behavior desirable for the new feature. It is also simple to create a new type of Sprite; it only needs its own class and an image file and a starting X and Y coordinate to be a member of the game.

###Choices

The decision was made to have a Wrapper-type AlienWorld class to take care of keeping the correct levels in play during the right times. It was given responsibility to take care of sounds because the sounds change naturally at such transitions that the AlienWorld class was involved in. The Shot class includes two constructors and two initializers which seems like bad design, but this is intentional as the actual object createds, the Shots themselves, need to be kept in one simple list and this is the easiest way to implement that. The Level class stores the earth as well as the shoot and explode functions for it because an earth as well as shooting and exploding capabilities are present on every Level, both Screens and Modes.