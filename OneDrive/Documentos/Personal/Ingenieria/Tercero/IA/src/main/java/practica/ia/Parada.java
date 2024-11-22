package practica.ia;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parada {
    private String nombre;
    private double longitud, latitud;
    @JsonProperty("aristasadyacentes")
    private List<Aristas> aristasadyacentes;
    private Linea linea;
    private List<Linea> lineas; // Lista de líneas asociadas
    private List<String> transbordos;

    // Constructor con parámetros
    public Parada(String nombre, double latitud, double longitud, Linea linea) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.linea = linea;
        this.lineas = new ArrayList<>(); // Inicializar la lista de líneas
        this.transbordos = new ArrayList<>();
        this.aristasadyacentes = new ArrayList<>();
    }

    // Constructor sin parámetros
    public Parada() {
        this.lineas = new ArrayList<>(); // Inicializar la lista de líneas
        this.transbordos = new ArrayList<>();
        this.aristasadyacentes = new ArrayList<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public Linea getLinea() {
        return this.linea;
    }

    public double getLatitud() {
        return this.latitud;
    }

    public double getLongitud() {
        return this.longitud;
    }

    public List<Aristas> getAristas() {
        return aristasadyacentes;
    }

    public List<String> getTransbordos() {
        return transbordos;
    }

    public void addarista(Aristas x) {
        aristasadyacentes.add(x);
    }

    public void addaristas(List<Aristas> x) {
        for (Aristas aux : x) {
            aristasadyacentes.add(aux);
        }
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public void addLinea(Linea linea) {
        if (!lineas.contains(linea)) { // Evitar duplicados
            lineas.add(linea);
        }
    }

    @Override
    public String toString() {
        return " Nombre= " + nombre + " Latitud= " + latitud +
                " Longuitud = " + longitud + " Linea= " + linea +
                " Aristasadyacentes= " + aristasadyacentes + " transbordos= " + transbordos;
    }
}
