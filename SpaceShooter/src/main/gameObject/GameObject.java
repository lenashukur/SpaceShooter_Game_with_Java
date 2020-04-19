package main.gameObject;

import main.utils.BoxCollider;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class GameObject {
	BoxCollider collider;
	PApplet parent;
	PVector pos;
	PImage img;
	String tag;
	public float health;

	public GameObject(PApplet parent, String tag, PVector pos, PImage img, float health) {
		this.parent = parent;
		this.tag = tag;
		this.pos = pos;
		this.img = img;
		this.health = health;
	}

	public void render() {
		parent.image(img, pos.x, pos.y);
	}

	public void update() {
	}
}
