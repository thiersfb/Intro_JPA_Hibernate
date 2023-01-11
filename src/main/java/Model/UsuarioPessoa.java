package Model;

import java.util.Date;
import java.util.List;

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
	private String email;
	private String login;
	private String senha;
	
	private Date dataNascimento;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<TelefoneUser> telefonesUser; 
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public List<TelefoneUser> getTelefonesUser() {
		return telefonesUser;
	}
	public void setTelefonesUser(List<TelefoneUser> telefonesUser) {
		this.telefonesUser = telefonesUser;
	}
	
	
	@Override
	public String toString() {
		return "UsuarioPessoa [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email
				+ ", login=" + login + ", senha=" + senha + ", dataNascimento=" + dataNascimento + "]";
	}
	
	
	
}
