package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity {

    int heal = 5;

    public OBJ_Potion(GamePanel gp) {
        super(gp);

        type = typeConsumable;

        name = "Potion";
        description = "[" + name + "]\nHeals your life by " + heal + ".";
        down1 = setup("/objects/potion_red");
    }

    public void use(Entity entity) {

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You drink the " + name + "!\n" +
            "Your life has been recovered by " + heal + ".";
        entity.life += heal;

        if(gp.player.life > gp.player.maxLife) {
            gp.player.life = gp.player.maxLife;
        }

        gp.playSoundEffect(2);
    }
}
