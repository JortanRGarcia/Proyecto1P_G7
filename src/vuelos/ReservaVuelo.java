package vuelos;

import aviones.Asiento;
import ficheros.Fichero;

public class ReservaVuelo {

    private final String codigo;
    private final Vuelo vuelo;
    private final TipoVuelo tipo;
    private final Tarifa tarifa;
    private final Asiento asiento;

    public ReservaVuelo(String codigo, Vuelo vuelo, TipoVuelo tipo, Tarifa tarifa, Asiento asiento) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.tipo = tipo;
        this.tarifa = tarifa;
        this.asiento = asiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public TipoVuelo getTipo() {
        return tipo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    /**
     * Agrega un registro de {@code ReservaVuelo} al archivo
     * {@code vuelosReserva.txt}
     *
     * @return {@code true} si la operación se realizó correctamente, caso
     * contrario se retornará {@code false}
     */
    public boolean agregarAlFichero() {
        return Fichero.escribir(
                "archivos/vuelosReserva.txt",
                codigo + "," + vuelo.getCodigo() + "," + tipo.toString() + "," + tarifa.toString() + "," + asiento.getNumero()
        );
    }

}
