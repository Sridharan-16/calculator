import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField display; // Text field to display the result
    
    private String operator = "";
    private boolean isOperatorClicked = false;

    public SimpleCalculator() {
        // Set the title and default close operation
        setTitle("Simple Calculator");
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        // setLayout(new BorderLayout());

        // Create the display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        // display.setHorizontalAlignment(JTextField.RIGHT);
        // display.setEditable(false); // No+n-editable
        add(display, BorderLayout.NORTH);

        // Create the button panel with GridLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 grid with spacing

        // Button labels
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Create and add buttons
        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.CENTER);
    }
    double firstNumber = 0;
    // @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle number and operator clicks
        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isOperatorClicked) {
                display.setText(""); // Clear the display for a new number
                isOperatorClicked = false;
            }
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            // Clear the display
            display.setText("");
            firstNumber = 0;
            operator = "";
        } else if (command.equals("=")) {
            // Perform calculation
            double secondNumber = Double.parseDouble(display.getText());
            switch (operator) {
                case "+" -> firstNumber += secondNumber;
                case "-" -> firstNumber -= secondNumber;
                case "*" -> firstNumber *= secondNumber;
                case "/" -> firstNumber /= secondNumber;
            }
            display.setText(String.valueOf(firstNumber));
            operator = "";
        } else {
            // Save the first number and operator
            firstNumber = Double.parseDouble(display.getText());
            operator = command;
            isOperatorClicked = true;
        }
    }

    public static void main(String[] args) {
        // Create and display the calculator
        SimpleCalculator calculator = new SimpleCalculator();
        calculator.setVisible(true);
    }
}

