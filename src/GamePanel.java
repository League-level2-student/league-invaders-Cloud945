import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font regular = new Font("Arial", Font.PLAIN, 24);
	int score;
	Rocketship rocket = new Rocketship(250,700,50,50);
	ObjectManager manager = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static Timer alienSpawn;
	
	
	
	GamePanel(){
		Timer frameDraw = new Timer (1000/60, this);
		frameDraw.start();
		if (needImage) {
		    loadImage ("space.png");
		}
	}
	
	void updateMenuState(){
		
	}
	void updateGameState() {
		manager.update();
		if(rocket.isActive==false) {
			currentState=END;
		}
	}
	void updateEndState() {
		
	}
	void drawMenuState(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 100);
		g.setFont(regular);
		g.drawString("Press ENTER to start", 135, 400);
		g.drawString("Press SPACE for instructions", 100, 550);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT,null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		manager.draw(g);
		
		
		
		
	}
	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 225);
		g.setFont(regular);
		g.drawString("You killed "+ score + " enemies", 125, 350);
		g.drawString("Press ENTER to restart", 125, 500);
		
		
		
		
	}
	void startGame() {
		
		alienSpawn = new Timer(1000, manager);
		alienSpawn.start();
		
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		System.out.println("action");
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		        if (currentState==GAME) {
		        	startGame();
		        }else if(currentState==END) {
		        	alienSpawn.stop();
		        }
		        
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_UP) {
		    if(rocket.y>0) {
		    	rocket.up();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
		    if (rocket.y<725) {
				rocket.down();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
		    if(rocket.x<450) {
		    	rocket.right();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
		    if(rocket.x>0) {
				rocket.left();
		    }
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			manager.addProjectile(rocket.getProjectile());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
