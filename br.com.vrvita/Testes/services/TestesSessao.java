package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionProperties;
import model.Sessao;
import service.SessaoService;

public class TestesSessao {

    public static void testeIniciarSessao(SessaoService sessaoService) {
        try {
            int idUsuario = 1;
            int idExercicio = 1;
            Date dataInicio = new Date(System.currentTimeMillis());
            
            sessaoService.iniciarSessao(idUsuario, idExercicio, dataInicio);
            System.out.println("Sessão iniciada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeFinalizarSessao(SessaoService sessaoService) {
        try {
            int idSessao = 1; // Assumindo que a sessão com ID 1 já existe
            double pontuacao = 8.5;
            int statusExercicio = 1;

            sessaoService.finalizarSessao(idSessao, pontuacao, statusExercicio);
            System.out.println("Sessão finalizada com sucesso.");
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void testeBuscarSessoesPorUsuario(SessaoService sessaoService) {
        try {
            int idUsuario = 1; // Assumindo que o usuário com ID 1 tem sessões registradas
            List<Sessao> sessoes = sessaoService.buscarSessoesPorUsuario(idUsuario);
            for (Sessao sessao : sessoes) {
                System.out.println("Sessão: " + sessao.getIdSessao() + ", Exercicio: " + sessao.getIdExercicio());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void testes(Connection conn) {
        try {
            Statement stmt = null;
            PreparedStatement preparedStatement = null;

            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            conn = DriverManager.getConnection(url, ConnectionProperties.getConnection());

            stmt = conn.createStatement();

            SessaoService sessaoService = new SessaoService(preparedStatement, conn);

            testeIniciarSessao(sessaoService);
            testeFinalizarSessao(sessaoService);
            testeBuscarSessoesPorUsuario(sessaoService);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
