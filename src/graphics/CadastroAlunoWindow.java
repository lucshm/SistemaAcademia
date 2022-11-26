package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.dao.AlunoDAO;
import database.model.Aluno;

public class CadastroAlunoWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private JButton btnLimpar;
	private JLabel lblAluno;
	private JLabel lblNascimento;
	private JLabel lblSexo;
	private JLabel lbltelefone;
	private JLabel lblcelular;
	private JLabel lblemail;
	private JLabel lblobs;
	private JLabel lblendereco;
	private JLabel lblnumero;
	private JLabel lblcomplemento;
	private JLabel lblbairro;
	private JLabel lblcidade;
	private JLabel lblestado;
	private JLabel lblpais;
	private JLabel lblcep;
	private JLabel lblCpf;
	private JTextField txfAluno;
	private JFormattedTextField txfNascimento;
	private JComboBox<String> txfSexo;
	private JFormattedTextField txfTelefone;
	private JFormattedTextField txfCelular;
	private JTextField txfEmail;
	private JTextField txfObs;
	private JTextField txfEndereco;
	private JTextField txfNumero;
	private JTextField txfComplemento;
	private JTextField txfBairro;
	private JTextField txfCidade;
	private JTextField txfEstado;
	private JTextField txfPais;
	private JTextField txfCep;
	private JTextField txfCpf;
	private MaskFormatter mascaraData;
	private MaskFormatter mascaraCelular;
	private MaskFormatter mascaraTelefone;
	private JSeparator separator;
	private JButton btnCancelar;
	private AlunoDAO dao;
	private boolean isInsert;
	private int codigoAluno;
	
	
	public CadastroAlunoWindow(Connection conexao) {
		
		try {
			dao = new AlunoDAO(conexao);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("CADASTRO ALUNO");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
			mascaraCelular = new MaskFormatter("  (##) # ####-####");
			mascaraCelular.setPlaceholderCharacter('_');
			mascaraTelefone = new MaskFormatter("  (##) ####-####");
			mascaraTelefone.setPlaceholderCharacter('_');
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		btnBuscar = new JButton(new AbstractAction("BUSCAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BuscarAlunoWindow ba = new BuscarAlunoWindow(conexao);
				ba.setVisible(true);
				Aluno a = ba.getAlunoGuardado();
				
				if (a != null) {
					
					isInsert = false;
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					componentesTela(true);
					codigoAluno = a.getCodigo_aluno();
					
					txfAluno.setText(a.getAluno());
					txfBairro.setText(a.getBairro());
					txfCelular.setText(a.getCelular());
					txfCep.setText(a.getCep());
					txfCidade.setText(a.getCidade());
					txfComplemento.setText(a.getComplemento());
					txfCpf.setText(a.getCpf());
					txfEmail.setText(a.getEmail());
					txfEndereco.setText(a.getEndereco());
					txfEstado.setText(a.getEstado());
					
					    
					 try {
						  
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");						  
						Date date = sf.parse(a.getNascimento());	
					
						SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
						String stringDate= DateFor.format(date);
						
						txfNascimento.setText(stringDate);	
						
					} catch (ParseException e1) {
						e1.printStackTrace();
					}		
					
					txfNumero.setText(a.getNumero());
					txfObs.setText(a.getObs());
					txfPais.setText(a.getPais());
					if(a.getSexo().equals("M")) {
						txfSexo.setSelectedIndex(0);
					}
					else if(a.getSexo().equals("F")) {
						txfSexo.setSelectedIndex(1);
					}
					else if(a.getSexo().equals("O")) {
						txfSexo.setSelectedIndex(2);
					}
					else {
						txfSexo.setSelectedIndex(-1);
					}
					txfTelefone.setText(a.getTelefone());
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
					Aluno a = new Aluno();
					a.setCodigo_aluno(codigoAluno);
					dao.Delete(a);
					
					JOptionPane.showMessageDialog(null, "Executado com Sucesso");
					
					componentesTela(false);
					btnRemover.setEnabled(false);
					btnSalvar.setEnabled(false);
					txfAluno.setText(null);
					txfBairro.setText(null);
					txfCelular.setText(null);
					txfCep.setText(null);
					txfCidade.setText(null);
					txfComplemento.setText(null);
					txfCpf.setText(null);
					txfEmail.setText(null);
					txfEndereco.setText(null);
					txfEstado.setText(null);
					txfNascimento.setText(null);
					txfNumero.setText(null);
					txfObs.setText(null);
					txfPais.setText(null);
					txfSexo.setSelectedIndex(-1);
					txfTelefone.setText(null);
					
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
					Aluno a = new Aluno();
					a.setAluno(txfAluno.getText());
					a.setNascimento(txfNascimento.getText());
					if(txfSexo.getSelectedItem()!=null) {
						a.setSexo(txfSexo.getSelectedItem().toString());
					}
					else {
						a.setSexo(null);
					}
					a.setTelefone(txfTelefone.getText());
					a.setCelular(txfCelular.getText());
					a.setEmail(txfEmail.getText());
					a.setObs(txfObs.getText());
					a.setPais(txfPais.getText());
					a.setCidade(txfCidade.getText());
					a.setEstado(txfEstado.getText());
					a.setBairro(txfBairro.getText());
					a.setEndereco(txfEndereco.getText());
					a.setNumero(txfNumero.getText());
					a.setComplemento(txfComplemento.getText());
					a.setCpf(txfCpf.getText());
					a.setCep(txfCep.getText());

					if (isInsert) {
						dao.Insert(a);
						JOptionPane.showMessageDialog(null, "Inserido com sucesso.");
					} 
					else {
						a.setCodigo_aluno(codigoAluno);
						
						dao.Update(a);
						JOptionPane.showMessageDialog(null, "Update realizado com sucesso.");
					}
					
					componentesTela(false);
					btnRemover.setEnabled(false);
					btnSalvar.setEnabled(false);
					txfAluno.setText(null);
					txfBairro.setText(null);
					txfCelular.setText(null);
					txfCep.setText(null);
					txfCidade.setText(null);
					txfComplemento.setText(null);
					txfCpf.setText(null);
					txfEmail.setText(null);
					txfEndereco.setText(null);
					txfEstado.setText(null);
					txfNascimento.setText(null);
					txfNumero.setText(null);
					txfObs.setText(null);
					txfPais.setText(null);
					txfSexo.setSelectedIndex(-1);
					txfTelefone.setText(null);
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
		
		lblAluno = new JLabel("Aluno*: ");
		lblAluno.setBounds(40, 110, 50, 30);
		getContentPane().add(lblAluno);
		
		txfAluno = new JTextField();
		txfAluno.setBounds(92, 110, 200, 30);
		getContentPane().add(txfAluno);
		
		lblNascimento = new JLabel("Data de Nascimento: ");
		lblNascimento.setBounds(40, 150, 120, 30);
		getContentPane().add(lblNascimento);
		
		txfNascimento = new JFormattedTextField(mascaraData);
		txfNascimento.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfNascimento.setBounds(162, 150, 130, 30);
		getContentPane().add(txfNascimento);
		
		lblSexo = new JLabel("Sexo: ");
		lblSexo.setBounds(40, 190, 50, 30);
		getContentPane().add(lblSexo);
		
		String sexo[] = {"Masculino" ,"Feminino", "Outro"};
		
		txfSexo = new JComboBox<>(sexo);
		txfSexo.setSelectedIndex(-1);
		txfSexo.setBounds(92, 190, 200, 30);
		getContentPane().add(txfSexo);
		
		lbltelefone = new JLabel("Telefone: ");
		lbltelefone.setBounds(40, 230, 60, 30);
		getContentPane().add(lbltelefone);
		
		txfTelefone = new JFormattedTextField(mascaraTelefone);
		txfTelefone.setBounds(110, 230, 185, 30);
		getContentPane().add(txfTelefone);
		
		lblcelular = new JLabel("Celular: ");
		lblcelular.setBounds(40, 270, 60, 30);
		getContentPane().add(lblcelular);
		
		txfCelular = new JFormattedTextField(mascaraCelular);
		txfCelular.setBounds(110, 270, 185, 30);
		getContentPane().add(txfCelular);
		
		lblemail = new JLabel("Email: ");
		lblemail.setBounds(40, 310, 50, 30);
		getContentPane().add(lblemail);
		
		txfEmail = new JTextField();
		txfEmail.setBounds(92, 310, 203, 30);
		getContentPane().add(txfEmail);
		
		lblobs = new JLabel("Observação: ");
		lblobs.setBounds(40, 350, 80, 30);
		getContentPane().add(lblobs);
		
		txfObs = new JTextField();
		txfObs.setBounds(130, 350, 165, 30);
		getContentPane().add(txfObs);
		
		lblCpf = new JLabel("*CPF: ");
		lblCpf.setBounds(40, 390, 50, 30);
		getContentPane().add(lblCpf);
		
		txfCpf = new JTextField();
		txfCpf.setBounds(95, 390, 203, 30);
		getContentPane().add(txfCpf);
		
		lblpais = new JLabel("Pais: ");
		lblpais.setBounds(400, 110, 50, 30);
		getContentPane().add(lblpais);
		
		txfPais = new JTextField();
		txfPais.setBounds(460, 110, 200, 30);
		getContentPane().add(txfPais);
		
		lblestado = new JLabel("Estado: ");
		lblestado.setBounds(400, 150, 50, 30);
		getContentPane().add(lblestado);
		
		txfEstado = new JTextField();
		txfEstado.setBounds(460, 150, 200, 30);
		getContentPane().add(txfEstado);
		
		lblcidade = new JLabel("Cidade: ");
		lblcidade.setBounds(400, 190, 50, 30);
		getContentPane().add(lblcidade);
		
		txfCidade = new JTextField();
		txfCidade.setBounds(460, 190, 200, 30);
		getContentPane().add(txfCidade);
		
		lblbairro = new JLabel("Bairro: ");
		lblbairro.setBounds(400, 230, 60, 30);
		getContentPane().add(lblbairro);
		
		txfBairro = new JTextField();
		txfBairro.setBounds(460, 230, 200, 30);
		getContentPane().add(txfBairro);
		
		lblendereco = new JLabel("Endereço: ");
		lblendereco.setBounds(400, 270, 60, 30);
		getContentPane().add(lblendereco);
		
		txfEndereco = new JTextField();
		txfEndereco.setBounds(460, 270, 200, 30);
		getContentPane().add(txfEndereco);
		
		lblnumero = new JLabel("Numero: ");
		lblnumero.setBounds(400, 310, 60, 30);
		getContentPane().add(lblnumero);
		
		txfNumero = new JTextField();
		txfNumero.setBounds(460, 310, 200, 30);
		getContentPane().add(txfNumero);
		
		lblcomplemento = new JLabel("Complemento: ");
		lblcomplemento.setBounds(400, 350, 100, 30);
		getContentPane().add(lblcomplemento);
		
		txfComplemento = new JTextField();
		txfComplemento.setBounds(500, 350, 160, 30);
		getContentPane().add(txfComplemento);
		
		lblcep = new JLabel("CEP: ");
		lblcep.setBounds(400, 390, 100, 30);
		getContentPane().add(lblcep);
		
		txfCep = new JTextField();
		txfCep.setBounds(500, 390, 160, 30);
		getContentPane().add(txfCep);
		
		btnLimpar = new JButton(new AbstractAction("LIMPAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				desativarComponentes();
			}
		});
		btnLimpar.setBounds(480, 450, 100, 30);
		getContentPane().add(btnLimpar);
		
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
		btnBuscar.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnAdicionar.setEnabled(true);
		txfAluno.setText(null);
		txfBairro.setText(null);
		txfCelular.setText(null);
		txfCep.setText(null);
		txfCidade.setText(null);
		txfComplemento.setText(null);
		txfCpf.setText(null);
		txfEmail.setText(null);
		txfEndereco.setText(null);
		txfEstado.setText(null);
		txfNascimento.setText(null);
		txfNumero.setText(null);
		txfObs.setText(null);
		txfPais.setText(null);
		txfSexo.setSelectedIndex(-1);
		txfTelefone.setText(null);
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
