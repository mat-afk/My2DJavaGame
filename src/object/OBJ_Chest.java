package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp) {
        super(gp);

        name = "Chest";
        image = setup("/objects/chest");
        image2 = setup("/objects/chest_opened");
        down1 = image;
        type = typeObstacle;

        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    @Override
    public void setLoot(Entity loot) {
        this.loot = loot;
    }

    @Override
    public void interact() {
        gp.gameState = gp.dialogueState;

        if(!opened) {
            gp.playSoundEffect(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest and found a ").append(loot.name).append(".");

            if(!gp.player.canObtainItem(loot)) {
                sb.append("\n...But you cannot carry more items");
            } else {
                sb.append("\nYou got a ").append(loot.name).append("!");
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();

        } else {
            gp.ui.currentDialogue = "It's empty";
        }
    }
}
