package dao;

import java.util.List;

import entities.Pedido;
import entities.Usuario;

public interface PedidoDao {
	
	void insert(Pedido obj);
	void update(Pedido obj);
	void deleteById(Integer id);
	Pedido findById(Integer id);
	List<Pedido> findAll();
	List<Pedido> findByUsuario(Usuario usuario);
}
