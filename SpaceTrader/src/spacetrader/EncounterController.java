package spacetrader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import spacetrader.SpaceTrader.ControlledScreen;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import java.util.Random;
import java.util.HashMap;

public class EncounterController implements ControlledScreen, Initializable {
    ScreensController controller;
    private SoundManager soundManager = SoundManager.getSoundManager();
    
    @FXML private ToggleButton turret;
    @FXML private ToggleButton cannon; 
    @FXML private ToggleButton laser;
    @FXML private ToggleGroup group;
    @FXML private Button fire;
    @FXML private Button guard;
    @FXML private Button flee;
    @FXML private TextArea combatLog;
    @FXML private Text playerHP;
    @FXML private Text enemyHP;
    @FXML private Text turnCount;
    @FXML private ProgressBar playerHPBar;
    @FXML private ProgressBar enemyHPBar;
    @FXML private AnchorPane firstPane;
    @FXML private AnchorPane secondPane;
    @FXML private AnchorPane thirdPane;
    @FXML private AnchorPane lastPane;
    @FXML private Text endText;
    @FXML private Button endButton;
    private Random random;
    private HashMap weapons;
    private HashMap shields;
    private Weapon turretWeapon;
    private Weapon cannonWeapon;
    private Weapon laserWeapon;
    private int playerTurnDamage;
    private int enemyTurnDamage;
    private int numPlayerHP;
    private int numEnemyHP;
    private int maxPlayerHP;
    private int maxEnemyHP;
    private int turnCounter;
    private double playerAcc;
    private double enemyAcc;
    private int[] stats;
    private Ship playerShip;
    private Ship enemyShip;
    private String[] quips = new String[]{"Your enemy is so evil that even the space around him has turned red...",//0
                                            "The enemy is making strange faces at you.", "Hey! The enemy made an obscene hand gesture!",//1, 2
                                            "The enemy seems distracted by their phone.", "Your stomach grumbles. Not the time!",//3, 4
                                            "Enemy transmission: ill rek u m8", "Sweat pours from your brow...things are looking dire!",//5, 6
                                            "Yeah, they're on the ropes!", "The enemy doesn't have much left...this is it!",//7, 8
                                            "You're never going to make it at this rate!", "The enemy looks bored.", "You smile at the enemy."//9,10,11
                                            + " Kill them with kindness?"};//11 cont.
    private String playerLastMove;
    private String enemyLastMove;
    private boolean isGuarding, isFleeing;
    
