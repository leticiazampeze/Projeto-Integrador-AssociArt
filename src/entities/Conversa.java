package entities;

import java.io.Serializable;

public class Conversa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id_conversa;
	protected String mensagem;
	protected Usuario usuario;
	protected Produto produto;
	
	public Conversa() {
		
	}
	public Conversa (Integer id_conversa, String mensagem,Usuario usuario,Produto produto) {
		this.id_conversa=id_conversa;
		this.mensagem=mensagem;
		this.usuario=usuario;
		this.produto=produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_conversa;
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
		Conversa other = (Conversa) obj;
		if (id_conversa != other.id_conversa)
			return false;
		return true;
	}

	public Integer getId_conversa() {
		return id_conversa;
	}

	public void setId_conversa(Integer id_conversa) {
		this.id_conversa = id_conversa;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Conversa [id_conversa=" + id_conversa + ", mensagem=" + mensagem + ", usuario=" + usuario
				+ ", produto=" + produto + "]";
	}

}
