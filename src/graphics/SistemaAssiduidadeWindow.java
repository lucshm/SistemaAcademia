package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.dao.AssiduidadeDAO;
import database.model.Assiduidade;

public class SistemaAssiduidadeWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JLabel lblCodigoMatricula;
	private JLabel lblDataEntrada;
	private JSeparator separator;
	private JButton btnCancelar;
	private JTextField txfCodigoMatricula;
	private JFormattedTextField txfDataEntrada;
	private MaskFormatter mascaraData;
	private AssiduidadeDAO dao;
	
	public SistemaAssiduidadeWindow(Connection conexao) {
		
		try {
			dao = new AssiduidadeDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("ASSIDUIDADE");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
		}
		catch (Exception e) {
			
		}
		
		btnBuscar = new JButton(new AbstractAction("BUSCAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BuscarAssiduidadeWindow ba = new BuscarAssiduidadeWindow(conexao);
				ba.setVisible(true);
				Assiduidade a = ba.getAssiduidadeGuardada();
				
				if (a != null) {
					componentesTela(true);
					
					txfCodigoMatricula.setText(""+a.getCodigo_matricula());
					txfDataEntrada.setText(a.getData_entrada());
					
				}				
				
			}
		});
		btnBuscar.setBounds(310, 20, 110, 50);
		getContentPane().add(btnBuscar);
	
		separator = new JSeparator();
		separator.setBounds(0, 90 , 780, 2);
		getContentPane().add(separator);
		
		lblCodigoMatricula = new JLabel("Codigo Matricula: ");
		lblCodigoMatricula.setBounds(40, 110, 110, 30);
		getContentPane().add(lblCodigoMatricula);
		
		txfCodigoMatricula = new JTextField();
		txfCodigoMatricula.setBounds(155, 110, 150, 30);
		getContentPane().add(txfCodigoMatricula);
		
		lblDataEntrada = new JLabel("Data de Entrada: ");
		lblDataEntrada.setBounds(40, 150, 110, 30);
		getContentPane().add(lblDataEntrada);
		
		txfDataEntrada = new JFormattedTextField(mascaraData);
		txfDataEntrada.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataEntrada.setBounds(155, 150, 150, 30);
		getContentPane().add(txfDataEntrada);
		
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
		txfCodigoMatricula.setText(null);
		
	}
	
	private void componentesTela(boolean habilitar) {
		for (Component comp : getContentPane().getComponents()) {
			if(comp instanceof JButton) {
				continue;
			}
			comp.setEnabled(habilitar);
		}
		lblCodigoMatricula.setEnabled(false);
		txfCodigoMatricula.setEnabled(false);
	}
	
}
