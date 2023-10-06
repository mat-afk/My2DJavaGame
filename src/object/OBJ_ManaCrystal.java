package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    int mana;

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);

        type = typePickup;

        name = "Mana Crystal";
        mana = 1;

        down1 = setup("/objects/manacrystal_full");
        image = setup("/objects/manacrystal_full");
        image2 = setup("/objects/manacrystal_blank");
    }

    public boolean use(Entity entity) {

        gp.playSoundEffect(2);

        gp.ui.addMessage("Mana +" + mana);
        entity.mana += mana;

        return true;
    }
}
