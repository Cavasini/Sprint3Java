package main;

import java.sql.SQLException;

import manager.ServiceManager;
import service.ExercicioService;
import service.ModuloTreinamentoService;
import service.SessaoService;
import services.TestesExercicio;
import services.TestesModuloTreinamento;
import services.TestesSessao;
import services.TestesUsuario;
import services.TestesUsuarioModulo;

public class Main {

    public static void main(String[] args) {
        try {
            ServiceManager serviceManager = ServiceManager.getInstance();
            ModuloTreinamentoService moduloService = serviceManager.getModuloTreinamentoService();
            ExercicioService exercicioService = serviceManager.getExercicioService();
            SessaoService sessaoService = serviceManager.getSessaoService();
            
            TestesUsuario testeUsuario = new TestesUsuario();
            TestesSessao testeSessao = new TestesSessao();
            TestesExercicio testesExercicio = new TestesExercicio();
            TestesUsuarioModulo testeUsuarioModulo = new TestesUsuarioModulo();
            TestesModuloTreinamento testesModuloTreinamento = new TestesModuloTreinamento();
            
            testeUsuario.testes(serviceManager.getConnection());
            testeSessao.testes(serviceManager.getConnection());
            testesExercicio.testes(serviceManager.getConnection());
            testeUsuarioModulo.testes(serviceManager.getConnection());
            testesModuloTreinamento.testes(serviceManager.getConnection());
            
        } catch (SQLException var10) {
            SQLException e = var10;
            e.printStackTrace();
        }

    }

}