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

import database.dao.AlunoDAO;
import database.dao.UsuariosDAO;
import database.model.Aluno;
import database.model.Usuarios;

public class BuscarUsuarioWindow extends JDialog {
	
	private JTable tbl;
	private DefaultTableModel model;
	private JScrollPane scroll;
	private UsuariosDAO dao;
	private HashMap<String, Usuarios> arlUsuarios = new HashMap<String, Usuarios>();
	private Usuarios usuarioGuardado;
	
	public BuscarUsuarioWindow(Connection conexao) {
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 300);
		
		model = new DefaultTableModel();
		model.addColumn("USUÁRIO");
		model.addColumn("PERFIL");
		
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
					
					usuarioGuardado = arlUsuarios.get(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
					
					dispose();
				}
			}
			
		});
		
		scroll = new JScrollPane(tbl);
		getContentPane().add(scroll);
		
		try {
			dao = new UsuariosDAO(conexao);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		List<Object> arlUsuario = new ArrayList<Object>();
		
		try {
			arlUsuario = dao.Select();
			
			for (Object c : arlUsuario) {
				
				Usuarios u = (Usuarios) c;
				model.addRow(new String[] {""+u.getUsuario(), u.getPerfil()});
				arlUsuarios.put(u.getUsuario(), u);
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
				
		setModal(true);
		
	}
	
	public Usuarios getUsuarioGuardado() {
		return usuarioGuardado;
	}
	
}
