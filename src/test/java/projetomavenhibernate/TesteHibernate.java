package projetomavenhibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.junit.Test;

import Model.UsuarioPessoa;
import dao.DaoGeneric;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() throws ParseException {
		//HibernateUtil.getEntityManager();
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setLogin("userTeste");
		pessoa.setSenha("userTeste");
		pessoa.setNome("User");
		pessoa.setSobrenome("Teste");
		pessoa.setEmail("teste@teste.com");
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dataNascimento = dateFormat.parse("02-02-2002");
		pessoa.setDataNascimento(dataNascimento);
		
		daoGeneric.salvar(pessoa);
		
	}
	
	@Test
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(1L); //pesquisando id = 2
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
	}
	
	
	@Test
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(2L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}

	@Test
	public void testeUpdate() throws ParseException {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		
		pessoa.setNome("Nome atualizado hibernate");
		pessoa.setSobrenome("Sobrenome atualizado hibernate");
		pessoa.setEmail("teste@teste.com");
		pessoa.setLogin("guest123");
		pessoa.setSenha("guest456");

		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dataNascimento = dateFormat.parse("29-02-1988");
		pessoa.setDataNascimento(dataNascimento);
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
	}

	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(6L, UsuarioPessoa.class);
		daoGeneric.excluirPorId(pessoa);
		
	}

	@Test
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------");
		}
	}
	
	@Test
	public void testeQueryList() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		//List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from TBUsuario").getResultList();
		//List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa").getResultList();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from " + UsuarioPessoa.class.getAnnotation(Table.class).name() + " where login = 'thiersfb' ").getResultList();

		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------");
			
		}
		
	}
	

	@Test
	public void testeQueryListMaxResult() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from "
				+ UsuarioPessoa.class.getAnnotation(Table.class).name() + " where login != 'thiersfb' order by nome ")
				.setMaxResults(5)	/* limita a quantia de registros encontrados */
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------");
			
		}
		
	}
	
	@Test
	public void testeQueryListParameter() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery(" from " + UsuarioPessoa.class.getAnnotation(Table.class).name() + " where login != :login order by nome ") /* :param_name é a sintaxe de passagem por parâmetro na query*/
				.setParameter("login", "thiersfb") /* nome do parâmetro seguindo do valor passado através do parâmetro; no exemplo o valor está fixo como "thiersfb */
				.setMaxResults(5)	/* limita a quantia de registros encontrados */
				.getResultList(); 
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("-----------------------------");
			
		}
	}
	
	@Test
	public void testeQuerySoma() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Long sumIDs = (Long) daoGeneric.getEntityManager().createQuery(" select sum(id) from " +  UsuarioPessoa.class.getAnnotation(Table.class).name())
				.getSingleResult();
		
		System.out.println("Soma de todos os IDs: " + sumIDs);
	}
	
	
}
