#____________________________
#imports
from linea import Linea
from parada import Parada
from Dia import Dia
from datetime import time

import json


#___________________________________
#variables

# Linea A
Alberti = Parada('Alberti', 'C4')
Pasco = Parada('Pasco', 'D4')
Congreso = Parada('Congreso', 'D4')
Saenz_Pena = Parada('Saaenz Peña', 'D4')
Lima = Parada('Lima', 'D4')
Avenida_de_Mayo = Parada('Avenida de Mayo', 'D4')
Piedras = Parada('Piedras', 'D4')
Peru = Parada('Peru', 'D4')
Plaza_de_Mayo = Parada('Plaza de Mayo', 'D4')

aux = []
aux.extend([Alberti, Pasco, Congreso, Saenz_Pena, Lima, Avenida_de_Mayo, Piedras, Peru, Plaza_de_Mayo])
linea_A = Linea(aux, 1.4375)

# Linea B
Leandro_N_Alem = Parada("Leandro N. Alem", "D3")
Florida = Parada("Florida", "D3")
Carlos_Pellegrini = Parada("Carlos Pellegrini", "D3")
Uruguay = Parada("Uruguay", "D3")
Callao_B = Parada("Callao", "D3")
Pasteur = Parada("Pasteur", "C3")

aux = []
aux.extend([Leandro_N_Alem, Florida, Carlos_Pellegrini, Uruguay, Callao_B, Pasteur])
linea_B = Linea(aux,1.53)

# Linea C
Avenida_de_Mayo_C = Parada("Avenida de Mayo", 'D4')
Constitucion = Parada("Constitución", 'D5')
Diagonal_Norte = Parada("Diagonal Norte", 'D3')
General_San_Martin = Parada("General San Martin", 'D3')
Independencia_C = Parada("Independencia", 'D4')
Lavalle = Parada("Lavalle", 'D3')
Moreno = Parada("Moreno", 'D4')
Retiro = Parada("Retiro", 'D3')
San_Juan = Parada("San Juan", 'D4')

aux = []
aux.extend([Avenida_de_Mayo_C, Constitucion, Diagonal_Norte, General_San_Martin, Independencia_C, Lavalle, Moreno, Retiro, San_Juan])
linea_C = Linea(aux, 1.4)

# Linea D
Facultad_de_medicina = Parada("Facultad de medicina", 'D3')
Callao_D = Parada("Callao", 'D3')
Tribunales = Parada("Tribunales", 'D3')
Nueve_de_Julio = Parada("9 de Julio", 'D3')
Catedral = Parada("Catedral", 'D4')

aux = []
aux.extend([Facultad_de_medicina, Callao_D, Tribunales, Nueve_de_Julio, Catedral])
linea_D = Linea(aux, 1.625)

# Linea E
Pichincha = Parada("Pichincha", "D5")
Entre_Rios = Parada("Entre Rios", "D5")
San_Jose = Parada("San Jose", "D4")
Independencia_E = Parada("Independencia", "D4")
Belgrano = Parada("Belgrano", "D4")
Bolivar = Parada("Bolivar", "D4")

aux = []
aux.extend([Pichincha, Entre_Rios, San_Jose, Independencia_E, Belgrano, Bolivar])
linea_E = Linea(aux,1.6)

dias_habiles = ['lunes', 'martes', 'miercoles', 'jueves' , 'viernes']


def saleTren(origen, dia, hora):
    res = False
    match(origen.getLinea()):
        case 'A':
            if dia in dias_habiles:
                if hora > time(5, 0) or hora < time(22, 28):
                    res = True
            elif dia == 'sabado':
                if hora > time(5, 0) or hora < time(22, 27):
                    
            else: 
                if hora > time(5, 0) or hora < time(22, 3):
                   

        case 'B':
            
            
        case 'C':
            
            
        case 'D':
            
            
        case 'E':
        
    
    return res


def costo(origen, destino):
    
    


def main():
    
    exit(0)
           
           
           
'''
  /**
   * Devuelve un conjunto con todos los vertices alcanzables desde AMBOS
   * v1 y v2.
   */
	public static <V> Set<Vertex<V>> reachableFromBoth(DirectedGraph<V, Boolean> g, Vertex<V> v1, Vertex<V> v2) {
        
        Set<Vertex<V>> verticesComunes = new HashTableMapSet<>();
		Set<Vertex<V>> alcanzableDesdeV1 = verticesAlcanzables(g, v1);
        Set<Vertex<V>> alcanzableDesdeV2 = verticesAlcanzables(g, v2);
        
        for (Vertex<V> verticeV1 : alcanzableDesdeV1) {
        	
            for (Vertex<V> verticeV2 : alcanzableDesdeV2) {
            	
                if (verticeV1.equals(verticeV2)) {
                	
                    verticesComunes.add(verticeV1);
                    break;
                    
                }
            }
        }
        
        return verticesComunes;
    }

	private static <V> Set<Vertex<V>> verticesAlcanzables(DirectedGraph<V, Boolean> g, Vertex<V> start) {
        
		Set<Vertex<V>> visitado = new HashTableMapSet<>();
        Set<Vertex<V>> alcanzado = new HashTableMapSet<>();
        Stack<Vertex<V>> pila = new Stack<>();
        pila.push(start);

        while (!pila.isEmpty()) {
            Vertex<V> actual = pila.pop();

            visitado.add(actual);
            alcanzado.add(actual);

                for (Edge<Boolean> arista : g.outgoingEdges(actual)) {
                	
                    Vertex<V> adyacente = g.endVertex(arista);

                    if (!visitado.contains(adyacente) && arista.element()) {
                    	
                        pila.push(adyacente);
                        
                    }
                }
            
        }

        return alcanzado;
    }
	
	
/**
 * Devuelve un camino (una lista de aristas) que llevan desde from y to,
 * donde la suma de los elementos de las aristas del camino <= limit.
 * Si no existe ningun camino que cumple con esta restriccion se devuelve
 * el valor null.
 */

	public static <V> PositionList<Edge<Integer>> existsPathLess(UndirectedGraph<V, Integer> g, Vertex<V> from, Vertex<V> to, int limit) {
		
		
		Set<Vertex<V>> visitado = new HashTableMapSet<>();
		PositionList<Edge<Integer>> camino = new NodePositionList<>();

        if (BeP(g, from, to, limit, 0, camino, visitado)) {
        	
            return camino;
            
        } else {
        	
            return null;
            
        }
    }

    private static <V> boolean BeP(UndirectedGraph<V, Integer> g, Vertex<V> actual, Vertex<V> destino, int limite,
                                   int sumaAc, PositionList<Edge<Integer>> camino, Set<Vertex<V>> visitado) {
    	
    	visitado.add(actual);

        if (actual.equals(destino)) {
            
            return true;
            
        }
        
        for (Edge<Integer> arista : g.edges(actual)) {
        	
            if (!visitado.contains(g.opposite(actual, arista))) {
            	
                Vertex<V> opuesto = g.opposite(actual, arista);
                int peso = arista.element();

                if (sumaAc + peso < limite) {
                	
                	camino.addLast(arista);

                    if (BeP(g, opuesto, destino, limite, sumaAc + peso, camino, visitado)) {
                    	
                        return true;
                        
                    }

                    camino.remove(camino.last());
                }
            }
        }

        return false;
    }
'''