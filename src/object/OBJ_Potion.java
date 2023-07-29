package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {

    int heal;

    public OBJ_Potion(GamePanel gp) {
        super(gp);

        type = typeConsumable;

        name = "Potion";
        description = "[" + name + "]\nHeals your life by " + heal + ".";
        heal = 5;
        down1 = setup("/objects/potion_red");
    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
            "Your life has been recovered by " + heal + ".";
        entity.life += heal;

        gp.playSoundEffect(2);
    }
}
