package graphics;

import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.ConnectionFactory;
import database.dao.UsuariosDAO;

public class LoginWindow extends JFrame{
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JTextField txfUsuario;
	private JPasswordField txfSenha;
	private JButton btnEntrar;
	private JButton btnCancelar;
	
	public LoginWindow() {
		setSize(300,200);
		setTitle("LOGIN");
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		lblUsuario = new JLabel("Usuário: ");
		lblUsuario.setBounds(20, 20, 50, 30);
		getContentPane().add(lblUsuario);
		
		txfUsuario = new JTextField();
		txfUsuario.setBounds(75, 22, 150, 25);
		getContentPane().add(txfUsuario);
		
		lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(20, 55, 50, 30);
		getContentPane().add(lblSenha);
		
		txfSenha = new JPasswordField();
		txfSenha.setBounds(75, 55, 150, 25);
		getContentPane().add(txfSenha);
		
		//
		//Adicionando ação no botão
		//
		btnEntrar = new JButton(new AbstractAction("Entrar") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//
				//Verificando se Usuario está vazio
				//
				if (txfUsuario.getText().isEmpty()) {
					
				}
				//
				//Verficando se Senha está vazia
				//
				else if (txfSenha.getText().isEmpty()) {
					
				}
				
				else {
					
					//
					//Fazendo Conexão
					//
					try {
						Connection connection = ConnectionFactory.getConnection
								(
									"localhost", 
									"5432", 
									"sistema", 
									"postgres", 
									"manager"
								);
						//
						// Se conexao for sucesso
						//
						if (connection != null) {
							
							UsuariosDAO dao = new UsuariosDAO(connection);
							//
							//Buscando Usuario no banco
							//
							String perfil = dao.Select(txfUsuario.getText(), txfSenha.getText());
							
							if (perfil != null) {
								//
								//Abrindo Menu de acordo com o perfil
								//
								new MenuWindow(connection, txfUsuario.getText(), perfil);
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "Usuário e senha inválidos ou não encontrado!");
							}
						}	
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
						ex.printStackTrace();
					}
				}
				
			}
		});
		btnEntrar.setBounds(35, 100, 100, 25);
		getContentPane().add(btnEntrar);
		
		btnCancelar = new JButton(new AbstractAction("CANCELAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnCancelar.setBounds(145, 100, 100, 25);
		getContentPane().add(btnCancelar);
		
		
	}
	
	
	public static void main(String[] args) {
		new LoginWindow().setVisible(true);
	}

}
