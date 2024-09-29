package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Exercicio;

public class ExercicioDAO {
    private final Connection conn;

    public ExercicioDAO(Connection conn) {
        this.conn = conn;
    }

    // Inserir novo exercício
    public void inserirExercicio(Exercicio exercicio) throws SQLException {
        String sql = "INSERT INTO Exercicios (id_exercicio, id_modulo, nome_exercicio, descricao, dificuldade) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getNextId());
            stmt.setInt(2, exercicio.getIdModulo());
            stmt.setString(3, exercicio.getNomeExercicio());
            stmt.setString(4, exercicio.getDescricao());
            stmt.setString(5, exercicio.getDificuldade());

            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Erro ao inserir exercício: " + e.getMessage() + " [Exercício: " + exercicio.getNomeExercicio() + "]");
        }
    }

    // Buscar exercício pelo ID
    public Exercicio buscarExercicioPorId(int idExercicio) throws SQLException {
        String sql = "SELECT * FROM Exercicios WHERE id_exercicio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idExercicio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Exercicio exercicio = new Exercicio();
                    exercicio.setIdExercicio(rs.getInt("id_exercicio"));
                    exercicio.setIdModulo(rs.getInt("id_modulo"));
                    exercicio.setNomeExercicio(rs.getString("nome_exercicio"));
                    exercicio.setDescricao(rs.getString("descricao"));
                    exercicio.setDificuldade(rs.getString("dificuldade"));
                    return exercicio;
                } else {
                    throw new SQLException("Nenhum exercício encontrado com o ID: " + idExercicio);
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Erro ao buscar exercício pelo ID: " + idExercicio + ". Detalhes: " + e.getMessage());
        }
    }

    // Atualizar exercício
    public void atualizarExercicio(Exercicio exercicio) throws SQLException {
        String sql = "UPDATE Exercicios SET nome_exercicio = ?, descricao = ?, dificuldade = ? WHERE id_exercicio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, exercicio.getNomeExercicio());
            stmt.setString(2, exercicio.getDescricao());
            stmt.setString(3, exercicio.getDificuldade());
            stmt.setInt(4, exercicio.getIdExercicio());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhuma linha foi atualizada. Verifique se o ID do exercício é válido. [ID: " + exercicio.getIdExercicio() + "]");
            }
        } catch(SQLException e) {
            throw new SQLException("Erro ao atualizar exercício [ID: " + exercicio.getIdExercicio() + "]: " + e.getMessage());
        }
    }

    // Deletar exercício pelo ID
    public void deletarExercicio(int idExercicio) throws SQLException {
        String sql = "DELETE FROM Exercicios WHERE id_exercicio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idExercicio);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Nenhuma linha foi deletada. Verifique se o ID do exercício é válido. [ID: " + idExercicio + "]");
            }
        } catch(SQLException e) {
            throw new SQLException("Erro ao deletar exercício [ID: " + idExercicio + "]: " + e.getMessage());
        }
    }

    // Listar todos os exercícios
    public List<Exercicio> listarExercicios() throws SQLException {
        String sql = "SELECT * FROM Exercicios";
        List<Exercicio> exercicios = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Exercicio exercicio = new Exercicio();
                exercicio.setIdExercicio(rs.getInt("id_exercicio"));
                exercicio.setIdModulo(rs.getInt("id_modulo"));
                exercicio.setNomeExercicio(rs.getString("nome_exercicio"));
                exercicio.setDescricao(rs.getString("descricao"));
                exercicio.setDificuldade(rs.getString("dificuldade"));
                exercicios.add(exercicio);
            }
        } catch(SQLException e) {
            throw new SQLException("Erro ao listar exercícios: " + e.getMessage());
        }
        return exercicios;
    }
    
    // Obter o próximo ID para inserir novo exercício
    private int getNextId() throws SQLException {
        String sql = "SELECT MAX(id_exercicio) AS maior_id FROM Exercicios";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int maiorId = rs.getInt("maior_id");
                return maiorId + 1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter o próximo ID para exercício: " + e.getMessage());
        }
    }
}
