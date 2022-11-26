package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import database.model.Matricula;
import database.model.Matricula;

public class MatriculaDAO extends SistemaDAO {
	
	private Connection conexao;
	private String select = "select * from public.matriculas;";
	private String selectMatricula = "select * from public.matriculas where codigo_matricula = ?";
	private String insert = "INSERT INTO public.matriculas(codigo_aluno, data_matricula, dia_vencimento, data_encerramento) VALUES (?, ?, ?, ?);";
	private String delete = "DELETE FROM public.matriculas where codigo_matricula=?";
	private String update = "UPDATE public.matriculas set codigo_aluno=?, data_matricula=?, dia_vencimento=?, data_encerramento=? where codigo_matricula=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectMatricula;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public MatriculaDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectMatricula = this.conexao.prepareStatement(selectMatricula);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlMatricula = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Matricula m = new Matricula();
			
			m.setCodigo_matricula(resultado.getInt("codigo_matricula"));
			m.setCodigo_aluno(resultado.getInt("codigo_aluno"));
			m.setData_matricula(resultado.getString("data_matricula"));
			m.setDia_vencimento(resultado.getInt("dia_vencimento"));
			m.setData_encerramento(resultado.getString("data_encerramento"));
			
			arlMatricula.add(m);
			
		}
		
		return arlMatricula;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		Matricula m = (Matricula)param;
		
		pstInsert.setInt(1, m.getCodigo_aluno());
		pstInsert.setTimestamp(2, new Timestamp(new Date(m.getData_matricula()).getTime()));
		pstInsert.setInt(3, m.getDia_vencimento());
		pstInsert.setTimestamp(4, new Timestamp(new Date(m.getData_encerramento()).getTime()));
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Matricula m = (Matricula)param;
		
		pstDelete.setInt(1, m.getCodigo_matricula());
		
		pstDelete.execute();
		
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		Matricula m = (Matricula)param;
		
		pstUpdate.setInt(1, m.getCodigo_aluno());
		pstUpdate.setString(2, m.getData_matricula());
		pstUpdate.setInt(3, m.getDia_vencimento());
		pstUpdate.setString(4, m.getData_encerramento());
		pstUpdate.setInt(5, m.getCodigo_matricula());
		
		System.out.println(pstUpdate);
		
		pstUpdate.execute();
		
		return pstUpdate.getUpdateCount();
	}

}
