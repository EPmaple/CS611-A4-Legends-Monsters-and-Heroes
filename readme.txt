
# CS611-Assignment 4
Legends: Monsters and Heroes
---------------------------------------------------------------------------
Name: Tony Cen Cen
Email: tcen17@bu.edu
Student ID: U30361395

## Files
---------------------------------------------------------------------------

Armor.java: Class containing behavior and states of Armor objects
Attribute.java: Enum used to represent attributes of Character
Battle.java: Class used to instantiate Battle instance, which is responsible
for the battle interface in our game
BattleIO.java: a subclass of IO filled with specific queries and displays related
to Battle
BattleMechanics.java: a class responsible for all the mechanics in Battle
Character.java: Abstract class that Hero and Monster extends from, holds the
general behavior and data members necessary for a Character
CharacterFactory.java: Factory responsible for loading all character .txt files
, createHero() and createMonster()
Colors.java: Colors class provides ANSI escape codes for text colors and methods to retrieve color codes.
CommonTileBehavior.java: Part of the TileBehavior Strategy Pattern that
represents the behavior of a CommoneTile
Constants.java: This class provides all global constants for any game.
Coordinate.java: This class is used to implement Coordinate for each Tile
Game.java: The hub for users to indicate which game to play.
Hero.java: Class containing behavior and states of Hero objects
HeroType.java: Enum used to represent the different possible types of heroes
InaccessibleTileBehavior.java: Part of the TileBehavior Strategy Pattern that
represents the behavior of a InaccessibleTile
IO.java: The superclass that holds the most general IO's and queries of the
program
Item.java: Abstract class that Armor, Weapon, Potion, and Spell extends from,
holds the general behavior and data members necessary for an Item
ItemFactory.java: Facotry responsible for loading all item .txt files, and is
reponsible for housing all the create methods for the various items
Main.java: This class is used to initalize the Game.
MapFactory.java: Factory used to create a map (Tile[][])
Market.java: Class housing all the behaviors and states of the Market interface
of our of game
MarketIO.java: a subclass of IO filled with specific queries and displays related
to Market
MarketTileBehavior.java: Part of the TileBehavior Strategy Pattern that
represents the behavior of a MarketTile
MHGame.java: Class that is the game starter for the game Legends: Monsters and
Heroes, by first querying from the user necessary game metrics
Monster.java: Class containing behavior and states of Monster objects
MonsterType.java: Enum used to represent the different possible types of monsters
Potion.java: Class containing behavior and states of Potion objects
Spell.java: Class containing behavior and states of Spell objects
SpellType.java: Enum used to represent the different possible types of spells
State.java: Enum used to represent the different possible types of states of a
character, allows for scalability in the future if more states are to be added
Summary.java: Class to give a summary of the user from playing the game
Tile.java: Class that houses all the behaviors and states of Tile objects, part
of the Strategy Pattern of TileBehavior
TileBehavior.java: A Java interface that implements the Strategy Pattern
TileFactory.java: Factory responsible for creating Tiles of different TileBehavior
Weapon.java: Class containing behavior and states of Weapon objects
World.java: Class housing all the behaviors and states of the World interface
of our of game; it is where the majority of the game is built around
WorldIO.java: a subclass of IO filled with specific queries and displays related
to World

## Diary
---------------------------------------------------------------------------

3-19 to 3-23: Spent time thinking about the project, writing down ideas about
the project, and some ways of implementations of things required for the
project. Also took time to review all design pattern slides from lecture and
took the time to read all of chapter 1 and chapter 5 of the Design Pattern 
"gang of 4" book.
3-23: Started writing code
3-23 to 3-24: Spent a lot of time writing various factories, it was a rather
tedious task, but surprisingly, it does allow for easier life afterwards!
3-24 to 2-26: Spent more time working on vairous factories, and also took time
to write down some notes as to the structural relationship of the project at the 
end
3-26 to 3-29: Start writing Battle, which I have not idea why took me so much time.
3-29 to 3-30: Finished writing Battle and now it is down hill from here. Finished market, 
then World, wrote the Tile- and Map-Facotry, and then pieced everything together.
But due to the fact that a considerate amount of time was took to think about the
relationship of objects, the process of putting things together was surprisingly easy.
Then took sometime debug and wrote a bit of tutorial information.
3-30: Take the time to finish the readme.txt, UML, and the related writing deliverables.

## Notes
---------------------------------------------------------------------------

I purposedly coded the methods so that if in the future, if there are ways to 
simply increase increase the lvl of the heroes, then it is possible to do so.

When various queries are made to the users for choies, I have provided the user
with the ability to "go back to the prior level of choices" in logical places

I've used some coloring to highlight certain texts to made them stand out more

I implemented BFS to make sure that every map given to the user is one with
all accessible tiles connected

## Citations
---------------------------------------------------------------------------

