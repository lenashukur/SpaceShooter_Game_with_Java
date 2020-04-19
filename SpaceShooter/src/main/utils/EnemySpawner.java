package main.utils;

import main.SpaceShooter;
import main.gameObject.Enemy;
import processing.core.PApplet;

public class EnemySpawner {
	public PApplet parent;
	public float max, min;
	public float leftTime;
	public int enemyCounter;
	public float w;

	public EnemySpawner(PApplet parent, float max, float min, int maxEnemy) {
		this.parent = parent;
		this.max = max;
		this.min = min;
		enemyCounter = maxEnemy;
		leftTime = parent.random(min, max);
		w = parent.width / 9.0f;
	}

	public void update() {
		leftTime -= SpaceShooter.deltaTime;

		if (leftTime <= 0 && enemyCounter > 0) {
			Enemy enemy = new Enemy(parent, SpaceShooter.tag_enemy, SpaceShooter.enemyImage,
					(PApplet.floor(parent.random(0, 9)) + 0.5f) * w - SpaceShooter.enemyImage.width / 2,
					-SpaceShooter.enemyImage.height, 1f);
			SpaceShooter.enemies.add(enemy);
			enemyCounter--;
			leftTime = parent.random(min, max);
		}
	}

}
