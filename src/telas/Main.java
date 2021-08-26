package telas;

import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

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

public class Main {

	public static void main(String[] args) {
		Connection conn = DB.getConnection();
		if (conn != null)
			System.out.println("Conexão com o banco realizada com sucesso!!!");

		Scanner t = new Scanner(System.in);

		UsuarioDao usuariodao = DaoFactory.createUsuarioDao();
		ProdutoDao produtodao = DaoFactory.createProdutoDao();
		PedidoDao pedidodao = DaoFactory.createPedidoDao();
		Pedido_ProdutoDao ppdao = DaoFactory.createPedido_ProdutoDao();
		ConversaDao conversadao = DaoFactory.createConversaDao();
		AvaliacaoDao avaliacaodao = DaoFactory.createAvaliacaoDao();
		Favorita_UsuarioDao fdao = DaoFactory.createFavorita_UsuarioDao();

		System.out.println("Digite 1 para realizar cadastro");
		System.out.println("Digite 2 para fazer login");
		int inicio = t.nextInt();

		// fazer cadastro usuario
		switch (inicio) {
		case 1:
			boolean cadastro = true;
			System.out.println("CADASTRO USUÁRIO");
			Usuario novoUsuario = new Usuario();

			System.out.println("Nome:");
			t.nextLine();
			String nomeUsuario = t.nextLine();
			novoUsuario.setNome(nomeUsuario);

			System.out.println("CPF:");
			String cpf = t.nextLine();

			// verificar se existe cpf igual no banco
				List<Usuario> lista = usuariodao.findAll();
				boolean novoCpf = false;
				for (Usuario us : lista) {
					if (us.getCpf().equals(cpf)) {
						novoCpf = false;
						break;
					} else {
						novoUsuario.setCpf(cpf);
						novoCpf = true;
					}
				}
				if(novoCpf == false) {
					System.out.println("CPF já cadastrado!");
				break;
			}

			System.out.println("1 - Comprador");
			System.out.println("2 - Vendedor");
			System.out.println("Tipo de usuário:");
			boolean tipo = true;
			int tipUsuario = t.nextInt();

			while (tipo) {

				if (tipUsuario == 1) {
					novoUsuario.setTipo("Comprador");
					tipo = false;
				}
				if (tipUsuario == 2) {
					novoUsuario.setTipo("Vendedor");
					tipo = false;
				}
				if (tipUsuario != 1 && tipUsuario != 2) {
					tipo = true;
					System.out.println("Número inválido!");
					break;
				}
			
			System.out.println("Crie uma senha (máximo 30 dígitos):");
			t.nextLine();
			String senha = t.nextLine();
			novoUsuario.setSenha(senha);

			usuariodao.insert(novoUsuario);
			System.out.println("USUÁRIO CADASTRADO");
			System.out.println(novoUsuario);
			cadastro = false;
			}
		case 2:
			System.out.println("LOGIN USUÁRIO");
			System.out.println("CPF:");
			boolean cpfTeste = true;
			t.nextLine();
			String loginCpf = t.nextLine();

			while (cpfTeste) {
				List<Usuario> listaa = usuariodao.findAll();

				for (Usuario us : listaa) {
					if (us.getCpf().equals(loginCpf)) {
						System.out.println("Senha:");
						String loginSenha = t.nextLine();
						if (us.getCpf().equals(loginCpf) && us.getSenha().equals(loginSenha)) {
							System.out.println("Login efetuado com sucesso!");
							cpfTeste = false;
						} else {
							System.out.println("CPF ou senha incorreto");
							System.out.println("Tente novamente:");
							cpfTeste = true;
							break;
						}
						if (us.getTipo().equals("Vendedor")) {
							exibeMenuVendedor();
								int atVendedor = t.nextInt();

								switch (atVendedor) {
								
								case 1:
									boolean se = true;
								System.out.println("1-Para adicionar um produto");
								System.out.println("2-Para editar informações de um produto");
								System.out.println("3-Para excluir um produto");
								System.out.println("4-Para retornar todos os seus produtos");
								int vendprod = t.nextInt();
								
								switch(vendprod) {
								case 1:
									Produto novoProduto = new Produto();
									System.out.println("ADICIONAR PRODUTO");
									System.out.println("Nome:");
									t.nextLine();
									String nome = t.nextLine();
									novoProduto.setNome(nome);
									System.out.println("Preço: R$");
									double preco = t.nextDouble();
									novoProduto.setPreco(preco);
									System.out.println("Descrição:");
									t.nextLine();
									String descricao = t.nextLine();
									novoProduto.setUsuario(us);
									novoProduto.setDescricao(descricao);
									produtodao.insert(novoProduto);
									System.out.println("PRODUTO ADICIONADO");
									System.out.println(novoProduto);
									break;

								case 2:
									System.out.println("Digite o ID do produto que será atualizado:");
									int idprod = t.nextInt();

									List<Produto> prod = produtodao.findByUsuario(us);
									boolean edprod = false;
									for (Produto p : prod) {
										if (p.getId_produto().equals(idprod)) {
											System.out.println("1-Editar o nome do produto");
											System.out.println("2-Editar o preço do produto");
											System.out.println("3-Editar a descrição do produto");
											System.out.println("O que deseja editar:");
											int editar = t.nextInt();

											if (editar == 1) {
												System.out.println("Digite o novo nome do produto:");
												t.nextLine();
												String novoNome = t.nextLine();
												p.setNome(novoNome);
												produtodao.update(p);
												System.out.println("Produto atualizado!");
												edprod = true;
											}
											if (editar == 2) {
												System.out.println("Digite o novo preço do produto:");
												double novoPreco = t.nextDouble();
												p.setPreco(novoPreco);
												produtodao.update(p);
												System.out.println("Produto atualizado!");
												edprod = true;
											}
											if (editar == 3) {
												System.out.println("Digite a nova descrição do produto:");
												t.nextLine();
												String novoDescricao = t.nextLine();
												p.setDescricao(novoDescricao);
												produtodao.update(p);
												System.out.println("Produto atualizado!");
												edprod = true;
											}
										}
									}
									if(edprod == false) {
									System.out.println(
											"Este ID não pertence a um de seus produtos ou não existe");
									}
									break;
								case 3:
									System.out.println("Digite o ID do produto que será deletado:");
									int deletaProd = t.nextInt();

									List<Produto> deletap = produtodao.findByUsuario(us);
									boolean exprod = false;
									for (Produto p : deletap) {
										if (p.getId_produto().equals(deletaProd)) {
											produtodao.deleteById(deletaProd);
											System.out.println("Produto deletado!");
											exprod = true;
											break;
										}
										}
									if(exprod == false) {
										System.out.println(
												"Este ID não pertence a um de seus produtos ou não existe");
									}
									break;
								case 4:
									System.out.println("Todos os seus produtos");
									System.out.println(produtodao.findByUsuario(us));
								}
								break;

							case 2:
								System.out.println("MENSAGENS DE SEUS PRODUTOS");
								System.out.println("Digite o ID do produto que deseja checar as mensagens:");
								int idprodutomen = t.nextInt();

								List<Produto> listaProd = produtodao.findAll();
								boolean findprod = false;
								for (Produto produto : listaProd) {
									if (produto.getId_produto().equals(idprodutomen)) {
										System.out.println(conversadao.findByProduto(produto));
										findprod = true;
										break;
									}
								}
								if(findprod == false) {
									System.out.println("Produto não encontrado");
								}
								break;
							case 3:
								System.out.println("AVALIAÇÕES DE SEUS PRODUTOS");
								System.out.println("Digite o ID do produto que deseja checar as avaliações:");
								int idprodutoava = t.nextInt();

								List<Produto> listaProdutos = produtodao.findAll();
								boolean avprod = false;
								for (Produto produtoa : listaProdutos) {
									if (produtoa.getId_produto().equals(idprodutoava)) {
										System.out.println(avaliacaodao.findByProduto(produtoa));
										avprod = true;
										break;
									} 
							
								}
								if(avprod == false) {
									System.out.println("Produto não encontrado");
								}
								break;
							case 4:
								System.out.println("1-Para retornar todos os pedidos de seus produtos");
								System.out.println("2-Para atualizar o status de seus pedidos");
								int infoped = t.nextInt();

								if (infoped == 1) {
									System.out.println("PEDIDOS DE SEUS PRODUTOS");
									System.out.println("Digite o ID do produto que deseja checar os pedidos:");
									int idprodutoped = t.nextInt();

									List<Produto> listaprod = produtodao.findByUsuario(us);
									boolean pedprod = false;
									for (Produto p : listaprod) {
										if (p.getId_produto().equals(idprodutoped)) {
											List<Pedido_Produto> listpp = ppdao.findByProduto(p);
											for (Pedido_Produto pp : listpp) {
												if (pp.getProduto().equals(p)) {
													System.out.println(ppdao.findByProduto(p));
													pedprod = true;
													break;
												} 
												}
											}
											}
									if(pedprod == false) {
										System.out.println("Este ID não pertence a um de seus produtos ou este produto não foi encontrado");
										}
							
								} else {
									System.out.println("Digite o ID do pedido que deseja atualizar o status:");
									int idped = t.nextInt();

									List<Pedido> pedidos = pedidodao.findAll();
									boolean atped = false;
									for (Pedido ped : pedidos) {
										if (ped.getId_pedido().equals(idped)) {
											System.out.println("Digite o novo status para este pedido");
											t.nextLine();
											String newStatus = t.nextLine();
											ped.setStatus(newStatus);
											pedidodao.update(ped);
											System.out.println("Status atualizado!");
											atped = true;
											break;
										}
									}
									if(atped == false) {
										System.out.println("Pedido não foi encontrado");
									}
								}
								}
								break;
						} else {
							System.out.println("Qual etapa deseja acessar?");
							menuComprador();
							int etapa = t.nextInt();

							switch (etapa) {
							case 1:
								System.out.println("PEDIDOS");
								System.out.println("1-Fazer um pedido");
								System.out.println("2-Alterar a quantidade de produtos de um pedido");
								System.out.println("3-Excluir um pedido");
								System.out.println("4-Acessar todos os seus pedidos");
								System.out.println("O que deseja fazer?");
								int ped = t.nextInt();

								if (ped == 1) {
									System.out.println("Digite o nome do produto que pretende comprar:");
									t.nextLine();
									String nomeProd = t.nextLine();
									System.out.println("Resultados:");
									System.out.println(produtodao.findByNome(nomeProd));

									System.out.println("Digite o ID do produto que deseja comprar:");
									int idcomprarp = t.nextInt();

									List<Produto> produtos = produtodao.findAll();
									boolean buy = false;
									for (Produto cprod : produtos) {
										if (cprod.getId_produto().equals(idcomprarp)) {
											System.out.println("Digite a quantidade que deseja comprar:");
											int quantidade = t.nextInt();
											Pedido pedi = new Pedido();
											pedi.setQuantidade(quantidade);
											pedi.setUsuario(us);
											pedi.setStatus("Pedido Confirmado");
											pedidodao.insert(pedi);
											Pedido_Produto pp = new Pedido_Produto();
											pp.setPedido(pedi);
											pp.setProduto(cprod);
											ppdao.insert(pp);
											System.out.println("Pedido realizado com sucesso!");
											buy = true;
										}
									}
									if(buy == false) {
										System.out.println("Produto não encontrado!");
									}
								}
								if (ped == 2) {
									System.out.println("Digite o ID do pedido que você deseja fazer alterações:");
									int idped = t.nextInt();
									System.out.println(
											"Digite a nova quantidade de produtos que deseja adicionar ao pedido:");
									int quant = t.nextInt();
									Pedido pedido = pedidodao.findById(idped);
									pedido.setQuantidade(quant);
									pedidodao.update(pedido);
									System.out.println("Pedido alterado com sucesso!");
								}
								if (ped == 3) {
									System.out.println("Digite o ID do pedido que você deseja excluir:");
									int idped = t.nextInt();
									pedidodao.deleteById(idped);
									System.out.println("Pedido excluído com sucesso!");
								}
								if (ped == 4) {
									System.out.println("Todos os seus pedidos:");
									System.out.println(pedidodao.findByUsuario(us));
								}
								break;
								
							case 2:
								System.out.println("FAVORITOS");
								System.out.println("1-Favoritar um usuário");
								System.out.println("2-Desfavoritar usuário");
								System.out.println("3-Acessar a lista de seus usuários favoritos");
								System.out.println("4-Acessar a lista de usuários que te favoritaram");
								int fav = t.nextInt();

								if (fav == 1) {
									System.out.println("Digite o ID do usuário que deseja favoritar:");
									int idfav = t.nextInt();
									
									List<Usuario> listaUsuario = usuariodao.findAll();
									boolean favo = false;
									for (Usuario u : listaUsuario) {
										if (u.getId_usuario().equals(idfav)) {
											Favorita_Usuario f = new Favorita_Usuario();
											f.setUsuario1(us);
											f.setUsuario2(u);
											fdao.insert(f);
											System.out.println("Usuário favoritado com sucesso!");
											favo = true;
											break;
											}
									}
									if(favo == false) {
										System.out.println("Usuário não encontrado!");
									}
								}
								if (fav == 2) {
									System.out.println("Digite o ID do usuário que deseja desfavoritar:");
									int idfav = t.nextInt();

									List<Favorita_Usuario> listaFavusuario = fdao.findByFavoritadoPorUsuario(us);
									for (Favorita_Usuario x : listaFavusuario) {
										if (x.getUsuario2().getId_usuario().equals(idfav)) {
											fdao.deleteById(x.getId_favorita());
											System.out.println("Usuário desfavoritado com sucesso!");
										}
									}
								}
								if (fav == 3) {
									System.out.println("Seus usuários favoritos:");
									System.out.println(fdao.findByFavoritadoPorUsuario(us));
								}
								if (fav == 4) {
									System.out.println("Usuários que te favoritaram:");
									System.out.println(fdao.findByFavoritaramUsuario(us));
								}
								break;
								
							case 3:
								System.out.println("MENSAGENS");
								System.out.println("1-Enviar mensagem para um produto");
								System.out.println("2-Excluir mensagem");
								int msg = t.nextInt();

								if (msg == 1) {
									System.out.println("Digite o ID do produto que você deseja enviar uma mensagem:");
									int idprodmsg = t.nextInt();

									List<Produto> list = produtodao.findAll();
									boolean favprod = false;
									for (Produto p : list) {
										if (p.getId_produto().equals(idprodmsg)) {
											System.out.println("Digite a mensagem que deseja enviar:");
											t.nextLine();
											String mensagem = t.nextLine();
											Conversa conversa = new Conversa();
											conversa.setUsuario(us);
											conversa.setProduto(p);
											conversa.setMensagem(mensagem);
											conversadao.insert(conversa);
											System.out.println("Mensagem enviada com sucesso!");
											favprod = true;
											break;
											
										}
									}
									if(favprod == false) {
										System.out.println("Produto não encontrado!");
										break;
									}
								}
								if (msg == 2) {
									System.out.println("Digite o ID da conversa que você gostaria de excluir:");
									int idconv = t.nextInt();
									conversadao.deleteById(idconv);
									System.out.println("Conversa deletada com sucesso!");
								}
								break;
								
							case 4:
								System.out.println("AVALIAÇÕES");
								System.out.println("1-Fazer uma avaliação de um produto");
								System.out.println("2-Editar uma avaliação de um produto");
								System.out.println("3-Excluir uma avaliação de um produto");
								System.out.println("4-Acessar todas as avaliações feitas por você");
								int av = t.nextInt();

								if (av == 1) {
									System.out.println("Digite o ID do produto a ser avaliado:");
									int idprodav = t.nextInt();

									List<Produto> listap = produtodao.findAll();
									boolean testeav = false;
									for (Produto pr : listap) {
										if (pr.getId_produto().equals(idprodav)) {
											System.out.println("Digite uma nota de 0 a 10 para o produto:");
											double nota = t.nextDouble();
											System.out.println("Digite um comentário sobre o produto:");
											t.nextLine();
											String comentario = t.nextLine();
											Avaliacao ava = new Avaliacao();
											ava.setUsuario(us);
											ava.setProduto(pr);
											ava.setNota(nota);
											ava.setComentario(comentario);
											avaliacaodao.insert(ava);
											System.out.println("Avaliação salva com sucesso!");
											testeav = true;
											break;
										}
										}
									if(testeav == false) {
										System.out.println("Produto não encontrado!");
										break;
									}
								}
								if (av == 2) {
									System.out.println("Digite o ID da avaliação que deseja editar:");
									int idav = t.nextInt();

									Avaliacao a1 = avaliacaodao.findById(idav);
									System.out.println("Digite sua nova nota:");
									a1.setNota(t.nextDouble());
									System.out.println("Digite seu novo comentário:");
									t.nextLine();
									String novoComen = t.nextLine();
									a1.setComentario(novoComen);
									avaliacaodao.update(a1);
									System.out.println("Avaliação alterada com sucesso!");
									break;
								}
								if (av == 3) {
									System.out.println("Digite o ID da avaliação a ser excluído:");
									avaliacaodao.deleteById(t.nextInt());
									System.out.println("Avaliação excluída com sucesso!");
									break;
								}
								if (av == 4) {
									System.out.println("Todas avaliações feitas por você");
									System.out.println(avaliacaodao.findByUsuario(us));
								}
								break;
								
							case 5:
								System.out.println("SUA CONTA");
								System.out.println("1-Editar sua conta");
								System.out.println("2-Excluir sua conta");
								int conta = t.nextInt();

								if (conta == 1) {
									System.out.println("Nome:");
									t.nextLine();
									String novoNome = t.nextLine();
									us.setNome(novoNome);
									System.out.println("Tipo:");
									System.out.println("1-Comprador");
									System.out.println("2-Vendedor");
									int novoTipo = t.nextInt();
									boolean editUs = true;

									while (editUs) {
										if (novoTipo == 1) {
											us.setTipo("Comprador");
											editUs = false;
											break;
										}
										if (novoTipo == 2) {
											us.setTipo("Vendedor");
											editUs = false;
											break;
										} else {
											System.out.println("Número inválido!");
											System.out.println("Tente novamente");
											editUs = true;
											break;
										}
									}
									System.out.println("Nova senha: (máximo 30 digitos)");
									t.nextLine();
									String novaSenha = t.nextLine();
									us.setSenha(novaSenha);
									usuariodao.update(us);
									System.out.println("Conta atualizada com sucesso!");
								}
								if (conta == 2) {
									usuariodao.deleteById(us.getId_usuario());
									System.out.println("Usuário deletado com sucesso!");
								}
								break;
							}
						}
								}
				}
			}
		}
	}
	public static void exibeMenuVendedor() {
		System.out.println("1-Para acessar seus produtos");
		System.out.println("2-Para acessar as mensagens de seus produtos");
		System.out.println("3-Para acessar as avaliações de seus produtos");
		System.out.println("4-Para acessar os pedidos de seus produtos");
	}
	public static void menuComprador() {
		System.out.println("1 - Pedidos");
		System.out.println("2 - Favoritos");
		System.out.println("3 - Mensagens");
		System.out.println("4 - Avaliações");
		System.out.println("5 - Sua conta");
	}
}
