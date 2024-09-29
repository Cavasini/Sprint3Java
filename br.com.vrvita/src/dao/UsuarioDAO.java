package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {
    
    private Connection conn;

    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }
    
    // Método auxiliar para obter o PreparedStatement
    private PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    // Método para inserir novo usuário
    public void inserirUsuario(Usuario usuario) throws SQLException {
        validarUsuario(usuario);
        String sql = "INSERT INTO Usuarios (id_usuario, nome, email, senha, tipo_usuario, especialidade, instituicao, nivel_experiencia, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = getPreparedStatement(sql)) {
            stmt.setInt(1, getNextId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getTipoUsuario());
            stmt.setString(6, usuario.getEspecialidade());
            stmt.setString(7, usuario.getInstituicao());
            stmt.setInt(8, usuario.getNivelExperiencia());
            stmt.setDate(9, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // Método para buscar usuário pelo ID
    public Usuario buscarUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = getPreparedStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return null;
    }

    // Método para atualizar um usuário existente
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        validarUsuario(usuario);
        String sql = "UPDATE Usuarios SET nome = ?, email = ?, senha = ?, tipo_usuario = ?, especialidade = ?, instituicao = ?, nivel_experiencia = ?, data_nascimento = ? WHERE id_usuario = ?";
        try (PreparedStatement stmt = getPreparedStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setString(5, usuario.getEspecialidade());
            stmt.setString(6, usuario.getInstituicao());
            stmt.setInt(7, usuario.getNivelExperiencia());
            stmt.setDate(8, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setInt(9, usuario.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Método para deletar um usuário pelo ID
    public void deletarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = getPreparedStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = getPreparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(criarUsuario(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Método para gerar o próximo ID de usuário
    private int getNextId() throws SQLException {
        int nextId = 1;
        String sql = "SELECT MAX(id_usuario) AS maior_id FROM Usuarios";
        try (PreparedStatement stmt = getPreparedStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                nextId = rs.getInt("maior_id") + 1;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter próximo ID: " + e.getMessage());
        }
        return nextId;
    }

    // Método para criar um objeto Usuario a partir do ResultSet
    private Usuario criarUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipoUsuario(rs.getString("tipo_usuario"));
        usuario.setEspecialidade(rs.getString("especialidade"));
        usuario.setInstituicao(rs.getString("instituicao"));
        usuario.setNivelExperiencia(rs.getInt("nivel_experiencia"));
        usuario.setDataNascimento(rs.getDate("data_nascimento"));
        usuario.setDataCadastro(rs.getDate("data_cadastro"));
        return usuario;
    }

    // Método para validar um usuário antes da inserção/atualização
    private void validarUsuario(Usuario usuario) {
        if (!usuario.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        if (!List.of("E", "M", "R").contains(usuario.getTipoUsuario())) {
            throw new IllegalArgumentException("Tipo de usuário inválido.");
        }

        if (!List.of(1, 2, 3).contains(usuario.getNivelExperiencia())) {
            throw new IllegalArgumentException("Nível de experiência inválido.");
        }
    }
}
