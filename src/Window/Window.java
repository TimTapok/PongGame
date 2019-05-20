package Window;

import java.awt.*;
import javax.swing.*;
import Game.*;

public class Window extends JFrame{
	final int WIDTH = 800;
	final int HEIGHT = 600;
	
	Window(){
		setTitle("Pong Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2);
		
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