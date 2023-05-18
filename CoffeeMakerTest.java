package coffeeMaker;
import java.util.*;

public class CoffeeMakerTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean go = true;
		CoffeeMaker cm = new CoffeeMaker();
		while (go) {
			System.out.println("Coffee Maker Menu");
			System.out.println("1 for use case 1");
			System.out.println("2 for use case 2");
			System.out.println("3 for use case 3");
			System.out.println("4 for A) The coffee lady morning routine.");
			System.out.println("5 for B) In the evening, she invited a friend over for coffee.");
			System.out.println("0 for exit");
			System.out.println();
			System.out.print("Input: ");
			String input = sc.next();
			System.out.println();
			
			if (input.equals("0")) {
				go = false;
				System.out.println("Exiting.");
			}
			
			else if (input.equals("1")) {
				cm.emptyPotOnWarmer();
				System.out.println("Coffee Lady fills boiler with water.");
				System.out.println("Coffee Lady puts filter and coffee grounds into filter holder.");
				System.out.println("Coffee Lady loads filter into the receptacle.");
				boolean sprayGo = true;
				String strength = "medium";
				while (sprayGo) {
					System.out.print("Select the coffee strength (strong, medium, light) medium is default: ");
					strength = sc.next();
					
					if ((strength.equals("strong")) || (strength.equals("medium")) || (strength.equals("light"))) {
						cm.sprayStrength(strength);
						sprayGo = false;
					}
					
					else
						System.out.println("Incorrect input, try again.");
				}
				System.out.println("Coffee Lady presses brew button.");
				cm.startBrew();
				cm.startSpray();
				cm.completeBrew(strength);
				System.out.println();
				System.out.println("^^^output for use case 1 is above^^^");
				cm = new CoffeeMaker();
			}
			
			else if (input.equals("2")) {
				cm.emptyPotOnWarmer();
				cm.sprayStrength("medium");
				cm.startBrew();
				cm.startSpray();
				cm.completeBrew("medium");
				cm.potOffWarmer();
				cm.potOnWarmer();
				String[] extras = {"mocha","cinnamon"};
				totalCost(extras);
				System.out.println();
				System.out.println("^^^output for use case 2 is above^^^");
				cm = new CoffeeMaker();
			}
			
			else if (input.equals("3")) {
				cm.emptyPotOnWarmer();
				cm.sprayStrength("medium");
				cm.startBrew();
				cm.startSpray();
				cm.potOffWarmer();
				cm.stopBrew();
				cm.potOnWarmer();
				cm.completeBrew("medium");
				System.out.println();
				System.out.println("^^^output for use case 3 is above^^^");
				cm = new CoffeeMaker();
			}
			
			else if (input.equals("4")) {
				cm.emptyPotOnWarmer();
				cm.sprayStrength("medium");
				cm.startBrew();
				cm.startSpray();
				cm.potOffWarmer();
				cm.stopBrew();
				String[] extras = {"whip"};
				totalCost(extras);
				System.out.println();
				System.out.println("^^^output for A) The coffee lady morning routine is above^^^");
				cm = new CoffeeMaker();
			}
			
			else if (input.equals("5")) {
				cm.emptyPotOnWarmer();
				cm.sprayStrength("light");
				cm.startBrew();
				cm.startSpray();
				cm.potOffWarmer();
				cm.stopBrew();
				String[] extras = {"cinnamon"};
				totalCost(extras);
				cm.potOnWarmer();
				cm.completeBrew("light");
				cm.potOffWarmer();
				extras[0] = "mocha";
				totalCost(extras);
				System.out.println();
				System.out.println("^^^output for B) In the evening, she invited a friend over for coffee^^^");
				cm = new CoffeeMaker();
			}
			
			else
				System.out.println("Incorrect input, try again.");
		}
		sc.close();
	}
	
	public static void totalCost(String[] input) {
		double totalCost = 1.10;
		System.out.println("Total cost is $" + totalCost + ".");
		for (String item: input) {
			if (item.equals("mocha")) {
				totalCost = totalCost + 0.90;
				System.out.println("Total cost with addition of mocha is $" + totalCost + ".");
			}
			else if (item.equals("whip")) {
				totalCost = totalCost + 1.25;
				System.out.println("Total cost with addition of whip is $" + totalCost + ".");
			}
			else if (item.equals("cinnamon")) {
				totalCost = totalCost + 0.90;
				System.out.println("Total cost with addition of cinnamon is $" + totalCost + ".");
			}
		}
		System.out.println("Total cost is $" + totalCost + ".");
	}
}
