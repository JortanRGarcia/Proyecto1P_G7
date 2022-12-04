package usuarios;

import airequador.AirEquador;
import aviones.Asiento;
import java.util.ArrayList;
import java.util.Random;
import pagos.ModoPago;
import pagos.Pago;
import pagos.Reserva;
import utilidades.Generador;
import utilidades.Validador;
import vuelos.ReservaVuelo;
import vuelos.Tarifa;
import vuelos.TipoVuelo;
import vuelos.Vuelo;

public class Cliente extends Usuario {

    private final String numeroTarjeta;
    private final TipoVip tipoVip;
    private final int millas;

    private static final Random random = new Random();

    public Cliente(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, String numeroTarjeta, TipoVip tipoVip, int millas) {
        super(cedula, nombres, apellidos, edad, correo, usuario, contraseña);
        this.numeroTarjeta = numeroTarjeta;
        this.tipoVip = tipoVip;
        this.millas = millas;
    }

    public Cliente(String cedula, String nombres, String apellidos, int edad, String correo, String usuario, String contraseña, String numeroTarjeta) {
        this(cedula, nombres, apellidos, edad, correo, usuario, contraseña, numeroTarjeta, null, 0);
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
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
                    comprarTicket();
                }
                case "2" -> {
                    mostrarReservas();
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
     * Menú para la compra de tickets
     */
    private void comprarTicket() {
        ArrayList<String> lugares = new ArrayList<>();
        for (Vuelo vuelo : AirEquador.vuelos) {
            String lugar = vuelo.getItinerario().getOrigen();
            if (!lugares.contains(lugar)) {
                lugares.add(lugar);
            }
            lugar = vuelo.getItinerario().getDestino();
            if (!lugares.contains(lugar)) {
                lugares.add(lugar);
            }
        }

        int i;

        System.out.println("\n-------ORIGEN--------\n");
        i = 0;
        for (String lugar : lugares) {
            i++;
            System.out.println("" + i + ". " + lugar);
        }

        System.out.print("Elige el punto de partida: ");
        Integer nOrigen = Validador.entero(scanner.nextLine());
        while (!(nOrigen != null && nOrigen >= 1 && nOrigen <= lugares.size())) {
            System.out.println("\nERROR");
            System.out.print("\nElige el punto de partida: ");
            nOrigen = Validador.entero(scanner.nextLine());
        }

        System.out.println("\n-------DESTINO--------\n");
        i = 0;
        for (String lugar : lugares) {
            i++;
            System.out.println("" + i + ". " + lugar);
        }

        System.out.print("Elige el punto de destino: ");
        Integer nDestino = Validador.entero(scanner.nextLine());
        while (!(nDestino != null && nDestino >= 1 && nDestino <= lugares.size() && !nOrigen.equals(nDestino))) {
            System.out.println("\nERROR");
            System.out.print("\nElige el punto de destino: ");
            nDestino = Validador.entero(scanner.nextLine());
        }

        System.out.print("\nFecha de salida: ");
        String fechaSalida = scanner.nextLine();
        while (!(Validador.esFecha(fechaSalida))) {
            System.out.println("\nERROR");
            System.out.print("\nFecha de salida: ");
            fechaSalida = scanner.nextLine();
        }

        System.out.print("\nFecha de retorno: ");
        String fechaRetorno = scanner.nextLine();
        while (!(Validador.esFecha(fechaRetorno))) {
            System.out.println("\nERROR");
            System.out.print("\nFecha de retorno: ");
            fechaRetorno = scanner.nextLine();
        }

        String lugarIda = lugares.get(nOrigen - 1);
        String lugarRetorno = lugares.get(nDestino - 1);

        ArrayList<Vuelo> vuelosIda = new ArrayList<>();
        ArrayList<Vuelo> vuelosRetorno = new ArrayList<>();

        for (Vuelo vuelo : AirEquador.vuelos) {
            // Filtrar vuelos de IDA
            if (vuelo.getItinerario().getOrigen().equals(lugarIda)
                    && vuelo.getItinerario().getDestino().equals(lugarRetorno)
                    && (Validador.sonFechasIguales(fechaSalida, vuelo.getFechaSalida())
                    || Validador.sonFechasIguales(fechaSalida, vuelo.getFechaLlegada()))) {
                vuelosIda.add(vuelo);
            }

            // Filtrar vuelos de RETORNO
            if (vuelo.getItinerario().getOrigen().equals(lugarRetorno)
                    && vuelo.getItinerario().getDestino().equals(lugarIda)
                    && (Validador.sonFechasIguales(fechaRetorno, vuelo.getFechaSalida())
                    || Validador.sonFechasIguales(fechaRetorno, vuelo.getFechaLlegada()))) {
                vuelosRetorno.add(vuelo);
            }
        }

        if (vuelosIda.isEmpty()) {
            System.out.println("\nNO HAY VUELOS DISPONIBLES DE IDA");
            return;
        }
        if (vuelosRetorno.isEmpty()) {
            System.out.println("\nNO HAY VUELOS DISPONIBLES DE RETORNO");
            return;
        }

        System.out.println("\n-----------------Vuelos Disponibles IDA-----------------");
        i = 0;
        for (Vuelo vuelo : vuelosIda) {
            i++;
            System.out.println("\n------------ " + i + " ---------------");
            System.out.println(vuelo);
        }

        System.out.print("\nElije el vuelo de ida: ");
        Integer nVueloIda = Validador.entero(scanner.nextLine());
        while (!(nVueloIda != null && nVueloIda >= 1 && nVueloIda <= vuelosIda.size())) {
            System.out.println("\nERROR");
            System.out.print("\nElije el vuelo de ida: ");
            nVueloIda = Validador.entero(scanner.nextLine());
        }

        Tarifa tarifaIda = seleccionarTarifa();

        System.out.println("\n-----------------Vuelos Disponibles RETORNO-----------------");
        i = 0;
        for (Vuelo vuelo : vuelosRetorno) {
            i++;
            System.out.println("\n------------ " + i + " ---------------");
            System.out.println(vuelo);
        }

        System.out.print("\nElije el vuelo de retorno: ");
        Integer nVueloRetorno = Validador.entero(scanner.nextLine());
        while (!(nVueloRetorno != null && nVueloRetorno >= 1 && nVueloRetorno <= vuelosIda.size())) {
            System.out.println("\nERROR");
            System.out.print("\nElije el vuelo de retorno: ");
            nVueloRetorno = Validador.entero(scanner.nextLine());
        }

        Tarifa tarifaRetorno = seleccionarTarifa();

        Vuelo vueloIda = vuelosIda.get(nVueloIda - 1);
        Vuelo vueloRetorno = vuelosRetorno.get(nVueloRetorno - 1);

        float subtotal = vueloIda.getPrecio();
        subtotal += vueloRetorno.getPrecio();

        if (tarifaRetorno == Tarifa.PE) {
            subtotal += 60;
        } else if (tarifaRetorno == Tarifa.PB) {
            subtotal += 90;
        }

        if (tarifaIda == Tarifa.PE) {
            subtotal += 60;
        } else if (tarifaIda == Tarifa.PB) {
            subtotal += 90;
        }

        System.out.println("\n---------- SUBTOTAL ---------------");
        System.out.println("El subtotal de tu vuelo es: " + subtotal);

        System.out.print("\n¿Deseas continuar? (s/n): ");
        char continuar = (scanner.nextLine() + " ").toLowerCase().charAt(0);
        while (!(continuar == 's' || continuar == 'n')) {
            System.out.println("\nERROR");
            System.out.print("\n¿Deseas continuar? (s/n): ");
            continuar = (scanner.nextLine() + " ").toLowerCase().charAt(0);
        }

        if (continuar == 'n') {
            return;
        }

        System.out.println("\n----------------------ASIENTOS-------------------------");

        Asiento asientoIda = vueloIda.getAvion().getAsientos().get(random.nextInt(vueloIda.getAvion().getAsientos().size()));
        Asiento asientoRetorno = vueloRetorno.getAvion().getAsientos().get(random.nextInt(vueloRetorno.getAvion().getAsientos().size()));

        System.out.println("Para tu vuelo de ida " + vueloIda.getCodigo() + " se te ha asignado el asiento: " + asientoIda.getNumero());
        System.out.println("Para tu vuelo de retorno " + vueloRetorno.getCodigo() + " se te ha asignado el asiento: " + asientoRetorno.getNumero());

        System.out.println("\n-----------DATOS CLIENTE-------------");
        System.out.println("\nCompleta los datos del pasajero:");
        System.out.println("Nombres: " + getNombres());
        System.out.println("Apellidos: " + getApellidos());
        System.out.println("Cédula: " + getCedula());
        System.out.println("Correo: " + getCorreo());
        System.out.print("Fecha de nacimiento: ");

        String fechaNacimiento = scanner.nextLine();
        while (!(Validador.esFecha(fechaNacimiento))) {
            System.out.println("\nERROR");
            System.out.print("\nFecha de nacimiento: ");
            fechaNacimiento = scanner.nextLine();
        }

        System.out.println("\n¿Desea guardar los datos del pasajero y continuar al pago? (s/n)");
        continuar = (scanner.nextLine() + " ").toLowerCase().charAt(0);
        while (!(continuar == 's' || continuar == 'n')) {
            System.out.println("\nERROR");
            System.out.println("\n¿Desea guardar los datos del pasajero y continuar al pago? (s/n)");
            continuar = (scanner.nextLine() + " ").toLowerCase().charAt(0);
        }

        if (continuar == 'n') {
            return;
        }

        System.out.println("\n--------------PAGO------------------");

        System.out.println("\nDescripción: ");

        System.out.println("\nSubtotal: " + subtotal);

        int porcentajeDescuento = 0;
        if (tipoVip == TipoVip.GOLDEN_PASS) {
            porcentajeDescuento = 20;
        } else if (tipoVip == TipoVip.PLATINUM_PASS) {
            porcentajeDescuento = 30;
        }

        subtotal *= (100 - porcentajeDescuento) / 100.0f;

        float iva = subtotal * 0.12f;

        float total = subtotal + iva;

        if (porcentajeDescuento > 0) {
            System.out.println("Descuento: " + porcentajeDescuento);
        }
        System.out.println("IVA: " + iva);
        System.out.println("TOTAL A PAGAR: " + total);

        ModoPago modoPago = seleccionarFormaDePago();

        System.out.println("\n¿Está seguro de pagar el vuelo? (s/n)");
        continuar = (scanner.nextLine() + " ").toLowerCase().charAt(0);
        while (!(continuar == 's' || continuar == 'n')) {
            System.out.println("\nERROR");
            System.out.println("\n¿Está seguro de pagar el vuelo? (s/n)");
            continuar = (scanner.nextLine() + " ").toLowerCase().charAt(0);
        }

        if (continuar == 'n') {
            return;
        }
        
        ReservaVuelo reservaVueloIda = new ReservaVuelo(Generador.tokenAleatorio(), vueloIda, TipoVuelo.IDA, tarifaIda, asientoIda);
        ReservaVuelo reservaVueloRetorno = new ReservaVuelo(Generador.tokenAleatorio(), vueloRetorno, TipoVuelo.VUELTA, tarifaRetorno, asientoRetorno);
        
        reservaVueloIda.agregarAlFichero();
        reservaVueloRetorno.agregarAlFichero();

        String codigoReserva = Generador.tokenAleatorio();
        System.out.println("\nHas comprado tu vuelo. El código de tu reserva es: " + codigoReserva);
        Reserva reservaIda = new Reserva(codigoReserva, vueloIda, this, fechaSalida, vueloIda.getPrecio());
        Reserva reservaRetorno = new Reserva(codigoReserva, vueloRetorno, this, fechaRetorno, vueloRetorno.getPrecio());

        reservaIda.agregarAlFichero();
        reservaRetorno.agregarAlFichero();

        Pago pago = new Pago(Generador.tokenAleatorio(), codigoReserva, subtotal * 1.12f, modoPago);
        pago.agregarAlFichero();
    }

    private Tarifa seleccionarTarifa() {
        System.out.println("\nTARIFAS\n");

        int i = 0;
        for (Tarifa tarifa : Tarifa.values()) {
            i++;
            System.out.print("" + i + ") ");
            switch (tarifa) {
                case E ->
                    System.out.println("Economy (+0)");
                case PE ->
                    System.out.println("Premium Economy (+60)");
                case PB ->
                    System.out.println("Business Economy (+90)");
            }
        }

        System.out.print("\nElije la tarifa para tu vuelo: ");
        Integer nTarifa = Validador.entero(scanner.nextLine());
        while (!(nTarifa != null && nTarifa >= 1 && nTarifa <= Tarifa.values().length)) {
            System.out.println("\nERROR");
            System.out.print("\nElije la tarifa para tu vuelo: ");
            nTarifa = Validador.entero(scanner.nextLine());
        }

        return Tarifa.values()[nTarifa - 1];
    }

    private ModoPago seleccionarFormaDePago() {
        System.out.println("\nFormas de Pago:");

        if (tipoVip == null) {
            System.out.println("Se ha seleccionado Tarjeta de Crédito");
            return ModoPago.TC;
        }

        int i = 0;
        for (ModoPago modoPago : ModoPago.values()) {
            i++;
            System.out.print(i + ". ");
            switch (modoPago) {

                case TC ->
                    System.out.println("Tarjeta de Crédito");
                case M ->
                    System.out.println("Millas");
            }
        }

        System.out.print("\nElije tu forma de pago: ");
        Integer nModoPago = Validador.entero(scanner.nextLine());
        while (!(nModoPago != null && nModoPago >= 1 && nModoPago <= ModoPago.values().length)) {
            System.out.println("\nERROR");
            System.out.print("\nElije tu forma de pago: ");
            nModoPago = Validador.entero(scanner.nextLine());
        }

        return ModoPago.values()[nModoPago - 1];
    }

    @Override
    public void mostrarReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();

        for (Reserva reserva : AirEquador.reservas) {
            if (reserva.getCliente().getCedula().equals(getCedula())) {
                reservas.add(reserva);
            }
        }

        if (reservas.isEmpty()) {
            System.out.println("\nNO HA REALIZADO RESERVAS");
            return;
        }

        System.out.println("\nReservas Realizadas:");

        for (Reserva reserva : reservas) {
            System.out.println("\n" + reserva);
            System.out.println("\n------------------------");
        }
    }

    @Override
    public String toString() {
        return "Nombre: " + getNombres() + " " + getApellidos() + "\n"
                + "Tipo: CLIENTE " + (tipoVip == null ? "ESTÁNDAR" : "VIP " + tipoVip.toString().replace("_", " ")) + "\n"
                + "Cédula: " + getCedula();
    }

}
