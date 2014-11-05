package spacetrader;

import java.io.Serializable;

/**
 * Market class
 */
public class Market implements Serializable{
    private Planet planet;
    private int[] stock = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private int[] prices = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    
    
    public Market(Planet planet) {
        this.planet = planet;
    }
    
    public void setPrices() {
        for (int i = 0; i < 10; i++) {
            prices[i] = TradeGood.getGoodFromID(i).calcMarketPrice();
        }
    }
    
    public void updateStock() {
        if (stock[0] == -1) {
            for (int i = 0; i < 10; i++) {
                stock[i] = MarketController.calcStock(TradeGood.getGoodFromID(i));
            }
        }
    }
    
    public void resetStock() {
        stock = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    }
    
    public int getStockIndex(int index) {
        return stock[index];
    }
    
    public int[] getPrices() {
        return prices;
    }
    
    public void setStockIndex(int index, int value) {
        stock[index] = value;
    }
}
