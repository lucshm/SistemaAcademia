package graphics;

import java.awt.event.ActionEvent;
import java.sql.Connection;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuWindow extends JFrame {

	private JMenuBar menubar;
	private JMenu menuSistema, menuFinanceiro, menuUtilitario;
	private JMenuItem itemSistemaUsuario;
	private JMenuItem itemSistemaAluno;
	private JMenuItem itemSistemaAssiduidade;
	private JMenuItem itemSistemaGraduacoes;
	private JMenuItem itemSistemaMatriculaModalidades;
	private JMenuItem itemSistemaMatricula;
	private JMenuItem itemSistemaModalidades;
	private JMenuItem itemSistemaPlanos;
	
	private JMenu  menuCadastro;
	private JMenuItem itemCadastroAluno;
	private JMenuItem itemCadastroUsuario;
	private JMenuItem itemCadastroModalidades;
	private JMenuItem itemCadastroGraduacoes;
	private JMenuItem itemCadastroPlanos;
	private JMenuItem itemCadastroMatricula;
	private JMenuItem itemCadastroAssiduidade;
	private JMenuItem itemCadastroMatriculaModalidades;
	
	private JMenuItem itemFinanceiroFaturaMatriculas;
	
	private JFrame framePrincipal;
	//private JDesktopPane desktopPrincipal;
	private String perfil;
	
	public MenuWindow(Connection conexao, String usuario, String perfil) {
		//
		//Recebendo Perfil do Login
		//
		this.perfil = perfil;
		
		menubar = new JMenuBar();
		
			menuSistema = new JMenu("Sistema");
				itemSistemaAluno = new JMenuItem(new AbstractAction("Aluno") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaAlunoWindow(conexao).setVisible(true);						
					}
				});
				
				
				itemSistemaUsuario = new JMenuItem(new AbstractAction("Usuario") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaUsuarioWindow(conexao).setVisible(true);
					}
				});
				
				itemSistemaModalidades = new JMenuItem(new AbstractAction("Modalidades") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaModalidadesWindow(conexao).setVisible(true);
					}
				});
				
				itemSistemaPlanos = new JMenuItem(new AbstractAction("Planos") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaPlanosWindow(conexao).setVisible(true);
					}
				});
				
				itemSistemaAssiduidade = new JMenuItem(new AbstractAction("Assiduidade") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaAssiduidadeWindow(conexao).setVisible(true);
					}
				});
				
				itemSistemaGraduacoes = new JMenuItem(new AbstractAction("Graduações") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaGraduacoesWindow(conexao).setVisible(true);
					}
				});
				
				itemSistemaMatricula = new JMenuItem(new AbstractAction("Matricula") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaMatriculasWindow(conexao).setVisible(true);
					}
				});
				
				itemSistemaMatriculaModalidades = new JMenuItem(new AbstractAction("Matricula Modalidades") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new SistemaMatriculasModalidadesWindow(conexao).setVisible(true);
					}
				});
			menuSistema.add(itemSistemaAluno);
			menuSistema.add(itemSistemaUsuario);
			menuSistema.add(itemSistemaPlanos);
			menuSistema.add(itemSistemaAssiduidade);
			menuSistema.add(itemSistemaGraduacoes);
			menuSistema.add(itemSistemaMatricula);
			menuSistema.add(itemSistemaModalidades);
			menuSistema.add(itemSistemaMatriculaModalidades);
			
			menuCadastro = new JMenu("Cadastro");
				//
				//Chamando Ação pro Cadasto de Aluno
				//
				itemCadastroAluno = new JMenuItem(new AbstractAction("Aluno") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroAlunoWindow(conexao).setVisible(true);						
					}
				});
				
				
				itemCadastroUsuario = new JMenuItem(new AbstractAction("Usuario") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroUsuarioWindow(conexao).setVisible(true);
					}
				});
				
				itemCadastroModalidades = new JMenuItem(new AbstractAction("Modalidades") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroModalidadesWindow(conexao).setVisible(true);
					}
				});
				
				itemCadastroPlanos = new JMenuItem(new AbstractAction("Planos") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroPlanosWindow(conexao).setVisible(true);
					}
				});
				
				itemCadastroAssiduidade = new JMenuItem(new AbstractAction("Assiduidade") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroAssiduidadeWindow(conexao).setVisible(true);
					}
				});
				
				itemCadastroGraduacoes = new JMenuItem(new AbstractAction("Graduações") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroGraduacoesWindow(conexao).setVisible(true);
					}
				});
				
				itemCadastroMatricula = new JMenuItem(new AbstractAction("Matricula") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroMatriculasWindow(conexao).setVisible(true);
					}
				});
				
				itemCadastroMatriculaModalidades = new JMenuItem(new AbstractAction("Matricula Modalidades") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new CadastroMatriculasModalidadeWindow(conexao).setVisible(true);
					}
				});
			menuCadastro.add(itemCadastroAluno);
			menuCadastro.add(itemCadastroUsuario);
			menuCadastro.add(itemCadastroPlanos);
			menuCadastro.add(itemCadastroAssiduidade);
			menuCadastro.add(itemCadastroGraduacoes);
			menuCadastro.add(itemCadastroMatricula);
			menuCadastro.add(itemCadastroModalidades);
			menuCadastro.add(itemCadastroMatriculaModalidades);
			
			menuFinanceiro = new JMenu("Financeiro");
				itemFinanceiroFaturaMatriculas = new JMenuItem(new AbstractAction("Faturas Matricula") {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						new FaturasMatriculasWindow(conexao).setVisible(true);
					}
				});
			menuFinanceiro.add(itemFinanceiroFaturaMatriculas);
		menubar.add(menuSistema);
		menubar.add(menuCadastro);
		menubar.add(menuFinanceiro);
		
		framePrincipal = new JFrame("LPL");
		framePrincipal.setDefaultCloseOperation(EXIT_ON_CLOSE);
		framePrincipal.setSize(800,600);
		framePrincipal.setJMenuBar(menubar);
		framePrincipal.setTitle("Controle de Academia - Usuário: "+usuario+" / Perfil: "+perfil);
		framePrincipal.setVisible(true);
		
		menuControlar();
		
	}
	
	private void menuControlar() {
		if (perfil.equalsIgnoreCase("financeiro")) {
			menuCadastro.setEnabled(false);
			menuSistema.setEnabled(true);
			menuFinanceiro.setEnabled(true);
		}
		
		else if (perfil.equalsIgnoreCase("usuario")) {
			menuCadastro.setEnabled(false);
			menuSistema.setEnabled(true);
			menuFinanceiro.setEnabled(false);
		}
		
		else if (perfil.equalsIgnoreCase("cadastral")) {
			menuCadastro.setEnabled(true);
			menuSistema.setEnabled(true);
			menuFinanceiro.setEnabled(false);
		}
	}

}
