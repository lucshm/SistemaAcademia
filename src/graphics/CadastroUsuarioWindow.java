package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.dao.AlunoDAO;
import database.dao.UsuariosDAO;
import database.model.Aluno;
import database.model.Usuarios;

public class CadastroUsuarioWindow extends JFrame {
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblPerfil;
	private JTextField txfUsuario;
	private JPasswordField txfSenha;
	private JComboBox<String> txfPerfil;
	private JSeparator separator;
	private JButton btnCancelar;
	private UsuariosDAO dao;
	private boolean isInsert;
	private String usuario = null;
	
	public CadastroUsuarioWindow(Connection conexao) {
		
		try {
			dao = new UsuariosDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("Cadastro Usuario");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btnBuscar = new JButton(new AbstractAction("BUSCAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BuscarUsuarioWindow bu = new BuscarUsuarioWindow(conexao);
				bu.setVisible(true);
				Usuarios u = bu.getUsuarioGuardado();
				
				if (u != null) {
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					
					usuario = u.getUsuario();
					txfUsuario.setText(u.getUsuario());	
					txfSenha.setText(u.getSenha());
					txfPerfil.setSelectedItem(u.getPerfil());
				}				
				
			}
		});
		btnBuscar.setBounds(80, 20, 110, 50);
		getContentPane().add(btnBuscar);
		
		btnAdicionar = new JButton(new AbstractAction("ADICIONAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isInsert = true;
				btnSalvar.setEnabled(true);
				btnRemover.setEnabled(true);
				componentesTela(true);
			}
		});
		btnAdicionar.setBounds(240, 20, 110, 50);
		getContentPane().add(btnAdicionar);
		
		btnRemover = new JButton(new AbstractAction("REMOVER") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Usuarios u = new Usuarios();
					u.setUsuario(usuario);
					dao.Delete(u);
					
					JOptionPane.showMessageDialog(null, "Executado com Sucesso");
					componentesTela(false);
					btnRemover.setEnabled(false);
					btnSalvar.setEnabled(false);
					txfUsuario.setText(null);
					txfSenha.setText(null);
					txfPerfil.setSelectedIndex(-1);
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.getStackTrace();
				}
				
			}
		});
		btnRemover.setEnabled(false);
		btnRemover.setBounds(400, 20, 110, 50);
		getContentPane().add(btnRemover);
		
		btnSalvar = new JButton(new AbstractAction("SALVAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					Usuarios u = new Usuarios();
					
					u.setSenha(txfSenha.getText());
					u.setPerfil(txfPerfil.getSelectedItem().toString());
					u.setUsuario(txfUsuario.getText());
					
					if (isInsert) {
						dao.Insert(u);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						dao.Update(u);
						JOptionPane.showMessageDialog(null, "Update realizado com sucesso.");
					}
					
					componentesTela(false);
					btnRemover.setEnabled(false);
					btnSalvar.setEnabled(false);
					txfUsuario.setText(null);
					txfSenha.setText(null);
					txfPerfil.setSelectedIndex(-1);
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(560, 20, 110, 50);
		btnSalvar.setEnabled(false);
		getContentPane().add(btnSalvar);
	
		separator = new JSeparator();
		separator.setBounds(0, 90 , 780, 2);
		getContentPane().add(separator);
		
		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(40, 110, 150, 30);
		getContentPane().add(lblUsuario);
		
		txfUsuario = new JTextField();
		txfUsuario.setBounds(125, 110, 180, 30);
		getContentPane().add(txfUsuario);
		
		lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(40, 150, 80, 30);
		getContentPane().add(lblSenha);
		
		txfSenha = new JPasswordField();
		txfSenha.setBounds(125, 150, 180, 30);
		getContentPane().add(txfSenha);
		
		lblPerfil = new JLabel("Perfil: ");
		lblPerfil.setBounds(40, 190, 80, 30);
		getContentPane().add(lblPerfil);
		
		String Funcoes[] = {"Admin" ,"Cadastral", "Financeiro"};
		txfPerfil = new JComboBox<String>(Funcoes);
		txfPerfil.setSelectedIndex(-1);
		txfPerfil.setBounds(125, 190, 180, 30);
		getContentPane().add(txfPerfil);
		
		
		btnCancelar = new JButton(new AbstractAction("CANCELAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancelar.setBounds(600, 450, 100, 30);
		getContentPane().add(btnCancelar);
		
		btnLimpar = new JButton(new AbstractAction("LIMPAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				desativarComponentes();
			}
		});
		btnLimpar.setBounds(480, 450, 100, 30);
		getContentPane().add(btnLimpar);
		
		componentesTela(false);
		
	}
	
	private void desativarComponentes() {
		
		for (Component comp : getContentPane().getComponents()) {
			comp.setEnabled(false);
		}
		btnBuscar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnAdicionar.setEnabled(true);
		btnLimpar.setEnabled(true);
		txfUsuario.setText(null);
		txfSenha.setText(null);
		txfPerfil.setSelectedIndex(-1);
	}
	
	private void componentesTela(boolean habilitar) {
		for (Component comp : getContentPane().getComponents()) {
			if(comp instanceof JButton) {
				continue;
			}
			comp.setEnabled(habilitar);
		}
			
	}

}
