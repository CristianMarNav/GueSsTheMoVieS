//Main.java
import java.io.*;
import java.util.*;

/**
 * Clase principal para ejecutar el juego "Guess the Movie".
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("¡Bienvenido a Guess the Movie!");
        System.out.println("¡Tienes que adivinar la película seleccionada!");
        System.out.println("Solo tienes 10 intentos. Cada vez que te equivoques, perderás un intento.");
        System.out.println("🍀🍀🍀¡Buena suerte!\n");

        Game juego = new Game("peliculas.txt", "ranking.bin");
        juego.iniciar();
    }
}
