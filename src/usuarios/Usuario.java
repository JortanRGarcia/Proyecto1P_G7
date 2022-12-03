package usuarios;

import ficheros.Fichero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Usuario {

    private final String cedula;
    private final String nombres;
    private final String apellidos;
    private final int edad;
    private final String correo;
    private final String usuario;
    private final String contraseña;

    protected static final Scanner scanner = new Scanner(System.in);

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

    /**
     * Valida las credenciales de usuario
     *
     * @param usuario
     * @param contraseña
     * @return Devuelve {@code true} si las credenciales son correctas, caso
     * contrario retorna {@code false}
     */
    public boolean iniciarSesion(String usuario, String contraseña) {
        return usuario.equalsIgnoreCase(this.usuario) && contraseña.equals(this.contraseña);
    }

    /**
     * Muestra el menú de usuario
     */
    abstract public void mostrarMenu();

    /**
     * Muestra las reservas registradas en el sistema
     */
    abstract public void mostrarReservas();

    /**
     * Obtiene la información de los archivos {@code usuarios.txt},
     * {@code operadores.txt} y {@code clientes.txt}
     *
     * @return Retorna un {@code ArrayList<Usuario>} con todos los usuarios
     * registrados
     */
    public static ArrayList<Usuario> cargarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        HashMap<String, String[]> datosOperadores = new HashMap<>();
        HashMap<String, String[]> datosClientes = new HashMap<>();

        for (String linea : Fichero.leerSinCabecera("archivos/operadores.txt")) {
            String[] datos = linea.split(",", 2);
            datosOperadores.put(datos[0], datos[1].split(","));
        }

        for (String linea : Fichero.leerSinCabecera("archivos/clientes.txt")) {
            String[] datos = linea.split(",", 2);
            datosClientes.put(datos[0], datos[1].split(","));
        }

        for (String linea : Fichero.leerSinCabecera("archivos/usuarios.txt")) {
            String[] datos = linea.split(",");

            String cedula = datos[0];
            String nombres = datos[1];
            String apellidos = datos[2];
            int edad = Integer.parseInt(datos[3]);
            String correo = datos[4];
            String usuario = datos[5];
            String contraseña = datos[6];
            char perfil = datos[7].charAt(0);

            if (perfil == 'O') {
                String[] datosOperador = datosOperadores.get(cedula);
                float sueldo = Float.parseFloat(datosOperador[0]);

                usuarios.add(new Operador(cedula, nombres, apellidos, edad, correo, usuario, contraseña, sueldo));
            } else {
                String[] datosCliente = datosClientes.get(cedula);
                String numeroTarjeta = datosCliente[0];
                TipoVip tipoVip = null;
                if (!datosCliente[1].equals("null")) {
                    tipoVip = TipoVip.valueOf(datosCliente[1]);
                }
                int millas = Integer.parseInt(datosCliente[2]);

                usuarios.add(new Cliente(cedula, nombres, apellidos, edad, correo, usuario, contraseña, numeroTarjeta, tipoVip, millas));
            }
        }

        return usuarios;
    }

}
