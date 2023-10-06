package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);

        name = "Key";
        description = "[" + name + "]\nIt opens a door.";
        down1 = setup("/objects/key");
        type = typeConsumable;

        price = 100;
    }

    @Override
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        int objIndex = getDetected(entity, gp.obj, "Door");

        if(objIndex != 999) {
            gp.ui.currentDialogue = "You used the " + name + " to open the door";
            gp.playSoundEffect(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }
        else {
            gp.ui.currentDialogue = "It is not time for that.";
            return false;
        }
    }
}
