package graphics;

import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import database.dao.GraduacoesDAO;
import database.model.Graduacoes;

public class SistemaGraduacoesWindow extends JFrame{
	private JButton btnBuscar;
	private JLabel lblModalidade;
	private JLabel lblGraduacao;
	private JSeparator separator;
	private JButton btnCancelar;
	private JButton btnLimpar;
	private JComboBox<String> txfModalidade;
	private JTextField txfGraduacao;
	private GraduacoesDAO dao;
	
	public SistemaGraduacoesWindow(Connection conexao) {
		
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
					componentesTela(true);
					
					txfModalidade.setSelectedItem(g.getModalidade());
					txfGraduacao.setText(g.getGraduacao());
					
				}				
				
			}
		});
		btnBuscar.setBounds(310, 20, 110, 50);
		getContentPane().add(btnBuscar);
	
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
