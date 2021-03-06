import java.util.Random;

/**
 * Clase gestora del tablero de juego. Guarda una matriz de enteros representado
 * el tablero. Si hay una mina en una posici�n guarda el n�mero -1 Si no hay una
 * mina, se guarda cu�ntas minas hay alrededor. Almacena la puntuaci�n de la
 * partida
 * 
 * @author jesusredondogarcia
 * @author Alejandro Bajo P�rez
 */
public class ControlJuego {

	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int[][] tablero;
	private int puntuacion;

	public ControlJuego() {
		// Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];

		// Inicializamos una nueva partida
		inicializarPartida();
	}

	/**
	 * M�todo para generar un nuevo tablero de partida:
	 * 
	 * @pre: La estructura tablero debe existir.
	 * @post: Al final el tablero se habr� inicializado con tantas minas como marque
	 *        la variable MINAS_INICIALES. El resto de posiciones que no son minas
	 *        guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida() {
		Random rd = new Random();
		int filas;
		int columnas;

		for (int i = 0; i < MINAS_INICIALES; i++) {
			filas = rd.nextInt(LADO_TABLERO);
			columnas = rd.nextInt(LADO_TABLERO);
			if (tablero[filas][columnas] != MINA) {
				tablero[filas][columnas] = MINA;
			} else {
				i--;
			}
		}

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA) {
					tablero[i][j] = calculoMinasAdjuntas(i, j);
				}
			}
		}
		depurarTablero();
	}

	/**
	 * C�lculo de las minas adjuntas: Para calcular el n�mero de minas tenemos que
	 * tener en cuenta que no nos salimos nunca del tablero. Por lo tanto, como
	 * mucho la i y la j valdr�n LADO_TABLERO-1. Por lo tanto, como mucho la i y la
	 * j valdr�n como poco 0.
	 * 
	 * @param i:
	 *            posici�n verticalmente de la casilla a rellenar
	 * @param j:
	 *            posici�n horizontalmente de la casilla a rellenar
	 * @return : El n�mero de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int i, int j) {
		int minas = 0;
		for (int k = Integer.max(0, (i - 1)); k <= Integer.min(LADO_TABLERO - 1, (i + 1)); k++) {
			for (int k2 = Integer.max(0, (j - 1)); k2 <= Integer.min(LADO_TABLERO - 1, (j + 1)); k2++) {
				if (tablero[k][k2] == MINA) {
					minas++;
				}
			}
		}
		return minas;
	}

	/**
	 * M�todo que nos permite
	 * 
	 * @pre : La casilla nunca debe haber sido abierta antes, no es controlado por
	 *      el GestorJuego. Por lo tanto siempre sumaremos puntos
	 * @param i:
	 *            posici�n verticalmente de la casilla a abrir
	 * @param j:
	 *            posici�n horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j) {
		if (tablero[i][j] == MINA) {
			return false;
		} else {
			puntuacion++;
			return true;
		}
	}

	/**
	 * M�todo que checkea si se ha terminado el juego porque se han abierto todas
	 * las casillas.
	 * 
	 * @return Devuelve verdadero si se han abierto todas las celdas que no son
	 *         minas.
	 **/
	public boolean esFinJuego() {
		return (LADO_TABLERO * LADO_TABLERO) - MINAS_INICIALES == puntuacion;
	}

	/**
	 * M�todo que pinta por pantalla toda la información del tablero, se utiliza
	 * para depurar
	 */
	public void depurarTablero() {
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuaci�n: " + puntuacion);
	}

	/**
	 * M�todo que se utiliza para obtener las minas que hay alrededor de una celda
	 * 
	 * @pre : El tablero tiene que estar ya inicializado, por lo tanto no hace falta
	 *      calcularlo, s�mplemente consultarlo
	 * @param i
	 *            : posici�n vertical de la celda.
	 * @param j
	 *            : posici�n horizontal de la cela.
	 * @return Un entero que representa el n�mero de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		return tablero[i][j];
	}

	/**
	 * M�todo que devuelve la puntuaci�n actual
	 * 
	 * @return Un entero con la puntuaci�n actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
}