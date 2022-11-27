package usuarios;

public class Operador extends Usuario {

    private final float sueldo;

    public Operador(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, float sueldo) {
        super(cedula, nombres, apellidos, edad, correo, usuario, contraseña);
        this.sueldo = sueldo;
    }

    public float getSueldo() {
        return sueldo;
    }

}
