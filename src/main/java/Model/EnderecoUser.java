package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "TBUsuarioEndereco")
@Table(name = "TBUsuarioEndereco")
public class EnderecoUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String numero;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private UsuarioPessoa usuario;

	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false, name = "cidade")
	private String localidade;	//cidade

	@Column(nullable = false)
	private String uf;
	
	@Column(nullable = false)
	private String complemento;

	@Column(nullable = false)
	private boolean isEnderecoPrincipal; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return localidade;
	}

	public void setCidade(String cidade) {
		this.localidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public boolean isEnderecoPrincipal() {
		return isEnderecoPrincipal;
	}

	public void setEnderecoPrincipal(boolean isEnderecoPrincipal) {
		this.isEnderecoPrincipal = isEnderecoPrincipal;
	}

	public UsuarioPessoa getUsuarioPessoa() {
		return usuario;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuario) {
		this.usuario = usuario;
	}

}
