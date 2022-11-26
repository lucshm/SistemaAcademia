package database.model;

public class Usuarios {

	private String usuario;
	private String perfil;
	private String senha;
	
	public Usuarios() {
		
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	@Override
	public String toString() {
		return "Usuarios: "+usuario+"\nPerfil: "+perfil+"\nSenha: "+senha;
	}

}
