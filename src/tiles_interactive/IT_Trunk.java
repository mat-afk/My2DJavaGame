package tiles_interactive;

import main.GamePanel;

public class IT_Trunk extends InteractiveTile {

    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);

        down1 = setup("/tiles_interactive/trunk");

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}
