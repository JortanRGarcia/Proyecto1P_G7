package utilidades;

public abstract class Validador {

    /**
     * Intenta convertir el String a un número entero
     *
     * @param str El String que contiene un dígito numérico entero
     * @return El número entero que contiene el String, caso contrario retorna
     * {@code null}
     */
    public static Integer entero(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }

    }

    /**
     * Verifica si el String es una fecha en formato dd/mm/yyyy
     *
     * @param str
     * @return {@code true} en caso de ser un formato válido, caso contrario
     * retorna {@code false}
     */
    public static boolean esFecha(String str) {
        try {
            String[] valores = str.split("/");
            if (valores.length != 3) {
                return false;
            }

            int d = Integer.parseInt(valores[0]);
            int m = Integer.parseInt(valores[1]);
            Integer.parseInt(valores[2]);

            if (d <= 0) {
                return false;
            }
            if (d > 31) {
                return false;
            }
            if (m <= 0) {
                return false;
            }
            if (m > 12) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Compara si dos String en formato fecha son iguales
     *
     * @param fecha1
     * @param fecha2
     * @return {@code true} en caso de que las fechas coincidan, caso contrario
     * retorna {@code false}
     */
    public static boolean sonFechasIguales(String fecha1, String fecha2) {

        String[] valores1 = fecha1.split("/");

        int d1 = Integer.parseInt(valores1[0]);
        int m1 = Integer.parseInt(valores1[1]);
        int y1 = Integer.parseInt(valores1[2]);

        String[] valores2 = fecha1.split("/");

        int d2 = Integer.parseInt(valores2[0]);
        int m2 = Integer.parseInt(valores2[1]);
        int y2 = Integer.parseInt(valores2[2]);

        return d1 == d2 && m1 == m2 && y1 == y2;
    }

}
