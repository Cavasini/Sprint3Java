package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Sessao;

public class SessaoDAO {
    
    private Connection conn;
    
    public SessaoDAO(Connection conn) {
        this.conn = conn;
    }
    
    // Inserir nova sessão
    public void inserirSessao(Sessao sessao) throws SQLException {
        String sql = "INSERT INTO Sessoes (id_sessao, id_usuario, id_exercicio, data_inicio, data_fim, pontuacao, status_exercicio) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, getNextId());
            stmt.setInt(2, sessao.getIdUsuario());
            stmt.setInt(3, sessao.getIdExercicio());
            stmt.setDate(4, new java.sql.Date(sessao.getDataInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(sessao.getDataFim().getTime()));
            stmt.setDouble(6, sessao.getPontuacao());
            stmt.setInt(7, sessao.getStatusExercicio());
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Erro ao inserir a sessão: " + e.getMessage(), e);
        }
    }
    
    // Buscar todas as sessões de um usuário específico
    public List<Sessao> buscarSessoesPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Sessoes WHERE id_usuario = ?";
        List<Sessao> sessoes = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch(SQLException e) {
            throw new SQLException("Erro ao buscar sessões para o usuário com ID " + idUsuario + ": " + e.getMessage(), e);
        }
        return sessoes;
    }
    
    // Buscar sessão por ID
    public Sessao buscarSessaoPorId(int idSessao) throws SQLException {
        String sql = "SELECT * FROM Sessoes WHERE id_sessao = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSessao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Sessao sessao = new Sessao();
                    sessao.setIdSessao(rs.getInt("id_sessao"));
                    sessao.setIdUsuario(rs.getInt("id_usuario"));
                    sessao.setIdExercicio(rs.getInt("id_exercicio"));
                    sessao.setDataInicio(rs.getDate("data_inicio"));
                    sessao.setDataFim(rs.getDate("data_fim"));
                    sessao.setPontuacao(rs.getDouble("pontuacao"));
                    sessao.setStatusExercicio(rs.getInt("status_exercicio"));
                    return sessao;
                }
            }
        } catch(SQLException e) {
            throw new SQLException("Erro ao buscar a sessão com ID " + idSessao + ": " + e.getMessage(), e);
        }
        return null; // Se não encontrar, retorna null
    }
    
    // Atualizar sessão
    public void atualizarSessao(Sessao sessao) throws SQLException {
        String sql = "UPDATE Sessoes SET pontuacao = ?, status_exercicio = ? WHERE id_sessao = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, sessao.getPontuacao());
            stmt.setInt(2, sessao.getStatusExercicio());
            stmt.setInt(3, sessao.getIdSessao());
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new SQLException("Erro ao atualizar a sessão com ID " + sessao.getIdSessao() + ": " + e.getMessage(), e);
        }
    }
    
    // Buscar próximo ID de sessão
    private int getNextId() throws SQLException {
        int nextId = 1;  
        String sql = "SELECT MAX(id_sessao) AS maior_id FROM Sessoes";  

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int maiorId = rs.getInt("maior_id");
                if (!rs.wasNull()) {
                    nextId = maiorId + 1;  
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter o próximo ID de sessão: " + e.getMessage(), e);
        }

        return nextId;  
    }
}
