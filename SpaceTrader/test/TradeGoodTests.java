
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.Assert;
import spacetrader.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Dangarr
 */
public class TradeGoodTests {
    
    public static Player player;
    public static Planet planet;
    public static Ship ship;
    public static TradeGood good;
    
    public TradeGoodTests() {
    }

    @BeforeClass
    public static void setUpClass() {
        Context.getInstance().getPlayer().setStats(new int[]{5, 5, 5, 5, 5});
        Context.getInstance().getPlayer().setName("TestPlayer");
        Context.getInstance().getPlayer().setShip(new Ship("mosquito"));
        Context.getInstance().getPlayer().setCurrentPlanet(new Planet());      
        player = Context.getInstance().getPlayer();
        planet = player.getCurrentPlanet();
        planet.setTechLevel(0);
    }

    @Test
    public void waterTestRegToCopy() {
        int[] results = genTestHelper(TradeGood.WATER);
        int price = results[0];
        int priceCopy = results[5];
        assertEquals(price, priceCopy);
    }
    
    @Test
    public void waterTestRegToLow() {
        int[] results = genTestHelper(TradeGood.WATER);
        int price = results[0];
        int lowerPrice = results[4];
        assertTrue(Integer.compare(price, lowerPrice) > 0);
    }
    
    @Test
    public void waterTestHighToHighest() {
        int[] results = genTestHelper(TradeGood.WATER);
        int highestPrice = results[1];
        int higherPrice = results[3];
        assertTrue(Integer.compare(higherPrice, highestPrice) < 0);
    }
    
    @Test
    public void waterTestRegToHigh() {
        int[] results = genTestHelper(TradeGood.WATER);
        int price = results[0];
        int higherPrice = results[3];
        assertTrue(Integer.compare(higherPrice, price) > 0);
    }
    
    @Test
    public void waterTestLowToLowest() {
        int[] results = genTestHelper(TradeGood.WATER);
        int lowerPrice = results[4];
        int lowestPrice = results[2];
        boolean ans = Integer.compare(lowerPrice, lowestPrice) > 0;
        assertTrue(ans);
    }
    
    @Test
    public void waterTestTraderToReg() {
        int[] results = genTestHelper(TradeGood.WATER);
        int price = results[0];
        int lowTraderPrice = results[6];
        assertTrue(Integer.compare(lowTraderPrice, price) < 0);
    }
    
    @Test
    public void waterTestTechLevelToReg() {
        int[] results = genTestHelper(TradeGood.WATER);
        int price = results[0];
        int lowTechLevelPrice = results[7];
        assertTrue(Integer.compare(lowTechLevelPrice, price) > 0);
    }
    
    public int[] genTestHelper(TradeGood good) {
        int price = good.calcMarketPrice();
        planet.setTechLevel(good.getMTLB());
        planet.setEvent(good.getDE());
        planet.setResource(good.getER());
        int highestPrice = good.calcMarketPrice();
        planet.setEvent(good.getIE());
        planet.setResource(good.getCR());
        int lowestPrice = good.calcMarketPrice();
        planet.setEvent(good.getDE());
        int lowerPrice = good.calcMarketPrice();
        planet.setEvent(good.getIE());
        planet.setResource(good.getER());
        int higherPrice = good.calcMarketPrice();
        planet.setEvent(Event.NONE);
        planet.setResource(Resource.NOSPECIALRESOURCES);
        planet.setTechLevel(good.getMTLB());
        int priceCopy = good.calcMarketPrice();
        player.setStats(new int[]{5, 5, 5, 5, 5});
        int lowTraderPrice = good.calcMarketPrice();
        player.setStats(new int[]{1, 1, 1, 1, 1});
        planet.setTechLevel(Context.HI_TECH);
        int lowTechLevelPrice = good.calcMarketPrice();
        int[] results = new int[]{price, highestPrice, lowestPrice, higherPrice,
            lowerPrice, priceCopy, lowTraderPrice, lowTechLevelPrice};
        planet.setTechLevel(0);
        return results;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

