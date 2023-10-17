package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import Model.EmailUser;
import Model.UsuarioPessoa;
import dao.DaoEmails;
import dao.DaoUsuario;

@ManagedBean(name = "emailUsuarioPessoaManagedBean")
@ViewScoped
public class EmailUserManagedBean {
	
	private UsuarioPessoa usuario = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<>();
	private DaoEmails<EmailUser> daoEmail = new DaoEmails<EmailUser>();
	private EmailUser email = new EmailUser();
	
	@PostConstruct
	public void init() {
		String user_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user_id"); // user_id -> parametro enviado da pagina de origem
		//System.out.println(user_id);
		usuario = daoUser.pesquisar(Long.parseLong(user_id), UsuarioPessoa.class);
	}
	
	public String salvar() {
		email.setUsuarioPessoa(usuario);
		//daoTelefone.salvar(telefone);
		daoEmail.updateMerge(email);
		recarregar();
		msgNotificacao("Salvo com sucesso");
		return "";
	}
	
	public String excluirEmail() throws Exception {
		daoEmail.excluirPorId(email);
		recarregar();
		msgNotificacao("Removido com sucesso");
		return "";
	}
	
	public void msgNotificacao(String texto) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", texto));
	}
	
	public void novo() {
		email = new EmailUser();
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
	
	public void setDaoTelefone(DaoEmails<EmailUser> daoEmail) {
		this.daoEmail = daoEmail;
	}
	
	public DaoEmails<EmailUser> getDaoEmail() {
		return daoEmail;
	}
	
	public void setEmail(EmailUser email) {
		this.email = email;
	}
	
	public EmailUser getEmail() {
		return email;
	}

}
