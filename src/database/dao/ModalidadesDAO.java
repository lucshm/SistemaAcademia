package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Modalidades;
import database.model.Modalidades;

public class ModalidadesDAO extends SistemaDAO {
	
	private Connection conexao;
	private String select = "select * from public.Modalidades;";
	private String insert = "INSERT INTO public.Modalidades(modalidade) VALUES (?);";
	private String delete = "DELETE FROM public.Modalidades where modalidade=?";
	private String update = "UPDATE public.Modalidades set modalidade=? where modalidade=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public ModalidadesDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlModalidades = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Modalidades m = new Modalidades();
			
			m.setModalidade(resultado.getString("modalidade"));
			
			arlModalidades.add(m);
			
		}
		
		return arlModalidades;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		Modalidades m = (Modalidades)param;
		
		pstInsert.setString(1, m.getModalidade());
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Modalidades m = (Modalidades)param;
		
		pstDelete.setString(1, m.getModalidade());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		Modalidades m = (Modalidades)param;
		
		pstUpdate.setString(1, m.getModalidade());
		pstUpdate.setString(2, m.getGuardar());
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}

}
