package main.gameObject;

import main.SpaceShooter;
import main.utils.BoxCollider;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Enemy extends GameObject {
	float speed;
	float cooldownMax = 5000.0f;
	float cooldownMin = 1000.0f;
	float leftTime;

	public Enemy(PApplet parent, String tag, PImage img, float x, float y, float speed) {
		super(parent, tag, new PVector(x, y), img, 2);
		this.speed = speed;
		collider = new BoxCollider(parent, pos, new PVector(img.width, img.height));
		leftTime = parent.random(cooldownMin, cooldownMax);
	}

	@Override
	public void update() {
		leftTime -= SpaceShooter.deltaTime;
		boolean colEnemy = false;
		for (Enemy e : SpaceShooter.enemies) {
			if (e != this && collider.isCollided(e.collider))
				colEnemy = true;
		}
		if (pos.y < parent.height / 2 && !colEnemy) {
			pos.y += speed * SpaceShooter.deltaTime;
			if (pos.y > parent.height / 2)
				pos.y = parent.height / 2;
		}
		collider.moveCollider(pos);
		shoot();
		for (Laser l : SpaceShooter.lasers) {
			if (collider.isCollided(l.collider) && l.tag != tag) {
				health -= l.damage;
				l.health--;
			}
		}
	}

	public void shoot() {
		if (leftTime <= 0) {
			Laser laser = new Laser(parent, tag, SpaceShooter.enemyLaserImage,
					pos.x + img.width / 2 - SpaceShooter.enemyLaserImage.width / 2,
					pos.y + SpaceShooter.enemyLaserImage.height * 0.75f, 2);
			SpaceShooter.lasers.add(laser);

			leftTime = parent.random(cooldownMin, cooldownMax);
		}
	}
}
