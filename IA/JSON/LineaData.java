package IA.JSON;

import java.util.List;
import java.util.Map;

public class LineaData {
    public Map<String, Map<String, Estacion>> linea;
    public Horario global_schedule;
}

class Estacion {
    public Map<String, Neighbor> neighbors;
    public Double latitud;   // Añadir si deseas obtener coordenadas
    public Double longitud;  // Añadir si deseas obtener coordenadas
}

class Neighbor {
    public double distance;
    public String direction;
}

class Horario {
    public Map<String, Map<String, DireccionHorario>> entre_semana;
    public Map<String, Map<String, DireccionHorario>> sabado;
    public Map<String, Map<String, DireccionHorario>> domingo;
}

class DireccionHorario {
    public String first_train;
    public String last_train;
    public int interval;
}
