package Window;

import java.awt.*;
import javax.swing.*;
import Game.*;

public class Window extends JFrame{
	public JFrame frame;
	final int WIDTH = 800;
	final int HEIGHT = 600;
	
	Window(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setTitle("Pong Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocation((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2);
		setVisible(true);
		add(new Game());
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                  new Window();
             }
        });
   }
}
