package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Model.TelefoneUser;
import Model.UsuarioPessoa;
import dao.DaoTelefones;
import dao.DaoUsuario;

@ManagedBean(name = "telefoneUsuarioPessoaManagedBean")
@ViewScoped
public class TelefoneUserManagedBean {
	
	private UsuarioPessoa usuario = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<>();
	private DaoTelefones<TelefoneUser> daoTelefone = new DaoTelefones<TelefoneUser>();
	private TelefoneUser telefone = new TelefoneUser();
	
	@PostConstruct
	public void init() {
		String user_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user_id"); // user_id -> parametro enviado da pagina de origem
		//System.out.println(user_id);
		usuario = daoUser.pesquisar(Long.parseLong(user_id), UsuarioPessoa.class);
	}
	
	public String salvar() {
		telefone.setUsuarioPessoa(usuario);
		//daoTelefone.salvar(telefone);
		daoTelefone.updateMerge(telefone);
		recarregar();
		msgNotificacao("Salvo com sucesso");
		return "";
	}
	
	public String excluirTelefone() throws Exception {
		daoTelefone.excluirPorId(telefone);
		recarregar();
		msgNotificacao("Removido com sucesso");
		return "";
	}
	
	public void msgNotificacao(String texto) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", texto));
	}
	
	public void novo() {
		telefone = new TelefoneUser();
	}
	
	public void recarregar() {
		usuario = daoUser.pesquisar(usuario.getId(), UsuarioPessoa.class);
		novo();
	}
	
	public void setUsuario(UsuarioPessoa usuario) {
		this.usuario = usuario;
	}
	
	public UsuarioPessoa getUsuario() {
		return usuario;
	}
	
	public void setDaoTelefone(DaoTelefones<TelefoneUser> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}
	
	public DaoTelefones<TelefoneUser> getDaoTelefone() {
		return daoTelefone;
	}
	
	public void setTelefone(TelefoneUser telefone) {
		this.telefone = telefone;
	}
	
	public TelefoneUser getTelefone() {
		return telefone;
	}

}
