package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.model.Usuarios;

public class UsuariosDAO extends SistemaDAO {
	
	private Connection conexao;
	private String select = "select * from public.usuarios;";
	private String selectUsuario = "select * from public.usuarios where usuario = ? and senha = ?";
	private String insert = "INSERT INTO public.usuarios(usuario, perfil, senha) VALUES (?, ?, ?);";
	private String delete = "DELETE FROM public.usuarios where usuario=?";
	private String update = "UPDATE public.usuarios set perfil=?, senha=? where usuario=?";
	
	private PreparedStatement pstSelect;
	private PreparedStatement pstSelectUsuario;
	private PreparedStatement pstInsert;
	private PreparedStatement pstDelete;
	private PreparedStatement pstUpdate;
	
	public UsuariosDAO(Connection conexao) throws SQLException {
		this.conexao = conexao;
		pstSelect = this.conexao.prepareStatement(select);
		pstSelectUsuario = this.conexao.prepareStatement(selectUsuario);
		pstInsert = this.conexao.prepareStatement(insert);
		pstDelete = this.conexao.prepareStatement(delete);
		pstUpdate = this.conexao.prepareStatement(update);
	}
	
	public String Select(String usuario, String senha) throws SQLException {
		
		String perfil = null;
		
		pstSelectUsuario.setString(1, usuario);
		pstSelectUsuario.setString(2, senha);
		ResultSet resultado = pstSelectUsuario.executeQuery();
		
		if (resultado.next()) {
			perfil = resultado.getString("perfil");
		}
		
		return perfil;
	}

	@Override
	public List<Object> Select() throws SQLException {
		
		ResultSet resultado = pstSelect.executeQuery();		
		List<Object> arlUsuarios = new ArrayList<Object>();
		
		while (resultado.next()) {
			
			Usuarios u = new Usuarios();
			u.setUsuario(resultado.getString("usuario"));
			u.setPerfil(resultado.getString("perfil"));
			u.setSenha(resultado.getString("senha"));
			
			arlUsuarios.add(u);
			
		}
		
		return arlUsuarios;
	}

	@Override
	public int Insert(Object param) throws SQLException {
		
		Usuarios u = (Usuarios)param;
		pstInsert.setString(1, u.getUsuario());
		pstInsert.setString(2, u.getPerfil());
		pstInsert.setString(3, u.getSenha());
		
		pstInsert.execute();
		
		return pstInsert.getUpdateCount();
	}

	@Override
	public long Delete(Object param) throws SQLException {
		
		Usuarios u = (Usuarios)param;
		
		pstDelete.setString(1, u.getUsuario());
		
		pstDelete.execute();
		return pstDelete.getUpdateCount();
	}

	@Override
	public long Update(Object param) throws SQLException {
		
		Usuarios u = (Usuarios)param;
		
		pstUpdate.setString(1, u.getPerfil());
		pstUpdate.setString(2, u.getSenha());
		pstUpdate.setString(3, u.getUsuario());
		
		pstUpdate.execute();
		return pstUpdate.getUpdateCount();
	}

}
