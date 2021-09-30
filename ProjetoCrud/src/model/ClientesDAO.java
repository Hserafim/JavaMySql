package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {

	// C
	public boolean create(Clientes clientes) {

		boolean status;
		String sql = "INSERT INTO clientes (nome, cpf, cidade, estado) VALUES (?, ?, ?, ?	)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, clientes.getNome());
			pstm.setString(2, clientes.getCpf());
			pstm.setString(3, clientes.getCidade());
			pstm.setString(4, clientes.getEstado());

			pstm.execute();

			status = true;

			// JOptionPane.showMessageDialog(null, "Salvo", "Cadastro",
			// JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return status;

	}
	// R

	public List<Clientes> read(String search) {

		String sql;

		if (search == "") {

			sql = "SELECT * FROM clientes";

		} else {

			sql = "SELECT * FROM clientes WHERE nome LIKE '%" + search + "%'";

		}

		List<Clientes> clientes = new ArrayList<Clientes>();

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rst = null;

		try {

			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rst = pstm.executeQuery();

			while (rst.next()) {

				Clientes cliente = new Clientes();

				cliente.setId(rst.getInt("id"));
				cliente.setNome(rst.getString("nome"));
				cliente.setCpf(rst.getString("cpf"));
				cliente.setCidade(rst.getString("cidade"));
				cliente.setEstado(rst.getString("estado"));

				clientes.add(cliente);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (rst != null) {
					rst.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return clientes;
	}

	// U

	public boolean update(Clientes cliente) {

		boolean status;

		String sql = "UPDATE clientes SET nome = ? , cpf = ? , cidade = ? , estado = ? WHERE id = ?";
		// id, nome, cpf, cidade, estado

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getCidade());
			pstm.setString(4, cliente.getEstado());

			pstm.setInt(5, cliente.getId());

			pstm.execute();

			status = true;

		} catch (Exception e) {

			status = false;
			e.printStackTrace();

		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return status;

	}

	// D

	public boolean delete(Clientes cliente) {

		boolean status;

		String sql = "DELETE FROM clientes WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, cliente.getId());

			pstm.execute();

			status = true;

		} catch (Exception e) {

			status = false;
			e.printStackTrace();

		} finally {

			try {

				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return status;

	}

}
