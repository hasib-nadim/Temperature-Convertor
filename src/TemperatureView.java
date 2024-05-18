import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureView extends JFrame implements ActionListener {
    String[] unitsList = {"Celsius", "Fahrenheit", "Kelvin"};
    JTextField input = new JTextField();
    JTextField output = new JTextField();
    JComboBox<String> inputUnits = new JComboBox<>(unitsList);
    JComboBox<String> outputUnits = new JComboBox<>(unitsList);
    JPanel btnPanel = new JPanel();

    JButton convert = new JButton("Convert");
    JButton btn_1 = new JButton("1");
    JButton btn_2 = new JButton("2");
    JButton btn_3 = new JButton("3");
    JButton btn_4 = new JButton("4");
    JButton btn_5 = new JButton("5");
    JButton btn_6 = new JButton("6");
    JButton btn_7 = new JButton("7");
    JButton btn_8 = new JButton("8");
    JButton btn_9 = new JButton("9");
    JButton btn_0 = new JButton("0");
    JButton btn_dot = new JButton(".");
    JButton btn_clear = new JButton("C");
    JButton btn_back = new JButton("<-");


    TemperatureView() {
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 510);
        this.setTitle("Temperature Converter");
        this.setResizable(false);
        intiComponents();
    }

    private void intiComponents() {
        input.setBounds(10, 10, 360, 30);
        inputUnits.setBounds(370, 10, 100, 30);
        output.setBounds(10, 40, 360, 30);
        output.setEditable(false);
        outputUnits.setBounds(370, 40, 100, 30);

        this.add(input);
        this.add(inputUnits);
        this.add(output);
        this.add(outputUnits);
        renderButtons();
    }

    private void renderButtons() {
        btnPanel.setBounds(10, 120, 460, 340);
        btnPanel.setLayout(new GridLayout(5, 5, 2, 2));
        convert.setFocusPainted(false);
        btn_1.setFocusPainted(false);
        btn_2.setFocusPainted(false);
        btn_3.setFocusPainted(false);
        btn_4.setFocusPainted(false);
        btn_5.setFocusPainted(false);
        btn_6.setFocusPainted(false);
        btn_7.setFocusPainted(false);
        btn_8.setFocusPainted(false);
        btn_9.setFocusPainted(false);
        btn_0.setFocusPainted(false);
        btn_dot.setFocusPainted(false);
        btn_clear.setFocusPainted(false);
        btn_back.setFocusPainted(false);

        convert.addActionListener(this);
        btn_1.addActionListener(this);
        btn_2.addActionListener(this);
        btn_3.addActionListener(this);
        btn_4.addActionListener(this);
        btn_5.addActionListener(this);
        btn_6.addActionListener(this);
        btn_7.addActionListener(this);
        btn_8.addActionListener(this);
        btn_9.addActionListener(this);
        btn_0.addActionListener(this);
        btn_dot.addActionListener(this);
        btn_clear.addActionListener(this);
        btn_back.addActionListener(this);

        btnPanel.add(btn_clear);
        btnPanel.add(convert);
        btnPanel.add(btn_back);
        btnPanel.add(btn_1);
        btnPanel.add(btn_2);
        btnPanel.add(btn_3);
        btnPanel.add(btn_4);
        btnPanel.add(btn_5);
        btnPanel.add(btn_6);
        btnPanel.add(btn_7);
        btnPanel.add(btn_8);
        btnPanel.add(btn_9);
        btnPanel.add(btn_dot);
        btnPanel.add(btn_0);
        this.add(btnPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton[] btns = {btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_dot};
        for (JButton btn : btns) {
            if (e.getSource() == btn) {
                try {
                    Double.parseDouble(input.getText() + btn.getText());
                    input.setText(input.getText() + btn.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number");
                }
            }
        }
        if (e.getSource() == convert) {
            this.OnConvert();
        } else if (e.getSource() == btn_clear) {
            input.setText("");
            output.setText("");
        } else if (e.getSource() == btn_back) {
            String str = input.getText();
            if (str.length() > 0) {
                str = str.substring(0, str.length() - 1);
                input.setText(str);

            }
        }
    }

    private void OnConvert() {
        String inputUnit = (String) inputUnits.getSelectedItem();
        String outputUnit = (String) outputUnits.getSelectedItem();
        double inputValue = 0;
        try {
            inputValue = Double.parseDouble(input.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
            return;
        }
        double outputValue = 0;
        if (inputUnit.equals(outputUnit)) {
            outputValue = inputValue;
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Fahrenheit")) {
            outputValue = (inputValue * 9 / 5) + 32;
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Kelvin")) {
            outputValue = inputValue + 273.15;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Celsius")) {
            outputValue = (inputValue - 32) * 5 / 9;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Kelvin")) {
            outputValue = (inputValue - 32) * 5 / 9 + 273.15;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Celsius")) {
            outputValue = inputValue - 273.15;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Fahrenheit")) {
            outputValue = (inputValue - 273.15) * 9 / 5 + 32;
        }
        // fix outputValue to 2 decimal places
        outputValue = Math.round(outputValue * 100.0) / 100.0;
        output.setText(String.valueOf(outputValue));
    }
}
