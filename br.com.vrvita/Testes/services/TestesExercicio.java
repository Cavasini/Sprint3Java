package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionProperties;
import model.Exercicio;
import service.ExercicioService;

public class TestesExercicio {

    public static void testeInserirExercicio(ExercicioService exercicioService) {
        Exercicio novoExercicio = new Exercicio();
        novoExercicio.setIdModulo(1); // Supondo que existe um módulo com ID 1
        novoExercicio.setNomeExercicio("Exercício de Laparoscopia");
        novoExercicio.setDescricao("Treinamento básico em laparoscopia.");
        novoExercicio.setDificuldade("F");

        try {
        	
            exercicioService.cadastrarExercicio(novoExercicio);;
            System.out.println("Exercício inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeBuscarExercicioPorId(ExercicioService exercicioService) {
        try {
            Exercicio exercicio = exercicioService.buscarExercicioPorId(1);
            if (exercicio != null) {
                System.out.println("Exercício encontrado: " + exercicio.getNomeExercicio());
            } else {
                System.out.println("Exercício não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeListarExercicios(ExercicioService exercicioService) {
        try {
            List<Exercicio> exercicios = exercicioService.listarExercicios();
            for (Exercicio exercicio : exercicios) {
                System.out.println("Exercício: " + exercicio.getNomeExercicio());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeAtualizarExercicio(ExercicioService exercicioService) {
        try {
            Exercicio exercicio = exercicioService.buscarExercicioPorId(1); // Assumindo que o exercício com ID 1 já existe
            if (exercicio != null) {
                exercicio.setDescricao("Treinamento avançado em laparoscopia.");
                exercicio.setDificuldade("D");
                exercicioService.atualizarExercicio(exercicio);
                System.out.println("Exercício atualizado com sucesso.");
            } else {
                System.out.println("Exercício não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeDeletarExercicio(ExercicioService exercicioService) {
        try {
            exercicioService.deletarExercicio(1); // Assumindo que o exercício com ID 1 existe
            System.out.println("Exercício deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void testes(Connection conn) {
        try {
            PreparedStatement preparedStatement = null;

            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            conn = DriverManager.getConnection(url, ConnectionProperties.getConnection());

            // Instanciando ExercicioService com PreparedStatement e Connection
            ExercicioService exercicioService = new ExercicioService(preparedStatement, conn);

            // Testes das funções
            testeInserirExercicio(exercicioService);
            testeBuscarExercicioPorId(exercicioService);
            testeListarExercicios(exercicioService);
            testeAtualizarExercicio(exercicioService);
            testeDeletarExercicio(exercicioService);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
