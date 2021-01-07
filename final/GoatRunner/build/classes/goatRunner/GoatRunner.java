 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatRunner;

import java.sql.SQLException;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class GoatRunner extends StateBasedGame {

    public static final String NOME_DO_JOGO = "GOAT RUNNER";
    //Como é um jogo de fases, ele trabalha com ids pra cada fasel, para saber qual ele deve tratar
    public static final int TELA_DE_INICIO = 0;
    public static final int TELA_DE_FASES = 1;
    public static final int FASE_1 = 2;
    public static final int FASE_2 = 3;
    public static final int FASE_3 = 4;
    public static final int FASE_4 = 5;
    public static final int FASE_5 = 6;
    public static final int TELA_DE_INSTRUCOES = 7;
    public static final int TELA_DE_PERSONAGENS = 8;
    public static final int ALTURA_JANELA = 500;
    public static final int LARGURA_JANELA = 791;
    public static final boolean FULLSCREEN = false;

    // Cada fase deve ser adicionada individualmente
    public GoatRunner() {
        super(NOME_DO_JOGO);
        this.addState(new TelaDeInicio(TELA_DE_INICIO));
        this.addState(new TelaDeFases(TELA_DE_FASES));
        this.addState(new TelaInstrucoes(TELA_DE_INSTRUCOES));
        this.addState(new TelaDePersonagens(TELA_DE_PERSONAGENS));
        this.addState(new Fase1(FASE_1));
        this.addState(new Fase2(FASE_2));
        this.addState(new Fase3(FASE_3));
        this.addState(new Fase4(FASE_4));
        this.addState(new Fase5(FASE_5));

    }

    //Toda vez qye você inicia a tela, ele puxa esse método
    //que telas que vamos trabalhar
    public void initStatesList(GameContainer gc) throws SlickException {

        //Estamos contando ao java que nosso jogo terá X fases
        this.getState(TELA_DE_INICIO).init(gc, this);
        this.getState(TELA_DE_FASES).init(gc, this);
        this.getState(TELA_DE_INSTRUCOES).init(gc, this);
        this.getState(TELA_DE_PERSONAGENS).init(gc, this);
        this.getState(FASE_1).init(gc, this);
        this.getState(FASE_2).init(gc, this);
        this.getState(FASE_3).init(gc, this);
        this.getState(FASE_4).init(gc, this);
        this.getState(FASE_5).init(gc, this);
        //Agora, como ele vai saber qual dessas ele tem que iniciar quando o jogo começar?
        //Temos que contar pro computador atraves:
        this.enterState(TELA_DE_INICIO);
    }

    //CRIAREMOS A JANELA DO JOGO NA MAIN, todas as janelas precisam 
    //ser chamadas na main
    public static void main(String[] args) throws SlickException, SQLException {
        AppGameContainer janela = new AppGameContainer(new GoatRunner());
        janela.setDisplayMode(LARGURA_JANELA, ALTURA_JANELA, FULLSCREEN);
        janela.start();

    }
}
