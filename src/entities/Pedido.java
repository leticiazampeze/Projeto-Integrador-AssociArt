package entities;

import java.io.Serializable;

public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id_pedido;
	protected int quantidade;
	protected double multa;
	protected String status;
	protected Usuario usuario;
	
	public Pedido() {
		
	}
	public Pedido(Integer id_pedido, int quantidade, double multa, String status, Usuario usuario) {
		this.id_pedido=id_pedido;
		this.quantidade=quantidade;
		this.multa=multa;
		this.status=status;
		this.usuario=usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_pedido;
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
		Pedido other = (Pedido) obj;
		if (id_pedido != other.id_pedido)
			return false;
		return true;
	}
	public Integer getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario id_usuario) {
		this.usuario = id_usuario;
	}
	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", quantidade=" + quantidade + ", multa="
				+ multa + ", status=" + status + ", usuario=" + usuario + "]";
	}

}
