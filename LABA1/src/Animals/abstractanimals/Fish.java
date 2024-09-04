package Animals.abstractanimals;

import Animals.habitatcategory.Aquatic;

public abstract class Fish implements Aquatic{
	String species;
	
	@Override
	public String toString() {
		return "Fish [species=" + species + "]";
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Fish(String species) {
		this.species = species;
	}

	@Override
	public String swim() {
		return "I am a fish that swims in a water";
	}
	
	@Override
	public String sleep(Integer hours) {
		return "I sleep for " + hours + " hours.";
	}
	
	@Override
	public String sleep() {
		return "Sleeping.";
	}
	
	@Override
	public String eat(String food) {
		return "Omnomnom, i eat " + food + ".";
	}
	
	@Override
	public String WhoAmI() {
		return "Don't cry i am just a FISH";
	}
}
