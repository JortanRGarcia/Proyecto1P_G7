package vuelos;

public class Itinerario {
    
    private final String codigo;
    private final String origen;
    private final String destino;
    private final String horaSalida;
    private final String horaLlegada;
    private final String duracion;

    public Itinerario(String codigo, String origen, String destino, String horaSalida, String horaLlegada, String duracion) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.duracion = duracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public String getDuracion() {
        return duracion;
    }
    
    

}
