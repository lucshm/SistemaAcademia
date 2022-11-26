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

import database.dao.MatriculaDAO;
import database.model.Matricula;

public class BuscarMatriculasWindow extends JDialog {
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private MatriculaDAO dao;
	private HashMap<Integer, Matricula> arlMatriculas = new HashMap<Integer, Matricula>();
	private Matricula matriculaGuardada;
	
	public BuscarMatriculasWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("CÓDIGO MATRICULA");
		model.addColumn("CÓDIGO ALUNO");
		
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
					
					matriculaGuardada = arlMatriculas.get(Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()));
					
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new MatriculaDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlMatricula = new ArrayList<Object>();
		
		try {
			arlMatricula = dao.Select();
			
			for (Object c : arlMatricula) {
				
				Matricula m = (Matricula) c;
				model.addRow(new String[] {""+m.getCodigo_matricula(), ""+m.getCodigo_aluno()});
				arlMatriculas.put(m.getCodigo_matricula(), m);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Matricula getMatriculaGuardada() {
		return matriculaGuardada;
	}
	
}
