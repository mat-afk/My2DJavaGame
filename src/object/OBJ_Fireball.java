package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    public OBJ_Fireball(GamePanel gp) {
        super(gp);

        name = "Fireball";

        speed = 8;
        maxLife = 80;
        life = maxLife;

        attack = 2;
        useCost = 1;
        alive = false;

        getImage();
    }

    public void getImage() {
        up1 = setup("/projectile/fireball_up_1");
        up2 = setup("/projectile/fireball_up_2");
        down1 = setup("/projectile/fireball_down_1");
        down2 = setup("/projectile/fireball_down_2");
        left1 = setup("/projectile/fireball_left_1");
        left2 = setup("/projectile/fireball_left_2");
        right1 = setup("/projectile/fireball_right_1");
        right2 = setup("/projectile/fireball_right_2");
    }

    public boolean haveResource(Entity user) {
        return user.mana >= useCost;
    }

    public void substractResource(Entity user) {
        user.mana -= useCost;
    }

    public Color getParticleColor() {
        return new Color(240, 50, 0);
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
