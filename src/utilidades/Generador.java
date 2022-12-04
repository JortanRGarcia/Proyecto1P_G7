package utilidades;

public abstract class Generador {

    /**
     * Genera un token aleatorio de 6 caracteres
     * @return 
     */
    public static String tokenAleatorio() {
        String token = "";

        for (int i = 0; i < 6; i++) {
            token += (char)(int) Math.floor(Math.random() * (122
                    - 97) + 97);
        }

        return token;
    }
    
 
}
