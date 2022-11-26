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

import database.dao.PlanosDAO;
import database.model.Planos;

public class BuscarPlanosWindow extends JDialog{
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private PlanosDAO dao;
	private HashMap<String, Planos> arlPlanos = new HashMap<String, Planos>();
	private Planos PlanosGuardada;
	
	public BuscarPlanosWindow(Connection conexao){
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(350, 300);
		
		model = new DefaultTableModel();
		model.addColumn("PLANO");
		model.addColumn("MODALIDADE");
		model.addColumn("VALOR MENSAL");
		
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
					
					PlanosGuardada = arlPlanos.get(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new PlanosDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlPlano = new ArrayList<Object>();
		
		try {
			arlPlano = dao.Select();
			
			for (Object c : arlPlano) {
				
				Planos p = (Planos) c;
				model.addRow(new String[] {p.getPlano(), p.getModalidade(), ""+p.getValor_mensal()});
				arlPlanos.put(p.getPlano(), p);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Planos getPlanosGuardada() {
		return PlanosGuardada;
	}
	
}
