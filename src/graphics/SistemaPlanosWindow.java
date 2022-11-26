package graphics;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import database.dao.PlanosDAO;
import database.model.Planos;

public class SistemaPlanosWindow extends JFrame{
	private JButton btnBuscar;
	private JButton btnLimpar;
	private JLabel lblModalidade;
	private JLabel lblPlano;
	private JLabel lblValorMensal;
	private JSeparator separator;
	private JButton btnCancelar;
	private JComboBox<String> txfModalidade;
	private JTextField txfPlano;
	private JTextField txfValorMensal;
	private PlanosDAO dao;
	
	public SistemaPlanosWindow(Connection conexao) {
		
		try {
			dao = new PlanosDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setSize(780,600);
		setTitle("PLANOS");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		btnBuscar = new JButton(new AbstractAction("BUSCAR") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BuscarPlanosWindow bp = new BuscarPlanosWindow(conexao);
				bp.setVisible(true);
				Planos p = bp.getPlanosGuardada();
				
				if (p != null) {
					componentesTela(true);
					txfModalidade.setSelectedItem(p.getModalidade());
					txfPlano.setText(p.getPlano());
					txfValorMensal.setText(""+p.getValor_mensal());
					
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
		
		lblPlano = new JLabel("Plano: ");
		lblPlano.setBounds(40, 150, 110, 30);
		getContentPane().add(lblPlano);
		
		txfPlano = new JTextField();
		txfPlano.setBounds(135, 150, 180, 30);
		getContentPane().add(txfPlano);
		
		lblValorMensal = new JLabel("Valor: ");
		lblValorMensal.setBounds(40, 190, 110, 30);
		getContentPane().add(lblValorMensal);
		
		txfValorMensal = new JTextField();
		txfValorMensal.setBounds(135, 190, 180, 30);
		getContentPane().add(txfValorMensal);
		
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
		txfPlano.setText(null);
		txfValorMensal.setText(null);
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
