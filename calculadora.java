import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    // Campos de texto para exibir os números e resultados
    private JTextField display;

    // Armazena o primeiro número e o operador
    private double num1 = 0;
    private String operador = "";

    // Construtor da classe
    public Main() {
        // Configurações básicas da janela
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuração do campo de exibição
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 4, 10, 10));

        // Array com os textos dos botões
        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "C", "+",
                "=", "(", ")", "raiz"
        };

        // Criação dos botões e adição ao painel
        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.PLAIN, 20));
            botao.addActionListener(this);
            painelBotoes.add(botao);
        }

        // Adiciona os componentes à janela
        add(display, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

    // Lógica de ação dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ((comando.charAt(0) >= '0' && comando.charAt(0) <= '9') || comando.equals(".")) {
            display.setText(display.getText() + comando);
        } else if (comando.equals("C")) {
            display.setText("");
            num1 = 0;
            operador = "";
        } else if (comando.equals("=")) {
            calcularResultado();
        } else if (comando.equals("sqrt")) {
            double valor = Math.sqrt(Double.parseDouble(display.getText()));
            display.setText(String.valueOf(valor));
        } else {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operador = comando;
                display.setText("");
            }
        }
    }

    // Método para realizar o cálculo com base no operador
    private void calcularResultado() {
        double num2 = Double.parseDouble(display.getText());
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                resultado = num1 / num2;
                break;
        }

        display.setText(String.valueOf(resultado));
        operador = "";
        num1 = 0;
    }

    // Método principal para iniciar a aplicação
    public static void main(String[] args) {
        new Main();
    }
}
