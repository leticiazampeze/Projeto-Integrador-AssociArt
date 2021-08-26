package dao;

import java.util.List;

import entities.Produto;
import entities.Usuario;

public interface ProdutoDao {
	
	void insert(Produto obj);
	void update(Produto obj);
	void deleteById(Integer id);
	Produto findById(Integer id);
	List<Produto> findAll();
	List<Produto> findByUsuario (Usuario usuario);
	List<Produto> findByNome (String nome);


}
