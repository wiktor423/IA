package IA;

import java.util.ArrayList;

public class parada{
    private String nombre;
    private double longitud, latitud;
    private ArrayList<aristas> aristasadyacentes;
    private String linea;

    public parada(String nombre, double latitud, double longitud, String linea){
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.linea = linea;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getLinea(){
        return this.linea;
    }

    public double getLatitud(){
        return this.latitud;
    }
    
    public double getLongitud(){
        return this.longitud;
    }
    
    public ArrayList<aristas> getAristas(){
        return aristasadyacentes;
    }

    public void addarista(aristas x){
        aristas.add(x);
    }

    public void addaristas(ArrayList<aristas> x){
        for(aristas aux : x){
            aristas.add(aux);
        }
    }




















}