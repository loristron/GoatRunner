/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author User
 */
public class Usuarios {

    private int id;
    private String login;
    private String senha;
    private String estrelas;
    private String fase1;

    private String fase2;
    private String fase3;
    private String fase4;
    private String fase5;

    private String cabra1;
    private String cabra2;
    private String cabra3;
    private String cabra4;

    private String cabraAtual;

    public String getCabra1() {
        return cabra1;
    }

    public void setCabra1(String cabra1) {
        this.cabra1 = cabra1;
    }

    public String getCabra2() {
        return cabra2;
    }

    public void setCabra2(String cabra2) {
        this.cabra2 = cabra2;
    }

    public String getCabra3() {
        return cabra3;
    }

    public void setCabra3(String cabra3) {
        this.cabra3 = cabra3;
    }

    public String getCabra4() {
        return cabra4;
    }

    public void setCabra4(String cabra4) {
        this.cabra4 = cabra4;
    }

    public String getCabraAtual() {
        return cabraAtual;
    }

    public void setCabraAtual(String cabraAtual) {
        this.cabraAtual = cabraAtual;
    }

    private static Usuarios usuarioAtual;

    private boolean isLogado = false;

    public boolean isIsLogado() {
        return isLogado;
    }

    public void setIsLogado(boolean isLogado) {
        this.isLogado = isLogado;
    }

    public static Usuarios getUsuarioAtual() {
        return usuarioAtual;
    }

    public static void setUsuarioAtual(Usuarios usuarioAtual) {
        Usuarios.usuarioAtual = usuarioAtual;
    }

    public Usuarios(String login, String senha, String estrelas, String fase1, String fase2, String fase3, String fase4, String fase5, String cabra1, String cabra2, String cabra3, String cabra4, String cabraAtual) {
        this.login = login;
        this.senha = senha;
        this.estrelas = estrelas;
        this.fase1 = fase1;
        this.fase2 = fase2;
        this.fase3 = fase3;
        this.fase4 = fase4;
        this.fase5 = fase5;
        this.cabra1 = cabra1;
        this.cabra2 = cabra2;
        this.cabra3 = cabra3;
        this.cabra4 = cabra4;
        this.cabraAtual = cabraAtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(String estrelas) {
        this.estrelas = estrelas;
    }

    public String getFase1() {
        return fase1;
    }

    public void setFase1(String fase1) {
        this.fase1 = fase1;
    }

    public String getFase2() {
        return fase2;
    }

    public void setFase2(String fase2) {
        this.fase2 = fase2;
    }

    public String getFase3() {
        return fase3;
    }

    public void setFase3(String fase3) {
        this.fase3 = fase3;
    }

    public String getFase4() {
        return fase4;
    }

    public void setFase4(String fase4) {
        this.fase4 = fase4;
    }

    public String getFase5() {
        return fase5;
    }

    public void setFase5(String fase5) {
        this.fase5 = fase5;
    }
}
