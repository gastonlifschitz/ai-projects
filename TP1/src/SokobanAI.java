import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;
import java.util.stream.Collectors;

public class SokobanAI {
    public int MAX_DEPTH = 300;
    private Node parent;
    private Heuristic heuristic;
    private Algorithm algorithm;
    private int expandedNodes = 0;
    private int frontierNodes = 1;
    private int maxDepth = 0 /* MAX_DEPTH*/;
    private boolean customIDA = false;
    private boolean deadlock = false;

    public SokobanAI(Board initialBoard, Algorithm algorithm, Heuristic heuristic, boolean deadlock, int max){
        this.parent = new Node(initialBoard, 0);
        this.algorithm = algorithm;
        this.heuristic = heuristic;
        this.deadlock = deadlock;
        System.out.println("Heuristic: " + heuristic);
        System.out.println("Algorithm: " + algorithm);
        if(deadlock && heuristic.equals(Heuristic.NON_ADMISSIBLE))
            deadlock = false;
        System.out.println("Deadlock: " + (deadlock ? "on": "off"));
        this.MAX_DEPTH = max;
        this.maxDepth = max;
    }
    
    public void run(){
        long startTime = System.currentTimeMillis();

        switch (algorithm){
            case DFS:
                SokobanDFS();
                break;
            case BFS:
                SokobanBFS();
                break;
            case IDDFS:
                SokobanIDDFS();
                break;
            case GLOBAL_GREEDY:
                globalGreedy();
                break;
            case A:
                a();
                break;
            case CUSTOM_IDA:
                customIDA = true;
            case IDA:
                IDA();
                break;
            default:
                System.out.println("Something happened, check your config properties file and make sure everything is fine.");
                return;
        }

        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println("Time Elapsed: " + timeElapsed + " milliseconds");
    }

    public void SokobanDFS (){
        List<Board> repeatedBoards = new ArrayList<>();
        int currentDepth = 0;
        Stack<Node> solution = new Stack<>();
        if(!SokobanDFSRec(parent,repeatedBoards,currentDepth,solution)){
            System.out.println("There's no solution to current map");
            return;
        }
        solution.add(parent);
        int depth = solution.size();
        int i = 0;
        while(i < depth){
            Node node = solution.pop();
            node.getBoard().printCells();
            System.out.println("-----------------");
            i++;
        }
        System.out.println("Depth = Cost = " + depth);
        System.out.println("Expanded nodes = " + expandedNodes);
        System.out.println("Frontier nodes = " + frontierNodes);

    }

    private boolean SokobanDFSRec(Node current, List<Board> repeatedBoards, int currentDepth, Stack<Node> solution){
        if(currentDepth>maxDepth){
            return false;
        }
        if(current.getBoard().isSolution()){
            return true;
        }
        repeatedBoards.add(current.getBoard());
        List<Board> possibleMoves = current.getBoard().possibleMoves();
        possibleMoves.removeAll(repeatedBoards);
        Node aux;
        if(possibleMoves.size()>0){
            frontierNodes--;
            frontierNodes += possibleMoves.size();
        }
        for(Board board : possibleMoves){
            aux = new Node(board, currentDepth+1);
            current.addChild(aux);
            expandedNodes++;
            if(SokobanDFSRec(aux,repeatedBoards,currentDepth+1,solution)){
                solution.push(aux);
                return true;
            }
        }
        return false;
    }
    public void SokobanBFS(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(parent);
        List<Board> repeatedBoards = new ArrayList<>();
        Node current;
        Node aux;
        while (!queue.isEmpty()){
            current = queue.remove();
            List<Board> possibleMoves = current.getBoard().possibleMoves();
            repeatedBoards.add(current.getBoard());
            possibleMoves.removeAll(repeatedBoards);
            for (Board b : possibleMoves){
                aux = new Node(b,current.getLevel()+1);
                current.addChild(aux);
                expandedNodes++;
                if(aux.getBoard().isSolution()){
                    printSolution(aux);
                    System.out.println("Frontier nodes = " + queue.size());
                    return;
                }else{
                    queue.add(aux);
                }
            }
        }
        System.out.println("There's no solution to current map");
    }

    public void SokobanIDDFS(){
        Node current = parent;
        List<Board> repeatedBords = new ArrayList<>();
        Stack<Node> solution = new Stack<>();
        for (int i = 5; i < MAX_DEPTH; i=i+2){
            maxDepth = i;
            if(SokobanDFSRec(current,repeatedBords, 0,solution)) {
                solution.push(parent);
                int depth = solution.size();
                for (Node node : solution) {
                    node.getBoard().printCells();
                    System.out.println("-----------------");
                }
                System.out.println("Depth = Cost = " + depth);
                System.out.println("Frontier nodes = " + frontierNodes);
                System.out.println("Expanded nodes = " + expandedNodes);
                return;
            }
            current = parent;
            frontierNodes = 0;
            expandedNodes = 0;
            repeatedBords.clear();
            solution.clear();
        }
        System.out.println("There's no solution to current map");
    }

