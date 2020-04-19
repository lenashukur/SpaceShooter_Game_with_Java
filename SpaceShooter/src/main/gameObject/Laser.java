package main.gameObject;

import main.SpaceShooter;
import main.utils.BoxCollider;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Laser extends GameObject {
	float speed;
	float damage = 1;

	public Laser(PApplet parent, String tag, PImage img, float x, float y, float speed) {
		super(parent, tag, new PVector(x, y), img, 1);
		this.speed = speed;
		collider = new BoxCollider(parent, pos, new PVector(img.width, img.height));
	}

	public void update() {
		pos.y += speed * SpaceShooter.deltaTime;
		collider.moveCollider(pos);

		for (Laser l : SpaceShooter.lasers) {
			if (collider.isCollided(l.collider) && l.tag != tag) {
				health -= l.damage;
			}
		}
	}

}
