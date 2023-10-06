package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity {

    public OBJ_Door(GamePanel gp) {
        super(gp);

        name = "Door";
        down1 = setup("/objects/door");
        type = typeObstacle;

        collision = true;

        solidArea.x = 0;
        solidArea.y = 12;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void interact() {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You need a key to open this!";
    }
}
