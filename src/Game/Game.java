package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Game extends JPanel implements ActionListener{
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
	boolean up;
	boolean down;
	boolean inGame = true;
	boolean player1W;
	
	public Game(){
		setBackground(Color.BLACK);
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
			}
			@Override
			public void keyReleased(KeyEvent e){
				up = false;
				down = false;
			}
		});
		setFocusable(true);
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
		vectRaket2[Vector2D.U_LEFT_X.ordinal()] = 776;
		vectRaket2[Vector2D.U_LEFT_Y.ordinal()] = 276;
		vectRaket2[Vector2D.U_RIGHT_X.ordinal()] = 792;
		vectRaket2[Vector2D.U_RIGHT_Y.ordinal()] = 276;
		vectRaket2[Vector2D.D_LEFT_X.ordinal()] = 776;
		vectRaket2[Vector2D.D_LEFT_Y.ordinal()] = 324;
		vectRaket2[Vector2D.D_RIGHT_X.ordinal()] = 792;
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
		if(inGame) {
			g.drawImage(raket, vectRaket1[Vector2D.U_LEFT_X.ordinal()], vectRaket1[Vector2D.U_LEFT_Y.ordinal()], this);
			g.drawImage(raket, vectRaket2[Vector2D.U_LEFT_X.ordinal()], vectRaket2[Vector2D.U_LEFT_Y.ordinal()], this);
			g.drawImage(ball, vectBall[Vector2D.U_LEFT_X.ordinal()], vectBall[Vector2D.U_LEFT_Y.ordinal()], this);
		}
		else {
			g.setColor(Color.white);
			if(player1W) {
				g.drawString("PLAYER1 WON", 348, 275);
			}
			else {
				g.drawString("PLAYER2 WON", 348, 275);
			}
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
			if(vectRaket1[Vector2D.U_LEFT_Y.ordinal()] < 524){
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
				if((vectBall[Vector2D.U_LEFT_Y.ordinal()] <= vectRaket1[Vector2D.D_RIGHT_Y.ordinal()]) &&
						(vectBall[Vector2D.D_LEFT_Y.ordinal()] >= vectRaket1[Vector2D.U_RIGHT_Y.ordinal()])){
					vectBall[Vector2D.SPEED_X.ordinal()] *= -1;
				}
				if(vectBall[Vector2D.U_LEFT_X.ordinal()] <= 0){
					player1W = false;
					inGame = false;
				}
			}
			if(vectBall[Vector2D.U_LEFT_Y.ordinal()] <= 0 || vectBall[Vector2D.D_LEFT_Y.ordinal()] >= 574){
				vectBall[Vector2D.SPEED_Y.ordinal()] *= -1;
			}
		}
		else{
			if (vectBall[Vector2D.U_RIGHT_X.ordinal()] >= vectRaket2[Vector2D.U_LEFT_X.ordinal()]){
				if((vectBall[Vector2D.U_RIGHT_Y.ordinal()] <= vectRaket2[Vector2D.D_LEFT_Y.ordinal()]) &&
						(vectBall[Vector2D.D_RIGHT_Y.ordinal()] >= vectRaket2[Vector2D.U_LEFT_Y.ordinal()])){
					vectBall[Vector2D.SPEED_X.ordinal()] *= -1;
				}
				if(vectBall[Vector2D.U_LEFT_X.ordinal()] >= 776){
					player1W = true;
					inGame = false;
				}
			}
			if(vectBall[Vector2D.U_LEFT_Y.ordinal()] <= 0 || vectBall[Vector2D.D_LEFT_Y.ordinal()] >= 574){
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(inGame){
			moveRaket1();
			moveRaket2();
			moveBall();
		}
		repaint();
	}
}