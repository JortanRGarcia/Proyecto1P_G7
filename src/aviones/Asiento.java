package aviones;

public class Asiento {

    private final String numero;
    private boolean disponible;

    public Asiento(String numero, boolean disponible) {
        this.numero = numero;
        this.disponible = disponible;
    }

    public String getNumero() {
        return numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
