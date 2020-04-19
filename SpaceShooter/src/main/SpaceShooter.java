package main;

import java.util.ArrayList;

import main.gameObject.Enemy;
import main.gameObject.Laser;
import main.gameObject.Player;
import main.gui.InGameUI;
import main.utils.EnemySpawner;
import processing.core.PApplet;
import processing.core.PImage;

public class SpaceShooter extends PApplet {
	public static void main(String[] args) {
		PApplet.main("main.SpaceShooter");
	}

	public void settings() {
		size(1366, 768);
	}

	public static final String tag_player = "player";
	public static final String tag_enemy = "enemy";

	public static PImage bg, playerImage, enemyImage, playerLaserImage, enemyLaserImage;
	public static float deltaTime;

	public static Player player;
	public static ArrayList<Laser> lasers;
	public static ArrayList<Enemy> enemies;
	public static EnemySpawner spawner;
	public static InGameUI inGameUI;

	public void setup() {
		size(1366, 768);

		bg = loadImage("res/bg.png");
		playerImage = loadImage("res/playerShip.png");
		playerLaserImage = loadImage("res/laser1.png");
		enemyImage = loadImage("res/enemy1.png");
		enemyLaserImage = loadImage("res/laserE1.png");

		player = new Player(this, tag_player, playerImage, width / 2 - playerImage.width / 2,
				height - playerImage.height, 1.5f);
		lasers = new ArrayList<Laser>();
		enemies = new ArrayList<Enemy>();
		spawner = new EnemySpawner(this, 3000, 1000, 15);

		inGameUI = new InGameUI(this);

		t1 = millis();
	}

	float t1, t2;

	public void draw() {
		background(0);
		t2 = millis();
		deltaTime = (t2 - t1);

		// Game Scene
		wrap(bg, 0, 0, width, height);

		// Update
		player.update();
		for (int i = enemies.size() - 1; i >= 0; i--) {
			if (enemies.get(i).health <= 0) {
				enemies.remove(i);
			} else {
				enemies.get(i).update();
			}
		}
		for (int i = lasers.size() - 1; i >= 0; i--) {
			if (lasers.get(i).health <= 0) {
				lasers.remove(i);
			} else {
				lasers.get(i).update();
			}
		}
		spawner.update();

		// Render
		for (Laser l : lasers)
			l.render();
		for (Enemy e : enemies)
			e.render();
		player.render();

		inGameUI.render();

		t1 = millis();
	}

	void wrap(PImage img, float x1, float y1, float x2, float y2) {
		for (float x = x1; x < x2; x += img.width) {
			for (float y = y1; y < y2; y += img.height) {
				image(img, x, y);
			}
		}
	}

	public static boolean[] keys = new boolean[5]; // W/UP, A/LEFT, S/DOWN, D/RIGHT, SPACE

	public void keyPressed() {
		if (key == 'w' || key == 'W' || keyCode == UP)
			keys[0] = true;
		if (key == 'a' || key == 'A' || keyCode == LEFT)
			keys[1] = true;
		if (key == 's' || key == 'S' || keyCode == DOWN)
			keys[2] = true;
		if (key == 'd' || key == 'D' || keyCode == RIGHT)
			keys[3] = true;
		if (key == ' ')
			keys[4] = true;
	}

	public void keyReleased() {
		if (key == 'w' || key == 'W' || keyCode == UP)
			keys[0] = false;
		if (key == 'a' || key == 'A' || keyCode == LEFT)
			keys[1] = false;
		if (key == 's' || key == 'S' || keyCode == DOWN)
			keys[2] = false;
		if (key == 'd' || key == 'D' || keyCode == RIGHT)
			keys[3] = false;
		if (key == ' ')
			keys[4] = false;
	}
}
