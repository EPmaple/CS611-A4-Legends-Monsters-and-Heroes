/*
 * BoardFactory.java
 * Ankur
 * 4/15/2024
 * 
 * A factory interface to specifying the creation of a board
 */

public interface BoardFactory {
	//the class initializes board
    Board createBoard(int row, int column);
}
