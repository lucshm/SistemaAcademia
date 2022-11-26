package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Login;

public class LoginDAO extends SistemaDAO {

	private Connection conexao;
	private String select = "select * from public.usuarios;";
	private String selectUsuarios = "select * from public.usuarios where usuario = ?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectLogin;
	
	public LoginDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectLogin = this.conexao.prepareStatement(selectUsuarios);
	}
	
	
	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlLogin = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Login l = new Login();
			
			l.setUsuario(resultado.getString("usuario"));
			l.setSenha(resultado.getString("senha"));
			
			arlLogin.add(l);
			
		}
		
		return arlLogin;
	}
	
	@Override
	public int Insert(Object param) throws SQLException {
		
		
		return 0;
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		return 0;
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		return 0;
	}
	
	
}
