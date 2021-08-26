package dao;

import java.util.List;
import entities.Pedido_Produto;
import entities.Produto;

public interface Pedido_ProdutoDao {
	
	void insert(Pedido_Produto obj);
	void update(Pedido_Produto obj);
	void deleteById(Integer id);
	Pedido_Produto findById(Integer id);
	List<Pedido_Produto> findAll();
	List<Pedido_Produto> findByProduto (Produto produto);
}
