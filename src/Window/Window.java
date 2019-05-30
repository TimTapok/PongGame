package Window;

import java.awt.*;
import javax.swing.*;
import Game.*;
import components.*;

public class Window extends JFrame{
	
	
	Window(){
		setTitle("Pong Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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