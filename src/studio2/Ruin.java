package studio2;
import java.util.Scanner;

public class Ruin {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your starting amount of money: ");
		double startAmount = in.nextDouble();
		
		System.out.print("Enter the probability that you win a single play: ");
		double winChance = in.nextDouble();
		
		System.out.print("Enter your win limit: ");
		double winLimit = in.nextDouble();
		
		System.out.print("Enter the number of days you want to play: ");
		int totalSimulations = in.nextInt();
		System.out.println();
		
		int daysOfRuin = 0;
		double a = (1 - winChance)/winChance;
		double expectedRuin = (Math.pow(a, startAmount) - Math.pow(a, winLimit))/(1 - Math.pow(a, winLimit));
		if (winChance == 0.5) {
			expectedRuin = 1 - (startAmount/winLimit);
		}
		
		for(int i = 1; i <= totalSimulations; i++) {
			int numPlays = 0;
			double dayStartAmount = startAmount;
			while (startAmount > 0 && startAmount < winLimit) {
				double gamble = (int)(Math.random()*11) /10.0;
				if (gamble <= winChance) {
					startAmount++;
				}
				else {
					startAmount--;
				}
				numPlays++;
			}
			System.out.println("simulation day number: " + i);
			System.out.println("number of plays: " + numPlays);
			if(startAmount == 0) {
				System.out.println("The day ended in ruin");
				daysOfRuin++;
			}
			else {
				System.out.println("The day ended in success");
			}
			System.out.println();
			startAmount = dayStartAmount;
		}
		double ruinRate = (double)daysOfRuin/totalSimulations;
		System.out.println("Losses: " + daysOfRuin + " Simiulations: " + totalSimulations);
		System.out.println("Ruin Rate from Simulation: " + ruinRate + " Expected Ruin Rate: " + expectedRuin);
	}

}
