package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao{
	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	public static Usuario instantiateUsuario(ResultSet rs) throws SQLException{
		Usuario obj = new Usuario();
		obj.setId_usuario(rs.getInt("id_usuario"));
		obj.setNome(rs.getString("nome"));
		obj.setCpf(rs.getString("cpf"));
		obj.setTipo(rs.getString("tipo"));
		obj.setSenha(rs.getString("senha"));
		return obj;
	}

	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO usuario " 
					+	"(nome, cpf, tipo, senha) " + "VALUES " +
					"(?, ?, ?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getCpf());
			st.setString(3, obj.getTipo());
			st.setString(4, obj.getSenha());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_usuario(id);
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
	public void update(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE usuario " +
					"SET nome = ?, tipo = ?, senha = ? " + "WHERE id_usuario = ?");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getTipo());
			st.setString(3, obj.getSenha());
			st.setInt(4, obj.getId_usuario());
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
			st = conn.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?");
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
	public Usuario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT usuario.* " + "FROM usuario "
					+ "WHERE usuario.id_usuario = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Usuario obj = instantiateUsuario(rs);
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
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT usuario.* "
					+ "FROM usuario " + "ORDER BY nome");
			rs = st.executeQuery();
			List<Usuario> lista = new ArrayList<>();
			while(rs.next()) {
				Usuario obj = instantiateUsuario(rs);
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
}
