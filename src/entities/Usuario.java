package entities;

import java.io.Serializable;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected Integer id_usuario;
	protected String nome;
	protected String cpf;
	protected String tipo;
	protected String senha;
	
	public Usuario() {
		
	}		
	public Usuario(Integer id_usuario, String nome, String cpf, String tipo, String senha) {
			this.id_usuario=id_usuario;
			this.nome=nome;
			this.cpf=cpf;
			this.tipo=tipo;
			this.senha=senha;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_usuario;
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
		Usuario other = (Usuario) obj;
		if (id_usuario != other.id_usuario)
			return false;
		return true;
	}
	public Integer getId_usuario() {
			return id_usuario;
		}

	public void setId_usuario(Integer id_usuario) {
			this.id_usuario = id_usuario;
		}

	public String getNome() {
			return nome;
		}

	public void setNome(String nome) {
			this.nome = nome;
		}

	public String getCpf() {
			return cpf;
		}

	public void setCpf(String cpf) {
			this.cpf = cpf;
		}

	public String getTipo() {
			return tipo;
		}

	public void setTipo(String tipo) {
			this.tipo = tipo;
		}
	
	public void setSenha(String senha) {
		this.senha = senha;
	
	}
	public String getSenha() {
		return senha;
	}
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nome=" + nome + ", cpf=" + cpf + ", tipo=" + tipo + ", senha="
				+ senha + "]";
	}
}
