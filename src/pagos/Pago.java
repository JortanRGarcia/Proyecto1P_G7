package pagos;

import ficheros.Fichero;

public class Pago {
    
    private final String id;
    private final Reserva reserva;
    private final float totalPagarFinal;
    private final ModoPago modoPago;

    public Pago(String id, Reserva reserva, float totalPagarFinal, ModoPago modoPago) {
        this.id = id;
        this.reserva = reserva;
        this.totalPagarFinal = totalPagarFinal;
        this.modoPago = modoPago;
    }

    public String getId() {
        return id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public float getTotalPagarFinal() {
        return totalPagarFinal;
    }

    public ModoPago getModoPago() {
        return modoPago;
    }
    
    /**
     * Agrega un registro de {@code Pago} al archivo {@code pagos.txt}
     *
     * @return {@code true} si la operación se realizó correctamente, caso
     * contrario se retornará {@code false}
     */
    public boolean agregarAlFichero() {
        return Fichero.escribir(
                "archivos/pagos.txt",
                id + "," + reserva.getCodigo() + "," + totalPagarFinal + "," + modoPago.toString()
        );
    }

}
