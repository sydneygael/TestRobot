package robot;

import java.util.Random;

public class LandSensor {
	private Random random ;
	private Double val;
	
	public LandSensor(Random r){
		this.random = r;
	}
	
	
	/**
	 * Calcule un facteur de modulation permettant de moduler l'energie consommee dans des conditions ideales.
	 * L'energie requise pour aller d'un point a un autre correspond au facteur  * energie consommee dans des conditions ideales
	 * @param coordinate1
	 * @param coordinate2
	 * @return facteur de modulation de l'energie consommee dans des conditions ideales 
	 */
    public double getPointToPointEnergyCoefficient(Coordinates coordinate1, Coordinates coordinate2) {
        val=random.nextDouble();
        double distance = distance(coordinate1, coordinate2);
        return 1 + distance / (distance *val);
    }

    public double distance(Coordinates coordinate1, Coordinates coordinate2) {
        return Math.sqrt(Math.pow(coordinate1.getX()-coordinate2.getX(), 2) + Math.pow(coordinate1.getX()-coordinate2.getX(),2));
    }


	public Double getVal() {
		return val;
	}


	public void setVal(Double val) {
		this.val = val;
	}
    
}
