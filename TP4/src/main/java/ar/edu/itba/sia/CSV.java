package ar.edu.itba.sia;

public class CSV {
	/*
	
	"Austria"
	"Belgium"
	"Bulgaria"
	"Croatia"
	"Czech Republic"
	"Denmark"
	"Estonia"
	"Finland"
	"Germany"
	"Greece"
	"Hungary"
	"Iceland"
	"Ireland"
	"Italy"
	"Latvia"
	"Lithuania"
	"Luxembourg"
	"Netherlands"
	"Norway"
	"Poland"
	"Portugal"
	"Slovakia"
	"Slovenia"
	"Spain"
	"Sweden"
	"Switzerland"
	"Ukraine"
	"United Kingdom"
	
	{"Austria","Belgium","Bulgaria","Croatia","Czech Republic","Denmark","Estonia","Finland","Germany","Greece","Hungary","Iceland","Ireland","Italy","Latvia","Lithuania","Luxembourg","Netherlands","Norway","Poland","Portugal","Slovakia","Slovenia","Spain","Sweden","Switzerland","Ukraine","United Kingdom"}
	
	
	
	"Country","Area","GDP","Inflation","Life.expect","Military","Pop.growth","Unemployment"
	
	"Austria",83871,41600,3.5,79.91,0.8,0.03,4.2
	
	83871,41600,3.5,79.91,0.8,0.03,4.2
	
	"Belgium",30528,37800,3.5,79.65,1.3,0.06,7.2
	
	30528,37800,3.5,79.65,1.3,0.06,7.2
	
	"Bulgaria",110879,13800,4.2,73.84,2.6,-0.8,9.6
	
	110879,13800,4.2,73.84,2.6,-0.8,9.6
	
	"Croatia",56594,18000,2.3,75.99,2.39,-0.09,17.7
	
	56594,18000,2.3,75.99,2.39,-0.09,17.7
	
	"Czech Republic",78867,27100,1.9,77.38,1.15,-0.13,8.5
	
	78867,27100,1.9,77.38,1.15,-0.13,8.5
	
	"Denmark",43094,37000,2.8,78.78,1.3,0.24,6.1
	
	43094,37000,2.8,78.78,1.3,0.24,6.1
	
	"Estonia",45228,20400,5,73.58,2,-0.65,12.5
	
	"Finland",338145,36000,3.3,79.41,2,0.07,7.8
	
	338145,36000,3.3,79.41,2,0.07,7.8
	
	"Germany",357022,38100,2.5,80.19,1.5,-0.2,6
	
	357022,38100,2.5,80.19,1.5,-0.2,6
	
	"Greece",131957,26300,3.3,80.05,4.3,0.06,17.4
	
	131957,26300,3.3,80.05,4.3,0.06,17.4
	
	"Hungary",93028,19600,3.9,75.02,1.75,-0.18,10.9
	
	93028,19600,3.9,75.02,1.75,-0.18,10.9
	
	"Iceland",103000,38100,4,81,0,0.67,7.4
	
	103000,38100,4,81,0,0.67,7.4
	
	"Ireland",70273,40800,2.6,80.32,0.9,1.11,14.4
	
	70273,40800,2.6,80.32,0.9,1.11,14.4
	
	"Italy",301340,30500,2.9,81.86,1.8,0.38,8.4
	
	301340,30500,2.9,81.86,1.8,0.38,8.4
	
	
	"Latvia",64589,16800,4.4,72.93,1.1,-0.6,12.8
	
	64589,16800,4.4,72.93,1.1,-0.6,12.8
	
	"Lithuania",65300,19100,4.1,75.55,0.9,-0.28,15.4
	
	65300,19100,4.1,75.55,0.9,-0.28,15.4
	
	"Luxembourg",2586,80600,3.4,79.75,0.9,1.14,5.7
	
	2586,80600,3.4,79.75,0.9,1.14,5.7
	
	"Netherlands",41543,42000,2.3,80.91,1.6,0.45,4.4
	
	41543,42000,2.3,80.91,1.6,0.45,4.4
	
	"Norway",323802,53400,1.3,80.32,1.9,0.33,3.3
	
	323802,53400,1.3,80.32,1.9,0.33,3.3
	
	"Poland",312685,20200,4.2,76.25,1.9,-0.08,12.4
	
	312685,20200,4.2,76.25,1.9,-0.08,12.4
	
	"Portugal",92090,23400,3.7,78.7,2.3,0.18,12.7
	
	92090,23400,3.7,78.7,2.3,0.18,12.7
	
	"Slovakia",49035,23300,3.9,76.03,1.08,0.1,13.2
	
	49035,23300,3.9,76.03,1.08,0.1,13.2
	
	"Slovenia",20273,28800,1.8,77.48,1.7,-0.19,11.8
	
	20273,28800,1.8,77.48,1.7,-0.19,11.8
	
	"Spain",505370,30500,3.1,81.27,1.2,0.65,21.7
	
	505370,30500,3.1,81.27,1.2,0.65,21.7
	
	"Sweden",450295,40700,3,81.18,1.5,0.17,7.5
	
	450295,40700,3,81.18,1.5,0.17,7.5
	
	"Switzerland",41277,44500,0.2,81.17,1,0.92,2.8
	
	41277,44500,0.2,81.17,1,0.92,2.8
	
	"Ukraine",603550,7200,8,68.74,1.4,-0.63,7.9
	
	603550,7200,8,68.74,1.4,-0.63,7.9
	
	"United Kingdom",243610,36500,4.5,80.17,2.7,0.55,8.1
	
	243610,36500,4.5,80.17,2.7,0.55,8.1
	 
	 */
	
