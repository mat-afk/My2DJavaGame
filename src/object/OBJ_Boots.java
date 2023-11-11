package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity {

    public final static String OBJ_NAME = "Boots";

    public OBJ_Boots(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        down1 = setup("/objects/boots");
    }
}
