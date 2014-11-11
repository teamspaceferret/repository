
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import spacetrader.Ship;
import spacetrader.TradeGood;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cora
 */
public class ShipCargoTests {
    
    private final Ship flea;
    private final int NUM = 2;
    
    public ShipCargoTests(){
        flea = new Ship("flea");
    }
    
    @Test
    public final void emptyCargo() {
        assertTrue(flea.isCargoEmpty());
    }
    
    @Test
    public final void addCargoToEmptyShip() {
        assertTrue(flea.addToCargo(TradeGood.FURS, NUM));
        assertFalse(flea.isCargoEmpty());
        assertTrue(flea.getCargoStock(TradeGood.FURS) == NUM);
    }
    
    @Test
    public final void subrtactNonEmptyCargo() {
        assertFalse(flea.removeFromCargo(TradeGood.FURS, NUM));
        assertTrue(flea.isCargoEmpty());
    }
    
    @Test
    public final void subtractFromEmptyCargo() {
        assertTrue(flea.isCargoEmpty());
        assertFalse(flea.removeFromCargo(TradeGood.FIREARMS, 1));
    }
    
    @Test
    public final void addToFullCargo() {
        flea.addToCargo(TradeGood.FURS, flea.getMaxCargoSlots());
        assertTrue(flea.isCargoFull());
        assertFalse(flea.addToCargo(TradeGood.FURS, NUM));
    }
}
