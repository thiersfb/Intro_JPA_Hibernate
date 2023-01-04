package projetomavenhibernate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import Model.UsuarioPessoa;
import dao.DaoGeneric;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() throws ParseException {
		//HibernateUtil.getEntityManager();
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setLogin("thiersfb");
		pessoa.setNome("Thiers");
		pessoa.setSobrenome("Barizon");
		pessoa.setEmail("thiers.fb@gmail.com");
		pessoa.setSenha("thiers07");
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dataNascimento = dateFormat.parse("29-02-1988");
		pessoa.setDataNascimento(dataNascimento);
		
		daoGeneric.salvar(pessoa);
		
		UsuarioPessoa pessoa2 = new UsuarioPessoa();
		pessoa2.setLogin("jupedro");
		pessoa2.setNome("Juliana");
		pessoa2.setSobrenome("Pedro");
		pessoa2.setEmail("jupedro.08@hotmail.com");
		pessoa2.setSenha("jupedroju");
		
		
		//DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dataNascimento = dateFormat.parse("06-11-1985");
		pessoa2.setDataNascimento(dataNascimento);
		
		daoGeneric.salvar(pessoa2);
		
	}
	
}
