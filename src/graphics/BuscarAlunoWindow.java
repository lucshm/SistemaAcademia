package graphics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.dao.AlunoDAO;
import database.model.Aluno;

public class BuscarAlunoWindow extends JDialog {
	
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private AlunoDAO dao;
	private HashMap<Integer, Aluno> arlAlunos = new HashMap<Integer, Aluno>();
	private Aluno alunoGuardado;
	
	public BuscarAlunoWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("CÓDIGO");
		model.addColumn("ALUNO");
		
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
					
					alunoGuardado = arlAlunos.get(Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()));
					
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new AlunoDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlAluno = new ArrayList<Object>();
		
		try {
			arlAluno = dao.Select();
			
			for (Object c : arlAluno) {
				
				Aluno a = (Aluno) c;
				model.addRow(new String[] {""+a.getCodigo_aluno(), a.getAluno()});
				arlAlunos.put(a.getCodigo_aluno(), a);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Aluno getAlunoGuardado() {
		return alunoGuardado;
	}

}
