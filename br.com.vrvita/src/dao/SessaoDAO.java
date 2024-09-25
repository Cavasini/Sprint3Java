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
            stmt.setInt(1, sessao.getIdSessao());
            stmt.setInt(2, sessao.getIdUsuario());
            stmt.setInt(3, sessao.getIdExercicio());
            stmt.setDate(4, new java.sql.Date(sessao.getDataInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(sessao.getDataFim().getTime()));
            stmt.setDouble(6, sessao.getPontuacao());
            stmt.setString(7, sessao.getStatusExercicio());
            
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
                    sessao.setStatusExercicio(rs.getString("status_exercicio"));
                    sessoes.add(sessao);
                }
            }
        }catch(SQLException e) {}
        return sessoes;
    }
    
    public void atualizarSessao(Sessao sessao) throws SQLException {
        String sql = "UPDATE Sessoes SET pontuacao = ?, status_exercicio = ? WHERE id_sessao = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, sessao.getPontuacao());
            stmt.setString(2, sessao.getStatusExercicio());
            stmt.setInt(3, sessao.getIdSessao());
            
            stmt.executeUpdate();
        }catch(SQLException e) {}
        
    }

}
