package database.model;

public class Planos {
	
	private String modalidade;
	private String plano;
	private Double valor_mensal;
	private String guardar;
	
	public Planos() {
		
		
	}
	
	public String getModalidade() {
		return modalidade;
	}
	
	public String getPlano() {
		return plano;
	}
	
	public Double getValor_mensal() {
		return valor_mensal;
	}
	
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	public void setPlano(String plano) {
		this.plano = plano;
	}
	
	public void setValor_mensal(Double valor_mensal) {
		this.valor_mensal = valor_mensal;
	}
	
	public String getGuardar() {
		return guardar;
	}
	
	public void setGuardar(String guardar) {
		this.guardar = guardar;
	}
	
	@Override
	public String toString() {
		return "Modalidade: "+modalidade+"\nPlano: "+plano+"\nValor Mensal: "+valor_mensal+"\nGuardar: "+guardar;
	}
}
