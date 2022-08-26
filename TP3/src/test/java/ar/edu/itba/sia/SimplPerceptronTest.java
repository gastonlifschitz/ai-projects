package ar.edu.itba.sia;

import org.junit.Before;
import org.junit.Test;

public class SimplPerceptronTest {
	SimplePerceptron sp = new SimplePerceptron();
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void predictTest() {
		double[] a = {1.0,2.0,3.0,9.0};
		double[] b = {4.0,5.0,6.0};
		double x = sp.predict(a,b);
	}
}
