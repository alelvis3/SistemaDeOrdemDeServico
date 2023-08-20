package br.com.alelvis3.os.vew;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaSobre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TelaSobre() {
		setTitle("Sobre");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 432, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSistema = new JLabel("Sistema de controle de agrotoxicos");
		lblSistema.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistema.setBounds(10, 11, 424, 59);
		contentPane.add(lblSistema);
		
		JLabel lblAutor = new JLabel("Desenvolvido por Alexandre de Souza Marques");
		lblAutor.setForeground(Color.BLUE);
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutor.setBounds(10, 73, 424, 70);
		contentPane.add(lblAutor);
		
		JLabel lblLicensa = new JLabel("Trabalho de conclus o do sexto semestre");
		lblLicensa.setHorizontalAlignment(SwingConstants.CENTER);
		lblLicensa.setBounds(87, 165, 255, 14);
		contentPane.add(lblLicensa);
		
		JLabel lblGNU = new JLabel("");
		lblGNU.setIcon(new ImageIcon(TelaSobre.class.getResource("/br/com/alelvis3/os/vew/icon/GNU.png")));
		lblGNU.setBounds(118, 207, 185, 77);
		contentPane.add(lblGNU);
	}
}