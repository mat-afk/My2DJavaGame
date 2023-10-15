package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {

    public OBJ_Tent(GamePanel gp) {
        super(gp);

        name = "Tent";
        type = typeConsumable;
        down1 = setup("/objects/tent");
        description = "[Tent] \nYou can sleep until \nnext morning.";
        price = 300;
    }

    public boolean use(Entity entity) {

        gp.gameState = gp.sleepState;
        gp.playSoundEffect(14);
        gp.player.restoreLifeAndMana();
        gp.player.getSleepingImage(down1);
        return true;
    }
}
