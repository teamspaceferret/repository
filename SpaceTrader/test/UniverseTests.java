import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import spacetrader.Context;
import spacetrader.Coordinate;
import spacetrader.Universe;

/**
 *
 * @author keatts
 */
public class UniverseTests {
    private final Universe universe;    
    
    public UniverseTests() {
        universe = new Universe();
        
        universe.generateUniverse();
    }
    
    @Test
    public final void numSolarSystems() {
        assertTrue(universe.getSolarSystems().length == Context.NUM_SOLAR_SYSTEMS);
    }
    
    @Test
    public final void solarSystemArrayLength() {        
        assertThat(universe.getSolarSystems().length,
                equalTo(universe.getSolarSystemNames().length));
    }
    
    @Test
    public final void solarSystemNames() {
        for (int i = 0; i < universe.getSolarSystems().length; i++) {
            assertThat(universe.getSolarSystems()[i].getName(),
                    equalTo(universe.getSolarSystemNames()[i]));
        }
    }
    
    @Test
    public final void otherUniverseIsNotEqual() {
        Universe otherUniverse = new Universe();
        
        otherUniverse.generateUniverse();
        
        assertThat(universe, not(equalTo(otherUniverse)));
    }
    
    @Test
    public final void solarSystemSpacing() {
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
    public final void solarSystemUniqueNames() {
        Set<String> set = new HashSet<>();
        
        set.addAll(Arrays.asList(universe.getSolarSystemNames()));
        
        assertThat(set.size(), equalTo(universe.getSolarSystemNames().length));
    }
}
