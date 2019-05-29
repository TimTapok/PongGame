package Window;

import java.awt.*;
import javax.swing.*;
import Game.*;
import components.*;

public class Window extends JFrame{
	
	
	Window(){
		setTitle("Pong Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		JLayeredPane lpane = new JLayeredPane();
		add(lpane);
		lpane.setBounds(0, 0, WIDTH, HEIGHT);
		
		JPanel panel0 = new JPanel();
		panel0.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.BLACK);
		panel1.setBounds(0, 0, 800, 600);
		panel1.setOpaque(true);
		
		lpane.add(panel1, new Integer(0), 0);

		add(panel0);*/
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - BackGround.WIDTH) / 2, (screenSize.height - BackGround.HEIGHT) / 2);
		
		add(new Game());
		
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                  new Window();
             }
        });
   }
}