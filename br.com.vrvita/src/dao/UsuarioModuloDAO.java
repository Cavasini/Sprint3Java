package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioModuloDAO {
    
    private Connection conn;
    
    public UsuarioModuloDAO(Connection conn) {
        this.conn = conn;
    }

    // Inserir novo relacionamento Usuário-Módulo
    public void inscreverUsuarioEmModulo(int idUsuario, int idModulo) throws SQLException {
        String sql = "INSERT INTO Usuarios_Modulos (id_usuario, id_modulo, data_inscricao) " +
                     "VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idModulo);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inscrever o usuário com ID " + idUsuario + " no módulo com ID " + idModulo + ": " + e.getMessage(), e);
        }
    }

    // Buscar módulos de um usuário
    public List<Integer> buscarModulosPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT id_modulo FROM Usuarios_Modulos WHERE id_usuario = ?";
        List<Integer> modulos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    modulos.add(rs.getInt("id_modulo"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar módulos para o usuário com ID " + idUsuario + ": " + e.getMessage(), e);
        }
        return modulos;
    }

    // Remover inscrição de um módulo
    public void removerUsuarioDeModulo(int idUsuario, int idModulo) throws SQLException {
        String sql = "DELETE FROM Usuarios_Modulos WHERE id_usuario = ? AND id_modulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idModulo);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover o usuário com ID " + idUsuario + " do módulo com ID " + idModulo + ": " + e.getMessage(), e);
        }
    }
}
