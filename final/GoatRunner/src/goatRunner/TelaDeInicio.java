package goatRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import persistencia.Persiste;

/**
 *
 * @author Lorena
 */
public class TelaDeInicio extends BasicGameState {

    public Persiste persiste = new Persiste();
    //formulario de login
    private static final JPanel Painel = new JPanel();
    private static final JFrame form = new JFrame();
    //formulario de cadastro
    private static final JPanel painelCadastro = new JPanel();
    private static final JFrame formCadastro = new JFrame();
    Image fundoInicio;
    public int mouseX;
    public int mouseY;
    //relação com banco de dados
    private boolean logado = false;
    public boolean check = false;
    public boolean senhaValida = false;


    public TelaDeInicio(int id) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        fundoInicio = new Image("recursos/backgrounds/backgroundTelaInicial_2.png");

    }

    //metodo que desenha as coisas na tela
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, org.newdawn.slick.Graphics g) throws SlickException {
        g.drawImage(fundoInicio, 0, 0);

    }

    //atualiza as imagens na tela, serve pra "mexer", quando se trabalha
    //com animações, elas precisam do update.
    // quando as coisas mudam!
    @Override
    public void update(GameContainer gc, final StateBasedGame sbg, int delta) {
        // pegando as coordenadas do mouse;

        mouseX = Mouse.getX();
        mouseY = Mouse.getY();
        Input input = gc.getInput();

        //Se clicou no botão "instruções"
        if (((mouseX > 550) && (mouseX < 771)) && ((mouseY > 433) && (mouseY < 479))) {
            if (input.isMousePressed(0)) {
                System.out.println("clicou instruções");
                sbg.enterState(GoatRunner.TELA_DE_INSTRUCOES, new FadeOutTransition(), new FadeInTransition());
            }

        }

        //se clicou em "Login", ele abre o painel de login
        if (((mouseX > 117) && (mouseX < 318)) && ((mouseY > 130) && (mouseY < 178))) {
            if (input.isMousePressed(0)) {
                System.out.println("clique  login");

                //---------CRIANDO O PAINEL DE LOGIN --------------------------------------
                form.setDefaultCloseOperation(form.DISPOSE_ON_CLOSE);
                form.setTitle("Faça seu login");
                form.setSize(470, 115);
                form.setLocationRelativeTo(null);
                Painel.setLayout(null);
                form.add(Painel);
                JLabel labelLogin = new JLabel("Login: ");
                JLabel labelSenha = new JLabel("Senha:");
                final JTextField loginTexto = new JTextField();
                final JPasswordField senhaTexto = new JPasswordField();
                JButton OK = new JButton("OK");
                //----------TRATADOR DO BOTÃO OK --------------------------------------------
                OK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        String loginDigitado = loginTexto.getText();
                        String senhaDigitada = senhaTexto.getText();
                        try {

                            check = persiste.verificaLogin(loginDigitado, senhaDigitada);
                            //se o login foi realizado com sucesso
                            if (check) {
                                logado = true;
                                sbg.enterState(GoatRunner.TELA_DE_PERSONAGENS, new FadeOutTransition(), new FadeInTransition());
                                form.setVisible(false);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(TelaDeInicio.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                // ----------------- FIM DO TRATADOR DO BOTÃO ----------------------------------------------
                adicionaLogin(labelLogin, 10, 10, 70, 25);
                adicionaLogin(loginTexto, 80, 10, 230, 25);
                adicionaLogin(labelSenha, 10, 40, 70, 25);
                adicionaLogin(senhaTexto, 80, 40, 230, 25);
                adicionaLogin(OK, 360, 40, 80, 25);
                form.setVisible(true);
                // ----------------------- FIM DA TELA DE LOGIN ----------------------------------------------
            }
        }

        //se clicou para fazer cadastro :D
        if (((mouseX > 470) && (mouseX < 700)) && ((mouseY > 130) && (mouseY < 178))) {
            if (input.isMousePressed(0)) {
                System.out.println("clique cadastro");
                //---------CRIANDO TELA DE CADASTRO ---------------
                formCadastro.setDefaultCloseOperation(formCadastro.DISPOSE_ON_CLOSE);
                formCadastro.setTitle("Cadastro");
                formCadastro.setSize(500, 150);
                formCadastro.setLocationRelativeTo(null);
                painelCadastro.setLayout(null);
                formCadastro.add(painelCadastro);
                JLabel labelLogin = new JLabel("Login: ");
                JLabel labelSenha = new JLabel("Senha: ");
                JLabel labelConfirmaSenha = new JLabel("Confirme sua senha: ");
                final JTextField textoLogin = new JTextField();
                final JPasswordField textoSenha = new JPasswordField();
                final JPasswordField textoConfirmaSenha = new JPasswordField();
                JButton OK = new JButton("OK");
                //----------------TRATADOR DO BOTAO DE CADASTRO ------------------------ 

                OK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        String dadoLogin = textoLogin.getText();
                        String dadoSenha = textoSenha.getText();
                        String dadoConfirmaSenha = textoConfirmaSenha.getText();
                        //verifica se a senha tá igual
                        if (dadoSenha.equals(dadoConfirmaSenha)) {
                            try {
                                // verifica se o login já existe:
                                check = persiste.verificaLoginNaoUsado(dadoLogin);
                                if (check) {
                                    persiste.cadastra(dadoLogin, dadoSenha);
                                    formCadastro.setVisible(false);
                                }
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Senhas não conferem.");
                        }
                    }
                });

                //-------------- FIM DO TRATADOR DE BOTAO -----------------------------
                adicionaCadastro(labelLogin, 10, 10, 70, 25);
                adicionaCadastro(textoLogin, 145, 10, 230, 25);
                adicionaCadastro(labelSenha, 10, 40, 70, 25);
                adicionaCadastro(textoSenha, 145, 40, 230, 25);
                adicionaCadastro(labelConfirmaSenha, 10, 70, 230, 25);
                adicionaCadastro(textoConfirmaSenha, 145, 70, 230, 25);
                adicionaCadastro(OK, 400, 70, 70, 25);
                formCadastro.setVisible(true);
                //----------- FIM DA TELA DE CADASTRO---------------------------------------
            }
        }

    }

    private static void adicionaLogin(Component Componente, int nColuna, int nLinha, int nLargura, int nAltura) {
        Painel.add(Componente);
        Componente.setBounds(nColuna, nLinha, nLargura, nAltura);
    }

    private static void adicionaCadastro(Component Componente, int nColuna, int nLinha, int nLargura, int nAltura) {
        painelCadastro.add(Componente);
        Componente.setBounds(nColuna, nLinha, nLargura, nAltura);
    }

    @Override
    public int getID() {
        //definimos isso lá na classe principal
        return 0;
    }
}
