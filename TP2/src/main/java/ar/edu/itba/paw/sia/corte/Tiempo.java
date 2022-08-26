package ar.edu.itba.paw.sia.corte;

public class Tiempo implements CriterioDeCorte {
    private int minutes;
    public Tiempo(int minutes){
        this.minutes=minutes;
    }

    @Override
    public boolean end(double value) {
        //Pasar value (en milisegundos) a minutos
        int minValue = (int)(value * (1.0/1000.00) *(1.0/60.0));
        if(minValue >= minutes)
            return true;
        return false;
    }
}
