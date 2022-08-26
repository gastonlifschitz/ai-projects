import java.util.Objects;

public class Position {
    private int i;// [0-height] represents the column
    private int j;// [0-width ] represents the row
    
    
    public Position(int i, int j) {
        this.i=i;
        this.j=j;
    }

    public Position() {
	    i =0;
	    j =0;
	}
    
	//Getters
    public int getRow(){
        return j;
    }
    public int getColumn(){
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public void setJ(int j) {
        this.j = j;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(i,j);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Position))
            return false;
        Position b = (Position) obj;
        if(b.i != this.i)
            return false;
        if (b.j != this.j)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
    	return String.format("(%d %d)",i,j);
    }

   
}