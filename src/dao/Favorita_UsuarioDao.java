package dao;

import java.util.List;

import entities.Favorita_Usuario;
import entities.Usuario;

public interface Favorita_UsuarioDao {
	void insert(Favorita_Usuario obj);
	void deleteById(Integer id);
	List <Favorita_Usuario> findByFavoritaramUsuario(Usuario usuario);
	List<Favorita_Usuario> findByFavoritadoPorUsuario(Usuario usuario);
}
