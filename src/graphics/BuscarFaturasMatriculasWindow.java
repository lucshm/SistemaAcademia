package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.dao.FaturaMatriculasDAO;
import database.model.FaturaMatriculas;

public class BuscarFaturasMatriculasWindow extends JDialog {
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private FaturaMatriculasDAO dao;
	private HashMap<Integer, FaturaMatriculas> arlFaturaMatriculass = new HashMap<Integer, FaturaMatriculas>();
	private FaturaMatriculas FaturaMatriculasGuardada;
	
	public BuscarFaturasMatriculasWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("CÓDIGO MATRICULA");
		model.addColumn("VALOR");
		
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
					
					FaturaMatriculasGuardada = arlFaturaMatriculass.get(Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()));
					
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new FaturaMatriculasDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlFaturaMatriculas = new ArrayList<Object>();
		
		try {
			arlFaturaMatriculas = dao.Select();
			
			for (Object c : arlFaturaMatriculas) {
				
				FaturaMatriculas fm = (FaturaMatriculas) c;
				model.addRow(new String[] {""+fm.getCodigo_matricula(), ""+fm.getValor()});
				arlFaturaMatriculass.put(fm.getCodigo_matricula(), fm);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public FaturaMatriculas getFaturaMatriculasGuardada() {
		return FaturaMatriculasGuardada;
	}
	
}
