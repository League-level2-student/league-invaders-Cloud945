import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class ObjectManager implements ActionListener{
	Rocketship rocket;
	ArrayList <Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList <Alien> aliens = new ArrayList<Alien>();
	Random random =new Random();
	
	ObjectManager(Rocketship rocket){
		this.rocket = rocket;
	}
	void addProjectile(Projectile projectile){
		projectiles.add(projectile);
	}
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	void alienMethod() {
		for(int i = 0; i<aliens.size()-1;i++) {
			Alien a = aliens.get(i);
			a.update();
			if (a.y>LeagueInvaders.HEIGHT || a.y<0) {
				a.isActive = false;
			}
			else {
				a.isActive = true;
			}
		}
	}
	void projectileMethod() {
		for(int i = 0; i<projectiles.size()-1;i++) {
			Projectile p = projectiles.get(i);
			p.update();
			if (p.y>LeagueInvaders.HEIGHT || p.y<0) {
				p.isActive = false;
			}
			
		}
	}
	void update() {
		for(int i = 0; i<aliens.size()-1;i++) {
			Alien a = aliens.get(i);
			a.update();
		}
		for(int i = 0; i<projectiles.size()-1;i++) {
			Projectile p = projectiles.get(i);
			p.update();
		}
		checkCollision();
		purgeObjects();
		
	}
	void draw(Graphics g) {
		rocket.draw(g);
		for(int i = 0; i<aliens.size()-1;i++) {
			Alien a = aliens.get(i);
			a.draw(g);
		}
		for(int i = 0; i<projectiles.size()-1;i++) {
			Projectile p = projectiles.get(i);
			p.draw(g);
		}
	}
	void purgeObjects() {
		for(int i = 0; i<aliens.size()-1;i++) {
			Alien a = aliens.get(i);
			if(a.isActive==false) {
				aliens.remove(i);
			}
		}
		for(int i = 0; i<projectiles.size()-1;i++) {
			Projectile p = projectiles.get(i);
			if(p.isActive==false) {
				projectiles.remove(i);
			}
		}
	}
	void checkCollision() {
		for(int i = 0; i<aliens.size()-1;i++) {
			Alien a = aliens.get(i);
			if(rocket.collisionBox.intersects(a.collisionBox)) {
				rocket.isActive = false;
				a.isActive = false;
			}
			for(int j = 0; j<projectiles.size()-1;j++) {
				Projectile p = projectiles.get(j);
				if(p.collisionBox.intersects(a.collisionBox)) {
					p.isActive = false;
					a.isActive = false;
				}
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==GamePanel.alienSpawn) {
			addAlien();
		}
		
	}
	
}
