package database.model;

public class FaturaMatriculas {
	
	private int codigo_matricula;
	private String data_vencimento;
	private double valor;
	private String data_pagamento;
	private String data_cancelamento;
	
	public FaturaMatriculas() {
		
		
	}
	
	public int getCodigo_matricula() {
		return codigo_matricula;
	}
	
	public String getData_cancelamento() {
		return data_cancelamento;
	}
	
	public String getData_pagamento() {
		return data_pagamento;
	}
	
	public String getData_vencimento() {
		return data_vencimento;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setCodigo_matricula(int codigo_matricula) {
		this.codigo_matricula = codigo_matricula;
	}
	
	public void setData_cancelamento(String data_cancelamento) {
		this.data_cancelamento = data_cancelamento;
	}
	
	public void setData_pagamento(String data_pagamento) {
		this.data_pagamento = data_pagamento;
	}
	
	public void setData_vencimento(String data_vencimento) {
		this.data_vencimento = data_vencimento;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "Codigo Matricula: "+codigo_matricula+"\nData Vencimento: "+data_vencimento+"\nValor: "+valor+"\nData Pagamento: "+data_pagamento+
				"\nData Cancelamento: "+data_cancelamento;
	}
	
}
