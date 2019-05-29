package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import components.*;

public class Game extends JPanel implements ActionListener{
	
	private JPanel mainMenu;
	private JButton button1;
	
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

	private final int STEP = 6;
	private Image raket;
	private Image ball;
	private int vectRaket1[] = new int[8];
	private int vectRaket2[] = new int[8];
	private int vectBall[] = new int[10];
	private Timer timer;
	private boolean up;
	private boolean down;
	private boolean esc;
	private boolean inGame = true;
	private boolean player1W;
	private boolean inMenu;
	
	public Game(){
		setBackground(Color.BLACK);
		setPreferredSize(BackGround.dimensionFrame);
		setLayout(null);
		
		mainMenu = new JPanel();
        mainMenu.setBounds(300, 100, 200, 400);
        mainMenu.setBackground(Color.BLUE);
        button1 = new JButton("button");
        mainMenu.add(button1, new GridBagLayout());
        add(mainMenu);
		
		loadImages();
		initGame();
		
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
					inMenu = !inMenu;/*
					if(esc == false) {
						menu();
					}*/
				}
			}
			@Override
			public void keyReleased(KeyEvent e){
				up = false;
				down = false;
				esc = false;
			}
		});
	}
	
	public void initGame(){
		createRaket1();
		createRoket2();
		createBall();
		
		timer = new Timer(10, this);
		timer.start();
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
		vectBall[Vector2D.U_LEFT_X.ordinal()] = 400;
		vectBall[Vector2D.U_LEFT_Y.ordinal()] = 300;
		vectBall[Vector2D.U_RIGHT_X.ordinal()] = 416;
		vectBall[Vector2D.U_RIGHT_Y.ordinal()] = 300;
		vectBall[Vector2D.D_LEFT_X.ordinal()] = 400;
		vectBall[Vector2D.D_LEFT_Y.ordinal()] = 316;
		vectBall[Vector2D.D_RIGHT_X.ordinal()] = 416;
		vectBall[Vector2D.D_RIGHT_Y.ordinal()] = 316;
		vectBall[Vector2D.SPEED_X.ordinal()] = -2;
		vectBall[Vector2D.SPEED_Y.ordinal()] = -2;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(raket, vectRaket1[Vector2D.U_LEFT_X.ordinal()], vectRaket1[Vector2D.U_LEFT_Y.ordinal()], this);
		g.drawImage(raket, vectRaket2[Vector2D.U_LEFT_X.ordinal()], vectRaket2[Vector2D.U_LEFT_Y.ordinal()], this);
		g.drawImage(ball, vectBall[Vector2D.U_LEFT_X.ordinal()], vectBall[Vector2D.U_LEFT_Y.ordinal()], this);
		/*if(inGame) {
			
		}
		else{
			g.setColor(Color.white);
			if(player1W) {
				g.drawString("PLAYER1 WON", 348, 275);
			}
			else {
				g.drawString("PLAYER1 WON", 348, 275);
			}
		}*/
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
		if(vectBall[Vector2D.SPEED_X.ordinal()] > 0 && vectBall[Vector2D.U_RIGHT_X.ordinal()] > 500) {
			if(vectBall[Vector2D.U_RIGHT_Y.ordinal()] < vectRaket2[Vector2D.D_LEFT_Y.ordinal()] - 24) {
				if(vectRaket2[Vector2D.U_LEFT_Y.ordinal()] > 0) {
					for(int i = 1; i < 8; i += 2){
						vectRaket2[i] -= STEP;
					}
				}
			}
			
			if(vectBall[Vector2D.D_RIGHT_Y.ordinal()] > vectRaket2[Vector2D.U_LEFT_Y.ordinal()] + 24) {
				if(vectRaket2[Vector2D.U_LEFT_Y.ordinal()] < 524){
					for(int i = 1; i < 8; i += 2){
						vectRaket2[i] += STEP;
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
				if(vectBall[Vector2D.U_LEFT_X.ordinal()] < 16){
					player1W = false;
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
				if(vectBall[Vector2D.U_LEFT_X.ordinal()] > 786){
					player1W = true;
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

	public void remMenu() {
		remove(mainMenu);
		repaint();
	    revalidate();
	}
	
	public void menu() {
		esc = true;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(inGame){
			mainMenu.setVisible(false);
			setFocusable(true);
			moveRaket1();
			moveRaket2();
			moveBall();
		}
		else if(inMenu){
			mainMenu.setVisible(true);
			setFocusable(true);
		}
		else {
			System.exit(0);
		}
		repaint();
	}
	
	@Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }
} 	