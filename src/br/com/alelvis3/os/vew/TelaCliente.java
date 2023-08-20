package br.com.alelvis3.os.vew;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.alelvis3.os.dao.ModuloConexao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class TelaCliente extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtCliPesquisar;
	private JTextField txtCliId;
	private JTextField txtCliNome;
	private JTextField txtCliEndereco;
	private JTextField txtCliFone;
	private JTextField txtCliEmail;

	private JButton btnCliCreate;
	private JButton btnCliUpdate;
	private JButton btnCliDelete;	

	private JScrollPane scrollPane;
	private JTable tblClientes;

	//  usando a variavel conexao do DAL
	Connection conexao = null;
	// criando vari veis especiais para conex o com o banco
	//Prepared Statement e ResultSet s o frameworks do pacote java.sql
	// e servem para preparar e executar as instru  es SQL
	PreparedStatement pst = null;
	ResultSet rs = null;


	/**
	 * Construtor
	 */
	public TelaCliente() {
		getContentPane().setFont(new Font("Arial Black", Font.BOLD, 12));
		componentesCliente();
		conexao = ModuloConexao.conector();

	}

	//Componentes da tela
	private void componentesCliente() {
		setIconifiable(true);		
		setClosable(true);
		setTitle("Cliente");
		setBounds(100, 100, 568, 498);
		getContentPane().setLayout(null);

		JLabel lblPesquisa = new JLabel("Pesquisar");
		lblPesquisa.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblPesquisa.setBounds(20, 17, 83, 18);
		getContentPane().add(lblPesquisa);

		txtCliPesquisar = new JTextField();		
		txtCliPesquisar.setBounds(99, 17, 433, 20);
		getContentPane().add(txtCliPesquisar);
		txtCliPesquisar.setColumns(10);

		JLabel lblCliId = new JLabel("ID");
		lblCliId.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCliId.setBounds(20, 253, 46, 14);
		getContentPane().add(lblCliId);

		txtCliId = new JTextField();
		txtCliId.setEditable(false);
		txtCliId.setBounds(89, 247, 86, 20);
		getContentPane().add(txtCliId);
		txtCliId.setColumns(10);


		JLabel lblCliNome = new JLabel("Nome");
		lblCliNome.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCliNome.setBounds(20, 278, 65, 14);
		getContentPane().add(lblCliNome);


		txtCliNome = new JTextField();
		txtCliNome.setBounds(89, 275, 443, 20);
		getContentPane().add(txtCliNome);
		txtCliNome.setColumns(10);

		JLabel lblCliEndereco = new JLabel("Endere\u00E7o");
		lblCliEndereco.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCliEndereco.setBounds(20, 303, 65, 14);
		getContentPane().add(lblCliEndereco);

		txtCliEndereco = new JTextField();
		txtCliEndereco.setBounds(89, 300, 443, 20);
		getContentPane().add(txtCliEndereco);
		txtCliEndereco.setColumns(10);

		JLabel lblCliTelefone = new JLabel("Telefone");
		lblCliTelefone.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCliTelefone.setBounds(20, 328, 65, 14);
		getContentPane().add(lblCliTelefone);

		txtCliFone = new JTextField();
		txtCliFone.setBounds(89, 325, 443, 20);
		getContentPane().add(txtCliFone);
		txtCliFone.setColumns(10);

		JLabel lblCliEmail = new JLabel("E-MAIL");
		lblCliEmail.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblCliEmail.setBounds(20, 356, 59, 18);
		getContentPane().add(lblCliEmail);

		txtCliEmail = new JTextField();
		txtCliEmail.setBounds(89, 356, 443, 20);
		getContentPane().add(txtCliEmail);
		txtCliEmail.setColumns(10);

		btnCliCreate = new JButton("");
		btnCliCreate.setBounds(20, 384, 97, 73);
		btnCliCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliCreate.setToolTipText("Adicionar");
		btnCliCreate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/create.png")));
		getContentPane().add(btnCliCreate);

		btnCliUpdate = new JButton("");
		btnCliUpdate.setBounds(222, 384, 97, 73);
		btnCliUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliUpdate.setToolTipText("Alterar");
		btnCliUpdate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/update.png")));
		getContentPane().add(btnCliUpdate);

		btnCliDelete = new JButton("");
		btnCliDelete.setBounds(435, 384, 97, 73);
		btnCliDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCliDelete.setToolTipText("Deletar");
		btnCliDelete.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/alelvis3/os/vew/icon/delete.png")));
		getContentPane().add(btnCliDelete);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 45, 512, 191);
		getContentPane().add(scrollPane);

			
		tblClientes = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}			
		};	

		scrollPane.setViewportView(tblClientes);

		//Evento para clicar na tabela "setar campos" 
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setar_campos();
			}
		});	

		//Evento para adicionar automaticamente a pesquisa
		txtCliPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar();
			}
		});


		//a  o do Bot o
		btnCliCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});

		btnCliUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});

		btnCliDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remover();
			}
		});
	}




	//m todo para adicionar clientes
	private void adicionar() {
		String sql = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtCliNome.getText());
			pst.setString(2, txtCliEndereco.getText());
			pst.setString(3, txtCliFone.getText());
			pst.setString(4, txtCliEmail.getText());

			//valida  o dos campos obrigat rios
			if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigat rios");
			} else {
				//a linha abaixo atualiza a tabela usuario com os dados do formul rio
				//a etrutura abaixo   usada para confirmar a inser  o dos dados na tabela
				int adicionado = pst.executeUpdate();
				//a linha abaixo serve de apoio ao entendimento da l gica
				//System.out.println(adicionado);
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
					limparCampos();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	//m todo para pesquisar clientes pelo nome com filtro

	private void pesquisar() {

		String sql = "select * from dbinfox.tbclientes where nomecli like ?";


		//prepara a tabela para receber os dados
		DefaultTableModel model = new DefaultTableModel();		

		model.addColumn("ID");
		model.addColumn("Nome");
		model.addColumn("Endere o");
		model.addColumn("Telefone");
		model.addColumn("E-mail");		

		tblClientes.setModel(model);		
		String[] dados = new String[5];


		try {
			pst = conexao.prepareStatement(sql);
			//passando o conte do da caixa de pesquisa para o ?
			//aten  o ao "%" - continua  o da String sql
			pst.setString(1,"%"+ txtCliPesquisar.getText() + "%");
			rs = pst.executeQuery();
			while(rs.next()) {
				dados[0]=rs.getString(1);
				dados[1]=rs.getString(2);
				dados[2]=rs.getString(3);
				dados[3]=rs.getString(4);
				dados[4]=rs.getString(5);
				model.addRow(dados);
			}


		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e);
		}

	}

	//	//m todo para setar os campos do formul rio com o conte do da tabela
	public void setar_campos() {
		int setar = tblClientes.getSelectedRow();
		txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
		txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
		txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
		txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
		txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
		btnCliCreate.setEnabled(false);
	}

	// m todo para alterar dados do cliente
	private void alterar() {
		String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtCliNome.getText());
			pst.setString(2, txtCliEndereco.getText());
			pst.setString(3, txtCliFone.getText());
			pst.setString(4, txtCliEmail.getText());
			pst.setString(5, txtCliId.getText());
			if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigat rios");
			} else {

				//a linha abaixo atualiza a tabela cliente com os dados do formul rio
				//a etrutura abaixo   usada para confirmar a altera  o dos dados na tabela
				int adicionado = pst.executeUpdate();
				//a linha abaixo serve de apoio ao entendimento da l gica
				//System.out.println(adicionado);
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
					limparCampos();
					btnCliCreate.setEnabled(true);

				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// m todo respons vel pela remo  o de clientes

	private void remover() {
		//a estrutura abaixo confirma a remo  o do cliente
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente ?", "Aten  o", JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String sql = "delete from tbclientes where idcli=?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtCliId.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente Removido com sucesso");
					limparCampos();
					btnCliCreate.setEnabled(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);

			}
		}
	}

	//limpar campos
	private void limparCampos() {
		txtCliPesquisar.setText(null);
		((DefaultTableModel)tblClientes.getModel()).setRowCount(0);
		txtCliId.setText(null);
		txtCliNome.setText(null);
		txtCliEndereco.setText(null);
		txtCliFone.setText(null);
		txtCliEmail.setText(null);			
	}

}
