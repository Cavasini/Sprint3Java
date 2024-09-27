package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Sessao;

public class SessaoDAO {
	
	private PreparedStatement stmt;
	private Connection conn;
	
	public SessaoDAO(PreparedStatement stmt, Connection conn) {
		this.stmt = stmt;
		this.conn = conn;
	}
	
    // Inserir nova sessão
    public void inserirSessao(Sessao sessao) throws SQLException {
        String sql = "INSERT INTO Sessoes (id_sessao, id_usuario, id_exercicio, data_inicio, data_fim, pontuacao, status_exercicio) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, getNextId(conn));
            stmt.setInt(2, sessao.getIdUsuario());
            stmt.setInt(3, sessao.getIdExercicio());
            stmt.setDate(4, new java.sql.Date(sessao.getDataInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(sessao.getDataFim().getTime()));
            stmt.setDouble(6, sessao.getPontuacao());
            stmt.setInt(7, sessao.getStatusExercicio());
            
            stmt.executeUpdate();
        }catch(SQLException e) {}
    }
    
    // Buscar todas as sessões de um usuário específico
    public List<Sessao> buscarSessoesPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Sessoes WHERE id_usuario = ?";
        List<Sessao> sessoes = new ArrayList<>();
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sessao sessao = new Sessao();
                    sessao.setIdSessao(rs.getInt("id_sessao"));
                    sessao.setIdUsuario(rs.getInt("id_usuario"));
                    sessao.setIdExercicio(rs.getInt("id_exercicio"));
                    sessao.setDataInicio(rs.getDate("data_inicio"));
                    sessao.setDataFim(rs.getDate("data_fim"));
                    sessao.setPontuacao(rs.getDouble("pontuacao"));
                    sessao.setStatusExercicio(rs.getInt("status_exercicio"));
                    sessoes.add(sessao);
                }
            }
        }catch(SQLException e) {}
        return sessoes;
    }
    
    public Sessao buscarSessoesPorId(int idSessao) throws SQLException {
        String sql = "SELECT * FROM Sessoes WHERE id_sessao = ?";
        Sessao sessao = new Sessao();
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idSessao);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sessao.setIdSessao(rs.getInt("id_sessao"));
                    sessao.setIdUsuario(rs.getInt("id_usuario"));
                    sessao.setIdExercicio(rs.getInt("id_exercicio"));
                    sessao.setDataInicio(rs.getDate("data_inicio"));
                    sessao.setDataFim(rs.getDate("data_fim"));
                    sessao.setPontuacao(rs.getDouble("pontuacao"));
                    sessao.setStatusExercicio(rs.getInt("status_exercicio"));
                }
            }
        }catch(SQLException e) {}
        return sessao;
    }
    
    public void atualizarSessao(Sessao sessao) throws SQLException {
        String sql = "UPDATE Sessoes SET pontuacao = ?, status_exercicio = ? WHERE id_sessao = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, sessao.getPontuacao());
            stmt.setInt(2, sessao.getStatusExercicio());
            stmt.setInt(3, sessao.getIdSessao());
            
            stmt.executeUpdate();
        }catch(SQLException e) {}
        
    }
    
    
    public int getNextId(Connection connection) {
        int nextId = 1;  // Inicialmente, define o nextId como 1
        String sql = "SELECT MAX(id_sessao) AS maior_id FROM Sessoes";  // Consulta para obter o maior ID

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maiorId = rs.getInt("maior_id");
                    // Verifica se o resultado foi NULL
                    if (!rs.wasNull()) {
                        nextId = maiorId + 1;  // Incrementa o maior ID
                    }
                    System.out.println(nextId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextId;  // Retorna o próximo ID
    }

    
    

}
