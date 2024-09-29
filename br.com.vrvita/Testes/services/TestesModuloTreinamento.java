package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProperties;
import model.ModuloTreinamento;
import service.ModuloTreinamentoService;

public class TestesModuloTreinamento {

    private ModuloTreinamentoService moduloService;

    // Construtor que recebe a conexão como parâmetro
    public TestesModuloTreinamento(Connection conn) {
        this.moduloService = new ModuloTreinamentoService(conn);
    }

    public void executarTestes() {
        testeInserirModulo();
        testeBuscarModuloPorId();
        testeAtualizarModulo();
        testeListarModulos();
//        testeDeletarModulo();
    }

    private void testeInserirModulo() {
        ModuloTreinamento novoModulo = new ModuloTreinamento();
        novoModulo.setNomeModulo("Introdução à Laparoscopia");
        novoModulo.setDescricao("Módulo básico para introdução ao uso de laparoscópios.");

        try {
            moduloService.cadastrarModulo(novoModulo);
            System.out.println("Módulo inserido com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir módulo: " + e.getMessage());
        }
    }

    private void testeBuscarModuloPorId() {
        try {
            ModuloTreinamento modulo = moduloService.buscarModuloPorId(1);
            if (modulo != null) {
                System.out.println("Módulo encontrado: " + modulo.getNomeModulo());
            } else {
                System.out.println("Módulo não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar módulo: " + e.getMessage());
        }
    }

    private void testeAtualizarModulo() {
        try {
            ModuloTreinamento modulo = moduloService.buscarModuloPorId(1);
            if (modulo != null) {
                modulo.setNomeModulo("Laparoscopia Avançada");
                modulo.setDescricao("Módulo avançado para técnicas avançadas de laparoscopia.");
                moduloService.atualizarModulo(modulo);
                System.out.println("Módulo atualizado com sucesso.");
            } else {
                System.out.println("Módulo não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar módulo: " + e.getMessage());
        }
    }

    private void testeDeletarModulo() {
        try {
            moduloService.deletarModulo(1);
            System.out.println("Módulo deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar módulo: " + e.getMessage());
        }
    }

    private void testeListarModulos() {
        try {
            List<ModuloTreinamento> modulos = moduloService.listarModulos();
            for (ModuloTreinamento modulo : modulos) {
                System.out.println("Módulo: " + modulo.getNomeModulo());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar módulos: " + e.getMessage());
        }
    }
}
