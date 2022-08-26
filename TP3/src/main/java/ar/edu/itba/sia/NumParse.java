package ar.edu.itba.sia;

public class NumParse {
	String str;
	public NumParse(String str) {
		this.str = str;
	}
	
	public int[][][] parse() {
		int[][][] ret =new int[10][35][2];
		int counter = 0;
		int i = 0;
		for(char c : str.toCharArray()) {
			if(c!='\t' && c!='\n') {
				if(counter%35==34) {
					ret[i][counter][1] = isPrime(i);
					counter = 0;
					//System.out.println(String.format("	%d 	%d \n",isPrime(i),i));
					i++;
				}
				else {
					int x = c-'0';
					//System.out.print(x);
					ret[i][counter][0] = x;
					counter++;
				}
			}
		}
		return ret;
	}
	
	private static int isPrime(int n) 
    { 
        // Corner case 
        if (n <= 1) 
            return -1; 
  
        // Check from 2 to n-1 
        for (int i = 2; i < n; i++) 
            if (n % i == 0) 
                return -1; 
  
        return 1; 
    }

	public double[][][] parseDouble() {

		double [][][] ret = new double[10][2][];
		for(int i = 0 ; i < 10 ; i++) {
			ret[i][0] = new double[36];	//34 o 35?
			ret[i][1] = new double[1];
		}

		//double[][][] ret =new double[10][2][];

		int counter = 0;
		int i = 0;
		for(char c : str.toCharArray()) {
			if(c!='\t' && c!='\n') {
				if(counter%35==34) {
					ret[i][1][0] = isPrime(i);
					counter = 0;
					//System.out.println(String.format("	%d 	%d \n",isPrime(i),i));
					ret[i][0][35] = 1.0;
					i++;
				}
				else {
					int x = c-'0';
					//System.out.print(x);
					ret[i][0][counter] = x;
					counter++;
				}
			}
		}
		return ret;
	}
	
}
