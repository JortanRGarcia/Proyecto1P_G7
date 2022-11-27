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

    @Override
    public void mostrarMenu() {
        System.out.println("MENÚ CLIENTE");

        String opcion;
        do {
            System.out.println(
                    """

                    1. Comprar tickets aéreos
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
                default -> {
                    System.out.println("\nOPCIÓN INCORRECTA");
                }
            }
        } while (!opcion.equals("3"));
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombres() + " " + getApellidos() + "\n"
                + "Tipo: CLIENTE " + (tipoVip == null ? "ESTÁNDAR" : "VIP " + tipoVip.toString().replace("_", " ")) + "\n"
                + "Cédula: " + getCedula();
    }

}
