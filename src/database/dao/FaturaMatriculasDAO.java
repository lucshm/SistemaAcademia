package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.model.FaturaMatriculas;

public class FaturaMatriculasDAO extends SistemaDAO {

	private Connection conexao;
	private String select = "select * from public.Faturas_Matriculas;";
	private String selectFaturaMatriculas = "select * from public.Faturas_Matriculas where codigo_matricula = ?";
	private String insert = "INSERT INTO public.Faturas_Matriculas(codigo_matricula, data_vencimento, valor, data_pagamento, data_cancelamento) VALUES (?, ?, ?, ?, ?);";
	private String delete = "DELETE FROM public.Faturas_Matriculas where codigo_matricula=?";
	private String update = "UPDATE public.Faturas_Matriculas set data_vencimento=?, valor=?, data_pagamento=?, data_cancelamento=? where codigo_matricula=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectFaturaMatriculas;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public FaturaMatriculasDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectFaturaMatriculas = this.conexao.prepareStatement(selectFaturaMatriculas);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlFaturaMatriculas = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			FaturaMatriculas f = new FaturaMatriculas();
			
			f.setCodigo_matricula(resultado.getInt("codigo_matricula"));
			f.setData_vencimento(resultado.getString("data_vencimento"));
			f.setValor(resultado.getDouble("valor"));
			f.setData_pagamento(resultado.getString("data_pagamento"));;
			f.setData_cancelamento(resultado.getString("data_cancelamento"));
			
			arlFaturaMatriculas.add(f);
			
		}
		
		return arlFaturaMatriculas;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		FaturaMatriculas f = (FaturaMatriculas)param;
		
		pstInsert.setInt(1, f.getCodigo_matricula());
		pstInsert.setTimestamp(2, new Timestamp(new Date(f.getData_vencimento()).getTime()));
		pstInsert.setDouble(3, f.getValor());
		pstInsert.setTimestamp(4, new Timestamp(new Date(f.getData_pagamento()).getTime()));
		pstInsert.setTimestamp(5, new Timestamp(new Date(f.getData_cancelamento()).getTime()));
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
		
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		FaturaMatriculas f = (FaturaMatriculas)param;
		
		pstDelete.setInt(1, f.getCodigo_matricula());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		FaturaMatriculas f = (FaturaMatriculas)param;
		
		pstUpdate.setTimestamp(1, new Timestamp(new Date(f.getData_vencimento()).getTime()));
		pstUpdate.setDouble(2, f.getValor());
		pstUpdate.setTimestamp(3, new Timestamp(new Date(f.getData_pagamento()).getTime()));
		pstUpdate.setTimestamp(4, new Timestamp(new Date(f.getData_cancelamento()).getTime()));
		pstUpdate.setInt(5, f.getCodigo_matricula());
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}
	
	
	
}
