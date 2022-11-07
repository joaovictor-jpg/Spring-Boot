package io.com.github.joaovictorjpg.domen.repository;

import io.com.github.joaovictorjpg.domen.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static String INSERT = "INSERT INTO CLIENTE(nome) VALUES(?)";
    public static String SELECT_ALL = "SELECT * FROM CLIENTE";
    public static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    public static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";
    public static String SELECT_NOME = "SELECT * FROM CLIENTE WHERE NOME LIKE ?";

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterClientes() {
        return  jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        });
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Cliente cliente) {
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
    }

    public List<Cliente> obterPorNome(String nome) {
        return jdbcTemplate.query(SELECT_NOME, new Object[]{"%" + nome + "%"}, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
             Integer id = rs.getInt("id");
             String nome = rs.getString("nome");
             return new Cliente(id, nome);
            }
        });
    }
}