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
            
            TestesUsuario testeUsuario = new TestesUsuario(serviceManager.getConnection());
            TestesSessao testeSessao = new TestesSessao(serviceManager.getConnection());
            TestesExercicio testesExercicio = new TestesExercicio(serviceManager.getConnection());
            TestesUsuarioModulo testeUsuarioModulo = new TestesUsuarioModulo(serviceManager.getConnection());
            TestesModuloTreinamento testesModuloTreinamento = new TestesModuloTreinamento(serviceManager.getConnection());
            
            testeUsuario.executarTestes();;
            testeSessao.executarTestes();
            testesExercicio.executarTestes();
            testeUsuarioModulo.executarTestes();
            testesModuloTreinamento.executarTestes();
            
        } catch (SQLException var10) {
            SQLException e = var10;
            e.printStackTrace();
        }

    }

}