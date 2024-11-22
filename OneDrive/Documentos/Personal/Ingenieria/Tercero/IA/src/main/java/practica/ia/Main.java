package practica.ia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static Grafo grafo = new Grafo();
    private static Map<String, List<Parada>> lineasParadas = new HashMap<>(); // Mapa global para líneas y paradas
    private static Map<String, Parada> paradas = new HashMap<>();
    private static Map<String, Parada> paradasUnicas = new HashMap<>();
    private static List<Nodo> nodos=new ArrayList<>();//nodos en el interfaz
    private static Map<String, Color> coloresLineas = new HashMap<>();
    private static String paradaSeleccionada = "";
    private static String paradaDestinoSeleccionada = "";
    

    public static void main(String[] args) {

         // Asignar un color único para cada línea
        coloresLineas.put("Linea A", Color.BLUE);
        coloresLineas.put("Linea B", Color.RED);
        coloresLineas.put("Linea C",new Color(0, 0, 139));
        coloresLineas.put("Linea D", Color.GREEN);
        coloresLineas.put("Linea E",new Color(128, 0, 128));
        // Leer el archivo JSON
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("paradas.json");
        if (inputStream == null) {
            System.out.println("No se pudo encontrar el archivo paradas.json");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
try {
    // Leer el archivo JSON y mapearlo a una lista de objetos 'Parada'
    List<Parada> paradas = mapper.readValue(
        inputStream,
        mapper.getTypeFactory().constructCollectionType(List.class, Parada.class)
    );

    // Unificar paradas duplicadas (mismo nombre, diferentes líneas)
    for (Parada p : paradas) {
        if (paradasUnicas.containsKey(p.getNombre())) {
            // Si la parada ya existe, actualiza sus datos
            Parada existente = paradasUnicas.get(p.getNombre());
            existente.addLinea(p.getLinea()); // Agregar la nueva línea
            existente.getAristas().addAll(p.getAristas()); // Combinar las conexiones
        } else {
            // Si no existe, añádela como nueva
            paradasUnicas.put(p.getNombre(), p);
        }

        // Añadir la parada al mapa por línea
        lineasParadas.computeIfAbsent(p.getLinea().getNombre(), k -> new ArrayList<>()).add(paradasUnicas.get(p.getNombre()));
    }

} catch (IOException e) {
    e.printStackTrace();
}


        

        // Crear ventana principal
        JFrame frame = new JFrame("Metro de Buenos Aires");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Panel izquierdo (Origen)
        JPanel panelOrigen = new JPanel();
        panelOrigen.setLayout(new GridLayout(8, 1, 10, 10)); // Ajustar para incluir Día y Hora
        panelOrigen.setBorder(BorderFactory.createTitledBorder("Origen"));

        String[] lineas = {"Linea A", "Linea B", "Linea C", "Linea D", "Linea E"};

        // Componentes del panel izquierdo
        JComboBox<String> comboLineaOrigen = new JComboBox<>(lineas);
        JComboBox<String> comboParadaOrigen = new JComboBox<>();
        JComboBox<String> comboDia = new JComboBox<>(new String[]{"Lunes a Viernes", "Sábado", "Domingo"});
        JComboBox<String> comboHora = new JComboBox<>(generarHoras());

        panelOrigen.add(new JLabel("Línea:"));
        panelOrigen.add(comboLineaOrigen);
        panelOrigen.add(new JLabel("Parada:"));
        panelOrigen.add(comboParadaOrigen);
        panelOrigen.add(new JLabel("Día:"));
        panelOrigen.add(comboDia);
        panelOrigen.add(new JLabel("Hora:"));
        panelOrigen.add(comboHora);

        // Panel derecho (Destino)
        JPanel panelDestino = new JPanel();
        panelDestino.setLayout(new GridLayout(4, 1, 10, 10));
        panelDestino.setBorder(BorderFactory.createTitledBorder("Destino"));

        // Componentes del panel derecho
        JComboBox<String> comboLineaDestino = new JComboBox<>(lineas);
        JComboBox<String> comboParadaDestino = new JComboBox<>();

        panelDestino.add(new JLabel("Línea:"));
        panelDestino.add(comboLineaDestino);
        panelDestino.add(new JLabel("Parada:"));
        panelDestino.add(comboParadaDestino);

        JPanel panelMapa = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
        
                // Dimensiones del mapa
                int dimensionH = getHeight() - 60;
                int dimensionW = getWidth() - 60;
        
                // Coordenadas mínimas y máximas de latitud y longitud
                double minLatitud = -34.6257;
                double maxLatitud = -34.5917;
                double minLongitud = -58.4019;
                double maxLongitud = -58.3709;
        
                // *** DIBUJAR LAS ARISTAS (Conexiones entre paradas) ***
                for (List<Parada> paradasLinea : lineasParadas.values()) {
                    for (Parada parada : paradasLinea) {
                        int x1 = (int) (((parada.getLongitud() - minLongitud) / (maxLongitud - minLongitud)) * dimensionW) + 30;
                        int y1 = (int) (dimensionH - (((parada.getLatitud() - minLatitud) / (maxLatitud - minLatitud)) * dimensionH)) + 30;
        
                        // Dibujar conexiones (aristas)
                        for (Aristas arista : parada.getAristas()) {
                            Parada paradaDestino = findParadaByName(arista.getfin());
                            if (paradaDestino != null) {
                                int x2 = (int) (((paradaDestino.getLongitud() - minLongitud) / (maxLongitud - minLongitud)) * dimensionW) + 30;
                                int y2 = (int) (dimensionH - (((paradaDestino.getLatitud() - minLatitud) / (maxLatitud - minLatitud)) * dimensionH)) + 30;
        
                                // Color de la línea correspondiente
                                Color colorLinea = coloresLineas.get(parada.getLinea().getNombre());
                                if (colorLinea == null) {
                                    colorLinea = Color.BLACK; // Color por defecto si no se encuentra
                                }
                                g.setColor(colorLinea);
                                g.drawLine(x1, y1, x2, y2); // Dibujar la línea
                            }
                        }
                    }
                }
        
                // *** DIBUJAR LAS PARADAS ***
                for (List<Parada> paradasLinea : lineasParadas.values()) {
                    for (Parada parada : paradasLinea) {
                        int x = (int) (((parada.getLongitud() - minLongitud) / (maxLongitud - minLongitud)) * dimensionW) + 30;
                        int y = (int) (dimensionH - (((parada.getLatitud() - minLatitud) / (maxLatitud - minLatitud)) * dimensionH)) + 30;
        
                        // Si la parada es seleccionada como origen o destino, dibujarla en rojo
                        if (parada.getNombre().equals(paradaSeleccionada)) {
                            g.setColor(Color.RED); // Origen
                        } else if (parada.getNombre().equals(paradaDestinoSeleccionada)) {
                            g.setColor(Color.RED); // Destino
                        } else {
                            g.setColor(Color.BLACK); // Color predeterminado
                        }
        
                        // Dibujar la parada como un punto
                        g.fillOval(x - 5, y - 5, 10, 10); // Dibujar el círculo de la parada
                        g.drawString(parada.getNombre(), x + 10, y); // Dibujar el nombre de la parada
                    }
                }
            }
        
            private Parada findParadaByName(String nombre) {
                for (List<Parada> paradas : lineasParadas.values()) {
                    for (Parada parada : paradas) {
                        if (parada.getNombre().equals(nombre)) {
                            return parada;
                        }
                    }
                }
                return null;
            }
        };


        // Escuchar cambios en los combos de Origen
        comboLineaOrigen.addActionListener(e -> {
            String lineaSeleccionada = (String) comboLineaOrigen.getSelectedItem();
            cargarParadas(comboParadaOrigen, lineaSeleccionada);
        });

        // Escuchar cambios en los combos de Destino
        comboLineaDestino.addActionListener(e -> {
            String lineaSeleccionada = (String) comboLineaDestino.getSelectedItem();
            cargarParadas(comboParadaDestino, lineaSeleccionada);
        });

        comboParadaOrigen.addActionListener(e -> {
            paradaSeleccionada = (String) comboParadaOrigen.getSelectedItem();  // Captura la parada origen seleccionada
            panelMapa.repaint();  // Solicita que se repinte el panel del mapa
        });
        
        // Actualización de la variable `paradaDestinoSeleccionada` para el destino
        comboParadaDestino.addActionListener(e -> {
            paradaDestinoSeleccionada = (String) comboParadaDestino.getSelectedItem();  // Captura la parada destino seleccionada
            panelMapa.repaint();  // Solicita que se repinte el panel del mapa
        });

        // Cargar paradas iniciales
        if (comboLineaOrigen.getItemCount() > 0) {
            comboLineaOrigen.setSelectedIndex(0); // Seleccionar la primera línea
            cargarParadas(comboParadaOrigen, (String) comboLineaOrigen.getSelectedItem());
        }

        if (comboLineaDestino.getItemCount() > 0) {
            comboLineaDestino.setSelectedIndex(0); // Seleccionar la primera línea
            cargarParadas(comboParadaDestino, (String) comboLineaDestino.getSelectedItem());
        }

        // Agregar paneles a la ventana
        frame.add(panelOrigen, BorderLayout.WEST);
        frame.add(panelDestino, BorderLayout.EAST);
        frame.add(panelMapa, BorderLayout.CENTER);
        //frame.setResizable(false);//mantener el tamaño de ventana fijo

        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método para cargar paradas según la línea seleccionada
    public static void cargarParadas(JComboBox<String> comboParadas, String lineaSeleccionada) {
        comboParadas.removeAllItems();
        if (lineasParadas.containsKey(lineaSeleccionada)) {
            for (Parada p : lineasParadas.get(lineaSeleccionada)) {
                comboParadas.addItem(p.getNombre());
            }
        }
    }
    

    // public static void dibujarMapa(Graphics g) {
    //     // Escala para convertir las coordenadas (latitud, longitud) a píxeles en el mapa
    //     double escalaX = 1000;  // Este es solo un ejemplo de escala, ajusta según tus necesidades
    //     double escalaY = 1000;

    //     for (Parada parada : paradas.values()) {
    //         // Convertir coordenadas lat, lon a coordenadas de pantalla
    //         int x = (int) (parada.getLongitud() * escalaX) + 400;  // 400 es para centrar el mapa
    //         int y = (int) (parada.getLatitud() * escalaY) + 300;   // 300 es para centrar el mapa

    //         // Dibujar la parada como un círculo en el mapa
    //         g.setColor(Color.RED);  // Color de la parada
    //         g.fillOval(x - 5, y - 5, 10, 10);  // Dibuja el círculo de la parada

    //         // Mostrar el nombre de la parada
    //         g.setColor(Color.BLACK);
    //         g.drawString(parada.getNombre(), x + 10, y);
    //     }
    // }

    // Método para generar las horas desde las 6:00 AM hasta las 00:00 cada 15 minutos
    public static String[] generarHoras() {
        List<String> horas = new ArrayList<>();
        for (int hora = 6; hora <= 23; hora++) {
            for (int minuto = 0; minuto < 60; minuto += 15) {
                horas.add(String.format("%02d:%02d", hora, minuto));
            }
        }
        horas.add("00:00");
        return horas.toArray(new String[0]);
    }
}

