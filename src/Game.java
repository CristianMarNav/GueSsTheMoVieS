import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Clase que gestionara la lógica del juego "GueSs the MoVieS"
 */
class Game {
    private final String archivosPeliculas;
    private List<String> peliculas;

    /**
     * Contructor de la clase Game.
     *
     * @param archivoPeliculas "Ruta del archivo con la lista de películas".
     */

    public Game(String archivoPeliculas) {
        this.archivosPeliculas = archivoPeliculas;
        this.peliculas = new ArrayList<>();
        cargarPeliculas();
    }

    /**
     * Carga las películas desde el archivo especificado.
     */
    private void cargarPeliculas() {
        File archivo = new File(archivosPeliculas);
        if (!archivo.exists()) {

            System.out.println("El archov de películas no existe: " + archivosPeliculas);
        }
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                peliculas.add(scanner.nextLine().toLowerCase());
            }
        } catch (FileNotFoundException e) {

            System.out.println("Error al abrir el archivo de películas: " + e.getMessage());
        }
    }
}

