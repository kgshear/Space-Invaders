# Space-Invaders
Java implementation of the Space Invaders Game, created for a object-oriented programming class

## How To Play
      Use ←↑↓→ keys to move the base, and space to shoot missiles
      
      Destroy all enemy invaders before they reach the bottom of the screen and reach your base
      
      Avoid enemy missiles, or your base will be destroyed
      
      Get points by shooting invaders
## Game Mechanics
      Invaders move at a faster rate when they hit the side of the screen. 
      
      When all invaders are destroyed, a new wave is created
      
      Occasionally, a mystery ship will fly across the top of the screen; this ship is worth a random
      amount of points
      
      Each time a base missile is shot, the missile is on cooldown until it reaches the edge of the screen
      
      
## Files

Description of the code created for the game

#### 1. SpaceInvaders.java
      JFrame that holds the SpaceInvaders game. Contains a menu bar that allows the player to pause the game,
      resume, restart, and  quit. Contains a WindowListener that shows a JOptionPane confirmation dialog if the window 
      is closed
#### 2. Panel.java
      Keeps the pulse of the game; manages all of the objects in the game (missiles, ship, aliens, etc). 
      Redraws the game state at every pulse. Removes objects that have been destroyed. Accepts key inputs 
      that move the base and shoot missiles.
#### 3. Base.java
      Holds attributes of the base, which is the ship that the player controls. Drawable object. 
#### 4. Drawable.java
      Base class that objects that can be drawn inherit from. Manages x/y position of drawable objects.
#### 5. Invader.java 
      Class that holds attributes of alien invaders (score, height, width)
#### 6. InvaderBottom.java, InvaderMiddle.java, InvaderTop.java
      Extends Invader.java, but utilizes different images depending on the row. Drawable objects. 
#### 7. Missile.java
      Holds attributes of a missile and allows it to be drawn
#### 8. Mystery.java
      Holds attributes and image/audio of the mystery ship, which may randomly go across the screen during gameplay. 
      Drawable object. 
#### 9. Ship.java
      Holds the attribute of any ship (Invader, Base).

      
 
