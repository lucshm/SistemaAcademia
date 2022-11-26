package database.model;

public class Modalidades {
	
	private String modalidade;
	private String guardar;
	
	public Modalidades() {
		
		
	}
	
	public String getModalidade() {
		return modalidade;
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
		return "Modalidade: "+modalidade+"\nGuardar: "+guardar;
	}
	
}

