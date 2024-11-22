package practica.ia;

public class Aristas{
    String inicio;
    String fin;
    double peso;

    public Aristas(String inicio, String fin, double peso){
        this.inicio = inicio;
        this.fin = fin;
        this.peso = peso;
    }
    public Aristas(){
    }

    public String getinicio(){
        return this.inicio;
    }
    
    public String getfin(){
        return this.fin;
    }

    public double getPeso() {
        return this.peso;
    }

    @Override
    public String toString(){
        return " Inicio= " + inicio + " Fin= " + fin + 
        " Peso = " + peso;
    }
}