package entities;

import java.io.Serializable;

public class Pedido_Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id_pedido_produto;
	protected Pedido pedido;
	protected Produto produto;
	
	public Pedido_Produto() {
		
	}	
	public Pedido_Produto(Integer id_pedido_produto, Pedido pedido, Produto produto) {
		this.id_pedido_produto=id_pedido_produto;
		this.pedido=pedido;
		this.produto=produto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_pedido_produto;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido_Produto other = (Pedido_Produto) obj;
		if (id_pedido_produto != other.id_pedido_produto)
			return false;
		return true;
	}
	public Integer getId_pedido_produto() {
		return id_pedido_produto;
	}
	public void setId_pedido_produto(Integer id_pedido_produto) {
		this.id_pedido_produto = id_pedido_produto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido id_pedido) {
		this.pedido = id_pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto id_produto) {
		this.produto = id_produto;
	}
	@Override
	public String toString() {
		return "Pedido_Produto [id_pedido_produto=" + id_pedido_produto + ", id_pedido=" + pedido + ", id_produto="
				+ produto + "]";
	}
	
}
