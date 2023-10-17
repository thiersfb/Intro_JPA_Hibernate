package managedBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.google.gson.Gson;

import Model.EnderecoUser;
import Model.UsuarioPessoa;
import dao.DaoEnderecos;
import dao.DaoUsuario;

@ManagedBean(name = "enderecoUsuarioPessoaManagedBean")
@ViewScoped
public class EnderecoUserManagedBean {
	
	private UsuarioPessoa usuario = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<>();
	private DaoEnderecos<EnderecoUser> daoEndereco = new DaoEnderecos<EnderecoUser>();
	private EnderecoUser endereco = new EnderecoUser();
	
	@PostConstruct
	public void init() {
		String user_id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("user_id"); // user_id -> parametro enviado da pagina de origem
		System.out.println(user_id);
		usuario = daoUser.pesquisar(Long.parseLong(user_id), UsuarioPessoa.class);
	}
	

	public void pesquisaCep(AjaxBehaviorEvent event ) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + endereco.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}
			
			EnderecoUser enderecoCep = new Gson().fromJson(jsonCep.toString(), EnderecoUser.class);
			//System.out.println(enderecoCep);
			endereco.setCep(enderecoCep.getCep());
			endereco.setLogradouro(enderecoCep.getLogradouro());
			endereco.setBairro(enderecoCep.getBairro());
			endereco.setCidade(enderecoCep.getCidade());
			endereco.setUf(enderecoCep.getUf());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//public String salvar() {
	public String salvar() {
		endereco.setUsuarioPessoa(usuario);
		//daoTelefone.salvar(telefone);
		try {
//			daoEndereco.atualizaEnderecoPrincipalUsuario(endereco);
			if (endereco.isEnderecoPrincipal() == true) {
				daoEndereco.atualizaEnderecoPrincipalUsuario(endereco);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		daoEndereco.updateMerge(endereco);
		recarregar();
		msgNotificacao("Salvo com sucesso");
		return "";
	}
	
	public String excluirEndereco() throws Exception {
		daoEndereco.excluirPorId(endereco);
		recarregar();
		msgNotificacao("Removido com sucesso");
		return "";
	}
	
	public void msgNotificacao(String texto) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", texto));
	}
	
	public void novo() {
		endereco = new EnderecoUser();
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
	
	
	public void setDaoEndereco(DaoEnderecos<EnderecoUser> daoEndereco) {
		this.daoEndereco = daoEndereco;
	}
	
	public DaoEnderecos<EnderecoUser> getDaoEnderecos() {
		return daoEndereco;
	}
	
	public void setEndereco(EnderecoUser endereco) {
		this.endereco = endereco;
	}
	
	public EnderecoUser getEndereco() {
		return endereco;
	}

}
