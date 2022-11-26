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
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import database.dao.MatriculaDAO;
import database.dao.MatriculaModalidadeDAO;
import database.model.Matricula;
import database.model.MatriculaModalidade;
import database.model.Usuarios;

public class SistemaMatriculasModalidadesWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JLabel lblCodigoMatricula;
	private JLabel lblModalidade;
	private JLabel lblGraduacao;
	private JLabel lblPlano;
	private JLabel lblDataInicio;
	private JLabel lblDataFim;
	private JTextField txfCodigoMatricula;
	private JComboBox<String> txfModalidade;
	private JTextField txfGraduacao;
	private JTextField txfPlano;
	private JFormattedTextField txfDataInicio;
	private JFormattedTextField txfDataFim;
	private JSeparator separator;
	private JButton btnCancelar;
	private MaskFormatter mascaraData;
	private MatriculaModalidadeDAO dao;
	
	public SistemaMatriculasModalidadesWindow(Connection conexao) {
		
		try {
			dao = new MatriculaModalidadeDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("CADASTRO MATRICULAS MODALIDADES");
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
				
				BuscarMatriculasModalidadeWindow bmm = new BuscarMatriculasModalidadeWindow(conexao);
				bmm.setVisible(true);
				MatriculaModalidade mm = bmm.getMatriculaModalidadeGuardada();
				
				if (mm != null) {
					componentesTela(true);
					
					try {
						  
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");						  
						Date dataFim = sf.parse(mm.getData_fim());
						Date dataInicio = sf.parse(mm.getData_inicio());
					
						SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
						String stringFim= DateFor.format(dataFim);
						String stringInicio= DateFor.format(dataInicio);
						
						txfDataFim.setText(stringFim);
						txfDataInicio.setText(stringInicio);
						
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					txfCodigoMatricula.setText(""+mm.getCodigo_matricula());
					txfGraduacao.setText(mm.getGraduacao());
					txfModalidade.setSelectedItem(mm.getModalidade());
					txfPlano.setText(mm.getPlano());
					
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
		
		lblModalidade = new JLabel("Modalidade: ");
		lblModalidade.setBounds(40, 150, 80, 30);
		getContentPane().add(lblModalidade);
		
		String Funcoes[] = {"Muay thai" ,"Karatê", "Jiu-jitsu", "Taekwondo", "Kung fu", "Capoeira"};
		txfModalidade = new JComboBox<String>(Funcoes);
		txfModalidade.setSelectedIndex(-1);
		txfModalidade.setBounds(125, 150, 180, 30);
		getContentPane().add(txfModalidade);
		
		lblGraduacao = new JLabel("Graduação: ");
		lblGraduacao.setBounds(40, 190, 80, 30);
		getContentPane().add(lblGraduacao);
		
		txfGraduacao = new JTextField();
		txfGraduacao.setBounds(125, 190, 180, 30);
		getContentPane().add(txfGraduacao);
		
		lblPlano = new JLabel("Plano: ");
		lblPlano.setBounds(40, 230, 70, 30);
		getContentPane().add(lblPlano);
		
		txfPlano = new JTextField();
		txfPlano.setBounds(125, 230, 180, 30);
		getContentPane().add(txfPlano);
		
		lblDataInicio = new JLabel("Data de Inicio: ");
		lblDataInicio.setBounds(40, 270, 120, 30);
		getContentPane().add(lblDataInicio);
		
		txfDataInicio = new JFormattedTextField(mascaraData);
		txfDataInicio.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataInicio.setBounds(125, 270, 180, 30);
		getContentPane().add(txfDataInicio);
		
		lblDataFim = new JLabel("Data Final: ");
		lblDataFim.setBounds(40, 310, 120, 30);
		getContentPane().add(lblDataFim);
		
		txfDataFim = new JFormattedTextField(mascaraData);
		txfDataFim.setHorizontalAlignment(JFormattedTextField.CENTER);
		txfDataFim.setBounds(125, 310, 180, 30);
		getContentPane().add(txfDataFim);
		
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
		txfDataFim.setText(null);
		txfDataInicio.setText(null);
		txfGraduacao.setText(null);
		txfModalidade.setSelectedIndex(-1);
		txfPlano.setText(null);
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
