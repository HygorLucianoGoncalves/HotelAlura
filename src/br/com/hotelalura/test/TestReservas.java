package br.com.hotelalura.test;



import java.util.Date;

import br.com.hotelalura.dao.ReservasDAO;
import br.com.hotelalura.model.Reservas;
public class TestReservas {
	public static void main(String[] args) {
		
		ReservasDAO reservaDAO = new ReservasDAO();
		
		Reservas r = new Reservas();
		r.setDataEntrada(new Date());
		r.setDataSaida(new Date());
		r.setValor(60);
		r.setFormaPagamento("poi");
		
		//ReservasDAO.salvar(r);
		
		Reservas c1 = new Reservas();
		c1.setDataEntrada(new Date());
		c1.setDataSaida(new Date());
		c1.setFormaPagamento("TESTE");
		c1.setId(1);// o id que esta no banco
		
	
		//reservaDAO.atualizar(c1);
		
		//Deleta o contato pelo seu numero de id 
		
		reservaDAO.deleteById(1);
		
		for (Reservas c : reservaDAO.getReservas()) {
			System.out.println("id :"+c.getId());
		}
		
	}

}
