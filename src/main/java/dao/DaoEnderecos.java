package dao;

import Model.EnderecoUser;

public class DaoEnderecos<E> extends DaoGeneric<EnderecoUser> {

	public void atualizaEnderecoPrincipalUsuario(EnderecoUser endereco) {
		
		getEntityManager().getTransaction().begin();
		String sqlAtualizarEndereco = "update TBUsuarioEndereco set isEnderecoPrincipal = false where usuario_id  = " + endereco.getUsuarioPessoa().getId();
		getEntityManager().createNativeQuery(sqlAtualizarEndereco).executeUpdate();	
		getEntityManager().getTransaction().commit();

		//super.excluirPorId(endereco);
		
		

	}

}
