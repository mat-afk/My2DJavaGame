package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/objects/key");
    }
}
