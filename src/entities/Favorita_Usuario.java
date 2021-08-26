package entities;

import java.io.Serializable;

public class Favorita_Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer id_favorita;
	protected Usuario usuario1;
	protected Usuario usuario2;
	
	public Favorita_Usuario() {
		
	}
	public Favorita_Usuario (Integer id_favorita, Usuario id_usuario1, Usuario id_usuario2) {
		this.id_favorita=id_favorita;
		this.usuario1=id_usuario1;
		this.usuario2=id_usuario2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_favorita;
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
		Favorita_Usuario other = (Favorita_Usuario) obj;
		if (id_favorita != other.id_favorita)
			return false;
		return true;
	}
	public Integer getId_favorita() {
		return id_favorita;
	}
	public void setId_favorita(Integer id_favorita) {
		this.id_favorita = id_favorita;
	}
	public Usuario getUsuario1() {
		return usuario1;
	}
	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}
	public Usuario getUsuario2() {
		return usuario2;
	}
	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	@Override
	public String toString() {
		return "Favorita_Usuario [id_favorita=" + id_favorita + ", usuario1=" + usuario1 + ", usuario2=" + usuario2
				+ "]";
	}
}
