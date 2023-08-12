package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SwordNormal extends Entity {

    public OBJ_SwordNormal(GamePanel gp) {
        super(gp);

        type = typeSword;

        name = "Normal Sword";
        description = "[" + name + "]\nAn old sword.";

        down1 = setup("/objects/sword_normal");

        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;

        price = 20;
    }
}
