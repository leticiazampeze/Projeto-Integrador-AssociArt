package dao;

import db.DB;

public class DaoFactory {
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());		
	}
	public static ProdutoDao createProdutoDao() {
		return new ProdutoDaoJDBC(DB.getConnection());		
	}
	public static PedidoDao createPedidoDao() {
		return new PedidoDaoJDBC(DB.getConnection());		
	}
	public static ConversaDao createConversaDao() {
		return new ConversaDaoJDBC(DB.getConnection());		
	}
	public static Favorita_UsuarioDao createFavorita_UsuarioDao() {
		return new Favorita_UsuarioDaoJDBC(DB.getConnection());		
	}
	public static AvaliacaoDao createAvaliacaoDao() {
		return new AvaliacaoDaoJDBC(DB.getConnection());		
	}
	public static Pedido_ProdutoDao createPedido_ProdutoDao() {
		return new Pedido_ProdutoDaoJDBC(DB.getConnection());		
	}
	

}
