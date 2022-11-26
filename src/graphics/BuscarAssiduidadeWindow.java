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

import database.dao.AssiduidadeDAO;
import database.model.Assiduidade;

public class BuscarAssiduidadeWindow extends JDialog {

	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private AssiduidadeDAO dao;
	private HashMap<Integer, Assiduidade> arlAssiduidades = new HashMap<Integer, Assiduidade>();
	private Assiduidade AssiduidadeGuardada;
	
	public BuscarAssiduidadeWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("CÓDIGO MATRICULA");
		
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
					
					AssiduidadeGuardada = arlAssiduidades.get(Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()));
					
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new AssiduidadeDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlAssiduidade = new ArrayList<Object>();
		
		try {
			arlAssiduidade = dao.Select();
			
			for (Object c : arlAssiduidade) {
				
				Assiduidade a = (Assiduidade) c;
				model.addRow(new String[] {""+a.getCodigo_matricula()});
				arlAssiduidades.put(a.getCodigo_matricula(), a);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Assiduidade getAssiduidadeGuardada() {
		return AssiduidadeGuardada;
	}
	
}