    /**
     * Set the screen parent.
     * @param screenParent the screen parent
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controller = screenParent;
    }
    
    public Ship chooseEnemy() {
        Ship[] choices = new Ship[]{new Ship("gnat"), new Ship("firefly"),
            new Ship("mosquito"), new Ship("bumblebee")};
        Ship choice = choices[random.nextInt(4)];
        System.out.println("enemy ship type: " + choice.getType());
        return choice;
    }
    
    public void availableWeapons() {
        //turret.setDisable(true);
        //cannon.setDisable(true);
        //laser.setDisable(true);
        for (Weapon w : playerShip.getWeapons()) {
            if (w.getName().equals("Turret")) {
                turret.setDisable(false);
            } else if (w.getName().equals("Dual Cannon")) {
                cannon.setDisable(false);
            } else {
                laser.setDisable(false);
            }
        }
    }
    
    public void initHP() {
        numPlayerHP = playerShip.getHullStrength();
        for (Shield s : playerShip.getShields()) {
            numPlayerHP += s.getHealth();
        }
        playerHP.setText("HP: " + numPlayerHP);
        numEnemyHP = enemyShip.getHullStrength();
        for (Shield s : enemyShip.getShields()) {
            numEnemyHP += s.getHealth();
        }
        enemyHP.setText("HP: " + numEnemyHP);
        maxPlayerHP = numPlayerHP;
        maxEnemyHP = numEnemyHP;
    }
    
    public void updateHP(boolean isPlayer, int damage) {
        if (isPlayer) {
            if (numPlayerHP - damage < 0) {
                numPlayerHP = 0;
            } else {
                numPlayerHP -= damage;
            }
            playerHP.setText("HP: " + numPlayerHP);
            playerHPBar.setProgress((float) (numPlayerHP / maxPlayerHP));
        } else {
            if (numEnemyHP - damage < 0) {
                numEnemyHP = 0;
            } else {
                numEnemyHP -= damage;
            }
            enemyHP.setText("HP: " + numEnemyHP);
            enemyHPBar.setProgress((float) (numEnemyHP / maxEnemyHP));
        }
    }
    
    private int getRandom(int lBound, int hBound) {
        int rand = random.nextInt(hBound + 1);
        while (!(rand >= lBound) || !(rand <= hBound)) {
            rand = random.nextInt(hBound + 1);
        }
        return rand;
    }
    
    public String generateQuip() {
        String quip = "";
        int choice = 0;
        if (numPlayerHP < maxPlayerHP / 4 && numEnemyHP < maxEnemyHP / 4) {
            choice = getRandom(6, 9);
        } else if (numPlayerHP < maxPlayerHP / 4) {
            int tempRand = random.nextInt(2);
            if (tempRand == 0) {
                choice = 6;
            } else {
                choice = 9;
            }
        } else if (numEnemyHP < maxEnemyHP / 4) {
          choice = getRandom(7, 8);  
        } else {
            int choice1 = getRandom(1, 5);
            int choice2 = getRandom(10, 11);
            int decider = getRandom(0, 1);
            if (decider == 0) {
                choice = choice1;
            } else {
                choice = choice2;
            }
        }
        return quips[choice];
    }
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {
        soundManager.setPrevScreen("Encounter");
        soundManager.setCurrentBG(SoundManager.ENCOUNTER_BG_ID);
        soundManager.playBGWithCheck(SoundManager.ENCOUNTER_BG_ID, SoundManager.ENCOUNTER_BG_PATH);
        combatLog.setText(quips[0]);
        stats = Context.getInstance().getPlayer().getStats();
        //stats = new int[]{5, 5, 5, 5, 5};
        Context.getInstance().getPlayer().setShip(new Ship("bumblebee"));
        random = new Random();
        playerShip = Context.getInstance().getPlayer().getShip();
        enemyShip = chooseEnemy();
        initHP();
        availableWeapons();
        turnCounter = 1;
        playerTurnDamage = 0;
        enemyTurnDamage = 0;
        playerAcc = 0;
        enemyAcc = 0; 
        group = new ToggleGroup();
        weapons = Shipyard.DEFAULT_WEAPONS;
        turretWeapon = (Weapon)weapons.get("Turret");
        cannonWeapon = (Weapon)weapons.get("Dual Cannon");
        laserWeapon = (Weapon)weapons.get("Oculaser");
        shields = Shipyard.DEFAULT_SHIELDS;
        turret.setToggleGroup(group);
        turret.setUserData("turret");
        cannon.setToggleGroup(group);
        cannon.setUserData("cannon");
        laser.setToggleGroup(group);
        laser.setUserData("laser");
    }
    
    public void endFight() {
        firstPane.getChildren().clear();
        secondPane.getChildren().clear();
        thirdPane.getChildren().clear();
        //lastPane.getChildren().clear();
        endText = new Text("");
        endButton.setVisible(true);
        endButton.setDisable(false);
        firstPane.getChildren().add(endText);
        firstPane.getChildren().add(endButton);
        AnchorPane.setTopAnchor(endText, 15.0);
        AnchorPane.setRightAnchor(endButton, 2.0);
        AnchorPane.setBottomAnchor(endButton, 2.0);
    }
    
    public void nextScreen() {
        controller.setScreen("PlanetScreen");
    }
    
    public int calcDamage(Weapon weap, int rand) {
        return (random.nextInt(2) * stats[3]) + weap.getDamage() + random.nextInt(rand) - random.nextInt(rand);
    }
    
    public void turn() {
        if (group.getSelectedToggle() == null) {
            combatLog.setText("Select a weapon.");
        } else {
            ToggleButton[] toggles = new ToggleButton[]{turret, cannon, laser};
            ToggleButton current = (ToggleButton)group.getSelectedToggle();
            boolean countered = false;
            fight();
            int playerDamage = playerTurnDamage;
            group.selectToggle(toggles[random.nextInt(3)]);
            ToggleButton enemyType = (ToggleButton)group.getSelectedToggle();
            double playerTempAcc = playerAcc;
            fight();
            enemyTurnDamage = playerTurnDamage;
            if (group.getSelectedToggle().getUserData().equals("turret")) {
                enemyAcc = .75;
                enemyTurnDamage -= 8;
                enemyTurnDamage += random.nextInt(5) + random.nextInt(5);
            } else if (group.getSelectedToggle().getUserData().equals("cannon")) {
                enemyAcc = .8;
                enemyTurnDamage -= 22;
                enemyTurnDamage += random.nextInt(12) + random.nextInt(12);
            } else {
                enemyAcc = .95;
                enemyTurnDamage -= 40;
                enemyTurnDamage += random.nextInt(21) + random.nextInt(21);
            }
            if (isGuarding) {
                enemyTurnDamage *= .5;
                if (random.nextInt(12) == 0) {
                    playerDamage = enemyTurnDamage * 6;
                    enemyTurnDamage = 0;
                    playerLastMove = "The enemy's attack bounced off your ship!";
                    countered = true;
                } else if (playerShip.getShields().size() > 0) {
                    playerLastMove = "Your guard is more effective with your shield.";
                    enemyTurnDamage *= .2;
                } else {
                    playerLastMove = "You brace yourself for impact and power up shields...";
                }
                if (!countered) {
                    playerDamage = 0;
                }
            } else if (isFleeing) {
                if (stats[1] + turnCounter < 14) {
                    if (random.nextInt(14 - stats[1] - turnCounter) == 0) {
                        playerLastMove = "You managed to escape!";
                        //end battle with running
                    }
                } else {
                    if (random.nextInt(3) == 0) {
                        playerLastMove = "You managed to escape!";
                        //end battle with running
                    }
                }
            } else {
                if (random.nextDouble() <= enemyAcc) {
                    updateHP(true, enemyTurnDamage);
                    enemyLastMove = "Enemy fires their " + enemyType.getUserData() + " for " + enemyTurnDamage + " damage!";
                } else {
                    enemyLastMove = "Enemy fires a devastating shot...and misses!!";
                }
                if (random.nextDouble() <= playerTempAcc) {
                    updateHP(false, playerDamage);
                    playerLastMove = "You fire your " + current.getUserData() + " for " + playerDamage + " damage!";
                } else {
                    playerLastMove = "You fire a devastating shot...and miss!!";
                }
            }
            if (countered) {
                enemyLastMove = "Enemy blasts themselves for " + playerDamage + " damage!";
            }
            turnCounter++;
            turnCount.setText(String.valueOf(turnCounter));
            combatLog.setText(generateQuip() + "\n" + playerLastMove + "\n" + enemyLastMove);
            group.selectToggle(current);
            if (isFleeing) { 
                endFight();
                endText.setText("You power up your engines and escape!");
            } else if (numPlayerHP == 0 || numEnemyHP == 0) {
                endFight();
                if (numPlayerHP == 0) {
                    endText.setText("Your ship explodes, sending cargo everywhere. You black out...");
                    combatLog.setText("You wake up at your destination with only 250 credits...");
                    Context.getInstance().getPlayer().setCredits(250);
                } else {
                    int reward = getRandom(100, 1000);
                    combatLog.setText("You gained " + reward + " credits!");
                    Context.getInstance().getPlayer().addCredits(reward);
                    endText.setText("The enemy ship blows apart into space dust.");
                }
            }
            isGuarding = false;
            isFleeing = false;
        }
    }
    
    public void fight() {
        int damage1 = 0;
        int damage2 = 0;
        boolean shotTwice = false;
        boolean oneCrit = false;
        boolean twoCrit = false;
        if (group.getSelectedToggle() == null) {
            //System.out.println("Pick an option!");
        } else if (group.getSelectedToggle().getUserData().equals("turret")) {
            playerAcc = .75;
            damage1 = calcDamage(turretWeapon, 6);
            damage2 = calcDamage(turretWeapon, 6);
            if (0 == random.nextInt(4)) {
                shotTwice = true;
                //System.out.println("turret shoots twice!!");
            }
            if (0 == random.nextInt(10)) {
                oneCrit = true;
                //System.out.println("SHOT ONE CRIT!!");
                damage1 *= 2.5;
            }
            if (shotTwice) {
                if (0 == random.nextInt(10)) {
                    if (oneCrit) {
                        //System.out.println("SHOT TWO CRIT!!");
                        twoCrit = true;
                    } else {
                        oneCrit = true;
                    }
                    damage2 *= 3.5;
                }
                int totalDamage = damage1 + damage2;
                //System.out.println("combined turret damage: " + totalDamage);
                playerTurnDamage = damage1 + damage2;
            } else {
                //System.out.println("turret damage: " + damage1);
                playerTurnDamage = damage1;
            }
        } else if (group.getSelectedToggle().getUserData().equals("cannon")) {
            playerAcc = .8;
            damage1 = calcDamage(cannonWeapon, 12);
            //when enem has over 50% HP, 30% crit. else 10%.
            if (numEnemyHP > maxEnemyHP / 2) {
                if (0 == random.nextInt(3)) {
                    damage1 *= 2.5;
                }
            } else if (0 == random.nextInt(10)) {
                damage1 *= 2.5;
            }
            //System.out.println("cannon damage: " + damage1);
            playerTurnDamage = damage1;
        } else {
            playerAcc = .95;
            damage1 = calcDamage(laserWeapon, 25);
            if (0 == random.nextInt(8)) {
                damage1 *= 2.5;
            }
            //hard to dodge laser- 95% accurate, turret is 75 and cannon is 80
            //System.out.println("laser damage: " + damage1);
            playerTurnDamage = damage1;
        }
        //System.out.println("playerTurnDamage: " + playerTurnDamage);
    }
    
    public void guard() {
        isGuarding = true;
        turn();
    }
    
    public void flee() {
        isFleeing = true;
        turn();
    }
    
    /**
     * Initializes the controller class.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
