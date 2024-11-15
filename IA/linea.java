package IA;

public class linea {
    private double tiempo;
    private String nombre;

    public linea (String nombre, double tiempo){
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public String getLinea(parada x){
        return x.getLinea();
        }

    public double getTiempoDeLinea(parada x) {
        if (this.nombre.equals(x.getLinea())) {
            return this.tiempo;
            }
            }

    public double getTiempo(){
        return this.tiempo;
    }
}