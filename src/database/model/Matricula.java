package database.model;

public class Matricula {
	
	private int codigo_matricula;
	private int codigo_aluno;
	private String data_matricula;
	private int dia_vencimento;
	private String data_encerramento;
	
	public Matricula() {
		
		
	}
	
	public int getCodigo_aluno() {
		return codigo_aluno;
	}
	
	public int getCodigo_matricula() {
		return codigo_matricula;
	}
	
	public String getData_encerramento() {
		return data_encerramento;
	}
	
	public String getData_matricula() {
		return data_matricula;
	}
	
	public int getDia_vencimento() {
		return dia_vencimento;
	}
	
	public void setCodigo_aluno(int codigo_aluno) {
		this.codigo_aluno = codigo_aluno;
	}
	
	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}
	
	public void setData_encerramento(String data_encerramento) {
		this.data_encerramento = data_encerramento;
	}
	
	public void setData_matricula(String data_matricula) {
		this.data_matricula = data_matricula;
	}
	
	public void setDia_vencimento(int dia_vencimento) {
		this.dia_vencimento = dia_vencimento;
	}
	
	@Override
	public String toString() {
		return "Codigo Matricula: "+codigo_matricula+"\nCodigo Aluno: "+codigo_aluno+"\nData Matricula: "+data_matricula+"\nDia Vencimento: "+dia_vencimento+
				"\nData Encerramento: "+data_encerramento;
	}
	
}
