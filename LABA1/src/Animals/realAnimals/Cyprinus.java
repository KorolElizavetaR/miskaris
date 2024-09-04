package Animals.realAnimals;

import Animals.abstractanimals.Fish;
import Animals.eatingcategory.Herbivores;

public class Cyprinus extends Fish implements Herbivores{

	public Cyprinus(String species) {
		super(species);
	}

	@Override
	public String chewAGrass() {
		return "I don't really chewAGrass(), but still considered to be a herbivore.";
	}
	
	@Override
	public String WhoAmI() {
		return getSpecies();
	}

}
