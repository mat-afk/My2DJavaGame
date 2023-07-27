package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ShieldWood extends Entity {

    public OBJ_ShieldWood(GamePanel gp) {
        super(gp);

        type = typeShield;

        name = "Wood Shield";
        description = "[" + name + "]\nMade by wood.";
        down1 = setup("/objects/shield_wood");

        defenseValue = 1;
    }
}
