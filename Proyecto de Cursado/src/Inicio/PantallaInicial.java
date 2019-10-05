package Inicio;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Administrador.PantallaAdministrador;
import ConfiguradorDeFondo.BackgroundSetter;

public class PantallaInicial {
	private static final int Ymax = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	private static final int Xmax = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private JFrame frame;
	private int frameWidth, frameHeight;
	private JButton botonAdmin, botonEmpleado;
	private Container panel;
	private ConsultorBdD consultor;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaInicial window = new PantallaInicial();
					window.frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PantallaInicial() {
		consultor = new ConsultorBdD("localhost:3306");
		setearFondo();
		crearBotones();
	}
	
	private void setearFondo() {
		frame = new JFrame();
		frame.setResizable(false);
		frameWidth = (int) (Xmax*0.4);
		frameHeight = (int) (Ymax*0.7);
		frame.setBounds((int) (Xmax * 0.425), (int) (Ymax * 0.275), frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BackgroundSetter backgroundSetter = new BackgroundSetter();
		backgroundSetter.configurarFrame(frame, "./Fondo de Inicio.jpg");
		panel = frame.getContentPane();
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		panel.setLayout(null);
	}
	
	private void crearBotones() {
		botonAdmin = new JButton("Ingresar como Administrador");
		setearBoton(botonAdmin, 0.2);
		botonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField password = new JPasswordField();
				Object[] cuadroDeIngreso = { "Contraseņa:", password };
				boolean valido = false;
				while (!valido) {
					int option = JOptionPane.showConfirmDialog(null, cuadroDeIngreso, "Acceso del Administrador", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						valido = consultor.chequearAdministrador(password.getText());
						if (!valido)
							JOptionPane.showMessageDialog(null, "La contraseņa ingresada es incorrecta.", "Contraseņa Incorrecta", JOptionPane.ERROR_MESSAGE);
					}
					else
						break;
				}
				
				if (valido) //Pasar a a vista del administrador.
					new PantallaAdministrador(frame, panel, consultor);
			}
		});
		
		botonEmpleado = new JButton("Ingresar como Empleado");
		setearBoton(botonEmpleado, 0.6);
		botonEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField legajo = new JTextField();
				JTextField password = new JPasswordField();
				Object[] cuadrosDeIngreso = {
				    "Legajo del Empleado:", legajo,
				    "Contraseņa:", password
				};
				boolean valido = false;
				while (!valido) {
					int option = JOptionPane.showConfirmDialog(null, cuadrosDeIngreso, "Acceso del Empleado", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						Conexion.iniciarConnection();
						valido = Conexion.iniciarConnection() != null;
					   // valido = consultor.chequearEmpleado(legajo.getText(), password.getText());
					    if (!valido)
							JOptionPane.showMessageDialog(null, "Los datos ingresados son incorrectos.", "Datos Incorrectos", JOptionPane.ERROR_MESSAGE);
					}
					else
						break;
				}
			}
		});
	}
	
	private void setearBoton(JButton boton, double y) {
		boton.setEnabled(true);
		boton.setBounds((int) (frameWidth * 0.3), (int) (frameHeight * y), (int) (frameWidth * 0.4), (int) (frameHeight * 0.075));
		int fontSize = (int) (boton.getHeight() * boton.getWidth() * 0.00135);
		boton.setFont(new Font("Segoe UI Symbol", Font.BOLD, fontSize));
		panel.add(boton);
	}
}
