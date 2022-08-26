package ar.edu.itba.sia;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TSVReader {
	
	public static int COL = 4;
	public static int FIL = 28;
	
	public double[][] file = new double[FIL][COL];
	
	public TSVReader() {
	
	}

	public double [] splitLine(String line) {
		double ret [] = {0,0,0,0};
		String [] tempArray = line.split("\t");
        double x1 = Double.parseDouble(tempArray[0]);
        double x2 = Double.parseDouble(tempArray[1]);
        double x3 = Double.parseDouble(tempArray[2]);
        double y = Double.parseDouble(tempArray[4]);
        ret[0] = x1;
        ret[1] = x2;
        ret[2] = x3;
        ret[3] = y;
		return ret;
	}
	
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
					//System.out.println(String.format("%.3f %.3f %.3f %.3f",file[i][0],file[i][1],file[i][2],file[i][3]));
					i++;
			}
			//System.out.println("Loaded " + i + " items");
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
		 
	}
	
	public String printFile() {
		StringBuilder sb = new StringBuilder();
		sb.append("x1	x2	x3	y\n");
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
