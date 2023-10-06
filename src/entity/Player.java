package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_ShieldWood;
import object.OBJ_SwordNormal;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        type = typePlayer;

        screenX = gp.screenWidth/2 - (gp.tileSize / 2);
        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }

    public void setDefaultValues() {

        setDefaultPositions();
        defaultSpeed = 4;
        speed = defaultSpeed;

        // Player Status
        level = 1;

        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;

        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 500;
        currentWeapon = new OBJ_SwordNormal(gp);
        currentShield = new OBJ_ShieldWood(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();
    }

    public void setDefaultPositions() {

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        gp.currentMap = 0;
        direction = "down";
    }

    private int getAttack() {
        attackArea = currentWeapon.attackArea;
        return strength * currentWeapon.attackValue;
    }

    private int getDefense() {
        return dexterity * currentShield.defenseValue;
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");
    }

    public void getPlayerAttackImage() {

        if(currentWeapon.type == typeSword) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }

        if(currentWeapon.type == typeAxe) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }
    }

    public void update() {

        if (attacking) {
            attacking();
        } else if (keyH.upPressed || keyH.downPressed
                || keyH.leftPressed || keyH.rightPressed
                || keyH.enterPressed) {

            if (keyH.upPressed)
                direction = "up";

            if (keyH.downPressed)
                direction = "down";

            if (keyH.leftPressed)
                direction = "left";

            if (keyH.rightPressed)
                direction = "right";

            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkEntity(this, gp.iTile);

            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            interactObject(objIndex);
            pickUpObject(objIndex);

            // Check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // Check event
            gp.eHandler.checkEvent();

            // if collision is false, player can move
            if (!collisionOn && !keyH.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if (keyH.enterPressed && !attackCanceled) {
                gp.playSoundEffect(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.enterPressed = false;

            spriteCounter++;

            if (spriteCounter > 12) {

                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }

        if (gp.keyH.shotKeyPressed && !projectile.alive
                && shotAvailableCounter == 30 && projectile.haveResource(this)) {

            // Set default coordinates, direction and user
            projectile.set(worldX, worldY, direction, true, this);

            // Subtract the cost (Mana, AMMO)
            projectile.subtractResource(this);

            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailableCounter = 0;

            gp.playSoundEffect(10);
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if (life > maxLife) {
            life = maxLife;
        }

        if (mana > maxMana) {
            mana = maxMana;
        }

        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.player.invincible = false;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSoundEffect(12);
        }
    }

    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= 5) {
            spriteNum = 1;
        }

        if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // Save the current data
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea

            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            // attackArea become solidArea
            solidArea.width = attackArea.width;
            solidArea.height= attackArea.height;

            // Check monster collision with the updated data
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex, attack, currentWeapon.knockBackPower);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndex);

            int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
            damageProjectile(projectileIndex);

            // Restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if(spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public boolean inventoryIsFull() {
        return inventory.size() == maxInventorySize;
    }

    public void pickUpObject(int i) {

        if(i != 999) {
            if(gp.obj[gp.currentMap][i].type != typeObstacle) {
                // Pickup only items
                if (gp.obj[gp.currentMap][i].type == typePickup) {

                    gp.obj[gp.currentMap][i].use(this);

                } else {

                    // Inventory items

                    String text;

                    if (!inventoryIsFull()) {

                        inventory.add(gp.obj[gp.currentMap][i]);
                        gp.playSoundEffect(1);
                        text = "Got a " + gp.obj[gp.currentMap][i].name + "!";

                    } else {
                        text = "You cannot carry any more!";
                    }

                    gp.ui.addMessage(text);
                }

                gp.obj[gp.currentMap][i] = null;
            }
        }
    }

    public void interactObject(int i) {
        if(i != 999) {
            attackCanceled = true;
            if(gp.obj[gp.currentMap][i].type == typeObstacle) {
                if(keyH.enterPressed) {
                    gp.obj[gp.currentMap][i].interact();
                }
            }
        }
    }

    public void interactNPC(int i) {

        if(gp.keyH.enterPressed) {
            if(i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }

    private void contactMonster(int i) {

        if(i != 999) {

            if(!invincible && !gp.monster[gp.currentMap][i].dying) {
                gp.playSoundEffect(6);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 0) {
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack, int knockBackPower) {

        if(i != 999) {

            if(!gp.monster[gp.currentMap][i].invincible) {
                gp.playSoundEffect(5);

                if(knockBackPower > 0) {
                    knockBack(gp.monster[gp.currentMap][i], knockBackPower);
                }

                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0) {
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                if(gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].life = 0;
                }
                gp.ui.addMessage(damage + " damage!");

                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("+ " + gp.monster[gp.currentMap][i].exp + " exp");
                    exp += gp.monster[gp.currentMap][i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void knockBack(Entity entity, int knockBackPower) {

        entity.direction = direction;
        entity.speed += knockBackPower;
        entity.knockBack = true;
    }

    public void damageInteractiveTile(int i) {

        if(i != 999 && gp.iTile[gp.currentMap][i].destructible
                && gp.iTile[gp.currentMap][i].itemIsCorrect(this) && !gp.iTile[gp.currentMap][i].invincible) {

            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
            }
        }
    }

    public void damageProjectile(int i) {

        if(i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }

    public void checkLevelUp() {

        if(exp >= nextLevelExp) {

            level++;
            nextLevelExp *= 2;
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSoundEffect(8);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n"
                    + "You feel stronger!";
        }
    }

    public void selectItem() {

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if(itemIndex < inventory.size()) {

            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == typeSword || selectedItem.type == typeAxe) {
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }

            if(selectedItem.type == typeShield) {
                currentShield = selectedItem;
                defense = getDefense();
            }

            if(selectedItem.type == typeConsumable) {
                if(selectedItem.use(this)) {
                    inventory.remove(itemIndex);
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                }
                if(attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) image = attackUp1;
                    if (spriteNum == 2) image = attackUp2;
                }
            }
            case "down" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                }
                if(attacking) {
                    if (spriteNum == 1) image = attackDown1;
                    if (spriteNum == 2) image = attackDown2;
                }
            }
            case "left" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                }
                if(attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    if (spriteNum == 1) image = attackLeft1;
                    if (spriteNum == 2) image = attackLeft2;
                }
            }
            case "right" -> {
                if(!attacking) {
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                }
                if(attacking) {
                    if (spriteNum == 1) image = attackRight1;
                    if (spriteNum == 2) image = attackRight2;
                }
            }
        }

        // invincible opacity
        if(invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
