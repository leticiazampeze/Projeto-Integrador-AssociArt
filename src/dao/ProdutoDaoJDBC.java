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
import entities.Produto;
import entities.Usuario;

public class ProdutoDaoJDBC implements ProdutoDao {
	private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn=conn;
		}
	public static Produto instantiateProduto(ResultSet rs, Usuario u) throws SQLException{
		Produto obj = new Produto();
		obj.setId_produto(rs.getInt("id_produto"));
		obj.setNome(rs.getString("nome"));
		obj.setPreco(rs.getDouble("preco"));
		obj.setDescricao(rs.getString("descricao"));
		obj.setUsuario(u);
		return obj;
		}
	@Override
	public void insert(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO produto "
					+ "(nome, preco, descricao, id_usuario) " + "VALUES " +
					"(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setString(3, obj.getDescricao());
			st.setInt(4, obj.getUsuario().getId_usuario());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_produto(id);
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
	public void update(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE produto " +
		"SET nome = ?, preco = ?, descricao = ? " + "WHERE id_produto = ?");
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getPreco());
			st.setString(3, obj.getDescricao());
			st.setInt(4, obj.getId_produto());
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
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM produto WHERE id_produto = ?");
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
	public Produto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT produto.*, usuario.nome, usuario.cpf, usuario.tipo, usuario.senha "
					 + "FROM produto INNER JOIN usuario "
					 + "ON produto.id_usuario = usuario.id_usuario "
					 + "WHERE produto.id_produto = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Usuario us = UsuarioDaoJDBC.instantiateUsuario(rs);
				Produto obj = instantiateProduto(rs, us);
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
	public List<Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT produto.*, usuario.cpf, usuario.tipo, usuario.senha "
					+ "FROM produto INNER JOIN usuario " 
					+ " ON produto.id_usuario = usuario.id_usuario " +
					" ORDER BY nome");
			rs = st.executeQuery();
			List<Produto> lista = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			while(rs.next()) {
				Usuario u = map.get(rs.getInt("id_usuario"));
				if(u == null) {
				 u = UsuarioDaoJDBC.instantiateUsuario(rs);
				map.put(rs.getInt("id_usuario"), u);
			}
			Produto obj = instantiateProduto(rs,u);
			lista.add(obj);
		}
		return lista;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		}
	@Override
	public List<Produto> findByUsuario(Usuario usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT produto.*, usuario.nome, usuario.cpf, usuario.tipo, usuario.senha " 
					+ "FROM produto INNER JOIN usuario "
					+ "ON produto.id_usuario = usuario.id_usuario "
					+ "WHERE produto.id_usuario = ? ");
			st.setInt(1, usuario.getId_usuario());
			rs = st.executeQuery();
			List<Produto> lista = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			while(rs.next()) {
				Usuario u = map.get(rs.getInt("id_usuario"));
				if(u == null) {
					u = UsuarioDaoJDBC.instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), u);
				}
				Produto obj = instantiateProduto(rs, u);
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
	@Override
	public List<Produto> findByNome(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT produto.*, usuario.nome, usuario.cpf, usuario.tipo, usuario.senha " 
					+ "FROM produto INNER JOIN usuario "
					+ "ON produto.id_usuario = usuario.id_usuario "
					+ "WHERE produto.nome LIKE ?");
			st.setString(1, '%' + nome + '%');
			rs = st.executeQuery();
			List<Produto> lista = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			while(rs.next()) {
				Usuario u = map.get(rs.getInt("id_usuario"));
				if(u == null) {
					u = UsuarioDaoJDBC.instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), u);
				}
				Produto obj = instantiateProduto(rs, u);
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
