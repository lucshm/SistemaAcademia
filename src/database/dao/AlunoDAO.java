package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.model.Aluno;
import database.model.Graduacoes;

public class AlunoDAO extends SistemaDAO {
	
	private Connection conexao;
	private String select = "select * from public.alunos;";
	private String query = "select distinct cidade from public.alunos order by cidade";
	private String insert = "INSERT INTO public.alunos(aluno, data_nascimento, sexo, telefone, celular, email, observacao, endereco, numero, "
			+ "complemento, bairro, cidade, estado, pais, cep, cpf) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private String delete = "DELETE FROM public.alunos where codigo_aluno = ?";
	private String update = "UPDATE public.alunos set aluno=?, data_nascimento=?, sexo=?, telefone=?, "
			+ "celular=?, email=?, observacao=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=?,"
			+ "pais=?, cep=?, cpf=? where codigo_aluno = ?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstQuery;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public AlunoDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstQuery = this.conexao.prepareStatement(query);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();	
		List<Object> arlAluno = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Aluno a = new Aluno();
		
			a.setAluno(resultado.getString("aluno"));
			a.setNascimento(resultado.getString("data_nascimento"));
			a.setSexo(resultado.getString("sexo"));
			a.setTelefone(resultado.getString("telefone"));
			a.setCelular(resultado.getString("celular"));
			a.setEmail(resultado.getString("email"));
			a.setObs(resultado.getString("observacao"));
			a.setEndereco(resultado.getString("endereco"));
			a.setNumero(resultado.getString("numero"));
			a.setComplemento(resultado.getString("complemento"));
			a.setBairro(resultado.getString("bairro"));
			a.setCidade(resultado.getString("cidade"));
			a.setEstado(resultado.getString("estado"));
			a.setPais(resultado.getString("pais"));
			a.setCep(resultado.getString("CEP"));
			a.setCpf(resultado.getString("CPF"));
			a.setCodigo_aluno(resultado.getInt("codigo_aluno"));
			
			arlAluno.add(a);
		}
		
		return arlAluno;
	}
	
	@Override
	public int Insert(Object param) throws SQLException {
		
		Aluno a = (Aluno)param;
		
		pstInsert.setString(1, a.getAluno());
		pstInsert.setTimestamp(2, new Timestamp(new Date(a.getNascimento()).getTime()));
		pstInsert.setString(3, ""+a.getSexo().charAt(0));
		pstInsert.setString(4, a.getTelefone());
		pstInsert.setString(5, a.getCelular());
		pstInsert.setString(6, a.getEmail());
		pstInsert.setString(7, a.getObs());
		pstInsert.setString(8, a.getEndereco());
		pstInsert.setString(9, a.getNumero());
		pstInsert.setString(10, a.getComplemento());
		pstInsert.setString(11, a.getBairro());
		pstInsert.setString(12, a.getCidade().toUpperCase());
		pstInsert.setString(13, a.getEstado());
		pstInsert.setString(14, a.getPais());
		pstInsert.setString(15, a.getCep());
		pstInsert.setString(16, a.getCpf());
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Aluno a = (Aluno)param;
		
		pstDelete.setInt(1, a.getCodigo_aluno());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
 		Aluno a = (Aluno)param;
		
		pstUpdate.setString(1, a.getAluno());
		pstUpdate.setTimestamp(2, new Timestamp(new Date(a.getNascimento()).getTime()));
		pstUpdate.setString(3, ""+a.getSexo().charAt(0));
		pstUpdate.setString(4, a.getTelefone());
		pstUpdate.setString(5, a.getCelular());
		pstUpdate.setString(6, a.getEmail());
		pstUpdate.setString(7, a.getObs());
		pstUpdate.setString(8, a.getEndereco());
		pstUpdate.setString(9, a.getNumero());
		pstUpdate.setString(10, a.getComplemento());
		pstUpdate.setString(11, a.getBairro());
		pstUpdate.setString(12, a.getCidade());
		pstUpdate.setString(13, a.getEstado());
		pstUpdate.setString(14, a.getPais());
		pstUpdate.setString(15, a.getCep());
		pstUpdate.setString(16, a.getCpf());
		pstUpdate.setInt(17, a.getCodigo_aluno());
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}

}
