import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import spacetrader.Context;
import spacetrader.Coordinate;
import spacetrader.SolarSystem;
import spacetrader.Universe;

/**
 *
 * @author keatts
 */
public class UniverseTests {
    private Universe universe;    
    
    public UniverseTests() {
        universe = new Universe();
        
        universe.generateUniverse();
    }
    
    @Test
    public void numSolarSystems() {
        assertTrue(universe.getSolarSystems().length == Context.NUM_SOLAR_SYSTEMS);
    }
    
    @Test
    public void solarSystemArrayLength() {        
        assertThat(universe.getSolarSystems().length, equalTo(universe.getSolarSystemNames().length));
    }
    
    @Test
    public void solarSystemNames() {
        for (int i = 0; i < universe.getSolarSystems().length; i++) {
            assertThat(universe.getSolarSystems()[i].getName(), equalTo(universe.getSolarSystemNames()[i]));
        }
    }
    
    @Test
    public void otherUniverseIsNotEqual() {
        Universe otherUniverse = new Universe();
        
        otherUniverse.generateUniverse();
        
        assertThat(universe, not(equalTo(otherUniverse)));
    }
    
    @Test
    public void solarSystemSpacing() {
        Coordinate[] coords = new Coordinate[universe.getSolarSystems().length];
        
        for (int i = 0; i < universe.getSolarSystems().length; i++) {
            for (int j = 0; j < universe.getSolarSystems().length; j++) {
                if (i != j) {
                    assertTrue(universe.getSolarSystems()[i].getCoords().distanceTo(universe.getSolarSystems()[j].getCoords())
                            >= Context.MIN_DISTANCE_BETWEEN_SOLAR_SYSTEMS);
                }
            }
        }
    }
    
    // It is acceptable for this test to fail
    // It just means that we ran out of names and had to reuse some
    @Test
    public void solarSystemUniqueNames() {
        Set<String> set = new HashSet<String>();
        
        set.addAll(Arrays.asList(universe.getSolarSystemNames()));
        
        assertThat(set.size(), equalTo(universe.getSolarSystemNames().length));
    }
}
