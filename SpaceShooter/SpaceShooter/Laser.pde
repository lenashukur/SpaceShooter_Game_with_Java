class Laser {
  PImage img;
  PVector pos;
  float speed;

  float damage;
  String tag;

  Laser(PImage img, float x, float y, float speed) {
    this.img = img;
    pos = new PVector(x, y);
    this.speed = speed;
  }

  void render() {
    image(img, pos.x, pos.y);
  }

  void update() {
    pos.y += speed*deltaTime;
  }  
}
