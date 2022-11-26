package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import database.dao.ModalidadesDAO;
import database.model.MatriculaModalidade;
import database.model.Modalidades;

public class CadastroModalidadesWindow extends JFrame {
	
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblModalidade;
	private JSeparator separator;
	private JButton btnCancelar;
	private JTextField txfModalidade;
	private ModalidadesDAO dao;
	private boolean isInsert;
	private String nomeModalidade;
	
	public CadastroModalidadesWindow(Connection conexao) {
		
		try {
			dao = new ModalidadesDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("MODALIDADES");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		btnBuscar = new JButton(new AbstractAction("BUSCAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BuscarModalidadesWindow bm = new BuscarModalidadesWindow(conexao);
				bm.setVisible(true);
				Modalidades m = bm.getModalidadesGuardada();
				
				if (m != null) {
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					
					nomeModalidade = m.getModalidade();
					txfModalidade.setText(m.getModalidade());
					
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
					Modalidades m = new Modalidades();
					m.setModalidade(nomeModalidade);
					dao.Delete(m);
					
					JOptionPane.showMessageDialog(null, "Executado com Sucesso");
					desativarComponentes();
					
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
					
					Modalidades m = new Modalidades();
					
					m.setModalidade(txfModalidade.getText());
					
					if (isInsert) {
						dao.Insert(m);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						m.setGuardar(nomeModalidade);
						dao.Update(m);
						JOptionPane.showMessageDialog(null, "Update realizado com sucesso.");
					}
					
					desativarComponentes();
					
				} 
				catch (SQLException e1) {
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
		
		lblModalidade = new JLabel("Modalidade: ");
		lblModalidade.setBounds(40, 110, 110, 30);
		getContentPane().add(lblModalidade);
		
		txfModalidade = new JTextField();
		txfModalidade.setBounds(135, 110, 180, 30);
		getContentPane().add(txfModalidade);
		
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
		txfModalidade.setText(null);
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
