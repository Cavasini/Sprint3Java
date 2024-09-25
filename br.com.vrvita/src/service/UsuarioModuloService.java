package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.UsuarioModuloDAO;

public class UsuarioModuloService {
    private UsuarioModuloDAO usuarioModuloDAO;

    public UsuarioModuloService(PreparedStatement stmt, Connection conn) {
        this.usuarioModuloDAO = new UsuarioModuloDAO(stmt, conn);
    }

    public void inscreverUsuarioEmModulo(int idUsuario, int idModulo) throws SQLException {
        usuarioModuloDAO.inscreverUsuarioEmModulo(idUsuario, idModulo);
    }

    public List<Integer> buscarModulosPorUsuario(int idUsuario) throws SQLException {
        return usuarioModuloDAO.buscarModulosPorUsuario(idUsuario);
    }

    public void removerUsuarioDeModulo(int idUsuario, int idModulo) throws SQLException {
        usuarioModuloDAO.removerUsuarioDeModulo(idUsuario, idModulo);
    }

    // Verificar se o usuário está inscrito em um módulo
    public boolean usuarioInscritoEmModulo(int idUsuario, int idModulo) throws SQLException {
        List<Integer> modulos = buscarModulosPorUsuario(idUsuario);
        return modulos.contains(idModulo);
    }
}

