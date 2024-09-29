package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ExercicioDAO;
import model.Exercicio;

public class ExercicioService {
    private ExercicioDAO exercicioDAO;

    public ExercicioService(Connection conn) {
        this.exercicioDAO = new ExercicioDAO(conn);
    }

    public void cadastrarExercicio(Exercicio exercicio) throws SQLException {
        exercicioDAO.inserirExercicio(exercicio);
    }

    public Exercicio buscarExercicioPorId(int idExercicio) throws SQLException {
        return exercicioDAO.buscarExercicioPorId(idExercicio);
    }

    public List<Exercicio> listarExercicios() throws SQLException {
        return exercicioDAO.listarExercicios();
    }

    public void atualizarExercicio(Exercicio exercicio) throws SQLException {
        exercicioDAO.atualizarExercicio(exercicio);
    }

    public void deletarExercicio(int idExercicio) throws SQLException {
        exercicioDAO.deletarExercicio(idExercicio);
    }

    public boolean exercicioExiste(int idExercicio) throws SQLException {
        return buscarExercicioPorId(idExercicio) != null;
    }

    public List<Exercicio> listarExerciciosPorModulo(int idModulo) throws SQLException {
        List<Exercicio> todosExercicios = listarExercicios();
        List<Exercicio> exerciciosDoModulo = new ArrayList<>();

        for (Exercicio exercicio : todosExercicios) {
            if (exercicio.getIdModulo() == idModulo) {
                exerciciosDoModulo.add(exercicio);
            }
        }
        return exerciciosDoModulo;
    }
}

