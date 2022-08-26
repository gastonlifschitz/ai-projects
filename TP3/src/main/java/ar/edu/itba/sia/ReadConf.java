package ar.edu.itba.sia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConf {
	
	boolean ej1a = false;
	int ej1a_epochs = 10;
	double ej1a_learningrate=0.01;
	boolean ej1a_ploteachepoch=true;
	boolean ej1a_stopearly=true;
	
	boolean ej1b = false;
	int ej1b_epochs = 10;
	double ej1b_learningrate=0.01;
	boolean ej1b_ploteachepoch=true;
	boolean ej1b_stopearly=true;
	
	boolean ej2  = false;
	boolean ej3a = false;
	boolean ej3b = false;

	int num_epochs, hidden_perceptrons = 150;
	double learning_rate = 0.3, beta = 0.1, maxError= 0.001;

	private static String TRUE = "TRUE";
	//private static String FALSE = "FALSE";
	
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
			String ej1a_epochs = prop.getProperty("ej1a_epochs");
			this.ej1a_epochs = Integer.parseInt(ej1a_epochs);
			String ej1a_learningrate = prop.getProperty("ej1a_learningrate");
			this.ej1a_learningrate = Double.parseDouble(ej1a_learningrate);
			String ej1a_ploteachepoch = prop.getProperty("ej1a_ploteachepoch");
			this.ej1a_ploteachepoch = ej1a_ploteachepoch.equals(TRUE);
			String ej1a_stopearly = prop.getProperty("ej1a_stopearly");
			this.ej1a_stopearly = ej1a_stopearly.equals(TRUE);
			
			
			String ej1b = prop.getProperty("ej1b");
			this.ej1b = ej1b.equals(TRUE);
			String ej1b_epochs = prop.getProperty("ej1b_epochs");
			this.ej1b_epochs = Integer.parseInt(ej1b_epochs);
			String ej1b_learningrate = prop.getProperty("ej1b_learningrate");
			this.ej1b_learningrate = Double.parseDouble(ej1b_learningrate);
			String ej1b_ploteachepoch = prop.getProperty("ej1b_ploteachepoch");
			this.ej1b_ploteachepoch = ej1b_ploteachepoch.equals(TRUE);
			String ej1b_stopearly = prop.getProperty("ej1b_stopearly");
			this.ej1b_stopearly = ej1b_stopearly.equals(TRUE);
			
			String ej2  = prop.getProperty("ej2");
			this.ej2 = ej2.equals(TRUE);
			String ej3a = prop.getProperty("ej3a");
			this.ej3a = ej3a.equals(TRUE);
			String ej3b = prop.getProperty("ej3b");
			this.ej3b = ej3b.equals(TRUE);

			if(prop.getProperty("num_epochs") != null)
				this.num_epochs = Integer.parseInt(prop.getProperty("num_epochs"));
			if(prop.getProperty("learning_rate") != null)
				this.learning_rate = Double.parseDouble(prop.getProperty("learning_rate"));
			if(prop.getProperty("beta") != null)
				this.beta = Double.parseDouble(prop.getProperty("beta"));

			if(prop.getProperty("hidden_perceptrons") != null)
				this.hidden_perceptrons = Integer.parseInt(prop.getProperty("hidden_perceptrons"));
			if(prop.getProperty("max_error_ej3") != null)
				this.maxError = Double.parseDouble(prop.getProperty("max_error_ej3"));
	} 
	
	@Override
	public String toString() {
		return String.format("%s	%s	%s	%s	%s	\n",ej1a,ej1b,ej2,ej3a,ej3b);
	}
	
}
