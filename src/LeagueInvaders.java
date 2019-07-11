import javax.swing.*;


public class LeagueInvaders {
	JFrame frame = new JFrame();
	static final int WIDTH = 500;
	static final int HEIGHT = 800;
	GamePanel panel = new GamePanel();
	LeagueInvaders(JFrame frame){
		this.frame = frame;	
	}
	void setup() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);
	}
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		LeagueInvaders game = new LeagueInvaders(frame);
		game.setup();
	}


}
