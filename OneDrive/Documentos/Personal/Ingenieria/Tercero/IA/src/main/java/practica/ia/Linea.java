package practica.ia;

public class Linea {
    private String nombre;
    private double tiempo;

    public Linea (String nombre, double tiempo){
        this.nombre = nombre;
        this.tiempo = tiempo;
    }
    public Linea (){

    }

    public String getNombre() {
        return nombre;
    }

    public Linea getLinea(Parada x){
        return x.getLinea();
        }

    public double getTiempoDeLinea(Parada x) {
        if (this.nombre.equals(x.getLinea())) {
            return this.tiempo;
            }
            return 0;
            }

    public double getTiempo(){
        return this.tiempo;
    }

    @Override
    public String toString(){
        return " Nombre= " + nombre + " Tiempo= " + tiempo;
    }
}