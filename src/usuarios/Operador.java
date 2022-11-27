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
                }
                case "2" -> {
                }
                case "3" -> {
                }
                default -> System.out.println("\nOPCIÓN INCORRECTA");
            }
        } while (!opcion.equals("3"));
    }

}
