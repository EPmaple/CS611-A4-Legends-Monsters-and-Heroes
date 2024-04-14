import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LVMap extends Board{
	//the class represents board of Legeand of Valor game
    public LVMap(LVCell[][] board, int rowNum, int colNum) {
        super(board, rowNum, colNum);
    }

    public LVCell getCell(int row, int col) {
        return (LVCell) board[row][col];
    }

    @Override
    public String toString() {
        String gameBoard = "";
        for(int i = 0; i< board.length; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < board[i].length; j++) {
                    LVCell lovcell = (LVCell) board[i][j];
                    gameBoard += lovcell.display().get(k) + " ";
                }

                gameBoard += '\n';
            }
        }
        return gameBoard;
    }
    
    
    public boolean checkCellAccess(int row, int col){
		LVCell temp = getCell(row, col);
        String[] pos = temp.getPositions();
        if(pos[0].trim().isEmpty() || row>7 || col>7 || row<0 || col<0) {
			return true;
		}else {
			return false;
		}
	}

    // *******************************************************

    public List<Integer> getLanesForTeleportation(LVCell currentTile) {
        List<Integer> lanesAvailable = new ArrayList<Integer>();
        lanesAvailable.addAll(Arrays.asList(1, 2, 3));

        // 0 1 |2| 3 4 |5| 6 7
        if (currentTile.getCol() == 0 || currentTile.getCol() == 1) {
            lanesAvailable.remove(Integer.valueOf(1));
        } else if (currentTile.getCol() == 3 || currentTile.getCol() == 4) {
            lanesAvailable.remove(Integer.valueOf(2));
        } else if (currentTile.getCol() == 6 || currentTile.getCol() == 7) {
            lanesAvailable.remove(Integer.valueOf(3));
        } else {
            throw new IllegalArgumentException("You have reached the else "+
                    "statement of LVMap.getLanesForTeleportation(), which "+
                    "should not happen if the logic for moving and placing "+
                    "a hero on the board is correct.");
        }

        return lanesAvailable;
    }

    // return true if the row does have monster
    // return false if the row does not have monster
    public boolean checkRowForMonster(LVCell currentTile) {
        // isCurrentTileEmpty, specifically means: there is no monster in this
        // tile, right?
        boolean currentTileHasMonster = false;
        if (!currentTile.getPositions()[1].trim().isEmpty()) {
            currentTileHasMonster = true;
        }

        boolean adjacentTileHasMonster = false;
        // go in the left direction
        LVCell leftTile = getLeftTile(currentTile);
        if (leftTile != null) { // tile isInBounds
            if (leftTile.getTileType() != '&') { // tile is not NonAccessible
                // Tile has a monster
                if (!leftTile.getPositions()[1].trim().isEmpty()) {
                    adjacentTileHasMonster = true;
                }
            }
        }
        // go in the right direction
        LVCell rightTile = getRightTile(currentTile);
        if (rightTile != null) { // tile isInBounds
            if (rightTile.getTileType() != '&') { // tile is not NonAccessible
                // Tile has a monster
                if (!rightTile.getPositions()[1].trim().isEmpty()) {
                    adjacentTileHasMonster = true;
                }
            }
        }

        return currentTileHasMonster && adjacentTileHasMonster;
    }

    /*
    board:
        col 0 1 2
    row
    0
    1
    2
     */
    public LVCell getUpTile(LVCell tile) {
        int row = tile.getRow();
        int col = tile.getCol();
        if (isInBounds(row - 1, col)) {
            return getCell(row - 1, col);
        }
        return null;
    }
    public LVCell getDownTile(LVCell tile) {
        int row = tile.getRow();
        int col = tile.getCol();
        if (isInBounds(row + 1, col)) {
            return getCell(row + 1, col);
        }
        return null;
    }
    public LVCell getLeftTile(LVCell tile) {
        int row = tile.getRow();
        int col = tile.getCol();
        if (isInBounds(row, col - 1)) {
            return getCell(row, col - 1);
        }
        return null;
    }
    public LVCell getRightTile(LVCell tile) {
        int row = tile.getRow();
        int col = tile.getCol();
        if (isInBounds(row, col + 1)) {
            return getCell(row, col + 1);
        }
        return null;
    }

    // within the 8x8 bound of the map, whereas the check for NonAccessible should
    // be done explicitly in an upper level method
    private boolean isInBounds(int row, int col) {
        if ((row >= 0 && row < this.board.length ) &&
                (col >= 0 && col < this.board[0].length )) {
            return true;
        }
        return false;
    }

    //this method returns tiles that are within the attack range of heroes
    //including impassable tiles and tiles without mosnters
    public LVCell[] getAttackTiles(int row, int col){
        //the tiles will be ordered in a clockwise fashion, tile at the 0th index will be the tile above the heroes pos
        LVCell[] tilesInRange = new LVCell[8];
        LVCell curCell = getCell(row, col);
        //filling out adjacent tiles
        tilesInRange[0] =  getUpTile(curCell); //top
        tilesInRange[2] =  getRightTile(curCell); //right
        tilesInRange[4] = getDownTile(curCell); //bottom
        tilesInRange[6] = getLeftTile(curCell); //left

        //filling out diagonal tiles
        tilesInRange[1] = getUpTile(tilesInRange[2]);  //top right diagonal
        tilesInRange[3] = getDownTile(tilesInRange[2]); //bottom right diagonal
        tilesInRange[7] = getUpTile(tilesInRange[6]);  //top left diagonal
        tilesInRange[5] = getDownTile(tilesInRange[6]); //bottom left diagonal

        return tilesInRange;
    }

    public LVCell[] getAttackTiles(LVCell curCell){
        //the tiles will be ordered in a clockwise fashion, tile at the 0th index will be the tile above the heroes pos
        LVCell[] tilesInRange = new LVCell[8];
        //filling out adjacent tiles
        tilesInRange[0] =  getUpTile(curCell); //top
        tilesInRange[2] =  getRightTile(curCell); //right
        tilesInRange[4] = getDownTile(curCell); //bottom
        tilesInRange[6] = getLeftTile(curCell); //left

        //filling out diagonal tiles
        tilesInRange[1] = getUpTile(tilesInRange[2]);  //top right diagonal
        tilesInRange[3] = getDownTile(tilesInRange[2]); //bottom right diagonal
        tilesInRange[7] = getUpTile(tilesInRange[6]);  //top left diagonal
        tilesInRange[5] = getDownTile(tilesInRange[6]); //bottom left diagonal

        return tilesInRange;
    }
}