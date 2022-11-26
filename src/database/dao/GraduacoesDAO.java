package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.model.Graduacoes;

public class GraduacoesDAO extends SistemaDAO {
	
	private Connection conexao;
	private String select = "select * from public.Graduacoes;";
	private String selectModalidade = "select modalidade from public.Graduacoes where modalidade = ?;";
	private String insert = "INSERT INTO public.Graduacoes(modalidade, graduacao) VALUES (?, ?);";
	private String delete = "DELETE FROM public.Graduacoes where graduacao=?";
	private String update = "UPDATE public.Graduacoes set modalidade=?, graduacao=? where graduacao=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectModalidade;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public GraduacoesDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectModalidade = this.conexao.prepareStatement(selectModalidade);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlGraduacoes = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Graduacoes g = new Graduacoes();
			
			g.setModalidade(resultado.getString("modalidade"));
			g.setGraduacao(resultado.getString("graduacao"));
			
			arlGraduacoes.add(g);
			
		}
		
		return arlGraduacoes;
	}
	
	
	public List<Object> Select(final String modalidade) throws SQLException {
		
		pstSelect.setString(1, modalidade);
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlGraduacoes = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Graduacoes g = new Graduacoes();
			g.setGraduacao(resultado.getString("graduacao"));
			
			arlGraduacoes.add(g);
			
		}
		
		return arlGraduacoes;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		Graduacoes g = (Graduacoes)param;
		
		pstInsert.setString(1, g.getModalidade());
		pstInsert.setString(2, g.getGraduacao());
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
		
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Graduacoes g = (Graduacoes)param;
		
		pstDelete.setString(1, g.getGraduacao());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		Graduacoes g = (Graduacoes)param;
		
		pstUpdate.setString(1, g.getModalidade());
		pstUpdate.setString(2, g.getGraduacao());;
		pstUpdate.setString(3, g.getGuardar());
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}
	
}
