import java.util.ArrayList;

public abstract class LVCell implements LVBoostStrategy {

	private int row;
	private int col;
	private char tileType;
	private char[] positions;
    private ArrayList<String> display;
	
	public LVCell(int row, int col, char tileType) {
		this.setRow(row);
		this.setCol(col);
		this.setTileType(tileType);
		this.display = new ArrayList<>();
		this.positions = new char[2]; // Initialize positions array
		positions[0] = ' '; // Default or initial values
        positions[1] = ' ';

		this.display.add(tileType + "------"+ tileType);
        this.display.add("| "+ this.positions[0] +"  "+ this.positions[1] +" |");
        this.display.add(tileType + "------"+ tileType);
	}

	@Override
    public void doBoostBehavior(Hero hero) {
        resetBoost(hero);
    }

    public void setPositions(char[] positions) {
        this.positions = positions;
        display.set(1,("| "+ this.positions[0] +"  "+ this.positions[1] + " |") );
    }

    public char[] getPositions(){
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
