package vuelos;

import aviones.Avion;
import ficheros.Fichero;
import java.util.ArrayList;
import java.util.HashMap;

public class Vuelo {

    private final String codigo;
    private final Avion avion;
    private final String fechaSalida;
    private final String fechaLlegada;
    private final Itinerario itinerario;
    private final float precio;
    private final int precioMillas;

    public Vuelo(String codigo, Avion avion, String fechaSalida, String fechaLlegada, Itinerario itinerario, float precio, int precioMillas) {
        this.codigo = codigo;
        this.avion = avion;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.itinerario = itinerario;
        this.precio = precio;
        this.precioMillas = precioMillas;
    }

    public String getCodigo() {
        return codigo;
    }

    public Avion getAvion() {
        return avion;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public float getPrecio() {
        return precio;
    }

    public int getPrecioMillas() {
        return precioMillas;
    }

    /**
     * Obtiene la información de los archivos {@code vuelos.txt} e
     * {@code itinerarios.txt}.
     *
     * @param aviones
     * @return Retorna un {@code ArrayList<Vuelo>} con todos los vuelos
     * registrados
     */
    public static ArrayList<Vuelo> cargarVuelos(ArrayList<Avion> aviones) {
        ArrayList<Vuelo> vuelos = new ArrayList<>();
        HashMap<String, Itinerario> datosItinerarios = new HashMap<>();
        HashMap<String, Avion> datosAviones = new HashMap<>();
        for (Avion avion : aviones) {
            datosAviones.put(avion.getCodigo(), avion);
        }

        for (String linea : Fichero.leerSinCabecera("archivos/itinerarios.txt")) {
            String[] datosItinerario = linea.split(",");
            datosItinerarios.put(datosItinerario[0], new Itinerario(datosItinerario[0], datosItinerario[1], datosItinerario[2], datosItinerario[3], datosItinerario[4], datosItinerario[5]));
        }

        for (String linea : Fichero.leerSinCabecera("archivos/vuelos.txt")) {
            String[] datos = linea.split(",");

            String codigo = datos[0];
            String codigoAvion = datos[1];
            String fechaSalida = datos[2];
            String fechaLlegada = datos[3];
            String codigoItinerario = datos[4];
            float precio = Float.parseFloat(datos[5]);
            int precioMillas = Integer.parseInt(datos[6]);

            vuelos.add(new Vuelo(codigo, datosAviones.get(codigoAvion), fechaSalida, fechaLlegada, datosItinerarios.get(codigoItinerario), precio, precioMillas));
        }

        return vuelos;
    }

    @Override
    public String toString() {
        return "CÓDIGO: " + codigo +"\n"
                + "HORA SALIDA: " + itinerario.getHoraSalida() +"\n"
                + "HORA LLEGADA: " + itinerario.getHoraLlegada()+"\n"
                + "DURACIÓN: "+ itinerario.getDuracion()+"\n"
                + "AVIÓN: "+ avion.getCodigo()+"\n"
                + "PRECIO: "+ precio+"\n"
                + "COSTO MILLAS: "+ precioMillas;
    }
    
    

}
