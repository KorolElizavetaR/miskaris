package Animals.realAnimals;

import Animals.abstractanimals.Dog;

public class Pomeranian extends Dog{

	public Pomeranian(Integer sleepingHours) {
		super(sleepingHours);
	}

	@Override
	public void walkOnAGround() {
		System.out.println("*running in circles*");
	}

	@Override
	public String Bark() {
		return "bark-bark!";
	}

	@Override
	public String sleep(Integer hours) {
		return "sleeping";
	}

}
