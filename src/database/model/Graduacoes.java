package database.model;

public class Graduacoes {
	
	private String modalidade;
	private String graduacao;
	private String guardar;
	
	public Graduacoes() {
		
		
	}
	
	public String getGraduacao() {
		return graduacao;
	}
	
	public String getModalidade() {
		return modalidade;
	}
	
	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}
	
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	public String getGuardar() {
		return guardar;
	}
	
	public void setGuardar(String guardar) {
		this.guardar = guardar;
	}
	
	@Override
	public String toString() {
		return "Modalidade: "+modalidade+"\nGraduacao: "+graduacao+"\nGuardar: "+guardar;
	}
	
}
