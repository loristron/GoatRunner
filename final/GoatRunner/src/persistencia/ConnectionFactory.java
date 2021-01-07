/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String BANCO_DE_DAOS = "jdbc:mysql://150.164.102.160/daw-aluno5";
    private static final String NOME_DE_USUARIO = "daw-aluno5";
    private static final String SENHA = "daw05";
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(BANCO_DE_DAOS, NOME_DE_USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
