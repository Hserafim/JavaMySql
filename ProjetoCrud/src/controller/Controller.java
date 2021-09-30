package controller;

import java.util.List;

import model.Clientes;
import model.ClientesDAO;

public class Controller {

	public boolean addCastro(Clientes cliente) {
		
		boolean status;	

		ClientesDAO banco = new ClientesDAO();

		status = banco.create(cliente);

		return status;

	}

	public boolean updateCadastro(Clientes cliente) {

		ClientesDAO banco = new ClientesDAO();
		return banco.update(cliente);

	}

	public boolean deletarCadastro(Clientes cliente) {

		ClientesDAO banco = new ClientesDAO();
		return banco.delete(cliente);

	}

	public List<Clientes> buscarClientes(String search) {

		ClientesDAO cdao = new ClientesDAO();
		return cdao.read(search);
	}

}
