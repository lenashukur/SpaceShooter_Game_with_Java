class Player {
  PImage img;
  PVector pos;
  float speed;

  float cooldown = 100;
  float leftTime;

  Player(PImage img, float x, float y, float speed) {
    this.img = img;
    pos = new PVector(x, y);
    this.speed = speed;
    leftTime = 0;
  }

  void render() {
    image(img, pos.x, pos.y);
  }

  void update() {
    if(leftTime > 0) leftTime-=deltaTime;
    
    if (keys[0]) pos.y -= speed*deltaTime;
    if (keys[2]) pos.y += speed*deltaTime;
    if (keys[1]) pos.x -= speed*deltaTime;
    if (keys[3]) pos.x += speed*deltaTime;
    
    if(pos.x < 0) pos.x = 0;
    else if(pos.x + img.width > width) pos.x = width-img.width;
    
    if(pos.y < 0) pos.y = 0;
    else if(pos.y + img.height > height) pos.y = height-img.height;
    
    if(keys[4]) shoot();
  }
  
  void shoot() {
    if(leftTime <= 0){
      Laser laser = new Laser(playerLaserImage, pos.x+img.width/2-playerLaserImage.width/2, pos.y-playerLaserImage.height*0.75, -2);
      lasers.add(laser);
      leftTime = cooldown;
    }
  }
}
