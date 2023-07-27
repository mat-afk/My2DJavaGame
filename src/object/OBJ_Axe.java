package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = typeAxe;

        name = "Woodcutter's Axe";
        description = "[" + name + "]\nProbably from Canada.\nCan cut some trees.";
        down1 = setup("/objects/axe");

        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
    }
}
