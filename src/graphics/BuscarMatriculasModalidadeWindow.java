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
import database.dao.MatriculaModalidadeDAO;
import database.model.Matricula;
import database.model.MatriculaModalidade;

public class BuscarMatriculasModalidadeWindow extends JDialog {
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private MatriculaModalidadeDAO dao;
	private HashMap<Integer, MatriculaModalidade> arlMatriculasModalidade = new HashMap<Integer, MatriculaModalidade>();
	private MatriculaModalidade matriculaModalidadeGuardada;
	
	public BuscarMatriculasModalidadeWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("CÓDIGO MATRICULA");
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
					
					matriculaModalidadeGuardada = arlMatriculasModalidade.get(Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()));
					
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new MatriculaModalidadeDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlMatriculasModalidades = new ArrayList<Object>();
		
		try {
			arlMatriculasModalidades = dao.Select();
			
			for (Object c : arlMatriculasModalidades) {
				
				MatriculaModalidade mm = (MatriculaModalidade) c;
				model.addRow(new String[] {""+mm.getCodigo_matricula(), mm.getModalidade()});
				arlMatriculasModalidade.put(mm.getCodigo_matricula(), mm);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public MatriculaModalidade getMatriculaModalidadeGuardada() {
		return matriculaModalidadeGuardada;
	}
	
}
