package airequador;

import aviones.Avion;
import java.util.ArrayList;
import java.util.Scanner;
import pagos.Reserva;
import usuarios.Usuario;
import vuelos.Vuelo;

public class AirEquador {

    public static final ArrayList<Usuario> usuarios = Usuario.cargarUsuarios();
    public static final ArrayList<Vuelo> vuelos = Vuelo.cargarVuelos(Avion.cargarAviones());
    public static final ArrayList<Reserva> reservas = Reserva.cargarReservas(vuelos, usuarios);

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    /**
     * Muesta el Menú Principal del Sistema
     */
    static void mostrarMenuPrincipal() {
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
