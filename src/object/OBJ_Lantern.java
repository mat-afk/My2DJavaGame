package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        name = "Lantern";
        type = typeLight;
        down1 = setup("/objects/lantern");
        description = "[Lantern]\nIlluminates your \nsurroundings";

        price = 200;
        lightRadius = 250;
    }
}
