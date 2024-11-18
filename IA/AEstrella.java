package IA;
import java.lang.Math;

public class AEstrella {


    public funcionF () {

    double costo = funcionG(origen, destino) + funcionH(origen, destino);



    }

    //funcion que calcula la distancia desde un nodo inicial
    public double funcionG (parada origen, parada destino) {

        ArrayList<aristas> vecinos = origen.getAristas();
        double distancia;

        for(aristas vecino: vecinos) {
            if (vecino.fin == destino) {
                return distancia;
            }

        }


    }

    //funcion heuristica

    public double funcionH (parada origen, parada destino) {

        return Math.sqrt(Math.pow(origen.longitud - destino.longitud) + Math.pow(origen.latitud - destino.latitud));
        
    }
}