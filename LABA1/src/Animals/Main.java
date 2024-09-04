package Animals;

import Animals.Model.Animals;
import Animals.eatingcategory.*;
import Animals.realAnimals.*;

public class Main {

	public static void main(String[] args) {
		Preditor mySamoyedDoggo = new Samoyed(12);
		Animals myBreamFish = new Bream("Bream");
		Pomeranian myPomeranianDoggo = new Pomeranian(13);
		Animals myCyprinusFish = new Cyprinus("Cyprinus");
		
		System.out.println(mySamoyedDoggo.huntFor(myBreamFish));
		System.out.println(mySamoyedDoggo.huntFor(myCyprinusFish));
		
	}

}
