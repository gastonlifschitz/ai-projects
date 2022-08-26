import java.util.Objects;

public class Cell {
	private char id;//  # ' ' . represents if it is a wall , an objective or an empty space
    private char has;// @ ' ' $ represents if it has a sokoban , a box or nothing
    
    public Cell(char id , char has) {
    	this.id = id;
        this.has = has;
    }
    
    public char getId() {
		return id;
	}

	public void setId(char id) {
		this.id = id;
	}

	public char getHas() {
		return has;
	}

	public void setHas(char has) {
		this.has = has;
	}

	public void in(char c) {
    	this.has = c;
    }
    
    public char out() {
    	char ret = this.has;
    	this.has = ' ';
    	return ret;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(this.id , this.has);
    }
    
    @Override
	public boolean equals(Object obj) {
    	if(!(obj instanceof Cell))
    		return false;
    	Cell cell = (Cell) obj;
		return id == cell.getId() && has == cell.getHas() ;
	}
    
    @Override
    public String toString() {
    	if(id=='#') {
    		return "#";
    	}
    	if(id==' ') {
    		if(has==' ') {
    			return " ";
    		}
    		if(has=='@') {
    			return "@";
    		}
    		if(has=='$') {
    			return "$";
    		}
    	}
    	if(id=='.') {
    		if(has==' ') {
    			return ".";
    		}
    		if(has=='@') {
    			return "+";
    		}
    		if(has=='$') {
    			return "*";
    		}
    	}
    	return "-";
    }

	public boolean is_star() {
		return id=='.' && has=='$';
	}
    
}

