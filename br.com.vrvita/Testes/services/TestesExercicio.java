package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProperties;
import model.Exercicio;
import service.ExercicioService;

public class TestesExercicio {

    private ExercicioService exercicioService;

    public TestesExercicio(Connection conn) {
        this.exercicioService = new ExercicioService(conn);
    }

    public void executarTestes() {
        testeInserirExercicio();
        testeBuscarExercicioPorId();
        testeListarExercicios();
        testeAtualizarExercicio();
//        testeDeletarExercicio();
    }

    private void testeInserirExercicio() {
        Exercicio novoExercicio = new Exercicio();
        novoExercicio.setIdModulo(1);
        novoExercicio.setNomeExercicio("Exercício de Laparoscopia");
        novoExercicio.setDescricao("Treinamento básico em laparoscopia.");
        novoExercicio.setDificuldade("F");

        try {
            exercicioService.cadastrarExercicio(novoExercicio);
            System.out.println("Exercício inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir exercício: " + e.getMessage());
        }
    }

    private void testeBuscarExercicioPorId() {
        try {
            Exercicio exercicio = exercicioService.buscarExercicioPorId(1);
            if (exercicio != null) {
                System.out.println("Exercício encontrado: " + exercicio.getNomeExercicio());
            } else {
                System.out.println("Exercício não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar exercício: " + e.getMessage());
        }
    }

    private void testeListarExercicios() {
        try {
            List<Exercicio> exercicios = exercicioService.listarExercicios();
            for (Exercicio exercicio : exercicios) {
                System.out.println("Exercício: " + exercicio.getNomeExercicio());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar exercícios: " + e.getMessage());
        }
    }

    private void testeAtualizarExercicio() {
        try {
            Exercicio exercicio = exercicioService.buscarExercicioPorId(1);
            if (exercicio != null) {
                exercicio.setDescricao("Treinamento avançado em laparoscopia.");
                exercicio.setDificuldade("D");
                exercicioService.atualizarExercicio(exercicio);
                System.out.println("Exercício atualizado com sucesso.");
            } else {
                System.out.println("Exercício não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar exercício: " + e.getMessage());
        }
    }

    private void testeDeletarExercicio() {
        try {
            exercicioService.deletarExercicio(1);
            System.out.println("Exercício deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar exercício: " + e.getMessage());
        }
    }
}
