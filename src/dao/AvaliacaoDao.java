package dao;

import java.util.List;

import entities.Avaliacao;
import entities.Produto;
import entities.Usuario;

public interface AvaliacaoDao {
	
	void insert(Avaliacao obj);
	void update(Avaliacao obj);
	void deleteById(Integer id);
	Avaliacao findById(Integer id);
	List<Avaliacao> findAll();
	List<Avaliacao> findByProduto(Produto produto);
	List<Avaliacao> findByUsuario(Usuario usuario);

}
