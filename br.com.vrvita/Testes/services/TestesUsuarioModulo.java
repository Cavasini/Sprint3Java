package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UsuarioModuloDAO;

public class TestesUsuarioModulo {

    private UsuarioModuloDAO usuarioModuloDAO;

    // Construtor que recebe a conexão como parâmetro
    public TestesUsuarioModulo(Connection conn) {
        this.usuarioModuloDAO = new UsuarioModuloDAO(conn);
    }

    public void executarTestes() {
        testeInscreverUsuarioEmModulo();
        testeBuscarModulosPorUsuario();
//        testeRemoverUsuarioDeModulo();
    }

    private void testeInscreverUsuarioEmModulo() {
        try {
            int idUsuario = 1; // ID do usuário que será inscrito no módulo
            int idModulo = 1;  // ID do módulo no qual o usuário será inscrito

            usuarioModuloDAO.inscreverUsuarioEmModulo(idUsuario, idModulo);
            System.out.println("Usuário inscrito no módulo com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inscrever usuário no módulo: " + e.getMessage());
        }
    }

    private void testeBuscarModulosPorUsuario() {
        try {
            int idUsuario = 1; // ID do usuário cujos módulos serão buscados

            List<Integer> modulos = usuarioModuloDAO.buscarModulosPorUsuario(idUsuario);
            if (modulos.isEmpty()) {
                System.out.println("Nenhum módulo encontrado para o usuário.");
            } else {
                for (int idModulo : modulos) {
                    System.out.println("ID do Módulo: " + idModulo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar módulos do usuário: " + e.getMessage());
        }
    }

    private void testeRemoverUsuarioDeModulo() {
        try {
            int idUsuario = 1; // ID do usuário que será removido do módulo
            int idModulo = 1;  // ID do módulo do qual o usuário será removido

            usuarioModuloDAO.removerUsuarioDeModulo(idUsuario, idModulo);
            System.out.println("Usuário removido do módulo com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário do módulo: " + e.getMessage());
        }
    }
}
