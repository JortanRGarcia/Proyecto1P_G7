package airequador;

import java.util.ArrayList;
import java.util.Scanner;
import usuarios.Usuario;

public class AirEquador {

    public static ArrayList<Usuario> usuarios = Usuario.cargarUsuarios();

    static Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mostrarMenu();
    }

    static void mostrarMenu() {
        System.out.println(
                "++++++++++++++++++++++++++++++++++\n"
                + "      BIENVENIDO AL SISTEMA\n"
                + "++++++++++++++++++++++++++++++++++\n"
        );

        System.out.print("USUARIO: ");
        String usuario = scanner.nextLine();
        System.out.print("CONTRASEÑA: ");
        String contraseña = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.iniciarSesion(usuario, contraseña)) {
                System.out.println("\nBienvenido " + u.getNombres() + " " + u.getApellidos() + "\n");
                u.mostrarMenu();
                return;
            }
        }

        System.out.println("\nUSUARIO O CONTRASEÑA INCORRECTOS");

    }

}
