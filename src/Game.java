// Game.java

import java.io.*;   // Para la lectura y escritura de archivos.
import java.util.*; // Para el uso de estructuras de datos como listas y conjuntos, y clases como Scanner y Random.

/**
 * Clase que gestiona la lógica del juego "Guess the Movie".
 */
class Game {
    private final String archivoPeliculas;
    private final String archivoRanking;
    private List<String> peliculas;
    private List<Player> ranking;

    private static final int PUNTOS_POR_LETRA = 10;
    private static final int PUNTOS_POR_PALABRA = 20;
    private static final int MAX_RANKING = 5;

    /**
     * Constructor de la clase Game.
     * Inicializa las rutas de archivos, listas y carga los datos necesarios.
     *
     * @param archivoPeliculas Ruta del archivo con la lista de películas.
     * @param archivoRanking Ruta del archivo binario donde se almacena el ranking.
     */
    public Game(String archivoPeliculas, String archivoRanking) {
        this.archivoPeliculas = archivoPeliculas;
        this.archivoRanking = archivoRanking;
        this.peliculas = new ArrayList<>();
        this.ranking = new ArrayList<>();
        cargarPeliculas();
        cargarRanking();
    }

    /**
     * Carga las películas desde el archivo especificado.
     * Si el archivo no existe, finaliza la ejecución del programa.
     */
    private void cargarPeliculas() {
        File archivo = new File(archivoPeliculas);
        if (!archivo.exists()) {
            System.err.println("❌ El archivo de películas no existe: " + archivoPeliculas);
            System.exit(1);
        }
        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                peliculas.add(scanner.nextLine().toLowerCase());
            }
        } catch (FileNotFoundException e) {
            System.err.println("❌ Error al abrir el archivo de películas: " + e.getMessage());
        }
    }

    /**
     * Carga el ranking desde un archivo binario.
     * Si el archivo no existe, inicializa un ranking vacío.
     */
    private void cargarRanking() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoRanking))) {
            ranking = (List<Player>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("⚠️ No se encontró el archivo de ranking. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al cargar el ranking: " + e.getMessage());
        }
    }

    /**
     * Guarda el ranking en un archivo binario.
     * Maneja posibles errores de escritura.
     */
    private void guardarRanking() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoRanking))) {
            oos.writeObject(ranking);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar el ranking: " + e.getMessage());
        }
    }

    /**
     * Método principal para iniciar el juego.
     * Controla la lógica del juego, permite al usuario adivinar letras o el título completo, y administra los intentos.
     */
    public void iniciar() {
        if (peliculas.isEmpty()) {
            System.err.println("❌ No hay películas disponibles para jugar.");
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

        while (intentos > 0 && peliculaOculta.indexOf("*") != -1) {
            System.out.println("🎥 Título: " + peliculaOculta);
            System.out.println("⌛ Intentos restantes: " + intentos);
            System.out.println("❌ Letras incorrectas: " + letrasErroneas);
            System.out.println("☝️ Adivinar una letra\n✌️ Adivinar el título\n👌 Salir");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("🔤 Introduce una letra:");
                    String entrada = scanner.nextLine().toLowerCase();
                    if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                        System.out.println("❌ Entrada inválida. Introduce una letra válida.");
                        continue;
                    }
                    char letra = entrada.charAt(0);
                    if (letrasAdivinadas.contains(letra) || letrasErroneas.contains(letra)) {
                        System.out.println("⚠️ Ya has intentado esta letra.");
                        continue;
                    }
                    if (peliculaSeleccionada.indexOf(letra) != -1) {
                        letrasAdivinadas.add(letra);
                        puntuacion += PUNTOS_POR_LETRA;
                        for (int i = 0; i < peliculaSeleccionada.length(); i++) {
                            if (peliculaSeleccionada.charAt(i) == letra) {
                                peliculaOculta.setCharAt(i, letra);
                            }
                        }
                    } else {
                        letrasErroneas.add(letra);
                        puntuacion -= PUNTOS_POR_LETRA;
                        intentos--;
                    }
                    break;

                case "2":
                    System.out.println("🤔 Introduce el título:");
                    String intentoTitulo = scanner.nextLine().toLowerCase();
                    if (intentoTitulo.equals(peliculaSeleccionada)) {
                        puntuacion += PUNTOS_POR_PALABRA;
                        System.out.println("¡Has ganado! El título era: " + peliculaSeleccionada);
                        finalizarJuego(puntuacion);
                        return;
                    } else {
                        puntuacion -= PUNTOS_POR_PALABRA;
                        System.out.println("❌ Respuesta incorrecta. Has perdido.");
                        finalizarJuego(puntuacion);
                        return;
                    }

                case "3":
                    System.out.println("👋 Saliendo del juego.");
                    finalizarJuego(puntuacion);
                    return;

                default:
                    System.out.println("❌ Opción inválida. Intenta de nuevo.");
            }
        }

        if (peliculaOculta.indexOf("*") == -1) {
            System.out.println("🎉 ¡Felicidades! Has adivinado el título: " + peliculaSeleccionada);
            finalizarJuego(puntuacion);
        } else {
            System.out.println("💔 Se te han acabado los intentos. El título era: " + peliculaSeleccionada);
            finalizarJuego(puntuacion);
        }
    }

    /**
     * Finaliza el juego mostrando la puntuación final y actualizando el ranking si corresponde.
     *
     * @param puntuacion Puntuación final del jugador.
     */
    private void finalizarJuego(int puntuacion) {
        System.out.println("🏁 Puntuación final: " + puntuacion);
        if (puntuacion > 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("✏️ Introduce tu nickname para el ranking:");
            String nickname = scanner.nextLine();

            while (!esNicknameUnico(nickname)) {
                System.out.println("⚠️ El nickname ya existe. Introduce otro:");
                nickname = scanner.nextLine();
            }

            ranking.add(new Player(nickname, puntuacion));
            ranking.sort((p1, p2) -> Integer.compare(p2.getPuntuacion(), p1.getPuntuacion()));
            if (ranking.size() > MAX_RANKING) {
                ranking.remove(ranking.size() - 1);
            }
            guardarRanking();
        }
        System.out.println("🏅 Ranking actual:");
        for (Player jugador : ranking) {
            System.out.println(jugador);
        }
    }

    /**
     * Verifica si un nickname es único en el ranking.
     *
     * @param nickname El nickname a verificar.
     * @return true si el nickname no está en el ranking, false en caso contrario.
     */
    private boolean esNicknameUnico(String nickname) {
        return ranking.stream().noneMatch(player -> player.getNickname().equals(nickname));
    }
}


