package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    public OBJ_Rock(GamePanel gp) {
        super(gp);

        name = "Rock";

        speed = 5;
        maxLife = 40;
        life = maxLife;

        attack = 2;
        knockBackPower = 0;
        useCost = 1;
        alive = false;

        getImage();
    }

    public void getImage() {

        up1 = setup("/projectile/rock_down_1");
        up2 = setup("/projectile/rock_down_1");
        down1 = setup("/projectile/rock_down_1");
        down2 = setup("/projectile/rock_down_1");
        left1 = setup("/projectile/rock_down_1");
        left2 = setup("/projectile/rock_down_1");
        right1 = setup("/projectile/rock_down_1");
        right2 = setup("/projectile/rock_down_1");
    }

    public boolean haveResource(Entity user) {
        return user.ammo >= useCost;
    }

    public Color getParticleColor() {
        return new Color(40, 50, 0);
    }

    public int getParticleSize() {
        return 10;
    }

    public int getParticleSpeed() {
        return 1;
    }

    public int getParticleMaxLife() {
        return 20;
    }
}
