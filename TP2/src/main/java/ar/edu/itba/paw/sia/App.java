package ar.edu.itba.paw.sia;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println("Hello World!");
        
        TSVReader tsvReader = new TSVReader();
        
        Motor m = new Motor(tsvReader);
        m.getPropValues();
        
        
        
        try{
            AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(m.poblacionSize,m.K,m.tipoPersonaje,m,tsvReader);
            algoritmoGenetico.run();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
