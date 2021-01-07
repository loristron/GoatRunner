package uteis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Leandro
 */
public class Entradas {

    /**
     * Opção retornada pelo diálogo quando o usuário escolhe sim.
     */
    public static final int OPCAO_SIM = JOptionPane.YES_OPTION;

    /**
     * Opção retornada pelo diálogo quando o usuário escolhe não.
     */
    public static final int OPCAO_NAO = JOptionPane.NO_OPTION;

    /**
     * Opção retornada pelo diálogo quando o usuário escolhe ok.
     */
    public static final int OPCAO_OK = JOptionPane.OK_OPTION;

    /**
     * Opção retornada pelo diálogo quando o usuário escolhe cancelar.
     */
    public static final int OPCAO_CANCELAR = JOptionPane.CANCEL_OPTION;

    /**
     * Opção retornada pelo diálogo ao ser fechado.
     */
    public static final int OPCAO_FECHAR = JOptionPane.CLOSED_OPTION;

    /**
     * Diálogo de entrada que recebe um texto do usuário.
     *
     * @param titulo Título que será exibido no diálogo.
     * @param mensagem Mensagem que será exibida no diálogo.
     * @return Texto digitado pelo usuário ou <code>null</code> caso o usuário
     * cancele ou feche o diálogo.
     */
    public static String dialogoEntrada(String titulo, String mensagem) {
        return JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Diálogo de entrada que fornece ao usuário uma lista de opções para ser
     * escolhida.
     *
     * @param titulo Título que será exibido no diálogo.
     * @param mensagem Mensagem que será exibida no diálogo.
     * @param opcoes Array de strings com todas as opções que serão
     * disponibilizadas ao usuário.
     * @param opcaoInicial Opção inicial.
     * @return Opção selecionada ou <code>null</code> caso o usuário cancele ou
     * feche o diálogo.
     */
    public static String dialogoOpcoes(String titulo, String mensagem, String[] opcoes, String opcaoInicial) {
        return String.valueOf(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcaoInicial));
    }

    /**
     * Diálogo de entrada com as opções de SIM e NÃO.
     *
     * @param titulo Título que será exibido no diálogo.
     * @param mensagem Mensagem que será exibido no diálogo.
     * @return Identificador referente à opção escolhida. Ver
     * <code>Entradas.OPCAO_SIM</code>, <code>Entradas.OPCAO_NAO</code> e
     * <code>Entradas.OPCAO_FECHAR</code>.
     */
    public static int dialogoSimNao(String titulo, String mensagem) {
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Diálogo de entrada com as opções de OK e CANCELAR.
     *
     * @param titulo Título que será exibido no diálogo.
     * @param mensagem Mensagem que será exibido no diálogo.
     * @return Identificador referente à opção escolhida. Ver
     * <code>Entradas.OPCAO_OK</code>, <code>Entradas.OPCAO_CANCELAR</code> e
     * <code>Entradas.OPCAO_FECHAR</code>.
     */
    public static int dialogoOkCancelar(String titulo, String mensagem) {
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Diálogo de entrada com as opções de SIM, NÃO e CANCELAR.
     *
     * @param titulo Título que será exibido no diálogo.
     * @param mensagem Mensagem que será exibido no diálogo.
     * @return Identificador referente à opção escolhida. Ver
     * <code>Entradas.OPCAO_SIM</code>, <code>Entradas.OPCAO_NAO</code>,
     * <code>Entradas.OPCAO_CANCELAR</code> e
     * <code>Entradas.OPCAO_FECHAR</code>.
     */
    public static int dialogoSimNaoCancelar(String titulo, String mensagem) {
        return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

}
