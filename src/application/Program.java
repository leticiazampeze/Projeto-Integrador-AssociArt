package application;

import java.sql.Connection;

import dao.AvaliacaoDao;
import dao.ConversaDao;
import dao.DaoFactory;
import dao.Favorita_UsuarioDao;
import dao.PedidoDao;
import dao.Pedido_ProdutoDao;
import dao.ProdutoDao;
import dao.UsuarioDao;
import db.DB;
import entities.Avaliacao;
import entities.Conversa;
import entities.Favorita_Usuario;
import entities.Pedido;
import entities.Pedido_Produto;
import entities.Produto;
import entities.Usuario;

public class Program {

	public static void main(String[] args) {
		Connection conn = DB.getConnection();
		if(conn != null)
			System.out.println("Conexão com o banco realizada com sucesso!!!");

		/*Usuario usuario = new Usuario(null, "Letícia", "123.456.789-10", "Comprador", "1234");
		System.out.println(usuario);
		
		UsuarioDao u = DaoFactory.createUsuarioDao();
		
		System.out.println("------------------TESTE INSERIR USUÁRIO------------------");
		u.insert(usuario);
		System.out.println("Inserted! New Id = " + usuario.getId_usuario());
		Usuario usuario2 = new Usuario(null, "Lucas", "449.823.843-94", "Vendedor", "456");
		Usuario usuario3 = new Usuario(null, "João", "943.438.329-32", "Vendedor", "789");
		Usuario usuario4 = new Usuario(null, "Ana", "939.329.939-82", "Comprador", "1011");
		u.insert(usuario2);
		u.insert(usuario3);
		u.insert(usuario4);
		
		System.out.println("-----------------TESTE findById USUARIO----------------");
		Usuario testeFindById = u.findById(1);
		System.out.println(testeFindById);
		
		System.out.println("-----------------TESTE UPDATE USUARIO-------------------");
		Usuario x = u.findById(1);
		x.setNome("Maria");
		u.update(x);
		System.out.println("Update completed");
		
		System.out.println("----------------TESTE FINDALL USUARIO---------------");
		System.out.println("Lista de todos usuários: " + u.findAll());
		
		System.out.println("--------------------TESTE DELETE USUARIO---------------");
		u.deleteById(1);
		System.out.println("Delete completed");*/
		
		/*Usuario usuario5 = new Usuario(null, "t", "93932", "Vendedor", "senha");
		u.insert(usuario5);
		Usuario usuarioo = new Usuario(null, "kdkf", "49", "Vendedor", "1");
		u.insert(usuarioo);
		System.out.println("Inserted, id= " + usuario5.getId_usuario());
		System.out.println("Inserted, id= " + usuarioo.getId_usuario());
		Produto p1 = new Produto(null, "camiseta", 25.0, "crochê", usuarioo);
		System.out.println(p1);
		//Produto p2 = new Produto(null, "Cachecol", 15.0, "Cachecol de crochê", usuario5);
		//System.out.println(p2);
		ProdutoDao p = DaoFactory.createProdutoDao();
		System.out.println("------------------TESTE INSERIR PRODUTO------------------");
				p.insert(p1);
				System.out.println("Inserted! New Id = " + p1.getId_produto());
				//p.insert(p2);
				//System.out.println("Inserted! New Id = " + p2.getId_produto());
				
				System.out.println("-----------------TESTE findById PRODUTO----------------");
				Produto testeFindById = p.findById(1);
				System.out.println(testeFindById);
		
				System.out.println("-----------------TESTE UPDATE PRODUTO-------------------");
				Produto x = p.findById(2);
				x.setPreco(30.0);
				p.update(x);
				System.out.println("Update completed");
		
				System.out.println("----------------TESTE FINDALL PRODUTO---------------");
				System.out.println("Lista de todos usuários: " + p.findAll());
				
				System.out.println("------------TESTE FINDBYUSUARIO PRODUTO----------------");
				System.out.println("Produtos: " + p.findByUsuario(usuarioo));
		
				/*System.out.println("------------TESTE FINDBYNAME PRODUTO----------------");
				System.out.println("Produtos: " + p.findByNome("camiseta"));
				
				System.out.println("--------------------TESTE DELETE PRODUTO---------------");
				p.deleteById(1);
				System.out.println("Delete completed");*/
		
				/*Usuario usuario6 = new Usuario(null, "rekfy", "0333", "Comprador", "123");
				u.insert(usuario6);
				Usuario usuario9 = new Usuario(null, "flerlfe", "460", "Comprador", "1000");
				u.insert(usuario9);
				System.out.println("Inserted, id= " + usuario6.getId_usuario());
				System.out.println("Inserted, id= " + usuario9.getId_usuario());
				Pedido pedido1 = new Pedido(null, 2, 0.00, "Enviando", usuario6);
				System.out.println(pedido1);
				Pedido pedido2 = new Pedido(null, 2, 0.00, "Saiu para entrega", usuario9);
				System.out.println(pedido2);
				PedidoDao pedido = DaoFactory.createPedidoDao();
				System.out.println("------------------TESTE INSERIR PEDIDO------------------");
						pedido.insert(pedido1);
						pedido.insert(pedido2);
						System.out.println("Inserted! New Id = " + pedido1.getId_pedido());
						System.out.println("Inserted! New Id = " + pedido2.getId_pedido());
						
						System.out.println("-----------------TESTE findById PEDIDO----------------");
						Pedido findById = pedido.findById(1);
						System.out.println(findById);
				
				System.out.println("-----------------TESTE UPDATE PEDIDO-------------------");
				Pedido i = pedido.findById(1);
				i.setMulta(10.0);
				pedido.update(i);
				System.out.println("Update completed");
				
				System.out.println("----------------TESTE FINDALL PEDIDO---------------");
				System.out.println("Lista de todos os pedidos: " + pedido.findAll());
				
				System.out.println("------------TESTE FINDBYUSUARIO PEDIDO----------------");
				System.out.println("Pedidos: " + pedido.findByUsuario(usuario9));
				
				System.out.println("--------------------TESTE DELETE PEDIDO---------------");
				pedido.deleteById(1);
				System.out.println("Delete completed");*/
			
				/*Usuario usuario7 = new Usuario(null, "rekfy", "2032320", "Comprador", "leticia");
				Usuario usuario8 = new Usuario(null, "ldsswe", "203432", "Vendedor", "9090");
				u.insert(usuario7);
				u.insert(usuario8);
				System.out.println("Inserted, id= " + usuario7.getId_usuario());
				System.out.println("Inserted, id= " + usuario8.getId_usuario());
				Favorita_Usuario f = new Favorita_Usuario(null, usuario7, usuario8);
				System.out.println(f);
				Usuario usuario10 = new Usuario(null, "fkeklw", "8", "Vendedor", "1234");
				Usuario usuario11 = new Usuario(null, "ffvdlwe", "9", "Comprador", "50100");
				u.insert(usuario10);
				u.insert(usuario11);
				System.out.println("Inserted, id= " + usuario10.getId_usuario());
				System.out.println("Inserted, id= " + usuario11.getId_usuario());
				Favorita_Usuario f2 = new Favorita_Usuario(null, usuario10, usuario8);
				System.out.println(f2);
				Favorita_Usuario f3 = new Favorita_Usuario(null, usuario7, usuario11);
				Favorita_UsuarioDao fu = DaoFactory.createFavorita_UsuarioDao();
				System.out.println("------------------TESTE INSERIR FAVORITA_USUARIO------------------");
						fu.insert(f);
						fu.insert(f2);
						fu.insert(f3);
						System.out.println("Inserted! New Id = " + f.getId_favorita());
						System.out.println("Inserted! New Id = " + f2.getId_favorita());
						System.out.println("Inserted! New Id = " + f3.getId_favorita());
				
				System.out.println("------------TESTE FINDBYFAVORITARAMUSUARIO FAVORITA_USUARIO----------------");
				System.out.println("Favoritaram o Usuário: " + fu.findByFavoritaramUsuario(usuario8));
				
				System.out.println("------------TESTE FINDBYFAVORITADOPORUSUARIO FAVORITA_USUARIO----------------");
				System.out.println("Favoritados pelo Usuário: " + fu.findByFavoritadoPorUsuario(usuario7));

				System.out.println("--------------------TESTE DELETE FAVORITA_USUARIO---------------");
				fu.deleteById(1);
				System.out.println("Delete completed");*/
	
				/*System.out.println("-------------TESTE INSERIR PEDIDO_PRODUTO--------------");
				Usuario usuariopp1 = new Usuario(null, "ana", "29200", "Comprador", "122");
				Usuario usuariopp2 = new Usuario(null, "joao", "91", "Vendedor", "889898");
				u.insert(usuariopp1);
				u.insert(usuariopp2);
				System.out.println("Id = " + usuariopp1.getId_usuario());
				System.out.println("Id = " + usuariopp2.getId_usuario());
				Pedido pedidopp = new Pedido(null, 2, 0.00, "Enviando", usuariopp1);
				Produto produtopp = new Produto(null, "Calça", 50.0, "Jeans", usuariopp2);
				Produto produto2 = new Produto(null, "Jaqueta", 40.0, "Jeans", usuariopp2);
				p.insert(produtopp);
				p.insert(produto2);
				pedido.insert(pedidopp);
				System.out.println("Id pedido = " + pedidopp.getId_pedido());
				System.out.println("Id pedido = " + produto2.getId_produto());
				System.out.println("Id produto = " + produtopp.getId_produto());
				Pedido_Produto pedidop = new Pedido_Produto(null, pedidopp, produtopp);
				Pedido_ProdutoDao pp = DaoFactory.createPedido_ProdutoDao();
				pp.insert(pedidop);
				
				System.out.println("-----------TESTE FINDBYPRODUTO PEDIDO_PRODUTO------------");
				System.out.println("Pedido_Produto por produto: " + pp.findByProduto(produtopp));
		
				System.out.println("------------TESTE FINDALL PEDIDO_PRODUTO---------------");
				System.out.println("Pedido_Produto: " + pp.findAll());
				
				System.out.println("--------------TESTE FINDBYID PEDIDO_PRODUTO--------------");
				System.out.println("Pedido_Produto: " + pp.findById(1));
				
				System.out.println("------------TESTE UPDATE PEDIDO_PRODUTO---------------");
				Pedido_Produto y = pp.findById(1);
				y.setProduto(produto2);;
				System.out.println("Updated!");
				
				System.out.println("-------------TESTE DELETE PEDIDO_PRODUTO--------------");
				pp.deleteById(1);
				System.out.println("Deleted!");*/
		
				/*System.out.println("--------------TESTE INSERT CONVERSA-------------");
				Usuario usuarioc1 = new Usuario(null, "maria", "0103", "Comprador", "123");
				Usuario usuarioc2 = new Usuario(null, "pedro", "4433444", "Vendedor", "7464");
				u.insert(usuarioc1);
				u.insert(usuarioc2);
				System.out.println("Id = " + usuarioc1.getId_usuario());
				System.out.println("Id = " + usuarioc2.getId_usuario());
				Produto produtoc = new Produto(null, "Calça", 50.0, "Jeans", usuarioc2);
				p.insert(produtoc);
				Conversa c1 = new Conversa(null, "Oi", usuarioc1, produtoc);
				ConversaDao c = DaoFactory.createConversaDao();
				c.insert(c1);
				System.out.println("Inserted! New Id = " + c1.getId_conversa());
				
				System.out.println("--------------TESTE FINDBYPRODUTO CONVERSA--------------");
				System.out.println("Conversas: " + c.findByProduto(produtoc));
		
				System.out.println("-------------TESTE FINDBYID CONVERSA----------------");
				System.out.println("Conversas: " + c.findById(1));
	
				System.out.println("--------------TESTE DELETEBYID CONVERSA--------------");
				c.deleteById(1);
				System.out.println("Deleted");*/
		
				/*System.out.println("-------------TESTE INSERT AVALIACAO---------------");
				Usuario usuarioa1 = new Usuario(null, "junior", "310000", "Comprador", "123");
				Usuario usuarioa2 = new Usuario(null, "cintia", "22000", "Vendedor","456");
				u.insert(usuarioa1);
				u.insert(usuarioa2);
				System.out.println("Id = " + usuarioa1.getId_usuario());
				System.out.println("Id = " + usuarioa2.getId_usuario());
				Produto produtoa = new Produto(null, "Cachecol", 50.0, "cachecol de lã", usuarioa2);
				p.insert(produtoa);
				Avaliacao a1 = new Avaliacao(null, 9, "Muito bom!", usuarioa1, produtoa);
				AvaliacaoDao a = DaoFactory.createAvaliacaoDao();
				a.insert(a1);
				System.out.println("Inserted! New Id: " + a1.getId_avaliacao());
		
				System.out.println("-----------TESTE FINDBYID AVALIACAO----------");
				System.out.println("Avaliação: " + a.findById(1));
				
				System.out.println("-----------TESTE FINDBYPRODUTO AVALIACAO----------");
				System.out.println("Avaliações: " + a.findByProduto(produtoa));
				
				System.out.println("-----------TESTE FINDBYUSUARIO AVALIACAO----------");
				System.out.println("Avaliados: " + a.findByUsuario(usuarioa1));
				
				System.out.println("-----------TESTE UPDATE AVALIACAO--------------");
				Avaliacao z = a.findById(1);
				z.setComentario("Amei");
				a.update(z);
				System.out.println("Updated!");
				
				System.out.println("--------------TESTE FINDALL AVALIACAO-------------");
				System.out.println("Avaliações: " + a.findAll());
				
				System.out.println("---------------TESTE DELETEBYID AVALIACAO-----------");
				a.deleteById(1);
				System.out.println("Deleted!");*/
		
		DB.closeConnection();
	}


	}