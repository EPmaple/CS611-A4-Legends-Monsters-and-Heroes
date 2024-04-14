import java.util.ArrayList;

public abstract class LVCell implements LVBoostStrategy {

	private int row;
	private int col;
	private char tileType;
	private String[] positions;
    private ArrayList<String> display;
	
	public LVCell(int row, int col, char tileType) {
		this.setRow(row);
		this.setCol(col);
		this.setTileType(tileType);
		this.display = new ArrayList<>();
		this.positions = new String[2]; // Initialize positions array
		positions[0] = Constants.DEFAULT_LVCELL_HEROPOSITION; // Default or initial values
        positions[1] = Constants.DEFAULT_LVCELL_MONSTERPOSITION; // handle the case of M**
		/*
		k---------k
		| H1  M12 |

		if position[1].length greater than
		 */
		this.display.add(tileType + "---------"+ tileType);
        this.display.add("| "+ this.positions[0] +"  "+ this.positions[1] +" |");
        this.display.add(tileType + "---------"+ tileType);
	}

	@Override
    public void doBoostBehavior(Hero hero) {
        resetBoost(hero);
    }

    public void setPositions(String[] positions) {
		// pad it with an extra space to make things line up
		if (positions[1].length() < 3) {
			positions[1] = positions[1] + " ";
		}
        this.positions = positions;
        display.set(1,("| "+ this.positions[0] +"  "+ this.positions[1] + " |") );
    }

    public String[] getPositions(){
        return positions;
    }

    public ArrayList<String> display() {
        return display;
    }

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public char getTileType() {
		return tileType;
	}

	public void setTileType(char type) {
		this.tileType = type;
	}
}
