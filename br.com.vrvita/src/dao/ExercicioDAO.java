package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Exercicio;

public class ExercicioDAO {
	private PreparedStatement stmt;
	private Connection conn;
	
	public ExercicioDAO(PreparedStatement stmt, Connection conn) {
		this.stmt = stmt;
		this.conn = conn;
	}
	
    // Inserir novo exercício
    public void inserirExercicio(Exercicio exercicio) throws SQLException {
        String sql = "INSERT INTO Exercicios (id_exercicio, id_modulo, nome_exercicio, descricao, dificuldade) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try  {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, getNextId(conn));
            stmt.setInt(2, exercicio.getIdModulo());
            stmt.setString(3, exercicio.getNomeExercicio());
            stmt.setString(4, exercicio.getDescricao());
            stmt.setString(5, exercicio.getDificuldade());

            stmt.executeUpdate();
        }catch(SQLException e) {
        	
        }
    }

    // Buscar exercício pelo ID
    public Exercicio buscarExercicioPorId(int idExercicio) throws SQLException {
        String sql = "SELECT * FROM Exercicios WHERE id_exercicio = ?";
        try	{
        	stmt = conn.prepareStatement(sql);
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
                }
            }
        }catch(SQLException e) {
        	
        }
        return null;
    }

    // Atualizar exercício
    public void atualizarExercicio(Exercicio exercicio) throws SQLException {
        String sql = "UPDATE Exercicios SET nome_exercicio = ?, descricao = ?, dificuldade = ? WHERE id_exercicio = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setString(1, exercicio.getNomeExercicio());
            stmt.setString(2, exercicio.getDescricao());
            stmt.setString(3, exercicio.getDificuldade());
            stmt.setInt(4, exercicio.getIdExercicio());

            stmt.executeUpdate();
        }catch(SQLException e) {
        	
        }
    }

    // Deletar exercício pelo ID
    public void deletarExercicio(int idExercicio) throws SQLException {
        String sql = "DELETE FROM Exercicios WHERE id_exercicio = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idExercicio);
            stmt.executeUpdate();
        }catch(SQLException e) {
        	
        }
    }

    // Listar todos os exercícios
    public List<Exercicio> listarExercicios() throws SQLException {
        String sql = "SELECT * FROM Exercicios";
        List<Exercicio> exercicios = new ArrayList<>();
        try {
        	stmt = conn.prepareStatement(sql);
        	ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exercicio exercicio = new Exercicio();
                exercicio.setIdExercicio(rs.getInt("id_exercicio"));
                exercicio.setIdModulo(rs.getInt("id_modulo"));
                exercicio.setNomeExercicio(rs.getString("nome_exercicio"));
                exercicio.setDescricao(rs.getString("descricao"));
                exercicio.setDificuldade(rs.getString("dificuldade"));
                exercicios.add(exercicio);
            }
        }catch(SQLException e) {
        	
        }
        return exercicios;
    }
    
    public int getNextId(Connection connection) {
        int nextId = 0;
        String sql = "SELECT MAX(id_exercicio) AS maior_id FROM Exercicios";  // Consulta para contar os nomes

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maiorId = rs.getInt("maior_id");
                    // Verifica se o resultado foi NULL
                    if (!rs.wasNull()) {
                        nextId = maiorId + 1;  // Incrementa o maior ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nextId;  // Retorna o próximo ID
    }
}

