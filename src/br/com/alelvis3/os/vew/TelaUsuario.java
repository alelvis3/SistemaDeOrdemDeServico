package br.com.alelvis3.os.vew;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.alelvis3.os.dao.ModuloConexao;

public class TelaUsuario extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtUsuId;
	private JTextField txtUsuNome;
	private JTextField txtUsuFone;
	private JComboBox<String> cboUsuPerfil;
	private JTextField txtUsuLogin;	
	private JPasswordField txtUsuSenha;
	private JPasswordField txtUsuConfirmaSenha;

	//  usando a variavel conexao do DAL
	Connection conexao = null;
	// criando vari veis especiais para conex o com o banco
	//Prepared Statement e ResultSet s o frameworks do pacote java.sql
	// e servem para preparar e executar as instru  es SQL
	PreparedStatement pst = null;
	ResultSet rs = null;


	//Construtor da Clase
	public TelaUsuario() {
		componentesUsuarios();
		conexao = ModuloConexao.conector();

	}

	/**
	 * Componetes e configura  o da tela
	 */
	private void componentesUsuarios() {
		setIconifiable(true);		
		setClosable(true);
		setTitle("Usu rio");
		setBounds(100, 100, 603, 498);
		getContentPane().setLayout(null);

		JLabel lblUsuID = new JLabel("ID:");
		lblUsuID.setBounds(20, 32, 23, 22);
		lblUsuID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuID.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuID);
		
		txtUsuId = new JTextField();
		txtUsuId.setBounds(83, 35, 73, 20);
		txtUsuId.setColumns(10);		
		getContentPane().add(txtUsuId);
		
		JLabel lblUsuNome = new JLabel("Nome:");
		lblUsuNome.setBounds(20, 62, 53, 22);
		lblUsuNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuNome.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuNome);		
		
		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(83, 65, 486, 20);
		txtUsuNome.setColumns(10);
		getContentPane().add(txtUsuNome);
		
		JLabel lblUsuFone = new JLabel("Fone:");
		lblUsuFone.setBounds(20, 94, 45, 22);
		lblUsuFone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuFone.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuFone);
		
		txtUsuFone = new JTextField();
		txtUsuFone.setBounds(83, 95, 166, 20);
		txtUsuFone.setColumns(10);
		getContentPane().add(txtUsuFone);
		
		JLabel lblUsuPerfil = new JLabel("Perfil:");
		lblUsuPerfil.setBounds(259, 94, 49, 22);
		lblUsuPerfil.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuPerfil.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuPerfil);
		
		cboUsuPerfil = new JComboBox<String>();
		cboUsuPerfil.setBounds(318, 96, 251, 20);
		cboUsuPerfil.setEditable(true);
		cboUsuPerfil.setModel(new DefaultComboBoxModel<String>(new String[] {"Admin", "User"}));
		getContentPane().add(cboUsuPerfil);

		JLabel lblUsuLogin = new JLabel("Login");
		lblUsuLogin.setBounds(20, 125, 45, 22);
		lblUsuLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuLogin.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuLogin);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(83, 129, 486, 20);
		txtUsuLogin.setColumns(10);
		getContentPane().add(txtUsuLogin);

		JLabel lblUsuSenha = new JLabel("Senha");
		lblUsuSenha.setBounds(20, 158, 51, 22);
		lblUsuSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuSenha.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuSenha);
		
		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setBounds(83, 161, 166, 20);
		getContentPane().add(txtUsuSenha);
		

		JLabel lblUsuConfimaSenha = new JLabel("Confirma Senha:");
		lblUsuConfimaSenha.setBounds(257, 158, 136, 22);
		lblUsuConfimaSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuConfimaSenha.setFont(new Font("Arial Black", Font.PLAIN, 15));
		getContentPane().add(lblUsuConfimaSenha);

		txtUsuConfirmaSenha = new JPasswordField();
		txtUsuConfirmaSenha.setBounds(403, 161, 166, 20);
		getContentPane().add(txtUsuConfirmaSenha);

		JButton btnUsuCreate = new JButton("");
		btnUsuCreate.setBounds(20, 206, 97, 73);
		btnUsuCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuCreate.setToolTipText("Adicionar");
		btnUsuCreate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/create.png")));
		getContentPane().add(btnUsuCreate);

		JButton btnUsuUpdate = new JButton("");
		btnUsuUpdate.setBounds(179, 206, 97, 73);
		btnUsuUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuUpdate.setToolTipText("Alterar");
		btnUsuUpdate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/update.png")));
		getContentPane().add(btnUsuUpdate);

		JButton btnUsuRead = new JButton("");
		btnUsuRead.setBounds(318, 206, 97, 73);
		btnUsuRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuRead.setToolTipText("Pesquisar");
		btnUsuRead.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/pesquisar.png")));
		getContentPane().add(btnUsuRead);

		JButton btnUsuDelete = new JButton("");
		btnUsuDelete.setBounds(472, 206, 97, 73);
		btnUsuDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuDelete.setToolTipText("Deletar");
		btnUsuDelete.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/delete.png")));
		getContentPane().add(btnUsuDelete);

		//a  o do Bot o
		btnUsuCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});

		btnUsuUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});

		btnUsuRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});

		btnUsuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});

	}

	//m todo para consultar usu rios

	private void consultar() {
		String sql = "select * from dbinfox.tbusuarios where iduser=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuId.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtUsuNome.setText(rs.getString(2));
				txtUsuFone.setText(rs.getString(3));
				txtUsuLogin.setText(rs.getString(4));
				txtUsuSenha.setText(rs.getString(5));
				//a linha abaixo se refere ao combobox
				cboUsuPerfil.setSelectedItem(rs.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "Usu rio n o cadastrado");
				//as linhas abaixo "limpam" os campos
				limparCampos();
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao acessar o banco de dados \n"+e);
		}
	}

	//m todo para adicionar usu rios
	private void adicionar() {
		if (txtUsuSenha.equals(txtUsuConfirmaSenha)) {
			
			JOptionPane.showMessageDialog(null, "Senha de confirma  o incorreta!!!!");
			
		} else {
			String sql = "insert into dbinfox.tbusuarios(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,?,?)";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtUsuId.getText());
				pst.setString(2, txtUsuNome.getText());
				pst.setString(3, txtUsuFone.getText());
				pst.setString(4, txtUsuLogin.getText());
				pst.setString(5, new String( txtUsuSenha.getPassword()));
				pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
				//valida  o dos campos obrigat rios
				if ((txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (new String( txtUsuSenha.getPassword())).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigat rios");
				} else {

					//a linha abaixo atualiza a tabela usuario com os dados do formul rio
					//a etrutura abaixo   usada para confirmar a inser  o dos dados na tabela
					int adicionado = pst.executeUpdate();
					//a linha abaixo serve de apoio ao entendimento da l gica
					//System.out.println(adicionado);
					if (adicionado > 0) {
						JOptionPane.showMessageDialog(null, "Usu rio adicionado com sucesso");
						limparCampos();

					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Erro ao acessar o banco de dados \n"+e);
			}
		}		
	}

	//criando o m todo para alterar dados do usu rio
	private void alterar() {
		String sql = "update tbusuario set dbinfox.tbusuarios=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuNome.getText());
			pst.setString(2, txtUsuFone.getText());
			pst.setString(3, txtUsuLogin.getText());
			pst.setString(4, new String( txtUsuSenha.getPassword()));
			pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
			pst.setString(6, txtUsuId.getText());
			if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (new String( txtUsuSenha.getPassword())).isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigat rios");
			} else {

				//a linha abaixo atualiza a tabela usuario com os dados do formul rio
				//a etrutura abaixo   usada para confirmar a altera  o dos dados na tabela
				int adicionado = pst.executeUpdate();
				//a linha abaixo serve de apoio ao entendimento da l gica
				//System.out.println(adicionado);
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Dados do usu rio alterados com sucesso");
					limparCampos();

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Erro ao acessar o banco de dados \n"+e);
		}
	}

	// m todo respons vel pela remo  o de usu rios

	private void remover() {
		//a estrutura abaixo confirma a remo  o do usu rio
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usu rio ?", "Aten  o", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String sql = "delete from dbinfox.tbusuarios where iduser=?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtUsuId.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Usu rio Removido com sucesso");
					limparCampos();
				}else {
					JOptionPane.showMessageDialog(null, "Usu rio n o removido ou n o existe");
					limparCampos();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"Erro ao acessar o banco de dados \n"+e);

			}
		}
	}
	
//limpar campos
	private void limparCampos() {
		txtUsuId.setText(null);
		txtUsuNome.setText(null);
		txtUsuFone.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		txtUsuConfirmaSenha.setText(null);
	}
}
