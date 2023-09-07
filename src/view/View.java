package view;

import javax.swing.JOptionPane;

/**
 *
 * @author jhonm
 */

public class View {

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public void printConsole(String message) {
        System.out.print(message);
    }

    public void showInt(int number) {
        JOptionPane.showMessageDialog(null, number);
    }

    public String readData(String message) {
        try {
            return JOptionPane.showInputDialog(null, message);
        } catch (Exception e) {
            showMessage("Error de entrada de datos: " + e.getMessage());
            return null;
        }
    }

    public double readDouble(String message) {
        try {
            String inputData = JOptionPane.showInputDialog(null, message);
            return Double.parseDouble(inputData);
        } catch (NumberFormatException e) {
            showMessage("Error: Por favor, ingrese un número válido.");
            return Double.NaN; // Valor no válido
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
            return Double.NaN; // Valor no válido
        }
    }

    public int readInt(String message) {
        try {
            String inputData = JOptionPane.showInputDialog(null, message);
            return Integer.parseInt(inputData);
        } catch (NumberFormatException e) {
            showMessage("Error: Por favor, ingrese un número entero válido.");
            return 0; // Valor no válido
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
            return 0; // Valor no válido
        }
    }
}
