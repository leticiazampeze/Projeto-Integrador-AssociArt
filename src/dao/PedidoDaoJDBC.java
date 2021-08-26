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
import entities.Pedido;
import entities.Usuario;

public class PedidoDaoJDBC implements PedidoDao{
	private Connection conn;

		public PedidoDaoJDBC(Connection conn) {
			this.conn=conn;
		}
		public static Pedido instantiatePedido(ResultSet rs, Usuario u) throws SQLException{
			Pedido obj = new Pedido();
			obj.setId_pedido(rs.getInt("id_pedido"));
			obj.setQuantidade(rs.getInt("quantidade"));
			obj.setMulta(rs.getDouble("multa"));
			obj.setStatus(rs.getString("status"));
			obj.setUsuario(u);
			return obj;
		}
	@Override
		public void insert(Pedido obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO pedido "
					+ "(quantidade, multa, status, id_usuario) "
					+ "VALUES " + "(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getQuantidade());
			st.setDouble(2, obj.getMulta());
			st.setString(3, obj.getStatus());
			st.setInt(4, obj.getUsuario().getId_usuario());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_pedido(id);
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
		public void update(Pedido obj) {
			PreparedStatement st = null;
			try {
				st = conn.prepareStatement("UPDATE pedido "
						+ "SET quantidade = ?, multa = ?, status = ? " + "WHERE id_pedido = ?");
				st.setInt(1, obj.getQuantidade());
				st.setDouble(2, obj.getMulta());
				st.setString(3, obj.getStatus());
				st.setInt(4, obj.getId_pedido());
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
				st = conn.prepareStatement("DELETE FROM pedido WHERE id_pedido = ?");
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
		public Pedido findById(Integer id) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT pedido.*, usuario.nome, usuario.cpf, usuario.tipo, usuario.senha " 
						+ "FROM pedido INNER JOIN usuario "
						+ "ON pedido.id_usuario = usuario.id_usuario "
						+ "WHERE pedido.id_pedido = ? ");
				st.setInt(1, id);
				rs = st.executeQuery();
				if(rs.next()) {
					Usuario u = UsuarioDaoJDBC.instantiateUsuario(rs);
					Pedido obj = instantiatePedido(rs, u);
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
		public List<Pedido> findAll() {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT p.*, usuario.nome, usuario.cpf, usuario.tipo, usuario.senha "
						+ "FROM pedido p INNER JOIN usuario " 
						+ "ON p.id_usuario = usuario.id_usuario "
						+ "ORDER BY id_pedido");
				rs = st.executeQuery();
				List<Pedido> lista = new ArrayList<>();
				Map<Integer, Usuario> map = new HashMap<>();
				while(rs.next()) {
					Usuario u = map.get(rs.getInt("id_usuario"));
					if(u == null) {
					 u = UsuarioDaoJDBC.instantiateUsuario(rs);
					map.put(rs.getInt("id_usuario"), u);
				}
				Pedido obj = instantiatePedido(rs,u);
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
		public List<Pedido> findByUsuario(Usuario usuario) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT p.*, u.nome, u.cpf, u.tipo, u.senha "
						+ "FROM pedido p INNER JOIN usuario u " 
						+ "ON p.id_usuario = u.id_usuario "
						+ "WHERE p.id_usuario = ?");
				st.setInt(1, usuario.getId_usuario());
				rs = st.executeQuery();
				List<Pedido> lista = new ArrayList<>();
				Map<Integer, Usuario> map = new HashMap<>();
				while(rs.next()) {
					Usuario u = map.get(rs.getInt("id_usuario"));
					if(u == null) {
						u = UsuarioDaoJDBC.instantiateUsuario(rs);
						map.put(rs.getInt("id_usuario"), u);
					}
					Pedido obj = instantiatePedido(rs, u);
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
