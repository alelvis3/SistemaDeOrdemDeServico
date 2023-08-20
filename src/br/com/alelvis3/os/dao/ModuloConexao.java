package br.com.alelvis3.os.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ModuloConexao {
	private static String banco = "osalelvis";
	
	public static String getBanco() {
		return banco;
	}
	
public static Connection conector() {
		
		//dados do banco de dados
		String usuario = "alelvis";
		String senha = "753951Al#";
		
		String enderecoBanco = "192.168.15.5:3306";
		String driver = "com.mysql.cj.jdbc.Driver";
		//--------------------------------------------------------------
		String url="jdbc:mysql://"+enderecoBanco+"/"+banco+"?useTimezone=true&serverTimezone=UTC";
		
		
		Connection conexao=null;

		try {
			Class.forName(driver);
			conexao = (Connection) DriverManager.getConnection(url,usuario,senha);
			return conexao;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Driver SQL n o encontrado! \n"+e.getMessage());
			
			return null;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao acessar o servidor SQL:\n"+e.getMessage());
			return null;
		}			
	}	
}
