package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ModuloTreinamento;

public class ModuloTreinamentoDAO {
	
	private PreparedStatement stmt;
	private Connection conn;
	
	public ModuloTreinamentoDAO(PreparedStatement stmt, Connection conn) {
		this.stmt = stmt;
		this.conn = conn;
	}
	
    // Inserir novo módulo
    public void inserirModulo(ModuloTreinamento modulo) throws SQLException {
        String sql = "INSERT INTO Modulos_Treinamento (id_modulo, nome_modulo, descricao) " +
                     "VALUES (?, ?, ?)";
        try  {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, getNextId(conn));
            stmt.setString(2, modulo.getNomeModulo());
            stmt.setString(3, modulo.getDescricao());

            stmt.executeUpdate();
        }catch(SQLException e) {
        	
        }
    }

    // Buscar módulo pelo ID
    public ModuloTreinamento buscarModuloPorId(int idModulo) throws SQLException {
        String sql = "SELECT * FROM Modulos_Treinamento WHERE id_modulo = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idModulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ModuloTreinamento modulo = new ModuloTreinamento();
                    modulo.setIdModulo(rs.getInt("id_modulo"));
                    modulo.setNomeModulo(rs.getString("nome_modulo"));
                    modulo.setDescricao(rs.getString("descricao"));
                    return modulo;
                }
            }
        }catch(SQLException e) {
        	
        }
        return null;
    }

    // Atualizar módulo
    public void atualizarModulo(ModuloTreinamento modulo) throws SQLException {
        String sql = "UPDATE Modulos_Treinamento SET nome_modulo = ?, descricao = ? WHERE id_modulo = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setString(1, modulo.getNomeModulo());
            stmt.setString(2, modulo.getDescricao());
            stmt.setInt(3, modulo.getIdModulo());

            stmt.executeUpdate();
        }catch(SQLException e) {
        	
        }
    }

    // Deletar módulo pelo ID
    public void deletarModulo(int idModulo) throws SQLException {
        String sql = "DELETE FROM Modulos_Treinamento WHERE id_modulo = ?";
        try {
        	stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idModulo);
            stmt.executeUpdate();
        }catch(SQLException e) {
        	
        }
    }

    // Listar todos os módulos
    public List<ModuloTreinamento> listarModulos() throws SQLException {
        String sql = "SELECT * FROM Modulos_Treinamento";
        List<ModuloTreinamento> modulos = new ArrayList<>();
        try  {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModuloTreinamento modulo = new ModuloTreinamento();
                modulo.setIdModulo(rs.getInt("id_modulo"));
                modulo.setNomeModulo(rs.getString("nome_modulo"));
                modulo.setDescricao(rs.getString("descricao"));
                modulos.add(modulo);
            }
        }catch(SQLException e) {
        	
        }
        return modulos;
    }
    
    public int getNextId(Connection connection) {
        int nextId = 0;
        String sql = "SELECT MAX(id_modulo) AS maior_id FROM Modulos_Treinamento";  // Consulta para contar os nomes

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int maiorId = rs.getInt("maior_id");
                    // Verifica se o resultado foi NULL
                    if (!rs.wasNull()) {
                        nextId = maiorId + 1;  // Incrementa o maior ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nextId;  // Retorna o próximo ID
    }

}

