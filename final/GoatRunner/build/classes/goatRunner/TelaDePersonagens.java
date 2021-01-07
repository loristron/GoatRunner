/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatRunner;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import persistencia.Persiste;
import persistencia.Usuarios;
import uteis.Entradas;

/**
 *
 * @author Lorena Miranda
 */
public class TelaDePersonagens extends BasicGameState {

    public Image cabra1Liberada = null;
    public Image cabra2Liberada = null;
    public Image cabra3Liberada = null;
    public Image cabra4Liberada = null;

    public static final String SIM = "sim";
    public static final String NAO = "nao";

    public boolean bcabra1Liberada = true;
    public boolean bcabra2Liberada = false;
    public boolean bcabra3Liberada = false;
    public boolean bcabra4Liberada = false;

    public int xMouse;
    public int yMouse;

    public int result;

    Persiste persiste = new Persiste();

    public TelaDePersonagens(int id) {

    }

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cabra1Liberada = new Image("recursos/backgrounds/backroungEscolhaPersonagemCabra1Liberada.png");
        cabra2Liberada = new Image("recursos/backgrounds/backroungEscolhaPersonagemCabra2Liberada.png");
        cabra3Liberada = new Image("recursos/backgrounds/backroungEscolhaPersonagemCabra3Liberada.png");
        cabra4Liberada = new Image("recursos/backgrounds/backroungEscolhaPersonagemCabra4Liberada.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if ((Usuarios.getUsuarioAtual().getCabra1().equals(SIM)) && (Usuarios.getUsuarioAtual().getCabra2().equals(NAO))) {
            g.drawImage(cabra1Liberada, 0, 0);
        }
        if ((Usuarios.getUsuarioAtual().getCabra2().equals(SIM)) && (Usuarios.getUsuarioAtual().getCabra3().equals(NAO))) {
            g.drawImage(cabra2Liberada, 0, 0);
        }
        if ((Usuarios.getUsuarioAtual().getCabra3().equals(SIM)) && (Usuarios.getUsuarioAtual().getCabra4().equals(NAO))) {
            g.drawImage(cabra3Liberada, 0, 0);
        }
        if (Usuarios.getUsuarioAtual().getCabra4().equals(SIM)) {
            g.drawImage(cabra4Liberada, 0, 0);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Graphics g = gc.getGraphics();
        xMouse = Mouse.getX();
        yMouse = Mouse.getY();
        Input input = gc.getInput();


// Se clicou na cabra 1
        if (((xMouse > 70) && (xMouse < 180)) && ((yMouse > 185) && (yMouse < 330))) {
            if (input.isMousePressed(0)) {
                System.out.println("Tentou Selecionar Cabra 1");
                //Todos os usuarios, ao cadastrar, tem a cabra 1 liberada ;), enão não é preciso checar se o numero de estrelas
                // é suficiente.
                if (Usuarios.getUsuarioAtual().getCabra1().equals(SIM)) {
                    try {
                        System.out.println("Você tem a cabra 1!");
                        persiste.atualizaCabraAtual(1);
                        persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                        sbg.enterState(GoatRunner.TELA_DE_FASES, new FadeOutTransition(), new FadeInTransition());
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

//Se passou o mouse na cabra 2
        if (((xMouse > 242) && (xMouse < 350)) && ((yMouse > 185) && (yMouse < 330))) {
            if (input.isMousePressed(0)) { // se clicou na cabra 2
                System.out.println("Tentou Selecionar a Cabra 2");
                if (Usuarios.getUsuarioAtual().getCabra2().equals(SIM)) {
                    try {
                        // caso o usuario já tenha ela liberada
                        System.out.println("Cabra 2 selecionada.");
                        persiste.atualizaCabraAtual(2);
                        persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                        sbg.enterState(GoatRunner.TELA_DE_FASES, new FadeOutTransition(), new FadeInTransition());
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else { // caso ele nao possua a cabra já liberada
                    if (Usuarios.getUsuarioAtual().getCabra1().equals(SIM)) { //se ele tem a cabr anterior
                        result = Entradas.dialogoSimNao("Nao possui personagem", "Voce não possui esse personagem, deseja comprá-lo por 10 moedas?");
                        System.out.println("Resultado JOPtion: " + result);
                        // result = 0 => opção sim
                        if (result == 0) { // se ele quiser comprar a cabra 2
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 10) { // se ele tiver 10+ estrelas (valor necessário para comprar a segunda cabra
                                try {
                                    persiste.atualizaMoedas(-10); //tira 10 moedas do que ele tem
                                    persiste.liberaPersonagem(2); //libera o segundo persogagem
                                    persiste.atualizaCabraAtual(2); // Coloca no banco que a cabra 2 foi selecionada
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                    JOptionPane.showMessageDialog(null, "Personagem adquirido com sucesso. Pressione OK para continuar.");
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes.");
                            } // else n possui moedas
                        } // if se apertou pra comprar cabra 
                    } // verifica se ele tem a cabra anterior
                    else { // se ele não tem a cabra anterior 
                        JOptionPane.showMessageDialog(null, "Você tem que comprar a cabra anterior primeiro.");
                    }
                } // caso o usuario clicou em um personagem que ele não tem
            } // caso tenha apertado o botão da cabra 2
        } // casp temja passado o mouse na cabra dois

// SE CLICOU NA TERCEIRA CABRA, A CABRA AZUL
        if (((xMouse > 410) && (xMouse < 518)) && ((yMouse > 185) && (yMouse < 330))) {
            if (input.isMousePressed(0)) { // se clicou na cabra 2
                System.out.println("Tentou Selecionar a Cabra 3 - AZUL");
                if (Usuarios.getUsuarioAtual().getCabra3().equals(SIM)) {
                    try {
                        // caso o usuario já tenha ela liberada
                        System.out.println("Cabra 3 selecionada");
                        persiste.atualizaCabraAtual(3);
                        persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                        sbg.enterState(GoatRunner.TELA_DE_FASES, new FadeOutTransition(), new FadeInTransition());
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else { // caso ele nao possua a cabra já liberada
                    if (Usuarios.getUsuarioAtual().getCabra2().equals(SIM)) { //se ele tem a cabr anterior
                        result = Entradas.dialogoSimNao("Nao possui personagem", "Voce não possui esse personagem, deseja comprá-lo por 10 moedas?");
                        System.out.println("Resultado JOPtion: " + result);
                        // result = 0 => opção sim
                        if (result == 0) { // se ele quiser comprar a cabra 2
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 20) { // se ele tiver 20+ estrelas (valor necessário para comprar a terceira cabra
                                try {
                                    persiste.atualizaMoedas(-20); //tira 10 moedas do que ele tem
                                    persiste.liberaPersonagem(3); //libera o segundo persogagem
                                    persiste.atualizaCabraAtual(3); // Coloca no banco que a cabra 2 foi selecionada
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                    JOptionPane.showMessageDialog(null, "Personagem adquirido com sucesso. Pressione OK para continuar.");
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes.");
                            } // else n possui moedas
                        } // if se apertou pra comprar cabra 
                    } // verifica se ele tem a cabra anterior
                    else { // se ele não tem a cabra anterior 
                        JOptionPane.showMessageDialog(null, "Você tem que comprar a cabra anterior primeiro.");
                    }
                } // caso o usuario clicou em um personagem que ele não tem
            } // caso tenha apertado o botão da cabra 2
        } // sepassou o mouse em cima da terceira cabra 

// SE CLICOU NA QUARTA CABRA, A CABRA INVERSA
        if (((xMouse > 578) && (xMouse < 689)) && ((yMouse > 185) && (yMouse < 330))) {
            if (input.isMousePressed(0)) { // se clicou na cabra 2
                System.out.println("Tentou Selecionar a Cabra 4 - INVERSA");
                if (Usuarios.getUsuarioAtual().getCabra4().equals(SIM)) {
                    try {
                        // caso o usuario já tenha ela liberada
                        System.out.println("Cabra 4 selecionada");
                        persiste.atualizaCabraAtual(4);
                        persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                        sbg.enterState(GoatRunner.TELA_DE_FASES, new FadeOutTransition(), new FadeInTransition());
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else { // caso ele nao possua a cabra já liberada
                    if (Usuarios.getUsuarioAtual().getCabra3().equals(SIM)) { //se ele tem a cabrA anterior
                        result = Entradas.dialogoSimNao("Nao possui personagem", "Voce não possui esse personagem, deseja comprá-lo por 10 moedas?");
                        System.out.println("Resultado JOPtion: " + result);
                        // result = 0 => opção sim
                        if (result == 0) { // se ele quiser comprar a cabra 4
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 30) { // se ele tiver 30+ estrelas (valor necessário para comprar a quarta cabra
                                try {
                                    persiste.atualizaMoedas(-30); //tira 10 moedas do que ele tem
                                    persiste.liberaPersonagem(4); //libera o segundo persogagem
                                    persiste.atualizaCabraAtual(4); // Coloca no banco que a cabra 2 foi selecionada
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                    JOptionPane.showMessageDialog(null, "Personagem adquirido com sucesso. Pressione OK para continuar.");
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDePersonagens.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui moedas suficientes.");
                            } // else n possui moedas
                        } // if se apertou pra comprar cabra 
                    } // verifica se ele tem a cabra anterior
                    else { // se ele não tem a cabra anterior 
                        JOptionPane.showMessageDialog(null, "Você tem que comprar a cabra anterior primeiro.");
                    }
                } // caso o usuario clicou em um personagem que ele não tem
            } // caso tenha apertado o botão da cabra 2
        } // sepassou o mouse em cima da terceira cabra 
    } // fecha o metodo update
} // fecha a classe Tela de Personagens 
