package br.com.hotelalura.factory;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	//NOME DO USUÁRIO DO MYSQL
	private static final String USERNAME = "root";
	
	//SENHA DO BANCO
	private static final String PASSWORD = "";
	
	//CAMINHO DO BANCO DE DADOS, PORTA , NOME DO BANCO DE DADOS
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/hotelalura";
	
	/*
	 * CONEXÃO COM O BANCO DE DADOS
	 */
	public static Connection createConccectionToMySql() throws SQLException, ClassNotFoundException {
		// FAZ COM QUE A CLASSSE SEJA CARREGADA PELA JVM
		Class.forName("com.mysql.jbdc.driver");
		// CRIA A CONEXÃO COM COM O BANCO
		Connection connection = DriverManager
				.getConnection(
						DATABASE_URL, 
						USERNAME,
						PASSWORD);
		
		return connection;
						
	}
	
	public static void main(String[] args) throws Exception {
		// RECUPERAR UMA CONEXÃO COM O BANCO DE DADOS 
		Connection con = createConccectionToMySql();
		
		//TESTER SE A CONCEXÃO E NULA 
		if (con!=null) {
			System.out.println("Conexão obetida com sucesso!");
			con.close();
		}
		
	}
}
