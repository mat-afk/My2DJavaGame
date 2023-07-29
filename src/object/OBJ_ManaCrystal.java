package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);

        name = "Mana Crystal";
        image = setup("/objects/manacrystal_full");
        image2 = setup("/objects/manacrystal_blank");
    }
}
