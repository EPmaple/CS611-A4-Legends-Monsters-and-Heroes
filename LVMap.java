
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
        char[] pos = temp.getPositions();
        if(pos[0] != ' ' || row>7 || col>7 || row<0 || col<0) {
			return true;
		}else {
			return false;
		}
	}
}