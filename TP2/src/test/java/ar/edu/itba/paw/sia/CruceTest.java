package ar.edu.itba.paw.sia;

import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;


public class CruceTest {
	
	private static int p1 = 2;
	private static int p2 = 4;
	private static int l  = 3;
	
	Genotipo a = new Genotipo();
	Genotipo b = new Genotipo();
	
	//TODO: testear el generador y ver que los valores que tira cubren todos los valores que puede tomar.
	
	@Test
	public void test_de_un_punto() {
		CruceDeUnPunto cruce = new CruceDeUnPunto();
		Object [] ac = {0,1,2,3,4,5};
		Object [] bc = {6,7,8,9,10,11};
		
		Object [] ae = {0,1,2,9,10,11};
		Object [] be = {6,7,8,3,4,5};
		
		a.setCromosoma(ac);
		//System.out.println("a = " + a);
		b.setCromosoma(bc);
		//System.out.println("b = " + b);
		
		cruce.de_un_punto(a,b,p1);
		
		//System.out.println("a = " + a);
		//System.out.println("b = " + b);
		
		
		
		Object [] x = a.getCromosoma();
		Object [] y = ae;
		Object [] z = b.getCromosoma();
		Object [] w = be;

		print(x);
		System.out.println("------------");
		print(y);
		System.out.println("------------");
		print(z);
		System.out.println("------------");
		print(w);


		assertArrayEquals(y,x);
		assertArrayEquals(w,z);
	}
	
	@Test
	public void test_de_dos_puntos() {
		
		CruceDeDosPuntos cruce = new CruceDeDosPuntos();
		
		Object [] ac = {0,1,2,3,4,5};
		Object [] bc = {6,7,8,9,10,11};

		Object [] ae = {0,1,2,9,10,5};
		Object [] be = {6,7,8,3,4,11};
		
		a.setCromosoma(ac);
		//System.out.println("a = " + a);
		b.setCromosoma(bc);
		//System.out.println("b = " + b);
		
		cruce.de_dos_puntos(a,b,p1,p2);
		
		//System.out.println("a = " + a);
		//System.out.println("b = " + b);
		
		
		
		Object [] x = a.getCromosoma();
		Object [] y = ae;
		Object [] z = (b.getCromosoma());
		Object [] w = be;
		
		assertArrayEquals((Object[])y,(Object[])x);
		assertArrayEquals((Object[])w,(Object[])z);
	}
	
	@Test
	public void test_anular() {
		
		CruceAnular cruce = new CruceAnular();
		
		Object [] ac = {0,1,2,3,4,5};
		Object [] bc = {6,7,8,9,10,11};

		Object [] ae = {0,1,2,9,10,11};
		Object [] be = {6,7,8,3,4,5};
		
		a.setCromosoma(ac);
		//System.out.println("a = " + a);
		b.setCromosoma(bc);
		//System.out.println("b = " + b);
		
		cruce.anular(a,b,p1,l);
		
		//System.out.println("a = " + a);
		//System.out.println("b = " + b);



		Object [] x = (a.getCromosoma());
		Object [] y = (ae);
		Object [] z = (b.getCromosoma());
		Object [] w = (be);
		
		assertArrayEquals((Object[])y,(Object[])x);
		assertArrayEquals((Object[])w,(Object[])z);
	}
	
	@Test
	public void test_anular_esquina() {
		
		CruceAnular cruce = new CruceAnular();
		
		Object [] ac = {0,1,2,3,4,5};
		Object [] bc = {6,7,8,9,10,11};
		
		Object [] ae = {6,7,2,3,4,11};
		Object [] be = {0,1,8,9,10,5};
		
		a.setCromosoma(ac);
		//System.out.println("a = " + a);
		b.setCromosoma(bc);
		//System.out.println("b = " + b);
		
		cruce.anular(a,b,p2,l);
		
		//System.out.println("a = " + a);
		//System.out.println("b = " + b);
		
		
		
		Object [] x = (a.getCromosoma());
		Object [] y = (ae);
		Object [] z = (b.getCromosoma());
		Object [] w = (be);
		
		assertArrayEquals((Object[])y,(Object[])x);
		assertArrayEquals((Object[])w,(Object[])z);
	}
	
	private Double[] toDoubleArr(double[] arr) {
		int n = arr.length;
		
		Double ret [] = new Double[n];
		
		for(int i = 0 ; i < n ; i++ ) {
			ret[i] = arr[i];
		}
		
		return ret;
	}

	private void print(Object [] objects){
		for(int i = 0;i<objects.length;i++){
			System.out.println(" - "+ (int)objects[i] + " - "  );
		}
	}
	
}

