package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProperties;
import model.ModuloTreinamento;
import service.ModuloTreinamentoService;

public class WhenRequestModuloTreinamentoServiceFunctions {

    public static void testeInserirModulo(ModuloTreinamentoService moduloService) {
        ModuloTreinamento novoModulo = new ModuloTreinamento();
        novoModulo.setNomeModulo("Introdução à Laparoscopia");
        novoModulo.setDescricao("Módulo básico para introdução ao uso de laparoscópios.");

        try {
        	moduloService.cadastrarModulo(novoModulo);
            System.out.println("Módulo inserido com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeBuscarModuloPorId(ModuloTreinamentoService moduloService) {
        try {
            ModuloTreinamento modulo = moduloService.buscarModuloPorId(3);// Assumindo que o módulo com ID 1 já existe
            if (modulo != null) {
                System.out.println("Módulo encontrado: " + modulo.getNomeModulo());
            } else {
                System.out.println("Módulo não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeAtualizarModulo(ModuloTreinamentoService moduloService) {
        try {
            ModuloTreinamento modulo = moduloService.buscarModuloPorId(3); // Assumindo que o módulo com ID 1 já existe
            if (modulo != null) {
                modulo.setNomeModulo("Laparoscopia Avançada");
                modulo.setDescricao("Módulo avançado para técnicas avançadas de laparoscopia.");
                moduloService.atualizarModulo(modulo);
                System.out.println("Módulo atualizado com sucesso.");
            } else {
                System.out.println("Módulo não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeDeletarModulo(ModuloTreinamentoService moduloService) {
        try {
        	moduloService.deletarModulo(2);
            System.out.println("Módulo deletado com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testeListarModulos(ModuloTreinamentoService moduloService) {
        try {
            List<ModuloTreinamento> modulos = moduloService.listarModulos();
            for (ModuloTreinamento modulo : modulos) {
                System.out.println("Módulo: " + modulo.getNomeModulo());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = null;
            PreparedStatement preparedStatement = null;
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
            conn = DriverManager.getConnection(url, ConnectionProperties.getConnection());

            // Criando uma instância de ModuloTreinamentoDAO para passar nos testes
            ModuloTreinamentoService moduloService = new ModuloTreinamentoService(preparedStatement, conn);

            // Chamando os métodos de teste
            testeInserirModulo(moduloService);
            testeBuscarModuloPorId(moduloService);
            testeAtualizarModulo(moduloService);
            testeListarModulos(moduloService);
            testeDeletarModulo(moduloService);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}