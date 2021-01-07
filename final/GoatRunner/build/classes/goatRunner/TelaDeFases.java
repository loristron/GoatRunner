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
import org.newdawn.slick.Color;
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
 * @author Lorena
 */
public class TelaDeFases extends BasicGameState {

    public Image fundoFase1Liberada = null;
    public Image fundoFase2Liberada = null;
    public Image fundoFase3Liberada = null;
    public Image fundoFase4Liberada = null;
    public Image fundoFase5Liberada = null;

    public Image estrela = null;

    public int xMouse;
    public int yMouse;

    Persiste persiste = new Persiste();

    public TelaDeFases(int id) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        fundoFase1Liberada = new Image("recursos/backgrounds/backgroundTelaDeFases1Liberadas_1.png");
        fundoFase2Liberada = new Image("recursos/backgrounds/backgroundTelaDeFases2Liberadas_1.png");
        fundoFase3Liberada = new Image("recursos/backgrounds/backgroundTelaDeFases3Liberadas_1.png");
        fundoFase4Liberada = new Image("recursos/backgrounds/backgroundTelaDeFases4Liberadas_1.png");
        fundoFase5Liberada = new Image("recursos/backgrounds/backgroundTelaDeFases_1.png");
        estrela = new Image("recursos/characters/star.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        if ((Usuarios.getUsuarioAtual().getFase1().equals("sim")) && (Usuarios.getUsuarioAtual().getFase2().equals("nao"))) {
            g.drawImage(fundoFase1Liberada, 0, 0);
        }
        if ((Usuarios.getUsuarioAtual().getFase2().equals("sim")) && (Usuarios.getUsuarioAtual().getFase3().equals("nao"))) {
            g.drawImage(fundoFase2Liberada, 0, 0);
        }
        if ((Usuarios.getUsuarioAtual().getFase3().equals("sim")) && (Usuarios.getUsuarioAtual().getFase4().equals("nao"))) {
            g.drawImage(fundoFase3Liberada, 0, 0);
        }
        if ((Usuarios.getUsuarioAtual().getFase4().equals("sim")) && (Usuarios.getUsuarioAtual().getFase5().equals("nao"))) {
            g.drawImage(fundoFase4Liberada, 0, 0);
        }
        if ((Usuarios.getUsuarioAtual().getFase5().equals("sim"))) {
            g.drawImage(fundoFase5Liberada, 0, 0);
        }
        g.drawString("X: " + xMouse + " Y: " + yMouse, 50, 50);
        g.setColor(Color.black);
        g.drawImage(estrela, 750, 0);
        g.drawString(Usuarios.getUsuarioAtual().getEstrelas(), 730, 20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        xMouse = Mouse.getX();
        yMouse = Mouse.getY();
        Input input = gc.getInput();

        //preço das fases: FASE 1: 0 ESTRELAS
        //FASE 2: 3 ESTRELAS
        //FASE 3: 6 ESTRELAS
        //FASE 4; 9 ESTRELAS
        //FASE 5: 12 ESTRELAS
// Se clicou pra trocar personagem 245 554
        if (((xMouse > 245) && (xMouse < 555)) && ((yMouse > 101) && (yMouse < 142))) {
            if (input.isMousePressed(0)) {
                System.out.println("Clicou pra ir na tela de personagens");
                sbg.enterState(GoatRunner.TELA_DE_PERSONAGENS, new FadeOutTransition(), new FadeInTransition());
            }
        }

        //SE CLICOU EM CIMA DA FASE 1 
        if (((xMouse > 55) && (xMouse < 141)) && ((yMouse > 177) && (yMouse < 266))) {
            if (input.isMousePressed(0)) {
                if (Usuarios.getUsuarioAtual().getFase1().equals("sim")) {
                    sbg.enterState(GoatRunner.FASE_1, new FadeOutTransition(), new FadeInTransition());
                }
            }
        }
        // SE CLICOU EM CIMA DA FASE 2
        if (((xMouse > 202) && (xMouse < 289)) && ((yMouse > 177) && (yMouse < 266))) {
            if (input.isMousePressed(0)) {
                if (Usuarios.getUsuarioAtual().getFase2().equals("sim")) {
                    sbg.enterState(GoatRunner.FASE_2, new FadeOutTransition(), new FadeInTransition());
                } else {
                    if (Usuarios.getUsuarioAtual().getFase1().equals("sim")) {
                        int result = Entradas.dialogoSimNao("Nao possui esta fase", "Voce não possui esta fase, deseja comprá-la por 3 estrelas?");
                        System.out.println("Resultado JOPtion: " + result);
                        if (result == 0) {
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 3) {
                                try {
                                    persiste.atualizaFases(2);
                                    persiste.atualizaMoedas(-3);
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDeFases.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui estrelas suficientes.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desbloqueie a fase anterior primeiro.");
                    }
                }

            }
        }

        // SE CLICOU EM CIMA DA FASE 3
        if (((xMouse > 350) && (xMouse < 434)) && ((yMouse > 177) && (yMouse < 266))) {
            if (input.isMousePressed(0)) {
                if (Usuarios.getUsuarioAtual().getFase3().equals("sim")) {
                    sbg.enterState(GoatRunner.FASE_3, new FadeOutTransition(), new FadeInTransition());
                } else {
                    if (Usuarios.getUsuarioAtual().getFase2().equals("sim")) {
                        int result = Entradas.dialogoSimNao("Nao possui esta fase", "Voce não possui esta fase, deseja comprá-la por 6 estrelas?");
                        System.out.println("Resultado JOPtion: " + result);
                        if (result == 0) {
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 6) {
                                try {
                                    persiste.atualizaFases(3);
                                    persiste.atualizaMoedas(-6);
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDeFases.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui estrelas suficientes.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desbloqueie a fase anterior primeiro.");
                    }
                }

            }
        }

        // SE CLICOU EM CIMA DA FASE 4
        if (((xMouse > 490) && (xMouse < 573)) && ((yMouse > 177) && (yMouse < 266))) {
            if (input.isMousePressed(0)) {
                if (Usuarios.getUsuarioAtual().getFase4().equals("sim")) {
                    sbg.enterState(GoatRunner.FASE_4, new FadeOutTransition(), new FadeInTransition());
                  } else {
                    if (Usuarios.getUsuarioAtual().getFase3().equals("sim")) {
                        int result = Entradas.dialogoSimNao("Nao possui esta fase", "Voce não possui esta fase, deseja comprá-la por 9 estrelas?");
                        System.out.println("Resultado JOPtion: " + result);
                        if (result == 0) {
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 9) {
                                try {
                                    persiste.atualizaFases(4);
                                    persiste.atualizaMoedas(-9);
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDeFases.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui estrelas suficientes.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desbloqueie a fase anterior primeiro.");
                    }
                }

            }
        }

        // SE CLICOU EM CIMA DA FASE 5
        if (((xMouse > 631) && (xMouse < 717)) && ((yMouse > 177) && (yMouse < 266))) {
            if (input.isMousePressed(0)) {
                if (Usuarios.getUsuarioAtual().getFase5().equals("sim")) {
                    sbg.enterState(GoatRunner.FASE_5, new FadeOutTransition(), new FadeInTransition());
                 } else {
                    if (Usuarios.getUsuarioAtual().getFase4().equals("sim")) {
                        int result = Entradas.dialogoSimNao("Nao possui esta fase", "Voce não possui esta fase, deseja comprá-la por 12 estrelas?");
                        System.out.println("Resultado JOPtion: " + result);
                        if (result == 0) {
                            if (Integer.parseInt(Usuarios.getUsuarioAtual().getEstrelas()) >= 12) {
                                try {
                                    persiste.atualizaFases(5);
                                    persiste.atualizaMoedas(-12);
                                    persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaDeFases.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Você não possui estrelas suficientes.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Desbloqueie a fase anterior primeiro.");
                    }
                }

            }
        }

    }

    /**
     *
     * @return a Id do estado
     */
    @Override
    public int getID() {
        return 1;
    }
}
