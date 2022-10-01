package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Lamina extends JPanel {
	/**
	 * Esta clase ademas de la lamina contiene toda la parte logica del programa.
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pantalla;
	private JPanel botones;
	private boolean inicio;
	private double resultante;// Aca se va almacenando el resultado.
	private String ultimaOperacion;
	private final double Pi = Math.PI;

	public Lamina() {
		setLayout(new BorderLayout());
		// setBackground(Color.BLACK);

		inicio = true;// Sirve para borrar el 0 del display que aparece al principio, tambien vacia el
						// display al presionar un boton de operacion matematica.
		ultimaOperacion = "=";// Almacena el valor de el ultimo boton presionado si es de una operacion
								// matematica.

		// Instanciado del campo de texto que funciona como display y se lo coloca en la
		// parte north del BorderLayout.
		pantalla = new JTextField("0");
		pantalla.setEnabled(true);
		pantalla.setEditable(false);
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		Font fuente = new Font("Arial", Font.BOLD, 60);

		pantalla.setFont(fuente);
		pantalla.setBackground(Color.BLACK);
		pantalla.setForeground(Color.WHITE);
		add(pantalla, BorderLayout.NORTH);

		// instancia del panel de botones y la agrega a la parte center del BorderLayout
		// de la lamina principal.
		botones = new JPanel();
		botones.setLayout(new GridLayout(5, 4, 5, 5));
		botones.setBackground(Color.BLACK);

		add(botones, BorderLayout.CENTER);

		// Instancias de listeners.
		ActionListener botonListener = new Escritor();
		ActionListener botonListener2 = new SegundaAccion();

		// Colocando botones. El metodo les coloca tanto valor y objeto listener, se los
		// pasamos por parametro.
		ponerBoton("7", botonListener);
		ponerBoton("8", botonListener);
		ponerBoton("9", botonListener);
		ponerBoton("+", botonListener2);

		ponerBoton("4", botonListener);
		ponerBoton("5", botonListener);
		ponerBoton("6", botonListener);
		ponerBoton("-", botonListener2);

		ponerBoton("1", botonListener);
		ponerBoton("2", botonListener);
		ponerBoton("3", botonListener);
		ponerBoton("*", botonListener2);

		ponerBoton("0", botonListener);
		ponerBoton(".", botonListener);
		ponerBoton("/", botonListener2);
		ponerBoton("=", botonListener2);

		ponerBoton("C", botonListener2);
		ponerBoton("π", botonListener2);
		ponerBoton("x²", botonListener2);
		ponerBoton("√", botonListener2);

	}

	// coloca botones.
	public void ponerBoton(String valor, ActionListener a) {
		JButton boton = new JButton(valor);
		boton.setBorder(new BordeRedondo(50));
		// boton.setOpaque(false);
		if (valor.equalsIgnoreCase("=")) {
			boton.setBackground(Color.gray);

		}
		boton.addActionListener(a);

		botones.add(boton);

	}

	// Clase de el primer listener.
	private class Escritor implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String captura = e.getActionCommand();
			if (inicio) {
				pantalla.setText("");
				inicio = false;
			}
			pantalla.setText(pantalla.getText() + captura);

		}

	}

	// Clase del listener para los botones de operaciones.
	private class SegundaAccion implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String operacion = e.getActionCommand();

			calculo(Double.parseDouble(pantalla.getText()));
			ultimaOperacion = operacion;// Captura el valor del boton pulsado cuando es una operacion matematica y lo
										// almacena en la variable ultimaOperacion.
			inicio = true;

		}

		public void calculo(double x) {// Esto realiza el calculo matematico.

			if (ultimaOperacion.equals("+")) {
				resultante += x;
			} else if (ultimaOperacion.equals("-")) {
				resultante -= x;
			} else if (ultimaOperacion.equals("*")) {
				resultante *= x;
			} else if (ultimaOperacion.equals("/")) {
				resultante /= x;
			} else if (ultimaOperacion.equals("C")) {
				resultante = 0;
			} else if (ultimaOperacion.equals("π")) {
				resultante *= Pi;
			} else if (ultimaOperacion.equals("√")) {
				resultante = Math.sqrt(resultante);
			} else if (ultimaOperacion.equals("x²")) {
				resultante = Math.pow(resultante, 2);

			} else if (ultimaOperacion.equals("=")) {
				resultante = x;
			}
			pantalla.setText("" + resultante);// Esto imprime el resultado en el display.

		}

	}

	// Esto redondea los botones.
	private class BordeRedondo implements Border {

		private int radio;

		BordeRedondo(int radio) {
			this.radio = radio;
		}

		@Override
		public Insets getBorderInsets(Component c) {
			// TODO Auto-generated method stub
			return new Insets(this.radio + 1, this.radio + 1, this.radio + 2, this.radio);
		}

		@Override
		public boolean isBorderOpaque() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int widht, int height) {
			// TODO Auto-generated method stub
			g.drawRoundRect(x, y, widht - 1, height - 1, radio, radio);

		}

	}

}
