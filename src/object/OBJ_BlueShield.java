package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueShield extends Entity {

    public OBJ_BlueShield(GamePanel gp) {
        super(gp);

        type = typeShield;

        name = "Blue Shield";
        description = "[" + name + "]\nA shiny blue shield.\nVery blue.";
        down1 = setup("/objects/shield_blue");

        defenseValue = 2;
    }
}
