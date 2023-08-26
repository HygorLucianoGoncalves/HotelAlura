package br.com.hotelalura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
			pstm.setDouble(3,reservas.getValor());
			pstm.setString(4, reservas.getFormaPagamento());
			
			//EXECUTAR A QUERY
			pstm.execute();
			
			System.out.println("Reserva salva com sucesso");
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

	public void atualizar(Reservas reservas) {
		String sql = "UPDATE reservas SET dataEntrada = ?, dataSaida = ?, formaPagamento = ? WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// CRIA CONEXÃO COM O BANCO
			conn = ConnectionFactory.createConccectionToMySql();
			
			// CRIA A CLASSE PARA EXECUTAR A QUERY
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//ADICIONAR OS VALORES PARA ATUALIZAR
			//ADICIONAR OS VALORES QUE SÃO ESPERADOS PELA QUERY
			pstm.setDate(1, new Date(reservas.getDataEntrada().getTime()));
			pstm.setDate(2,new Date(reservas.getDataSaida().getTime()));
			pstm.setString(3, reservas.getFormaPagamento());
			
			//QUAL O ID DO REGISTRO QUE DESEJA ATUALIZAR
			pstm.setInt(4, reservas.getId());
			
			pstm.execute();
			System.out.println("Atualizado com sucesso");
		}catch (Exception e) {
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
	
	public List<Reservas> getReservas() {

		String sql = "SELECT * FROM reservas";
		
		List<Reservas> reservas = new ArrayList<Reservas>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// CLASSE QUE VAI RECUERAR A DADOS DO BANCO DE DADOS 
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConccectionToMySql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Reservas reservas2 = new Reservas();
				
				// RECUPERA O ID
				reservas2.setId(rset.getInt("id"));
				// RECUPERAR A DATA DE ENTRADA
				reservas2.setDataEntrada(rset.getDate("dataEntrada"));
				//RECUPERA A DATA DE SAIDA 
				reservas2.setDataSaida(rset.getDate("dataSaida"));
				//RECUPERA O VALOR DA RESERVA
				reservas2.setValor(rset.getDouble("valor"));
				
				reservas.add(reservas2);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			//fechar as conexões
			try {
				if (rset != null) {
					rset.close();
				}
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
		return reservas;
		
	}

}
