import java.io.*;
import java.util.Properties;

public class FileScan {
	private Heuristic heuristic;
	private Algorithm algorithm;
	private String path;
	private boolean deadlock;
	private int maxDepth;

	public char[][] scan(String path) throws IOException {
		  File f= new File(path);//Creation of File Descriptor for input file
		  int i = 0;
		  int j = 0;
	      FileReader fr= new FileReader(f);//Creation of File Reader object
	      BufferedReader br= new BufferedReader(fr);//Creation of BufferedReader object
	      int c = 0; 
	      String s="";
	      int chars_in_line = 0;
	      int max = 0;
	      int lines = 0;
	      while((c = br.read()) != -1)//Read char by Char
	      {		
	    	  	
	    	  	if(c!='\n') {
	    	  		char character = (char) c;//Converting integer to char
	    	  		s = s + character;
	    	  		chars_in_line++;
	    	  		//System.out.print(character);
	    	  	}else {
	    	  		i++;
	    	  		s = "";
	    	  		//System.out.println(chars_in_line);
	    	  		if(chars_in_line > max ) {
	    	  			max = chars_in_line;
	    	  		}
	    	  		lines++;
	    	  		chars_in_line = 0;
	    	  	}
	      }
	      //System.out.println(chars_in_line);
	      //System.out.println("HEIGHT: " + lines);
	      //System.out.println("WIDTH:  " + max);
	      char[][] ret = new char[lines + 1][max  + 1];
	      
	      FileReader fr_aux= new FileReader(f);//Creation of File Reader object
	      BufferedReader br_aux= new BufferedReader(fr_aux);//Creation of BufferedReader object
	      c = 0;
	      i = 0;
	      j = 0;
	      while((c = br_aux.read()) != -1)//Read char by Char
	      {			
	    	  	if(c!='\n') {
	    	  		char character = (char) c;//Converting integer to char
	    	  		ret[i][j] = character;
	    	  		//System.out.print(ret[i][j]);
	    	  		//System.out.print(j);
	    	  		j++;
	    	  	}else {
	    	  		//System.out.println();
	    	  		ret[i][j] = '\n' ;
	    	  		i++;
	    	  		j=0;
	    	  	}
	      }
	      
	      fr.close();
	      fr_aux.close();
	      
	      return ret;
	}

	public void getProperties(){
		InputStream inputStream;
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			String h = prop.getProperty("heuristic");
			String a = prop.getProperty("algorithm");
			String d = prop.getProperty("deadlock");
			this.maxDepth = Integer.parseInt(prop.getProperty("maxDepth"));
			this.path = prop.getProperty("path");
			if(d.equals("TRUE")) {
				this.deadlock = true;
			}
			else {
				this.deadlock = false;
			}
			switch (h){
				case "0":
					heuristic = Heuristic.ADMISSIBLE_1;
					break;
				case  "1":
					heuristic = Heuristic.ADMISSIBLE_2;
					break;
				case "2":
					heuristic = Heuristic.ADMISSIBLE_3;
					break;
				case "3":
					heuristic = Heuristic.NON_ADMISSIBLE;
					break;
				default:
					heuristic = Heuristic.NONE;

			}
			switch (a){
				case "DFS":
					algorithm = Algorithm.DFS;
					heuristic = Heuristic.NONE;
					break;
				case "BFS":
					algorithm = Algorithm.BFS;
					heuristic = Heuristic.NONE;
					break;
				case "IDDFS":
					algorithm = Algorithm.IDDFS;
					heuristic = Heuristic.NONE;
					break;
				case "GLOBAL_GREEDY":
					algorithm = Algorithm.GLOBAL_GREEDY;
					break;
				case "A":
					algorithm = Algorithm.A;
					break;
				case "IDA":
					algorithm = Algorithm.IDA;
					break;
				case "CUSTOM_IDA":
					algorithm = Algorithm.CUSTOM_IDA;
					break;
				default:
					algorithm = Algorithm.OTHER;
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	public boolean isDeadlock() {
		return deadlock;
	}
	
	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public Heuristic getHeuristic() {
		return heuristic;
	}

	public String getPath() {
		return path;
	}

	public int getMaxDepth() {
		return maxDepth;
	}
}
