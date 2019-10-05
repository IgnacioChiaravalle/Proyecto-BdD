package Administrador;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ConfiguradorDeFondo.BackgroundSetter;
import Inicio.ConsultorBdD;

public class PantallaAdministrador {
	private JFrame frame;
	private Container panel;
	private int frameHeight, frameWidth;
	private JTextArea textArea;
	private JLabel ingreseSentencias, tablas;
	private JButton limpiarTextArea, aceptarSentencia;
	private JList<String> listaTablas;
	private ConsultorBdD consultor;
	
	public PantallaAdministrador(JFrame f, Container p, ConsultorBdD c) {
		frame = f; panel = p; consultor = c;
		frameHeight = frame.getHeight();
		frameWidth = frame.getWidth();
		BackgroundSetter backgroundSetter = new BackgroundSetter();
		backgroundSetter.configurarFrame(frame, "./Fondo de Administrador.jpg");
		panel = frame.getContentPane();
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panel.setLayout(null);
		
		setearElementosPantalla();
	}
	
	private void setearElementosPantalla() {
		ingreseSentencias = new JLabel("Escriba aqu� las sentencias SQL que desee ejecutar:");
		ingreseSentencias.setBounds((int) (frameWidth * 0.05), (int) (frameHeight * 0.03), frameWidth, (int) (frameHeight * 0.035));
		fontSizer(ingreseSentencias, true, 0.0013);
		panel.add(ingreseSentencias);
		
		setearTextArea();
		
		limpiarTextArea = new JButton("Limpiar Texto");
		setearBoton(limpiarTextArea, 0.1, 0.25, 0.0035);
		limpiarTextArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { textArea.setText(""); }
		});
		
		aceptarSentencia = new JButton("Aceptar la Sentencia");
		setearBoton(aceptarSentencia, 0.5, 0.4, 0.0025);
		aceptarSentencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { aceptada(); }
		});
		
		tablas = new JLabel("Tablas en la base Vuelos:");
		tablas.setBounds((int) (frameWidth * 0.6), (int) (frameHeight * 0.3), (int) (frameWidth * 0.35), (int) (frameHeight * 0.035));
		fontSizer(tablas, true, 0.0035);
		panel.add(tablas);
		
		listaTablas = new JList<String>(consultor.obtenerTablas());
		JScrollPane scrollPaneLista = new JScrollPane(listaTablas);
		listaTablas.setBounds((int) (frameWidth * 0.6), (int) (frameHeight * 0.35), (int) (frameWidth * 0.35), (int) (frameHeight * 0.55));
		scrollPaneLista.setBounds(listaTablas.getBounds());
		fontSizer(listaTablas, false, 0.00025);
		crearMouseListener();
		panel.add(scrollPaneLista);
		
	}
	
	private void setearTextArea() {
		textArea = new JTextArea();
		JScrollPane scrollPaneArea = new JScrollPane(textArea);
		textArea.setBounds((int) (frameWidth * 0.05), (int) (frameHeight * 0.1), (int) (frameWidth * 0.9), (int) (frameHeight * 0.1));
		textArea.setMargin(new Insets(5,5,5,5));
		scrollPaneArea.setBounds(textArea.getBounds());
		fontSizer(textArea, false, 0.0005);
		panel.add(scrollPaneArea);
	}
	
	private void setearBoton(JButton boton, double x, double ancho, double letra) {
		boton.setEnabled(true);
		boton.setBounds((int) (frameWidth * x), (int) (frameHeight * 0.22), (int) (frameWidth * ancho), (int) (frameHeight * 0.055));
		fontSizer(boton, true, letra);
		panel.add(boton);
	}
	
	private void fontSizer(Component c, boolean negrita, double valor) {
		int fontSize = (int) (c.getHeight() * c.getWidth() * valor);
		c.setFont(new Font("Segoe UI Symbol", (negrita ? Font.BOLD : Font.LAYOUT_LEFT_TO_RIGHT), fontSize));
	}
	
	private void aceptada() {
		String resultadoConsulta = consultor.comprobarSentencia(textArea.getText());
		if (resultadoConsulta.equals("")) {
			
		}
		else
			textArea.setText(textArea.getText() + "\n\nError en la sentencia:\n" + resultadoConsulta); //El error obtenido mediante el consultor.
			//No s� c�mo se hacen las consultas ni qu� devuelven. Si devuelven un String, entonces puedo usar directamente ese String
			//(decidiendo con alg�n criterio si es un error o no.)
	}
	
	private void crearMouseListener() {
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) {
					String selectedItem = listaTablas.getSelectedValue();
					JList<String> lista = new JList<String>(consultor.obtenerAtributos(selectedItem));
					JScrollPane scrollPaneOptionPane = new JScrollPane(lista);
					JOptionPane.showMessageDialog(null, scrollPaneOptionPane, "Atributos de la tabla " + selectedItem, JOptionPane.PLAIN_MESSAGE);
				}
			}
		};
		listaTablas.addMouseListener(mouseListener);
	}
}
