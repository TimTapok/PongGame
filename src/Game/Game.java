package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;
import components.*;
import javafx.scene.layout.Border;

public class Game extends JPanel implements ActionListener{
	
	private JPanel mainMenu, gameO;
	private JButton button1, button2;
	private JCheckBox hardBox;
	
	private enum Vector2D{
		U_LEFT_X,
		U_LEFT_Y,
		U_RIGHT_X,
		U_RIGHT_Y,
		D_LEFT_X,
		D_LEFT_Y,
		D_RIGHT_X,
		D_RIGHT_Y,
		SPEED_X,
		SPEED_Y;
	}

	private Random rand = new Random();
	private Timer timer = null;
	
	private final int STEP = 6;
	private int easyOrHard;
	private int sm;
	
	private Image raket;
	private Image ball;
	
	private int vectRaket1[] = new int[8];
	private int vectRaket2[] = new int[8];
	private int vectBall[] = new int[10];
	
	private boolean hardM;
	private boolean up;
	private boolean down;
	private boolean inGame;
	private boolean inMenu;
	
	public Game(){
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(BackGround.WIDTH, BackGround.HEIGHT));
		setLayout(null);
        addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					down = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_UP){
					up = true;
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					inGame = !inGame;
					inMenu = !inMenu;
				}
			}
			@Override
			public void keyReleased(KeyEvent e){
				up = false;
				down = false;
			}
		});
		
        mainMenu = new JPanel();
		mainMenu.setLayout(null);
        mainMenu.setBounds(300, 100, 200, 400);
        mainMenu.setBackground(new Color(66, 48, 88, 150));
        
        button1 = new JButton("New Game");
        button1.setBounds(50, 20, 100, 50);
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         	   if(e.getActionCommand().equals("New Game")) {
    				initGame();
    			}
            }
        });
        
        button2 = new JButton("Exit");
        button2.setBounds(50, 160, 100, 50);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         	   if(e.getActionCommand().equals("Exit")) {
    				System.exit(0);
    			}
            }
        });
        
        hardBox = new JCheckBox("Hard Mode!");
        hardBox.setBounds(50, 90, 100, 16);
        hardBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				hardM = !hardM;
			}
        });
        
        mainMenu.add(button1);
        mainMenu.add(button2);
        mainMenu.add(hardBox);
        
        gameO = new JPanel();
        gameO.setLayout(null);
        gameO.setBackground(Color.BLACK);
        gameO.setPreferredSize(new Dimension(BackGround.WIDTH, BackGround.HEIGHT));
        
        add(mainMenu);
        add(gameO);
        
		loadImages();
		initGame();	
	}
	
	public void initGame(){
		inGame = true;
		inMenu = false;
		
		createRaket1();
		createRoket2();
		createBall();
		
		if(timer == null) {
			timer = new Timer(10, this);
			timer.start();
		}
	}
	
	public void createRaket1() {
		vectRaket1[Vector2D.U_LEFT_X.ordinal()] = 0;
		vectRaket1[Vector2D.U_LEFT_Y.ordinal()] = 276;
		vectRaket1[Vector2D.U_RIGHT_X.ordinal()] = 16;
		vectRaket1[Vector2D.U_RIGHT_Y.ordinal()] = 276;
		vectRaket1[Vector2D.D_LEFT_X.ordinal()] = 0;
		vectRaket1[Vector2D.D_LEFT_Y.ordinal()] = 324;
		vectRaket1[Vector2D.D_RIGHT_X.ordinal()] = 16;
		vectRaket1[Vector2D.D_RIGHT_Y.ordinal()] = 324;
	}
	
	public void createRoket2() {
		vectRaket2[Vector2D.U_LEFT_X.ordinal()] = 784;
		vectRaket2[Vector2D.U_LEFT_Y.ordinal()] = 276;
		vectRaket2[Vector2D.U_RIGHT_X.ordinal()] = 800;
		vectRaket2[Vector2D.U_RIGHT_Y.ordinal()] = 276;
		vectRaket2[Vector2D.D_LEFT_X.ordinal()] = 784;
		vectRaket2[Vector2D.D_LEFT_Y.ordinal()] = 324;
		vectRaket2[Vector2D.D_RIGHT_X.ordinal()] = 800;
		vectRaket2[Vector2D.D_RIGHT_Y.ordinal()] = 324;
	}
	
	public void createBall() {
		int Ux = rand.nextInt(300) + 300;
		int Uy = rand.nextInt(400) + 100;
		vectBall[Vector2D.U_LEFT_X.ordinal()] = Ux;
		vectBall[Vector2D.U_LEFT_Y.ordinal()] = Uy;
		vectBall[Vector2D.U_RIGHT_X.ordinal()] = Ux + 16;
		vectBall[Vector2D.U_RIGHT_Y.ordinal()] = Uy;
		vectBall[Vector2D.D_LEFT_X.ordinal()] = Ux;
		vectBall[Vector2D.D_LEFT_Y.ordinal()] = Uy + 16;
		vectBall[Vector2D.D_RIGHT_X.ordinal()] = Ux + 16;
		vectBall[Vector2D.D_RIGHT_Y.ordinal()] = Uy;
		vectBall[Vector2D.SPEED_X.ordinal()] = -2;
		vectBall[Vector2D.SPEED_Y.ordinal()] = -2;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(inGame == false && inMenu == false) {
			g.setFont(new Font("Arial", Font.ITALIC, 56));
			g.setColor(Color.white);
			g.drawString("Game over", BackGround.WIDTH / 3, BackGround.HEIGHT / 3);
		}
		else {
			g.drawImage(raket, vectRaket1[Vector2D.U_LEFT_X.ordinal()], vectRaket1[Vector2D.U_LEFT_Y.ordinal()], this);
			g.drawImage(raket, vectRaket2[Vector2D.U_LEFT_X.ordinal()], vectRaket2[Vector2D.U_LEFT_Y.ordinal()], this);
			g.drawImage(ball, vectBall[Vector2D.U_LEFT_X.ordinal()], vectBall[Vector2D.U_LEFT_Y.ordinal()], this);
		}
	}
	
	public void moveRaket1(){
		if(up){
			if(vectRaket1[Vector2D.U_LEFT_Y.ordinal()] > 0){
				for(int i = 1; i < 8; i += 2){
					vectRaket1[i] -= STEP;
				}
			}
		}
		if(down){
			if(vectRaket1[Vector2D.D_LEFT_Y.ordinal()] < 600){
				for(int i = 1; i < 8; i += 2){
					vectRaket1[i] += STEP;
				}
			}
		}
	}

	public void moveRaket2() {
		
		if(hardM == true) {
			easyOrHard = STEP;
			sm = 24;
		}
		else {
			easyOrHard = (rand.nextInt(STEP) + 1);
			sm = 56;
		}
		
		if(vectBall[Vector2D.SPEED_X.ordinal()] > 0 && vectBall[Vector2D.U_RIGHT_X.ordinal()] > 500) {
			if(vectBall[Vector2D.U_RIGHT_Y.ordinal()] < vectRaket2[Vector2D.D_LEFT_Y.ordinal()] - sm) {
				if(vectRaket2[Vector2D.U_LEFT_Y.ordinal()] > 0) {
					for(int i = 1; i < 8; i += 2){
						vectRaket2[i] -= easyOrHard;
					}
				}
			}

			if(vectBall[Vector2D.D_RIGHT_Y.ordinal()] > vectRaket2[Vector2D.U_LEFT_Y.ordinal()] + sm) {
				if(vectRaket2[Vector2D.U_LEFT_Y.ordinal()] < 524){
					for(int i = 1; i < 8; i += 2){
						vectRaket2[i] += easyOrHard;
					}
				}
			}
		}
	}
	
	public void moveBall(){
		for(int i = 0, j = 1; i < 8; i += 2, j += 2){
			vectBall[i] += vectBall[Vector2D.SPEED_X.ordinal()];
			vectBall[j] += vectBall[Vector2D.SPEED_Y.ordinal()];
		}
		collision();
	}
	
	public void collision(){
		if(vectBall[Vector2D.SPEED_X.ordinal()] < 0){
			if (vectBall[Vector2D.U_LEFT_X.ordinal()] <= vectRaket1[Vector2D.U_RIGHT_X.ordinal()]){
				if((vectBall[Vector2D.U_LEFT_Y.ordinal()] <= vectRaket1[Vector2D.D_LEFT_Y.ordinal()]) &&
						(vectBall[Vector2D.D_LEFT_Y.ordinal()] >= vectRaket1[Vector2D.U_LEFT_Y.ordinal()])){
					vectBall[Vector2D.SPEED_X.ordinal()] *= -1;
				}
				if(vectBall[Vector2D.U_LEFT_X.ordinal()] <= 0){
					inMenu = false;
					inGame = false;
				}
			}
			if(vectBall[Vector2D.U_LEFT_Y.ordinal()] <= 0 || vectBall[Vector2D.D_LEFT_Y.ordinal()] >= 600){
				vectBall[Vector2D.SPEED_Y.ordinal()] *= -1;
			}
		}
		else{
			if (vectBall[Vector2D.U_RIGHT_X.ordinal()] >= vectRaket2[Vector2D.U_LEFT_X.ordinal()]){
				if((vectBall[Vector2D.U_LEFT_Y.ordinal()] <= vectRaket2[Vector2D.D_LEFT_Y.ordinal()]) &&
						(vectBall[Vector2D.D_LEFT_Y.ordinal()] >= vectRaket2[Vector2D.U_LEFT_Y.ordinal()])){
					vectBall[Vector2D.SPEED_X.ordinal()] *= -1;
				}
				if(vectBall[Vector2D.U_LEFT_X.ordinal()] >= 800){
					inMenu = false;
					inGame = false;
				}
			}
			if(vectBall[Vector2D.U_LEFT_Y.ordinal()] <= 0 || vectBall[Vector2D.D_LEFT_Y.ordinal()] >= 600){
				vectBall[Vector2D.SPEED_Y.ordinal()] *= -1;
			}
		}
	}
	
	public void loadImages(){
		ImageIcon iraket = new ImageIcon("Images/raket.png");
		raket = iraket.getImage();
		ImageIcon iball = new ImageIcon("Images/ball.png");
		ball = iball.getImage();
	}
	
	public void menu() {
		mainMenu.setVisible(true);
		button1.setFocusable(false);
		button2.setFocusable(false);
		hardBox.setFocusable(false);
	}
	
	public void gameOver() {
		gameO.setVisible(true);
		gameO.setFocusable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(inGame){
			mainMenu.setVisible(false);
			gameO.setVisible(false);
			setFocusable(true);
			moveRaket1();
			moveRaket2();
			moveBall();
		}
		else if(inMenu){
			menu();
		}
		else {
			
			menu();
			//System.exit(0);
		}
		repaint();
	}
}