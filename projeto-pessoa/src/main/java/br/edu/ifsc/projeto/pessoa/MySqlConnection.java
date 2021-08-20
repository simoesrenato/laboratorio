/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.projeto.pessoa;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renato
 */
public class MySqlConnection {
    /**
     * Uma outra forma de se conectar em bancos é acessando o DataSource de cada
     * distribuição, veja este exemplo
     */

    private static MysqlDataSource ds = new MysqlDataSource();

    static {
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("root");
        ds.setDatabaseName("aula_poo");
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void main(String[] args) {
        try {
            getConnection();
            System.out.println("ok");
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
