package manager;

import connection.ConnectionProperties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import service.ExercicioService;
import service.ModuloTreinamentoService;
import service.SessaoService;
import service.UsuarioModuloService;
import service.UsuarioService;

public class ServiceManager {
    private static ServiceManager instance;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ExercicioService exercicioService;
    private ModuloTreinamentoService moduloTreinamentoService;
    private SessaoService sessaoService;
    private UsuarioModuloService usuarioModuloService;
    private UsuarioService usuarioService;
    private final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";

    private ServiceManager() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, ConnectionProperties.getConnection());
            this.preparedStatement = null;
            this.exercicioService = new ExercicioService(this.connection);
            this.moduloTreinamentoService = new ModuloTreinamentoService(this.connection);
            this.sessaoService = new SessaoService(this.connection);
            this.usuarioModuloService = new UsuarioModuloService(this.connection);
            this.usuarioService = new UsuarioService(this.connection);
        } catch (SQLException e) {
            throw new SQLException("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new SQLException("Erro inesperado ao inicializar o ServiceManager: " + e.getMessage(), e);
        }
    }

    public static ServiceManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new ServiceManager();
        } else if (instance.connection.isClosed()) {
            instance = new ServiceManager();
        }
        return instance;
    }

    public ExercicioService getExercicioService() {
        return this.exercicioService;
    }

    public ModuloTreinamentoService getModuloTreinamentoService() {
        return this.moduloTreinamentoService;
    }

    public SessaoService getSessaoService() {
        return this.sessaoService;
    }

    public UsuarioModuloService getUsuarioModuloService() {
        return this.usuarioModuloService;
    }

    public UsuarioService getUsuarioService() {
        return this.usuarioService;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() throws SQLException {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao fechar a conexão com o banco de dados: " + e.getMessage(), e);
        }
    }
}
