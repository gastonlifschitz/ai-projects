import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class Sokoban {
    private Algorithm algorithm;
    public static void main(String args[]){
        FileScan f = new FileScan();
		f.getProperties();

		Heuristic heuristic = f.getHeuristic();
        Algorithm algorithm = f.getAlgorithm();
        boolean deadlock = f.isDeadlock();
        int maxDepth = f.getMaxDepth();
        
        if(algorithm == Algorithm.OTHER){
        	System.out.println("You must indicate a valid algorithm in the config properties file");
        	return;
        }
        try{
            FileScan fs = new FileScan();
            String path = f.getPath();
            char[][] b = fs.scan(path);
            
            int boxes = 0;
            int objectives = 0;
            Position sokoban = new Position();
            List<Position> objectivelist = new ArrayList<Position>();
            List<Position> boxlist = new ArrayList<Position>();
            int i = 0;
            int j = 0;
            
            Cell[][] cells = new Cell[b.length][b[0].length];
            
            
            
            for(i = 0 ; i < b.length; i++) {
            	//System.out.print(board[i]);
            	j = 0;
            	for(char c : b[i]) {
            		if(b[i][j]=='.') {
            			//empty objective
            			objectivelist.add(new Position(i,j));
            			cells[i][j] = new Cell('.',' ');
            		}
            		else if(b[i][j]=='@') {
            			//sokoban
            			sokoban.setI(i);
            			sokoban.setJ(j);
            			cells[i][j] = new Cell(' ','@');
            		}
            		else if(b[i][j]=='$') {
            			//box
            			boxes++;
            			boxlist.add(new Position(i,j));
            			cells[i][j] = new Cell(' ','$');
            		}
            		else if(b[i][j]=='*') {
            			//occupied objective
            			objectives++;
            			objectivelist.add(new Position(i,j));
            			cells[i][j] = new Cell('.','$');
            		}
            		else if(b[i][j]=='+') {
            			//sokoban in an objective
            			sokoban.setI(i);
            			sokoban.setJ(j);
            			objectivelist.add(new Position(i,j));
            			cells[i][j] = new Cell('.','@');
            		}
            		else if(b[i][j]==' ') {
            			cells[i][j] = new Cell(' ',' ');
            		}
            		else if(b[i][j]=='#') {
            			cells[i][j] = new Cell('#',' ');
            		}
            		else {
            			cells[i][j] = new Cell(' ',' ');
            		}
            		j++;
            	}
            	
            }
            
            //a board o a sokoban pasarle deadlock.
            
            Board board = new Board(b,cells,boxes,objectives,sokoban,objectivelist,boxlist);
            SokobanAI ai = new SokobanAI(board,algorithm,heuristic,deadlock,maxDepth);
            //ai.globalGreedy();
			ai.run();

        }catch (FileNotFoundException e) {
            System.out.println("Please, add a config file to the project");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("Please, add a config file to the project");
            e.printStackTrace();
        }

    }    
}