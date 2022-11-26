package database.model;

public class MatriculaModalidade {
	
	private int codigo_matricula;
	private String modalidade;
	private String graduacao;
	private String plano;
	private String data_inicio;
	private String data_fim;
	
	public MatriculaModalidade() {
		
		
	}
	
	public int getCodigo_matricula() {
		return codigo_matricula;
	}
	
	public String getData_fim() {
		return data_fim;
	}
	
	public String getData_inicio() {
		return data_inicio;
	}
	
	public String getGraduacao() {
		return graduacao;
	}
	
	public String getModalidade() {
		return modalidade;
	}
	
	public String getPlano() {
		return plano;
	}
	
	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}
	
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	
	public void setGraduacao(String graduacao) {
		this.graduacao = graduacao;
	}
	
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
	public void setPlano(String plano) {
		this.plano = plano;
	}
	
	@Override
	public String toString() {
		return "Codigo Matricula: "+codigo_matricula+"\nModalidade: "+modalidade+"\nGraduacao: "+graduacao+"\nPlano: "+plano+
				"\nData Inicio: "+data_inicio + "\nData Fim: "+data_fim;
	}
	
}
