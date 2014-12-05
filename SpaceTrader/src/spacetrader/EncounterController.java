package spacetrader;

import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import spacetrader.SpaceTrader.ControlledScreen;

public class EncounterController implements ControlledScreen, Initializable {
    ScreensController controller;
    private SoundManager soundManager = SoundManager.getSoundManager();
    
    @FXML private ToggleButton turret;
    @FXML private ToggleButton cannon; 
    @FXML private ToggleButton laser;
    @FXML private ToggleGroup group;
    @FXML private ImageView enemyImage;
    @FXML private ImageView playerImage;
    @FXML private Button fire;
    @FXML private Button guard;
    @FXML private Button flee;
    @FXML private Button printWeaps;
    @FXML private Button addT;
    @FXML private Button addC;
    @FXML private Button addL;
    @FXML private TextArea combatLog;
    @FXML private Text playerHP;
    @FXML private Text enemyHP;
    @FXML private Text turnCount;
    @FXML private Text enemyLabel;
    @FXML private Text playerLabel;
    @FXML private ProgressBar playerHPBar;
    @FXML private ProgressBar enemyHPBar;
    @FXML private AnchorPane firstPane;
    @FXML private AnchorPane secondPane;
    @FXML private AnchorPane thirdPane;
    @FXML private AnchorPane lastPane;
    @FXML private Text endText;
    @FXML private Button endButton;
    @FXML private Button buyButton;
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
    private int amt;
    private TradeGood good;
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
    private String type;
    private boolean isGuarding, isFleeing;
    private String pirateType, policeType, traderType;
    
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
        turret.setDisable(true);
        cannon.setDisable(true);
        laser.setDisable(true);
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
    
    private String genRate(String type, double genPolice) {
        if (genPolice == 1.0) {
            return type;
        } else {
            if (random.nextDouble() < genPolice) {
                return type;
            }
            return "";
        }
    }
    
    private void detEncounterRates(Government govt) {
        policeType = genRate("police", (double)govt.getPolice() * .1);
        pirateType = genRate("pirate", (double)govt.getPirate() * .1);
        traderType = genRate("trader", (double)govt.getPirate() * .1);
    }
    
    public String setEncounterType() {
        Planet current = Context.getInstance().getPlayer().getCurrentPlanet();
        Government govt = current.getGovernment();
        policeType = "";
        pirateType = "";
        traderType = "";
        detEncounterRates(govt);
        String[] types = new String[]{policeType, pirateType, traderType};
        String[] trues = new String[3];
        int counter = 0;
        for (String s : types) {
            if (!s.equals("")) {
                trues[counter] = s;
                counter++;
            }
        }
        if (counter == 0 || counter == 3) {
            int temp = random.nextInt(3);
            if (temp == 0 && govt.getPolice() != 0) {
                return policeType;
            } else if (temp == 1 && govt.getPirate() != 0) {
                return pirateType;
            } else if (temp == 2 && govt.getTrader() != 0 ) {
                return traderType;
            }
        } else if (counter == 2) {
            int temp = random.nextInt(2);
            if (temp == 0) {
                return trues[0];
            } else if (temp == 1) {
                return trues[1];
            }
        } else {
            return trues[0];
        }
        return "";
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
    
    public void typeSetUp(String type) {
        if (type.equals("police")) {
            enemyImage.setImage(new Image("spacetrader/resources/police.jpg"));
            playerImage.setImage(new Image("spacetrader/resources/playership.jpg"));
            combatLog.setText("5-0! The cops are on your tail!");
            enemyLabel.setText("Police");
        } else if (type.equals("pirate")) {
            enemyImage.setImage(new Image("spacetrader/resources/pirate.jpg"));
            playerImage.setImage(new Image("spacetrader/resources/playership.jpg"));
            combatLog.setText("Pirates stop you dead in your tracks...they want your cargo!");
            enemyLabel.setText("Pirate");
        } else {
            enemyImage.setImage(new Image("spacetrader/resources/trader.jpg"));
            playerImage.setImage(new Image("spacetrader/resources/playerface.jpg"));
            combatLog.setText("Oh, a trader. Maybe they want to make a deal?");
            enemyLabel.setText("Trader");
        }
    }
    
    public void typeBreakDown(String type) {
        if (type.equals("police")) {
            
        } else if (type.equals("pirate")) {
            
        } else {
            
        }
    }
    
    /**
     * Initializes the screen.
     */
    @Override
    public void initScreen() {
        soundManager.setPrevScreen("Encounter");
        soundManager.setCurrentBG(SoundManager.ENCOUNTER_BG_ID);
        soundManager.playBGWithCheck(SoundManager.ENCOUNTER_BG_ID, SoundManager.ENCOUNTER_BG_PATH);
        random = new Random();
        playerShip = Context.getInstance().getPlayer().getShip();
        enemyShip = chooseEnemy();
        type = setEncounterType();
        //type = "trader";
        typeSetUp(type);
        stats = Context.getInstance().getPlayer().getStats(); 
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
        if (type.equals("trader")) {
            endFightTrader();
        }
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
        firstPane.getChildren().add(buyButton);
        AnchorPane.setTopAnchor(endText, 20.0);
        AnchorPane.setRightAnchor(endButton, 2.0);
        AnchorPane.setBottomAnchor(endButton, 2.0);
    }
    
    public void endFightTrader() {
        endFight();
        buyButton.setVisible(true);
        buyButton.setDisable(false);
        int goodType = random.nextInt(10);
        good = TradeGood.getGoodFromID(goodType);
        amt = getRandom(1, 3);
        int price = amt * (good.calcMarketPrice() - getRandom(25, 40));
        endText.setText("I'll sell you " + amt + " " + good.getName() + "(s) for " + price + " credits! "
                + "(Your credits: " + Context.getInstance().getPlayer().getCredits() + " cr.)");
        
    }
    
    public void buy() {
        endText.setText("Thanks!");
        playerShip.addToCargo(good, amt);
        int creds = Context.getInstance().getPlayer().getCredits();
        Context.getInstance().getPlayer().setCredits(creds - amt);
        buyButton.setDisable(true);
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
