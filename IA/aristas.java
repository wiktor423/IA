package IA;

public class aristas{
    parada inicio;
    parada fin;
    double tiempo;

    public aristas(parada inicio, parada fin){
        this.inicio = inicio;
        this.fin = fin;
        tiempo = tiempo();

    }

    public parada getinicio(){
        return this.inicio;
    }
    
    public parada getfin(){
        return this.fin;
    }

    public double tiempo(){
        linea res = null;
        return res.getTiempoDeLinea(this.fin);
    } 
}