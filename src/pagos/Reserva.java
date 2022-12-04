package pagos;

import ficheros.Fichero;
import java.util.ArrayList;
import java.util.HashMap;
import usuarios.Cliente;
import usuarios.Usuario;
import vuelos.Vuelo;

public class Reserva {

    private final String codigo;
    private final Vuelo vuelo;
    private final Cliente cliente;
    private final String fecha;
    private final float valorPagar;

    public Reserva(String codigo, Vuelo vuelo, Cliente cliente, String fecha, float valorPagar) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.cliente = cliente;
        this.fecha = fecha;
        this.valorPagar = valorPagar;
    }

    public String getCodigo() {
        return codigo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public float getValorPagar() {
        return valorPagar;
    }

    /**
     * Agrega un registro de {@code Reserva} al archivo {@code reservas.txt}
     *
     * @return {@code true} si la operación se realizó correctamente, caso
     * contrario se retornará {@code false}
     */
    public boolean agregarAlFichero() {
        return Fichero.escribir(
                "archivos/reservas.txt",
                codigo + "," + vuelo.getCodigo() + "," + cliente.getNombres() + " " + cliente.getApellidos() + "," + fecha + "," + valorPagar
        );
    }

    /**
     * Obtiene la información del archivo {@code reservas.txt}
     *
     * @param vuelos
     * @param usuarios
     * @return Retorna un {@code ArrayList<Reserva>} con todas los reservas
     * registradas
     */
    public static ArrayList<Reserva> cargarReservas(ArrayList<Vuelo> vuelos, ArrayList<Usuario> usuarios) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        HashMap<String, Vuelo> datosVuelos = new HashMap<>();
        for (Vuelo vuelo : vuelos) {
            datosVuelos.put(vuelo.getCodigo(), vuelo);
        }

        HashMap<String, Cliente> datosClientes = new HashMap<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente cliente) {
                datosClientes.put(cliente.getNombres() + " " + cliente.getApellidos(), cliente);
            }
        }

        for (String linea : Fichero.leerSinCabecera("archivos/reservas.txt")) {
            String[] datos = linea.split(",");

            String codigo = datos[0];
            String codigoVuelo = datos[1];
            String nombreCliente = datos[2];
            String fecha = datos[3];
            float valorPagar = Float.parseFloat(datos[4]);

            reservas.add(new Reserva(codigo, datosVuelos.get(codigoVuelo), datosClientes.get(nombreCliente), fecha, valorPagar));
        }

        return reservas;
    }

    @Override
    public String toString() {
        return "NOMBRES: " + cliente.getNombres() + " " + cliente.getApellidos() + "\n"
                + "Cédula: " + cliente.getCedula() + "\n"
                + "VUELO: " + vuelo.getCodigo() + "\n"
                + "HORA SALIDA: " + vuelo.getItinerario().getHoraSalida() + "\n"
                + "HORA LLEGADA: " + vuelo.getItinerario().getHoraLlegada() + "\n"
                + "AVIÓN: : " + vuelo.getAvion().getCodigo();
    }

}
