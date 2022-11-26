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

import database.dao.FaturaMatriculasDAO;
import database.dao.MatriculaDAO;
import database.model.FaturaMatriculas;
import database.model.Matricula;

public class FaturasMatriculasWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JLabel lblCodigoMatricula;
	private JLabel lblDataVencimento;
	private JLabel lblDataPagamento;
	private JLabel lblDataCancelamento;
	private JLabel lblValor;
	private JSeparator separator;
	private JButton btnCancelar;
	private JTextField txfCodigoMatricula;
	private JFormattedTextField txfDataVencimento;
	private JFormattedTextField txfDataPagamento;
	private JFormattedTextField txfDataCancelamento;
	private JTextField txfValor;
	private MaskFormatter mascaraData;
	private FaturaMatriculasDAO dao;
	private boolean isInsert;
	private int codigoMatricula;
	
	public FaturasMatriculasWindow(Connection conexao) {
		
		try {
			dao = new FaturaMatriculasDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("FATURAS MATRICULAS");
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
				
				BuscarFaturasMatriculasWindow bfm = new BuscarFaturasMatriculasWindow(conexao);
				bfm.setVisible(true);
				FaturaMatriculas fm = bfm.getFaturaMatriculasGuardada();
				
				if (fm != null) {
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					
					try {
						  
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");						  
						Date dataCancelamento = sf.parse(fm.getData_cancelamento());
						Date dataPagamento = sf.parse(fm.getData_pagamento());
						Date dataVencimento = sf.parse(fm.getData_vencimento());
						
						SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
						String stringCancelamento= DateFor.format(dataCancelamento);
						String stringPagamento= DateFor.format(dataPagamento);
						String stringVencimento= DateFor.format(dataVencimento);
						
						txfDataCancelamento.setText(stringCancelamento);
						txfDataPagamento.setText(stringPagamento);
						txfDataVencimento.setText(stringVencimento);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					codigoMatricula = fm.getCodigo_matricula();
					txfCodigoMatricula.setText(""+fm.getCodigo_matricula());
					txfDataCancelamento.setText(fm.getData_cancelamento());
					txfDataPagamento.setText(fm.getData_pagamento());
					txfDataVencimento.setText(""+fm.getData_vencimento());
					txfValor.setText(""+fm.getValor());
					
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
					FaturaMatriculas fm = new FaturaMatriculas();
					fm.setCodigo_matricula(codigoMatricula);
					dao.Delete(fm);
					
					JOptionPane.showMessageDialog(null, "Executado com Sucesso");
					
					desativarComponentes();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
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
					
					FaturaMatriculas fm = new FaturaMatriculas();
					
					fm.setCodigo_matricula(Integer.parseInt(txfCodigoMatricula.getText()));;;
					fm.setData_cancelamento(txfDataCancelamento.getText());
					fm.setData_pagamento(txfDataPagamento.getText());
					fm.setData_vencimento(txfDataVencimento.getText());
					fm.setValor(Double.parseDouble(txfValor.getText()));
					
					if (isInsert) {
						dao.Insert(fm);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						dao.Update(fm);
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
		
		lblDataVencimento = new JLabel("Data de Vencimento: ");
		lblDataVencimento.setBounds(40, 150, 130, 30);
		getContentPane().add(lblDataVencimento);
		
		txfDataVencimento = new JFormattedTextField(mascaraData);
		txfDataVencimento.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataVencimento.setBounds(175, 150, 130, 30);
		getContentPane().add(txfDataVencimento);
		
		lblValor = new JLabel("Valor: ");
		lblValor.setBounds(40, 190, 80, 30);
		getContentPane().add(lblValor);
		
		txfValor = new JTextField();
		txfValor.setBounds(125, 190, 180, 30);
		getContentPane().add(txfValor);
		
		lblDataPagamento = new JLabel("Data de Pagamento: ");
		lblDataPagamento.setBounds(40, 230, 130, 30);
		getContentPane().add(lblDataPagamento);
		
		txfDataPagamento = new JFormattedTextField(mascaraData);
		txfDataPagamento.setBounds(175, 230, 130, 30);
		txfDataPagamento.setHorizontalAlignment(JFormattedTextField.CENTER);
		getContentPane().add(txfDataPagamento);
		
		lblDataCancelamento = new JLabel("Data de Cancelamento: ");
		lblDataCancelamento.setBounds(40, 270, 140, 30);
		getContentPane().add(lblDataCancelamento);
		
		txfDataCancelamento = new JFormattedTextField(mascaraData);
		txfDataCancelamento.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataCancelamento.setBounds(185, 270, 120, 30);
		getContentPane().add(txfDataCancelamento);
		
		btnCancelar = new JButton(new AbstractAction("CANCELAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancelar.setBounds(600, 450, 100, 30);
		getContentPane().add(btnCancelar);
		
		componentesTela(false);
		
	}
	
	private void desativarComponentes() {
		
		for (Component comp : getContentPane().getComponents()) {
			comp.setEnabled(false);
		}
		btnAdicionar.setEnabled(true);
		btnBuscar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnRemover.setEnabled(false);
		btnSalvar.setEnabled(false);
		txfCodigoMatricula.setText(null);
		txfDataCancelamento.setText(null);
		txfDataPagamento.setText(null);
		txfDataVencimento.setText(null);
		txfValor.setText(null);
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
