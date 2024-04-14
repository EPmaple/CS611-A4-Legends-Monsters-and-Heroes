//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//@SuppressWarnings("unused")
//class LVMapFactoryTest {
//
//    @Test
//    public void createBoard() {
//        LVMap board = (LVMap) new LVMapFactory().createBoard(8,8);
//        ArrayList<Hero> ht = new ArrayList<Hero>();
//        ArrayList<Monster> mt = new ArrayList<Monster>();
//        for(int i = 0; i< 3; i++) {
//        	Warrior warrior = new Warrior(
//                    "Aragorn", // name
//                    100, // mana
//                    150, // strength
//                    100, // agility
//                    80,  // dexterity
//                    1000, // money
//                    0,    // experience
//                    100,  // hp
//                    50,   // defense
//                    1,    // level
//                    State.CONSCIOUS, // state
//                    null, // weapon (no weapon equipped)
//                    null, // armor (no armor equipped)
//                    null, // heroType (no specific type)
//                    new ArrayList<>() // inventory (empty inventory)
//                );
//            warrior.setRow(7);
//            warrior.setCol(3 * i);
//            ht.add(warrior);
//            LVCell tempCell = (LVCell)  board.getCell(7,3*i);
//            tempCell.setPositions(new char[]{'H',' '});
//        }
//        System.out.print(board.toString());
//    }
//
//    public class Warrior extends Hero {
//        public Warrior(String name, int mana, int strength, int agility, int dexterity, int money, int experience,
//                       int hp, int defense, int level, State state, Weapon weapon, Armor armor, HeroType heroType,
//                       List<Item> inventory) {
//            super(name, mana, strength, state, dexterity, money, experience, hp, defense, level, agility, weapon, armor, heroType, inventory);
//        }
//        @Override
//        public void updateSkillsOnLvlUp() {
//            // Implement logic specific to leveling up a Warrior
//            // Example: Increase strength more compared to other attributes
//            this.strength += 10; // increase strength by 10 upon level-up
//            // Similarly update other attributes...
//        }
//}
//}
//// import static org.junit.jupiter.api.Assertions.*;
//// import org.junit.jupiter.api.Test;
//
//// public class LVMapFactoryTest {
//
////     @Test
////     public void testCreateMap() {
////         // Assume LVMapFactory and LVMap have been correctly implemented
////         LVMapFactory factory = new LVMapFactory();
////         LVMap map = (LVMap) factory.createBoard(8, 8); // Creates an 8x8 map
//
////         // Test the map dimensions
////         assertEquals(8, map.getRowNum());
////         assertEquals(8, map.getColNum());
//
////         // Test specific tile types at certain locations
////         assertTrue(map.getCell(0, 2) instanceof LVNonAccessibleTile, "Expected non-accessible tile at (0,2)");
////         assertTrue(map.getCell(0, 5) instanceof LVNonAccessibleTile, "Expected non-accessible tile at (0,5)");
//
////         // Add more assertions as necessary to cover all critical aspects of the map
////     }
//// }
