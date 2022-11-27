package usuarios;

import airequador.AirEquador;
import java.util.ArrayList;

public class Operador extends Usuario {

    private final float sueldo;

    public Operador(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, float sueldo) {
        super(cedula, nombres, apellidos, edad, correo, usuario, contraseña);
        this.sueldo = sueldo;
    }

    public float getSueldo() {
        return sueldo;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("MENÚ OPERADOR");

        String opcion;
        do {
            System.out.println(
                    """

                    1. Consultar usuarios
                    2. Consultar reservas
                    3. Salir
                    """
            );

            System.out.print("OPCIÓN: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> {
                    consultarUsuarios(AirEquador.usuarios);
                }
                case "2" -> {
                }
                case "3" -> {
                }
                default -> {
                    System.out.println("\nOPCIÓN INCORRECTA");
                }
            }
        } while (!opcion.equals("3"));
    }

    /**
     * Muestra los datos de los usuarios.
     *
     * @param usuarios
     */
    private void consultarUsuarios(ArrayList<Usuario> usuarios) {
        System.out.println("\nUsuarios del Sistema:");
        for (Usuario usuario : usuarios) {
            System.out.println("\n" + usuario);
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombres() + " " + getApellidos() + "\n"
                + "Tipo: OPERADOR\n"
                + "Sueldo: $" + sueldo;
    }

}