How to use factory pattern
https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
To determine to use what to parse the txt files, settling down with Scanner
https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/
Involving the basic use of enum
https://www.w3schools.com/java/java_enums.asp
enum.valueOf()
https://www.tutorialspoint.com/java/lang/enum_valueof.htm
get String for type of object for debugging purposes
https://www.tutorialspoint.com/java/lang/class_getsimplename.htm
System.exit(0)
https://www.geeksforgeeks.org/system-exit-in-java/
To add multiple Objects to an ArrayList at the same time
https://www.tutorialspoint.com/java/util/arrays_aslist.htm
override Object.equals()
https://www.geeksforgeeks.org/overriding-equals-method-in-java/

## How to compile and run
---------------------------------------------------------------------------

1. Navigate to the directory "HW4" after unzipping the files
2. Run the following instructions:
   * javac *.java
   * java Main

## Input/Output Example
---------------------------------------------------------------------------
output:
[>] Welcome! Please enter the number for the game you want ot play: 
[0] Legends: Monsters and Heroes
input:
0
output:
[>] Would you like to change the dimensions of the world? (Default is 8x8)(y/n)
input:
n
output:
[>] How many heroes do you want in your party? (min is 1, max is 3)
input:
1
output:
[>] Enter 'paladin', 'sorcerer', or 'warrior' to select the type of the hero: 
 Paladins are favored on strength and dexterity, 
 Sorcerers are favored on dexterity and agility, 
 Warriors are favored on strength and agility.
input:
paladin
output:
[>] [0]Name: Parzival
Level: 1
State: CONSCIOUS
HP: 100
MP: 300
Strength: 750
Agility: 650
Dexterity: 700
Gold: 2500
Experience: 7/10
Hero Type: PALADIN
Currently Equipped Weapon: null
Currently Equipped Armor: null
Inventory: []
...... (the list of paladins)
Enter the number for the PALADIN you want to select, or enter Z/z to go back to the prior level: 
input:
0
output:
[>] The PALADIN, Parzival has been selected. 
---------------------------------
| H |   | M | M |   |   | M |   |
---------------------------------
|   |   | X |   |   | M |   |   |
---------------------------------
| M | M |   |   |   | M |   |   |
---------------------------------
|   |   |   |   |   |   | M |   |
---------------------------------
| M |   |   |   |   |   |   | M |
---------------------------------
|   |   | M | M |   |   | M |   |
---------------------------------
|   |   | X |   |   | M |   | X |
---------------------------------
| X |   |   | X | M | X |   | M |
---------------------------------

[>] Current coordinate: (0, 0)
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial: 
input:
i
output:
[>] Below are information about your heroes: 

Name: Parzival
Level: 1
HP: 100
MP: 300
Current Exp: 7/10
Gold: 2500
Strength: 750
Agility: 650
Dexterity: 700

---------------------------------
| H |   | M | M |   |   | M |   |
---------------------------------
|   |   | X |   |   | M |   |   |
---------------------------------
| M | M |   |   |   | M |   |   |
---------------------------------
|   |   |   |   |   |   | M |   |
---------------------------------
| M |   |   |   |   |   |   | M |
---------------------------------
|   |   | M | M |   |   | M |   |
---------------------------------
|   |   | X |   |   | M |   | X |
---------------------------------
| X |   |   | X | M | X |   | M |
---------------------------------

[>] Current coordinate: (0, 0)
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial: 
input:
t
output:
[>] H is where you are on the map. 
    X are inaccessible spaces. 
    X are markets where you can buy and sell things.
    When you moved onto a blank space, there is a 50/50 chance of entering battle.
    TIP: use potions outside of battle to boost skills of the heroes for higher chance of winning.
...... (another print of the map querying for actions)
input:
s
output:
[>] You were unlucky with the dice roll, and now you will be entering battle.

[>] Below are information about the monsters you are facing: 

Name: Blinky
Level: 1
HP: 100
Damage: 150
Defense: 350
Dodge Chance: 35

[>] It is Parzival's turn. 
[>] Enter A/a for Attack, U/u to use your inventory, I/i to show information about heroes and monsters, T/t to show tutorial:
input:
t
output:
[>] The following can be achieved by using a hero's inventory: allows a hero to put on another piece of weapon or armor, to use a spell on an enemy target, and to use a potion.
[>] It is Parzival's turn. 
[>] Enter A/a for Attack, U/u to use your inventory, I/i to show information about heroes and monsters, T/t to show tutorial:
input:
i
output:
[>] Below are information about your heroes: 

Name: Parzival
Level: 1
HP: 100
MP: 300
Currently Equipped Weapon: null
Currently Equipped Armor: null

[>] Below are information about the monsters you are facing: 

Name: Blinky
Level: 1
HP: 100
Damage: 150
Defense: 350
Dodge Chance: 35

