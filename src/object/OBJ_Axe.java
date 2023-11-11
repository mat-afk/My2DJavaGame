package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public final static String OBJ_NAME = "Woodcutter's Axe";

    public OBJ_Axe(GamePanel gp) {
        super(gp);

        type = typeAxe;

        name = OBJ_NAME;
        description = "[" + name + "]\nProbably from Canada.\nCan cut some trees.";
        down1 = setup("/objects/axe");

        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        knockBackPower = 10;
        motion1Duration = 15;
        motion2Duration = 35;

        price = 75;
    }
}
