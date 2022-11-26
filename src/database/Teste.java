package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import database.dao.UsuariosDAO;
import database.model.Usuarios;

public class Teste {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection
								(
									"localhost", 
									"5432", 
									"sistema", 
									"postgres", 
									"manager"
								);
		if (connection != null) {
			
			int opc=0;
			
			do {
				
				String escolha = JOptionPane.showInputDialog("1 - INSERT \n2 - SELECT \n3 - DELETE \n4 - UPDATE \n5 - SAIR");
				
				if(escolha==null) {
					
					JOptionPane.showMessageDialog(null, "Cancelado...");
					System.exit(0);
				}
				
				opc = Integer.parseInt(escolha);
				UsuariosDAO dao = new UsuariosDAO(connection);
				
				if(opc==1) {
					
					Usuarios p = new Usuarios();
					
					String usuario = JOptionPane.showInputDialog("Digite o nome do usuario para ser inserido: ");
					String perfil = JOptionPane.showInputDialog("Digite o perfil do usuario para ser inserido: ");
					String senha  = JOptionPane.showInputDialog("Digite a senha do usuario para ser inserido: ");
					p.setUsuario(usuario);
					p.setPerfil(perfil);
					p.setSenha(senha);
					
					try {
						
						int fezInsert = dao.Insert(p);
						
						System.out.println(fezInsert);
						
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "Erro ao inserir: " + e.getMessage());
					}
					
					
				}
				
				else if(opc==2) {
					
					List<Object> listaPessoa = dao.Select();
					String lista = "";
					for (int i = 0; i < listaPessoa.size(); i++) {
						
						Usuarios p = (Usuarios)listaPessoa.get(i);
						lista = p + "\n----------------------\n" + lista;	
					}
					
					JOptionPane.showMessageDialog(null, lista);
				}
				
				else if(opc==3) {
					
					Usuarios p = new Usuarios();
					
					String usuario = JOptionPane.showInputDialog("Digite o nome do usuario para ser deletado: ");
					String perfil = JOptionPane.showInputDialog("Digite o perfil do usuario para ser deletado: ");
					p.setUsuario(usuario);
					p.setPerfil(perfil);
					
					long fezDelete = dao.Delete(p);
					
					System.out.println(fezDelete);
					
				}
				
				else if(opc==4) {
					
					Usuarios p = new Usuarios();
					
					String usuario = JOptionPane.showInputDialog("Digite o nome do usuario para mudar o seu perfil: ");
					String perfil = JOptionPane.showInputDialog("Digite o perfil desejado: ");
					p.setUsuario(usuario);
					p.setPerfil(perfil);
					
					long fezUpdate = dao.Update(p);
					
					System.out.println(fezUpdate);
				}
				
				else if(opc!=5 && opc>5 && opc<0) {
					JOptionPane.showMessageDialog(null, "Favor inserir uma op��o entre 1 e 11.");
				}
				
			} while(opc!=5);
			
		}
		
		else {
			System.out.println("N�O CONECTADO");
		}

	}

}
