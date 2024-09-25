package model;

import java.util.Date;

public class Usuario {
	

	private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;
    private String especialidade;
    private String instituicao;
    private String nivelExperiencia;
    private Date dataNascimento;
    private Date dataCadastro;
    
    
	public int getIdUsuario() {
		return idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public String getInstituicao() {
		return instituicao;
	}
	public String getNivelExperiencia() {
		return nivelExperiencia;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
    public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public void setNivelExperiencia(String nivelExperiencia) {
		this.nivelExperiencia = nivelExperiencia;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

    
}

