package usuarios;

public class Cliente extends Usuario {

    private final String numeroTarjeta;
    private final TipoVip tipoVip;
    private final int millas;

    public Cliente(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, String numeroTarjeta, TipoVip tipoVip, int millas) {
        super(cedula, nombres, apellidos, edad, correo, usuario, contraseña);
        this.numeroTarjeta = numeroTarjeta;
        this.tipoVip = tipoVip;
        this.millas = millas;
    }

    public Cliente(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, String numeroTarjeta) {
        this(cedula, nombres, apellidos, edad, correo, usuario, contraseña, numeroTarjeta, null, 0);
    }

    public TipoVip getTipoVip() {
        return tipoVip;
    }

    public int getMillas() {
        return millas;
    }

}
