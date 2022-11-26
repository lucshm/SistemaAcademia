package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import database.dao.ModalidadesDAO;
import database.model.Modalidades;

public class SistemaModalidadesWindow extends JFrame {
	
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JLabel lblModalidade;
	private JSeparator separator;
	private JButton btnCancelar;
	private JTextField txfModalidade;
	private ModalidadesDAO dao;
	
	public SistemaModalidadesWindow(Connection conexao) {
		
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
					componentesTela(true);
					txfModalidade.setText(m.getModalidade());
					
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
