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
		r.setFormaPagamento("pix");
		
		ReservasDAO.salvar(r);
		
		//VISUALIZÃO DOS DADOS
		
		for (Reservas c : reservaDAO.getReservas()) {
			System.out.println("id :"+c.getId());
		}
		
	}

}
