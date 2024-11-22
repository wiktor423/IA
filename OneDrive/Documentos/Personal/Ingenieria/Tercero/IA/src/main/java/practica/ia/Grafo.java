package practica.ia;

import java.util.HashMap;
import java.util.Map;

public class Grafo {
    private Map<String, Parada> paradas;

    public Grafo() {
        paradas = new HashMap<>();
    }

    public void agregarParada(Parada parada) {
        paradas.put(parada.getNombre(), parada);
    }

    public void agregarArista(Aristas arista) {
        Parada origen = paradas.get(arista.getinicio());
        Parada destino = paradas.get(arista.getfin());

        if (origen != null && destino != null) {
            origen.addarista(arista);
        }
    }

    public Parada obtenerParada(String nombre) {
        return paradas.get(nombre);
    }

    public Map<String, Parada> getParadas() {
        return paradas;
    }

    public void mostrarGrafo() {
        for (Parada parada : paradas.values()) {
            System.out.println(parada);
        }
    }
}
