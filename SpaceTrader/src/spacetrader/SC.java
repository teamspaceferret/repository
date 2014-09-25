/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spacetrader;

/**
 * This class details all special conditions that can exist on planets.
 *
 */
public enum SC {
    NONE("None", -1), LOTSOFWATER("Lots of water", 0), RICHFAUNA("Rich fauna", 1),
    RICHSOIL("Rich soil", 2), MINERALRICH("Rich in minerals", 3),
    ARTISTIC("Artistic", 4), WARLIKE("Warlike", 5), LOTSOFHERBS("Herbs", 6),
    WEIRDMUSHROOMS("Weird mushrooms", 7), DESERT("Desert", 8),
    LIFELESS("Lifeless", 9), POORSOIL("Poor soil", 10),
    MINERALPOOR("Mineral poor", 11), PACIFIST("Pacifist", 12);
    
    private final String NAME;
    private final int ID;
    
    private SC(String name, int id) {
        NAME = name;
        ID = id;
    }
    
    public String getName() {
        return NAME;
    }
    
    public int getID() {
        return ID;
    }
}
