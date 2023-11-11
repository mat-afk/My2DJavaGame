package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public final static String OBJ_NAME = "Lantern";

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        name = OBJ_NAME;
        type = typeLight;
        down1 = setup("/objects/lantern");
        description = "[Lantern]\nIlluminates your \nsurroundings";

        price = 200;
        lightRadius = 250;
    }
}
