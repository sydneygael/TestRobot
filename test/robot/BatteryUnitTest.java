package robot;

import static org.junit.Assert.*;
import org.junit.Test;
import static java.lang.Thread.sleep;

public class BatteryUnitTest {

    @Test
    public void testCharge() {
        Battery cell = new Battery();
        assertEquals(100f, cell.getChargeLevel(),0);
        cell.charge();
        assertEquals(111f, cell.getChargeLevel(),0);
    }

    @Test
    public void testUse() throws InsufficientChargeException {
        Battery cell = new Battery();
        assertEquals(100f, cell.getChargeLevel(),0);
        cell.use(20.1d);
        assertEquals(79.9f,cell.getChargeLevel(),0);
    }

    @Test(expected = InsufficientChargeException.class)
    public void testUseTooMuchEnergY() throws InsufficientChargeException {
        Battery cell = new Battery();
        assertEquals(100f, cell.getChargeLevel(),0);
        cell.use(120);
    }

    @Test
    public void testSetUp() throws InterruptedException {
        Battery cell = new Battery();
        assertEquals(100f,cell.getChargeLevel(),0);
        cell.setUp();
        sleep(1);
        assertEquals(111f,cell.getChargeLevel(),0);
    }

    @Test
    public void timeToSufficientEnnergy(){
        Battery cell = new Battery();
        assertEquals(100f,cell.getChargeLevel(),0);
        long clockTime = cell.timeToSufficientCharge(111);
        assertEquals(1000,clockTime);
    }
  
}