package br.com.alelvis3.os.vew;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.alelvis3.os.dao.ModuloConexao;

public class TelaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JButton btnLogin;
	private JLabel lblStatus;

	/**
	 * Iniciando o sistema
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criando Janela
	 */
	public TelaLogin() {

		tela();

	}

	private void tela() {
		setTitle("INFOX - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(1, 13, 70, 15);
		lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUsurio);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(1, 38, 70, 15);
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSenha);

		lblStatus = new JLabel("");
		lblStatus.setBounds(12, 66, 135, 54);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblStatus);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(82, 10, 192, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(82, 37, 192, 19);
		contentPane.add(txtSenha);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(157, 66, 117, 54);
		// btnLogin.addActionListener(this);
		contentPane.add(btnLogin);

		conexao = ModuloConexao.conector();
		// System.out.println(conexao);

		if (conexao != null) {
			lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/alelvis3/os/vew/icon/dbok.png")));
		} else {
			lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/alelvis3/os/vew/icon/dberror.png")));
		}
		// ação do botão logar
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});

	}

	// criando o metodo logar
	public void logar() {
		
		if (conexao != null) {
			// logica principal para pesquisar no banco de dados
			String sql = "select * from "+ModuloConexao.getBanco()+".tbusuarios where login = ? and senha = ?";
			try {
				// as linhas abaixo preparam a consulta em função do que foi
				// digitado nas caixas de texto. O ? é substituído pelo conteúdo
				// das variáveis que são armazenadas em pst.setString
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, new String(txtSenha.getPassword()));
				// a linha abaixo executa a query(consulta)
				rs = pst.executeQuery();
				// se existir um usuário e senha correspondente
				if (rs.next()) {

					// conteudo do compo perfil da tabela tbusuario

					TelaPrincipal principal = new TelaPrincipal();
					principal.setVisible(true);
					String perfil = rs.getString(6);// le o conteudo da coluna 6 da tbusuario

					// controle de acesso por usuario
					switch (perfil) {
					case "admin":

						TelaPrincipal.mnCadastro.setEnabled(true);
						TelaPrincipal.mntmCliente.setEnabled(true);
						TelaPrincipal.mntmOS.setEnabled(true);
						TelaPrincipal.mntmUsurios.setEnabled(true);

						TelaPrincipal.mnRelatorio.setEnabled(true);
						TelaPrincipal.mntmServicos.setEnabled(true);

						TelaPrincipal.lblUsuario.setForeground(Color.red);

						break;

					case "user":

						TelaPrincipal.mnCadastro.setEnabled(true);
						TelaPrincipal.mntmCliente.setEnabled(true);
						TelaPrincipal.mntmOS.setEnabled(true);
						// TelaPrincipal.mntmUsurios.setEnabled(false);

						// TelaPrincipal.mnRelatorio.setEnabled(true);
						// TelaPrincipal.mntmServicos.setEnabled(false);

						TelaPrincipal.lblUsuario.setForeground(Color.BLUE);
						break;

					default:
						break;
					}
					// busca o campo nome da table e coloca no lblusuario
					TelaPrincipal.lblUsuario.setText("<html>" + rs.getString(2) + "</html>");

					this.dispose();// fecha a tela de login apos iniciar a tela principal
					conexao.close();// fecha a conexao com o banco de dados

				} else {

					JOptionPane.showMessageDialog(null, "usuário ou senha inválido");
					txtUsuario.setText("");
					txtSenha.setText("");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

		} else {

			JOptionPane.showMessageDialog(this, "Sem acesso ao banco de dados \n Acionar o suporte técnico", "Aviso!!!",
					JOptionPane.ERROR_MESSAGE);

		}
	}
}
