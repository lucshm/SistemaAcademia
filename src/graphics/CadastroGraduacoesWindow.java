package graphics;

import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.dao.GraduacoesDAO;
import database.dao.PlanosDAO;
import database.model.Graduacoes;
import database.model.Planos;

public class CadastroGraduacoesWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblModalidade;
	private JLabel lblGraduacao;
	private JSeparator separator;
	private JButton btnCancelar;
	private JComboBox<String> txfModalidade;
	private JTextField txfGraduacao;
	private GraduacoesDAO dao;
	private boolean isInsert;
	private String nomeGraduacao;
	
	public CadastroGraduacoesWindow(Connection conexao) {
		
		try {
			dao = new GraduacoesDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("GRADUAÇÕES");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		btnBuscar = new JButton(new AbstractAction("BUSCAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BuscarGraduacoesWindow bg = new BuscarGraduacoesWindow(conexao);
				bg.setVisible(true);
				Graduacoes g = bg.getGraduacoesGuardada();
				
				if (g != null) {
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					
					nomeGraduacao = g.getGraduacao();
					txfModalidade.setSelectedItem(g.getModalidade());
					txfGraduacao.setText(g.getGraduacao());
					
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
					Graduacoes g = new Graduacoes();
					g.setGraduacao(nomeGraduacao);
					dao.Delete(g);
					
					JOptionPane.showMessageDialog(null, "Executado com Sucesso");
					desativarComponentes();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
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
					
					Graduacoes g = new Graduacoes();
					
					g.setModalidade(txfModalidade.getSelectedItem().toString());
					g.setGraduacao(txfGraduacao.getText());
					
					if (isInsert) {
						dao.Insert(g);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						g.setGuardar(nomeGraduacao);
						dao.Update(g);
						JOptionPane.showMessageDialog(null, "Update realizado com sucesso.");
					}
					
					desativarComponentes();
					
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
		
		lblModalidade = new JLabel("Modalidade: ");
		lblModalidade.setBounds(40, 110, 110, 30);
		getContentPane().add(lblModalidade);
		
		String Funcoes[] = {"Muay thai" ,"Karatê", "Jiu-jitsu", "Taekwondo", "Kung fu", "Capoeira"};
		txfModalidade = new JComboBox<String>(Funcoes);
		txfModalidade.setSelectedIndex(-1);
		txfModalidade.setBounds(135, 110, 180, 30);
		getContentPane().add(txfModalidade);
		
		lblGraduacao = new JLabel("Graduação: ");
		lblGraduacao.setBounds(40, 150, 110, 30);
		getContentPane().add(lblGraduacao);
		
		txfGraduacao = new JTextField();
		txfGraduacao.setBounds(135, 150, 180, 30);
		getContentPane().add(txfGraduacao);		
		
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
		btnLimpar.setEnabled(true);
		btnAdicionar.setEnabled(true);
		txfModalidade.setSelectedIndex(-1);
		txfGraduacao.setText(null);
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
