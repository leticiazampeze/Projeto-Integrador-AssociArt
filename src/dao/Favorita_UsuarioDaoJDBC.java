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
import entities.Favorita_Usuario;
import entities.Usuario;

public class Favorita_UsuarioDaoJDBC implements Favorita_UsuarioDao{
private Connection conn;	
	
	public Favorita_UsuarioDaoJDBC(Connection conn) {
	this.conn = conn;
}
	private Favorita_Usuario instantiateF_Usuario(ResultSet rs, Usuario u1, Usuario u2) throws SQLException{
		Favorita_Usuario obj = new Favorita_Usuario();
		obj.setId_favorita(rs.getInt("id_favorita_usuario"));
		obj.setUsuario1(u1);
		obj.setUsuario2(u2);
		return obj;
	}
	@Override
	public void insert(Favorita_Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO favorita_usuario " 
					+	"(id_usuario1, id_usuario2) " + "VALUES " +
					"(?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getUsuario1().getId_usuario());
			st.setInt(2, obj.getUsuario2().getId_usuario());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_favorita(id);
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
			st = conn.prepareStatement("DELETE FROM favorita_usuario WHERE id_favorita_usuario = ? ");
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
	// CLASSE QUE DEVOLVE UMA LISTA DE USUÁRIOS QUE FAVORITARAM UM USUÁRIO
	@Override
	public List<Favorita_Usuario> findByFavoritaramUsuario(Usuario usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT f.*, u.id_usuario, u.nome, u.cpf, u.tipo, u.senha "
					+ "FROM favorita_usuario f INNER JOIN usuario u "
					+ "ON f.id_usuario2 = u.id_usuario "
					+ "WHERE f.id_usuario2 = ? ");
			st.setInt(1, usuario.getId_usuario());
			rs = st.executeQuery();
			List<Favorita_Usuario> lista = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			while(rs.next()) {
				Usuario u2 = map.get(rs.getInt("id_usuario2"));
				if(u2 == null) {
					u2 = UsuarioDaoJDBC.instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario2"), u2);
				}
				Usuario u1 = new Usuario();
				u1.setId_usuario(rs.getInt("id_usuario1"));
				Favorita_Usuario obj = instantiateF_Usuario(rs, u2, u1);
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
	// CLASSE QUE DEVOLVE UMA LISTA COM OS USUÁRIOS QUE UM USUÁRIO FAVORITOU
	@Override
	public List<Favorita_Usuario> findByFavoritadoPorUsuario(Usuario usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT f.*, u.id_usuario, u.nome, u.cpf, u.tipo, u.senha "
					+ "FROM favorita_usuario f INNER JOIN usuario u "
					+ "ON f.id_usuario1 = u.id_usuario "
					+ "WHERE f.id_usuario1 = ? ");
			st.setInt(1, usuario.getId_usuario());
			rs = st.executeQuery();
			List<Favorita_Usuario> lista = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			while(rs.next()) {
				Usuario u1 = map.get(rs.getInt("id_usuario1"));
				if(u1 == null) {
					u1 = UsuarioDaoJDBC.instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario1"), u1);
				}
				Usuario u2 = new Usuario();
				u2.setId_usuario(rs.getInt("id_usuario2"));
				Favorita_Usuario obj = instantiateF_Usuario(rs, u1, u2);
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
