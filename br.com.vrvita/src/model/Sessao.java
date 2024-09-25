package model;

import java.util.Date;

public class Sessao {
	

	private int idSessao;
    private int idUsuario;
    private int idExercicio;
    private Date dataInicio;
    private Date dataFim;
    private double pontuacao;  // Pontuação do exercício
    private String statusExercicio; // "Sucesso" ou "Falha"
	
    public int getIdSessao() {
		return idSessao;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public int getIdExercicio() {
		return idExercicio;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public double getPontuacao() {
		return pontuacao;
	}
	public String getStatusExercicio() {
		return statusExercicio;
	}
	
	public void setIdSessao(int idSessao) {
		this.idSessao = idSessao;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdExercicio(int idExercicio) {
		this.idExercicio = idExercicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}
	public void setStatusExercicio(String statusExercicio) {
		this.statusExercicio = statusExercicio;
	}

    // Getters e Setters
}
