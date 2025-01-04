import java.io.*;
import java.util.*;

/**
 * Clase que gestionara la lógica del juego "GueSs the MoVieS"
 */
class Game {
    private final String archivosPeliculas;
    private final String archivoRanking;
    private List<String> peliculas;
    private List<Player> ranking;

    private static final int PUNTOS_POR_LETRA = 10;
    private static final int PUNTOS_POR_PALABRA = 20;
    private static final int MAX_RANKING = 5;

    /**
     * Contructor de la clase Game.
     *
     * @param archivoPeliculas "Ruta del archivo con la lista de películas".
     */

    public Game(String archivoPeliculas, String archivoRanking) {
        this.archivosPeliculas = archivoPeliculas;
        this.archivoRanking = archivoRanking;
        this.peliculas = new ArrayList<>();
        this.ranking = new ArrayList<>();
        cargarPeliculas();
        cargarRanking();
    }

    /**
     * Carga las películas desde el archivo especificado.
     */
    private void cargarPeliculas() {
        File archivo = new File(archivosPeliculas);
        if (!archivo.exists()) {

            System.out.println("El archivo de películas no existe: " + archivosPeliculas);
            System.exit(1);
        }
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                peliculas.add(scanner.nextLine().toLowerCase());
            }
        } catch (FileNotFoundException e) {

            System.out.println("Error al abrir el archivo de películas: " + e.getMessage());
        }
    }

    /**
     * Método que carga el ranking desde el archivo binario.
     * Si el archivo no existe, incializa un nuevo ranking vacío.
     */
    private void cargarRanking() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoRanking))) {
            ranking = (List<Player>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de ranking. Se creará uno nuevo");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el ranking: " + e.getMessage());
        }
    }

    private void guardarRanking() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoRanking))) {
            oos.writeObject(ranking);
        } catch (IOException e) {
            System.err.println("Error al guardar el ranking: " + e.getMessage());
        }
    }

    public void iniciar() {
        if (peliculas.isEmpty()) {
            System.err.println("No hay películas disponibles para jugar.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String peliculaSeleccionada = peliculas.get(random.nextInt(peliculas.size()));
        StringBuilder peliculaOculta = new StringBuilder();
        for (char c : peliculaSeleccionada.toCharArray()) {
            if (Character.isLetter(c)) {
                peliculaOculta.append('*');
            } else {
                peliculaOculta.append(c);
            }
        }

        int intentos = 10;
        int puntuacion = 0;
        Set<Character> letrasAdivinadas = new HashSet<>();
        Set<Character> letrasErroneas = new HashSet<>();

    }
}

