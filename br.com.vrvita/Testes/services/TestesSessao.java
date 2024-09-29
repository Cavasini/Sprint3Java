package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Sessao;
import service.SessaoService;

public class TestesSessao {

    private SessaoService sessaoService;

    // Construtor que recebe a conexão como parâmetro
    public TestesSessao(Connection conn) {
        this.sessaoService = new SessaoService(conn);
    }

    public void executarTestes() {
        testeIniciarSessao();
        testeFinalizarSessao();
        testeBuscarSessoesPorUsuario();
    }

    private void testeIniciarSessao() {
        try {
            int idUsuario = 1;
            int idExercicio = 1;
            Date dataInicio = new Date(System.currentTimeMillis());
            
            sessaoService.iniciarSessao(idUsuario, idExercicio, dataInicio);
            System.out.println("Sessão iniciada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao iniciar sessão: " + e.getMessage());
        }
    }

    private void testeFinalizarSessao() {
        try {
            int idSessao = 1; // Assumindo que a sessão com ID 1 já existe
            double pontuacao = 8.5;
            int statusExercicio = 1;

            sessaoService.finalizarSessao(idSessao, pontuacao, statusExercicio);
            System.out.println("Sessão finalizada com sucesso.");
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Erro ao finalizar sessão: " + e.getMessage());
        }
    }

    private void testeBuscarSessoesPorUsuario() {
        try {
            int idUsuario = 1; // Assumindo que o usuário com ID 1 tem sessões registradas
            List<Sessao> sessoes = sessaoService.buscarSessoesPorUsuario(idUsuario);
            for (Sessao sessao : sessoes) {
                System.out.println("Sessão: " + sessao.getIdSessao() + ", Exercício: " + sessao.getIdExercicio());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar sessões: " + e.getMessage());
        }
    }
}
