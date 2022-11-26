package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.model.Assiduidade;

public class AssiduidadeDAO extends SistemaDAO {

	private Connection conexao;
	private String select = "select * from public.assiduidade;";
	private String selectAssiduidade = "select * from public.assiduidade where codigo_matricula = ?";
	private String insert = "INSERT INTO public.assiduidade(codigo_matricula, data_entrada) VALUES (?, ?);";
	private String delete = "DELETE FROM public.assiduidade where codigo_matricula=?";
	private String update = "UPDATE public.assiduidade set data_entrada=? where codigo_matricula=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectAssiduidade;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public AssiduidadeDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectAssiduidade = this.conexao.prepareStatement(selectAssiduidade);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlAssiduidade = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Assiduidade a = new Assiduidade();
			
			a.setCodigo_matricula(resultado.getInt("codigo_matricula"));
			a.setData_entrada(resultado.getString("data_entrada"));
			
			arlAssiduidade.add(a);
			
		}
		
		return arlAssiduidade;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		Assiduidade a = (Assiduidade)param;
		
		pstInsert.setInt(1, a.getCodigo_matricula());
		pstInsert.setTimestamp(2, new Timestamp(new Date(a.getData_entrada()).getTime()));
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
		
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Assiduidade a = (Assiduidade)param;
		
		pstDelete.setInt(1, a.getCodigo_matricula());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		Assiduidade a = (Assiduidade)param;
		
		pstUpdate.setString(1, a.getData_entrada());
		pstUpdate.setTimestamp(2, new Timestamp(new Date(a.getData_entrada()).getTime()));
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}

}
