package Model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "TBUsuario") 
@Table(name = "TBUsuario") 
@NamedQueries({
	
	@NamedQuery(name = "UsuarioPessoa.findAll", query = "select u from TBUsuario u "),
	@NamedQuery(name = "UsuarioPessoa.findByName", query = "select u from TBUsuario u where u.nome = :nome")
	
})
public class UsuarioPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String sobrenome;
	//private String email;
	private String login;
	private String senha;
	private String sexo;
	private Date dataNascimento;
	private Double salario;
	
	@Column(columnDefinition = "text")
	private String imagem;
	
	//@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<TelefoneUser> telefonesUser; 

	//@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<EnderecoUser> enderecosUser; 

	//@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<EmailUser> emailsUser; 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	/*
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	*/
	
	public void setEmailsUser(List<EmailUser> emailsUser) {
		this.emailsUser = emailsUser;
	}
	
	public List<EmailUser> getEmailsUser() {
		return emailsUser;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public List<TelefoneUser> getTelefonesUser() {
		return telefonesUser;
	}
	public void setTelefonesUser(List<TelefoneUser> telefonesUser) {
		this.telefonesUser = telefonesUser;
	}
	public List<EnderecoUser> getEnderecosUser() {
		return enderecosUser;
	}
	public void setEnderecosUser(List<EnderecoUser> enderecosUser) {
		this.enderecosUser = enderecosUser;
	}
	@Override
	public String toString() {
		//return "UsuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
		return "UsuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome 
				+ ", login=" + login + ", senha=" + senha + ", dataNascimento=" + dataNascimento + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPessoa other = (UsuarioPessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
