/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatRunner;

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

/**
 *
 * @author User
 */
public class TelaInstrucoes extends BasicGameState {

    public Image fundo = null;

    public int xMouse;
    public int yMouse;

    public TelaInstrucoes(int id) {

    }

    @Override
    public int getID() {
        return 7;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        fundo = new Image("recursos/backgrounds/backgroundInstrucoes.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(fundo, 0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        xMouse = Mouse.getX();
        yMouse = Mouse.getY();

        if (((xMouse > 220) && (xMouse < 521)) && ((yMouse > 70) && (yMouse < 110))) {
            if (input.isMousePressed(0)) {
                System.out.println("clicou menu");
                sbg.enterState(GoatRunner.TELA_DE_INICIO, new FadeOutTransition(), new FadeInTransition());
            }
        }

    }

}