[>] It is Parzival's turn. 
[>] Enter A/a for Attack, U/u to use your inventory, I/i to show information about heroes and monsters, T/t to show tutorial:
input:
a
output:
[>] Please enter the number for the monster you want to select, or enter Z/z to go back to the previous step: 
    [0] Blinky 
input:
0
[>] Parzival attacked Blinky for 75.
[>] Blinky has 25 HP left. 

[>] Now is the monsters' turn.
[>] Blinky attacked Parzival for 100.
[>] Parzival has 0 HP left. Parzival has fainted.

[>] You were defeated in battle and game is over.

[>] Total gold gain: 0
[>] Total gold spent: 0
[>] Highest hero level achieved: 1
(blinky is too OP!!!)

HERE is another situation showing the functionality of market:

output:
---------------------------------
| M |   |   | X | M |   |   |   |
---------------------------------
|   |   |   | X |   |   |   |   |
---------------------------------
| H | M |   | M | M |   |   |   |
---------------------------------
| M |   | M | M | X |   | M |   |
---------------------------------
| M |   |   |   |   | M |   |   |
---------------------------------
|   |   |   |   | M | M |   | X |
---------------------------------
|   | X |   |   | X |   |   |   |
---------------------------------
|   | X | M |   |   |   | X | M |
---------------------------------

[>] Current coordinate: (0, 2)
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial, M/m to enter market:
input:
M
output:
[>] You have entered the market.
[>] Gaerdal_Ironhand is currently lvl.1, and currently has 1354 gold.
[>] Enter B/b to buy any item from the stock available, or S/s to sell any item from Gaerdal_Ironhand's inventory, or F/f to finish trading for Gaerdal_Ironhand, or T/t for tutorial:
input:
t
output:
[>] You can only buy items if: 
    Hero's level is greater than the item's required level, and that the hero has enough gold to buy it.
    You can sell anything from the hero's inventory for half of its cost, and the sold item would be transferred to the market.
[>] Gaerdal_Ironhand is currently lvl.1, and currently has 1354 gold.
[>] Enter B/b to buy any item from the stock available, or S/s to sell any item from Gaerdal_Ironhand's inventory, or F/f to finish trading for Gaerdal_Ironhand, or T/t for tutorial:
input:
B
output:
[>] Below are the items available in this current market: 
[>] [0] Name: Magic_Potion
Buy Price: 350
Sell Price: 175
Required Level: 2
Attribute Increase: 100
Attributes Affected: [MANA]
Number of Uses Remaining: 3/3

[1] Name: Bow
Buy Price: 300
Sell Price: 150
Required Level: 2
Damage: 500
Required Hands: 2

[2] Name: Mermaid_Tears
Buy Price: 850
Sell Price: 425
Required Level: 5
Attribute Increase: 100
Attributes Affected: [MANA, STRENGTH, HEALTH, AGILITY]
Number of Uses Remaining: 3/3

[3] Name: Sword
Buy Price: 500
Sell Price: 250
Required Level: 1
Damage: 800
Required Hands: 1

[4] Name: Healing_Potion
Buy Price: 250
Sell Price: 125
Required Level: 1
Attribute Increase: 100
Attributes Affected: [HEALTH]
Number of Uses Remaining: 3/3

[5] Name: Platinum_Shield
Buy Price: 150
Sell Price: 75
Required Level: 1
Damage Reduction Value: 200

[6] Name: Bow
Buy Price: 300
Sell Price: 150
Required Level: 2
Damage: 500
Required Hands: 2

[7] Name: Dagger
Buy Price: 200
Sell Price: 100
Required Level: 1
Damage: 250
Required Hands: 1

[8] Name: Magic_Potion
Buy Price: 350
Sell Price: 175
Required Level: 2
Attribute Increase: 100
Attributes Affected: [MANA]
Number of Uses Remaining: 3/3

[9] Name: Platinum_Shield
Buy Price: 150
Sell Price: 75
Required Level: 1
Damage Reduction Value: 200


[>] Gaerdal_Ironhand is currently lvl.1, and currently has 1354 gold.
[>] Please enter the number for the item you want to buy, or enter Z/z to go back to the prior level: 
input:
9
output:
[>] Gaerdal_Ironhand has successfullly purchased Platinum_Shield for 150. Gaerdal_Ironhand has 1204 gold left.
input:
8
output:
[>] Gaerdal_Ironhand failed to purchase Magic_Potion due to either insufficient level or gold.
[>] Gaerdal_Ironhand is currently lvl.1, and currently has 104 gold.
[>] Enter B/b to buy any item from the stock available, or S/s to sell any item from Gaerdal_Ironhand's inventory, or F/f to finish trading for Gaerdal_Ironhand, or T/t for tutorial:
input:
s
output:
[>] Below is the inventory for Gaerdal_Ironhand: 
[0] Name: Platinum_Shield
Buy Price: 150
Sell Price: 75
Required Level: 1
Damage Reduction Value: 200

