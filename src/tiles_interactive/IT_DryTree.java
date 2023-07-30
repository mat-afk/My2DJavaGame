package tiles_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile {

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);

        down1 = setup("/tiles_interactive/drytree");
        destructible = true;
        life = 3;
    }

    public boolean itemIsCorrect(Entity entity) {

        return entity.currentWeapon.type == typeAxe;
    }

    public void playSE() {
        gp.playSoundEffect(11);
    }

    public InteractiveTile getDestroyedForm() {
        return new IT_Trunk(gp, worldX/ gp.tileSize, worldY / gp.tileSize);
    }
}
