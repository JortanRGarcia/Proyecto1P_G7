package aviones;

import ficheros.Fichero;
import java.util.ArrayList;
import java.util.HashMap;

public class Avion {

    private final String codigo;
    private final int capacidad;
    private final ArrayList<Asiento> asientos;

    public Avion(String codigo, int capacidad, ArrayList<Asiento> asientos) {
        this.codigo = codigo;
        this.capacidad = capacidad;
        this.asientos = asientos;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public ArrayList<Asiento> getAsientos() {
        return asientos;
    }

    /**
     * Obtiene la información de los archivos {@code aviones.txt} y
     * {@code asientos.txt}
     *
     * @return Retorna un {@code ArrayList<Avion>} con todos los aviones
     * registrados
     */
    public static ArrayList<Avion> cargarAviones() {
        ArrayList<Avion> aviones = new ArrayList<>();
        HashMap<String, ArrayList<Asiento>> datosAsientos = new HashMap<>();

        for (String linea : Fichero.leerSinCabecera("archivos/asientos.txt")) {
            String[] datos = linea.split(",", 2);
            datosAsientos.putIfAbsent(datos[0], new ArrayList<>());
            String[] datosAsiento = datos[1].split(",");
            datosAsientos.get(datos[0]).add(
                    new Asiento(datosAsiento[0], datosAsiento[1].equals("S"))
            );
        }

        for (String linea : Fichero.leerSinCabecera("archivos/aviones.txt")) {
            String[] datos = linea.split(",");

            String codigo = datos[0];
            int capacidad = Integer.parseInt(datos[1]);

            aviones.add(new Avion(codigo, capacidad, datosAsientos.get(codigo)));

        }

        return aviones;
    }

}
