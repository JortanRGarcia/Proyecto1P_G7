package airequador;

import java.util.ArrayList;
import java.util.Scanner;
import usuarios.Usuario;

public class AirEquador {

    public static ArrayList<Usuario> usuarios = Usuario.cargarUsuarios();

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mostrarMenu();
    }

    static void mostrarMenu() {
        System.out.println(
            """
            ++++++++++++++++++++++++++++++++++
                  BIENVENIDO AL SISTEMA
            ++++++++++++++++++++++++++++++++++
            """
        );

        System.out.print("USUARIO: ");
        String usuario = scanner.nextLine();
        System.out.print("CONTRASEÑA: ");
        String contraseña = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.iniciarSesion(usuario, contraseña)) {
                System.out.println("\nBienvenido " + u.getNombres() + " " + u.getApellidos() + "\n");
                u.mostrarMenu();
                System.out.println("\nGRACIAS POR UTILIZAR NUESTRO SISTEMA");
                return;
            }
        }

        System.out.println("\nUSUARIO O CONTRASEÑA INCORRECTOS");

    }

}
