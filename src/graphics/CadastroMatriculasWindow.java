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

import database.dao.MatriculaDAO;
import database.dao.UsuariosDAO;
import database.model.Matricula;
import database.model.Usuarios;

public class CadastroMatriculasWindow extends JFrame {
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblCodigoMatricula;
	private JLabel lblCodigoAluno;
	private JLabel lblDataMatricula;
	private JLabel lblDiaVencimento;
	private JLabel lblDataEncerramento;
	private JTextField txfCodigoMatricula;
	private JTextField txfCodigoAluno;
	private JTextField txfDataMatricula;
	private JTextField txfDiaVencimento;
	private JFormattedTextField txfDataEncerramento;
	private JSeparator separator;
	private JButton btnCancelar;
	private MaskFormatter mascaraData;
	private MatriculaDAO dao;
	private boolean isInsert;
	private int codigoMatricula;
	
	public CadastroMatriculasWindow(Connection conexao) {
		
		try {
			dao = new MatriculaDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("CADASTRO MATRICULAS");
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
				
				BuscarMatriculasWindow bm = new BuscarMatriculasWindow(conexao);
				bm.setVisible(true);
				Matricula m = bm.getMatriculaGuardada();
				
				if (m != null) {
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					
					try {
						  
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");						  
						Date dataEncerramento = sf.parse(m.getData_encerramento());
						Date dataMatricula = sf.parse(m.getData_matricula());
					
						SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
						String stringEncerramento= DateFor.format(dataEncerramento);
						String stringMatricula= DateFor.format(dataMatricula);
						
						txfDataEncerramento.setText(stringEncerramento);
						txfDataMatricula.setText(stringMatricula);
						
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
						e1.printStackTrace();
					}	
					
					codigoMatricula = m.getCodigo_matricula();
					txfCodigoAluno.setText(""+m.getCodigo_aluno());
					txfCodigoMatricula.setText(""+m.getCodigo_matricula());
					txfDiaVencimento.setText(""+m.getDia_vencimento());
					
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
					Matricula m = new Matricula();
					m.setCodigo_matricula(codigoMatricula);
					dao.Delete(m);
					
					JOptionPane.showMessageDialog(null, "Executado com Sucesso");
					
					desativarComponentes();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
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
					
					Matricula m = new Matricula();
					
					m.setCodigo_aluno(Integer.parseInt(txfCodigoAluno.getText()));
					m.setData_encerramento(txfDataEncerramento.getText());
					m.setData_matricula(txfDataMatricula.getText());
					m.setDia_vencimento(Integer.parseInt(txfDiaVencimento.getText()));
					
					if (isInsert) {
						dao.Insert(m);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						dao.Update(m);
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
		txfCodigoMatricula.setBounds(145, 110, 160, 30);
		getContentPane().add(txfCodigoMatricula);
		
		lblCodigoAluno = new JLabel("Codigo Aluno: ");
		lblCodigoAluno.setBounds(40, 150, 80, 30);
		getContentPane().add(lblCodigoAluno);
		
		txfCodigoAluno = new JTextField();
		txfCodigoAluno.setBounds(125, 150, 180, 30);
		getContentPane().add(txfCodigoAluno);
		
		lblDataMatricula = new JLabel("Data Matricula: ");
		lblDataMatricula.setBounds(40, 190, 90, 30);
		getContentPane().add(lblDataMatricula);
		
		txfDataMatricula = new JFormattedTextField(mascaraData);
		txfDataMatricula.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataMatricula.setBounds(135, 190, 170, 30);
		getContentPane().add(txfDataMatricula);
		
		lblDiaVencimento = new JLabel("Dia Vencimento: ");
		lblDiaVencimento.setBounds(40, 230, 120, 30);
		getContentPane().add(lblDiaVencimento);
		
		txfDiaVencimento = new JTextField();
		txfDiaVencimento.setBounds(140, 230, 165, 30);
		getContentPane().add(txfDiaVencimento);
		
		lblDataEncerramento = new JLabel("Data Encerramento: ");
		lblDataEncerramento.setBounds(40, 270, 120, 30);
		getContentPane().add(lblDataEncerramento);
		
		txfDataEncerramento = new JFormattedTextField(mascaraData);
		txfDataEncerramento.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataEncerramento.setBounds(160, 270, 145, 30);
		getContentPane().add(txfDataEncerramento);
	
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
		txfCodigoAluno.setText(null);
		txfCodigoMatricula.setText(null);
		txfDataEncerramento.setText(null);
		txfDataMatricula.setText(null);
		txfDiaVencimento.setText(null);
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
