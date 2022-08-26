import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private char[][] matrix;
    private int height;
    private int width;
    private Cell[][] cells;
    public int boxes;
    public int objectives;
    private Position sokoban;
    private List <Position> objectivelist;
    private List <Position> boxlist;
    private int right;
    private int down;
    private int up;
    private int left;
    private boolean objective_in_left;
    private boolean objective_in_right;
    private boolean objective_in_up;
    private boolean objective_in_down;
    //history_of_moves can be easily implemented
    
    public Board(char[][] matrix,Cell[][] cells ,int boxes, int objectives, Position sokoban , List<Position> objectivelist , List<Position> boxlist){
        this.matrix = matrix;
        this.cells = cells;
        this.height = matrix.length;
        this.width = matrix[0].length;
        this.boxes  = boxes;
        this.objectives = objectives;
        this.sokoban = sokoban;
        this.objectivelist = objectivelist;
        this.boxlist = new ArrayList<>(boxlist);
        this.right = width - 3;
        this.down = height - 2;
        this.up = 1;
        this.left = 1;
        //this.print_col(right-1);
        objective_in_right = obj_col(right);
        //System.out.println(objective_in_right);
        //this.print_row(1);
        objective_in_up    = obj_row(up);
        //System.out.println(objective_in_up);
        //this.print_col(1);
        objective_in_left  = obj_col(left);
        //System.out.println(objective_in_left);
        //this.print_row(down);
        objective_in_down  = obj_row(down);
        //System.out.println(objective_in_down);
        /*for(Position pos : boxlist){
        	this.boxlist.add(pos);
		}*/
        //System.out.println("DEBUG: "+ objectivelist.size() + " - " + this.boxlist.size());
    }
    
    public Board() {
		// TODO Auto-generated constructor stub
	}
    
    public boolean inRange(int i , int j) {
    	return inRange(new Position(i,j));
    }
    
    public boolean inRange(Position p) {
    	return 0 <= p.getRow() && p.getRow() < width && 0<= p.getColumn() && p.getColumn() < height;	
    }
    
	public boolean isValid(Position p) {
    	return 0 <= p.getRow() && p.getRow() < width && 0<= p.getColumn() && p.getColumn() < height && cells[p.getColumn()][p.getRow()].getId() != '#' && !cells[p.getColumn()][p.getRow()].is_star() ;
    }

	@Override
	public boolean equals(Object obj) {
    	if(!(obj instanceof Board))
    		return false;
		return this.equals(this, (Board)obj);
	}
	
	
	public static boolean equals (Board b1, Board b2){
        if(b1.boxes != b2.boxes) {
            return false;
        }
        if(b1.cells[0].length != b2.cells[0].length) {
            return false;
        }
        if(b1.cells.length != b2.cells.length) {
            return false;
        }
        if(b1.objectives != b2.objectives)
        	return false;
        if(!b1.objectivelist.containsAll(b2.objectivelist))
        	return false;
		if(!b1.boxlist.containsAll(b2.boxlist))
			return false;
		if(!b1.sokoban.equals(b2.sokoban))
			return false;
        for (int i = 0;i<b1.cells.length;i++){
            for (int j = 0;j<b1.cells[0].length;j++){
            	//System.out.println("comparing " + b1.cells[i][j].getHas() + " with " + b2.cells[i][j].getHas() );
                //System.out.println(String.format("%d %d",i,j));
            	if(b1.cells[i][j].getHas() != b2.cells[i][j].getHas()) {
                	return false;
                }
				if(b1.cells[i][j].getId() != b2.cells[i][j].getId()) {
					return false;
				}
            }
        }
        return true;
    }
    
    public List<Board> possibleMoves(){
        List<Board> possibleBoards = new ArrayList<>();
        Board current;
        if((current=moveRight()) != null)
            possibleBoards.add(current);
        if((current=moveLeft()) != null)
            possibleBoards.add(current);
        if((current=moveUp()) != null)
            possibleBoards.add(current);
        if((current=moveDown()) != null)
            possibleBoards.add(current);

        return possibleBoards;
    }

	@Override
	public int hashCode() {
		return Objects.hash(this.matrix ,this.height, this.cells , this.objectives , this.sokoban , this.objectivelist , this.boxlist , this.boxes, this.matrix, this.width);
    }

    public Board moveRight(){
    	int i = sokoban.getColumn();
    	int j = sokoban.getRow();
		Cell [][] copyCell = new Cell[this.height][this.width];
		for(int k = 0; k< cells.length; k++)
		{
			Cell[] aMatrix = cells[k];
			int   aLength = aMatrix.length;
			copyCell[k] = new Cell[aLength];
			for(int l = 0;l<aLength;l++){
				copyCell[k][l] = new Cell(cells[k][l].getId(), cells[k][l].getHas());
			}
		}
		Board ret = new Board(matrix,copyCell,boxes,objectives,new Position(sokoban.getRow(),sokoban.getColumn()),objectivelist,boxlist);

		Position next = new Position(i,j+1);
    	if(!this.isValid(next)) {
    		return null;
    	}
    	boolean r = this.isValid(next) && this.getCells()[i][j+1].getHas() == ' ';
    	if(r) {
    		ret.setSokoban(new Position(i,j+1));
    		char c = ret.cells[i][j].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i][j+1].in(c);
    		return ret;
    	}
    	Position nextnext = new Position(i,j+2);
    	if(!this.isValid(nextnext)) {
    		return null;
    	}
    	boolean R = this.isValid(next) && this.getCells()[i][j+1].getHas() == '$';
    	if(R) {
    		ret.setSokoban(new Position(i,j+1));
    		char c = ret.cells[i][j].out();
    		char d = ret.cells[i][j+1].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i][j+1].in(c);
    		ret.cells[i][j+2].in(d);
    		if(ret.cells[i][j+2].getId()=='.'&&d=='$') {
    			ret.objectives++;
    		}
    		if(ret.cells[i][j+1].getId()=='.'&&d=='$') {
    			ret.objectives--;
    		}
    		if(d=='$') {
    			ret.boxlist.remove(new Position(i,j+1));
    			ret.boxlist.add(new Position(i,j+2));

    		}
    		return ret;
    	}
    	return null;
    }
    
    public Board moveLeft(){
    	int i = sokoban.getColumn();
    	int j = sokoban.getRow();
    	Position next = new Position(i,j-1);
		Cell [][] copyCell = new Cell[this.height][this.width];
		for(int k = 0; k< cells.length; k++)
		{
			Cell[] aMatrix = cells[k];
			int   aLength = aMatrix.length;
			copyCell[k] = new Cell[aLength];
			for(int l = 0;l<aLength;l++){
				copyCell[k][l] = new Cell(cells[k][l].getId(), cells[k][l].getHas());
			}
		}
		Board ret = new Board(matrix,copyCell,boxes,objectives,new Position(sokoban.getRow(),sokoban.getColumn()),objectivelist,boxlist);

		if(!this.isValid(next)) {
    		return null;
    	}
    	boolean l = this.isValid(next) && this.getCells()[i][j-1].getHas() == ' ';
    	if(l) {
    		ret.setSokoban(new Position(i,j-1));
    		char c = ret.cells[i][j].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i][j-1].in(c);
    		return ret;
    	}
    	Position nextnext = new Position(i,j-2);
    	if(!this.isValid(nextnext)) {
    		return null;
    	}
    	boolean L = this.isValid(next) && this.getCells()[i][j-1].getHas() == '$';
    	if(L) {
    		ret.setSokoban(new Position(i,j-1));
    		char c = ret.cells[i][j].out();
    		char d = ret.cells[i][j-1].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i][j-1].in(c);
    		ret.cells[i][j-2].in(d);
    		if(ret.cells[i][j-2].getId()=='.'&&d=='$') {
    			ret.objectives++;
    		}
    		if(ret.cells[i][j-1].getId()=='.'&&d=='$') {
    			ret.objectives--;
    		}
    		if(d=='$') {
    			ret.boxlist.remove(new Position(i,j-1));
    			ret.boxlist.add(new Position(i,j-2));

    		}
    		return ret;
    	}
    	return null;
    }
    
    public Board moveDown(){
    	int i = sokoban.getColumn();
    	int j = sokoban.getRow();
		Cell [][] copyCell = new Cell[this.height][this.width];
		for(int k = 0; k< cells.length; k++)
		{
			Cell[] aMatrix = cells[k];
			int   aLength = aMatrix.length;
			copyCell[k] = new Cell[aLength];
			for(int l = 0;l<aLength;l++){
				copyCell[k][l] = new Cell(cells[k][l].getId(), cells[k][l].getHas());
			}
		}
		Board ret = new Board(matrix,copyCell,boxes,objectives,new Position(sokoban.getRow(),sokoban.getColumn()),objectivelist,boxlist);

    	
    	Position next = new Position(i+1,j);
    	if(!this.isValid(next)) {
    		return null;
    	}
    	boolean d = this.isValid(next) && this.getCells()[i+1][j].getHas() == ' ';
    	if(d) {
    		ret.setSokoban(new Position(i+1,j));
    		char c = ret.cells[i][j].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i+1][j].in(c);
    		return ret;
    	}
    	Position nextnext = new Position(i+2,j);
    	if(!this.isValid(nextnext)) {
    		return null;
    	}
    	boolean D = this.isValid(next) && this.getCells()[i+1][j].getHas() == '$';
    	if(D) {
    		ret.setSokoban(new Position(i+1,j));    		
    		char c = ret.cells[i][j].out();
    		char d_ = ret.cells[i+1][j].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i+1][j].in(c);
    		ret.cells[i+2][j].in(d_);
    		if(ret.cells[i+2][j].getId()=='.'&&d_=='$') {
    			ret.objectives++;
    		}
    		if(ret.cells[i+1][j].getId()=='.'&&d_=='$') {
    			ret.objectives--;
    		}
    		if(d_=='$') {
    			ret.boxlist.remove(new Position(i+1,j));
    			ret.boxlist.add(new Position(i+2,j));

    		}
    		return ret;
    	}
    	return null;
    }
    public Board moveUp(){
    	int i = sokoban.getColumn();
    	int j = sokoban.getRow();
    	Position next = new Position(i-1,j);
		Cell [][] copyCell = new Cell[this.height][this.width];
		for(int k = 0; k< cells.length; k++)
		{
			Cell[] aMatrix = cells[k];
			int   aLength = aMatrix.length;
			copyCell[k] = new Cell[aLength];
			for(int l = 0;l<aLength;l++){
				copyCell[k][l] = new Cell(cells[k][l].getId(), cells[k][l].getHas());
			}
		}
		Board ret = new Board(matrix,copyCell,boxes,objectives,new Position(sokoban.getRow(),sokoban.getColumn()),objectivelist,boxlist);

		if(!this.isValid(next)) {
    		return null;
    	}
    	boolean u = this.isValid(next) && this.getCells()[i-1][j].getHas() == ' ';
    	if(u) {
    		ret.setSokoban(new Position(i-1,j));
    		char c = ret.cells[i][j].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i-1][j].in(c);
    		return ret;
    	}
    	Position nextnext = new Position(i-2,j);
    	if(!this.isValid(nextnext)) {
    		return null;
    	}
    	boolean U = this.isValid(next) && this.getCells()[i-1][j].getHas() == '$';
    	if(U) {
    		ret.setSokoban(new Position(i-1,j));
    		char c = ret.cells[i][j].out();
    		char d = ret.cells[i-1][j].out();
    		ret.cells[i][j].in(' ');
    		ret.cells[i-1][j].in(c);
    		ret.cells[i-2][j].in(d);
    		if(ret.cells[i-2][j].getId()=='.'&&d=='$') {
    			ret.objectives++;
    		}
    		if(ret.cells[i-1][j].getId()=='.'&&d=='$') {
    			ret.objectives--;
    		}
    		if(d=='$') {
    			ret.boxlist.remove(new Position(i-1,j));
    			ret.boxlist.add(new Position(i-2,j));

    		}
    		return ret;
    	}
    	return null;
    }
    
    public boolean is_objective(Position other){
    	for(Position p : objectivelist) {
    		if(p.equals(other)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isSolution() {
    	return this.objectives == this.boxes;
    }
    
    public void printObjectives() {
    	for(Position p : objectivelist) {
    		System.out.println("objective at " + p);
    	}
    }
    
    public void printBoxlist() {
    	for(Position p : boxlist) {
    		System.out.println("box at " + p);
    	}
    }
    
    public List<Position> getObjectives(){
    	return objectivelist;
    }
    
    public List<Position> getBoxlist(){
    	return boxlist;
    }
    
    public void setSokoban(Position p) {
    	this.sokoban = p;
    }
    
    public Position getSokoban() {
    	return sokoban;
    }
    
    public char[][] getMatrix(){
    	return matrix;
    }
    
    public Cell[][] getCells(){
    	return cells;
    }
    
    public void setPosition(int i, int j, char value){
        this.matrix[i][j] = value;
    }
    public char getPosition(int i, int j){
        return this.matrix[i][j];
    }
    
    public void printCells() {
    	for (int i = 0;i<this.cells.length;i++){
            for (int j = 0;j<this.cells[0].length;j++) {
            	System.out.print(this.cells[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public void printBoard(){
        for (int i = 0;i<this.matrix.length;i++){
            for (int j = 0;j<this.matrix[0].length;j++) {
                System.out.print(this.matrix[i][j]);
            }
        }
        System.out.print("\n");
    }

	/***
	 * Admissible Heuristic 1.
	 * @return minimal distance between boxes and storage locations
	 */
	public int manhattanDistanceBoxObjective(boolean deadlockOn) {

		//For every box, calculate nearest distance to box place
		if(isSolution())
			return 0;
		if(deadlockOn && deadlock()){
			return 999999;
		}
		int globalMin = 999999, minDistance=999999, currDist;
		for (Position box : boxlist){
			if(!objectivelist.contains(box)){
				for(Position storage : objectivelist){
					currDist = (Math.abs(box.getRow() - storage.getRow()) + Math.abs(box.getColumn() - storage.getColumn()));
					if(currDist < minDistance)
						minDistance = currDist;
				}
				if(minDistance < globalMin)
					globalMin = minDistance;
			}
		}
		return  globalMin;
	}

	/***
	 * Admissible Heuristic 2.
	 * @return the sum distance between player and any box that is not in the objective
	 */
	public int manhattanDistancePlayerBox(boolean deadlockOn) {
		if(isSolution())
			return 0;
		if(deadlockOn && deadlock())
			return 999999;
		int currDistance = 0;
		for(Position box : boxlist){
			if(!objectivelist.contains(box)) {
				currDistance += (Math.abs(box.getRow() - sokoban.getRow()) + Math.abs(box.getColumn() - sokoban.getColumn()));
			}
		}
		return currDistance;
	}
	
	public void print_col(int col) {
		for (int i = 0;i<this.cells.length;i++){
            for (int j = 0;j<this.cells[0].length;j++) {
            	if(j==col){
            		System.out.print(this.cells[i][j]);
            	}
            	else{
            		System.out.print('-');
            	}
            }
            System.out.print("\n");
        }
        System.out.print("\n");
	}
	
	public void print_row(int row) {
		for (int i = 0;i<this.cells.length;i++){
            	if(i==row){
            		for (int j = 0;j<this.cells[0].length;j++) {
            		System.out.print(this.cells[i][j]);
            		}
            }
            	else{
            		for (int j = 0;j<this.cells[0].length;j++) {
                		System.out.print("-");
                		}
            	}
            System.out.print("\n");
        }
        System.out.print("\n");
	}
	
	public boolean obj_col(int col) {
		for (int i = 0;i<this.cells.length;i++){
            for (int j = 0;j<this.cells[0].length;j++) {
            	if(j==col){
            		if(cells[i][j].getId()=='.') {
        				return true;
        			}
            	}
            }
        }
		return false;
	}
	
	public boolean obj_row(int row) {
		for (int i = 0;i<this.cells.length;i++){
            	if(i==row){
            		for (int j = 0;j<this.cells[0].length;j++) {
            			if(cells[i][j].getId()=='.') {
            				return true;
            			}
            		}
            }
        }
		return false;
	}
	
	public String print_down_wall() {
		return null;
	}
	
	/*
	_
	|
	|$
	|
	|
	|
	-
	 */
	//TODO: Caja en una pared sin objetivo
	private boolean edge_deadlock() {
		int i = sokoban.getColumn();
		int j = sokoban.getRow();
		if(i==left) {
			return this.objective_in_left;
		}
		if(i==right) {
			return this.objective_in_right;
		}
		if(j==up) {
			return this.objective_in_up;
		}
		if(j==down) {
			return this.objective_in_down;
		}
		return false;
	}
	
	private boolean corner_deadlock() {
		
		for(Position p : boxlist) {
			if(!objectivelist.contains(p)){
				int i = p.getRow();
				int j = p.getColumn();
				boolean valid = inRange(i-1,j) && inRange(i+1,j) && inRange(i,j-1) && inRange(i,j+1);
				if(valid && (cells[i-1][j].getId()=='#'||cells[i+1][j].getId()=='#')&&(cells[i][j-1].getId()=='#'||cells[i][j+1].getId()=='#')) {
					return true;
				}
			}
		}
		return false;	
	}
	
	private boolean deadlock(){
		return corner_deadlock() || edge_deadlock();
	}

	/***
	 * Non admissible.
	 * @return the area the boxes between sum(sokoban -> boxes) + sum(boxes -> objective)
	 */
	public int nonAdmissibleHeuristic(boolean deadlockOn) {
		return manhattanDistanceBoxObjective(false) * manhattanDistancePlayerBox(false) * admissibleHeuristic3(false) * boxes;
	}

	/***
	 * Admissible Heuristic 3.
	 * @return best heuristic
	 */
	//TODO:
	public int admissibleHeuristic3(boolean deadlockOn){
		if(isSolution())
			return 0;
		int minDist = 999999;
		if(deadlockOn && deadlock())
			return minDist;
		//sumdistancias

/*
		objectivelist [p1,p2,p3]
		boxlist       [b1,b2,b3]
		------------------------
		Ret: [2,0,1]

 */
		int accum = 0;
		int [] ret = relateObjToBoxes();
		for(int i = 0;i<objectivelist.size();i++){
			accum += Math.abs(sokoban.getColumn() - boxlist.get(ret[i]).getColumn()) + Math.abs(sokoban.getRow() - boxlist.get(ret[i]).getRow());
			accum += Math.abs(boxlist.get(ret[i]).getColumn() - objectivelist.get(i).getColumn()) + Math.abs(boxlist.get(ret[i]).getRow() - objectivelist.get(i).getRow());
		}
		return accum;

/*		for(Position box : boxlist){
			if(!objectivelist.contains(box)) {
				for(Position obj : objectivelist){
					actDist = Math.abs(sokoban.getColumn() - box.getColumn()) + Math.abs(sokoban.getRow() - box.getRow());
					actDist += Math.abs(box.getColumn() - obj.getColumn()) + Math.abs(box.getRow() - obj.getRow());
					minDist = Math.min(minDist,actDist);
				}
			}
		}
		return minDist;*/
	}


	private int[] relateObjToBoxes(){
		int j = 0;
		int[] ret = new int[objectivelist.size()];
		for(Position p : objectivelist){
			int minDist = 99999;
			int i = 0;
			int minPosition=0;
			for(Position b: boxlist){
				if(Math.abs(p.getColumn() - b.getColumn()) + Math.abs(p.getRow() - b.getRow()) < minDist){
					minDist = Math.abs(p.getColumn() - b.getColumn()) + Math.abs(p.getRow() - b.getRow());
					minPosition = i;
				}
				i++;
			}

			ret[j] = minPosition;
			j++;
		}
		return ret;
	}
}

