package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.model.MatriculaModalidade;

public class MatriculaModalidadeDAO extends SistemaDAO {

	private Connection conexao;
	private String select = "select * from public.matriculas_modalidades;";
	private String selectMatricula = "select * from public.matriculas_modalidades where codigo_matricula = ?";
	private String insert = "INSERT INTO public.matriculas_modalidades(codigo_matricula, modalidade, graduacao, plano, data_inicio, data_fim) VALUES (?, ?, ?, ?, ?, ?);";
	private String delete = "DELETE FROM public.matriculas_modalidades where codigo_matricula=?";
	private String update = "UPDATE public.matriculas_modalidades set codigo_matricula=?, modalidade=?, graduacao=?, plano=?, data_inicio=?, data_fim=? where codigo_matricula=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectMatriculaModalidade;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public MatriculaModalidadeDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectMatriculaModalidade = this.conexao.prepareStatement(selectMatricula);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlMatriculaModalidade = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			MatriculaModalidade mm = new MatriculaModalidade();
			
			mm.setCodigo_matricula(Integer.parseInt(resultado.getString("codigo_matricula")));
			mm.setModalidade(resultado.getString("modalidade"));
			mm.setGraduacao(resultado.getString("graduacao"));
			mm.setPlano(resultado.getString("plano"));
			mm.setData_fim(resultado.getString("data_fim"));
			mm.setData_inicio(resultado.getString("data_inicio"));
			
			arlMatriculaModalidade.add(mm);
			
		}
		
		return arlMatriculaModalidade;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		MatriculaModalidade mm = (MatriculaModalidade)param;
		
		pstInsert.setInt(1, mm.getCodigo_matricula());
		pstInsert.setString(2, mm.getModalidade());
		pstInsert.setString(3, mm.getGraduacao());
		pstInsert.setString(4, mm.getPlano());
		pstInsert.setTimestamp(5, new Timestamp(new Date(mm.getData_inicio()).getTime()));
		pstInsert.setTimestamp(6, new Timestamp(new Date(mm.getData_fim()).getTime()));
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		MatriculaModalidade mm = (MatriculaModalidade)param;
		
		pstDelete.setInt(1, mm.getCodigo_matricula());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		MatriculaModalidade mm = (MatriculaModalidade)param;
		
		pstUpdate.setInt(1, mm.getCodigo_matricula());
		pstUpdate.setString(2, mm.getModalidade());
		pstUpdate.setString(3, mm.getGraduacao());
		pstUpdate.setString(4, mm.getPlano());
		pstUpdate.setTimestamp(5, new Timestamp(new Date(mm.getData_inicio()).getTime()));
		pstUpdate.setTimestamp(6, new Timestamp(new Date(mm.getData_fim()).getTime()));
		pstUpdate.setInt(7, mm.getCodigo_matricula());
		
		pstUpdate.execute();
		
		return pstUpdate.getUpdateCount();
	}
}
