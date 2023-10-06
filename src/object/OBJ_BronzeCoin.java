package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BronzeCoin extends Entity {

    int value;

    public OBJ_BronzeCoin(GamePanel gp) {
        super(gp);

        type = typePickup;
        name = "Bronze Coin";
        value = 1;
        down1 = setup("/objects/coin_bronze");
    }

    public boolean use(Entity entity) {

        gp.playSoundEffect(1);
        gp.ui.addMessage("Coin +" + value);
        gp.player.coin += value;

        return true;
    }
}
