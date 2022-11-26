package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Planos;

public class PlanosDAO extends SistemaDAO {
	
	private Connection conexao;
	private String select = "select * from public.Planos;";
	private String selectPlanos = "select * from public.Planos where modalidade = ?";
	private String insert = "INSERT INTO public.Planos(modalidade, plano, valor_mensal) VALUES (?, ?, ?);";
	private String delete = "DELETE FROM public.Planos where plano=?";
	private String update = "UPDATE public.Planos set modalidade=?, plano=?, valor_mensal=? where plano=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public PlanosDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlPlanos = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Planos p = new Planos();
			
			p.setModalidade(resultado.getString("modalidade"));
			p.setPlano(resultado.getString("plano"));
			p.setValor_mensal(resultado.getDouble("valor_mensal"));
			
			arlPlanos.add(p);
			
		}
		
		return arlPlanos;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		Planos p = (Planos)param;
		
		pstInsert.setString(1, p.getModalidade());
		pstInsert.setString(2, p.getPlano());
		pstInsert.setDouble(3, p.getValor_mensal());
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
		
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Planos p = (Planos)param;
		
		pstDelete.setString(1, p.getPlano());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		Planos p = (Planos)param;
		
		pstUpdate.setString(1, p.getModalidade());
		pstUpdate.setString(2, p.getPlano());;
		pstUpdate.setDouble(3, p.getValor_mensal());
		pstUpdate.setString(4, p.getGuardar());;
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}
	
}
