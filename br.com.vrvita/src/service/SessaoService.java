package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.SessaoDAO;
import model.Sessao;

public class SessaoService {
	
	private SessaoDAO sessaoDAO;
	
	public SessaoService(PreparedStatement stmt, Connection conn) {
		this.sessaoDAO= new SessaoDAO(stmt, conn);
	}
	
    public void iniciarSessao(int idUsuario, int idExercicio, Date dataInicio) throws SQLException {
        Sessao sessao = new Sessao();
        sessao.setIdUsuario(idUsuario);
        sessao.setIdExercicio(idExercicio);
        sessao.setDataInicio(dataInicio);
        sessao.setDataFim(calcularFimSessao(dataInicio));  // Lógica para calcular o fim da sessão

        // Definir pontuação inicial como 0 e status como 'Falha'
        sessao.setPontuacao(0.0);
        sessao.setStatusExercicio(0);

        // Inserir nova sessão
        sessaoDAO.inserirSessao(sessao);
    }
    
    public void finalizarSessao(int idSessao, double pontuacao, int statusExercicio) throws SQLException {
        Sessao sessao =  sessaoDAO.buscarSessoesPorId(idSessao);
        
        if (sessao == null) {
            throw new IllegalArgumentException("Sessão não encontrada.");
        }

        // Regras de negócio: Pontuação deve estar entre 0 e 10 e status deve ser 'Sucesso' ou 'Falha'
        if (pontuacao < 0 || pontuacao > 10) {
            throw new IllegalArgumentException("Pontuação deve estar entre 0 e 10.");
        }
        if (statusExercicio != 0 && statusExercicio != 1) {
            throw new IllegalArgumentException("Status inválido. Deve ser 0 ou 1.");
        }

        sessao.setPontuacao(pontuacao);
        sessao.setStatusExercicio(statusExercicio);

        // Atualizar sessão no banco
        sessaoDAO.atualizarSessao(sessao);
    }
    
    public List<Sessao> buscarSessoesPorUsuario(int idUsuario) throws SQLException {
        return sessaoDAO.buscarSessoesPorUsuario(idUsuario);
    }
    
    private Date calcularFimSessao(Date dataInicio) {
        // Exemplo de cálculo: adicionar 2 horas à data de início
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataInicio);
        cal.add(Calendar.HOUR_OF_DAY, 2);
        return cal.getTime();
    }

}
