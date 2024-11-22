package practica.ia;

//_____________________________________________________________________________
//imports
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//_____________________________________________________________________________
//clase del algoritmo
public class AEstrella {

    //clase para los nodos de la busqueda
    public class Nodo {
        private String parada;
        private String linea;
        private Nodo padre;
    
        public Nodo(String parada, String linea) {
            this.parada = parada;
            this.linea = linea;
            this.padre = null;
        }

        public Nodo(String parada, String linea, Nodo padre) {
            this.parada = parada;
            this.linea = linea;
            this.padre = padre;
        }
    
        public String getParada() {
            return parada;
        }
    
        public String getLinea() {
            return linea;
        }

        public Nodo getPadre() {
            return padre;
        }

        public void setPadre(Nodo padre) {
            this.padre = padre;
        }
    }

    private List<Parada> paradas;
    private List<Nodo> cerrado; // Conjunto de nodos cerrados
    private List<Nodo> abierto; // Cola de prioridad para nodos abiertos
    private Map<Nodo, Double> pesos;
    private Parada origen;

    public AEstrella(Parada origen, List<Parada> paradas) {
        this.paradas = new ArrayList<>();
        this.cerrado = new ArrayList<>();
        this.abierto = new ArrayList<>();
        this.pesos = new HashMap<>();
        abierto.add(new Nodo(origen.getNombre(), origen.getLinea().getNombre()));
    }

    // public Set<Nodo> ejecucionAlgoritmo (Parada destino) {

    //     if (abierto.isEmpty()) { throw new IllegalArgumentException(); }

    //     Nodo end = new Nodo(destino.getNombre(), destino.getLinea().getNombre());
    //     Set<Nodo> recorrido = new HashSet<>();
    //     Nodo actual = abierto.get(0);
    //     recorrido.add(actual);
    //     pesos.put(actual, (double) 0);
        
    //     while(!abierto.isEmpty()) {
            
    //         double peso = 0;
    //         Nodo q = null;
    //         for (Nodo n: abierto) { if (pesos.get(n) > peso) { q = n; } }

    //         abierto.remove(q);

    //         Parada act = encontrarParada(q.getParada(), q.getLinea());

    //         //cambiar json porq en aristas adyacentes no se tiene en cuanta q puedes hacer transbordo
    //         for (Aristas vecino: act.getAristas()) { abierto.add(new Nodo(vecino.getfin(), q.getLinea(), q)); }
                
    //         for (Nodo sucesor: abierto) {
    //             if (sucesor == end) {
    //                 break; //se termina
    //             }

    //             Parada pSucesor = encontrarParada(sucesor.getParada(), sucesor.getLinea());
    //             Parada pPadre = encontrarParada(sucesor.getPadre().getParada(), sucesor.getPadre().getLinea());
    //             double pesoSucesor = pesos.get(sucesor.getPadre()) + heuristica(pPadre, pSucesor);
    //             pesos.put(sucesor, pesoSucesor);

    //         }


    //     }

    //     return null;
    // }

    // private List<String> reconstruirCamino(Nodo objetivo) {
    //     List<String> camino = new LinkedList<>();
    //     Nodo actual = objetivo;
    //     while (actual != null) {
    //         camino.add(0, actual.nombre);
    //         actual = actual.predecesor;
    //     }
    //     return camino;
    // }

    private double heuristica(Parada origen, Parada destino) {
        return Math.sqrt(Math.pow(origen.getLongitud() - destino.getLongitud(), 2) + Math.pow(origen.getLatitud() - destino.getLatitud(), 2));
    }

    /* 
        funcion que devuelve la parada buscada por nombre y linea,
        no solo por nombre ya que hay paradas como independencia 
        que aparecen 2 veces en la lista de paradas.
    */

    private Parada encontrarParada (String nombre, String linea) {

        Parada res = null;

        for (Parada parada: paradas) {
            if (parada.getNombre().equals(nombre) 
                && parada.getLinea().equals(linea)){
                
                res = parada;
            }
        }

        return res;
    }
}