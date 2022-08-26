import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
    private Board board         = null;
    private List<Node> children = new ArrayList<>();
    private Node parent         = null;
    private int level           = 0;
    private int heuristic       = 0;
    public Node(Board board, int level) {
        this.board = board;
        this.level = level;
    }
    public Node addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node> getChildren() {
        return children;
    }

    public Board getBoard() {
        return board;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void calculateHeuristic(Heuristic h, boolean deadlock) {
        switch (h){
            case ADMISSIBLE_1:
                heuristic = board.manhattanDistanceBoxObjective(deadlock);
                break;
            case ADMISSIBLE_2:
                heuristic = board.manhattanDistancePlayerBox(deadlock);
                break;
            case NON_ADMISSIBLE:
                heuristic = board.nonAdmissibleHeuristic(deadlock);
                break;
            case ADMISSIBLE_3:
                heuristic = board.admissibleHeuristic3(deadlock);
                break;
            default:
                heuristic = 0;
        }
    }

    public void incrementHeuristic(int cost){
        heuristic += cost;
    }

    @Override
    public int compareTo(Node emp) {
        Integer o1 = this.heuristic, o2 = emp.heuristic;
        return o1.compareTo(o2);
    }

    public int getHeuristic() {
        return heuristic;
    }
}