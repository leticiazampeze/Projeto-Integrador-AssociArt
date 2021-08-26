package dao;

import java.util.List;

import entities.Conversa;
import entities.Produto;


public interface ConversaDao {
	
	void insert(Conversa obj);
	void deleteById(Integer id);
	Conversa findById(Integer id);
	List<Conversa> findByProduto(Produto produto);

}
