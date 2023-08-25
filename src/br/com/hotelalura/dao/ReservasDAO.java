package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import br.com.hotelalura.factory.ConnectionFactory;
import br.com.hotelalura.model.Reservas;

public class ReservasDAO {
	
	public static void salvar(Reservas reservas) {
		
		String sql = "INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento ) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// CRIANDO UMA CONEXÃO COM O BANCO DE DADOS
			conn = ConnectionFactory.createConccectionToMySql();
					
			
			//CRIAMOS UMA PREPAREDSTRAMENT, PARA EXECUTAR UMA QUERY
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//ADICIONAR OS VALORES QUE SÃO ESPERADOS PELA QUERY
			pstm.setDate(1, new Date(reservas.getDataEntrada().getTime()));
			pstm.setDate(2,new Date(reservas.getDataSaida().getTime()));
			pstm.setInt(3,reservas.getValor());
			pstm.setString(4, reservas.getFormaPagamento());
			
			//EXECUTAR A QUERY
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//fechar as conexões
			try {
				if (pstm!=null) {
					pstm.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
