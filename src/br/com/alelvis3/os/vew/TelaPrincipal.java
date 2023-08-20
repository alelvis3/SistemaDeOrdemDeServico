package br.com.alelvis3.os.vew;

import static javax.swing.KeyStroke.getKeyStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public  class  TelaPrincipal  extends  JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JMenu mnRelatorio;
	public static JMenuItem mntmUsurios;
	public static JLabel lblUsuario;
	public static JMenu mnCadastro;

	public static JMenuItem mntmCliente;

	public static JMenuItem mntmOS;

	public static JMenuItem mntmServicos;

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Criando Janela
	 */
	public TelaPrincipal() {
		setTitle("Sistema Info X");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnCadastro = new JMenu("Cadastro");
		mnCadastro.setEnabled(false);
		menuBar.add(mnCadastro);

		mntmCliente = new JMenuItem("Cliente");		
		mntmCliente.setEnabled(false);
		mntmCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(mntmCliente);

		mntmOS = new JMenuItem("OS");
		mntmOS.setEnabled(false);
		mntmOS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(mntmOS);

		mntmUsurios = new JMenuItem("Usuários");		
		mntmUsurios.setEnabled(false);
		mntmUsurios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		mnCadastro.add(mntmUsurios);

		mnRelatorio = new JMenu("Relatório");
		mnRelatorio.setEnabled(false);
		menuBar.add(mnRelatorio);

		mntmServicos = new JMenuItem("Serviços");
		mntmServicos.setEnabled(false);
		mntmServicos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.ALT_DOWN_MASK));
		mnRelatorio.add(mntmServicos);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmSobre = new JMenuItem("Sobre");		
		mntmSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		mnAjuda.add(mntmSobre);

		JMenu mnOpes = new JMenu("Op  es");
		menuBar.add(mnOpes);

		JMenuItem mntmSair = new JMenuItem("Sair");		
		mntmSair.setAccelerator(getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		mnOpes.add(mntmSair);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		

		desktopPane = new JDesktopPane();
		desktopPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Desktop", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		desktopPane.setBackground(Color.BLACK);


		lblUsuario = new JLabel("Usu rio");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

		JLabel lblData = new JLabel("Data");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(6)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(lblData)
						.addGap(395))
				);
		contentPane.setLayout(gl_contentPane);
		
		//a  o mostrar data atual		
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
		lblData.setText(formatador.format(data));

		

		//a  o para abrir tela cliente dentro do descktoppane		
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				desktopPane.add(cliente);				
			}
		});		

		//a  o abriri tela usuario dentro do descktoppane
		mntmUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setVisible(true);
				desktopPane.add(usuario);
			}
		});
		
		//a  o menu Sobre		
				mntmSobre.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {				
						TelaSobre sobre = new TelaSobre();
						sobre.setVisible(true);
					}
				});		

		//a  o do menu sair
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Aten  o", JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}				
			}
		});
	}
}
