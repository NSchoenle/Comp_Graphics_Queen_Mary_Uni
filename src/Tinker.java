import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;

public class Tinker {
	public static void main(String[] args) {
		JButton showface;
		JPanel menu;
		final Boolean showFace;
		JFrame a = new JFrame();
		a.setVisible(true);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setVisible(true);
		a.setSize(400, 300);
		showFace = true;
		System.out.println("Gui set");

		showface = new JButton("Hide/Show Faces");
		showface.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "HI");
				//showFace = !showFace;
			}
		});
		menu = new JPanel();
		menu.add(showface);
		menu.setBackground(Color.YELLOW);
		a.add(menu, BorderLayout.SOUTH);

	}
}
