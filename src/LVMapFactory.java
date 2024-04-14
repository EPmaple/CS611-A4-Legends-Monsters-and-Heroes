import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LVMapFactory implements BoardFactory {
    private ItemFactory ifInstance = ItemFactory.getInstance();
    @Override
    public Board createBoard(int rowNum, int colNum) {
        Random random = new Random();
        LVCell[][] board = new LVCell[rowNum][colNum];
        int num;
        for(int i = 0; i<rowNum; i++) {
            for(int j = 0; j<colNum; j++) {
                if(j == 2 || j == 5){
                    board[i][j] = new LVNonAccessibleTile(i,j);
                }
                else {
                    if (i == rowNum-1){
                        List<Item> stock = new ArrayList<Item>();

                        for (int k = 0; k < 10; k++) {
                            stock.add(ifInstance.createItem());
                        }

                        board[i][j] = new LVHeroNexusTile(i,j, stock);
                    }
                    else if(i == 0){
                        board[i][j] = new LVMonsterNexusTile(i,j);
                    }
                    else {
                        num = random.nextInt(10);
                        if (num < 2) {
                            board[i][j] = new LVKoulouTile(i,j);
                        }
                        else if (num <4) {
                            board[i][j] = new LVBushTile(i, j);
                        }
                        else if (num  < 6) {
                            board[i][j] = new LVCaveTile(i,j);
                        }
                        else{
                            board[i][j] = new LVPlainTile(i,j);
                        }

                    }
                }
            }
        }
        return new LVMap(board,rowNum,colNum);
    }
}
