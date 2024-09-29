package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Usuario;
import service.UsuarioService;

public class TestesUsuario {

    private UsuarioService usuarioService;

    // Construtor que recebe a conexão como parâmetro
    public TestesUsuario(Connection conn) {
        this.usuarioService = new UsuarioService(conn);
    }

    public void executarTestes() {
        testeCadastrarUsuario();
        testeBuscarUsuarioPorId();
        testeListarUsuarios();
        testeAtualizarUsuario();
//        testeDeletarUsuario();
        testeUsuarioExiste();
    }

    private void testeCadastrarUsuario() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("João Silva");
        novoUsuario.setEmail("joao@gmail.com");
        novoUsuario.setSenha("senha123");
        novoUsuario.setTipoUsuario("E");
        novoUsuario.setEspecialidade("medicina");
        novoUsuario.setInstituicao("UFRJ");
        novoUsuario.setNivelExperiencia(1);
        novoUsuario.setDataNascimento(Date.valueOf("1990-01-01"));
        novoUsuario.setDataCadastro(new Date(System.currentTimeMillis()));

        try {
            usuarioService.cadastrarUsuario(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    private void testeBuscarUsuarioPorId() {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(1);
            if (usuario != null) {
                System.out.println("Usuário encontrado: " + usuario.getNome());
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    private void testeListarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarUsuarios();
            for (Usuario usuario : usuarios) {
                System.out.println("Usuário: " + usuario.getNome());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
    }

    private void testeAtualizarUsuario() {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(1); // Assumindo que o usuário com ID 1 já existe
            if (usuario != null) {
                usuario.setEmail("novoemail@gmail.com");
                usuarioService.atualizarUsuario(usuario);
                System.out.println("Usuário atualizado com sucesso.");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    private void testeDeletarUsuario() {
        try {
            usuarioService.deletarUsuario(1); // Assumindo que o usuário com ID 1 existe
            System.out.println("Usuário deletado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    private void testeUsuarioExiste() {
        try {
            boolean existe = usuarioService.usuarioExiste(1); // Verifica se o usuário com ID 1 existe
            System.out.println("Usuário com ID 1 existe? " + existe);

            boolean naoExiste = usuarioService.usuarioExiste(99); // Verifica se um ID inexistente existe
            System.out.println("Usuário com ID 99 existe? " + naoExiste);
        } catch (SQLException e) {
            System.err.println("Erro ao verificar existência de usuário: " + e.getMessage());
        }
    }
}