    private void printSolution(Node node){
        Stack<Board> stack = new Stack();
        while(node != null){
            stack.add(node.getBoard());
            node = node.getParent();
        }
        int depth = stack.size();
        int i = 0;
        while(i < depth) {
            Board b = stack.pop();
            b.printCells();
            System.out.println("-----------------");
            i++;
        }
        System.out.println("Depth = Cost = " + depth);
        System.out.println("Expanded Nodes = " + expandedNodes);
    }

    public void globalGreedy(){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(parent);
        List<Board> repeatedBoards = new ArrayList<>();
        Node current;
        Node aux;
        while (!queue.isEmpty()){
            current = queue.remove();
            List<Board> possibleMoves = current.getBoard().possibleMoves();
            repeatedBoards.add(current.getBoard());
            possibleMoves.removeAll(repeatedBoards);
            for (Board b : possibleMoves){
                aux = new Node(b,current.getLevel()+1);
                aux.calculateHeuristic(heuristic, deadlock);
                current.addChild(aux);
                expandedNodes++;
                if(aux.getBoard().isSolution()){
                    printSolution(aux);
                    System.out.println("Frontier nodes = " + queue.size());
                    return;
                }else{
                    queue.add(aux);
                }
            }
        }
        System.out.println("There's no solution to current map");
    }

    public void a(){
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(parent);
        List<Board> repeatedBoards = new ArrayList<>();
        Node current;
        Node aux;
        while (!queue.isEmpty()){
            current = queue.remove();
            List<Board> possibleMoves = current.getBoard().possibleMoves();
            repeatedBoards.add(current.getBoard());
            possibleMoves.removeAll(repeatedBoards);
            for (Board b : possibleMoves){
                aux = new Node(b,current.getLevel()+1);
                aux.calculateHeuristic(heuristic, deadlock);
                aux.incrementHeuristic(current.getLevel()+1);
                current.addChild(aux);
                expandedNodes++;
                if(aux.getBoard().isSolution()){
                    printSolution(aux);
                    System.out.println("Frontier nodes = " + queue.size());
                    return;
                }else{
                    queue.add(aux);
                }
            }
        }
        System.out.println("There's no solution to current map");
    }

    private void IDA() {
        Stack<Node> solution = new Stack<>();
        List<Board> path = new ArrayList<>();
        parent.calculateHeuristic(heuristic, deadlock);
        int threshold = parent.getHeuristic();

        while(true){
            int temp = DFS_IDA(parent, 0, threshold, solution, path);
            if(temp == -1){ //Found solution
                solution.push(parent);
                int depth = solution.size();
                Node n = solution.pop();
                int size = solution.size();
                int i = 0;
                while(i < size) {
                    n.getBoard().printCells();
                    System.out.println("-----------------");
                    n = solution.pop();
                    i++;
                }
                System.out.println("Depth = Cost = " + depth);
                System.out.println("Expanded nodes = " + expandedNodes);
                System.out.println("Frontier nodes = " + frontierNodes);
                return;
            }
            path.clear();
            path.add(parent.getBoard());
            frontierNodes = 0;
            expandedNodes = 0;
            solution.clear();
            threshold = temp;
        }

    }

    private int DFS_IDA(Node current, int currDepth, int threshold, Stack<Node> solution, List<Board> path) {
        current.calculateHeuristic(heuristic, deadlock);
        int func = currDepth + current.getHeuristic();
        if(func > threshold)
            return func;
        if(current.getBoard().isSolution()) {
            solution.push(current);
            return -1; //Represents found
        }
        int minVal = 999999;
        List<Board> possibleMoves = current.getBoard().possibleMoves();
        if(possibleMoves.size()>0){
            frontierNodes--;
            frontierNodes += possibleMoves.size();
        }
        int temp;
        for(Board board : possibleMoves){
            if(!path.contains(board)){
                expandedNodes++;
                Node aux = new Node(board,current.getLevel()+1);
                if (customIDA)
                    path.add(board);
                temp=DFS_IDA(aux, currDepth+1, threshold, solution, path);
                if(temp == -1){
                    solution.push(aux);
                    return -1;
                }
                if(temp<minVal)
                    minVal = temp;
                if (customIDA)
                    path.remove(board);
            }
        }
        return minVal;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

}