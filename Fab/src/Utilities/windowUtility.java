package Utilities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class windowUtility {
	
	public static void errorMessage(String message) {
		JFrame frame = new JFrame("Erro");
		JOptionPane.showMessageDialog(frame,message);
	}
	
	public static void Message(String message) {
		JFrame frame = new JFrame("Mensagem");
		JOptionPane.showMessageDialog(frame,message);
	}
}
