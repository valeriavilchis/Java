import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField pantalla;
    private double num1, num2;
    private char operacion;

    public Calculadora() {

        // Configuracion de la ventana
        setTitle("Mi calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creacion de la pantalla
        pantalla = new JTextField();
        pantalla.setEditable(false);
        pantalla.setPreferredSize(new Dimension(300, 50)); 
        add(pantalla, BorderLayout.NORTH);

        // Creamos el panel de los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4));

        String[] botones = {
            "1", "2", "3", "+",
            "4", "5", "6", "-",
            "7", "8", "9", "*",
            "C", "0", "=", "/"
        };

        for (String boton : botones) {
            JButton btn = new JButton(boton);
            btn.addActionListener(this);
            btn.setPreferredSize(new Dimension(80, 80)); 
            panelBotones.add(btn);
        }

        add(panelBotones, BorderLayout.CENTER);
    }

    // Eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        String ValorBotonPresionado = e.getActionCommand();
        //System.out.println(ValorBotonPresionado);

        // Si se presiona un número o punto se agrega el texto al input
        if (Character.isDigit(ValorBotonPresionado.charAt(0)) || ValorBotonPresionado.equals(".")) {
            pantalla.setText(pantalla.getText() + ValorBotonPresionado);
        }

        // Si el valor del boton presionado es una oepracion, se guarda el valor del numero ingresado, la operacion y se limpia la pantalla para el segundo numero
        else if (ValorBotonPresionado.equals("/") || ValorBotonPresionado.equals("*") || ValorBotonPresionado.equals("-") || ValorBotonPresionado.equals("+")) {
            num1 = Double.parseDouble(pantalla.getText());
            operacion = ValorBotonPresionado.charAt(0);
            pantalla.setText("");
        }

        // Si el valor es igual toma el segungo numero ingresado, se utiliza el metodo "realizarOperacion" y se muestra el resultado
        else if (ValorBotonPresionado.equals("=")) {
            num2 = Double.parseDouble(pantalla.getText());
            double resultado = realizarOperacion(num1, num2, operacion);
            pantalla.setText(String.valueOf(resultado));
        }

        // Si presiona el boton "C" limpia la pantalla
        else {
            pantalla.setText("");
        }
    }

    // Metodo que realiza las operaciones
    private double realizarOperacion(double num1, double num2, char operacion) {
        switch (operacion) {
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: División por cero", "Error", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            case '*':
                return num1 * num2;
            case '-':
                return num1 - num2;
            case '+':
                return num1 + num2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }

}