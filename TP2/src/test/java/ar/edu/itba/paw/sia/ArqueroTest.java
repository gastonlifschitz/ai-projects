package ar.edu.itba.paw.sia;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArqueroTest {
	
	TSVReader tsvReader = new TSVReader("file.tsv");
	
	Arquero arquero = new Arquero(tsvReader,0,"file.tsv");
	
	@Test
	public void fitnessTest() {
		System.out.println(arquero);
		System.out.println(arquero.fitness());
	}
	
	//@Test
	public void test() {
		fail("Not yet implemented");
	}

}
