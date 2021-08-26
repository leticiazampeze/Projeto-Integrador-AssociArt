package entities;

import java.io.Serializable;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer id_produto;
	protected String nome;
	protected double preco;
	protected String descricao;
	protected Usuario usuario;
	
	public Produto() {
		
	}	
	public Produto(Integer id_produto, String nome, double preco, String descricao, Usuario usuario) {
		this.id_produto=id_produto;
		this.nome=nome;
		this.preco=preco;
		this.descricao=descricao;
		this.usuario=usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_produto;
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
		Produto other = (Produto) obj;
		if (id_produto != other.id_produto)
			return false;
		return true;
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public void setId_produto(Integer id_produto) {
		this.id_produto = id_produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Produto [id_produto=" + id_produto + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao
				+ ", Usuario=" + usuario + "]";
	}

}
