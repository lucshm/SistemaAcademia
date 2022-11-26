package database.model;

public class Login {
	
	private String usuario;
	private String senha;
	
	public Login() {
		
		
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	@Override
	public String toString() {
		return "Usuario: "+usuario+"\nSenha: "+senha;
	}
	
}
