package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {

    int heal;

    public OBJ_Potion(GamePanel gp) {
        super(gp);

        type = typeConsumable;

        name = "Potion";
        heal = 5;
        description = "[" + name + "]\nHeals your life by " + heal + ".";
        down1 = setup("/objects/potion_red");
        stackable = true;

        price = 25;
    }

    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        if(entity.life == maxLife) {
            gp.ui.currentDialogue = "Your life is full!";
            return false;
        }

        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
            "Your life has been recovered by " + heal + ".";
        entity.life += heal;

        gp.playSoundEffect(2);

        return true;
    }
}
