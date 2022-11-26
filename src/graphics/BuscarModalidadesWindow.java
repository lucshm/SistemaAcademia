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

import database.dao.ModalidadesDAO;
import database.model.Modalidades;

public class BuscarModalidadesWindow  extends JDialog {
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private ModalidadesDAO dao;
	private HashMap<String, Modalidades> arlModalidades = new HashMap<String, Modalidades>();
	private Modalidades ModalidadesGuardada;
	
	public BuscarModalidadesWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("MODALIDADE");
		
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
					
					ModalidadesGuardada = arlModalidades.get(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new ModalidadesDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlModalidade = new ArrayList<Object>();
		
		try {
			arlModalidade = dao.Select();
			
			for (Object c : arlModalidade) {
				
				Modalidades m = (Modalidades) c;
				model.addRow(new String[] {m.getModalidade()});
				arlModalidades.put(m.getModalidade(), m);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Modalidades getModalidadesGuardada() {
		return ModalidadesGuardada;
	}
	
	
}
