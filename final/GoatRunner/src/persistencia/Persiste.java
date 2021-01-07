/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Persiste {

    private boolean isValid = false;
    private boolean naoExiste = false;
    private Connection connection;

    public Persiste() {
        this.connection = ConnectionFactory.getConnection();
    }

    public boolean verificaLogin(String login, String senha) throws SQLException {
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("select * from usuarios where login=?");
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuarios user = new Usuarios(rs.getString("login"), rs.getString("senha"), rs.getString("estrelas"), rs.getString("fase1"), rs.getString("fase2"), rs.getString("fase3"), rs.getString("fase4"), rs.getString("fase5"), rs.getString("cabra1"), rs.getString("cabra2"), rs.getString("cabra3"), rs.getString("cabra4"), rs.getString("cabraAtual"));
            if (user.getLogin().equals(login) && (user.getSenha().equals(senha))) {
                isValid = true;
                Usuarios.setUsuarioAtual(user);
                JOptionPane.showConfirmDialog(null, "Olá, " + user.getLogin(), null, JOptionPane.OK_CANCEL_OPTION);
            } else {
                isValid = false;
                JOptionPane.showMessageDialog(null, "Acesso negado, confira seus dados ou cadastre-se.");
            }
        }
        rs.close();
        stmt.close();
        return isValid;
    }

    public void atualizaUsuario(Usuarios usuarioAtual) throws SQLException {
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("SELECT * FROM usuarios WHERE login=?");
        stmt.setString(1, usuarioAtual.getLogin());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Usuarios atualizado = new Usuarios(rs.getString("login"), rs.getString("senha"), rs.getString("estrelas"), rs.getString("fase1"), rs.getString("fase2"), rs.getString("fase3"), rs.getString("fase4"), rs.getString("fase5"), rs.getString("cabra1"), rs.getString("cabra2"), rs.getString("cabra3"), rs.getString("cabra4"), rs.getString("cabraAtual"));
            Usuarios.setUsuarioAtual(atualizado);
        }
        System.out.println("Atualizei o usuario com as novas informacoes do banco");
        rs.close();
        stmt.close();

    }

    //retorna true se o login NÃO foi usado
    //retorna flase se o login já foi usado
    public boolean verificaLoginNaoUsado(String login) throws SQLException {
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("select * from usuarios where login=?");
        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Usuarios> usuarios = new ArrayList<Usuarios>();
        while (rs.next()) {
            Usuarios user = new Usuarios(rs.getString("login"), rs.getString("senha"), rs.getString("estrelas"), rs.getString("fase1"), rs.getString("fase2"), rs.getString("fase3"), rs.getString("fase4"), rs.getString("fase5"), rs.getString("cabra1"), rs.getString("cabra2"), rs.getString("cabra3"), rs.getString("cabra4"), rs.getString("cabraAtual"));
            usuarios.add(user);
        }

        if (usuarios.size() > 0) {
            naoExiste = false;
            JOptionPane.showConfirmDialog(null, "O Login já está sendo usado.", null, JOptionPane.OK_CANCEL_OPTION);
        } else {
            naoExiste = true;
            JOptionPane.showMessageDialog(null, "Cadastro aprovado!");
        }
        rs.close();
        stmt.close();
        return naoExiste;
    }

    public void cadastra(String login, String senha) throws SQLException {
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("insert into usuarios (login, senha, estrelas, fase1, fase2, fase3, fase4, fase5, cabra1, cabra2, cabra3, cabra4, cabraAtual) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        stmt.setString(1, login);
        stmt.setString(2, senha);
        stmt.setString(3, "0");
        stmt.setString(4, "sim");
        stmt.setString(5, "nao");
        stmt.setString(6, "nao");
        stmt.setString(7, "nao");
        stmt.setString(8, "nao");
        stmt.setString(9, "sim");
        stmt.setString(10, "nao");
        stmt.setString(11, "nao");
        stmt.setString(12, "nao");
        stmt.setString(13, "cabra1");
        stmt.execute();
    }

    public void atualizaFases(int faseASerLiberada) throws SQLException {
        switch (faseASerLiberada) {
            case 2:
                PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("update usuarios set fase2='sim' where login=?");
                stmt.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt.execute();
                break;

            case 3:
                PreparedStatement stmt1 = ConnectionFactory.getConnection().prepareStatement("update usuarios set fase3='sim' where login=?");
                stmt1.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt1.execute();
                break;

            case 4:
                PreparedStatement stmt2 = ConnectionFactory.getConnection().prepareStatement("update usuarios set fase4='sim' where login=?");
                stmt2.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt2.execute();
                break;

            case 5:
                PreparedStatement stmt3 = ConnectionFactory.getConnection().prepareStatement("update usuarios set fase5='sim' where login=?");
                stmt3.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt3.execute();
        }
    }

    public void atualizaMoedas(int estrelas) throws SQLException {
        int estrelasAntigas = Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas());
        estrelas = estrelas + estrelasAntigas;
        String estrelasBanco = Integer.toString(estrelas);
        System.out.println("Voce tem " + estrelasBanco + "estrelas.");
        PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("update usuarios set estrelas=? where login=?");
        stmt.setString(1, estrelasBanco);
        stmt.setString(2, Usuarios.getUsuarioAtual().getLogin());
        stmt.execute();

    }

    public void liberaPersonagem(int personagemASerLiberado) throws SQLException {
        switch (personagemASerLiberado) {
            case 2:
                System.out.println("alterando para cabra2");
                PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabra2='sim' where login=?");
                stmt.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt.execute();
                System.out.println("liberei a cabra dois");
                break;
            case 3:
                PreparedStatement stmt1 = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabra3='sim' where login=?");
                stmt1.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt1.execute();
                break;
            case 4:
                PreparedStatement stmt2 = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabra4='sim' where login=?");
                stmt2.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt2.execute();
                break;
        }
    }

    public void atualizaCabraAtual(int cabraSelecionada) throws SQLException {
        switch (cabraSelecionada) {
            case 1:
                PreparedStatement stmt = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabraAtual='cabra1' where login=?");
                stmt.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt.execute();
                break;
            case 2:
                PreparedStatement stmt1 = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabraAtual='cabra2' where login=?");
                stmt1.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt1.execute();
                break;
            case 3:
                PreparedStatement stmt2 = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabraAtual='cabra3' where login=?");
                stmt2.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt2.execute();
                break;
            case 4:
                PreparedStatement stmt3 = ConnectionFactory.getConnection().prepareStatement("update usuarios set cabraAtual='cabra4' where login=?");
                stmt3.setString(1, Usuarios.getUsuarioAtual().getLogin());
                stmt3.execute();
                break;

        }
    }
}
