package ar.edu.itba.paw.sia;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

import ar.edu.itba.paw.sia.corte.CantidadDeGeneraciones;
import ar.edu.itba.paw.sia.corte.CriterioDeCorte;
import ar.edu.itba.paw.sia.corte.Tiempo;
import ar.edu.itba.paw.sia.implementation.Fill;
import ar.edu.itba.paw.sia.implementation.FillAll;
import ar.edu.itba.paw.sia.implementation.FillParent;
import ar.edu.itba.paw.sia.seleccion.Boltzmann;
import ar.edu.itba.paw.sia.seleccion.Elite;
import ar.edu.itba.paw.sia.seleccion.Ranking;
import ar.edu.itba.paw.sia.seleccion.Ruleta;
import ar.edu.itba.paw.sia.seleccion.Seleccion;
import ar.edu.itba.paw.sia.seleccion.TorneosDeterministicos;
import ar.edu.itba.paw.sia.seleccion.TorneosProbabilisticos;
import ar.edu.itba.paw.sia.seleccion.Universal;

public class Motor {
	
	int K = 10;
	private TSVReader tsvReader;
	Cruce cruce = new CruceDeUnPunto();
	Mutacion mutacion = new MutacionGen(tsvReader);
	double probabilidad_mutacion = 0.5;
	Seleccion seleccion_de_padres = new Elite(K);
	Seleccion reemplazo_de_individuos = new Elite(K);
	
	Seleccion metodo1 = new Elite(K);
	Seleccion metodo2 = new Ruleta(K);
	double a = 0.6;
	int poblacionSize;
	Seleccion metodo3 = new Universal(K);
	Seleccion metodo4 = new Ranking(K);
	double b = 0.8;
	//String implementacion;
	CriterioDeCorte criterioDeCorte;
	Fill implementacion;
	
	double valor = 10;

	PersonajeType tipoPersonaje;
	
	public Motor(TSVReader tsvReader) {
		this.tsvReader = tsvReader;
	}
	
	public void getPropValues() {
		
		Properties prop = new Properties();
		
		String propFileName = "config.properties";
				 
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		try {
			prop.load(inputStream);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String K = prop.getProperty("K");
		String cruce = prop.getProperty("CRUCE");
		String mutacion = prop.getProperty("MUTACION");
		String probabilidad_mutacion = prop.getProperty("PROBABILIDAD_MUTACION");
		String seleccion_de_padres = prop.getProperty("SELECCION_DE_PADRES");
		String metodo1 = prop.getProperty("METODO1");
		String metodo2 = prop.getProperty("METODO2");
		String a = prop.getProperty("A");
		String reemplazo_de_individuos = prop.getProperty("REEMPLAZO_DE_INDIVIDUOS");
		String metodo3 = prop.getProperty("METODO3");
		String metodo4 = prop.getProperty("METODO4");
		String b = prop.getProperty("B");
		String implementacion = prop.getProperty("IMPLEMENTACION");
		String criterioDeCorte = prop.getProperty("CRITERIO_DE_CORTE");
		String valorCriterioDeCorte = prop.getProperty("VALOR_CRITERIO_DE_CORTE");
		//String valor = prop.getProperty("VALOR");
		String poblacionSize = prop.getProperty("SIZE");
		tipoPersonaje = parseTipoPersonaje(prop.getProperty("TIPO_PERSONAJE"));
		
		this.K = Integer.parseInt(K);
		this.cruce = parseCruce(cruce);
		this.mutacion = parseMutacion(mutacion);
		this.probabilidad_mutacion = Double.parseDouble(probabilidad_mutacion);
		this.metodo1 = parseSeleccion(metodo1);
		this.metodo2 = parseSeleccion(metodo2);
		this.a = Double.parseDouble(a);
		this.metodo3 = parseSeleccion(metodo3);
		this.metodo4 = parseSeleccion(metodo4);
		this.b = Double.parseDouble(b);
		this.poblacionSize = Integer.parseInt(poblacionSize);

		if(esImpar(this.K)){
			if(this.poblacionSize >= (this.K + 1)){
				this.K++;
			}else{
				this.K = this.poblacionSize;
			}
		}

		try {

			parseImplementacion(implementacion);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

		
		switch(criterioDeCorte) {
		case "TIEMPO":
			this.criterioDeCorte = new Tiempo(Integer.parseInt(valorCriterioDeCorte));
			break;
		case "CANTIDAD_GENERACIONES":
			this.criterioDeCorte = new CantidadDeGeneraciones(Integer.parseInt(valorCriterioDeCorte));
			break;
		default:
			System.out.println("CRITERIO DE CORTE INVALID0");
			break;
		}
	}
	
	private Cruce parseCruce(String cruce) {
		switch(cruce) {
		case "DE_UN_PUNTO":
			return new CruceDeUnPunto();
		case "DE_DOS_PUNTOS":
			return new CruceDeDosPuntos();
		case "ANULAR":
			return new CruceAnular();
		case "UNIFORME":
			return new CruceUniforme();
		default:
			return null;
		}
	}

	private PersonajeType parseTipoPersonaje(String tipo){
		switch (tipo){
			case "ARQUERO":
				return PersonajeType.ARQUERO;
			case "DEFENSOR":
				return PersonajeType.DEFENSOR;
			case "ESPIA":
				return PersonajeType.ESPIA;
			case "GUERRERO":
				return PersonajeType.GUERRERO;
			default:
				return PersonajeType.GUERRERO;

		}
	}
	
	private Mutacion parseMutacion(String mutacion) {
		switch(mutacion) {
		case "GEN":
			return new MutacionGen(tsvReader);
		case "MULTIGEN_LIMITADA":
			return new MutacionMultigenLimitada(tsvReader);
		case "MULTIGEN_UNIFORME":
			return new MutacionMultigenUniforme(tsvReader);
		case "COMPLETA":
			return new MutacionCompleta(tsvReader);
		default:
			return new MutacionGen(tsvReader);
		}
	}
	
	private Seleccion parseSeleccion(String seleccion) {
		switch(seleccion) {
		case "ELITE":
			return new Elite(K);
		case "RULETA":
			return new Ruleta(K);
		case "UNIVERSAL":
			return new Universal(K);
		case "BOLTZMANN":
			return new Boltzmann(K);
		case "TORNEO_A":
			return new TorneosDeterministicos(K);
		case "TORNEO_B":
			return new TorneosProbabilisticos(K);
		case "RANKING":
			return new Ranking(K);
		default:
			return new Elite(K);
		}
	}
	private void parseImplementacion(String imp) throws Exception{
		switch (imp){
			case "FILL-ALL":
				implementacion = new FillAll(poblacionSize,metodo3,metodo4,K,b);
				break;
			case "FILL-PARENT":
				implementacion= new FillParent(this.poblacionSize,metodo3,metodo4,K,b);
				break;
			default:
				throw new Exception("Invalid type of Fill, select either FILL-PARENT or FILL-ALL");
		}
	}
	public boolean esImpar(int iNumero) {
		if (iNumero%2!=0)
			return true;
		else
			return false;
	}
}

