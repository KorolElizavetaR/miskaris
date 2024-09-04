package Animals.realAnimals;

import Animals.Model.Animals;
import Animals.abstractanimals.Dog;

public class Samoyed extends Dog{
	
	public Samoyed(Integer sleepingHours) {
		super(sleepingHours);
	}

	@Override
	public void walkOnAGround() {
		System.out.println("*walking*");
	}
	
	@Override
	public String huntFor(Animals an) {
		// TODO Auto-generated method stub
		return "I don't want to hunt \"" + an.WhoAmI() + "\"";
	}

	@Override
	public String Bark() {
		return "Woof! Woof!";
	}

	@Override
	public String sleep(Integer hours) {
		return "*Sleeping*";
	}

}
