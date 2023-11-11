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

        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You need a key to open this!";
    }

    @Override
    public void interact() {

        startDialogue(this, 0);
    }
}
