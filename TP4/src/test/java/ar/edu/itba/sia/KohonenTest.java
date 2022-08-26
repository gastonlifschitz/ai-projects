package ar.edu.itba.sia;

import org.junit.Test;

import static org.junit.Assert.*;

public class KohonenTest {
	
	Kohonen kohonen = new Kohonen();
	
	/*
	
	The basic idea behind Kohonen Test is to VISUALIZE what is going on on the default case.
	
	inputs:
	
	0.1		0.2		0.13
	0.5		0.3		0.7
	
	weights:
	
	5	14	2
	13	7	12
	20	3	30
	
	DIM = inputs.length
	
	W = weights.length
	
	R = random
	
	dR,W = sqrt(sum(i,DIM)([ inputs[R][i] - weights[i][W] ]) )
	
	
	*/
	
	@Test
	public void testgetRandomNumberInRange() {
		int r = kohonen.getRandomNumberInRange(0,5 - 1);
		System.out.println(r);
		assertTrue(r < 5);
		assertTrue(r >= 0);
	}
	
	@Test
	public void Stest() {
		double d = kohonen.S(0,1);
		System.out.println(d);
	}
	
	@Test
	public void sigmaTest() {
		double last = Double.MAX_VALUE;
		for(int i = 0 ; i < 10 ; i++) {
			double d = kohonen.sigma(i);
			//System.out.println(d);
			assertTrue(last > d);
			last = d;
		}
	}
	
	@Test
	public void nTest() {
		double last  = Double.MAX_VALUE;
		for(int i = 0 ; i < 10 ; i++) {
			double d = kohonen.n(i);
			//System.out.println(d);
			assertTrue(last > d);
			last = d;
		}
	}
	
}
