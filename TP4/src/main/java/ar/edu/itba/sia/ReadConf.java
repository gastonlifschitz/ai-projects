package ar.edu.itba.sia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConf {
	
	boolean ej1a = false;
	boolean ej1b = false;
	boolean ej2a = false;
	boolean ej2b = false;
	private static String TRUE = "TRUE";
	
	// Get proprety values, make use of maven structure and configuration
	
	public void getPropValues() {
			
			Properties prop = new Properties();
			
			String propFileName = "config.properties";
					 
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			try {
				prop.load(inputStream);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			String ej1a = prop.getProperty("ej1a");
			this.ej1a = ej1a.equals(TRUE);
			String ej1b = prop.getProperty("ej1b");
			this.ej1b = ej1b.equals(TRUE);
			String ej2a = prop.getProperty("ej2a");
			this.ej2a = ej2a.equals(TRUE);
			String ej2b = prop.getProperty("ej2b");
			this.ej2b = ej2b.equals(TRUE);
	} 
	
	@Override
	public String toString() {
		return String.format("%s	%s	%s	%s\n",ej1a,ej1b,ej2a,ej2b);
	}
	
}
