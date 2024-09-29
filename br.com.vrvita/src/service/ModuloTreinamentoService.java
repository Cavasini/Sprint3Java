package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.ModuloTreinamentoDAO;
import model.ModuloTreinamento;

public class ModuloTreinamentoService {
    private ModuloTreinamentoDAO moduloDAO;

    public ModuloTreinamentoService(Connection conn) {
        this.moduloDAO = new ModuloTreinamentoDAO(conn);
    }

    public void cadastrarModulo(ModuloTreinamento modulo) throws SQLException {
        moduloDAO.inserirModulo(modulo);
    }

    public ModuloTreinamento buscarModuloPorId(int idModulo) throws SQLException {
        return moduloDAO.buscarModuloPorId(idModulo);
    }

    public List<ModuloTreinamento> listarModulos() throws SQLException {
        return moduloDAO.listarModulos();
    }

    public void atualizarModulo(ModuloTreinamento modulo) throws SQLException {
        moduloDAO.atualizarModulo(modulo);
    }

    public void deletarModulo(int idModulo) throws SQLException {
        moduloDAO.deletarModulo(idModulo);
    }

    // Verificar se o módulo existe com base no ID
    public boolean moduloExiste(int idModulo) throws SQLException {
        return buscarModuloPorId(idModulo) != null;
    }
}
