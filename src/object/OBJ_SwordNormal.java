package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_SwordNormal extends Entity {

    public OBJ_SwordNormal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/objects/sword_normal");

        attackValue = 1;
    }
}
