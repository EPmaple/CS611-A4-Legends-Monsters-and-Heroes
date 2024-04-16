
# CS611-Assignment 5
Legends of Valor
---------------------------------------------------------------------------
Name: Tony Cen Cen, Ankur Ankit Abdelazim Lokma
Email: tcen17@bu.edu, alokma@bu.edu, anankit@bu.edu
Student ID: U30361395

## Files
---------------------------------------------------------------------------

Armor.java: Class containing behavior and states of Armor objects
Attribute.java: Enum used to represent attributes of Character
Battle.java: Class used to instantiate Battle instance, which is responsible
for the battle interface in our gameManager
BattleIO.java: a subclass of IO filled with specific queries and displays related
to Battle
BattleMechanics.java: a class responsible for all the mechanics in Battle
Character.java: Abstract class that Hero and Monster extends from, holds the
general behavior and data members necessary for a Character
Board.java: The class handles basic game board functions such as initialization, 
cell type management, display, and move validation based on cell types.
BoardFactory.java: A factory interface to specifying the creation of a board
CharacterFactory.java: Factory responsible for loading all character .txt files
, createHero() and createMonster()
Colors.java: Colors class provides ANSI escape codes for text colors and methods to retrieve color codes.
CommonTileBehavior.java: Part of the TileBehavior Strategy Pattern that
represents the behavior of a CommoneTile
Constants.java: This class provides all global constants for any gameManager.
Consumable.java: A contract to clearly indicate that an item is consumable
Coordinate.java: This class is used to implement Coordinate for each MHTile
Dragon.java: Class for creating the specific concrete Dragon object
Exoskeleton.java: Class for creating the specific concrete Exoskeleton object
Game.java: An abstract class to serve as a template to create other Game starters
GameManager.java: The hub for users to indicate which gameManager to play.
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
LegendsOfValorIO.java: This class is responsible for displays, queries related 
to the game Legends of Valor, managing the game I/O through a consistent interface
LegendsOfValorMechanics.java: This class is created to refactor some of the LVWorld mechanics
LegendsOfValorWorld.java: This class is main body of the Legends of Valor game, 
responsible initializing the game, making sure the game is in a consistent state, 
and allow the game to correctly function
LVBoostStrategy.java: Interface to implementing the boost of skills when a hero is on the tile
LVBushTile.java: LVCell responsible for the boost of a hero's dexterity
LVCaveTile.java: LVCell responsible for the boost of a hero's agility
LVCell.java: LVCell implements the Interface LVBoostStrategy, and serve as a t
emplate for other LVTiles to extend from. It's responsible for the basic interaction with a tile
LVGame.java: LVGame extends from Game and serves as a game starter for Legends 
of Valor, by querying from the user the necessary input params and start the game
LVHeroNexusTile.java: LVCell that represents the nexus for heroes, and is also 
responsible for initiating trade by market
LVKoulouTile.java: LVCell responsible for boosting a hero's strength
LVMap.java: LVMap extends from the Java Interface Board and provides all the 
related functionalities in relation to the board it contains
LVMapFactory.java: Factory to create the LVMap with the different types of tiles 
LVMapFactoryTest.java: Class to test whether the map is working, commented wrote because is outdated
LVMonsterNexusTile.java: LVCell representing the nexus of monster
LVNonAccessibleTile.java: LVCell representing a tile that is non-accessible
LVPlainTile.java: LVCell that does nothing but simply resets the boost of the hero
LVSummary.java: A more customized summary class to print a win/loss summary for a game of Legends of Valor
Main.java: This class is used to initalize the GameManager.
MapFactory.java: Factory used to create a map (MHTile[][])
Market.java: Class housing all the behaviors and states of the Market interface
of our of gameManager
MarketIO.java: a subclass of IO filled with specific queries and displays related
to Market
MarketTileBehavior.java: Part of the TileBehavior Strategy Pattern that
represents the behavior of a MarketTile
MHGame.java: Class that is the gameManager starter for the gameManager Legends: Monsters and
Heroes, by first querying from the user necessary gameManager metrics
Monster.java: Class containing behavior and states of Monster objects
MonsterType.java: Enum used to represent the different possible types of monsters
Paladin.java: A concrete class of Hero specifying the Paladin hero type
Potion.java: Class containing behavior and states of Potion objects
Sorcerer.java: A concrete class of Hero specifying the Sorcerer hero type
Spell.java: Class containing behavior and states of Spell objects
SpellType.java: Enum used to represent the different possible types of spells
Spirit.java: A concrete class of Hero specifying the Spirit monster type
State.java: Enum used to represent the different possible types of states of a
character, allows for scalability in the future if more states are to be added
Summary.java: Class to give a summary of the user from playing the gameManager
MHTile.java: Class that houses all the behaviors and states of MHTile objects, part of the Strategy Pattern of TileBehavior
Tile.java: An abstract class to serve as a template to create other types of tiles
TileBehavior.java: A Java interface that implements the Strategy Pattern
TileFactory.java: Factory responsible for creating Tiles of different TileBehavior
Tradeable.java: An interface to clearly indicate that any classes that implements it should be able to be purchased and sold
Warrior.java: A concrete class of Hero specifying the Warrior hero type
Weapon.java: Class containing behavior and states of Weapon objects
HeroesAndMonstersWorld.java: Class housing all the behaviors and states of the World interface
of our of gameManager; it is where the majority of the gameManager is built around
World.java: A simple interface that serves as a contract on what methods are  essential in a new game world
WorldIO.java: a subclass of IO filled with specific queries and displays related
to World

