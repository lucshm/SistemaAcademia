package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.dao.GraduacoesDAO;
import database.model.Graduacoes;

public class BuscarGraduacoesWindow extends JDialog {
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private GraduacoesDAO dao;
	private HashMap<String, Graduacoes> arlGraduacoes = new HashMap<String, Graduacoes>();
	private Graduacoes graduacoesGuardada;
	
	public BuscarGraduacoesWindow(Connection conexao){
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("MODALIDADE");
		model.addColumn("GRADUACAO");
		
		tbl = new JTable(model) {
			 public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
		};
		tbl.setRowSelectionAllowed(false);
		tbl.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
					graduacoesGuardada = arlGraduacoes.get(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new GraduacoesDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlGraduacao = new ArrayList<Object>();
		
		try {
			arlGraduacao = dao.Select();
			
			for (Object c : arlGraduacao) {
				
				Graduacoes g = (Graduacoes) c;
				model.addRow(new String[] {g.getModalidade(), g.getGraduacao()});
				arlGraduacoes.put(g.getModalidade(), g);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Graduacoes getGraduacoesGuardada() {
		return graduacoesGuardada;
	}
	
}
