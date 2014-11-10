package spacetrader;

import java.io.Serializable;

/**
 * Market class
 */
public class Market implements Serializable{
    private final Planet planet;
    private int[] stock;
    private final int[] prices;
    
    /**
     * Creates a market with empty stock and no prices.
     * @param planet the planet that the market belongs to.
     */
    public Market(final Planet planet) {
        this.planet = planet;
        this.stock = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        this.prices = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    }
    
    /**
     * Sets the prices for each good in the market.
     */
    public final void setPrices() {
        for (int i = 0; i < TradeGood.NUM_TRADE_GOODS; i++) {
            prices[i] = TradeGood.getGoodFromID(i).calcMarketPrice();
        }
    }
    
    /**
     * Updates stock of each trade good in the market.
     */
    public final void updateStock() {
        if (stock[0] == -1) {
            for (int i = 0; i < TradeGood.NUM_TRADE_GOODS; i++) {
                stock[i] = MarketController.calcStock(TradeGood.getGoodFromID(i));
            }
        }
    }
    
    /**
     * Resets stock to default values.
     */
    public final void resetStock() {
        stock = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    }
    
    /**
     * Gets the stock of the item at the given index.
     * @param index the index of the item to get the stock from
     * @return the number of the item in stock
     */
    public final int getStockIndex(final int index) {
        return stock[index];
    }
    
    /**
     * Gets the prices of the goods in the market.
     * @return the price array
     */
    public final int[] getPrices() {
        return prices;
    }
    
    /**
     * Sets the stock of the good at the given index.
     * @param index the good to set the stock of
     * @param value the value to set the stock as
     */
    public final void setStockIndex(final int index, final int value) {
        stock[index] = value;
    }
}
