package database.model;

public class Assiduidade {

	private int codigo_matricula;
	private String data_entrada;
	
	public Assiduidade() {
		
	}
	
	public int getCodigo_matricula() {
		return codigo_matricula;
	}
	
	public String getData_entrada() {
		return data_entrada;
	}
	
	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}
	
	public void setData_entrada(String data_entrada) {
		this.data_entrada = data_entrada;
	}
	
	@Override
	public String toString() {
		return "Codigo Matricula: "+codigo_matricula+"\nData Entrada: "+data_entrada;
	}
	
	
}