	double [][] europe = {
			{83871.0,41600.0,3.5,79.91,0.8,0.03,4.2},
			{30528.0,37800.0,3.5,79.65,1.3,0.06,7.2},
			{110879.0,13800.0,4.2,73.84,2.6,-0.8,9.6},
			{56594.0,18000.0,2.3,75.99,2.39,-0.09,17.7},
			{78867.0,27100.0,1.9,77.38,1.15,-0.13,8.5},
			{43094.0,37000.0,2.8,78.78,1.3,0.24,6.1},
			{45228.0,20400.0,5.0,73.58,2.0,-0.65,12.5},
			{338145.0,36000.0,3.3,79.41,2.0,0.07,7.8},
			{357022.0,38100.0,2.5,80.19,1.5,-0.2,6.0},
			{131957.0,26300.0,3.3,80.05,4.3,0.06,17.4},
			{93028.0,19600.0,3.9,75.02,1.75,-0.18,10.9},
			{103000.0,38100.0,4,81,0,0.67,7.4},
			{70273.0,40800.0,2.6,80.32,0.9,1.11,14.4},
			{301340.0,30500.0,2.9,81.86,1.8,0.38,8.4},
			{64589.0,16800.0,4.4,72.93,1.1,-0.6,12.8},
			{65300.0,19100.0,4.1,75.55,0.9,-0.28,15.4},
			{2586.0,80600.0,3.4,79.75,0.9,1.14,5.7},
			{41543.0,42000.0,2.3,80.91,1.6,0.45,4.4},
			{323802.0,53400.0,1.3,80.32,1.9,0.33,3.3},
			{312685.0,20200.0,4.2,76.25,1.9,-0.08,12.4},
			{92090.0,23400.0,3.7,78.7,2.3,0.18,12.7},
			{49035.0,23300.0,3.9,76.03,1.08,0.1,13.2},
			{20273.0,28800.0,1.8,77.48,1.7,-0.19,11.8},
			{505370.0,30500.0,3.1,81.27,1.2,0.65,21.7},
			{450295.0,40700.0,3,81.18,1.5,0.17,7.5},
			{41277.0,44500.0,0.2,81.17,1,0.92,2.8},
			{603550.0,7200.0,8,68.74,1.4,-0.63,7.9},
			{243610.0,36500.0,4.5,80.17,2.7,0.55,8.1},
	};
	
	int N = europe.length;//28
	int M = europe[0].length;//7
	
	double[][] aux = new double[M][N];
	
	double[][] europeStandarized = new double[N][M];
	
	public void standarize() {
		
		for(int i = 0 ; i < M ; i++) {
			for(int j = 0 ; j < N ; j++) {
				aux[i][j] = europe[j][i];
			}
		}
		
		for(int i = 0 ; i < M ; i++) {
			double max = max(aux[i]); 
			double min = min(aux[i]);
			for(int j = 0 ; j < N ; j++) {
				aux[i][j] = (aux[i][j] - min) / (max - min);
			}
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				europeStandarized[i][j] = aux[j][i];
			}
		}
		
	}
	
	public void standarize2() {
		for(int i = 0 ; i < M ; i++) {
			for(int j = 0 ; j < N ; j++) {
				aux[i][j] = europe[j][i];
			}
		}
		
		for(int i = 0 ; i < M ; i++) {
			double mean   = mean(aux[i]); 
			double stddev = stddev(aux[i]);
			for(int j = 0 ; j < N ; j++) {
				aux[i][j] = (aux[i][j] - mean) /stddev;
			}
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				europeStandarized[i][j] = aux[j][i];
			}
		}
	}
	
	public double mean(double x[]) {
		double sum = 0;
		for(int i = 0 ; i < x.length ; i++){
			sum += x[i];
		}
		return sum/x.length;
	}
	
	public double stddev(double x[]) {
		double sum = 0;
		double u = mean(x);
		for(int i = 0 ; i < x.length ; i++) {
			sum += Math.pow((x[i]-u),2);
		}
		double ret = Math.sqrt(sum/x.length);
		return ret;
	}
	
	public double max(double x[]) {
		double max = 0;
		for(int i = 0 ; i < x.length ; i++) {
			if(x[i] > max) {
				max = x[i];
			}
		}
		return max;
	}
	
	public double min(double x[]) {
		double min = Double.MAX_VALUE;
		for(int i = 0 ; i < x.length ; i++) {
			if(x[i] < min) {
				min = x[i];
			}
		}
		return min;
	}
		
	public String print() {
		StringBuilder ret = new StringBuilder();
		
		for(int i = 0 ; i < europe.length ; i++) {
			for(int j = 0 ; j < europe[i].length ; j++) {
				ret.append(europe[i][j]);
				ret.append("\t");
			}
			ret.append("\n");
		}
		
		return ret.toString();
	}
	
	public String printStandarized() {
		StringBuilder ret = new StringBuilder();
		
		for(int i = 0 ; i < europeStandarized.length ; i++) {
			for(int j = 0 ; j < europeStandarized[i].length ; j++) {
				ret.append(String.format("%.2f",europeStandarized[i][j]));
				ret.append("\t");
			}
			ret.append("\n");
		}
		
		return ret.toString();
	}
	
}
