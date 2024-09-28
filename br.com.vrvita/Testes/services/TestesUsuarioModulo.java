package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionProperties;
import dao.UsuarioModuloDAO;

public class TestesUsuarioModulo {

    public static void testeInscreverUsuarioEmModulo(UsuarioModuloDAO usuarioModuloDAO) {
        try {
            int idUsuario = 1; // ID do usuário que será inscrito no módulo
            int idModulo = 1;  // ID do módulo no qual o usuário será inscrito

            usuarioModuloDAO.inscreverUsuarioEmModulo(idUsuario, idModulo);
            System.out.println("Usuário inscrito no módulo com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeBuscarModulosPorUsuario(UsuarioModuloDAO usuarioModuloDAO) {
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
            e.printStackTrace();
        }
    }

    public static void testeRemoverUsuarioDeModulo(UsuarioModuloDAO usuarioModuloDAO) {
        try {
            int idUsuario = 1; // ID do usuário que será removido do módulo
            int idModulo = 1;  // ID do módulo do qual o usuário será removido

            usuarioModuloDAO.removerUsuarioDeModulo(idUsuario, idModulo);
            System.out.println("Usuário removido do módulo com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void testes(Connection conn ) {
        try {
            Statement stmt = null;
            PreparedStatement preparedStatement = null;

            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            conn = DriverManager.getConnection(url, ConnectionProperties.getConnection());

            stmt = conn.createStatement();

            UsuarioModuloDAO usuarioModuloDAO = new UsuarioModuloDAO(preparedStatement, conn);

            testeInscreverUsuarioEmModulo(usuarioModuloDAO);
            testeBuscarModulosPorUsuario(usuarioModuloDAO);
            testeRemoverUsuarioDeModulo(usuarioModuloDAO);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