## Diary
---------------------------------------------------------------------------

1. the tuesday one week before the due date, we got together before the
class and settled down on whose work we go going to base our code off of,
which is Tony's, and we took some time to understand the assignment together.
Then we split up the work.
2. After the Tuesday up until Saturday, Ankur took his time to do his part,
which is LVBoostStrategy, LVTile's/Cell's, and LVMap 
3. I and Abdel started working on our parts on Sunday, which was LVWorld,
LVMechanics, LVIO
    I and Abdel kept in touch all day and frequently provided each other
    with one's own progress
4. Monday, we finished writing everything, Abdel helped me created the UML, I added
comments to all new files (including the ones written by Ankur), wrote the
documentation, and finished the readme; Abdel was also willing to help
me debug


## Notes
---------------------------------------------------------------------------

I will like to note that some methods like getAttackable were written in 
terms of Character, and not in terms of Hero/Monster, and this allows for 
scalability

I also took into the consideration that when a hero A dies, another hero B may
already be on the hero A's nexus, and thus I implemented forced recall, for which
in such a situation hero B would be forced recall back to his own nexus, and thus
allowing A to be revived back at his own nexus

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

1. Navigate to the directory "HW5" after unzipping the files
2. Run the following instructions:
    * mkdir bin (if a bin folder does not exist already)
    * javac -d bin -sourcepath src src/*.java
    * java -cp bin Main

## Input/Output Example
---------------------------------------------------------------------------
Legends of Valor:
output:
[>] Welcome! Please enter the number for the game you want ot play: 
[0] Legends: Monsters and Heroes
[1] Legends of Valor
input:
1
output:
[>] You are currently in the hero selection phase, and you will have to select 3 more heroes.
[>] Enter 'paladin', 'sorcerer', or 'warrior' to select the type of the hero: 
 Paladins are favored on strength and dexterity, 
 Sorcerers are favored on dexterity and agility, 
 Warriors are favored on strength and agility.
input:
paladin
...... (hero selection same as in Heroes and Monsters)
output: 
[>] Would you like to place Sehanine_Sunbow on , lane 1 , lane 2 , lane 3 
input:
1
output:
[>] Would you like to place Skoraeus_Stonebones on , lane 2 , lane 3 
input:
2
output:
[>] Would you like to place Garl_Glittergold on , lane 3 
input:
3
output:
[>] round: 1
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |     M0  | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
| H1      | |         | | X   X   | | H2      | |         | | X   X   | | H3      | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 

[>] It is currently H1 Sehanine_Sunbow's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster, M/m to enter market: 
input:
w
output:
[>] Successfully moved to tile at (6,0)
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |     M0  | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
| H1      | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | | H2      | |         | | X   X   | | H3      | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 

[>] It is currently H2 Skoraeus_Stonebones's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster, M/m to enter market: 
input:
A
output:
[>] Failed to move in the requested direction.
[>] It is currently H2 Skoraeus_Stonebones's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster, M/m to enter market: 
input:
m
output:
[>] You have entered the market.
[>] Skoraeus_Stonebones is currently lvl.1, and currently has 2500 gold.
[>] Enter B/b to buy any item from the stock available, or S/s to sell any item from Skoraeus_Stonebones's inventory, or F/f to finish trading for Skoraeus_Stonebones, or T/t for tutorial:
......(same market as in Heroes and Monsters)
output:
[>] It is currently H2 Skoraeus_Stonebones's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster, M/m to enter market: 
input:
i
output:
[>] Enter 'hero' to display info of all heroes, 'monster' to display info of all monsters: 
input:
hero
output:
[>] Below are information about heroes on the map: 
[>] H1Name: Sehanine_Sunbow
Level: 1
State: CONSCIOUS
HP: 100
MP: 300
Strength: 750
Agility: 700
Dexterity: 700
Gold: 2500
Experience: 7/10
Hero Type: PALADIN
Currently Equipped Weapon: null
Currently Equipped Armor: null
Inventory: []

[>] H2Name: Skoraeus_Stonebones
Level: 1
State: CONSCIOUS
HP: 100
MP: 250
Strength: 650
Agility: 600
Dexterity: 350
Gold: 2500
Experience: 4/10
Hero Type: PALADIN
Currently Equipped Weapon: null
Currently Equipped Armor: null
Inventory: []

[>] H3Name: Garl_Glittergold
Level: 1
State: CONSCIOUS
HP: 100
MP: 100
Strength: 600
Agility: 500
Dexterity: 400
Gold: 2500
Experience: 5/10
Hero Type: PALADIN
Currently Equipped Weapon: null
Currently Equipped Armor: null
Inventory: []

[>] Above are information about heroes on the map: 
......
output:
[>] It is currently H2 Skoraeus_Stonebones's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster, M/m to enter market: 
input:
t
output:
[>] Basic Actions:
MOVEMENT:The W/A/S/D keys allow you to move, press them to go up/left/down/right respectively.
INVENTORY:Press U to access your heroes inventories, you will need to specify which hero's inventory to access after pressing U.
INFORMATION:Press I to display Hero or Monster inventory, you will need to specify if you want to see Hero or Monster info after pressing I.
MARKETS:When on a nexus tile, press M to access its market, this option cannot be executed unless a Hero is standing on one of the nexus tiles
TUTORIAL:Obviously, T displays the tutorial
TELEPORTATION TO ANOTHER LANE:allows you to teleport to a another lane that has a hero, specifically to the right/left/down of this hero
RECALL TO NEXUS:Press R to teleport a hero back to their nexus, you will need to specify which hero to teleport after pressing R
ATTACKING:Press F to attack with a hero, you will need to specify which hero to attack with after pressing F
......
output:
[>] It is currently H2 Skoraeus_Stonebones's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster, M/m to enter market: 
input:
p
output:
[>] Enter the lane you want H2 Skoraeus_Stonebones to teleport to: 1, 3, 
input:
1
output:
[>] Which hero in lane 1 would you like to teleport to: H1, 
input:
h1
output:
[>] Enter the direction in relation to the targetHero you want to teleport to: right, down, 
input:
right
output:
[>] Successfully moved to tile at (6,1)
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |     M0  | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
| H1      | | H2      | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | |         | |         | | X   X   | | H3      | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
......
output:
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |     M0  | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
| H1      | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
|         | | H2      | | X   X   | |         | |         | | X   X   | | H3      | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
[>] It is currently H2 Skoraeus_Stonebones's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster: 
input:
r
output:
[>] Successfully moved to tile at (7,3)
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |     M0  | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
| H1      | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
|         | |         | | X   X   | |         | |         | | X   X   | | H3      | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | | H2      | |         | | X   X   | |         | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
......
output:
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |     M0  | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
| H1      | |         | | X   X   | |         | |         | | X   X   | | H3      | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
|         | |         | | X   X   | | H2      | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 

[>] It is currently H1 Sehanine_Sunbow's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster: 
input:
f
output:
[>] Here are the Monsters that this hero can attack:
[*] - M0
[>] Enter the index of the monster you wish to attack
input:
m0
output:
[>] Sehanine_Sunbow attacked Natsunomeryu for 225.
[>] Natsunomeryu has 0 HP left. Natsunomeryu has fainted.
[>] Sehanine_Sunbow has gained 100 gold.
[>] Skoraeus_Stonebones has gained 100 gold.
[>] Garl_Glittergold has gained 100 gold.
[>] Sehanine_Sunbow has gained 2 exp.
[>] Skoraeus_Stonebones has gained 2 exp.
[>] Garl_Glittergold has gained 2 exp.
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |     M1  | | X   X   | |         | |     M2  | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
| H1      | |         | | X   X   | |         | |         | | X   X   | | H3      | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
|         | |         | | X   X   | | H2      | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
......
output:
[>] round: 9
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
|         | |     M3  | | X   X   | |         | |     M4  | | X   X   | |         | |     M5  | 
M---------M M---------M &---------& M---------M M---------M &---------& M---------M M---------M 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
|         | | H1      | | X   X   | |         | |         | | X   X   | |         | | H3      | 
C---------C P---------P &---------& C---------C P---------P &---------& B---------B B---------B 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
|         | |         | | X   X   | | H2      | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& P---------P K---------K &---------& K---------K B---------B 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
B---------B P---------P &---------& B---------B B---------B &---------& P---------P P---------P 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& P---------P P---------P &---------& P---------P P---------P 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
K---------K P---------P &---------& K---------K P---------P &---------& C---------C K---------K 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
P---------P P---------P &---------& B---------B P---------P &---------& K---------K C---------C 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
|         | |         | | X   X   | |         | |         | | X   X   | |         | |         | 
H---------H H---------H &---------& H---------H H---------H &---------& H---------H H---------H 
[>] It is currently H1 Sehanine_Sunbow's turn.
[>] Enter W/w to move up, A/a to move left, S/s to move down, D/d to move right, U/u to use the inventory of a hero, I/i to show information about the heroes, T/t for tutorial,  P/p to teleport to another lane, R/r to recall back to nexus, F/f to attack a monster: 
input:
w
output:
[>] Successfully moved to tile at (0,1)
[>] Victory! You've won the game in 9 rounds

[>] Total gold gain: 900
[>] Total gold spent: 0
[>] Highest hero level achieved: 2
[>] Would you like to play another round of Legends of Valor?(y/n)
input:
n
output:
[>] Welcome! Please enter the number for the game you want ot play: 
[0] Legends: Monsters and Heroes
[1] Legends of Valor
input:
q

---------------------------------------------------------------------------------

Heroes and Monsters:
output:
[>] Welcome! Please enter the number for the gameManager you want ot play:
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

[>] You were defeated in battle and gameManager is over.

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

[>] You were defeated in battle and gameManager is over.

[>] Total gold gain: 6700
[>] Total gold spent: 1700
[>] Highest hero level achieved: 4