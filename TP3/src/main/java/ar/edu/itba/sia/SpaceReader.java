package ar.edu.itba.sia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SpaceReader {
	public int FIL = 70;
	public int COL = 5;
	
	public double[][] file;
	
	public SpaceReader() {
		file = new double[FIL][COL];
	}
	
	public SpaceReader(int fil , int col) {
		file = new double[fil][col];
		FIL = fil;
		COL = col;
	}
	
	public double [] splitLine(String line) {
		double ret [];
		String [] tempArray = line.split(" ");
		ret = new double[tempArray.length];
		int i = 0;
		for(String str : tempArray) {
			ret[i] = Double.parseDouble(tempArray[i]);
			i++;
		}
		return ret;
	}
	
	
	// Load from a property file
	public void load(String propFileName) {
		//System.out.println("LOAD " + propFileName);
		double [] aux = null;
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		try {
	        String thisLine;
	        int i = 0;
			while ((thisLine = reader.readLine()) != null) {
					file[i] = splitLine(thisLine);
					//System.out.println(String.format("%d %d %d %d",file[i][0],file[i][1],file[i][2],file[i][3]));
					i++;
			}
			//System.out.println("Loaded " + i + " items");
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		 
	}
	
	public String printFile() {
		StringBuilder sb = new StringBuilder();
		//sb.append("x1	x2	x3	x4	x5\n");
		for(int i = 0 ; i < FIL ; i++) {
			for(int j = 0 ; j < COL ; j++) {
				sb.append(file[i][j]);
				sb.append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
