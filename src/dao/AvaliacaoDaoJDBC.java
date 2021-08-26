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
import entities.Avaliacao;
import entities.Produto;
import entities.Usuario;

public class AvaliacaoDaoJDBC implements AvaliacaoDao{
	private Connection conn;
	
	public AvaliacaoDaoJDBC(Connection conn) {
		this.conn=conn;
	}
	public static Avaliacao instantiateAvaliacao(ResultSet rs, Usuario u, Produto p) throws SQLException{
		Avaliacao obj = new Avaliacao();
			obj.setId_avaliacao(rs.getInt("id_avaliacao"));
			obj.setNota(rs.getDouble("nota"));
			obj.setComentario(rs.getString("comentario"));
			obj.setUsuario(u);
			obj.setProduto(p);
			return obj;
			}
	@Override
	public void insert(Avaliacao obj) {
		PreparedStatement st = null;
			try {
				st = conn.prepareStatement("INSERT INTO avaliacao "
						+ "(nota, comentario, id_usuario, id_produto) "
						+ "VALUES " + "(?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				st.setDouble(1, obj.getNota());
				st.setString(2, obj.getComentario());
				st.setInt(3, obj.getUsuario().getId_usuario());
				st.setInt(4, obj.getProduto().getId_produto());
				int rowsAffected = st.executeUpdate();
				if(rowsAffected > 0) {
					ResultSet rs = st.getGeneratedKeys();
					if(rs.next()) {
						int id = rs.getInt(1);
						obj.setId_avaliacao(id);
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
	public void update(Avaliacao obj) {
		PreparedStatement st = null;
			try {
				st = conn.prepareStatement("UPDATE avaliacao "
						+ "SET nota = ?, comentario =? " 
						+ "WHERE id_avaliacao = ? ");
				st.setDouble(1, obj.getNota());
				st.setString(2, obj.getComentario());
				st.setInt(3, obj.getId_avaliacao());
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
				st = conn.prepareStatement("DELETE FROM avaliacao WHERE id_avaliacao = ?");
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
	public List<Avaliacao> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT a.*, p.nome, p.preco, p.descricao, p.id_usuario, u.nome, u.cpf, u.tipo, u.senha "
						+ "FROM avaliacao a INNER JOIN produto p " 
						+ "ON a.id_produto = p.id_produto "
						+ "INNER JOIN usuario u "
						+ "ON a.id_usuario = u.id_usuario "
						+ "ORDER BY id_avaliacao");
				rs = st.executeQuery();
				List<Avaliacao> lista = new ArrayList<>();
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
						p = ProdutoDaoJDBC.instantiateProduto(rs,u);
						map1.put(rs.getInt("id_produto"), p);
					}
					Avaliacao obj = instantiateAvaliacao(rs, u, p);
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
	public List<Avaliacao> findByProduto(Produto produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT a.*, p.nome, p.preco, p.descricao, p.id_usuario, u.nome, u.cpf, u.tipo, u.senha "
						+ "FROM avaliacao a INNER JOIN produto p " 
						+ "ON a.id_produto = p.id_produto "
						+ "INNER JOIN usuario u "
						+ "ON a.id_usuario = u.id_usuario "
						+ "WHERE a.id_produto = ? " + "ORDER BY p.nome ");
				st.setInt(1, produto.getId_produto());
				rs = st.executeQuery();
				List<Avaliacao> lista = new ArrayList<>();
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
						p = ProdutoDaoJDBC.instantiateProduto(rs,u);
						map1.put(rs.getInt("id_produto"), p);
					}
					Avaliacao obj = instantiateAvaliacao(rs, u, p);
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
	public List<Avaliacao> findByUsuario(Usuario usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
			try {
				st = conn.prepareStatement("SELECT a.*, u.nome, u.cpf, u.tipo, u.senha, p.nome, p.preco, p.descricao, p.id_usuario "
						+ "FROM avaliacao a INNER JOIN usuario u "
						+ "ON a.id_usuario = u.id_usuario "
						+ "INNER JOIN produto p "
						+ "ON a.id_produto = p.id_produto "
						+ "WHERE a.id_usuario = ? ");
				st.setInt(1, usuario.getId_usuario());
				rs = st.executeQuery();
				List<Avaliacao> lista = new ArrayList<>();
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
						p = ProdutoDaoJDBC.instantiateProduto(rs,u);
						map1.put(rs.getInt("id_produto"), p);
					}
					Avaliacao obj = instantiateAvaliacao(rs, u, p);
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
	public Avaliacao findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT a.*, p.nome, p.preco, p.descricao, p.id_usuario , u.nome, u.cpf, u.tipo, u.senha " 
					+ "FROM avaliacao a INNER JOIN produto p "
					+ "ON a.id_produto = p.id_produto "
					+ "INNER JOIN usuario u " 
					+ "ON a.id_usuario = u.id_usuario "
					+ "WHERE a.id_avaliacao = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Usuario u = new Usuario();
				u.setId_usuario(rs.getInt("p.id_usuario"));
				Produto p = ProdutoDaoJDBC.instantiateProduto(rs, u);
				Usuario usuario1 = UsuarioDaoJDBC.instantiateUsuario(rs);
				Avaliacao obj = AvaliacaoDaoJDBC.instantiateAvaliacao(rs, usuario1, p);
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
