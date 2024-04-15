import java.util.*;

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

    public List<Integer> getLanesForTeleportation(LVCell currentTile, List<Hero> heroes, Hero currentHero) {
        Set<Integer> lanesAvailable = new HashSet<>(); // Use a Set to avoid duplicates
        List<Hero> tempHeroes = new ArrayList<>(heroes);
        tempHeroes.remove(currentHero);
    
        // Determine lanes where other heroes are located
        for (Hero hero : tempHeroes) {
            // using integer division here
            // ex.) 2/3 = 0 + 1 = 1
            // ex.) 4/3 = 1 + 1 = 2
            int heroLane = (hero.getCol() / 3) + 1;
            // System.out.println("hero.getCol() from getLanesForTeleportation: " + hero.getCol());
            lanesAvailable.add(heroLane);
        }
    
        /*
         * in the case that another hero is in the same lane as this hero
         */
        // Determine the lane of the current hero
        int currentHeroLane = (currentTile.getCol() / 3) + 1;
        lanesAvailable.remove(currentHeroLane); // Remove the current hero's lane
        // System.out.println("lanesAvailable: " + lanesAvailable.toString());
    
        // Convert to list for return
        return new ArrayList<>(lanesAvailable);
        // List<Integer> lanesAvailable = new ArrayList<Integer>();
        // List<Hero> tempHeroes = new ArrayList<>(heroes);
        // tempHeroes.remove(currentHero);

        // /*
        // we will iterate through all heroes, except currentHero, to determine the lanes
        // available for tp

        // ite
        //  */
        // for (Hero hero : tempHeroes) {
        //     int heroCol = hero.getCol();
        //     for (Integer i = 0; i < 3; i++) {
        //         if (3 * i + 1 == heroCol || 3 * i == heroCol) {
        //             lanesAvailable.add(i + 1);
        //         }
        //     }
        // }

        // /*
        //  * do such a loop to find out the lane that the currentHero is on
        //  * 
        //  * then based on this currentHeroLane, we will iterate to find out
        //  * all heroes that are not on this lane
        //  * 
        //  * then iterate through tempHeroes to their lanes specifically
        //  */



        // // 0 1 |2| 3 4 |5| 6 7
        // if (currentTile.getCol() == 0 || currentTile.getCol() == 1) {
        //     lanesAvailable.remove(Integer.valueOf(1));
        // } else if (currentTile.getCol() == 3 || currentTile.getCol() == 4) {
        //     lanesAvailable.remove(Integer.valueOf(2));
        // } else if (currentTile.getCol() == 6 || currentTile.getCol() == 7) {
        //     lanesAvailable.remove(Integer.valueOf(3));
        // } else {
        //     throw new IllegalArgumentException("You have reached the else "+
        //             "statement of LVMap.getLanesForTeleportation(), which "+
        //             "should not happen if the logic for moving and placing "+
        //             "a hero on the board is correct.");
        // }

        // int counter = 0; // to make sure there is a hero in the lane to be teleported to
        // for (Integer laneNum : lanesAvailable) {
        //     for (Hero hero : heroes) {
        //         int heroCol = hero.getCol();
        //         if (3 * (laneNum - 1) + 1 == heroCol || 3 * (laneNum - 1) == heroCol) {
        //             counter += 1;
        //         }
        //     }
        //     if (counter == 0) {
        //         lanesAvailable.remove(Integer.valueOf(laneNum));
        //     }
        // }

        // System.out.println(lanesAvailable.toString());
        // return lanesAvailable;
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
    // public LVCell[] getAttackTiles(int row, int col){
    //     //the tiles will be ordered in a clockwise fashion, tile at the 0th index will be the tile above the heroes pos
    //     LVCell[] tilesInRange = new LVCell[8];
    //     LVCell curCell = getCell(row, col);
    //     //filling out adjacent tiles
    //     tilesInRange[0] =  getUpTile(curCell); //top
    //     tilesInRange[2] =  getRightTile(curCell); //right
    //     tilesInRange[4] = getDownTile(curCell); //bottom
    //     tilesInRange[6] = getLeftTile(curCell); //left

    //     //filling out diagonal tiles
    //     tilesInRange[1] = getUpTile(tilesInRange[2]);  //top right diagonal
    //     tilesInRange[3] = getDownTile(tilesInRange[2]); //bottom right diagonal
    //     tilesInRange[7] = getUpTile(tilesInRange[6]);  //top left diagonal
    //     tilesInRange[5] = getDownTile(tilesInRange[6]); //bottom left diagonal

    //     return tilesInRange;
    // }

    public List<LVCell> getAttackTiles(int row, int col) {
        List<LVCell> tilesInRange =  new ArrayList<LVCell>();
        LVCell curCell = getCell(row, col);

        // topTile
        LVCell topTile = getUpTile(curCell);
        if (topTile != null) {
            tilesInRange.add(topTile);
        }
        // right
        LVCell rightTile = getRightTile(curCell);
        if (rightTile != null) { // that rightTile isInBounds
            tilesInRange.add(rightTile);

            // top right diagonal
            LVCell topRightTile = getUpTile(rightTile);
            if (topRightTile != null) {
                tilesInRange.add(topRightTile);
            }
            // bottom right diagonal
            LVCell bottomRightTile = getDownTile(rightTile);
            if (bottomRightTile != null) {
                tilesInRange.add(bottomRightTile);
            }
        }
        // bottom
        LVCell bottomTile = getDownTile(curCell);
        if (bottomTile != null) {
            tilesInRange.add(bottomTile);
        }
        // left
        LVCell leftTile = getLeftTile(curCell);
        if (leftTile != null) {
            tilesInRange.add(leftTile);

            // top left diagonal
            LVCell topLeftTile = getUpTile(leftTile);
            if (topLeftTile != null) {
                tilesInRange.add(topLeftTile);
            }
            // bottom left diagonal
            LVCell bottomLeftTile = getDownTile(leftTile);
            if (bottomLeftTile != null) {
                tilesInRange.add(bottomLeftTile);
            }
        }
        return tilesInRange;
    }

    // public LVCell[] getAttackTiles(LVCell curCell){
    //     //the tiles will be ordered in a clockwise fashion, tile at the 0th index will be the tile above the heroes pos
    //     LVCell[] tilesInRange = new LVCell[8];
    //     //filling out adjacent tiles
    //     tilesInRange[0] =  getUpTile(curCell); //top
    //     tilesInRange[2] =  getRightTile(curCell); //right
    //     tilesInRange[4] = getDownTile(curCell); //bottom
    //     tilesInRange[6] = getLeftTile(curCell); //left

    //     //filling out diagonal tiles
    //     tilesInRange[1] = getUpTile(tilesInRange[2]);  //top right diagonal
    //     tilesInRange[3] = getDownTile(tilesInRange[2]); //bottom right diagonal
    //     tilesInRange[7] = getUpTile(tilesInRange[6]);  //top left diagonal
    //     tilesInRange[5] = getDownTile(tilesInRange[6]); //bottom left diagonal

    //     return tilesInRange;
    // }

    public List<LVCell> getAttackTiles(LVCell curCell) {
        List<LVCell> tilesInRange =  new ArrayList<LVCell>();

        // topTile, which will never be null in a normal game
        LVCell topTile = getUpTile(curCell);
        tilesInRange.add(topTile);
        // right
        LVCell rightTile = getRightTile(curCell);
        if (rightTile != null) { // that rightTile isInBounds
            tilesInRange.add(rightTile);

            // top right diagonal
            LVCell topRightTile = getUpTile(rightTile);
            if (topRightTile != null) {
                tilesInRange.add(topRightTile);
            }
            // bottom right diagonal
            LVCell bottomRightTile = getDownTile(rightTile);
            if (bottomRightTile != null) {
                tilesInRange.add(bottomRightTile);
            }
        }
        // bottom
        LVCell bottomTile = getDownTile(curCell);
        if (bottomTile != null) {
            tilesInRange.add(bottomTile);
        }
        // left
        LVCell leftTile = getLeftTile(curCell);
        if (leftTile != null) {
            tilesInRange.add(leftTile);

            // top left diagonal
            LVCell topLeftTile = getUpTile(leftTile);
            if (topLeftTile != null) {
                tilesInRange.add(topLeftTile);
            }
            // bottom left diagonal
            LVCell bottomLeftTile = getDownTile(leftTile);
            if (bottomLeftTile != null) {
                tilesInRange.add(bottomLeftTile);
            }
        }
        return tilesInRange;
    }
}