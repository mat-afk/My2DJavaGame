package main;

import npc.NPC_Merchant;
import npc.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;
import tiles_interactive.IT_DryTree;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 33;
        gp.obj[mapNum][i].worldY = gp.tileSize * 7;
    }

    public void setNPC() {

        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 21;

        mapNum = 1;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;
    }

    public void setMonster() {

        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 21;
        gp.monster[mapNum][i].worldY = gp.tileSize * 38;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;

    }

    public void setInteractiveTile() {

        int mapNum = 0;

        // Right block

        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 21);

        // Axe way

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12);

        // Hut way

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 40);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 40);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41);

        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 40);
    }
}
