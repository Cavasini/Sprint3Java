//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
            this.connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", ConnectionProperties.getConnection());
            this.preparedStatement = null;
            this.exercicioService = new ExercicioService(this.preparedStatement, this.connection);
            this.moduloTreinamentoService = new ModuloTreinamentoService(this.preparedStatement, this.connection);
            this.sessaoService = new SessaoService(this.preparedStatement, this.connection);
            this.usuarioModuloService = new UsuarioModuloService(this.preparedStatement, this.connection);
            this.usuarioService = new UsuarioService(this.preparedStatement, this.connection);
            
        } catch (Exception var2) {
            Exception e = var2;
            e.printStackTrace();
            throw new SQLException("Driver JDBC não encontrado.");
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

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException var2) {
            SQLException e = var2;
            e.printStackTrace();
        }

    }
}
