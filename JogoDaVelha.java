import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame implements ActionListener {

    private JButton[][] botoes = new JButton[3][3];
    private String jogadorAtual = "X";
    private boolean jogoAtivo = true;

    // 🎨 Cores globais (podem ser usadas em qualquer método)
    private final Color azulEscuro = new Color(72, 61, 139);   // texto
    private final Color azulNeon = new Color(0, 191, 255);     // cor do X
    private final Color magenta = new Color(153, 50, 204);     // cor do O

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        inicializarTabuleiro();
        setVisible(true);
    }

    private void inicializarTabuleiro() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                botoes[linha][coluna] = new JButton("");
                botoes[linha][coluna].setFont(new Font("Arial", Font.BOLD, 60));
                botoes[linha][coluna].addActionListener(this);

                // 🎨 Cor padrão preta (para o primeiro jogo)
                botoes[linha][coluna].setBackground(azulEscuro);
                botoes[linha][coluna].setForeground(Color.WHITE);
                botoes[linha][coluna].setOpaque(true);
                botoes[linha][coluna].setBorderPainted(true);

                add(botoes[linha][coluna]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jogoAtivo) return;

        JButton botaoClicado = (JButton) e.getSource();
        
        if (!botaoClicado.getText().equals("")) return;

        botaoClicado.setText(jogadorAtual);
        botaoClicado.setOpaque(true);
        botaoClicado.setBorderPainted(false);
        botaoClicado.setForeground(azulEscuro); // texto azul escuro

        if (jogadorAtual.equals("X")) {
            botaoClicado.setBackground(azulNeon);
        } else {
            botaoClicado.setBackground(magenta);
        }

        if (verificarVitoria()) {
            jogoAtivo = false;
            JOptionPane.showMessageDialog(this, "Jogador " + jogadorAtual + " venceu!");
            perguntarReiniciar();
        } else if (verificarEmpate()) {
            jogoAtivo = false;
            JOptionPane.showMessageDialog(this, "Deu velha!");
            perguntarReiniciar();
        } else {
            jogadorAtual = jogadorAtual.equals("X") ? "O" : "X";
        }
    }

    private boolean verificarVitoria() {
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(jogadorAtual) &&
                botoes[i][1].getText().equals(jogadorAtual) &&
                botoes[i][2].getText().equals(jogadorAtual)) {
                return true;
            }

            if (botoes[0][i].getText().equals(jogadorAtual) &&
                botoes[1][i].getText().equals(jogadorAtual) &&
                botoes[2][i].getText().equals(jogadorAtual)) {
                return true;
            }
        }

        if (botoes[0][0].getText().equals(jogadorAtual) &&
            botoes[1][1].getText().equals(jogadorAtual) &&
            botoes[2][2].getText().equals(jogadorAtual)) {
            return true;
        }

        if (botoes[0][2].getText().equals(jogadorAtual) &&
            botoes[1][1].getText().equals(jogadorAtual) &&
            botoes[2][0].getText().equals(jogadorAtual)) {
            return true;
        }

        return false;
    }

    private boolean verificarEmpate() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (botoes[linha][coluna].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void perguntarReiniciar() {
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja jogar novamente?", "Reiniciar Jogo",
                JOptionPane.YES_NO_OPTION);

        if (opcao == JOptionPane.YES_OPTION) {
            reiniciarJogo();
        } else {
            dispose();
        }
    }

    private void reiniciarJogo() {
        jogadorAtual = "X";
        jogoAtivo = true;
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                botoes[linha][coluna].setText("");
            }
        }
        resetarCores();
    }

    // 🔄 Reseta os botões para preto com texto branco
    private void resetarCores() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                botoes[linha][coluna].setBackground(azulEscuro);
                botoes[linha][coluna].setForeground(Color.WHITE);
                botoes[linha][coluna].setOpaque(true);
                botoes[linha][coluna].setBorderPainted(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JogoDaVelha::new);
    }
}
