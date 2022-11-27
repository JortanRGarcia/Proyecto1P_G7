package usuarios;

public abstract class Usuario {

    private final String cedula;
    private final String nombres;
    private final String apellidos;
    private final int edad;
    private final String correo;
    private final String usuario;
    private final String contraseña;

    public Usuario(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.correo = correo;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsuario() {
        return usuario;
    }

}
