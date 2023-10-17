package managedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import Model.EmailUser;
import Model.UsuarioPessoa;
import dao.DaoEmails;
import dao.DaoUsuario;
import datatablelazy.LazyDataTableModelUserPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean {
	
	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	//private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<>();
	//private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private LazyDataTableModelUserPessoa<UsuarioPessoa> list = new LazyDataTableModelUserPessoa<UsuarioPessoa>();
	
	private DaoUsuario<UsuarioPessoa> daoGeneric = new DaoUsuario<UsuarioPessoa>();
	private EmailUser emailUser = new EmailUser();
	private DaoEmails<EmailUser> daoEmail = new DaoEmails<EmailUser>();
	private String campoPesquisa;

	
	private BarChartModel barChartModel = new BarChartModel();
	
	@PostConstruct
	public void init() {
		
		//list = daoGeneric.listar(UsuarioPessoa.class);
		list.load(0, 5, null, null);
		
		montarGrafico();
		
		
	}

	private void montarGrafico() {
		barChartModel = new BarChartModel();
		ChartSeries userSalario = new ChartSeries();
		for(UsuarioPessoa usuarioPessoa : list.list) {
			userSalario.set(usuarioPessoa.getNome(), usuarioPessoa.getSalario());
		}
		barChartModel.addSeries(userSalario); //Adiciona o grupo//
		barChartModel.setTitle("Gráfico de Salários");
	}
	
	public BarChartModel getBarChartModel() {
		return barChartModel;
	}
	
	
	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}
	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}
	
	public EmailUser getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(EmailUser emailUser) {
		this.emailUser = emailUser;
	}

	public void msgNotificacao(String texto) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", texto));
	}
	
	public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    /*
    public void showInfo(String texto) {
        //addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Message Content");
    	addMessage(FacesMessage.SEVERITY_INFO, "Info Message", texto);
    }
    */
    
    public void showMessageSuccess(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Informação", message) );
        //context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Message", message) );
        //context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        //context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }
	
	public void addEmail() {
		//System.out.println(emailUser);
		emailUser.setUsuarioPessoa(usuarioPessoa);
		emailUser = daoEmail.updateMerge(emailUser);
		usuarioPessoa.getEmailsUser().add(emailUser);
		//msgNotificacao("E-mail salvo com sucesso");
		//showInfo("E-mail salvo com sucesso");
		showMessageSuccess("E-mail salvo com sucesso");
		emailUser = new EmailUser();
	}
	
	public String salvar() {
		//daoGeneric.salvar(usuarioPessoa);
		daoGeneric.updateMerge(usuarioPessoa);
		//list.add(usuarioPessoa);
		list.list.add(usuarioPessoa);
		
		//daoUsuario.salvar(daoUsuario);
		//list.add(daoUsuario);
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso"));
		msgNotificacao("Salvo com sucesso");
		init();
		//novo();
		return "";
	}
	
	public void novo() {
		usuarioPessoa = new UsuarioPessoa();
	}
	
	//public List<UsuarioPessoa> getList() {
	public LazyDataTableModelUserPessoa<UsuarioPessoa> getList() {
		
		
		//list = daoGeneric.listar(UsuarioPessoa.class);
		montarGrafico();
		return list;
	}
	
	/*
	public String editar() {
		novo();
		return "";
	}
	*/
	
	public String remover() {
		
		try {
			//daoGeneric.excluirPorId(usuarioPessoa);
			daoGeneric.removerUsuario(usuarioPessoa);
			//list.remove(usuarioPessoa);
			list.list.remove(usuarioPessoa);
			novo();
			init();
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso"));
			msgNotificacao("Removido com sucesso");
		} catch (Exception e) {
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException ) {
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Não foi possível remover pois existem telefones para o usuário"));
				msgNotificacao("Não foi possível remover pois existem telefones para o usuário");
			} else {
				e.printStackTrace();
			}
		}
		return"";			
	}
	
	public void removerEmail() throws Exception {
		String emailId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idEmail");
		
		EmailUser mailDelete = new EmailUser();
		mailDelete.setId(Long.parseLong(emailId));
		daoEmail.excluirPorId(mailDelete);
		usuarioPessoa.getEmailsUser().remove(mailDelete);
		//showMessageSuccess("E-mail ID " + emailId +" removido com sucesso");
		showMessageSuccess("E-mail removido com sucesso");
		
	}
	
	public void pesquisar() {
		//list = daoGeneric.pesquisar(campoPesquisa);
		list.pesquisar(campoPesquisa);
		montarGrafico();
	}
	
	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}
	
	public String getCampoPesquisa() {
		return campoPesquisa;
	}
	
	public void upload(FileUploadEvent image) {
		//String imageExt = "png";
		String ext = image.getFile().getContentType();
		String[] imageExt = ext.split("/");
		String imagem = "data:image/" + imageExt[1] + ";base64," + DatatypeConverter.printBase64Binary(image.getFile().getContent());
		usuarioPessoa.setImagem(imagem);
	}
	
	public void download() throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String fileDownloadID = params.get("fileDownloadId");
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(Long.parseLong(fileDownloadID), UsuarioPessoa.class);
		
		byte[] imagem = new Base64().decodeBase64(pessoa.getImagem().split("\\,")[1]);
		//String imageExt = imagem.getFile().getContentType();
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		response.addHeader("Content-Disposition", "attachment; filename=download.png");
		response.setContentType("application/octet-stream");
		response.setContentLength(imagem.length);
		response.getOutputStream().write(imagem);
		response.getOutputStream().flush();
		FacesContext.getCurrentInstance().responseComplete();
		
	}
	
	
}
