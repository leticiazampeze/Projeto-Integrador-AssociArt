package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import entities.Conversa;
import entities.Produto;
import entities.Usuario;

public class ConversaDaoJDBC implements ConversaDao {
	private Connection conn;
	
	public ConversaDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	private Conversa instantiateConversa(ResultSet rs, Usuario u, Produto p) throws SQLException{
		Conversa obj = new Conversa();
		obj.setId_conversa(rs.getInt("id_conversa"));
		obj.setMensagem(rs.getString("mensagem"));
		obj.setUsuario(u);
		obj.setProduto(p);
		return obj;
	}
	@Override
	public void insert(Conversa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO conversa " 
					+	"(mensagem, id_usuario, id_produto) " + "VALUES " +
					"(?, ?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getMensagem());
			st.setInt(2, obj.getUsuario().getId_usuario());
			st.setInt(3, obj.getProduto().getId_produto());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_conversa(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected Error! No rows affected!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM conversa WHERE id_conversa = ?");
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Conversa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT c.*, u.nome, u.cpf, u.tipo, u.senha, p.nome, p.preco, p.descricao, p.id_usuario "
					+ "FROM conversa c INNER JOIN usuario u "
					+ "ON c.id_usuario = u.id_usuario "
					+ "INNER JOIN produto p ON c.id_produto = p.id_produto "
					+ "WHERE c.id_conversa = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Usuario u = UsuarioDaoJDBC.instantiateUsuario(rs);
				Usuario us = new Usuario();
				us.setId_usuario(rs.getInt("p.id_usuario"));
				Produto p = ProdutoDaoJDBC.instantiateProduto(rs, us);
				Conversa obj = instantiateConversa(rs, u,p);
				return obj;
		}
		return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		}

	@Override
	public List<Conversa> findByProduto(Produto produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT c.*, u.nome, u.cpf, u.tipo, u.senha, p.preco, p.descricao, p.id_usuario "
					+ "FROM conversa c INNER JOIN usuario u "
					+ "ON c.id_usuario = u.id_usuario "
					+ "INNER JOIN produto p ON c.id_produto = p.id_produto "
					+ "WHERE c.id_produto = ? ");
			st.setInt(1, produto.getId_produto());
			rs = st.executeQuery();
			List<Conversa> lista = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			Map<Integer, Produto> map1 = new HashMap<>();
			while(rs.next()) {
				Usuario u = map.get(rs.getInt("id_usuario"));
				Produto p = map1.get(rs.getInt("id_produto"));
				if(u == null) {
					u = UsuarioDaoJDBC.instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), u);
				}
				if(p == null) {
					Usuario us = new Usuario();
					us.setId_usuario(rs.getInt("p.id_usuario"));
					p = ProdutoDaoJDBC.instantiateProduto(rs,us);
					map1.put(rs.getInt("id_produto"), p);
				}
				Conversa obj = instantiateConversa(rs, u, p);
				lista.add(obj);
			}
			return lista;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
