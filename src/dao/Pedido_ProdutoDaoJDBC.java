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
import entities.Pedido_Produto;
import entities.Produto;
import entities.Usuario;

public class Pedido_ProdutoDaoJDBC implements Pedido_ProdutoDao{
	private Connection conn;
	
	public Pedido_ProdutoDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	public static Pedido_Produto instantiatePedido_Produto(ResultSet rs, Pedido p, Produto pr) throws SQLException{
		Pedido_Produto obj = new Pedido_Produto();
		obj.setId_pedido_produto(rs.getInt("id_pedido_produto"));
		obj.setPedido(p);
		obj.setProduto(pr);
		return obj;
	}
	@Override
	public void insert(Pedido_Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO pedido_produto " 
					+	"(id_pedido, id_produto) " + "VALUES " +
					"(?, ?)",
			Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getPedido().getId_pedido());
			st.setInt(2, obj.getProduto().getId_produto());
			int rowsAffected = st.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_pedido_produto(id);
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
	public void update(Pedido_Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE pedido_produto " +
					"SET id_pedido = ?, id_produto = ? " 
					+ "WHERE id_pedido_produto = ? ");
			st.setInt(1, obj.getPedido().getId_pedido());
			st.setInt(2, obj.getProduto().getId_produto());
			st.setInt(3, obj.getId_pedido_produto());
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
			st = conn.prepareStatement("DELETE FROM pedido_produto WHERE id_pedido_produto = ?");
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
	public List<Pedido_Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT pp.*, p.nome, p.preco, p.descricao, p.id_produto, p.id_usuario, ped.id_pedido, ped.quantidade, ped.multa, ped.status, ped.id_usuario "
					+ "FROM pedido_produto pp INNER JOIN produto p "
					+ "ON pp.id_produto = p.id_produto "
					+ "INNER JOIN pedido ped "
					+ "ON pp.id_pedido = ped.id_pedido ");
			rs = st.executeQuery();
			List<Pedido_Produto> lista = new ArrayList<>();
			Map<Integer, Produto> map = new HashMap<>();
			Map<Integer, Pedido> map1 = new HashMap<>();
			while(rs.next()) {
				Produto p = map.get(rs.getInt("p.id_produto"));
				Pedido p1 = map1.get(rs.getInt("ped.id_pedido"));
				if(p == null) {
					Usuario u = new Usuario();
					u.setId_usuario(rs.getInt("p.id_usuario"));
					p = ProdutoDaoJDBC.instantiateProduto(rs, u);
					map.put(rs.getInt("id_produto"), p);
				}
				if(p1 == null) {
					Usuario u = new Usuario();
					u.setId_usuario(rs.getInt("ped.id_usuario"));
					p1 = PedidoDaoJDBC.instantiatePedido(rs,u);
					map1.put(rs.getInt("id_pedido"), p1);
				}
				Pedido_Produto obj = instantiatePedido_Produto(rs, p1, p);
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
	public List<Pedido_Produto> findByProduto(Produto produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT pp.*, p.nome, p.preco, p.descricao, p.id_usuario, ped.quantidade, ped.multa, ped.status, ped.id_usuario "
					+ "FROM pedido_produto pp INNER JOIN produto p "
					+ "ON pp.id_produto = p.id_produto "
					+ "INNER JOIN pedido ped "
					+ "ON pp.id_pedido = ped.id_pedido "
					+ "WHERE pp.id_produto = ?");
				st.setInt(1, produto.getId_produto());
				rs = st.executeQuery();
				List<Pedido_Produto> lista = new ArrayList<>();
				Map<Integer, Produto> map = new HashMap<>();
				Map<Integer, Pedido> map1 = new HashMap<>();
				while(rs.next()) {
					Produto p = map.get(rs.getInt("id_produto"));
					Pedido p1 = map1.get(rs.getInt("id_pedido"));
					if(p == null) {
						Usuario u = new Usuario();
						u.setId_usuario(rs.getInt("p.id_usuario"));
						p = ProdutoDaoJDBC.instantiateProduto(rs,u);
						map.put(rs.getInt("id_produto"), p);
					}
					if(p1 == null) {
						Usuario us = new Usuario();
						us.setId_usuario(rs.getInt("ped.id_usuario"));
						p1 = PedidoDaoJDBC.instantiatePedido(rs,us);
						map1.put(rs.getInt("id_pedido"), p1);
					}
					Pedido_Produto obj = instantiatePedido_Produto(rs, p1, p);
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
	public Pedido_Produto findById(Integer id) {
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT pp.*, p.nome, p.preco, p.descricao, p.id_usuario, ped.quantidade, ped.multa, ped.status, ped.id_usuario " 
						+ "FROM pedido_produto pp INNER JOIN produto p "
						+ "ON pp.id_produto = p.id_produto "
						+ "INNER JOIN pedido ped " 
						+ "ON pp.id_pedido = ped.id_pedido "
						+ "WHERE pp.id_pedido_produto = ? ");
				st.setInt(1, id);
				rs = st.executeQuery();
				if(rs.next()) {
					Usuario u = new Usuario();
					u.setId_usuario(rs.getInt("p.id_usuario"));
					Produto p = ProdutoDaoJDBC.instantiateProduto(rs, u);
					Usuario us = new Usuario();
					us.setId_usuario(rs.getInt("ped.id_usuario"));
					Pedido ped = PedidoDaoJDBC.instantiatePedido(rs, us);
					Pedido_Produto obj = Pedido_ProdutoDaoJDBC.instantiatePedido_Produto(rs, ped, p);
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
	}
