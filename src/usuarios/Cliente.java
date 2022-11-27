package usuarios;

public class Cliente extends Usuario {

    private final TipoVip tipoVip;
    private final int millas;

    public Cliente(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña) {
        super(cedula, nombres, apellidos, edad, correo, usuario, contraseña);
        this.tipoVip = null;
        this.millas = 0;
    }

    public Cliente(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, TipoVip tipoVip, int millas) {
        super(cedula, nombres, apellidos, edad, correo, usuario, contraseña);
        this.tipoVip = tipoVip;
        this.millas = millas;
    }

    public TipoVip getTipoVip() {
        return tipoVip;
    }

    public int getMillas() {
        return millas;
    }

}
