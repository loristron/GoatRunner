/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goatRunner;

import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import static org.newdawn.slick.Input.KEY_ENTER;
import static org.newdawn.slick.Input.KEY_ESCAPE;
import static org.newdawn.slick.Input.KEY_UP;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import persistencia.Persiste;
import persistencia.Usuarios;

/**
 *
 * @author Lorena
 */
public class Fase4 extends BasicGameState {

    public Persiste persiste = new Persiste();
    //imagens
    public Image fundo = null;
    public Image goatParada = null;
    public Image estrela = null;
    public Image monster = null;

    public SpriteSheet smokeSheet = null;
    public Animation smoke = null;

    //cabras:
    //branca
    public SpriteSheet whiteGoatMovendoSheet = null;
    public Animation whiteGoatMovendo = null;
    //rosa
    public SpriteSheet pinkGoatMovendoSheet = null;
    public Animation pinkGoatMovendo = null;
    //azul
    public SpriteSheet blueGoatMovendoSheet = null;
    public Animation blueGoatMovendo = null;
    //inversa
    public SpriteSheet inverseGoatMovendoSheet = null;
    public Animation inverseGoatMovendo = null;
    //Music: Toca o tempo todo
    //Sound: toca em determinada situação
    public Music music;
    public Sound scream;
    //posições iniciais e definições da cabra
    public float goatX = 50;
    public float goatY = 370;
    public float alturaCabra = 80;
    public float larguraCabra = 79;
    //pular
    public boolean pulando = false;
    public boolean caindo = false;
    public long TEMPO_DE_PULO = 250;
    //fase completa
    private boolean completouAFase = false;
    //para mover o fundo
    private float xBackground = 0;
    private final float yBackground = 0;
    private static final float VELOCIDADE_DO_FUNDO = 0.5f;
    //cabra viva
    private boolean goatIsAlive = true;
    //Para verificar se colide com os obstáculos, não tem como fazer isso diretamente pela imagem. Usar método intersects
    Rectangle retanguloCabra = new Rectangle((int) goatX, (int) goatY, (int) larguraCabra - 15, (int) alturaCabra);
    public boolean colidiu = false;
    //pontos feitos na fase:
    private float pontos = 7;
    //moedas fase 1
    public int moedasFase4 = 0;

    //tamanho total da fase: 6209
    public float yEstrelas = 330;
    public float xEstrela1 = 780;
    public float xEstrela2 = 2001;
    public float xEstrela3 = 3698;
    public float xEstrela4 = 5946; //
    public boolean colidiuEstrela1 = false;
    public boolean colidiuEstrela2 = false;
    public boolean colidiuEstrela3 = false;
    public boolean colidiuEstrela4 = false;

    //---------------------------- SERÃO 15 OBSTÁCULOS NA TERCEIRA FASE:
    //Coordenadas iniciais do primeiro obstáculo: 
    public float yObstaculos = 410; // no "chão"
    public float xObstaculo1 = 789; // no fim da imagem
    public float xObstaculo2 = 780 + 400; // no fim da imagem
    public float xObstaculo3 = 780 + 230 + 450; // no fim da imagem
    public float xObstaculo4 = 789 + 230 + 450 + 300; // no fim da imagem
    public float xObstaculo5 = 780 + 230 + 450 + 280 + 280; // no fim da imagem
    public float xObstaculo6 = 780 + 230 + 450 + 280 + 280 + 300; // no fim da imagem
    public float xObstaculo7 = 789 + 230 + 450 + 280 + 280 + 300 + 264; // no fim da imagem
    public float xObstaculo8 = 780 + 230 + 450 + 280 + 280 + 300 + 264 + 350; // no fim da imagem
    public float xObstaculo9 = 700 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 290; // no fim da imagem
    public float xObstaculo10 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 360; // no fim da imagem
    public float xObstaculo11 = 780 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 380; // no fim da imagem
    public float xObstaculo12 = 780 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 380 + 266; // no fim da imagem
    public float xObstaculo13 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300; // no fim da imagem
    public float xObstaculo14 = 780 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251; // no fim da imagem
    public float xObstaculo15 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251 + 270; // no fim da imagem
    public float xObstaculo16 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251 + 270 + 300; // no fim da imagem
    public float xObstaculo17 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251 + 270 + 300 + 299;
    public float xObstaculo18 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251 + 270 + 300 + 299 + 254;
    public float xObstaculo19 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251 + 270 + 300 + 299 + 254 + 305;
    public float xObstaculo20 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 306 + 300 + 266 + 300 + 251 + 270 + 300 + 299 + 254 + 305 + 266;
//-----------------------------------------------------------------------
    public Sound cabra;
    public Sound catra;
    public Music musicaFundo;

