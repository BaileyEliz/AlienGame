package gamePackage;

/*
 * All of the numbers and strings used multiple times or in program logic.
 */

public class Constants {

    //Base

    public static final int MILLISECONDS_IN_SECOND = 1000;

    //AlienWorld

    public static final String GAME_TITLE = "Alien Attack";
    public static final int FRAMES_PER_SECOND = 60;
    public static final int ALIEN_GOAL = 30;

    //Level

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;

    //Screen

    public static final String SPLASH_SCREEN = "Welcome to Alien Attack!\n\n"
            + "Earth is being attacked by a newly discovered\n"
            + "alien species and needs to be defended!\n\n"
            + "Use your mouse to aim on the screen and\n"
            + "the space bar to shoot to destroy them.\n\nPress the enter key to start.";
    public static final String LOST_SCREEN = "You lost!\n\nPress the enter key to play again or E to exit.";
    public static final String STAR_SCREEN = "Engaging Star Mode.\n\nHit one to make it explode!\n\n"
            + "Press the enter key to start.";
    public static final String BOSS_SCREEN = "\nWelcome to the Boss Level!\n\n"
            + "Be careful, the mothership is fast!\n\nPress the enter key to start.";
    public static final String END_SCREEN = "\nCongrats! You saved Earth!\n\nPress enter to play again or E to exit.";

    public static final int DIVIDE_BY_TEN = 10;
    
    //Mode

    public static final int MONITOR_HEIGHT = 30;
    public static final int COUNT_STARTER = 61;

    public static final int OFF_SCREEN_OFFSET = 100;

    public static final int RANDOM_OFFSCREEN = 1;
    public static final int RANDOM_ONSCREEN = 2;

    public static final int GET_ZERO_OR_ONE = 2;

    public static final int NUMBER_OF_EXPLOSIONS_EARTH = 8;
    public static final int EARTH_EXPLOSION_INDICATOR = 1;

    //AlienMode

    public static final int MAX_ALIENS_LAUNCHED = 29;
    public static final int ALIEN_LAUNCH_RATE = 30;

    //StarMode

    public static final int STAR_CREATION_RATE = 160;
    public static final int STAR_EXPLOSION_INDICATOR = 0;
    public static final int NUMBER_OF_EXPLOSIONS_STAR = 5;

    public static final int INNER_RANDOM_BUFFER = 50;

    //BossMode

    public static final double BOSS_LIVES = 9;
    public static final int BOSS_MOVE_RATE = 30;

    //Earth

    public static final double EARTH_LIVES = 3;

    public static final int EARTH_WIDTH = 100;
    public static final int EARTH_HEIGHT = 100;
    public static final int ORIGIN = SCREEN_WIDTH/2;
    public static final String EARTH_IMAGE = "Images/earth.png"; 		//from https://pixabay.com/en/world-earth-planet-continents-154527/
    public static final int IMAGE_OFFSET = 50;

    //Shot

    public static final int SHOT_WIDTH = 20;
    public static final int SHOT_HEIGHT = 20;
    public static final String SHOT_IMAGE = "Images/fire.png"; 			//from http://ncptt.nps.gov/wp-content/uploads/fire-vector.png

    public static final int TOP_LEFT_CORNER = 0;
    public static final int TOP_RIGHT_CORNER = 1;
    public static final int BOTTOM_RIGHT_CORNER = 2;
    public static final int BOTTOM_LEFT_CORNER = 3;
    public static final int TOP_MIDDLE = 4;
    public static final int RIGHT_MIDDLE = 5;
    public static final int BOTTOM_MIDDLE = 6;
    public static final int LEFT_MIDDLE = 7;

    public static final int SHOT_RATE_UPPER_BOUND = 5000;
    public static final double SHOT_RATE_CONSTANT = 1.2;
    public static final int SHOT_RATE_LOWER_BOUND = 400;
    
    public static final int DIVIDE_BY_ZERO_CATCH = 10000;
    
    //Explosion
    
    public static final int EXPLOSION_RATE = 5000;

    //Alien

    public static final int ALIEN_WIDTH = 90;
    public static final int ALIEN_HEIGHT = 100;
    public static final String ALIEN_IMAGE = "Images/alien.png"; 		//from http://s265.photobucket.com/user/lll_085/media/Alien.png.html

    public static final int LOWER_SPEED_BOUND = 4000;
    public static final int UPPER_SPEED_OFFSET = 3000;

    public static final int SCREEN_OFFSET = 300;
    
    public static final int SLOW_MO = 3;

    //Star

    public static final int STAR_WIDTH = 30;
    public static final int STAR_HEIGHT = 30;
    public static final String STAR_IMAGE = "Images/star.png";			//from http://pngimg.com/download/1589

    //Boss

    public static final int BOSS_WIDTH = 150;
    public static final int BOSS_HEIGHT = 70;
    public static final String BOSS_IMAGE = "Images/mothership.png"; 	//from http://cliparts.co/clipart/2341166

}
