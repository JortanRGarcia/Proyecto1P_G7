package pagos;

import ficheros.Fichero;
import vuelos.Vuelo;

public class Reserva {

    private final String codigo;
    private final Vuelo vuelo;
    private final String cliente;
    private final String fecha;
    private final float valorPagar;

    public Reserva(String codigo, Vuelo vuelo, String cliente, String fecha, float valorPagar) {
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

    public String getCliente() {
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
                codigo + "," + vuelo.getCodigo() + "," + cliente + "," + fecha + "," + valorPagar
        );
    }

}
