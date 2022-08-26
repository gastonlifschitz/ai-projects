package ar.edu.itba.paw.sia.corte;

public class CantidadDeGeneraciones implements CriterioDeCorte {
    private int n;
    public CantidadDeGeneraciones(int n){
        this.n=n;
    }
    @Override
    public boolean end(double value) {
        //vale = generacion actual
        if(value==n)
            return true;
        return false;
    }
}
