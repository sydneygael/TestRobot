package robot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;


import static robot.Direction.WEST;
import static robot.Direction.EAST;


public class RobotUnitTest {
	

	/**
	 * si le robot n'a pas été poser 
	 * c'est à dire que les coordonnées ne sont pas entrées
	 * lance exception
	 * @throws UnlandedRobotException
	 */
	@Test
	public void testLand() throws UnlandedRobotException {
		Robot robot = new Robot();
		robot.land(new Coordinates(3,0));
		assertEquals(3, robot.getXposition());
		assertEquals(0, robot.getYposition());
	}

	@Test
	public void testRobotMustBeLandedBeforeAnyMove() throws Exception {
		Robot robot = new Robot();
		robot.land(new Coordinates(3,0));
		int currentXposition = robot.getXposition();
		int currentYposition = robot.getYposition();
		robot.moveForward();
		assertEquals(currentXposition, robot.getXposition());
		assertEquals(currentYposition+1, robot.getYposition());
	}


	@Test
	public void testMoveForward() throws UnlandedRobotException {
		Robot robot = new Robot();
		robot.land(new Coordinates(3, 0));
		int currentXposition = robot.getXposition();
		int currentYposition = robot.getYposition();
		robot.moveForward();
		assertEquals(currentXposition, robot.getXposition());
		assertEquals(currentYposition+1, robot.getYposition());

	}

	@Test
	public void testMoveBackward() throws UnlandedRobotException {
        Robot robot = new Robot();
        robot.land(new Coordinates(3,0));
        int currentXposition = robot.getXposition();
        int currentYposition = robot.getYposition();
        robot.moveBackward();
        assertEquals(currentXposition, robot.getXposition());
		assertEquals(currentYposition-1, robot.getYposition());
    }

	@Test
	public void testTurnLeft() throws UnlandedRobotException {
		Robot robot = new Robot();
		robot.land(new Coordinates(3, 0));
		robot.turnLeft();
		assertEquals(WEST, robot.getDirection());
	}

	@Test
	public void testTurnRight() throws UnlandedRobotException {
		Robot robot = new Robot();
		robot.land(new Coordinates(3, 0));
		robot.turnRight();
		assertEquals(EAST, robot.getDirection());
	}

	@Test
	public void testFollowInstruction() throws UnlandedRobotException {
		Robot robot = new Robot();
		robot.land(new Coordinates(5, 7));
		robot.setRoadBook(new RoadBook(Arrays.asList(Instruction.FORWARD, Instruction.FORWARD, Instruction.TURNLEFT, Instruction.FORWARD)));
		robot.letsGo();
		assertEquals(4, robot.getXposition());
		assertEquals(9, robot.getYposition());
	}
	
	/**
	 * test mock question 14
	 */

	    @Test
	    public void testRobotNotEnoughtBattery() throws Exception {
	    	
	        Mockito.when(battery.getChargeLevel()).thenReturn((float) 10.0);
	        //Retourne une exception sur la méthode use de la batterie
	        Mockito.doThrow(new InsufficientChargeException()).when(battery).use(Mockito.anyDouble());
	        
	        Robot robot = new Robot(1.0);
	        robot.land(new Coordinates(3, 0));
	        int positionX = robot.getXposition();
	        int positionY = robot.getYposition();
	        robot.moveForward();
	        
	        assertEquals(positionX, robot.getXposition());
	        assertEquals(positionY, robot.getYposition());
	    }
	    
	    /**
		 * Test des mocks Battery et Landsensor
		 * Batterie rechargé à 100 avec un déplacement à 10
		 * donc le robot avance en Y
		 */
	    @Mock
	    private LandSensor landsensor;
		@Mock
		private Battery battery;
		
		
		@Before
		public void setUp() throws Exception {
			
			battery = org.mockito.Mockito.mock(Battery.class);
			landsensor = org.mockito.Mockito.mock(LandSensor.class);
			//pour l'appel de getPointToPointEnergyCoefficien on retourne 10
	        Mockito.when(landsensor.getPointToPointEnergyCoefficient(Mockito.any(), Mockito.any())).thenReturn((double) 10.0);
	    } 
	    
		
	    @Test
	    public void testRobotEnoughtBattery() throws Exception {
	    	
	        Mockito.when(battery.getChargeLevel()).thenReturn((float) 100.0);
	        
	        Robot robot = new Robot(1.0);
	        robot.land(new Coordinates(3, 0));
	        int positionX = robot.getXposition();
	        int positionY = robot.getYposition();
	        robot.moveForward();
	        
	        assertEquals(positionX, robot.getXposition());
	        assertEquals(positionY+1, robot.getYposition());
	    }
}