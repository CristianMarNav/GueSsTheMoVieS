# 🎬 Guess the Movie 🎥

**GueSs The MoVieS** es un juego interactivo desarrollado en **Java** en el que los jugadores deben adivinar el título de una película utilizando letras. 

## 📜 Características del Juego

1. **Mecánicas de Juego**:
   - Adivina el título de una película letra por letra o intenta adivinar el título completo.
   - Gana puntos por cada letra correcta (+10) y por acertar el título completo (+20).
   - Pierde puntos por errores en las letras (-10) y en el título completo (-20).

2. **Gestión de Intentos**:
   - Los jugadores tienen un máximo de 10 intentos.
   - Cada intento fallido reduce la cantidad de intentos disponibles.

3. **Ranking de Jugadores**:
   - Guarda los 5 mejores puntajes en un archivo binario (`ranking.bin`).
   - El ranking se actualiza automáticamente al final de cada partida.
   - Solo se permite ingresar un nickname único.

4. **Diseño Modular**:
   - Uso de clases separadas para la gestión de la lógica del juego (`Game`) y la representación de jugadores (`Player`).
   - Métodos bien definidos y documentados con **JavaDocs**.

5. **Interactividad Mejorada**:
   - Mensajes al usuario con colores y emojis para una experiencia más atractiva.

## 🛠️ Requisitos Técnicos

- **Archivos Necesarios**:
  - `peliculas.txt`: Lista de películas para adivinar.
  - `ranking.bin`: Archivo binario donde se almacena el ranking.
- **Librerías Utilizadas**:
  - `java.io.*`: Para la lectura, escritura y serialización de archivos.
  - `java.util.*`: Para listas, conjuntos, Scanner y selección aleatoria.

## 🧩 Estructura del Proyecto

- **Main.java**: Clase principal que inicia el juego.
- **Game.java**: Clase que gestiona la lógica del juego.
- **Player.java**: Clase que representa a un jugador y su puntuación.

## 🚀 Ejecución del Juego

1. Asegúrate de tener el archivo `peliculas.txt` en el mismo directorio que el proyecto. Este archivo debe contener una lista de títulos de películas, una por línea.
2. Compila y ejecuta el archivo `Main.java` para iniciar el juego.
3. Sigue las instrucciones en pantalla para jugar, adivinar títulos y competir en el ranking.

## 📚 Documentación Adicional

El proyecto incluye **JavaDocs** que describen cada clase, método e importación utilizada. Esto facilita la comprensión del código y su mantenimiento.

## 👥 Autor

**CristianMarNav**  
Estudiante en desarrollo de aplicaciones multiplataforma (DAM).

---

¡Gracias por jugar a **Guess the Movie**! 🎉 ¡Que tengas suerte adivinando! 🍀
