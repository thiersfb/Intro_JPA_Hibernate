package dao;

import java.util.List;

import javax.persistence.Query;

import Model.UsuarioPessoa;

public class DaoUsuario<E> extends DaoGeneric<UsuarioPessoa> {
	
	public void removerUsuario(UsuarioPessoa pessoa) throws Exception {

		getEntityManager().getTransaction().begin();
		
		/*
		String sqlExcluirFone = "delete from TBUsuarioTelefone where usuario_id  = " + pessoa.getId();
		getEntityManager().createNativeQuery(sqlExcluirFone).executeUpdate();		

		String sqlExcluirEndereco = "delete from TBUsuarioEndereco where usuario_id  = " + pessoa.getId();
		getEntityManager().createNativeQuery(sqlExcluirEndereco).executeUpdate();
		
		String sqlExcluirEmail = "delete from TBUsuarioEmail where usuario_id  = " + pessoa.getId();
		getEntityManager().createNativeQuery(sqlExcluirEmail).executeUpdate();
		*/
		
		
		getEntityManager().remove(pessoa); // remove em cascata devido à classe modelo, o objeto estar mapeado para remover registros orfãos e remover em cascata
		getEntityManager().getTransaction().commit();

		/*
		String sqlExcluirFone = "delete from TBUsuarioTelefone where usuario_id  = " + pessoa.getId();
		getEntityManager().getTransaction().begin();
		getEntityManager().createNativeQuery(sqlExcluirFone).executeUpdate();
		getEntityManager().getTransaction().commit();
		

		String sqlExcluirEndereco = "delete from TBUsuarioEndereco where usuario_id  = " + pessoa.getId();
		getEntityManager().getTransaction().begin();
		getEntityManager().createNativeQuery(sqlExcluirEndereco).executeUpdate();
		getEntityManager().getTransaction().commit();
		

		String sqlExcluirEmail = "delete from TBUsuarioEmail where usuario_id  = " + pessoa.getId();
		getEntityManager().getTransaction().begin();
		getEntityManager().createNativeQuery(sqlExcluirEmail).executeUpdate();
		getEntityManager().getTransaction().commit();
		*/
		
		super.excluirPorId(pessoa);
		
	}

	public List<UsuarioPessoa> pesquisar(String campoPesquisa) {
		
		String sqlPesquisa = " from TBUsuario where nome like '%" + campoPesquisa + "%' or sobrenome like '%" + campoPesquisa + "%' or login like '%" + campoPesquisa + "%' ";
		
		Query query = super.getEntityManager().createQuery(sqlPesquisa);
		
		return query.getResultList();
	}

}
