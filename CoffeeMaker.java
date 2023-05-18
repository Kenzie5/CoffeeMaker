package coffeeMaker;

public class CoffeeMaker {
	private SprayControl sc;
	private WarmerPlate wp;
	private Boiler b;
	private boolean indicatorLightOn;
	private boolean interupted;
	
	public CoffeeMaker() { 
		this.sc = new SprayControl();
		this.wp = new WarmerPlate();
		this.b = new Boiler();
		indicatorLightOn = false;
		interupted = false;
	}
	
	public void emptyPotOnWarmer() {
		wp.setState("potEmpty");
		System.out.println("Empty Pot placed on warmer plate.");
	}
	
	public void potOnWarmer() {
		wp.setState("potNotEmpty");
		wp.setHeatingElement(true);
		System.out.println("Pot placed on warmer plate.");
		System.out.println("warmer plate turned on to heat pot.");
		
		if (this.getinterupt())
			this.restartBrew();
	}
	
	public void startBrew() {
		b.setPressureReliefValve(false);
		System.out.println("Valve closed, water enters boiler.");
		b.setBoilerEmpty(false);
		b.setHeatingElement(true);
		System.out.println("Water heats till boiling.");
	}
	
	public void sprayStrength(String strength) {
		if (strength.equals("light"))
			sc.setState("fast and strong");
		
		else if (strength.equals("medium"))
			sc.setState("medium");
		
		else if (strength.equals("strong"))
			sc.setState("slow and light");
		
		System.out.println("Coffee strength set to " + strength + ".");
	}
	
	public void startSpray() {
		wp.setState("potNotEmpty");
		wp.setHeatingElement(true);
		System.out.println("Beginning to spray beans and drip coffee into pot.");
		System.out.println("Warmer plate turned on to keep coffee warm.");
	}
	
	public void completeBrew(String strength) {
		b.setBoilerEmpty(true);
		b.setHeatingElement(false);
		b.setPressureReliefValve(true);
		this.setindicatorLight(true);
		System.out.println("Boiler empties and heating element turns off.");
		System.out.println("Pressure relief valve opens to stop flow.");
		System.out.println("Indicator light comes on to show brewing done.");
		System.out.println("Pot of " + strength + " coffee completed.");
	}
	
	public void stopBrew() {
		b.setPressureReliefValve(true);
		b.setHeatingElement(false);
		this.setinterupt(true);
		System.out.println("Brew stops early as pot was taken off warmer plate.");
	}
	
	public void restartBrew() {
		b.setPressureReliefValve(false);
		b.setHeatingElement(true);
		this.setinterupt(false);
		System.out.println("Brew restarts now that pot is returned.");
	}
	
	public void potOffWarmer() {
		wp.setState("warmerEmpty");
		wp.setHeatingElement(false);
		this.setindicatorLight(false);
		System.out.println("Pot taken off warmer plate.");
		System.out.println("warmer plate turned off.");
		System.out.println("indicator light turned off.");
	}
	
	public void setindicatorLight(boolean input) { this.indicatorLightOn = input; }
	public boolean getindicatorLight() { return this.indicatorLightOn; }
	
	public void setinterupt(boolean input) { this.interupted = input; }
	public boolean getinterupt() { return this.interupted; }
}

class SprayControl extends PartWithStates{
	public static String[] states = { "slow and light", "medium", "fast and strong" };
	
	public SprayControl() {
		this.state = states[1];
	}
	
}

class WarmerPlate extends PartWithHeatingAndStates {
	public static String[] states = { "warmerEmpty", "potEmpty", "potNotEmpty" };
	
	public WarmerPlate() {
		this.heatingElementOn = false;
		this.state = states[0];
	}
}

class Boiler extends PartWithHeatingElement {
	public boolean boilerEmpty;
	private boolean pressureReliefValveOpen;
	
	public Boiler() {
		this.heatingElementOn = false;
		this.pressureReliefValveOpen = true;
		this.boilerEmpty = true;
	}
	
	public void setBoilerEmpty(boolean input) { this.boilerEmpty = input; }
	public void setPressureReliefValve(boolean input) { this.pressureReliefValveOpen = input; }
	
	public boolean getPressureReliefValve() { return this.pressureReliefValveOpen; }
	public boolean getBoilerEmpty() { return this.boilerEmpty; }
}

class PartWithStates {
	public static String[] states = new String[3];
	public String state;
	
	public void setState(String input) {
		for (String item: states) {
			if (input.equals(item))
				this.state = item;
		}
	}
	
	public String getState() { return this.state; }
}

class PartWithHeatingElement {
	public boolean heatingElementOn;
	
	public void setHeatingElement(boolean input) { this.heatingElementOn = input; }
	public boolean getHeatingElement() { return this.heatingElementOn; }
}

class PartWithHeatingAndStates extends PartWithStates {
	public boolean heatingElementOn;
	
	public void setHeatingElement(boolean input) { this.heatingElementOn = input; }
	public boolean getHeatingElement() { return this.heatingElementOn; }
}