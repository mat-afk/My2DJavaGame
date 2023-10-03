package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_BronzeCoin;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gp) {
        super(gp);

        type = typeMonster;
        name = "Green Slime";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;

        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 40;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMonsterImage();
    }

    public void getMonsterImage() {
        up1 = setup("/monster/greenslime_down_1");
        up2 = setup("/monster/greenslime_down_2");
        down1 = setup("/monster/greenslime_down_1");
        down2 = setup("/monster/greenslime_down_2");
        left1= setup("/monster/greenslime_down_1");
        left2 = setup("/monster/greenslime_down_2");
        right1 = setup("/monster/greenslime_down_1");
        right2 = setup("/monster/greenslime_down_2");
    }

    public void setAction() {
        actionLockCounter++;

        Random random;
        if (actionLockCounter == 120) {
            random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25)
                direction = "up";
            if (i > 25 && i <= 50)
                direction = "down";
            if (i > 50 && i <= 75)
                direction = "left";
            if (i > 75)
                direction = "right";

            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100) + 1;

        if(i > 99 && !projectile.alive && shotAvailableCounter == 30) {
            projectile.set(worldX, worldY, direction, true, this);

            for(int j = 0; j < gp.projectile[1].length; j++) {
                if(gp.projectile[gp.currentMap][j] == null) {
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        direction = gp.player.direction;
    }

    public void checkDrop() {

        // Cast a die
        int i = new Random().nextInt(100) + 1;

        // Set the monster drop
        if(i < 50) {
            dropItem(new OBJ_BronzeCoin(gp));
        }

        if(i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }

        if(i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
