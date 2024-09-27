package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {
	private PreparedStatement stmt;
	private Connection conn;
	
	public UsuarioDAO(PreparedStatement stmt, Connection conn) {
		this.stmt = stmt;
		this.conn = conn;
	}
	

	public void inserirUsuario(Usuario usuario) throws SQLException {
	    // Validações
	    if (!usuario.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	        throw new IllegalArgumentException("E-mail inválido.");
	    }

	    if (!List.of("E", "M", "R").contains(usuario.getTipoUsuario())) {
	        throw new IllegalArgumentException("Tipo de usuário inválido.");
	    }

	    if (!List.of(1, 2, 3).contains(usuario.getNivelExperiencia())) {
	        throw new IllegalArgumentException("Nível de experiência inválido.");
	    }

	    String sql = "INSERT INTO Usuarios (id_usuario, nome, email, senha, tipo_usuario, especialidade, instituicao, nivel_experiencia, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	    	stmt.setInt(1, getNextId(conn));
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
	        System.err.println("Erro ao inserir usuário: " + usuario);
	        e.printStackTrace();
	    }
	}

	
    public Usuario buscarUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM Usuarios WHERE id_usuario = ?";
        try {
        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuarios SET nome = ?, email = ?, senha = ?, tipo_usuario = ?, especialidade = ?, instituicao = ?, nivel_experiencia = ?, data_nascimento = ? WHERE id_usuario = ?";
        try {
        	stmt = conn.prepareStatement(sql);
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
        }catch(SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public void deletarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM Usuarios WHERE id_usuario = ?";
        try  {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }catch(SQLException e) {
        	e.printStackTrace();
        }
    }
    
    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        try  {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
                usuarios.add(usuario);
            }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
        return usuarios;
    }
    
    
    
    public int getNextId(Connection connection) {
        int nextId = 0;
        String sql = "SELECT MAX(id_usuario) AS maior_id FROM Usuarios";  // Consulta para contar os nomes

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
