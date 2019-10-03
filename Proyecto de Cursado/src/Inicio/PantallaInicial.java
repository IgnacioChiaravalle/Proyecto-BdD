package Inicio;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		
		JLabel fondo = new JLabel();
		ImageIcon iconoOriginal = new ImageIcon(this.getClass().getResource("/Fondo de Pantalla.jpg"));
		ImageIcon iconoEscala = new ImageIcon(iconoOriginal.getImage().getScaledInstance(frameWidth, frameHeight, java.awt.Image.SCALE_DEFAULT));
		fondo.setIcon(iconoEscala);
		frame.setContentPane(fondo);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		panel = frame.getContentPane();
		panel.setBounds(0, 0, frameWidth, frameHeight);
		panel.setLayout(null);
	}
	
	private void crearBotones() {
		botonAdmin = new JButton("Ingresar como Administrador");
		setearBoton(botonAdmin, 0.2);
		botonAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField password = new JPasswordField();
				Object[] cuadroDeIngreso = { "Contrase�a:", password };
				boolean valido = false;
				while (!valido) {
					int option = JOptionPane.showConfirmDialog(null, cuadroDeIngreso, "Acceso del Administrador", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						valido = consultor.chequearAdministrador(password.getText());
						if (!valido)
							JOptionPane.showMessageDialog(null, "La contrase�a ingresada es incorrecta.", "Contrase�a Incorrecta", JOptionPane.ERROR_MESSAGE);
					}
					else
						break;
				}
				
				//if (valido)
				//Pasar a a vista del administrador.
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
				    "Contrase�a:", password
				};
				boolean valido = false;
				while (!valido) {
					int option = JOptionPane.showConfirmDialog(null, cuadrosDeIngreso, "Acceso del Empleado", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
					    valido = consultor.chequearEmpleado(legajo.getText(), password.getText());
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
		boton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
		//botonAdmin.setBackground(new Color(240, 230, 140));
		boton.setBounds((int) (frameWidth * 0.3), (int) (frameHeight * y), (int) (frameWidth * 0.4), (int) (frameHeight * 0.075));
		panel.add(boton);
	}
}