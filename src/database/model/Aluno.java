package database.model;

import javax.swing.JLabel;

public class Aluno {

	private int Codigo_aluno;
	private String Aluno;
	private String Nascimento;
	private String Sexo;
	private String telefone;
	private String celular;
	private String email;
	private String obs;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
	private String cpf;
	
	public Aluno() {
		
	}
	
	public void setCodigo_aluno(int Codigo_aluno) {
		this.Codigo_aluno = Codigo_aluno;
	}
	
	public int getCodigo_aluno() {
		return Codigo_aluno;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setAluno(String Aluno) {
		this.Aluno = Aluno;
	}
	public void setNascimento(String nascimento) {
		Nascimento = nascimento;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getAluno() {
		return Aluno;
	}
	public String getNascimento() {
		return Nascimento;
	}
	public String getSexo() {
		return Sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getCelular() {
		return celular;
	}
	public String getEmail() {
		return email;
	}
	public String getObs() {
		return obs;
	}
	public String getBairro() {
		return bairro;
	}
	public String getCep() {
		return cep;
	}
	public String getCidade() {
		return cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getEstado() {
		return estado;
	}
	public String getNumero() {
		return numero;
	}
	public String getPais() {
		return pais;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		
		return "Aluno: "+Aluno +"\nNascimento: " +Nascimento +"\nSexo: " +Sexo +"\nTelefone: " +telefone
				+"\nCelular: " +celular +"\nEmail: " +email +"\nObervação: " +obs +"\nEndereço: " +endereco
				+"\nNumero: " +numero +"\nComplemento: " +complemento +"\nBairro: " +bairro +"\nCidade: " +cidade
				+"\nEstado: " +estado +"\nPais: " +pais +"\nCEP: " +cep + "\nCPF: " + cpf + "\nCodigo Aluno: " + Codigo_aluno;
	}
	
	
}
