package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;
	
	
	public UsuarioService(Connection conn) {
		this.usuarioDAO = new UsuarioDAO(conn);
	}
	

	public void cadastrarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.inserirUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int idUsuario) throws SQLException {
        return usuarioDAO.buscarUsuarioPorId(idUsuario);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioDAO.listarUsuarios();
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void deletarUsuario(int idUsuario) throws SQLException {
        usuarioDAO.deletarUsuario(idUsuario);
    }

    // Verificar se o usuário existe com base no ID
    public boolean usuarioExiste(int idUsuario) throws SQLException {
        return buscarUsuarioPorId(idUsuario) != null;
    }
}
