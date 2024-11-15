package IA
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LineaParser {
    private Map<String, parada> paradasMap = new HashMap<>();

    // Método para cargar las estaciones y conexiones desde el JSON
    public void cargarDatos(LineaData data) {
        // Crear paradas
        for (String linea : data.linea.keySet()) {
            for (String nombreParada : data.linea.get(linea).keySet()) {
                Estacion estacionData = data.linea.get(linea).get(nombreParada);
                parada parada = new parada(nombreParada, estacionData.latitud, estacionData.longitud, linea);
                paradasMap.put(nombreParada, parada);
            }
        }

        // Crear aristas (conexiones entre paradas)
        for (String linea : data.linea.keySet()) {
            for (String nombreParada : data.linea.get(linea).keySet()) {
                Estacion estacionData = data.linea.get(linea).get(nombreParada);
                parada paradaInicio = paradasMap.get(nombreParada);

                for (String neighborName : estacionData.neighbors.keySet()) {
                    Neighbor neighborData = estacionData.neighbors.get(neighborName);
                    parada paradaFin = paradasMap.get(neighborName);

                    // Crear arista y añadirla a la parada
                    aristas arista = new aristas(paradaInicio, paradaFin, neighborData.distance);
                    paradaInicio.addarista(arista);
                }
            }
        }
    }
}
