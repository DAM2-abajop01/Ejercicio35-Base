import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa el listener de los botones del Buscaminas. De alguna
 * manera tendr� que poder acceder a la ventana principal. Se puede lograr
 * pasando en el constructor la referencia a la ventana. Recuerda que desde la
 * ventana, se puede acceder a la variable de tipo ControlJuego
 * 
 * @author jesusredondogarcia
 * @author Alejandro Bajo P�rez
 **
 */
public class ActionBoton implements ActionListener {
	private VentanaPrincipal ventanaPrincipal;
	private int i;
	private int j;

	public ActionBoton(VentanaPrincipal ventanaPrincipal, int i, int j) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.i = i;
		this.j = j;
	}

	/**
	 * Acci�n que ocurrir� cuando pulsamos uno de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Destapo el bot�n
		ventanaPrincipal.mostrarNumMinasAlrededor(i, j);
		// Compruebo si era una mina o si el usuario a ganado el juego
		if (!ventanaPrincipal.getJuego().abrirCasilla(i, j)) {
			ventanaPrincipal.mostrarFinJuego(true);
		} else if (ventanaPrincipal.getJuego().esFinJuego()) {
			ventanaPrincipal.mostrarFinJuego(false);
		}
		// Actualizo la puntuaci�n
		ventanaPrincipal.actualizarPuntuacion();
	}
}