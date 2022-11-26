package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.dao.AssiduidadeDAO;
import database.dao.MatriculaDAO;
import database.model.Assiduidade;
import database.model.Matricula;

public class CadastroAssiduidadeWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblCodigoMatricula;
	private JLabel lblDataEntrada;
	private JSeparator separator;
	private JButton btnCancelar;
	private JTextField txfCodigoMatricula;
	private JFormattedTextField txfDataEntrada;
	private MaskFormatter mascaraData;
	private AssiduidadeDAO dao;
	private boolean isInsert;
	private int codigoMatricula;
	
	public CadastroAssiduidadeWindow(Connection conexao) {
		
		try {
			dao = new AssiduidadeDAO(conexao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("CADASTRO ASSIDUIDADE");
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
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					
					codigoMatricula = a.getCodigo_matricula();
					txfCodigoMatricula.setText(""+a.getCodigo_matricula());
					
					try {
						  
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");						  
						Date date = sf.parse(a.getData_entrada());	
					
						SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
						String stringDate= DateFor.format(date);
						
						txfDataEntrada.setText(stringDate);	
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}	
					
					txfDataEntrada.setText(a.getData_entrada());
					
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
					Assiduidade a = new Assiduidade();
					a.setCodigo_matricula(codigoMatricula);
					dao.Delete(a);
					
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
					
					Assiduidade a = new Assiduidade();
					
					a.setCodigo_matricula(Integer.parseInt(txfCodigoMatricula.getText()));
					a.setData_entrada(txfDataEntrada.getText());
					
					if (isInsert) {
						dao.Insert(a);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						dao.Update(a);
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
		btnAdicionar.setEnabled(true);
		txfCodigoMatricula.setText(null);
		txfDataEntrada.setText(null);
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
