package entities;

import java.io.Serializable;

public class Avaliacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id_avaliacao;
	protected double nota;
	protected String comentario;
	protected Usuario usuario;
	protected Produto produto;
	
	public Avaliacao() {
		
	}
	public Avaliacao(Integer id_avaliacao, double nota, String comentario, Usuario usuario, Produto produto) {
		this.id_avaliacao=id_avaliacao;
		this.nota=nota;
		this.comentario=comentario;
		this.usuario=usuario;
		this.produto=produto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_avaliacao;
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
		Avaliacao other = (Avaliacao) obj;
		if (id_avaliacao != other.id_avaliacao)
			return false;
		return true;
	}

	public Integer getId_avaliacao() {
		return id_avaliacao;
	}

	public void setId_avaliacao(Integer id_avaliacao) {
		this.id_avaliacao = id_avaliacao;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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
		return "Avaliacao [id_avaliacao=" + id_avaliacao + ", nota=" + nota + ", comentario=" + comentario
				+ ", usuario=" + usuario + ", produto=" + produto + "]";
	}
	
}
