package ConfiguradorDeFondo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sizer {
	private int frameHeight, frameWidth;
	
	public Sizer() {}
	
	public void configurarFrame (JFrame frame, String rutaImagen) {
		JLabel fondo = new JLabel();
		ImageIcon iconoOriginal = new ImageIcon(rutaImagen);//new ImageIcon(this.getClass().getResource(rutaImagen));
		ImageIcon iconoEscala = new ImageIcon(iconoOriginal.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), java.awt.Image.SCALE_DEFAULT));
		fondo.setIcon(iconoEscala);
		frame.setContentPane(fondo);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		frameHeight = frame.getHeight();
		frameWidth = frame.getWidth();
	}
	
	public void boundsSetter(Component c, double x, double y, double width, double height) {
		c.setBounds((int) (frameWidth * x), (int) (frameHeight * y), (int) (frameWidth * width), (int) (frameHeight * height));
	}
	
	public void fontSizer(Component c, boolean negrita, double valor) {
		int fontSize = (int) (c.getHeight() * c.getWidth() * valor);
		c.setFont(new Font("Segoe UI Symbol", (negrita ? Font.BOLD : Font.LAYOUT_LEFT_TO_RIGHT), fontSize));
	}
}
