package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.ConnectionProperties;
import model.Usuario;
import service.UsuarioService;

public class WhenRequestUsuarioServiceFunctions {
	

	public static void testeCadastrarUsuario(UsuarioService usuarioService) {
	    Usuario novoUsuario = new Usuario();
	    novoUsuario.setIdUsuario(2);
	    novoUsuario.setNome("João Silva");
	    novoUsuario.setEmail("joao@gmail.com");
	    novoUsuario.setSenha("senha123");
	    novoUsuario.setTipoUsuario("Estudante");
	    novoUsuario.setEspecialidade("medicina");
	    novoUsuario.setInstituicao("UFRJ");
	    novoUsuario.setNivelExperiencia("Iniciante");
	    novoUsuario.setDataNascimento(Date.valueOf("1990-01-01"));
	    novoUsuario.setDataCadastro(new Date(System.currentTimeMillis()));

	    try {
	        usuarioService.cadastrarUsuario(novoUsuario);
	        System.out.println("Usuário cadastrado com sucesso.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void testeBuscarUsuarioPorId(UsuarioService usuarioService) {
	    try {
	        Usuario usuario = usuarioService.buscarUsuarioPorId(1);
	        if (usuario != null) {
	            System.out.println("Usuário encontrado: " + usuario.getNome());
	        } else {
	            System.out.println("Usuário não encontrado.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void testeListarUsuarios(UsuarioService usuarioService) {
	    try {
	        List<Usuario> usuarios = usuarioService.listarUsuarios();
	        for (Usuario usuario : usuarios) {
	            System.out.println("Usuário: " + usuario.getNome());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void testeAtualizarUsuario(UsuarioService usuarioService) {
	    try {
	        Usuario usuario = usuarioService.buscarUsuarioPorId(2); // Assumindo que o usuário com ID 2 já existe
	        if (usuario != null) {
	            usuario.setEmail("novoemail@gmail.com");
	            usuarioService.atualizarUsuario(usuario);
	            System.out.println("Usuário atualizado com sucesso.");
	        } else {
	            System.out.println("Usuário não encontrado.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void testeDeletarUsuario(UsuarioService usuarioService) {
	    try {
	        usuarioService.deletarUsuario(3); // Assumindo que o usuário com ID 3 existe
	        System.out.println("Usuário deletado com sucesso.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void testeUsuarioExiste(UsuarioService usuarioService) {
	    try {
	        boolean existe = usuarioService.usuarioExiste(1); // Verifica se o usuário com ID 4 existe
	        System.out.println("Usuário com ID 1 existe? " + existe);

	        boolean naoExiste = usuarioService.usuarioExiste(99); // Verifica se um ID inexistente existe
	        System.out.println("Usuário com ID 99 existe? " + naoExiste);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void main(String[] args) {
		try {
			Connection conn = null;
			Statement stmt = null;
			 PreparedStatement preparedStatement = null;
			
			String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
			conn = DriverManager.getConnection(url, ConnectionProperties.getConnection());
			
			stmt = conn.createStatement();
			
//			testeCadastrarUsuario(new UsuarioService(preparedStatement, conn));
//			testeBuscarUsuarioPorId(new UsuarioService(preparedStatement, conn));
//			testeListarUsuarios(new UsuarioService(preparedStatement, conn));
//			testeAtualizarUsuario(new UsuarioService(preparedStatement, conn));
//			testeDeletarUsuario(new UsuarioService(preparedStatement, conn));
			testeUsuarioExiste(new UsuarioService(preparedStatement, conn));
		}catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
}