    public Fase4(int id) {
    }

    public boolean completouAFase() {
        return completouAFase;
    }

    public void setCompletouAFase(boolean completouAFase) {
        this.completouAFase = completouAFase;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        fundo = new Image("recursos/backgrounds/background.png");
        estrela = new Image("recursos/characters/star.png");
        monster = new Image("recursos/characters/monster_2.png");

        smokeSheet = new SpriteSheet("recursos/characters/smoke.png", 140, 123);
        smoke = new Animation(smokeSheet, 100);

        whiteGoatMovendoSheet = new SpriteSheet("recursos/characters/goatMovendo.png", 79, 80);
        whiteGoatMovendo = new Animation(whiteGoatMovendoSheet, 200);

        pinkGoatMovendoSheet = new SpriteSheet("recursos/characters/pinkGoatMovendo.png", 79, 80);
        pinkGoatMovendo = new Animation(pinkGoatMovendoSheet, 200);

        blueGoatMovendoSheet = new SpriteSheet("recursos/characters/goatAzulMovendo.png", 79, 80);
        blueGoatMovendo = new Animation(blueGoatMovendoSheet, 200);

        inverseGoatMovendoSheet = new SpriteSheet("recursos/characters/goatReversaMovendo.png", 79, 80);
        inverseGoatMovendo = new Animation(inverseGoatMovendoSheet, 200);

        musicaFundo = new Music("recursos/sounds/musicaFundo.wav");
        musicaFundo.loop();
        cabra = new Sound("recursos/sounds/goat1.wav");
        catra = new Sound("recursos/sounds/catra2.wav");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Input input = gc.getInput();

        Rectangle obstaculo1 = new Rectangle((int) xObstaculo1, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo2 = new Rectangle((int) xObstaculo2, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo3 = new Rectangle((int) xObstaculo3, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo4 = new Rectangle((int) xObstaculo4, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo5 = new Rectangle((int) xObstaculo5, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo6 = new Rectangle((int) xObstaculo6, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo7 = new Rectangle((int) xObstaculo7, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo8 = new Rectangle((int) xObstaculo8, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo9 = new Rectangle((int) xObstaculo9, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo10 = new Rectangle((int) xObstaculo10, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo11 = new Rectangle((int) xObstaculo11, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo12 = new Rectangle((int) xObstaculo12, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo13 = new Rectangle((int) xObstaculo13, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo14 = new Rectangle((int) xObstaculo14, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo15 = new Rectangle((int) xObstaculo15, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo16 = new Rectangle((int) xObstaculo16, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo17 = new Rectangle((int) xObstaculo17, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo18 = new Rectangle((int) xObstaculo18, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo19 = new Rectangle((int) xObstaculo19, (int) yObstaculos, monster.getWidth(), monster.getHeight());
        Rectangle obstaculo20 = new Rectangle((int) xObstaculo20, (int) yObstaculos, monster.getWidth(), monster.getHeight());

        Rectangle estrela1 = new Rectangle((int) xEstrela1, (int) yEstrelas, estrela.getWidth(), estrela.getHeight());
        Rectangle estrela2 = new Rectangle((int) xEstrela2, (int) yEstrelas, estrela.getWidth(), estrela.getHeight());
        Rectangle estrela3 = new Rectangle((int) xEstrela3, (int) yEstrelas, estrela.getWidth(), estrela.getHeight());
        Rectangle estrela4 = new Rectangle((int) xEstrela4, (int) yEstrelas, estrela.getWidth(), estrela.getHeight());

        Rectangle retanguloCabra = new Rectangle((int) goatX, (int) goatY, (int) larguraCabra - 15, (int) alturaCabra);

        g.setColor(Color.black);
        g.drawImage(fundo, xBackground, yBackground);
        g.drawImage(fundo, xBackground + 791, yBackground);
        g.drawImage(estrela, 750, 0);
        g.drawString(Usuarios.getUsuarioAtual().getEstrelas(), 730, 20);
        if (goatIsAlive) {
            // g.drawRect(goatX, goatY, larguraCabra - 15, alturaCabra);
            g.drawString("Pontos: " + (int) pontos, 400, 25);

            g.drawImage(monster, xObstaculo1, yObstaculos);
            g.drawImage(monster, xObstaculo2, yObstaculos);
            g.drawImage(monster, xObstaculo3, yObstaculos);
            g.drawImage(monster, xObstaculo4, yObstaculos);
            g.drawImage(monster, xObstaculo5, yObstaculos);
            g.drawImage(monster, xObstaculo6, yObstaculos);
            g.drawImage(monster, xObstaculo7, yObstaculos);
            g.drawImage(monster, xObstaculo8, yObstaculos);
            g.drawImage(monster, xObstaculo9, yObstaculos);
            g.drawImage(monster, xObstaculo10, yObstaculos);
            g.drawImage(monster, xObstaculo11, yObstaculos);
            g.drawImage(monster, xObstaculo12, yObstaculos);
            g.drawImage(monster, xObstaculo13, yObstaculos);
            g.drawImage(monster, xObstaculo14, yObstaculos);
            g.drawImage(monster, xObstaculo15, yObstaculos);
            g.drawImage(monster, xObstaculo16, yObstaculos);
            g.drawImage(monster, xObstaculo17, yObstaculos);
            g.drawImage(monster, xObstaculo18, yObstaculos);
            g.drawImage(monster, xObstaculo19, yObstaculos);
            g.drawImage(monster, xObstaculo20, yObstaculos);

            if (Usuarios.getUsuarioAtual().getCabraAtual().equals("cabra1")) {
                whiteGoatMovendo.draw(goatX, goatY);
            }
            if (Usuarios.getUsuarioAtual().getCabraAtual().equals("cabra2")) {
                pinkGoatMovendo.draw(goatX, goatY);
            }
            if (Usuarios.getUsuarioAtual().getCabraAtual().equals("cabra3")) {
                blueGoatMovendo.draw(goatX, goatY);
            }
            if (Usuarios.getUsuarioAtual().getCabraAtual().equals("cabra4")) {
                inverseGoatMovendo.draw(goatX, goatY);
            }

            // --- se pegou a primeira estrela
            if (retanguloCabra.intersects(estrela1)) {
                colidiuEstrela1 = true;
            }
            if (!colidiuEstrela1) {
                g.drawImage(estrela, xEstrela1, yEstrelas);
            }
            if (colidiuEstrela1) {
                moedasFase4 = 1;
            }
            // --- sepegou a segunda estrela
            if (retanguloCabra.intersects(estrela2)) {
                colidiuEstrela2 = true;
            }
            if (!colidiuEstrela2) {
                g.drawImage(estrela, xEstrela2, yEstrelas);
            }
            if (colidiuEstrela2) {
                moedasFase4 = 2;
            }
            // ---- se pegou a terceira estrela 
            if (retanguloCabra.intersects(estrela3)) {
                colidiuEstrela3 = true;
            }
            if (!colidiuEstrela3) {
                g.drawImage(estrela, xEstrela3, yEstrelas);
            }
            if (colidiuEstrela3) {
                moedasFase4 = 3;
            }
            //-----se pegou a quarta estrela

            // ---- se pegou a terceira estrela 
            if (retanguloCabra.intersects(estrela4)) {
                colidiuEstrela4 = true;
            }
            if (!colidiuEstrela4) {
                g.drawImage(estrela, xEstrela4, yEstrelas);
            }
            if (colidiuEstrela4) {
                moedasFase4 = 4;
            }
            //------------------------------------

            if (retanguloCabra.intersects(obstaculo1)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo2)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo3)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo4)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo5)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo6)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo7)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo8)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo9)) {
                colidiu = true;
            }

            if (retanguloCabra.intersects(obstaculo10)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo11)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo12)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo13)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo14)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo15)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo16)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo17)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo18)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo19)) {
                colidiu = true;
            }
            if (retanguloCabra.intersects(obstaculo20)) {
                colidiu = true;
            }

            if (colidiu) {
                goatIsAlive = false;
            }

            if (xObstaculo20 < 25) {
                g.drawString("Você passou a fase 4! Pressione Enter para ir para a tela de níveis!", GoatRunner.LARGURA_JANELA / 9, GoatRunner.ALTURA_JANELA / 3);
                if (input.isKeyPressed(KEY_ENTER)) {
                    try {
                        persiste.atualizaMoedas(moedasFase4);
                        persiste.atualizaUsuario(Usuarios.getUsuarioAtual());
                        restartState();
                        sbg.enterState(GoatRunner.TELA_DE_FASES, new FadeOutTransition(), new FadeInTransition());
                    } catch (SQLException ex) {
                        Logger.getLogger(Fase4.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            smoke.draw(goatX, goatY - 30);
            g.drawString("Você perdeu! Pressione Enter para jogar novamente ou ESC para sair.", GoatRunner.LARGURA_JANELA / 5, GoatRunner.ALTURA_JANELA / 3);
            if (input.isKeyPressed(KEY_ENTER)) {
                restartState();
            }
            if (input.isKeyPressed(KEY_ESCAPE)) {
                sbg.enterState(GoatRunner.TELA_DE_FASES, new FadeOutTransition(), new FadeInTransition());
            }
        }

    }

    // Esse método seta tudo com os valores iniciais
    public void restartState() {
        goatX = 50;
        goatY = 370;
        xObstaculo1 = 780;
        xObstaculo2 = 780 + 400; // no fim da imagem
        xObstaculo3 = 780 + 200 + 450; // no fim da imagem
        xObstaculo4 = 780 + 200 + 450 + 300; // no fim da 
        xObstaculo5 = 780 + 200 + 450 + 280 + 280; // no fim da imagem
        xObstaculo6 = 780 + 200 + 450 + 280 + 280 + 265; // no fim da imagem
        xObstaculo7 = 780 + 200 + 450 + 280 + 280 + 265 + 264; // no fim da imagem
        xObstaculo8 = 780 + 200 + 450 + 280 + 280 + 265 + 264 + 350; // no fim da imagem
        xObstaculo9 = 780 + 200 + 450 + 280 + 280 + 265 + 264 + 299 + 290; // no fim da imagem
        xObstaculo10 = 780 + 200 + 450 + 280 + 280 + 265 + 264 + 299 + 200 + 375; // no fim da imagem
        xObstaculo11 = 780 + 200 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300; // no fim da imagem
        xObstaculo12 = 780 + 200 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266; // no fim da imagem
        xObstaculo13 = 780 + 200 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300; // no fim da imagem
        xObstaculo14 = 780 + 200 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251; // no fim da imagem
        xObstaculo15 = 780 + 200 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251 + 270; // no fim da imagem
        xObstaculo16 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251 + 270 + 300; // no fim da imagem
        xObstaculo17 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251 + 270 + 300 + 299;
        xObstaculo18 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251 + 270 + 300 + 299 + 254;
        xObstaculo19 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251 + 270 + 300 + 299 + 254 + 305;
        xObstaculo20 = 789 + 230 + 450 + 280 + 280 + 300 + 264 + 299 + 200 + 266 + 300 + 266 + 300 + 251 + 270 + 300 + 299 + 254 + 305 + 266;

        pontos = 7;
        colidiu = false;
        goatIsAlive = true;
        moedasFase4 = 0;
        //tamanho total da fase: 6209
        xEstrela1 = 780 + 666;
        xEstrela2 = 2001 + 600;
        xEstrela3 = 3698 + 600;
        xEstrela4 = 5946 + 600; //
        colidiuEstrela1 = false;
        colidiuEstrela2 = false;
        colidiuEstrela3 = false;
        colidiuEstrela4 = false;

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if (goatIsAlive) {
            xObstaculo20 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo1 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo2 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo3 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo4 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo6 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo5 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo7 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo8 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo9 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo10 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo11 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo12 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo13 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo14 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo15 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo16 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo17 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo18 -= delta * VELOCIDADE_DO_FUNDO;
            xObstaculo19 -= delta * VELOCIDADE_DO_FUNDO;

            // Traz as estrelas pra tela
            xEstrela1 -= delta * VELOCIDADE_DO_FUNDO;
            xEstrela2 -= delta * VELOCIDADE_DO_FUNDO;
            xEstrela3 -= delta * VELOCIDADE_DO_FUNDO;
            xEstrela4 -= delta * VELOCIDADE_DO_FUNDO;

            // Se a fase ainda não acabou
            if (xObstaculo20 >= 25) {
                xBackground -= delta * VELOCIDADE_DO_FUNDO;
                pontos += (delta * VELOCIDADE_DO_FUNDO) * 0.02;
            }

            whiteGoatMovendo.update(delta);
            pinkGoatMovendo.update(delta);
            blueGoatMovendo.update(delta);
            inverseGoatMovendo.update(delta);
            smoke.update(delta);

            if (xBackground <= -791) {
                xBackground = 0;
            }
            if (input.isKeyPressed(KEY_UP)) {
                pulando = true;
                if (Usuarios.getUsuarioAtual().getCabraAtual().equals("cabra4")) {
                    catra.play();
                } else {
                    cabra.play();
                }
                new Thread(new Fase4.thread()).start();
            }
            if (pulando) {
                if (goatY < 400) {
                    goatY -= 0.4 * delta;
                } else {
                    pulando = false;
                }
            }
            if (caindo) {
                if (goatY < 370) {
                    goatY += 0.4 * delta;
                } else {
                    caindo = false;
                }
            }
        }
    }

    @Override
    public int getID() {
        return GoatRunner.FASE_4;

    }

    public class thread implements Runnable {

        @Override
        public void run() {

            try {
                Thread.sleep(TEMPO_DE_PULO);
                pulando = false;
                caindo = true;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                new Thread(this).start();
                System.exit(0);
            }

        }
    }
}
