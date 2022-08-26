package ar.edu.itba.paw.sia;

import org.junit.Test;

public class TSVReaderTest {
	
	TSVReader reader = new TSVReader();
	
	//@Test
	public void goToLineTest() {
		reader.goToId(1, "armas.tsv");
	}
	
	//@Test
	public void testSplitLine() {
		String line = "0	2.232888509690683	14.318021119172805	13.44403175778557	12.018078242699934	5.09989740867537";
		reader.splitLine(line);
	}
	
	//@Test
	public void testReadLineByLine() {
		reader.readLineByLine();
	}

	//@Test
	public void testRandomItem(){
		int id = (int) (Math.random() * (999999 - 1)) + 1;
		double [] values =  reader.goToId(id, "armas.tsv");
		System.out.println("Values: "+ values[0] + ", " + values[1]+ ", " + values[2]+ ", " + values[3]+ ", " + values[4]);
	}

}
