package robot;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class LandSensorUnitTest {
	
	private  Random aleatoire = new Random();

	@Test
    public void testGetPointToPointEnergyCoefficient(){
        LandSensor landSensor = new LandSensor(aleatoire);
        Coordinates c1 = new Coordinates(8,6);
        Coordinates c2 = new Coordinates(2,2);
        double res1 = landSensor.getPointToPointEnergyCoefficient(c1,c2);
        double d = landSensor.distance(c1,c2);
        double res2 = 1 + d / (d *landSensor.getVal());
        assertEquals(res2,res1,0);
    }

    @Test
    public void testDistance(){

        LandSensor landSensor = new LandSensor(new Random());
        Coordinates c1 = new Coordinates(8,6);
        Coordinates c2 = new Coordinates(2,3);
        assertEquals(5,landSensor.distance(c1,c2),0);

    }

}
