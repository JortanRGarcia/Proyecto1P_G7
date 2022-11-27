package ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Fichero {

    /**
     * Lee todas las líneas de un archivo de texto. Permite saltarse la primera
     * línea del fichero en caso de así requerirlo.
     *
     * @param ruta Ruta relativa o absoluta del archivo
     * @param saltarPrimeraLinea De ser {@code true}, la primera línea del
     * archivo será ignorada
     * @return Un {@code ArrayList<String>} con todas las líneas leídas del
     * archivo
     */
    public static ArrayList<String> leer(String ruta, boolean saltarPrimeraLinea) {
        ArrayList<String> lineas = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;

            if (saltarPrimeraLinea) {
                reader.readLine();
            }

            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException ex) {
            System.err.println("No se pudo leer el archivo " + ruta);
        }

        return lineas;
    }

    /**
     * Atajo para {@link #leer(String ruta, boolean saltarPrimeraLinea)} en
     * donde {@code saltarPrimeraLinea} es {@code false}
     *
     * @param ruta Ruta relativa o absoluta del archivo
     * @return Un {@code ArrayList<String>} con todas las líneas leídas del
     * archivo
     */
    public static ArrayList<String> leer(String ruta) {
        return leer(ruta, false);
    }

    /**
     * Atajo para {@link #leer(String ruta, boolean saltarPrimeraLinea)} en
     * donde {@code saltarPrimeraLinea} es {@code true}
     *
     * @param ruta Ruta relativa o absoluta del archivo
     * @return Un {@code ArrayList<String>} con todas las líneas leídas del
     * archivo a excepción de la primera
     */
    public static ArrayList<String> leerSinCabecera(String ruta) {
        return leer(ruta, true);
    }

    /**
     * Agrega una nueva linea a un archivo de texto específico. <br>
     * En caso de que el archivo no exista, este será creado.
     *
     * @param ruta Ruta relativa o absoluta del archivo
     * @param linea Linea nueva que se quiere agregar al archivo
     * @return {@code true} si la operación se realizó correctamente, caso
     * contrario se retornará {@code false}
     */
    public static boolean escribir(String ruta, String linea) {

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(ruta, true))) {
            writer.write(linea + "\n");
        } catch (IOException ex) {
            System.err.println("No se pudo escribir el archivo " + ruta);
            return false;
        }

        return true;
    }

}
