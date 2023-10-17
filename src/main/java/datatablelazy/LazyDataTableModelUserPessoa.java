package datatablelazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import Model.UsuarioPessoa;
import dao.DaoUsuario;

public class LazyDataTableModelUserPessoa<T> extends LazyDataModel<UsuarioPessoa> {
	
	private DaoUsuario<UsuarioPessoa>  daoUsuario = new DaoUsuario<UsuarioPessoa>();
	
	public List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private String sql = " from TBUsuario";

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		
		return 0;
	}

	@Override
	public List<UsuarioPessoa> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
	//public List<UsuarioPessoa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filterBy) {
		
		
		list = daoUsuario.getEntityManager().createQuery(getSql())
				.setFirstResult(first)
				.setMaxResults(pageSize)
				.getResultList();
		
		setPageSize(pageSize);
		Integer qtdRegistro = Integer.parseInt(daoUsuario.getEntityManager().createQuery(" select count(1) " + getSql()).getSingleResult().toString());
		
		setRowCount(qtdRegistro);
		

		sql = " from TBUsuario";
		
		return list;
	}
	
	public String getSql() {
		return sql;
	}
	
	public List<UsuarioPessoa> getList() {
		return list;
	}
	
	public void pesquisar(String campoPesquisa) {
		sql += " where nome like '%" + campoPesquisa + "%' or sobrenome like '%" + campoPesquisa + "%' or login like '%" + campoPesquisa + "%' ";
	}
	
}
