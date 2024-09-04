package Animals.realAnimals;

import Animals.Model.Animals;
import Animals.abstractanimals.Fish;
import Animals.eatingcategory.Preditor;

public class Bream extends Fish implements Preditor {

	public Bream(String species) {
		super(species);
	}

	@Override
	public String huntFor(Animals an) {
		return "hunting for " + an.WhoAmI();
	}

}