[1] Name: Dagger
Buy Price: 200
Sell Price: 100
Required Level: 1
Damage: 250
Required Hands: 1

[2] Name: Platinum_Shield
Buy Price: 150
Sell Price: 75
Required Level: 1
Damage Reduction Value: 200

[3] Name: Healing_Potion
Buy Price: 250
Sell Price: 125
Required Level: 1
Attribute Increase: 100
Attributes Affected: [HEALTH]
Number of Uses Remaining: 3/3

[4] Name: Sword
Buy Price: 500
Sell Price: 250
Required Level: 1
Damage: 800
Required Hands: 1

[>] Please enter the number for the item you want to select, or enter Z/z to return to the prior level: 
input:
2
output:
[>] Are you sure you want to sell Platinum_Shield for 75 gold? Enter Y/y for yes, N/n for no: (y/n)
input:
y
output:
[>] You have successfully sold Platinum_Shield for 75 gold. It has been successfully transferred to the stock of this market, and Gaerdal_Ironhand now has 179 gold.

......

output:
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial, M/m to enter market: 
input:
u
output:
[>] Which of the following hero's inventory would you like to use, or enter Z/z to go back to the prior level: 
[0] Gaerdal_Ironhand  
input:
0
output:
[>] Below is the inventory for Gaerdal_Ironhand: 
[0] Name: Platinum_Shield
Buy Price: 150
Sell Price: 75
Required Level: 1
Damage Reduction Value: 200
......
output:
[>] Please enter the number for the item you want to select, or enter Z/z to return to the prior level: 
input:
3
output:
[>] Gaerdal_Ironhand has put on Sword.
[>] Please enter the number for the item you want to select, or enter Z/z to return to the prior level: 
input:
2
output:
[>] Gaerdal_Ironhand used the potion Healing_Potion and, recovered 100 HP.
[>] The number of remaining uses for Healing_Potion is 0. It has perished.

......

output:
[>] It is Gaerdal_Ironhand's turn. 
[>] Enter A/a for Attack, U/u to use your inventory, I/i to show information about heroes and monsters, T/t to show tutorial:
input:
a
output:
[>] Please enter the number for the monster you want to select, or enter Z/z to go back to the previous step: 
    [0] Blinky 
input:
0
output:
[>] Gaerdal_Ironhand attacked Blinky for 400.
[>] Blinky has 0 HP left. Blinky has fainted.

[>] Victory!
[>] Gaerdal_Ironhand has gained 2 exp.
[>] Gaerdal_Ironhand has gained 100 gold.

......

output:
[>] It is Gaerdal_Ironhand's turn. 
[>] Enter A/a for Attack, U/u to use your inventory, I/i to show information about heroes and monsters, T/t to show tutorial:
input:
u
output
[>] Below is the inventory for Gaerdal_Ironhand: 
[0] Name: Dagger
Buy Price: 200
Sell Price: 100
Required Level: 1
Damage: 250
Required Hands: 1

[1] Name: Heat_Wave
Buy Price: 450
Sell Price: 225
Required Level: 2
Damage: 600
Mana Cost: 150
Spell Type: FIRE
Number of Uses Remaining: 3/3

[>] Please enter the number for the item you want to select, or enter Z/z to return to the prior level: 
input:
1
output:
[>] Please enter the number for the monster you want to select, or enter Z/z to go back to the previous step: 
    [0] Andrealphus 
input:
0
output:
[>] The number of remaining uses for Heat_Wave is 2. 
[>] Gaerdal_Ironhand cast Heat_Wave on Andrealphus for 100 damage.
[>] Andrealphus has 100 HP left. 

[>] Heat_Wave caused Andrealphus's defense to decrease to 450.
[>] Now is the monsters' turn.
[>] Andrealphus attacked Gaerdal_Ironhand for 1.
[>] Gaerdal_Ironhand has 199 HP left. 

[>] A round has ended. Conscious heroes have regained some of their HP and MP.
[>] Below are information about the monsters you are facing: 

Name: Andrealphus
Level: 2
HP: 100
Damage: 250
Defense: 450
Dodge Chance: 40

[>] It is Gaerdal_Ironhand's turn. 
[>] Enter A/a for Attack, U/u to use your inventory, I/i to show information about heroes and monsters, T/t to show tutorial:

......

output:
[>] Now is the monsters' turn.
[>] BunsenBurner attacked Gaerdal_Ironhand for 150.
[>] Gaerdal_Ironhand has 0 HP left. Gaerdal_Ironhand has fainted.

[>] You were defeated in battle and game is over.

[>] Total gold gain: 6700
[>] Total gold spent: 1700
[>] Highest hero level achieved: 4