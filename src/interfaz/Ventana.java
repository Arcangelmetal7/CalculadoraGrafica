package interfaz;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Ventana extends JFrame {

	/**
	 * La clase solo crea el marco donde se añade la lamina.
	 */
	private static final long serialVersionUID = 1L;

	Toolkit herramientas = Toolkit.getDefaultToolkit();
	Image icono = herramientas.getImage("ico.png");

	public Ventana(String titulo) {
		setTitle(titulo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 490);
		setLocationRelativeTo(null);

		setIconImage(icono);
		Lamina laminaArriba = new Lamina();
		add(laminaArriba);
		setVisible(true);
	}

}
